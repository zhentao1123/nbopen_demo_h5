/**
 * Created by user on 2017/12/11.
 */
var pageIndex = 1;

$(function () {

    var userId = $('#inputUser').val();
    if (userId == null || userId == "") {
        alert("没有传入用户名，请关闭本页面");
        return;
    }

    $('#pullMoreOrder').css("display", "block");
    $('#noOrderInfo').css("display", "none");

    $('#spanBack').click(function() {
        var hotelListlUrl = '/view/hotel/list';
        if ($('#inputUser').val() != null && $('#inputUser').val() != "" && $('#inputUser').val() != "null") {
            hotelListlUrl += "?user=" + $('#inputUser').val();
        }
        window.location = hotelListlUrl;
    });

    initPage();

    $('#pullMoreOrder').click(function () {
        pullMoreOrder();
    });
});

function pullMoreOrder() {
    pageIndex += 1;
    getOrders();
}

function initPage() {
    pageIndex = 1;
    $('#divOrders').html("");
    getOrders();
}

function getOrders() {
    var url = "/api/order/getOrderList";
    var req = {
        "username": $('#inputUser').val(),
        "page": pageIndex
    };
    var result = ajaxCommonForJson(url, "POST", req);
    if (result != null && result.orders != null &&result.orders.length > 0) {
        var listHtml = "";
        $.each(result.orders, function (i, info) {
            listHtml += '<div class="panel panel-default">' +
                '        <div name="divOrder" orderid="'+info.orderId+'"class="panel-body my-bookinginfo">' +
                '            <p class="status"><span class="t1">'+info.showStatus+'</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;订单编号：'+info.orderId+'</span></p>' +
                '            <p class="name"><span>'+info.hotelName+'</span></p>' +
                '            <p><span>'+info.roomName+'</span><span class="price">'+info.paymentType+'<em>'+changeCurrency(info.currencyCode)+info.totalPrice+'</em></span></p>' +
                '            <p><span>'+info.dateDescription+'</span><span class="num">'+info.numberOfDays+'晚'+info.numberOfRooms+'间</span></p>'+
                '<div class="my-btn-group">';

            if (info.bCancel) {
                listHtml += '<button name="btnCancel" orderId="'+info.orderId+'" class="btn btn-default">取消订单</button>';
            }
            if (info.bPayable) {
                listHtml += '<button name="btnPay" orderId="'+info.orderId+'" payAmount="'+info.payAmount+'" class="btn btn-danger">去支付</button>';
            }

            listHtml += '</div></div></div>';

        });
        $('#divOrders').append(listHtml);

        $('div[name="divOrder"]').click(function (event) {
            window.location.href = "/view/order/detail?user="+$('#inputUser').val()+"&orderId="+$(this).attr("orderId");
            //取消事件冒泡
            var e=arguments.callee.caller.arguments[0]||event; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
            if (e && e.stopPropagation) {
                // this code is for Mozilla and Opera
                e.stopPropagation();
            } else if (window.event) {
                // this code is for IE
                window.event.cancelBubble = true;
            }
        });

        $('button[name="btnCancel"]').click(function (event) {
            //取消事件冒泡
            var e=arguments.callee.caller.arguments[0]||event;
            if (e && e.stopPropagation) {
                // this code is for Mozilla and Opera
                e.stopPropagation();
            } else if (window.event) {
                // this code is for IE
                window.event.cancelBubble = true;
            }
            if (!confirm("确定取消订单吗？")) {
                return;
            }
            var url = "/api/order/cancelOrder";
            var req = {
                "orderId": parseInt($(this).attr("orderId"))
            };
            var result = ajaxCommonForJson(url, "POST", req);
            if (result != null && result.success) {
                alert("取消成功");
            } else {
                alert(result.reason);
            }
            window.location.reload(true);


        });

        $('button[name="btnPay"]').click(function (event) {
            /** 新增的收银台支付方式，支持信用卡、QQ支付、支付宝、微信支付 **/
            if (parseFloat($(this).attr("payAmount")) == 0) {
                alert("此订单不需要支付");
            } else {
                var cashDeskRequestUrl = "/api/order/getCashDesk";
                var cashDeskRequest = {
                    "orderId": $(this).attr("orderId"),
                    "amount": $(this).attr("payAmount")
                };
                var cashDeskResult = ajaxCommonForJson(cashDeskRequestUrl, "POST", cashDeskRequest);
                if (cashDeskResult != null) {
                    if (cashDeskResult.payUrl != null && cashDeskResult.payUrl != "") {
                        window.open(cashDeskResult.payUrl);
                    } else if (cashDeskResult.errorMessage != null && cashDeskResult.errorMessage != "") {
                        alert(cashDeskResult.errorMessage);
                    } else {
                        alert("获取收银台失败，请重试");
                    }
                } else {
                    alert("获取收银台失败，请重试");
                }
            }


            /** 这是传统的使用信用卡支付的方式
            window.location.href = "/view/order/pay?user="+$('#inputUser').val()+"&orderId="+$(this).attr("orderId");
            **/

            //取消事件冒泡
            var e=arguments.callee.caller.arguments[0]||event;
            if (e && e.stopPropagation) {
                // this code is for Mozilla and Opera
                e.stopPropagation();
            } else if (window.event) {
                // this code is for IE
                window.event.cancelBubble = true;
            }
        });
    } else {
        $('#pullMoreOrder').css("display", "none");
        $('#noOrderInfo').css("display", "block");
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
