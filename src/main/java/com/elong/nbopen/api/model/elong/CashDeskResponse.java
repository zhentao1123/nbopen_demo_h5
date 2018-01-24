package com.elong.nbopen.api.model.elong;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2018/1/22 14:11  qianqian.xu     1.0    	初始化创建<br>
 * </p>
 *
 * @author qianqian.xu
 * @version 1.0
 * @since JDK1.7
 */
public class CashDeskResponse {

    private String errorMessage;

    private String payUrl;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }
}
