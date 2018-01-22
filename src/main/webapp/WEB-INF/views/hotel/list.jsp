<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/11/24
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>酒店列表</title>
<link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<link rel="stylesheet" href="/resources/plugins/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css" />
<link rel="stylesheet" type="text/css" media="all" href="/resources/css/common/daterangepicker.css" />
<link rel="stylesheet" href="/resources/css/common/cityPicker.css" />
<link rel="stylesheet" href="/resources/css/hotel/list.css" />
<script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
<script type="text/javascript" src="/resources/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/common/moment.min.js"></script>
<script type="text/javascript" src="/resources/js/common/daterangepicker.js"></script>
<script type="text/javascript" src="/resources/js/common/cityData.js"></script>
<script type="text/javascript" src="/resources/js/common/cityPicker.js"></script>
<script type="text/javascript" src="/resources/js/common/common.js"></script>
<script type="text/javascript" src="/resources/js/hotel/list.js"></script>
<head>
    <title>酒店列表查询</title>
</head>
<body style="background: #F4F4F4;">
<nav class="navbar navbar-default my-header">
    <div class="container">
        <div>
            <div>
            <span>
                酒店列表
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

<div class="container">
    <!-- 城市选择 -->
    <div class="row search-item">
        <div class="col-lg-12">
            <i class="glyphicon glyphicon-road"></i>
            <i class="glyphicon glyphicon-chevron-right"></i>
            <input id="cityChoice" type="text" class="form-control" value="北京市" readonly="readonly">
            <input id="city" value="0101" type="text" style="display: none">
            <input id="province" value="0100" type="text" style="display: none">
        </div>
    </div>
    <!-- 城市选择 -->

    <!-- 入离日期选择 -->
    <div class="row search-item">
        <div class="col-lg-12">
            <i class="glyphicon glyphicon-calendar"></i>
            <i class="glyphicon glyphicon-chevron-right"></i>
            <input id="dateSelect" readonly="readonly" class="form-control" value="2017-01-15 到 2017-01-16">
            <input style="display: none" name="daterangepicker_start">
            <input style="display: none" name="daterangepicker_end">
        </div>
    </div>
    <!-- 入离日期选择 -->

    <!-- 酒店名称、位置输入 -->
    <div class="row search-item">
        <div class="col-lg-12">
            <i class="glyphicon glyphicon-map-marker"></i>
            <input id="inputHotelOrLocation" type="text" class="form-control" placeholder="酒店名称/位置 不限">

        </div>
    </div>
    <!-- 酒店名称、位置输入 -->

    <!-- 星级筛选 -->
    <div class="row search-item">
        <div class="tit">星级（可多选）</div>
        <div class="btn-group" data-toggle="buttons" style="width: 100%">
            <button id="btnStarRateDefault" name="btnStarRate" value="-1" class="btn btn-info" style="width: 20%">不限</button>
            <button id="btnStarRate012" name="btnStarRate" value="0,1,2" class="btn btn-default" style="width: 20%">经济/客栈</button>
            <button id="btnStarRate3" name="btnStarRate" value="3" class="btn btn-default" style="width: 20%">三星/舒适</button>
            <button id="btnStarRate4" name="btnStarRate" value="4" class="btn btn-default" style="width: 20%">四星/高档</button>
            <button id="btnStarRate5" name="btnStarRate" value="5" class="btn btn-default" style="width: 20%">五星/豪华</button>
        </div>
    </div>
    <!-- 星级筛选 -->

    <!-- 价格筛选 -->
    <div class="row search-item">
        <div class="tit">价格</div>
        <div class="btn-group" style="width: 100%">
            <button name="btnRate" class="btn btn-info" style="width: 16.66%" lowRate="-1" highRate="-1">不限</button>
            <button name="btnRate" class="btn btn-default" style="width: 16.66%" lowRate="0" highRate="150">0-150</button>
            <button name="btnRate" class="btn btn-default" style="width: 16.66%" lowRate="150" highRate="300">150-300</button>
            <button name="btnRate" class="btn btn-default" style="width: 16.66%" lowRate="300" highRate="450">300-450</button>
            <button name="btnRate" class="btn btn-default" style="width: 16.66%" lowRate="450" highRate="700">450-700</button>
            <button name="btnRate" class="btn btn-default" style="width: 16.66%" lowRate="700" highRate="100000">700以上</button>
        </div>
    </div>
    <!-- 价格筛选 -->
    <!-- 排序规则 -->
    <div class="row search-item">
        <div class="tit">排序方式</div>
        <div class="btn-group" style="width: 100%">
            <button name="btnSortType" class="btn btn-info" style="width: 25%" value="Default">智能排序</button>
            <button name="btnSortType" class="btn btn-default" style="width: 25%" value="StarRankDesc">推荐星级降序</button>
            <button name="btnSortType" class="btn btn-default" style="width: 25%" value="RateAsc">价格升序</button>
            <button name="btnSortType" class="btn btn-default" style="width: 25%" value="RateDesc">价格降序</button>
        </div>
    </div>
    <!-- 排序规则 -->

</div>

<div class="container search-box">
    <div class="col-sm-12">
        <button id="btnSearch" class="btn btn-info">搜索</button>
    </div>
</div>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="divHotelList" class="list-group">

            </div>
            <div id="pullMoreHotel" style="text-align: center; display: none">点击加载更多</div>
            <div id="noHotelInfo" style="text-align: center; display: none">没有更多酒店了</div>
        </div>
    </div>
</div>
</body>
</html>
