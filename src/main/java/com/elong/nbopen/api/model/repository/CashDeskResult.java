package com.elong.nbopen.api.model.repository;

import com.alibaba.fastjson.annotation.JSONField;
import com.elong.nbopen.api.model.elong.CashDeskResponse;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2018/1/22 14:12  qianqian.xu     1.0    	初始化创建<br>
 * </p>
 *
 * @author qianqian.xu
 * @version 1.0
 * @since JDK1.7
 */
public class CashDeskResult extends BaseResult{
    @JSONField(name="Result")
    private CashDeskResponse result;

    public CashDeskResponse getResult() {
        return result;
    }
    public void setResult(CashDeskResponse result) {
        this.result = result;
    }
}