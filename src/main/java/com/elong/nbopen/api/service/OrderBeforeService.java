package com.elong.nbopen.api.service;

import com.elong.nbopen.api.model.elong.ValidateCondition;
import com.elong.nbopen.api.model.repository.DataValidateResult;
import com.elong.nbopen.api.model.viewmodel.order.ValidateRequest;
import com.elong.nbopen.api.model.viewmodel.order.ValidateResult;
import com.elong.nbopen.api.repository.HotelDataValidateApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017/12/21.
 */

/**
 * 成单前校验
 */
@Service
public class OrderBeforeService {

    private Logger logger = Logger.getLogger("HotelListService");

    @Autowired
    private HotelDataValidateApi hotelDataValidateApi;

    public ValidateResult checkProduct (ValidateRequest request) {
        ValidateResult result = new ValidateResult();

        ValidateCondition condition = new ValidateCondition();
        condition.setArrivalDate(request.getArrivalDate());
        condition.setDepartureDate(request.getDepartureDate());
        condition.setEarliestArrivalTime(request.getEarliestArrivalTime());
        condition.setLatestArrivalTime(request.getLatestArrivalTime());
        condition.setHotelId(request.getHotelId());
        condition.setRoomTypeId(request.getRoomTypeID());
        condition.setRatePlanId(request.getRatePlanId());
        condition.setTotalPrice(request.getTotalPrice());
        condition.setNumberOfRooms(request.getNumberOfRooms());

        DataValidateResult validateResult = null;
        try {
            validateResult = hotelDataValidateApi.Invoke(condition);
        } catch (Exception e) {
            logger.error(e);
        }

        if (validateResult != null) {
            if (validateResult.getCode().equals("0")) {
                switch (validateResult.getResult().getResultCode()) {
                    case OK:
                        // 可以正常预订
                        result.setValidate(true);
                        if (validateResult.getResult().getGuaranteeRate() != null && validateResult.getResult().getGuaranteeRate().doubleValue() > 0) {
                            result.setMessage("下单时需要支付担保金额：" + (validateResult.getResult().getCurrencyCode() == null?"RMB": validateResult.getResult().getCurrencyCode().name())
                                    + validateResult.getResult().getGuaranteeRate()+",确认下单吗？");
                            result.setGuaranteeRate(validateResult.getResult().getGuaranteeRate());
                            result.setCurrencyCode(validateResult.getResult().getCurrencyCode() == null?"RMB": validateResult.getResult().getCurrencyCode().name());
                        }
                        break;
                    case Product:
                        // 产品有误
                        result.setErrorMessage("该产品不可订， 请更换房型或酒店");
                        break;
                    case Rate:
                        // 总价有误
                        result.setErrorMessage("价格有误或价格有变动，请刷新本页面以获取最新价格");
                        break;
                    case Inventory:
                        // 房量不足
                        result.setErrorMessage("房量不足，请减少预订房间数量");
                        break;
                }
            } else {
                result.setErrorMessage("该房型已满房，请更换房型或酒店");
            }
        } else {
            result.setErrorMessage("未知错误");
        }
        return result;
    }
}
