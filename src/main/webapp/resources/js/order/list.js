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
                '<div name="divOrder" orderid="'+info.orderId+'" class="panel-body my-bookinginfo">' +
                '<span>'+info.showStatus+'</span><span></span><br/><hr />' +
                '<span>'+info.hotelName+'</span><br />' +
                '<span>'+info.roomName+'</span><span>'+info.paymentType+'</span><span>￥'+info.totalPrice+'</span><br />' +
                '<span>'+info.dateDescription+'</span><span>'+info.numberOfDays+'晚'+info.numberOfRooms+'间</span><hr />' +
                '<div class="my-btn-group">' ;
            if (info.bCancel) {
                listHtml += '<div class="btn-group my-btn-order">' +
                    '<button name="btnCancel" orderId="'+info.orderId+'" class="btn btn-default">取消订单</button></div>';
            }
            if (info.bPayable) {
                listHtml += '<div class="btn-group my-btn-order">' +
                    '<button name="btnPay" orderId="'+info.orderId+'" class="btn btn-danger">去支付</button></div>';
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
            var url = "/api/order/cancelOrder";
            var req = {
                "orderId": parseInt($(this).attr("orderId"))
            };
            var result = ajaxCommonForJson(url, "POST", req);
            if (result != null && result.success) {
                alert("取消成功");
            } else {
                alert("取消失败");
            }
            window.location.reload(true);

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

        $('button[name="btnPay"]').click(function (event) {
            window.location.href = "/view/order/pay?user="+$('#inputUser').val()+"&orderId="+$(this).attr("orderId");
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