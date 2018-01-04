package com.elong.nbopen.api.controller;

import com.elong.nbopen.api.model.city.CityData;
import com.elong.nbopen.api.model.city.CityInfo;
import com.elong.nbopen.api.model.city.HotCity;
import com.elong.nbopen.api.model.city.ProvinceInfo;
import com.elong.nbopen.api.model.dao.db.UserDo;
import com.elong.nbopen.api.model.viewmodel.hotel.ListPageRequest;
import com.elong.nbopen.api.model.viewmodel.hotel.ListPageResult;
import com.elong.nbopen.api.model.viewmodel.hotel.SimpleDetailResult;
import com.elong.nbopen.api.service.HotelListService;
import com.elong.nbopen.api.util.CommonsUtil;
import com.elong.nbopen.api.util.Http;
import com.elong.nbopen.api.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/hotel")
public class HotelListController {

    private static String cityList = "";

    @Autowired
    private HotelListService hotelListService;

    private Logger logger = Logger.getLogger("HotelListController");


    HotelListController() {
        updateCityList();
    }


    @RequestMapping(value = "/getHotelList", method= RequestMethod.POST)
    @ResponseBody
    public ListPageResult<SimpleDetailResult> getHotelList(HttpServletRequest request, @RequestBody ListPageRequest requestBody) {
        ListPageResult<SimpleDetailResult> result = null;

        // 参数校验
        String paramValidate = requestBody.validate();
        if (StringUtils.isNotBlank(paramValidate)) {
            return new ListPageResult<SimpleDetailResult>();
        }

        result = hotelListService.getHotelListApi(requestBody);
        return result != null ? result : new ListPageResult<SimpleDetailResult>();
    }

    /**
     *
     * 更新城市列表
     *
     * @return
     */
    private boolean updateCityList() {

        // 获取geo静态文件
        String url = CommonsUtil.CONFIG_PROVIDAR.getProperty("elong_static_geo_url");
        String responseData = Http.Send("GET", url, "");

        // 保存geo静态文件，有则删除后新建，无则直接新建
        String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/resources/staticfile/geo_cn.xml");
        File tempFile = new File(path);
        if (tempFile.exists()) {
            tempFile.delete();
        }

        Document doc = null;
        try{
            tempFile.createNewFile();
            FileOutputStream out = null;
            out = new FileOutputStream(tempFile);
            out.write(responseData.getBytes());
            out.close();
            SAXReader reader = new SAXReader();
            doc = reader.read(tempFile);

        } catch (Exception e) {
            logger.error(e);
            return false;
        }

        Element root = doc.getRootElement();
        Element item = root.element("HotelGeoList");
        List<Element> listitem = item.elements("HotelGeo");

        CityData cityData = new CityData();
        Map<String, List<CityInfo>> mapProvince = new HashMap<String, List<CityInfo>>();

        List<ProvinceInfo> listProvince = new ArrayList<ProvinceInfo>();
        List<HotCity> listHotCity = new ArrayList<HotCity>();

        for (int i = 0; i < listitem.size(); i++) {
            Element temp = listitem.get(i);

            String cityCode = temp.attributeValue("CityCode");
            String cityName = temp.attributeValue("CityName");
            String provinceId = temp.attributeValue("ProvinceId");
            String provinceName = temp.attributeValue("ProvinceName");

            // 首尔应该是错误数据，过滤掉
            if (cityName.contains("首尔")) {
                continue;
            }

            // 如果map中存在这个省份，那么向其对应的城市列表添加城市
            if (mapProvince.containsKey(provinceId)) {
                CityInfo cityInfo = new CityInfo();
                cityInfo.setId(cityCode);
                cityInfo.setName(cityName);
                mapProvince.get(provinceId).add(cityInfo);
            } else {
                // 如果map中不存在这个省份，那么向map中添加这个省份并生成对应的城市列表
                List<CityInfo> listCityInfo = new ArrayList<CityInfo>();
                CityInfo cityInfo = new CityInfo();
                cityInfo.setId(cityCode);
                cityInfo.setName(cityName);
                listCityInfo.add(cityInfo);
                mapProvince.put(provinceId, listCityInfo);

                // 然后向listProvince新增一个ProvinceInfo节点
                ProvinceInfo provinceInfo = new ProvinceInfo();
                provinceInfo.setId(provinceId);
                // 对省份过长的处理
                if (provinceName.contains("内蒙古")) {
                    provinceName = "内蒙古";
                } else if (provinceName.contains("黑龙江")) {
                    provinceName = "黑龙江";
                } else if (provinceName.contains("广西")) {
                    provinceName = "广西";
                } else if (provinceName.contains("西藏")) {
                    provinceName = "西藏";
                } else if (provinceName.contains("宁夏")) {
                    provinceName = "宁夏";
                } else if (provinceName.contains("新疆")) {
                    provinceName = "新疆";
                }
                provinceInfo.setName(provinceName);
                listProvince.add(provinceInfo);
            }

            // 热门城市是写死的，需要更改的话请直接修改以下代码
            if (cityName.contains("北京") || cityName.contains("上海")
                    || cityName.contains("广州") || cityName.contains("深圳")
                    || cityName.contains("天津") || cityName.contains("南京")) {
                HotCity hotCity = new HotCity();
                hotCity.setId(cityCode);
                hotCity.setName(cityName);
                hotCity.setPid(provinceId);
                hotCity.setPname(provinceName);
                listHotCity.add(hotCity);
            }
        }

        // 将map中的城市列表添加到对应的省份中
        for (ProvinceInfo provinceInfo : listProvince) {
            provinceInfo.setCity(mapProvince.get(provinceInfo.getId()));
        }

        cityData.setHot(listHotCity);
        cityData.setProvince(listProvince);

        try {
            cityList = "var cityData=" + JsonUtil.entity2Json(cityData);
        } catch (Exception e) {
            logger.error(e);
            return false;
        }

        String cityDataPatch = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/resources/js/common/cityData.js");
        File cityDataFile = new File(cityDataPatch);

        try {
            if(!cityDataFile.exists()) {
                cityDataFile.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(cityDataFile);
            fileWriter.write(cityList);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
        return true;
    }
}
