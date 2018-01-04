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
<!-- 登录信息隐藏域 -->
<input id="inputUser" style="display: none" value="${user}">

<!-- 订单详情 -->
<div class="container">
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
        </div>
    </div>

    <div class="panel panel-default">
        <div id="divBookingInfo" class="panel-body my-booking-info">
        </div>
    </div>

    <div class="container" style="position: relative; top: 8px; padding-left:0px; padding-right: 30px;">
        <div id="divCancelOrder" class="col-sm-6" style="text-align: center; display: none">
            <button id="btnCancelOrder" class="btn btn-default" style="width: 60%;">取消订单</button>
        </div>
        <div id="divPayOrder" class="col-sm-6" style="text-align: center; display: none">
            <button id="btnPayOrder" class="btn btn-danger" style="width: 60%;">去支付</button>
        </div>
    </div>
</div>
<!-- 订单详情 -->
</body>
</html>
