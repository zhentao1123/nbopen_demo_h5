package com.elong.nbopen.api.repository;

import com.elong.nbopen.api.model.elong.CashDestRequest;
import com.elong.nbopen.api.model.repository.CashDeskResult;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 修改历史:											<br>
 * 修改日期    		修改人员   	版本	 		修改内容<br>
 * -------------------------------------------------<br>
 * 2018/1/22 14:16  qianqian.xu     1.0    	初始化创建<br>
 * </p>
 *
 * @author qianqian.xu
 * @version 1.0
 * @since JDK1.7
 */
@Repository
public class GetCashDeskApi extends BaseApi<CashDestRequest, CashDeskResult>{
    @Override
    public String method() {
        return "hotel.order.cashdesk";
    }

    @Override
    public boolean isRequiredSSL() {
        return true;
    }
}
