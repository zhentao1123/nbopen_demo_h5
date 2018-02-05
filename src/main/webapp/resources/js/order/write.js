/**
 * Created by user on 2017/12/8.
 */

var guaranteeRules = [];
var nightlyRates = [];
var prepayRules = [];
var Payment_Type = "";
$(function(){

    $('#spanBack').click(function() {
        var hotelDetailUrl = '/view/hotel/detail?hotelId=' + $("#inputHotelId").val();
        if ($('#inputUser').val() != null && $('#inputUser').val() != "" && $('#inputUser').val() != "null") {
            hotelDetailUrl += "&user=" + $('#inputUser').val();
        }
        window.location = hotelDetailUrl;
    });

    getProductInfo();

    $('div[name="divSelect"]').find('ul').find('li').bind('click', function(){
        $(this).parent().parent().find('button').html($(this).find('a').html() + '<span class="glyphicon glyphicon-menu-right"></span>');
        $(this).parent().parent().find('button').attr('value', $(this).find('a').attr('value'));
        // 更改了房间数量
        if ($(this).parent().parent().attr('id') == 'divRoomNumSelect') {
            changeRoomNum();
        }

        // 更改了最晚到店时间
        if ($(this).parent().parent().attr('id') == 'divArrivalTimeSelect') {
            changeArrivalTime();
        }

        // 是否需要发票
        if ($(this).parent().parent().attr('id') == 'divNeedInvoiceSelect') {
            changeNeedInvoice();
        }

        // 更改发票类型
        if ($(this).parent().parent().attr('id') == 'divInvoiceTypeSelect') {
            changeInvoiceType();
        }

        // 更改抬头类型
        if ($(this).parent().parent().attr('id') == 'divTitleTypeSelect') {
            changeTitleType();
        }
    });

    $('#btnCreateOrder').click(function () {
        $('#btnCreateOrder').attr('disabled', true);
        $('#loadingModal').modal({backdrop: 'static', keyboard: false});
        setTimeout(function(){
            createOrder();
            $("#loadingModal").modal('hide');
            $('#btnCreateOrder').attr('disabled', false);
        },2000);
    });
});

