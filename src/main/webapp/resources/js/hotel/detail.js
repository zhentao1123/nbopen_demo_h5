/**
 * Created by user on 2017/12/6.
 */
$(function() {

    if ($("#inputHotelId").val() == null || $("#inputHotelId").val() == "") {
        alert("没有获取到HotelId，请关闭本页面");
    }
    $('#spanBack').click(function() {
        var hotelListUrl = '/view/hotel/list';
        if ($('#inputUser').val() != null || $('#inputUser').val() != "" || $('#inputUser').val() != "null") {
            hotelListUrl += "?user=" + $('#inputUser').val();
        }
        window.location = hotelListUrl;
    });

    $('#spanOrder').click(function() {
        if ($('#inputUser').val() == null || $('#inputUser').val() == "" || $('#inputUser').val() == "null") {
            window.location = $('#inputLoginPage').val();
        } else {
            window.location = "/view/order/list?user=" + $('#inputUser').val();
        }
    });

    getDetail();

    //$("[name='daterangepicker_end']").val(initDepartureDate);
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
        "startDate": $("[name='daterangepicker_start']").val(),
        "endDate": $("[name='daterangepicker_end']").val(),
        "minDate": moment().format('YYYY-MM-DD'),
        "maxDate": moment().add('days', 180).format('YYYY-MM-DD')
    }, function(start, end, label) {
        $("[name='daterangepicker_start']").val(start.format("YYYY-MM-DD"));
        $("[name='daterangepicker_end']").val(end.format("YYYY-MM-DD"));
        getDetail();
    });

    $('div[name="divRoomDetail"]').click(function() {
        var arrow = $(this).find("span").eq(4).find("i");
        if (arrow.attr('class') == 'glyphicon glyphicon-chevron-right') {
            arrow.attr('class', 'glyphicon glyphicon-chevron-down');
        } else {
            arrow.attr('class', 'glyphicon glyphicon-chevron-right');
        }

    });
});

