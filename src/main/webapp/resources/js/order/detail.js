/**
 * Created by user on 2017/12/12.
 */
$(function () {
    var orderId = $('#inputOrderId').val();
    if (orderId == null || orderId == "") {
        alert("没有传入订单编号，请关闭本页面");
        return;
    }
    var userId = $('#inputUser').val();
    if (userId == null || userId == "") {
        alert("没有传入用户名，请关闭本页面");
        return;
    }

    $('#spanBack').click(function() {
        window.location.href = '/view/order/list?user=' + $('#inputUser').val();
    });

    initPage();

    $('#btnPayOrder').click(function () {
        window.location.href = '/view/order/pay?user=' + $('#inputUser').val() + '&orderId=' + $('#inputOrderId').val();
    });

    $('#btnCancelOrder').click(function () {
        if (confirm("确定取消订单吗？")) {
            cancelOrder();
            initPage();
        }
    });
});

function cancelOrder() {
    var url = "/api/order/cancelOrder";
    var req = {
        "orderId": parseInt($('#inputOrderId').val())
    };
    var result = ajaxCommonForJson(url, "POST", req);
    if (result != null && result.success) {
        alert("取消成功");
    } else {
        alert("取消失败");
    }
    window.location.reload(true);
}

function initPage() {
    var url = "/api/order/getOrderDetail";
    var req = {
        "orderId": parseInt($('#inputOrderId').val()),
        "username": $('#inputUser').val()
    };
    var result = ajaxCommonForJson(url, "POST", req);
    if (result != null) {
        var tempHtml = "";
        tempHtml += '<span>订单号码：</span><span>'+ result.orderId +'</span><br/><span>订单状态：</span><span>'+ result.showStatus +'</span>';
        if (result.bPayable) {
            tempHtml += '<span>请在'+ result.lastPayTime +'之前完成支付，过时订单将会自动取消</span>';
        } else {
            tempHtml += '<span></span>';
        }
        $('#divOrderInfo').html(tempHtml);

        tempHtml = "";
        tempHtml += '<span>'+result.hotelName+'</span><br/>' +
            '<span>'+result.address+'</span>' +
            '<hr/><span>酒店电话：</span><span>'+result.hotelPhone +'</span>';
        $('#divHotelInfo').html(tempHtml);

        tempHtml = "";
        tempHtml += '<span>入离日期：</span>' +
            '<span>'+ result.arrivalDate +'-' +result.departureDate +' </span>' +
            '<span>共'+ result.numberOfDays+'晚</span>' +
            '<hr/>' +
            '<span>入住房型：</span><span>'+result.roomName+'（'+ result.numberOfRooms +'间）</span><br/>' +
            '<span>最晚到店：</span><span>'+result.lastArrivalTime+'</span><br/>' +
            '<span>入住人：</span><span>'+result.customerNames+'</span><br/>' +
            '<span>联系电话：</span><span>'+result.contactPhone+'</span>' +
            '<hr/>' +
            '<span>早餐信息：</span><span>'+result.valueAdds+'</span>' +
            '<hr/>' +
            '<span>发票状态：</span><span>'+result.invoiceStatus+'</span>';
        if (result.invoiceUrl != null) {
            tempHtml += '<span><a url="#">下载发票</a></span>';
        } else {
            tempHtml += '<span></span>';
        }
        $('#divProductInfo').html(tempHtml);

        tempHtml = '';
        tempHtml += '<span>预订日期：</span>' +
            '<span>'+result.bookingDate+'</span>' +
            '<hr/>' +
            '<span>订单金额：</span>' +
            '<span>￥'+result.totalPrice+'</span>' +
            '<span>（'+result.paymentType+'）</span>';
        if (result.penalty != null && result.penalty != 0) {
            tempHtml +=  '<hr/><span>罚金：</span><span>￥'+ result.penalty+'</span><br/>';
        }
        $('#divBookingInfo').html(tempHtml);

        if (result.bCancel) {
            $('#divCancelOrder').css("display", "block");
        }
        if (result.bPayable) {
            $('#divPayOrder').css("display", "block");
        }
    }
}