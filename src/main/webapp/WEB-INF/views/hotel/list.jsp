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
    <div class="row">
        <div class="col-lg-12">
            <input id="cityChoice" type="text" class="form-control" style="text-align: center;" value="北京市">
            <input id="city" value="0101" type="text" style="display: none">
            <input id="province" value="0100" type="text" style="display: none">
        </div>
    </div> <!-- 城市选择 -->

    <!-- 入离日期选择 -->
    <div class="row">
        <div class="col-lg-12">
        <div class="input-group">
            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
            <input id="dateSelect" readonly="readonly" class="form-control" style="text-align: center; display: inline-block; background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%;">
            <input style="display: none" name="daterangepicker_start" />
            <input style="display: none" name="daterangepicker_end"/>
        </div>
        </div>
    </div> <!-- 入离日期选择 -->

    <!-- 酒店名称、位置输入 -->
    <div class="row">
        <div class="col-lg-12">
            <input id="inputHotelOrLocation" type="text" class="form-control" style="text-align: center;" placeholder="酒店名称/位置 不限">

        </div>
    </div> <!-- 酒店名称、位置输入 -->

    <!-- 分割线，之后是过滤条件 -->
    <div class="row">
        <div class="col-lg-12">
            <hr class="divider" />
        </div>
    </div> <!-- 分割线，之后是过滤条件 -->

    <!-- 星级筛选 -->
    <div class="row">
        <div class="col-lg-12">
            <div class="btn-group" data-toggle="buttons"  style="width: 100%;">
                <button id='btnStarRateDefault' name="btnStarRate" value="-1" class="btn btn-default" style="width: 20%"> 不限 </button>
                <button id='btnStarRate012' name="btnStarRate" value="0,1,2" class="btn btn-default" style="width: 20%"> 经济/客栈 </button>
                <button id='btnStarRate3' name="btnStarRate" value="3" class="btn btn-default" style="width: 20%"> 三星/舒适 </button>
                <button id='btnStarRate4' name="btnStarRate" value="4" class="btn btn-default" style="width: 20%"> 四星/高档 </button>
                <button id='btnStarRate5' name="btnStarRate" value="5" class="btn btn-default" style="width: 20%"> 五星/豪华 </button>

            </div>
        </div>
    </div> <!-- 星级筛选 -->

    <div class="row">
        <!-- 价格筛选 -->
        <div class="col-sm-6">
            <div name="divSelect" class="dropdown" style="width: 100%;">
                <button id="btnRate" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"  style="width: 100%;">
                    价格 不限<span style="display: none" lowrate="-1" highrate="-1"></span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="width: 100%;">
                    <li style="text-align: center;"><a>价格 不限<span style="display: none" lowrate="-1" highrate="-1"></span></a></li>
                    <li style="text-align: center;"><a>价格：0-150元<span style="display: none" lowrate="0" highrate="150"></span></a></li>
                    <li style="text-align: center;"><a>价格：150-300元<span style="display: none" lowrate="150" highrate="300"></span></a></li>
                    <li style="text-align: center;"><a>价格：300-450元<span style="display: none" lowrate="300" highrate="450"></span></a></li>
                    <li style="text-align: center;"><a>价格：450-700元<span style="display: none" lowrate="450" highrate="700"></span></a></li>
                    <li style="text-align: center;"><a>价格：700元以上<span style="display: none" lowrate="700" highrate="100000"></span></a></li>
                </ul>
            </div>
        </div> <!-- 价格筛选 -->
        <!-- 排序规则 -->
        <div class="col-sm-6">
            <div name="divSelect" class="dropdown" style="width: 100%;">
                <button id="btnSort" class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"  style="width: 100%;">
                    智能排序<span style="display:none;" value="Default"></span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="width: 100%;">
                    <li style="text-align: center;"><a>智能排序<span style="display:none;" value="Default"></span></a></li>
                    <li style="text-align: center;"><a>推荐星级降序<span style="display:none;" value="StarRankDesc"></span></a></li>
                    <li style="text-align: center;"><a>价格升序<span style="display:none;" value="RateAsc"></span></a></li>
                    <li style="text-align: center;"><a>价格降序<span style="display:none;" value="RateDesc"></span></a></li>
                </ul>
            </div>
        </div> <!-- 排序规则 -->
    </div>

</div>

<div class="container" style="position: relative; top: 8px;">
    <div class="col-sm-12" style="text-align: center;">
        <button id="btnSearch" class="btn btn-info" style="width: 60%;">搜索</button>
    </div>
    <hr />
</div>

<div class="container" style="position: relative; top: 16px;">
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
