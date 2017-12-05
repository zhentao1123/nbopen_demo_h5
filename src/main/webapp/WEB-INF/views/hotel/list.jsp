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
<link rel="stylesheet" type="text/css" media="all" href="/resources/css/common/daterangepicker-bs3.css" />
<link rel="stylesheet" href="/resources/css/common/cityPicker.css" />
<link rel="stylesheet" href="/resources/css/hotel/list.css" />
<script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
<script type="text/javascript" src="/resources/plugins/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/common/moment.min.js"></script>
<script type="text/javascript" src="/resources/js/common/daterangepicker.js"></script>
<script type="text/javascript" src="/resources/js/common/cityData.js"></script>
<script type="text/javascript" src="/resources/js/common/cityPicker.js"></script>
<script type="text/javascript" src="/resources/js/hotel/list.js"></script>
<head>
    <title>酒店列表查询</title>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#" title="HOME">酒店列表</a>
        </div>
        <!-- /.navbar-header -->

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#">订单</a>
                </li>
            </ul>
            <!-- /.nav -->
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>
<div class="container">
    <!-- 城市选择 -->
    <div class="row">
        <div class="col-lg-12">
            <input id="cityChoice" type="text" class="form-control" style="text-align: center;" value="北京市">

        </div>
    </div> <!-- 城市选择 -->

    <!-- 入离日期选择 -->
    <div class="row">
        <div class="col-lg-12">
            <div id="dateSelect" class="btn" style="display: inline-block; background: #fff; cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; width: 100%;">
                <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
                <span></span> <b class="caret"></b>
            </div>
        </div>
    </div> <!-- 入离日期选择 -->

    <!-- 酒店名称、位置输入 -->
    <div class="row">
        <div class="col-lg-12">
            <input type="text" class="form-control" style="text-align: center;" placeholder="酒店名称/位置 不限">

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
                <button id='btnStarRateDefault' name="btnStarRate"  class="btn btn-info" style="width: 20%"> 不限 </button>
                <button id='btnStarRate012' name="btnStarRate"  class="btn btn-default" style="width: 20%"> 经济/客栈 </button>
                <button id='btnStarRate3' name="btnStarRate"  class="btn btn-default" style="width: 20%"> 三星/舒适 </button>
                <button id='btnStarRate4' name="btnStarRate"  class="btn btn-default" style="width: 20%"> 四星/高档 </button>
                <button id='btnStarRate5' name="btnStarRate"  class="btn btn-default" style="width: 20%"> 五星/豪华 </button>

            </div>
        </div>
    </div> <!-- 星级筛选 -->

    <div class="row">
        <!-- 价格筛选 -->
        <div class="col-sm-6">
            <div name="divSelect" class="dropdown" style="width: 100%;">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"  style="width: 100%;">
                    价格 不限
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="width: 100%;">
                    <li style="text-align: center;"><a href="#">价格 不限</a></li>
                    <li style="text-align: center;"><a href="#">价格：0-150元</a></li>
                    <li style="text-align: center;"><a href="#">价格：150-300元</a></li>
                    <li style="text-align: center;"><a href="#">价格：300-450元</a></li>
                    <li style="text-align: center;"><a href="#">价格：450-700元</a></li>
                    <li style="text-align: center;"><a href="#">价格：700元以上</a></li>
                </ul>
            </div>
        </div> <!-- 价格筛选 -->
        <!-- 排序规则 -->
        <div class="col-sm-6">
            <div name="divSelect" class="dropdown" style="width: 100%;">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"  style="width: 100%;">
                    智能排序
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenu1" style="width: 100%;">
                    <li style="text-align: center;"><a href="#">智能排序</a></li>
                    <li style="text-align: center;"><a href="#">推荐星级降序</a></li>
                    <li style="text-align: center;"><a href="#">价格升序</a></li>
                    <li style="text-align: center;"><a href="#">价格降序</a></li>
                    <li style="text-align: center;"><a href="#">距离升序</a></li>
                </ul>
            </div>
        </div> <!-- 排序规则 -->
    </div>

</div>

<div class="container" style="position: relative; top: 8px;">
    <div class="col-sm-12" style="text-align: center;">
        <button class="btn btn-info" style="width: 60%;">搜索</button>
    </div>
    <hr />
</div>

<div class="container" style="position: relative; top: 16px;">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div id="divHotelList" class="list-group">
                <div class="list-group-item">
                    <div class="container my-hotellist">
                        <img src="http://www.runoob.com/try/bootstrap/layoutit/v3/default3.jpg" style="width: 80px; height: 80px;"/>
                        <span>如家酒店</span>
                        <span>4.5分 <span>5808条点评</span></span>
                        <span>经济型</span>
                        <span>天安门/王府井商业区</span>
                        <span>￥448</span>
                    </div>
                </div>
                <div class="list-group-item">
                    <div class="container my-hotellist">
                        <img src="http://www.runoob.com/try/bootstrap/layoutit/v3/default3.jpg" style="width: 80px; height: 80px;"/>
                        <span>如家酒店</span>
                        <span>4.5分 <span>5808条点评</span></span>
                        <span>经济型</span>
                        <span>天安门/王府井商业区</span>
                        <span>￥448</span>
                    </div>
                </div>
                <div class="list-group-item">
                    <div class="container my-hotellist">
                        <img src="http://www.runoob.com/try/bootstrap/layoutit/v3/default3.jpg" style="width: 80px; height: 80px;"/>
                        <span>如家酒店</span>
                        <span>4.5分 <span>5808条点评</span></span>
                        <span>经济型</span>
                        <span>天安门/王府井商业区</span>
                        <span>￥448</span>
                    </div>
                </div>
            </div>
            <div id="pullMoreHotel" style="text-align: center;">点击加载更多</div>
        </div>
    </div>
</div>
</body>
</html>
