package com.elong.nbopen.api.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * JSON通用处理类
 * 
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容	<br>
 * -------------------------------------------------<br>
 * 2017.07.21   qianqian.xu     1.0			初始化创建<br>
 * </p> 
 * 
 * @author		qianqian.xu
 * @department	northbound
 */
public class JsonUtil {
	
	public static <T> String entity2Json(T t) throws Exception{
		try {
			return JSON.toJSONString(t/*, SerializerFeature.DisableCircularReferenceDetect*/);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @throws Exception 
	 * 
	  * jsonToObjectNew(json字符串转对象，字符和实体对象长度可以不一样)
	  * @Title: jsonToObjectNew
	  * @param @param jsonVal
	  * @param @param clazz
	  * @param @return
	  * @return Object
	  * @throws
	 */
	public static <T> T jsonToObject(String jsonVal,Class<T> clazz) throws Exception{
	
		try {
			return JSONObject.parseObject(jsonVal, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * @throws Exception 
	 * 
	  * jsonToListNew(字符串转换为集合)
	  * @Title: jsonToListNew
	  * @param @param jsonVal
	  * @param @param clazz
	  * @param @return
	  * @return List<T>
	  * @throws
	 */
	public static <T> List<T> jsonToList(String jsonVal,Class<T> clazz) throws Exception{
		
		try {
			return JSONObject.parseArray(jsonVal, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
