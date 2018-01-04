<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/12/8
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>订单填写</title>
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />

    <script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="/resources/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="/resources/css/order/write.css" />
    <script type="text/javascript" src="/resources/js/common/moment.min.js"></script>
    <script type="text/javascript" src="/resources/js/common/common.js"></script>
    <script type="text/javascript" src="/resources/js/order/write.js"></script>
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
                订单填写
            </span>
            </div>
        </div>
    </div>
</nav>

<!-- 登录信息隐藏域 -->
<input id="inputUser" style="display: none" value="${user}">

<!--  产品隐藏信息 -->
<input id="inputArrivalDate" style="display: none" value="${arrivalDate}" />
<input id="inputDepartureDate" style="display: none" value="${departureDate}" />
<input id="inputHotelId" style="display: none" value="${hotelId}" />
<input id="inputRoomTypeId" style="display: none" value="${roomTypeId}" />
<input id="inputRatePlanId" style="display: none" value="${ratePlanId}" />
<input id="inputTotalRate" style="display: none" value="">
<input id="inputPaymentType" style="display: none" value="">
<input id="inputCustomerType" style="display: none" value="">


<!-- 产品基础信息 -->
<div class="container">

    <div class="panel panel-default">
        <div id="divProductInfo" class="panel-body my-bookinginfo">
        </div>
    </div>
</div>
<!-- 产品基础信息 -->

<!-- 预订信息 -->
<div class="container">
    <!-- 房间数量 -->
    <div id="divRoomNumSelect" name="divSelect" class="dropdown my-select">
        <%--<button value="1" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">--%>
            <%--房间数量：1间--%>
            <%--<span class="caret"></span>--%>
        <%--</button>--%>
        <%--<ul class="dropdown-menu">--%>
            <%--<li><a value="1">房间数量：1间 </a></li>--%>
            <%--<li><a value="2">房间数量：2间 </a></li>--%>
            <%--<li><a value="3">房间数量：3间 </a></li>--%>
            <%--<li><a value="4">房间数量：4间 </a></li>--%>
            <%--<li><a value="5">房间数量：5间 </a></li>--%>
            <%--<li><a value="6">房间数量：6间 </a></li>--%>
            <%--<li><a value="7">房间数量：7间 </a></li>--%>
            <%--<li><a value="8">房间数量：8间 </a></li>--%>
            <%--<li><a value="9">房间数量：9间 </a></li>--%>
            <%--<li><a value="10">房间数量：10间 </a></li>--%>
        <%--</ul>--%>
    </div>
    <!-- 房间数量 -->
    <!-- 最晚到店时间 -->
    <div id="divArrivalTimeSelect" name="divSelect" class="dropdown my-select">
        <button id="btnArrivalTime" value="20:00" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            最晚到店时间：20:00
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu">
            <li><a value="17:00">最晚到店时间：17:00</a></li>
            <li><a value="18:00">最晚到店时间：18:00</a></li>
            <li><a value="19:00">最晚到店时间：19:00</a></li>
            <li><a value="20:00">最晚到店时间：20:00</a></li>
            <li><a value="21:00">最晚到店时间：21:00</a></li>
            <li><a value="22:00">最晚到店时间：22:00</a></li>
            <li><a value="23:00">最晚到店时间：23:00</a></li>
            <li><a value="00:00">最晚到店时间：00:00</a></li>
            <li><a value="01:00">最晚到店时间：凌晨01:00</a></li>
            <li><a value="02:00">最晚到店时间：凌晨02:00</a></li>
            <li><a value="03:00">最晚到店时间：凌晨03:00</a></li>
            <li><a value="04:00">最晚到店时间：凌晨04:00</a></li>
            <li><a value="05:00">最晚到店时间：凌晨05:00</a></li>
            <li><a value="06:00">最晚到店时间：凌晨06:00</a></li>
        </ul>
    </div>
    <!-- 房间数量 -->
</div>

<!-- 入住人信息 -->
<div id="divCustomerName" class="container">
    <br />
    <div class="input-group my-input-group">
        <span class="input-group-addon">入住人：</span>
        <input name="inputCustomerName" type="text" class="form-control" placeholder="姓名，每个房间填一人即可" aria-describedby="basic-addon1">
    </div>
</div>
<!-- 入住人信息 -->

<!-- 联系人信息 -->
<div class="container">
    <br />
    <div class="input-group my-input-group">
        <span class="input-group-addon">联系人：</span>
        <input id="inputContactName" type="text" class="form-control" placeholder="请填入姓名" aria-describedby="basic-addon1">
    </div>
    <div class="input-group my-input-group">
        <span class="input-group-addon">手机号：</span>
        <input id="inputContactPhone" type="text" class="form-control" placeholder="请填入手机号码" aria-describedby="basic-addon1">
    </div>
