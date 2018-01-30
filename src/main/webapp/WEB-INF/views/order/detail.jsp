<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/12/11
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单详情</title>
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />

    <script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="/resources/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/order/detail.css" />
    <script type="text/javascript" src="/resources/js/common/common.js"></script>
    <script type="text/javascript" src="/resources/js/order/detail.js"></script>
</head>
<body style="background: #F4F4F4;">
<nav class="navbar navbar-default my-header">
    <div class="container">
        <div>
            <div>
                <span id="spanBack" class="glyphicon glyphicon-chevron-left"></span>
            </div>
            <div>
            <span>
                订单详情
            </span>
            </div>
        </div>
    </div>
</nav>

<!-- 订单id隐藏域信息 -->
<input id="inputOrderId" style="display:none;" value="${orderId}"/>
<input id="inputPayAmount" style="display: none"/>
<!-- 登录信息隐藏域 -->
<input id="inputUser" style="display: none" value="${user}">

<!-- 订单详情 -->
<div class="container">
    <!-- 改成这样 -->
    <div class="panel panel-default">
        <div id="divOrderInfo" class="panel-body my-order-info">
        </div>
    </div>

    <div class="panel panel-default">
        <div id="divHotelInfo" class="panel-body my-hotel-info">

        </div>
    </div>

    <div class="panel panel-default">
        <div id="divProductInfo" class="panel-body my-product-info">
            <p><span>入离日期：</span><span>01月29日-01月30日 </span><span>共1晚</span></p>
            <p><span>入住房型：</span><span>标准大床房-1（1间）</span></p>
            <p><span>最晚到店：</span><span>01月29日 20:00</span></p>
            <p><span>入住人：</span><span>sdfsdfg</span></p>
            <p><span>联系电话：</span><span>13625232125</span></p>
            <p class="hr"><span>早餐信息：</span><span>包含2份早餐；</span></p>
            <p class="hr"><span>发票状态：</span><span>发票状态请联系酒店获悉</span><span></span></p>
        </div>
    </div>

    <div class="panel panel-default">
        <div id="divBookingInfo" class="panel-body my-booking-info">
            <p><span>预订日期：</span><span>2018-01-29</span></p>
            <p><span>订单金额：</span><span class="price">￥ 688</span><span>（现付）</span></p>
        </div>
    </div>
    <!-- 改成这样 -->

    <div class="container my-btn-group">
        <div>
            <button id="btnCancelOrder" class="btn btn-default" style="display: none">取消订单</button>
            <button id="btnPayOrder" class="btn btn-danger" style="display: none">去支付</button>
        </div>
    </div>
</div>
<!-- 订单详情 -->
</body>
</html>
