<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/12/11
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单列表</title>
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />

    <script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="/resources/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/order/list.css" />
    <script type="text/javascript" src="/resources/js/common/common.js"></script>
    <script type="text/javascript" src="/resources/js/order/list.js"></script>
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
                订单列表
            </span>
            </div>
        </div>
    </div>
</nav>

<!-- 登录信息隐藏域 -->
<input id="inputUser" style="display: none" value="${user}">

<!-- 订单列表 -->
<div id="divOrders" class="container">
    <div class="panel panel-default">
        <div class="panel-body my-bookinginfo">
            <span>等待支付</span><span></span><br/>
            <hr />
            <span>飘HOME连锁酒店（北京华贸一店）（原雅悦酒店）</span><br />
            <span>标准双床房</span><span>预付</span><span>￥350</span><br />
            <span>12月22日（周五） - 12月23日（周六）</span><span>1晚1间</span>
            <hr />
            <div class="my-btn-group">
                <div class="btn-group my-btn-order">
                    <button class="btn btn-default">取消订单</button>
                </div>

                <div class="btn-group my-btn-order">
                    <button class="btn btn-danger">去支付</button>
                </div>
            </div>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-body my-bookinginfo">
            <span>已经离店</span><span></span><br/>
            <hr />
            <span>飘HOME连锁酒店（北京华贸一店）（原雅悦酒店）</span><br />
            <span>标准大床房</span><span>现付</span><span>￥320</span><br />
            <span>12月22日（周五） - 12月23日（周六）</span><span>2晚1间</span>
            <hr />
            <div class="my-btn-group">
                <div class="btn-group my-btn-order">
                    <button class="btn btn-default">再次预订</button>
                </div>
            </div>
        </div>
    </div>


</div>
<!-- 订单列表 -->
<div id="pullMoreOrder" style="text-align: center; display: block">点击加载更多</div>
<div id="noOrderInfo" style="text-align: center; display: none">没有更多酒店了</div>
</body>

</html>
