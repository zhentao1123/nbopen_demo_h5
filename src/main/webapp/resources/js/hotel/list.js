var pageIndex = 1;
var arrivalDateMark = moment().format('YYYY-MM-DD');
var departureDateMark = moment().add('days', 1).format('YYYY-MM-DD');
var cityCodeMark = "";
var cityNameMark = "";
$(function() {

    $('#spanOrder').click(function() {
        if ($('#inputUser').val() == null || $('#inputUser').val() == "" || $('#inputUser').val() == "null") {
            window.location = $('#inputLoginPage').val();
        } else {
            window.location = "/view/order/list?user=" + $('#inputUser').val();
        }
    });

    $('#dateSelect').daterangepicker({
        "dateLimit": {
            "days": 30
        },
        "locale": {
            "format": "YYYY-MM-DD",
            "separator": " 到 ",
            "applyLabel": "应用",
            "cancelLabel": "取消",
            "fromLabel": "从",
            "toLabel": "到",
            "customRangeLabel": "Custom",
            "daysOfWeek": [
                "日",
                "一",
                "二",
                "三",
                "四",
                "五",
                "六"
            ],
            "monthNames": [
                "一月",
                "二月",
                "三月",
                "四月",
                "五月",
                "六月",
                "七月",
                "八月",
                "九月",
                "十月",
                "十一月",
                "十二月"
            ],
            "firstDay": 1
        },
        "startDate": moment().format('YYYY-MM-DD'),
        "endDate": moment().add('days', 1).format('YYYY-MM-DD'),
        "minDate": moment().format('YYYY-MM-DD'),
        "maxDate": moment().add('days', 180).format('YYYY-MM-DD')
    }, function(start, end, label) {
        $("[name='daterangepicker_start']").val(start.format("YYYY-MM-DD"));
        $("[name='daterangepicker_end']").val(end.format("YYYY-MM-DD"));
        document.cookie = 'arrivalDate=' + start.format("YYYY-MM-DD");
        document.cookie = 'departureDate=' + end.format("YYYY-MM-DD");
        pageIndex = 1;
        searchHotelList();
    });

    if (getCookie("arrivalDate") != "" && getCookie("departureDate") != "") {
        $("[name='daterangepicker_start']").val(getCookie("arrivalDate"));
        $("[name='daterangepicker_end']").val(getCookie("departureDate"));
        $('#dateSelect').val($("[name='daterangepicker_start']").val() + ' 到 ' + $("[name='daterangepicker_end']").val());
    } else {
        $("[name='daterangepicker_start']").val(moment().format('YYYY-MM-DD'));
        $("[name='daterangepicker_end']").val(moment().add('days', 1).format('YYYY-MM-DD'));
    }

    var cityCodeMark = getCookie("cityCode");
    var cityNameMark = getCookie("cityName");

    var cityPicker = new IIInsomniaCityPicker({
        data: cityData,
        target: '#cityChoice',
        valType: 'k',
        hideCityInput: '#city',
        hideProvinceInput: '#province',
        callback: function(city_id){
            document.cookie = 'cityCode=' + city_id;
            document.cookie = 'cityName=' + escape($('#cityChoice').val());
            pageIndex = 1;
            searchHotelList();
        }
    });

    cityPicker.init();
    if (cityCodeMark != "" && cityNameMark != "" && cityCodeMark != $('#city').val()) {
        $('#city').val(cityCodeMark);
        $('#cityChoice').val(cityNameMark);
    }

    if (getCookie("lowRate") != "" && getCookie("highRate") != "") {
        if (getCookie("lowRate") == "-1") {
            $('#btnRate').html('价格 不限<span style="display: none" lowrate="-1" highrate="-1"></span>' +
                '<span class="caret"></span>');
        } else {
            $('#btnRate').html('价格： '+getCookie("lowRate") + '-' + getCookie("highRate")+'元<span style="display: none" lowrate="-1" highrate="-1"></span>' +
                '<span class="caret"></span>');
        }
        $('#btnRate').find("span:nth-child(1)").attr("lowrate", getCookie("lowRate"));
        $('#btnRate').find("span:nth-child(1)").attr("highrate", getCookie("highRate"));
    }

    if (getCookie("sortType") != "") {
        $('#btnSort').find("span:nth-child(1)").attr("value", getCookie("sortType"));
        if (getCookie("sortType") == "StarRankDesc") {
            $('#btnSort').html('推荐星级降序<span style="display:none;" value="StarRankDesc"></span>' +
                '<span class="caret"></span>');
        } else if(getCookie("sortType") == "RateAsc") {
            $('#btnSort').html('价格升序<span style="display:none;" value="RateAsc"></span>' +
                '<span class="caret"></span>');
        } else if(getCookie("sortType") == "RateDesc") {
            $('#btnSort').html('价格降序<span style="display:none;" value="RateDesc"></span>' +
                '<span class="caret"></span>');
        }

    }
    $('div[name="divSelect"]').find('ul').find('li').bind('click', function(){
        pageIndex = 1;
        $(this).parent().parent().find('button').html($(this).find('a').html() + '<span class="caret"></span>');
        if ($(this).parent().parent().find('button').attr("id") == "btnRate") {
            document.cookie = 'lowRate=' + $('#btnRate').find("span:nth-child(1)").attr("lowrate");
            document.cookie = 'highRate=' + $('#btnRate').find("span:nth-child(1)").attr("highrate");
        }
        if ($(this).parent().parent().find('button').attr("id") == "btnSort") {
            document.cookie = 'sortType=' + $('#btnSort').find("span:nth-child(1)").attr("value");
        }
        searchHotelList();
    });

    var cookieStarRate = getCookie("starRate");
    if (cookieStarRate != "") {
        $('button[name="btnStarRate"]').each(function(i, info) {
            if (cookieStarRate.indexOf(info.getAttribute("value")) >= 0) {
                info.setAttribute("class", "btn btn-info");
            }
        });
    } else {
        $('#btnStarRateDefault').attr("class", "btn btn-info");
    }

    $('button[name="btnStarRate"]').click(function() {
        pageIndex = 1;
        if($(this).attr("id") == "btnStarRateDefault"){
            if ($(this).attr("class").indexOf("btn btn-default") >= 0) {
                $('button[name="btnStarRate"]').each(function(i, info) {
                    if(info.getAttribute("id") != "btnStarRateDefault"){
                        info.setAttribute("class", "btn btn-default");
                    } else {
                        info.setAttribute("class", "btn btn-info");
                    }
                });
            }
        } else {
            if ($(this).attr("class").indexOf("btn btn-default") >= 0) {
                $(this).attr("class", "btn btn-info");
                $('button[name="btnStarRate"]').each(function(i, info) {
                    if(info.getAttribute("id") == "btnStarRateDefault"){
                        info.setAttribute("class", "btn btn-default");
                    }
                });
            } else {
                $(this).attr("class", "btn btn-default");
                var mark = 0;
                $('button[name="btnStarRate"]').each(function(i, info) {
                    if(info.getAttribute("id") != "btnStarRateDefault"
                        && (info.getAttribute("class").indexOf("btn btn-default") >= 0)){
                        mark++;
                    }
                });
                if (mark >= 4) {
                    $('#btnStarRateDefault').attr("class", "btn btn-info")
                }
            }
        }

        $('button[name="btnStarRate"]').each(function(i, info) {
            if (info.getAttribute("class").indexOf("btn btn-info") >= 0) {
                if (info.getAttribute("id") == "btnStarRateDefault") {
                    document.cookie = "starRate=" + "-1";
                    return false;
                } else {
                    document.cookie = "starRate=" + getCookie("starRate=") + info.getAttribute("value") + ",";;
                }
            }
        });
        searchHotelList();
    });

    $('#pullMoreHotel').click(function(){
        pageIndex ++;
        searchHotelList();
    });

    if (getCookie("queryText") != "") {
        $('#inputHotelOrLocation').val(getCookie("queryText"));
    }
    $('#btnSearch').click(function () {
        pageIndex = 1;
        document.cookie = 'queryText=' + escape($('#inputHotelOrLocation').val());
        searchHotelList();
    });

    searchHotelList();
});

