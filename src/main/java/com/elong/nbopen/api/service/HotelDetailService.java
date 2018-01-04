package com.elong.nbopen.api.service;

import com.elong.nbopen.api.model.elong.*;
import com.elong.nbopen.api.model.repository.HotelDetailResult;
import com.elong.nbopen.api.model.viewmodel.hotel.*;
import com.elong.nbopen.api.model.viewmodel.hotel.RatePlanResult;
import com.elong.nbopen.api.model.viewmodel.order.WriteRequest;
import com.elong.nbopen.api.repository.HotelDetailApi;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by user on 2017/12/19.
 */
@Service
public class HotelDetailService {

    private static final long MILLISECONDOFDAY = 1000 * 60 * 60 * 24;

    private Logger logger = Logger.getLogger("HotelListService");

    @Autowired
    private HotelDetailApi hotelDetailApi;

    /**
     *
     * 获取酒店详情页的数据信息
     *
     * @param request
     * @return
     */
    public DetailResult getDetail(DetailRequest request) {
        HotelDetailCondition condition = new HotelDetailCondition();
        condition.setArrivalDate(request.getArrivalDate());
        condition.setDepartureDate(request.getDepartureDate());
        condition.setHotelIds(request.getHotelId());
        condition.setPaymentType(EnumPaymentType.valueOf(CommonService.SALE_PAYMENT_TYPE));
        condition.setOptions("1,2,3,4");

        HotelDetailResult detailResult = null;
        try {
            detailResult = hotelDetailApi.Invoke(condition);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

        // 如果获取失败
        if (detailResult == null || !detailResult.getCode().equals("0") || detailResult.getResult().getHotels().size() <= 0) {
            logger.error(detailResult.getCode());
            return null;
        }

        // 返回参数处理
        DetailResult result = convertToDetail(detailResult.getResult().getHotels().get(0));

        return result;
    }

    /**
     *
     * 提取页面所需的参数
     *
     * @return
     */
    private DetailResult convertToDetail(Hotel hotel) {
        DetailResult result = new DetailResult();

        /************* 酒店基础信息处理 ****************/
        result.setHotelId(hotel.getHotelId());
        result.setFacilities(hotel.getFacilities());

        // 图片的处理
        List<String> images = new ArrayList<String>();
        if (hotel.getImages() != null && hotel.getImages().size() > 0) {
            for (HotelImg hotelImg : hotel.getImages()) {
                if (hotelImg.getLocations() != null && hotelImg.getLocations().size() > 0) {
                    images.add(hotelImg.getLocations().get(0).getUrl());
                }
            }
        }
        if (images.size() <= 0) {
            images.add("/resources/img/no_hap.jpg");
        }
        result.setImages(images);

        // 详细信息的处理
        Map<Integer, String> mapPrepayRule = null;
        Map<Integer, String> mapGuaranteeRule = null;
        if (hotel.getDetail() != null) {

            /******************** 预付规则处理 **********************/
            // Integer 预付规则编号， String 取消规则描述
            mapPrepayRule = new HashMap<Integer, String>();
            if (hotel.getPrepayRules() != null && hotel.getPrepayRules().size() > 0) {
                for (PrepayRule prepayRule : hotel.getPrepayRules()) {
                    if(prepayRule.getChangeRule().name().equals("PrepayNoChange")) {
                        mapPrepayRule.put(prepayRule.getPrepayRuleId(), "不可取消");
                    } else {
                        mapPrepayRule.put(prepayRule.getPrepayRuleId(), "限时取消");
                    }
                }
            }
            /******************** 预付规则处理 **********************/

            /******************** 担保规则处理 **********************/
            // Integer 担保规则编号， String 取消规则描述
            mapGuaranteeRule = new HashMap<Integer, String>();
            if (hotel.getGuaranteeRules() != null && hotel.getGuaranteeRules().size() > 0) {
                for (GuaranteeRule guaranteeRuleRule : hotel.getGuaranteeRules()) {
                    if(guaranteeRuleRule.getChangeRule().name().equals("NoChange")) {
                        mapGuaranteeRule.put(guaranteeRuleRule.getGuranteeRuleId(), "不可取消");
                    } else {
                        mapGuaranteeRule.put(guaranteeRuleRule.getGuranteeRuleId(), "限时取消");
                    }
                }
            }
            /******************** 担保规则处理 **********************/

            result.setHotelName(hotel.getDetail().getHotelName());
            result.setHotelPhone(hotel.getDetail().getPhone());
            result.setAddress(hotel.getDetail().getAddress());
            result.setGeneralAmenities(hotel.getDetail().getGeneralAmenities());
            result.setTraffic(hotel.getDetail().getTraffic());

            if (hotel.getDetail().getStarRate() == 0) {
                if (hotel.getDetail().getCategory() >= 0 && hotel.getDetail().getCategory() <= 2) {
                    result.setStarRate("经济型");
                } else if (hotel.getDetail().getCategory() == 3){
                    result.setStarRate("舒适型");
                } else if (hotel.getDetail().getCategory() == 4){
                    result.setStarRate("高档型");
                } else if (hotel.getDetail().getCategory() == 5){
                    result.setStarRate("豪华型");
                }
            } else {
                switch (hotel.getDetail().getStarRate()) {
                    case 1:
                        result.setStarRate("一星级");
                        break;
                    case 2:
                        result.setStarRate("二星级");
                        break;
                    case 3:
                        result.setStarRate("三星级");
                        break;
                    case 4:
                        result.setStarRate("四星级");
                        break;
                    case 5:
                        result.setStarRate("五星级");
                        break;
                }
            }
        }
        /************* 酒店基础信息处理 ****************/

        /************* 房型信息处理 ****************/
        if (hotel.getRooms() != null && hotel.getRooms().size() > 0) {
            List<RoomResult> roomResultList = new ArrayList<RoomResult>();
            for (Room room : hotel.getRooms()) {
                RoomResult roomResult = new RoomResult();

                /**************** 房型基础信息处理 ***************************/
                roomResult.setRoomId(room.getRoomId());
                if (StringUtils.isBlank(room.getImageUrl())) {
                    roomResult.setImgUrl("/resources/img/no_hap.jpg");
                } else {
                    roomResult.setImgUrl(room.getImageUrl());
                }
                roomResult.setDescription(StringUtils.isBlank(room.getDescription()) ? "暂无详细信息": room.getDescription());
                roomResult.setName(room.getName());
                /**************** 房型基础信息处理 ***************************/

                /**************** 产品信息处理 *******************************/
                if (room.getRatePlans() != null || room.getRatePlans().size() > 0) {
                    Double lowRate = -1.00;
                    String lowRateCurrencyCode = "RMB";
                    List<RatePlanResult> ratePlanResults = new ArrayList<RatePlanResult>();
                    for (ListRatePlan ratePlan : room.getRatePlans()) {
                        RatePlanResult ratePlanResult = new RatePlanResult();
                        ratePlanResult.setRatePlanId(ratePlan.getRatePlanId());
                        ratePlanResult.setCurrencyCode(ratePlan.getCurrencyCode() == null? "RMB": ratePlan.getCurrencyCode().name());
                        ratePlanResult.setRatePlanName(ratePlan.getRatePlanName());
                        ratePlanResult.setTotalRate(ratePlan.getTotalRate());
                        ratePlanResult.setPaymentType(ratePlan.getPaymentType().name());
                        ratePlanResult.setRoomTypeId(ratePlan.getRoomTypeId());

                        // 最低价格判断逻辑
                        if (lowRate < 0) {
                            lowRate = ratePlan.getTotalRate().doubleValue();
                            lowRateCurrencyCode = ratePlan.getCurrencyCode() == null? "RMB": ratePlan.getCurrencyCode().name();

                        } else if (ratePlan.getTotalRate().doubleValue() < lowRate) {
                            if (ratePlan.getTotalRate().doubleValue() > 0) {
                                lowRate = ratePlan.getTotalRate().doubleValue();
                                lowRateCurrencyCode = ratePlan.getCurrencyCode() == null? "RMB": ratePlan.getCurrencyCode().name();
                            }
                        }

                        // 取消规则的简易描述
                        if (ratePlan.getPaymentType().name().equals("Prepay")) {
                            if (StringUtils.isBlank(ratePlan.getPrepayRuleIds())) {
                                ratePlanResult.setCancelRule("免费取消");
                            } else {
                                String[] prepayRuleIds = ratePlan.getPrepayRuleIds().split(",");
                                // 一般不会出现多条规则，如果出现默认使用第一条
                                Integer prepayRuleId = Integer.parseInt(prepayRuleIds[0]);
                                ratePlanResult.setCancelRule(mapPrepayRule.get(prepayRuleId));
                            }
                        } else {
                            if (StringUtils.isBlank(ratePlan.getGuaranteeRuleIds())) {
                                ratePlanResult.setCancelRule("免费取消");
                            } else {
                                String[] guranteeRuleIds = ratePlan.getGuaranteeRuleIds().split(",");
                                Integer guranteeRuleId = Integer.parseInt(guranteeRuleIds[0]);
                                ratePlanResult.setCancelRule(mapGuaranteeRule.get(guranteeRuleId));
                            }
                        }
                        ratePlanResults.add(ratePlanResult);
                    }
                    roomResult.setRatePlanList(ratePlanResults);
                    roomResult.setLowRate(new BigDecimal(lowRate).setScale(1, BigDecimal.ROUND_HALF_UP));
                    roomResult.setCurrencyCode(lowRateCurrencyCode);
                } else {
                    // 无产品房型最低价格为0
                    roomResult.setLowRate(new BigDecimal(0.00));
                    roomResult.setCurrencyCode("RMB");
                }

                /**************** 产品信息处理 *******************************/

                roomResultList.add(roomResult);
            }
            result.setRooms(roomResultList);
        }
        /************* 房型基础信息处理 ****************/

        return result;
    }

    /**
     *
     * 获取订单填写页的酒店产品信息
     *
     * @return
     */
    public DetailForOrderWrite getDetailForOrderWrite(WriteRequest request) {
        HotelDetailCondition condition = new HotelDetailCondition();
        condition.setArrivalDate(request.getArrivalDate());
        condition.setDepartureDate(request.getDepartureDate());
        condition.setHotelIds(request.getHotelId());
        condition.setPaymentType(EnumPaymentType.valueOf(CommonService.SALE_PAYMENT_TYPE));
        condition.setRoomTypeId(request.getRoomTypeId());
        condition.setRatePlanId(request.getRatePlanId());
        condition.setOptions("1,2,4");

        HotelDetailResult detailResult = null;
        try {
            detailResult = hotelDetailApi.Invoke(condition);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

        // 如果获取失败
        if (detailResult == null || !detailResult.getCode().equals("0") || detailResult.getResult().getHotels().size() <= 0) {
            logger.error(detailResult.getCode());
            return null;
        }

        // 返回参数处理
        DetailForOrderWrite result = convertToWrite(detailResult.getResult().getHotels().get(0));

        // 在店天数
        result.setNumberOrDays ((request.getDepartureDate().getTime() - request.getArrivalDate().getTime()) / MILLISECONDOFDAY);

        // 入离日期描述
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request.getArrivalDate());
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        String dateDescription = (calendar.get(Calendar.MONTH)+1)+"月"+(calendar.get(Calendar.DAY_OF_MONTH))+"日（"+weekOfDays[w]+"） - ";
        calendar.setTime(request.getDepartureDate());
        w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        dateDescription += (calendar.get(Calendar.MONTH)+1)+"月"+(calendar.get(Calendar.DAY_OF_MONTH))+"日（"+weekOfDays[w]+"）";
        result.setDateDescription(dateDescription);
        return result;
    }

    /**
     * 转换成订单填写页需要的数据模型
     *
     * @param hotel
     * @return
     */
    private DetailForOrderWrite convertToWrite(Hotel hotel) {
        DetailForOrderWrite result = new DetailForOrderWrite();

        /************* 酒店基础信息处理 ****************/

        // 详细信息的处理
        if (hotel.getDetail() != null) {

            result.setHotelName(hotel.getDetail().getHotelName());

            if (hotel.getDetail().getStarRate() == 0) {
                if (hotel.getDetail().getCategory() >= 0 && hotel.getDetail().getCategory() <= 2) {
                    result.setStarRate("经济型");
                } else if (hotel.getDetail().getCategory() == 3){
                    result.setStarRate("舒适型");
                } else if (hotel.getDetail().getCategory() == 4){
                    result.setStarRate("高档型");
                } else if (hotel.getDetail().getCategory() == 5){
                    result.setStarRate("豪华型");
                }
            } else {
                switch (hotel.getDetail().getStarRate()) {
                    case 1:
                        result.setStarRate("一星级");
                        break;
                    case 2:
                        result.setStarRate("二星级");
                        break;
                    case 3:
                        result.setStarRate("三星级");
                        break;
                    case 4:
                        result.setStarRate("四星级");
                        break;
                    case 5:
                        result.setStarRate("五星级");
                        break;
                }
            }
        }
        /************* 酒店基础信息处理 ****************/

        /************* 房型信息处理 ****************/
        if (hotel.getRooms() != null && hotel.getRooms().size() > 0) {

            /**************** 房型基础信息处理 ***************************/
            Room room = hotel.getRooms().get(0);
            result.setRoomName(room.getName());
            /**************** 房型基础信息处理 ***************************/

            /**************** 产品信息处理 *******************************/
            if (room.getRatePlans() != null || room.getRatePlans().size() > 0) {
                ListRatePlan ratePlan = room.getRatePlans().get(0);
                result.setRatePlanName(ratePlan.getRatePlanName());
                result.setTotalRate(ratePlan.getTotalRate());
                result.setCurrencyCode(ratePlan.getCurrencyCode() == null? "RMB": ratePlan.getCurrencyCode().name());
                result.setTotalRate(result.getTotalRate().setScale(1, BigDecimal.ROUND_HALF_UP));
                result.setInvoiceMode(ratePlan.getInvoiceMode() == null ? null: ratePlan.getInvoiceMode().name());
                result.setMinAmount(ratePlan.getMinAmount());
                result.setPaymentType(ratePlan.getPaymentType().name());
                result.setCustomerType(ratePlan.getCustomerType().name());

                /************* 取消规则处理 *******************/
                if (ratePlan.getPaymentType().name().equals("Prepay")) {
                    if (hotel.getPrepayRules() != null && hotel.getPrepayRules().size() > 0) {
                        result.setCancelRule(hotel.getPrepayRules().get(0).getDescription().substring(hotel.getPrepayRules().get(0).getDescription().indexOf("：")+1));
                    }
                } else {
                    if (hotel.getGuaranteeRules() != null && hotel.getGuaranteeRules().size() > 0) {
                        result.setCancelRule(hotel.getGuaranteeRules().get(0).getDescription().substring(hotel.getGuaranteeRules().get(0).getDescription().indexOf("：")+1));
                    }
                }
                /************* 取消规则处理 *******************/
            } else {
                // 无产品房型最低价格为0
                result.setCurrencyCode("RMB");
                result.setTotalRate(new BigDecimal(0.00));
                result.setRatePlanName("--");
                result.setInvoiceMode("Hotel");
                result.setMinAmount(0);
            }

            /**************** 产品信息处理 *******************************/
        }
        /************* 房型基础信息处理 ****************/

        return result;
    }
}