</div>
<!-- 联系人信息 -->

<!-- 发票信息 -->
<div class="container">
    <br />
    <!-- 艺龙开具发票 -->
    <div id="divElongInvoice" style="display: none;">
    <div id="divNeedInvoiceSelect" name="divSelect" class="dropdown my-select">
        <button id="btnNeedInvoice" value="0" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
            不开具发票
            <span class="caret"></span>
        </button>
        <ul class="dropdown-menu" style="width: 100%;">
            <li><a value="0">不开具发票 </a></li>
            <li><a value="1">开具发票 </a></li>
        </ul>
    </div>
    <div id="divInvoiceInfo" style="display: none">
    <div id="divInvoiceTypeSelect" class="input-group my-span-select">
        <span class="input-group-addon my-span-title">发票类型：</span>
        <div name="divSelect" class="dropdown">

            <button id="btnInvoiceType" value="Electronic" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                电子发票
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a value="Electronic">电子发票 </a></li>
                <li><a value="Paper">纸质发票 </a></li>
            </ul>
        </div>
    </div>
    <div id="divTitleTypeSelect" class="input-group my-span-select">
        <span class="input-group-addon my-span-title">抬头类型：</span>
        <div name="divSelect" class="dropdown">
            <button id="btnTitleType" value="Enterprise" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                公司
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a value="Enterprise">公司</a></li>
                <li><a value="Personally">个人</a></li>
                <li><a value="Government">机关单位</a></li>
            </ul>
        </div>
    </div>
    <div id="divPersonNotInput" style="display: block">
    <div class="input-group my-input-group">
        <span class="input-group-addon">发票抬头：</span>
        <input id="inputTitle" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
    </div>

    <div class="input-group my-input-group">
        <span class="input-group-addon">纳税人识别号：</span>
        <input id="inputItin" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
    </div>
    </div>
    <div class="input-group my-span-select">
        <span class="input-group-addon my-span-title">发票内容：</span>
        <div name="divSelect" class="dropdown">
            <button id="btnItemName" value="代订房费" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                代订房费
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a value="代订房费">代订房费</a></li>
                <li><a value="代订住宿费">代订住宿费</a></li>
            </ul>
        </div>
    </div>
    <div class="input-group my-input-group">
        <span class="input-group-addon" style="background: white; width: 130px; text-align: left;">收票人手机：</span>
        <input id="inputRecievePhone" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
    </div>
    <div class="input-group my-span-select">
        <span class="input-group-addon my-span-title">备注订单信息：</span>
        <div name="divSelect" class="dropdown">
            <button id="btnNeedRelationOrder" value="0" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                否
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a value="1">是</a></li>
                <li><a value="0">否</a></li>
            </ul>
        </div>
    </div>
    <div id="divPaperInvoiceAddr" style="display: none">
        <div class="input-group my-input-group">
            <span class="input-group-addon">收票人姓名：</span>
            <input id="inputRecieveName" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">省份：</span>
            <input id="inputProvince" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">城市：</span>
            <input id="inputCity" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">行政区：</span>
            <input id="inputDistrict" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">邮编：</span>
            <input id="inputPostalCode" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">街道：</span>
            <input id="inputStreet" type="text" class="form-control" placeholder="" aria-describedby="basic-addon1">
        </div>
        <div class="input-group my-input-group">
            <span class="input-group-addon">Email：</span>
            <input id="inputRecieveEmail" type="text" class="form-control" placeholder="选填" aria-describedby="basic-addon1">
        </div>
    </div>
    </div>
    </div>

    <!-- 艺龙开具发票 -->

    <!-- 酒店开具发票 -->
    <div id="divHotelInvoice" class="input-group my-none-input-group" style="display: none">
        <input type="text" class="form-control" placeholder="如需发票请到酒店前台索取" readonly="readonly" />
    </div>
    <!-- 酒店开具发票 -->

    <!-- 不开发票 -->
    <!-- 不开发票 -->
</div>
<!-- 发票信息 -->

<div class="container">
    <div id="divCancelRule" class="my-cancelrule">
    </div>
</div>

<div class="container" style="position: relative; top: 8px;">
    <div class="col-sm-12" style="text-align: center;">
        <button id="btnCreateOrder" class="btn btn-danger" style="width: 60%;">提交订单</button>
    </div>
    <hr />
</div>
</body>

</html>
