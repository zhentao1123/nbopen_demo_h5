package com.elong.nbopen.api.model.viewmodel.order;

import java.math.BigDecimal;

public class ValidateResult {

    /**
     * 产品是否可用
     */
    private boolean validate;

    /**
     * 产品不可用的原因
     */
    private String errorMessage;

    /**
     * 担保金额
     */
    private BigDecimal guaranteeRate;

    /**
     * 担保金额货币类型
     */
    private String currencyCode;

    /**
     * 产品可用时的一些提示
     */
    private String message;

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BigDecimal getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(BigDecimal guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
