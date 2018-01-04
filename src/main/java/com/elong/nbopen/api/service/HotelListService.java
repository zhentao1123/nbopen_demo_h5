package com.elong.nbopen.api.service;

import com.elong.nbopen.api.model.elong.*;
import com.elong.nbopen.api.model.repository.HotelListResult;
import com.elong.nbopen.api.model.viewmodel.hotel.ListPageRequest;
import com.elong.nbopen.api.model.viewmodel.hotel.ListPageResult;
import com.elong.nbopen.api.model.viewmodel.hotel.SimpleDetailResult;
import com.elong.nbopen.api.repository.HotelListApi;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class HotelListService {

    private Logger logger = Logger.getLogger("HotelListService");

    @Autowired
    private HotelListApi hotelListApi;

    /**
     * 默认酒店列表每次查询多少条
     */
    private static int PAGE_SIZE = 10;

    public ListPageResult<SimpleDetailResult> getHotelListApi(ListPageRequest request) {
        HotelListCondition condition = new HotelListCondition();
        condition.setArrivalDate(request.getArrivalDate());
        condition.setDepartureDate(request.getDepartureDate());
        condition.setCityId(request.getCityId());

        condition.setGroupId(null);

        // 如果用户输入了关键词，那么默认智能搜索
        if (StringUtils.isNotBlank(request.getQueryText())) {
            condition.setQueryType(EnumQueryType.Intelligent);
            condition.setQueryText(request.getQueryText());
        } else {
            condition.setQueryType(null);
            condition.setQueryText(null);
        }

        if (StringUtils.isNotBlank(request.getStarRate()) && !request.getStarRate().contains("-1")) {
            condition.setStarRate(request.getStarRate());
        } else {
            condition.setStarRate(null);
        }

        // 只有最低价时最高价默认传入最低价加上65535，只有最高价时最低价默认传入0
        if (request.getLowRate() != null && request.getHighRate() != null) {
            condition.setLowRate(request.getLowRate());
            condition.setHighRate(request.getHighRate());
        } else if (request.getLowRate() != null){
            condition.setLowRate(request.getLowRate());
            condition.setHighRate(request.getLowRate() + 65535);
        } else if (request.getHighRate() != null) {
            condition.setLowRate(0);
            condition.setHighRate(request.getHighRate());
        } else {
            condition.setLowRate(null);
            condition.setHighRate(null);
        }

        if (request.getSortType() != null) {
            condition.setSort(request.getSortType());
        }
        condition.setPageIndex(request.getPageIndex());
        // 默认参数录入
        condition.setPageSize(PAGE_SIZE);
        condition.setCustomerType(CommonService.CUSTOMER_TYPE);
        condition.setPaymentType(EnumPaymentType.valueOf(CommonService.SALE_PAYMENT_TYPE));
        condition.setResultType("1,3,4,5");

        HotelListResult result = null;

        // TODO:异常日志和接口返回错误码日志应该分开记录
        try {
            result = hotelListApi.Invoke(condition);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }

        // 如果获取失败
        if (result == null || !result.getCode().equals("0")) {
            logger.error(result.getCode());
            return null;
        }

        // 返回参数的处理
        ListPageResult<SimpleDetailResult> listResult = new ListPageResult<SimpleDetailResult>();
        listResult.setCount(result.getResult().getCount());
        if (request.getPageIndex() * PAGE_SIZE < result.getResult().getCount()){
            listResult.setHaveNextPage(true);
        } else {
            listResult.setHaveNextPage(false);
        }
        listResult.setList(convertToDetailForListPagers(result.getResult().getHotels()));
        return listResult;
    }

    /**
     *
     * 提取页面展示所需要的信息
     *
     * @param listHotel
     * @return
     */
    private List<SimpleDetailResult> convertToDetailForListPagers(List<Hotel> listHotel) {
        List<SimpleDetailResult> result = new ArrayList<SimpleDetailResult>();
        if (listHotel == null) {
            return result;
        }
        if (listHotel.size() > 0) {
            for (Hotel hotel : listHotel) {
                SimpleDetailResult simple = new SimpleDetailResult();
                simple.setHotelId(hotel.getHotelId());
                simple.setHotelName(hotel.getDetail().getHotelName());
                simple.setLowRate(hotel.getLowRate());
                simple.setDistrictName(StringUtils.isBlank(hotel.getDetail().getDistrictName())? "--": hotel.getDetail().getDistrictName());
                simple.setBusinessZoneName(StringUtils.isBlank(hotel.getDetail().getBusinessZoneName())? "--": hotel.getDetail().getBusinessZoneName());
                if (hotel.getCurrencyCode() == null) {
                    simple.setCurrencyCode("RMB");
                } else {
                    simple.setCurrencyCode(hotel.getCurrencyCode().name());
                }

                // 有图片则用图片，没有则使用默认图片
                if (StringUtils.isNotBlank(hotel.getDetail().getThumbNailUrl())) {
                    simple.setThumbnailUrl(hotel.getDetail().getThumbNailUrl());
                } else {
                    simple.setThumbnailUrl("/resources/img/no_hap.jpg");
                }

                if (hotel.getDetail().getStarRate() == 0) {
                    if (hotel.getDetail().getCategory() >= 0 && hotel.getDetail().getCategory() <= 2) {
                        simple.setStarRate("经济型");
                    } else if (hotel.getDetail().getCategory() == 3){
                        simple.setStarRate("舒适型");
                    } else if (hotel.getDetail().getCategory() == 4){
                        simple.setStarRate("高档型");
                    } else if (hotel.getDetail().getCategory() == 5){
                        simple.setStarRate("豪华型");
                    }
                } else {
                    switch (hotel.getDetail().getStarRate()) {
                        case 1:
                            simple.setStarRate("一星级");
                            break;
                        case 2:
                            simple.setStarRate("二星级");
                            break;
                        case 3:
                            simple.setStarRate("三星级");
                            break;
                        case 4:
                            simple.setStarRate("四星级");
                            break;
                        case 5:
                            simple.setStarRate("五星级");
                            break;
                    }
                }

                if (hotel.getDetail().getReview() != null) {
                    if (hotel.getDetail().getReview().getCount().equals("0")) {
                        simple.setScore("暂无评");
                    } else {
                        Double score = Double.parseDouble(hotel.getDetail().getReview().getGood()) / Double.parseDouble(hotel.getDetail().getReview().getCount()) * 5.0;
                        BigDecimal bigscore = new BigDecimal(score);
                        simple.setScore(((Double)(bigscore.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue())).toString());

                    }
                    simple.setReviewCount(hotel.getDetail().getReview().getCount());
                } else {
                    simple.setScore("--");
                    simple.setReviewCount("--");
                }
                result.add(simple);
            }
        }
        return result;
    }
}