function createOrder() {

    /********************* 参数校验 ***********************************************/
    if ($("#inputHotelId").val() == null || $("#inputHotelId").val() == "" || $("#inputHotelId").val() == "null") {
        alert("没有获取到HotelId，请关闭本页面");
        return;
    }
    if ($("#inputArrivalDate").val() == null || $("#inputArrivalDate").val() == "" || $("#inputArrivalDate").val() == "null") {
        alert("没有获取到入住日期，请关闭本页面");
        return;
    }
    if ($("#inputDepartureDate").val() == null || $("#inputDepartureDate").val() == "" || $("#inputDepartureDate").val() == "null") {
        alert("没有获取到离店日期，请关闭本页面");
        return;
    }
    if ($("#btnRoomNum").attr("value") == null || $("#btnRoomNum").attr("value") == "" || $("#btnRoomNum").attr("value") == "null") {
        alert("没有获取到房间数量，请关闭本页面");
        return;
    }
    if ($("#btnArrivalTime").attr("value") == null || $("#btnArrivalTime").attr("value") == "" || $("#btnArrivalTime").attr("value") == "null") {
        alert("没有获取到最晚到店时间，请刷新本页面");
        return;
    }
    if ($("#inputRoomTypeId").val() == null || $("#inputRoomTypeId").val() == "" || $("#inputRoomTypeId").val() == "null") {
        alert("没有获取到房型ID，请关闭本页面");
        return;
    }
    if ($("#inputRatePlanId").val() == null || $("#inputRatePlanId").val() == "" || $("#inputRatePlanId").val() == "null") {
        alert("没有获取到产品ID，请关闭本页面");
        return;
    }
    if ($('#inputTotalRate').val() == null || $('#inputTotalRate').val() == "" || $('#inputTotalRate').val() == "null") {
        alert("没有获取到总价，请刷新页面")
    }
    if ($('#inputTotalRate').attr("currencyCode") == null || $('#inputTotalRate').attr("currencyCode") == "" || $('#inputTotalRate').attr("currencyCode") == "null") {
        alert("没有获取到总价的货币类型，请刷新页面")
    }
    var isAllInputCustomer = true;
    var customerString = new Array();
    $('input[name="inputCustomerName"]').each(function (i, info) {
        if (info.value == null || info.value == "") {
            isAllInputCustomer = false;
            return;
        }
        customerString[i] = info.value;
    });
    //customerString = customerString.substring(0, customerString.length - 1);
    if (!isAllInputCustomer) {
        alert("每个房间都需要填写一个入住人姓名");
        return;
    }
    if ($('#inputContactName').val() == null || $('#inputContactName').val() == "") {
        alert("请输入联系人姓名");
        return;
    }
    if ($('#inputContactPhone').val() == null || $('#inputContactPhone').val() == "") {
        alert("请输入联系人手机号");
        return;
    }

    /********************* 参数校验 ***********************************************/

    /********************* 产品及库存校验 ******************************************/
    var arrivalDate = $("#inputArrivalDate").val();
    var arrivalNextDate = moment($("#inputArrivalDate").val(),"YYYY-MM-DD").add('days', 1).format('YYYY-MM-DD');
    var nextHour = moment().add('hours',1).format('HH');

    var validateUrl = "/api/order/checkProduct";
    var validateReq = {
        "arrivalDate": arrivalDate,
        "departureDate": $("#inputDepartureDate").val(),
        "hotelId": $("#inputHotelId").val(),
        "roomTypeID": $("#inputRoomTypeId").val(),
        "ratePlanId": $("#inputRatePlanId").val(),

        "earliestArrivalTime": (nextHour.substring(0,1) == "0"? arrivalNextDate: arrivalDate) + " " + nextHour + ":00",
        "latestArrivalTime": ($('#btnArrivalTime').attr("value").substring(0,1) == "0"? arrivalNextDate: arrivalDate) + " " + $('#btnArrivalTime').attr("value"),
        "numberOfRooms": $("#btnRoomNum").attr("value"),
        "totalPrice": parseFloat($('#inputTotalRate').val())
    };

    var validateResult = ajaxCommonForJson(validateUrl, "POST", validateReq);
    if (validateResult != null) {
        if (validateResult.validate) {
            if (validateResult.message != null && validateResult.message != "") {
                if( !confirm( validateResult.message)) {
                    return;
                }
            }
        } else {
            alert(validateResult.errorMessage);
            return;
        }
    }
    /********************* 产品及库存校验 ******************************************/

    /********************* 创建订单 ***********************************************/

    var invoice = null;
    var needInvoice = false;
    if ($('#divElongInvoice').css('display') == 'block') {
        if ($('#btnNeedInvoice').attr('value') == "1") {
            needInvoice = true;
            var recipient = null;
            recipient = {
                "province": ($('#inputProvince').val() == null || $('#inputProvince').val() == "")? null: $('#inputProvince').val(),
                "city": ($('#inputCity').val() == null || $('#inputCity').val() == "")? null: $('#inputCity').val(),
                "district": ($('#inputDistrict').val() == null || $('#inputDistrict').val() == "")? null: $('#inputDistrict').val(),
                "street": ($('#inputStreet').val() == null || $('#inputStreet').val() == "")? null: $('#inputStreet').val(),
                "postalCode": ($('#inputPostalCode').val() == null || $('#inputPostalCode').val() == "")? null: $('#inputPostalCode').val(),
                "name": ($('#inputRecieveName').val() == null || $('#inputRecieveName').val() == "")? null: $('#inputRecieveName').val(),
                "phone": ($('#inputRecievePhone').val() == null || $('#inputRecievePhone').val() == "")? null: $('#inputRecievePhone').val(),
                "email": ($('#inputRecieveEmail').val() == null || $('#inputRecieveEmail').val() == "")? null: $('#inputRecieveEmail').val(),
            };
            invoice = {
                "invoiceType": $('#btnInvoiceType').attr("value"),
                "titleType": $('#btnTitleType').attr("value"),
                "title": ($('#inputTitle').val() == null || $('#inputTitle').val() == "")? null: $('#inputTitle').val(),
                "itin": ($('#inputItin').val() == null || $('#inputItin').val() == "")? null: $('#inputItin').val(),
                "itemName": $('#btnItemName').attr("value"),
                "amount": $('#inputTotalRate').val(),
                "needRelationOrder": ($('#btnNeedRelationOrder').attr("value") == "1"),
                "recipient": recipient
            };
        }
    }

    var createUrl = "/api/order/createOrder";
    var createReq = {
        "userName": $('#inputUser').val(),
        "arrivalDate": arrivalDate,
        "departureDate": $("#inputDepartureDate").val(),
        "hotelId": $("#inputHotelId").val(),
        "roomTypeId": $("#inputRoomTypeId").val(),
        "ratePlanId": $("#inputRatePlanId").val(),
        "earliestArrivalTime": (nextHour.substring(0,1) == "0"? arrivalNextDate: arrivalDate) + " " + nextHour + ":00",
        "latestArrivalTime": ($('#btnArrivalTime').attr("value").substring(0,1) == "0"? arrivalNextDate: arrivalDate) + " " + $('#btnArrivalTime').attr("value"),
        "paymentType": $('#inputPaymentType').val(),
        "numberOfRooms": $("#btnRoomNum").attr("value"),
        "currencyCode": $('#inputTotalRate').attr("currencyCode"),
        "totalPrice": parseFloat($('#inputTotalRate').val()),
        "needInvoice": needInvoice,
        "invoice": invoice,
        "customerType": $('#inputCustomerType').val(),
        "customers": customerString,
        "contactName": $('#inputContactName').val(),
        "contactPhone": $('#inputContactPhone').val()
    };

    var createResult = ajaxCommonForJson(createUrl, "POST", createReq);
    if (createResult != null) {
        if (createResult.success) {
            window.location.href = '/view/order/detail?user=' + $('#inputUser').val() + '&orderId=' + createResult.orderId;
            // // 如果需要支付，跳转到支付页面
            // if (createResult.needPay != null && createResult.needPay) {
            //     window.location.href = '/view/order/pay?user=' + $('#inputUser').val() + '&orderId=' + createResult.orderId;
            // } else {
            //     // 不需要支付去订单详情页
            //     window.location.href = '/view/order/detail?user=' + $('#inputUser').val() + '&orderId=' + createResult.orderId;
            // }
        } else {
            alert(createResult.errorMessage);
        }
    }
    /********************* 创建订单 ***********************************************/
}

