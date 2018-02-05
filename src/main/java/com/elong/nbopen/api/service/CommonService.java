package com.elong.nbopen.api.service;

import com.elong.nbopen.api.util.CommonsUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by user on 2017/12/14.
 */
@Service
public class CommonService {

    private Logger logger = Logger.getLogger("CommonService");
    /**
     * 代理售卖哪种付款类型的产品
     * 默认为所有产品
     */
    public static String SALE_PAYMENT_TYPE = "All";

    /**
     * 列表查询时的宾客类型
     * 默认为所有宾客类型
     */
    public static String CUSTOMER_TYPE = "None";

    /**
     * 确认类型
     */
    public static String CONFIRMATION_TYPE = "SMS_cn";

    /**
     * 代理的用户登录页面
     * 默认为测试登录页面
     */
    public static String LOGIN_PAGE = "/test/login";

    public static String ORDER_DOMAIN = "";

    /**
     * 订单增量更新间隔
     */
    public static Long INCR_ORDER_FREQUENCY = 60L * 1000L;

    public static String MAC_ADDR = "";


    CommonService() {
        String salePaymentType = CommonsUtil.CONFIG_PROVIDAR.get("sale_payment_type").toString();
        if (StringUtils.isNotBlank(salePaymentType)) {
            SALE_PAYMENT_TYPE = salePaymentType;
        }

        String customerType = CommonsUtil.CONFIG_PROVIDAR.get("customer_type").toString();
        if (StringUtils.isNotBlank(customerType)) {
            CUSTOMER_TYPE = customerType;
        }

        String loginPage = CommonsUtil.CONFIG_PROVIDAR.get("login_page").toString();
        if (StringUtils.isNotBlank(loginPage)) {
            LOGIN_PAGE = loginPage;
        }

        String confirmationType = CommonsUtil.CONFIG_PROVIDAR.get("confirmation_type").toString();
        if (StringUtils.isNotBlank(confirmationType)) {
            CONFIRMATION_TYPE = confirmationType;
        }

        String orderDomain = CommonsUtil.CONFIG_PROVIDAR.get("order_domain").toString();
        if (StringUtils.isNotBlank(orderDomain)) {
            ORDER_DOMAIN = orderDomain;
        }

        try {
            Long incrOrderFrequency = Long.parseLong(CommonsUtil.CONFIG_PROVIDAR.get("incr_order_frequency").toString());
            INCR_ORDER_FREQUENCY = incrOrderFrequency * 1000;
        } catch (Exception e) {
            logger.error("初始化订单更新频率失败" + e);
        }

        if (!initMacAddr()) {
            logger.error("初始化mac地址失败");
        }
    }

    /**
     * 初始化mac地址
     */
    public static boolean initMacAddr() {
        try {
            byte[] mac = null;
            Enumeration<NetworkInterface> inters =  NetworkInterface.getNetworkInterfaces();
            while(inters.hasMoreElements()) {
                NetworkInterface inter = inters.nextElement();
                mac =inter.getHardwareAddress();
                if (mac != null && mac.length > 0) {
                    StringBuffer sb = new StringBuffer();
                    for(int j=0;j<mac.length;j++){
                        //mac[i] & 0xFF 是为了把byte转化为正整数
                        String s = Integer.toHexString(mac[j] & 0xFF);
                        sb.append(s.length()==1?0+s:s);
                    }
                    MAC_ADDR = sb.toString().toUpperCase();
                    break;
                }
            }

            if (StringUtils.isNotBlank(MAC_ADDR)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