function getDetail() {
    if ($("#inputHotelId").val() == null || $("#inputHotelId").val() == "" || $("#inputHotelId").val() == "null") {
        alert("没有获取到HotelId，请关闭本页面");
        return;
    }

    var url = "/api/hotel/getDetail";

    var req = {
        "arrivalDate": $("[name='daterangepicker_start']").val(),
        "departureDate": $("[name='daterangepicker_end']").val(),
        "hotelId": $("#inputHotelId").val()
    };

    var result = ajaxCommonForJson(url, "POST", req);

    if (result != null) {
        // 轮播图处理
        $('#divHotelImageList').html("");
        listImageHtml = "";
        $.each(result.images, function (i, info) {
            if (i == 0) {
                listImageHtml += '<div class="item active"> <img src="' + info + '" alt="First slide"> </div>';
            } else if (i < 5) {
                listImageHtml += '<div class="item"> <img src="' + info + '" alt="First slide"> </div>';
            }
        });

        $('#divHotelImageList').html(listImageHtml);
        $("#carouselHotelImage").carousel('cycle');

        // 酒店简要信息处理
        var hotelName = "";
        hotelName = result.hotelName + '<span>'+result.starRate+'</span>';
        $('#divHotelName').html(hotelName);

        var addresses = new Array();
        addresses = result.address.split(/[()]/);

        var hotelSimpleInfoHtml = "";
        hotelSimpleInfoHtml += '<p>'+addresses[0];
        if (addresses.length > 1) {
            hotelSimpleInfoHtml += '<span class="em">' + addresses[1] + '</span>';
        }
        hotelSimpleInfoHtml += '</p>';
        hotelSimpleInfoHtml += '<p><span class="tel"><i class="glyphicon glyphicon-earphone"></i>'+result.hotelPhone+'</span>' +
            '<span class="more" id="aMoreHotelInfo">更多信息<i class="glyphicon glyphicon-chevron-right"></i></span></p>';
        $('#divHotelSimpelInfo').html(hotelSimpleInfoHtml);

        // 酒店经纬度处理
        $('#allmap').click(function () {
            var mapUrl = 'http://api.map.baidu.com/marker?location='+result.latitude+','+result.longitude+'&title=酒店位置&content='+result.hotelName+'&output=html';
            window.open(mapUrl);
        });
        // var map = new BMap.Map("allmap");
        // var point = new BMap.Point(result.longitude, result.latitude);
        // map.centerAndZoom(point, 12);
        // map.addOverlay(new BMap.Marker(point));
        // // 初始化地图， 设置中心点坐标和地图级别
        // var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL});
        // map.addControl(top_right_navigation);

        // 酒店设施信息处理
        var facilityHtml = "";
        if (result.facilities != null && result.facilities != "") {
            var facility = result.facilities.split(",");
            if (facility.length > 0) {
                var tempfacility
                $.each(facility, function (i, info) {
                    tempfacility = transFacility(info);
                    if (tempfacility != "") {
                        facilityHtml += tempfacility;
                    }
                });
                if (tempfacility != "") {
                    $('#divFacilities').html('<div class="panel-heading">'
                        + '<h3 class="panel-title">酒店设施</h3>'
                        + '</div> <div class="panel-body">'
                        + facilityHtml + '</div>');
                }
            }
        }
        if (facilityHtml == "") {
            $('#divFacilities').remove();
        }

        // 酒店服务信息处理
        if (result.generalAmenities != null && result.generalAmenities != "") {
                $('#divGeneralAmenities').html('<div class="panel-heading">'
                    + '<h3 class="panel-title">酒店服务</h3>'
                    + '</div> <div class="panel-body">'
                    + result.generalAmenities + '</div>');
        } else {
            $('#divGeneralAmenities').remove();
        }

        // 酒店交通信息处理
        if (result.traffic != null && result.traffic != "") {
            var traffic = result.traffic.replace(/\n/g,"<br/>");
            $('#divTraffic').html('<div class="panel-heading">'
                + '<h3 class="panel-title">周边信息</h3>'
                + '</div> <div class="panel-body">'
                + traffic + '</div>');
        } else {
            $('#divTraffic').remove();
        }

        // 房型信息处理
        if (result.rooms != null && result.rooms.length > 0) {
            var listRoomHtml = "";
            $.each(result.rooms,function (i, roomInfo) {
                var roomHtml = '<div class="panel"> <div name="divRoomDetail" class="panel-heading collapsed my-room-info" data-toggle="collapse" data-parent="#accordion" href="#collapse'+ i +'">'
                        + '<img src="' + roomInfo.imgUrl + '" /><div>'
                        + '<span>'+ roomInfo.name +'</span>'
                        + '<span>'+ roomInfo.description +'</span>'
                        + '<span>'+ changeCurrency(roomInfo.currencyCode) + roomInfo.lowRate +'<span>起</span></span>'
                        + '<span><i class="glyphicon glyphicon-chevron-right"></i></span> </div> </div>'
                        + '<input style="display: none" value="'+ roomInfo.roomId +'" />';

                // 产品处理
                if (roomInfo.ratePlanList != null && roomInfo.ratePlanList.length > 0) {
                    roomHtml += '<div id="collapse'+ i +'" class="panel-collapse collapse my-rateplan-info">';
                    $.each(roomInfo.ratePlanList, function (i, ratePlanInfo) {
                        roomHtml += '<div class="panel-body">' +
                            '                    <p class="p1">' + ratePlanInfo.ratePlanName + '</p>' +
                            '                    <p class="p2">'+ (ratePlanInfo.cooperationType == 1? "艺龙": "代理") + "&nbsp;&nbsp;&nbsp;" + ratePlanInfo.cancelRule +'</p>' +
                            '                    <div class="divBooking">' +
                            '                        <span class="price">'+changeCurrency(ratePlanInfo.currencyCode) + ratePlanInfo.totalRate+'</span>' +
                            '                        <a name="aBooking" href="#" roomTypeId="'+ratePlanInfo.roomTypeId+'" ratePlanId="'+ratePlanInfo.ratePlanId+'">' + cahngePaymentType(ratePlanInfo.paymentType) + '</a>' +
                            '                    </div>' +
                            '                </div>';
                    });
                }
                listRoomHtml += roomHtml + '</div></div>';
            });
            $('#divRooms').html(listRoomHtml);
        }

        $('a[name="aBooking"]').click(function () {
            // 如果没有user，那么需要先登录
            if ($('#inputUser').val() == null || $('#inputUser').val() == "" || $('#inputUser').val() == "null") {
                window.location = $('#inputLoginPage').val();
            } else {
                var writeUrl = '/view/order/write?arrivalDate='+$("[name='daterangepicker_start']").val()
                    +'&departureDate='+$("[name='daterangepicker_end']").val() + '&hotelId='+$("#inputHotelId").val()
                    +'&roomTypeId='+$(this).attr('roomTypeId') + '&ratePlanId='+$(this).attr('ratePlanId')
                    +'&user='+$('#inputUser').val();
                window.location.href = writeUrl;
            }

        });
    }
    $('#aMoreHotelInfo').click(function(){
        if ($(this).find('i').attr('class') == "glyphicon glyphicon-chevron-down") {
            $(this).find('i').attr('class', 'glyphicon glyphicon-chevron-right');
            $('div[name="divMoreHotelInfo"]').css('display', 'none');
        } else {
            $(this).find('i').attr('class', 'glyphicon glyphicon-chevron-down');
            $('div[name="divMoreHotelInfo"]').css('display', 'block');
        }
    });
}

// 付款类型转换
function cahngePaymentType(info) {
    if (info == "Prepay") {
        return "预付";
    } else if (info == "SelfPay") {
        return "现付";
    } else {
        return "";
    }
}

// 货币符号转换
function changeCurrency(info) {
    var currency = "￥ ";
    if (info == "RMB") {
        currency = "￥ ";
    } else if (info == "HKD") {
        currency = "＄ ";
    } else if (info == "MOP") {
        currency = "＄ ";
    } else if (info == "JPY") {
        currency = "￥";
    } else if (info == "TWD") {
        currency = "＄ ";
    }
    return currency;
}

// 设施转换
function transFacility(info) {
    switch (info) {
        case "1":
            return "免费wifi ";
            break;
        case "5":
            return "免费停车场 ";
            break;
        case "7":
            return "免费接机服务 ";
            break;
        case "9":
            return "室内游泳池 ";
            break;
        case "10":
            return "室外游泳池 ";
            break;
        case "11":
            return "健身房 ";
            break;
        case "12":
            return "商务中心 ";
            break;
        case "13":
            return "会议室 ";
            break;
        case "14":
            return "酒店餐厅 ";
            break;
        case "15":
            return "叫醒服务 ";
            break;
        case "16":
            return "行李寄存 ";
            break;
        case "17":
            return "双床 ";
            break;
        case "18":
           return "大床 ";
            break;
        default:
            return "";
            break;
    }

}