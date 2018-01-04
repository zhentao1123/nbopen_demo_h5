package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.EnumLocal;
import com.elong.nbopen.api.model.repository.BaseRequest;
import com.elong.nbopen.api.util.*;

import javax.xml.bind.JAXBException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by user on 2017/12/13.
 */
public abstract class BaseApi<T,T2> {
    private static String appUser = null;
    private static String appKey = null;
    private static String appSecret = null;
    private static double version = 1.1;
    private static EnumLocal locale = EnumLocal.zh_CN;
    private static String serverHost = null;

    //返回实体的类型
    public Class <T2>  T2Class = null;

    /**
     *
     * (构造函数说明)
     *
     */
    @SuppressWarnings("unchecked")
    public BaseApi(){
        //通过反射获取返回实体的类型数据
        ParameterizedType type =(ParameterizedType) (getClass().getGenericSuperclass());
        java.lang.reflect.Type[]  types = type.getActualTypeArguments();
        if(types!= null && types.length >1) {
            T2Class  =  (Class < T2 > ) types[ 1 ];
        }

        //加载配置信息
        loadConf();
    }

    /**
     *
     * 加载所有配置信息
     *
     */
    private  void loadConf() {
        appUser = CommonsUtil.CONFIG_PROVIDAR.getProperty("username");
        appKey = CommonsUtil.CONFIG_PROVIDAR.getProperty("appkey");
        appSecret = CommonsUtil.CONFIG_PROVIDAR.getProperty("secretkey");
        serverHost = CommonsUtil.CONFIG_PROVIDAR.getProperty("elong_openapi_url");
        version = Double.parseDouble(CommonsUtil.CONFIG_PROVIDAR.getProperty("version"));
        String localeString = CommonsUtil.CONFIG_PROVIDAR.getProperty("local");
        if("en_US".equals(localeString)) {
            locale = EnumLocal.en_US;
        } else {
            locale = EnumLocal.zh_CN;
        }

        if(appUser == null || appUser.equals("")) {
            System.out.println("ERROR - plz conf appUser in  test.conf\r\n");
        } else if(appKey == null || appKey.equals("")) {
            System.out.println("ERROR - plz conf appKey in  test.conf\r\n");
        } else if(appSecret == null || appSecret.equals("")) {
            System.out.println("ERROR - plz conf appSecret in  test.conf\r\n");
        } else if(serverHost == null || serverHost.equals("")) {
            System.out.println("ERROR - plz conf serverHost in  test.conf\r\n");
        }
    }

    /***********************************************start 抽象方法 start******************************************************/
    public abstract String method() ;
    public abstract boolean isRequiredSSL();
    /*********************************************** end 抽象方法 end******************************************************/

    /**
     * 是否打印请求参数与返回参数
     */
    private boolean isVerbosed = true;

    /**
     * 请求、返回参数类型：json/xml
     */
    private String format = "json";

    /**
     * elong api 返回的字符串
     */
    private String responseData;

    /**
     *
     * 提供外部最终调用的方法
     *
     * @param t
     * @return
     * @throws Exception
     */
    public T2 Invoke (T t) throws Exception {
        if(appUser == null || appUser.equals("")) {
            System.out.println("please set appUser and keys.");
            return null;
        }
        T2 result = null;
        BaseRequest<T> req = new BaseRequest<T>();
        req.setVersion(version);
        req.setLocal(locale);
        req.setRequest(t);

        String str = null;
        if(format.equals("xml")) {
            str = XmlUtil.objectToXml(req);
        }else {
            str = JsonUtil.entity2Json(req);
        }
        if(isVerbosed){
            System.out.println("request info:" + str);
        }

        //产生签名
        long epoch = System.currentTimeMillis()/1000;
        String sig = Tool.md5(epoch + Tool.md5(str + appKey) + appSecret);

        //产生请求链接
        String url = "http"+(this.isRequiredSSL() ? "s": "")+"://"+serverHost+"/rest?format="+ this.format +"&method=";
        url += this.method();
        url += "&user="+ appUser +"&timestamp=";
        url += epoch;
        url += "&signature=";
        url += sig;
        url += "&data=" + Tool.encodeUri(str);;
        if(isVerbosed){
            System.out.println();
            System.out.println("request url:" + url);
        }

        //发送请求
        responseData = Http.Send("GET", url, "");
        if(isVerbosed){
            System.out.println();
            System.out.println("response raw data:" + responseData);
        }
        responseData = responseData.trim();
        responseData = responseData.replaceAll("0001-01-01T00:00:00", "2001-01-01T00:00:00");

        if(format.equals("xml")) {
            result = XmlUtil.xmlToObject(responseData, T2Class);
        }else {
            result = JsonUtil.jsonToObject(responseData, T2Class);
        }
        return result;
    }

    public String getResponseData() {
        return responseData;
    }
    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public void setVerbose(boolean v) {
        isVerbosed = v;
    }

    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
}
