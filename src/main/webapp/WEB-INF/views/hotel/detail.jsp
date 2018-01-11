<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/12/6
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>酒店详情</title>
<link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" media="all" href="/resources/css/common/daterangepicker.css" />
<link rel="stylesheet" href="/resources/css/hotel/detail.css" />
<script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
<script type="text/javascript" src="/resources/js/common/moment.min.js"></script>
<script type="text/javascript" src="/resources/js/common/common.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=Un9kDG27zSkwEL9Eut9dRPpoTu6BTGdg"></script>
<script type="text/javascript" src="/resources/js/hotel/detail.js"></script>
<script type="text/javascript" src="/resources/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/common/daterangepicker.js"></script>
<head>
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
                    酒店详情
                </span>
            </div>
            <div>
                <span id="spanOrder" class="glyphicon glyphicon-user"></span>
            </div>
        </div>
    </div>
    <!-- /.container -->
</nav>

<!-- 登录信息隐藏域 -->
<input id="inputUser" style="display: none" value="${user}">
<input id="inputLoginPage"  style="display: none" value="${loginPage}">

<!-- 酒店基础信息 -->
<div class="container">
    <input id="inputHotelId" style="display: none;" value="${hotelId}"/>
    <!-- 轮播图 -->
    <div id="carouselHotelImage" class="carousel slide">
        <!-- 轮播（Carousel）项目 -->
        <div id="divHotelImageList" class="carousel-inner" style="height: 300px;">

        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#carouselHotelImage" data-slide="prev">&lsaquo;
        </a>
        <a class="carousel-control right" href="#carouselHotelImage" data-slide="next">&rsaquo;
        </a>

    </div>
    <!-- 轮播图 -->

    <!-- 地图 -->
    <div id="allmap" style="height: 100px;width: 100%">

    </div>

    <div class="panel panel-default">
        <div id="divHotelSimpelInfo" class="panel-body my-hotelsimpleinfo">

        </div>
    </div>



    <div name="divMoreHotelInfo" id="divFacilities" class="panel panel-default" style="display: none;">
    </div>
    <div name="divMoreHotelInfo" id="divGeneralAmenities" class="panel panel-default" style="display: none;">
    </div>
    <div name="divMoreHotelInfo" id="divTraffic" class="panel panel-default" style="display: none;">
    </div>
</div>
<!-- 酒店基础信息 -->

<div class="container">
    <!-- 入离日期选择 -->
    <div class="row">
        <div class="col-lg-12">
            <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
                <input id="dateSelect" readonly="readonly" class="form-control" style="text-align: center; display: inline-block; background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%;">
                <input style="display: none" name="daterangepicker_start" value="${initArrivalDate}" />
                <input style="display: none" name="daterangepicker_end" value="${initDepartureDate}"/>
            </div>
        </div>
    </div>
    <!-- 入离日期选择 -->

</div>

<div class="container" style="position: relative; top: 8px;">
    <div id="divRooms" class="panel-group" id="accordion">

    </div>
</div>
</body>

</html>