function changeTitleType() {
    if ($('#divTitleTypeSelect').find('button').attr("value") == 'Personally') {
        $('#divPersonNotInput').css('display', 'none');
    } else {
        $('#divPersonNotInput').css('display', 'block');
    }
}

function changeInvoiceType() {
    if ($('#divInvoiceTypeSelect').find('button').attr("value") == 'Paper') {
        $('#divPaperInvoiceAddr').css('display', 'block');
    } else {
        $('#divPaperInvoiceAddr').css('display', 'none');
    }
}

function changeNeedInvoice() {
    if ($('#divNeedInvoiceSelect').find('button').attr("value") == '0') {
        $('#divInvoiceInfo').css('display', 'none');
    } else {
        $('#divInvoiceInfo').css('display', 'block');
    }

}

function getProductInfo() {
    if ($("#inputHotelId").val() == null || $("#inputHotelId").val() == "" || $("#inputHotelId").val() == "null") {
        alert("没有获取到HotelId，请关闭本页面");
        return;
    }
    if ($("#inputArrivalDate").val() == null || $("#inputArrivalDate").val() == "" || $("#inputArrivalDate").val() == "null") {
        alert("没有获取到入住日期，请关闭本页面");
        return;
    }
    if ($("#inputDepartureDate").val() == null || $("#inputDepartureDate").val() == "" || $("#inputDepartureDate").val() == "null") {
        alert("没有获取到离店日期，请关闭本页面");
        return;
    }
    if ($("#inputRoomTypeId").val() == null || $("#inputRoomTypeId").val() == "" || $("#inputRoomTypeId").val() == "null") {
        alert("没有获取到房型ID，请关闭本页面");
        return;
    }
    if ($("#inputRatePlanId").val() == null || $("#inputRatePlanId").val() == "" || $("#inputRatePlanId").val() == "null") {
        alert("没有获取到产品ID，请关闭本页面");
        return;
    }

    var url = "/api/order/getDetailForWrite";

    var req = {
        "arrivalDate": $("#inputArrivalDate").val(),
        "departureDate": $("#inputDepartureDate").val(),
        "hotelId": $("#inputHotelId").val(),
        "roomTypeId": $("#inputRoomTypeId").val(),
        "ratePlanId": $("#inputRatePlanId").val()
    };

    var result = ajaxCommonForJson(url, "POST", req);
    if (result != null) {
        var productHtml = "";
        productHtml += '<p class="hotel-name">'+result.hotelName+'<span>（'+result.starRate+'）</span></p>' +
            '            <p>'+result.roomName+'<span>('+(result.paymentType=="Prepay"?"预付":"现付")+')</span></p>' +
            '            <p>'+result.dateDescription+'<span>共'+result.numberOrDays+'晚</span></p>';
        $('#divProductInfo').html(productHtml);

        guaranteeRules = result.guaranteeRules;
        nightlyRates = result.nightlyRates;
        prepayRules = result.prepayRules;
        Payment_Type = result.paymentType;

        // 最少预订房间数量
        var selectRoomNumHtml = '<span class="key">房间数量：</span><button id="btnRoomNum" value="'+ result.minAmount +'" class="btn dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">'
            + result.minAmount +'间 <span class="glyphicon glyphicon-menu-right"></span> </button> <ul class="dropdown-menu">';
        for (i = result.minAmount; i <= 10; i++) {
            selectRoomNumHtml += '<li><a value="'+ i +'">'+ i +'间 </a></li>';
        }
        selectRoomNumHtml += '</ul>';
        $('#divRoomNumSelect').html(selectRoomNumHtml);

        changeRoomNum();

        // 发票处理
        if (result.invoiceMode != null && result.invoiceMode == "Elong") {
            $('#divElongInvoice').css('display', 'block');
        } else if (result.invoiceMode != null && result.invoiceMode == "Hotel") {
            $('#divHotelInvoice').css('display', 'block');
        }

        // 取消规则描述
        // if (result.cancelRule == null || result.cancelRule == "") {
        //     $('#divCancelRule').html('取消规则：您可以在入住前随时取消');
        // } else {
        //     $('#divCancelRule').html('取消规则：' + result.cancelRule);
        // }

        $('#inputPaymentType').val(result.paymentType);
        $('#inputTotalRate').val(result.totalRate);
        $('#inputTotalRate').attr("currencyCode", result.currencyCode);
        $('#inputCustomerType').val(result.customerType);

    }
}

