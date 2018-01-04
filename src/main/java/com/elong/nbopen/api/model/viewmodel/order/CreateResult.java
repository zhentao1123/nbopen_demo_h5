package com.elong.nbopen.api.model.viewmodel.order;

public class CreateResult {

    /**
     * 是否创建订单成功
     */
    private Boolean success = false;

    /**
     * 失败原因或提醒
     */
    private String errorMessage;

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 是否需要支付或者担保
     */
    private Boolean isNeedPay;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean getNeedPay() {
        return isNeedPay;
    }

    public void setNeedPay(Boolean needPay) {
        isNeedPay = needPay;
    }
}
