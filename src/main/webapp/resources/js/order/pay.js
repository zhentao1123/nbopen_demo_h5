/**
 * Created by user on 2017/12/11.
 */
$(function () {

    $('#spanBack').click(function() {
        if($('#inputUser').val() == null || $('#inputUser').val() == "" || $('#inputUser').val() == "null"){
            window.location = '/view/hotel/list';
        } else {
            window.location = '/view/order/list?user=' + $('#inputUser').val();
        }

    });

    $('div[name="divSelect"]').find('ul').find('li').bind('click', function(){
        $(this).parent().parent().find('button').html($(this).find('a').html() + '<span class="caret"></span>');
        $(this).parent().parent().find('button').attr('value', $(this).find('a').attr('value'));
    });

    initPage();

    $('#btnPay').click(function () {
        payOrder();
    });
});

var creditCardNumMark = "";
var isValidate = false;
var needCVV = false;
function payOrder() {

    var payAmount = $('#inputPayAmount').val();
    if (payAmount == null || payAmount == "") {
        alert("支付金额为0，不需要支付");
        return;
    }
    var orderId = $('#inputOrderId').val();
    if (orderId == null || orderId == "") {
        alert("没有传入订单编号，请关闭本页面");
        return;
    }
    var cardNumer = $('#inputCardNumber').val();
    if (cardNumer == null || cardNumer == "") {
        alert("请输入信用卡卡号");
        return;
    }
    var holderName = $('#inputHolderName').val();
    if (holderName == null || holderName == "") {
        alert("请输入持卡人姓名");
        return;
    }
    var term = $('#inputTerm').val();
    if (term == null || term == "") {
        alert("请输入信用卡有效期");
        return;
    }
    var termYear = parseInt(term.substring(2, 4));
    var termMonth = parseInt(term.substring(0, 2));
    if (isNaN(termYear) || isNaN(termMonth)) {
        alert("信用卡有效期有误");
        return;
    }
    termYear = termYear + 2000;
    var portNumber = $('#inputPortNumber').val();
    if (portNumber == null || portNumber == "") {
        alert("请输入证件号码");
        return;
    }

    if (creditCardNumMark != cardNumer) {
        isValidate = false;
        creditCardNumMark = cardNumer;
    }

    if (!isValidate) {
        var validateUrl = "/api/order/validateCreditCard";
        var validateReq = {
            "creditCardNum": cardNumer
        };

        var validateResult = ajaxCommonForJson(validateUrl, "POST", validateReq);
        if (validateResult != null && validateResult.isValid) {
            if (validateResult.isNeedVerifyCode) {
                alert("请输入CVV");
                $('#divCVV').css("display", "block");
                needCVV = true;
                return;
            } else {
                $('#divCVV').css("display", "none");
                needCVV = false;
            }
        } else {
            alert("信用卡无效");
            return ;
        }
    }
    var cVV = $('#inputCVV').val();
    var creditCardReq = {};
    if (needCVV) {
        if (cVV == null || cVV == "") {
            alert("请输入CVV");
            return;
        }
        creditCardReq = {
            "number": cardNumer,
            "cvv": cVV,
            "expirationYear": termYear,
            "expirationMonth": termMonth,
            "holderName": holderName,
            "idType": $('#btnIdType').attr("value"),
            "idNo": portNumber
        };
    } else {
        creditCardReq = {
            "number": cardNumer,
            "expirationYear": termYear,
            "expirationMonth": termMonth,
            "holderName": holderName,
            "idType": $('#btnIdType').attr("value"),
            "idNo": portNumber
        };
    }
    var url = "/api/order/payOrder";
    var req = {
        "orderId": orderId,
        "creditCard": creditCardReq,
        "payAmount": payAmount
    };
    var result = ajaxCommonForJson(url, "POST", req);
    if (result != null) {
        if (result.success) {
            alert("支付成功");
        } else {
            alert(result.message);
        }
    } else {
        alert("支付失败");
    }
    window.location.href='/view/order/detail?user=' + $('#inputUser').val() + '&orderId=' + $('#inputOrderId').val();
}

/**
 * 初始化页面
 */
function initPage() {
    var orderId = $('#inputOrderId').val();
    if (orderId == null || orderId == "") {
        alert("没有传入订单编号，请关闭本页面");
        return;
    }

    var url = "/api/order/getSimpleProduct?orderId=" + orderId;
    var result = ajaxCommonForJson(url, "GET", "");

    if (result != null) {
        var productHtml = '<span>' + result.hotelName + '</span><span></span><br/>' +
            '<hr />' +
            '<span>'+result.roomName+'</span><br />' +
            '<span>'+result.ratePlanName+'</span><span>'+result.roomNum+'间</span><br />' +
            '<span>'+result.dateDescription+'</span><span>共'+result.numberOrDays+'晚</span><br/>' +
            '<span>订单金额：</span><span>￥'+result.totalRate+'</span>';
        $('#divProductInfo').html(productHtml);
        $('#inputPayAmount').val(result.payAmount);
    }
}