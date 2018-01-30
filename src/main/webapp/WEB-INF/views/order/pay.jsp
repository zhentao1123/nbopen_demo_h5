<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/12/11
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单支付</title>
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />

    <script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="/resources/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/order/pay.css" />
    <script type="text/javascript" src="/resources/js/common/common.js"></script>
    <script type="text/javascript" src="/resources/js/order/pay.js"></script>
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
                订单支付
            </span>
                </div>
            </div>
    </div>
    <!-- /.container -->
</nav>

<!-- 订单id隐藏域信息 -->
<input id="inputOrderId" style="display:none;" value="${orderId}"/>
<input id="inputPayAmount" style="display: none" value=""/>
<!-- 登录信息隐藏域 -->
<input id="inputUser" style="display: none" value="${user}">

<!-- 产品基础信息 -->
<div class="container">

    <div class="panel panel-default">
        <div id="divProductInfo" class="panel-body my-bookinginfo">
            <p><span class="name">北京皇冠假日酒店</span><em>(经济型)</em></p>
            <p><span>标准间</span><span>不含早</span><em>1间</em></p>
            <p><span>12月22日（周五） - 12月23日（周六）</span><em class="blue">共1晚</em></p>
            <p><span>订单金额：</span><em>￥349.30</em></p>
            <%--<span>北京皇冠假日酒店</span><span>(经济型)</span><br/>--%>
            <%--<hr />--%>
            <%--<span>标准间</span><br />--%>
            <%--<span>不含早</span><span>1间</span><br />--%>
            <%--<span>12月22日（周五） - 12月23日（周六）</span><span>共1晚</span><br/>--%>
            <%--<span>订单金额：</span><span>￥349.30</span>--%>
        </div>
    </div>
</div>
<!-- 产品基础信息 -->

<!-- 信用卡信息 -->
<div class="container">

    <div class="panel panel-default my-creditcardinfo">
        <div class="input-group my-input-group">
            <span class="input-group-addon">卡号：</span>
            <input id="inputCardNumber" type="text" class="form-control" placeholder="请输入13~16位卡号" aria-describedby="basic-addon1">
        </div>
        <div id="divCVV" style="display: none">
            <div class="input-group my-input-group">
                <span class="input-group-addon">CVV：</span>
                <input id="inputCVV" type="text" class="form-control" placeholder="请输入3位CVV" aria-describedby="basic-addon1">
            </div>
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">持卡人姓名：</span>
            <input id="inputHolderName" type="text" class="form-control" placeholder="例：张三" aria-describedby="basic-addon1">
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">有效期：</span>
            <input id="inputTerm" type="text" class="form-control" placeholder="月年，例：0118" aria-describedby="basic-addon1">
        </div>
        <div id="divInvoiceTypeSelect" name="divSelect" class="dropdown my-select">
            <span class="key">证件类型：</span>
            <button id="btnIdType" value="IdentityCard" class="btn dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                身份证
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a value="IdentityCard">身份证 </a></li>
                <li><a value="Passport">护照 </a></li>
                <li><a value="Other">其他 </a></li>
            </ul>
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">证件号码：</span>
            <input id="inputPortNumber" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
    </div>
</div>

<div class="container" style="text-align: center">
    <button id="btnPay" class="btn-save">支付</button>
</div>
<!-- 信用卡信息 -->

</body>

</html>