function searchHotelList() {

    var city = $('#city').val();
    var arrivalDate = $("[name='daterangepicker_start']").val();
    var departureDate = $("[name='daterangepicker_end']").val();
    var queryText = $('#inputHotelOrLocation').val();
    var starRate = "";

    if (arrivalDate != arrivalDateMark || departureDate != departureDateMark) {
        pageIndex = 1;
    }

    $('button[name="btnStarRate"]').each(function(i, info) {
        if (info.getAttribute("class").indexOf("btn btn-info") >= 0) {
            if (info.getAttribute("id") == "btnStarRateDefault") {
                starRate = "-1";
                return false;
            } else {
                starRate += info.getAttribute("value") + ",";
            }
        }
    });

    var lowRate = parseInt($('#btnRate').find("span:nth-child(1)").attr("lowrate"));
    var highRate = parseInt($('#btnRate').find("span:nth-child(1)").attr("highrate"));

    var sort = $('#btnSort').find("span:nth-child(1)").attr("value");

    if (lowRate == -1) {
        lowRate = null;
    }
    if (highRate == -1) {
        highRate = null;
    }

    if (pageIndex == 1) {
        $('#divHotelList').html("");
    }

    var url = "/api/hotel/getHotelList";

    var req = {
        "cityId": city,
        "arrivalDate": arrivalDate,
        "departureDate" : departureDate,
        "queryText": queryText,
        "highRate": highRate,
        "lowRate": lowRate,
        "sortType": sort,
        "starRate": starRate,
        "pageIndex" : pageIndex
    };

    var result = ajaxCommonForJson(url, "POST", req);

    if (result != null && result.list != null && result.list.length > 0) {
        var hotelList = "";
        $.each(result.list, function (i, info) {

            var currency = "￥ ";
            if (info.currencyCode == "RMB") {
                currency = "￥ ";
            } else if (info.currencyCode == "HKD") {
                currency = "＄ ";
            } else if (info.currencyCode == "MOP") {
                currency = "＄ ";
            } else if (info.currencyCode == "JPY") {
                currency = "￥";
            } else if (info.currencyCode == "TWD") {
                currency = "＄ ";
            }
            hotelList += '<div name="divHotelCard" class="list-group-item"><div class="container my-hotellist">';
            hotelList += '<img src="' + info.thumbnailUrl + '" />';
            hotelList += '<span>' + info.hotelName + '</span><span>' + info.score +'分 <span>' + info.reviewCount + '条点评</span></span><span>' + info.starRate + '</span>';
            hotelList += '<span>' + info.districtName + '/' + info.businessZoneName + '</span><span>' + currency + info.lowRate + '</span>';
            hotelList += '<input style="display: none" value="'+ info.hotelId +'" /></div></div>';
        });
        $('#divHotelList').append(hotelList);

        $('div[name="divHotelCard"]').click(function () {
            var detailUrl = '/view/hotel/detail?hotelId=' + $(this).find('div').find('input').val() + '&arrivalDate=' + $("[name='daterangepicker_start']").val() + '&departureDate=' +$("[name='daterangepicker_end']").val();
            if ($('#inputUser').val() != null && $('#inputUser').val() != "" && $('#inputUser').val() != "null") {
                detailUrl += "&user=" + $('#inputUser').val();
            }
            window.location.href= detailUrl;
        });
    }
    if (result != null && result.haveNextPage) {
        $('#pullMoreHotel').css("display", "block");
        $('#noHotelInfo').css("display", "none");
    } else {
        $('#pullMoreHotel').css("display", "none");
        $('#noHotelInfo').css("display", "block");
    }
    return result;
}

function clickPullMoreHotel(){

    var hotelList = "";
    hotelList += '<div class="list-group-item"><div class="container my-hotellist">';
    hotelList += '<img src="http://www.runoob.com/try/bootstrap/layoutit/v3/default3.jpg" />';
    hotelList += '<span>如家酒店</span><span>4.5分 <span>5808条点评</span></span><span>经济型</span>';
    hotelList += '<span>天安门/王府井商业区</span><span>￥448</span></div></div>';
//	hotelList += '<div id="pullMoreHotel" style="text-align: center;">点击加载更多</div>';
//	$('#pullMoreHotel').remove();
    $('#divHotelList').append(hotelList);
}
