package com.elong.nbopen.api.model.viewmodel.order;

import java.math.BigDecimal;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2018/1/22 14:23  qianqian.xu     1.0    	初始化创建<br>
 * </p>
 *
 * @author qianqian.xu
 * @version 1.0
 * @since JDK1.7
 */
public class GetCashDeskRequest {

    private long orderId;
    private BigDecimal amount;
    private String successUrl;
    private String errorUrl;
    private String cancelUrl;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String validate() {
        if (amount == null) {
            return "付款金额不可为空";
        }
        return null;
    }
}