function changeCancelRule() {
    var lastArrivalTime = moment($("#inputArrivalDate").val() + " " + $('#btnArrivalTime').attr('value'), "YYYY-MM-DD HH:mm").valueOf();
    var roomNum = parseInt($('#btnRoomNum').attr('value'));
    if (Payment_Type == "SelfPay") {
        var guarantee = getGuaranteeDescription(guaranteeRules, nightlyRates, lastArrivalTime, roomNum, moment($("#inputArrivalDate").val(), "YYYY-MM-DD").valueOf(), moment($("#inputDepartureDate").val(), "YYYY-MM-DD").valueOf());
        $('#divCancelRule').html(guarantee.description);
    } else if (Payment_Type == "Prepay") {
        var prepay = getPrepayDescription(prepayRules, nightlyRates, lastArrivalTime, roomNum, moment($("#inputArrivalDate").val(), "YYYY-MM-DD").valueOf(), moment($("#inputDepartureDate").val(), "YYYY-MM-DD").valueOf());
        $('#divCancelRule').html(prepay.description);
    }

}

function changeArrivalTime() {
    changeCancelRule();
}

function changeRoomNum() {
    var customHtml = '<div class="input-group my-input-group"><span class="input-group-addon">入住人：</span><input name="inputCustomerName" type="text" class="form-control" placeholder="姓名，每个房间填一人即可" aria-describedby="basic-addon1"></div>';

    var roomNum = parseInt($('#divRoomNumSelect').find('button').attr("value"));
    var totalRate = parseFloat($('#inputTotalRate').val());
    $('#inputTotalRate').val(totalRate * roomNum);
    var customHtmls = '<br/>';
    for (i = 0; i < roomNum; i++) {
        customHtmls += customHtml;
    }
    $('#divCustomerName').html('');
    $('#divCustomerName').append(customHtmls);
    changeCancelRule();
}