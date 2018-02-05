<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2018/1/9
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>用户中心</title>
    <link href="/resources/css/test/default.css" rel="stylesheet" type="text/css">
    <!--必要样式-->
    <link href="/resources/css/test/styles.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/test/demo.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/test/loaders.css" rel="stylesheet" type="text/css">
    <link id="layuicss-skinlayercss" rel="stylesheet" href="/resources/plugins/layui/css/modules/layer/default/layer.css?v=3.0.3303" media="all">
</head>
<body style>
<canvas class="pg-canvas" width="1388" height="150"></canvas>

<div class="login">
    <div class="login_title">
        <span>登录以开始演示</span>
    </div>
    <div class="login_fields" style="width: 100%;">
        <div class="login_fields__user" style="width: 100%;">
            <div class="icon">
                <img alt="" src="/resources/img/user_icon_copy.png">
            </div>
            <input name="login" placeholder="用户名" maxlength="16" type="text" autocomplete="off" style="width: 100%;">
            <div class="validation">
                <img alt="" src="/resources/img/tick.png">
            </div>
        </div>
        <div class="login_fields__password" style="width: 100%;">
            <div class="icon">
                <img alt="" src="/resources/img/lock_icon_copy.png">
            </div>
            <input name="pwd" placeholder="密码" maxlength="16" type="text" autocomplete="off" style="width: 100%;">
            <div class="validation">
                <img alt="" src="/resources/img/tick.png">
            </div>
        </div>
        <div class="login_fields__password" style="width: 100%;">
            <div class="icon">
                <img alt="" src="/resources/img/key.png">
            </div>
            <input name="code" placeholder="验证码" maxlength="4" type="text" autocomplete="off" style="width: 100%;">
            <div class="validation" style="opacity: 1; right: -5px;top: -3px;">
                <canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
            </div>
        </div>
        <div class="login_fields__submit" style="top: 50px; left: -10px;">
            <button id="btnLogin" type="button" method="login" value="登录">
        </div>
        <div class="login_fields__submit" style="position: absolute; top: 176px; left: 170px;">
            <button id="btnRegister" type="button" method="register" value="注册">
        </div>
    </div>
    <div class="success">
    </div>
</div>
<div class="authent">
    <div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
    </div>
    <p>认证中...</p>
</div>
<div class="OverWindows"></div>

<link href="/resources/plugins/layui/css/layui.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/resources/js/test/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/test/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/js/test/stopExecutionOnTimeout.js?t=1"></script>
<script type="text/javascript" src="/resources/plugins/layui/layui.js"></script>
<%--<script type="text/javascript" src="/resources/js/test/Particleground.js"></script>--%>
<script type="text/javascript" src="/resources/js/test/Treatment.js"></script>
<script type="text/javascript" src="/resources/js/test/jquery.mockjax.js"></script>
<script type="text/javascript">
    var canGetCookie = 0;//是否支持存储Cookie 0 不支持 1 支持
    var ajaxmockjax = 1;//是否启用虚拟Ajax的请求响 0 不启用  1 启用
    //默认账号密码

    var truelogin = "123456";
    var truepwd = "123456";
    var method = "login";

    var CodeVal = 0;
    Code();
    function Code() {
        if(canGetCookie == 1){
            createCode("AdminCode");
            var AdminCode = getCookieValue("AdminCode");
            showCheck(AdminCode);
        }else{
            showCheck(createCode(""));
        }
    }
    function showCheck(a) {
        CodeVal = a;
        var c = document.getElementById("myCanvas");
        var ctx = c.getContext("2d");
        ctx.clearRect(0, 0, 1000, 1000);
        ctx.font = "80px 'Hiragino Sans GB'";
        ctx.fillStyle = "#E8DFE8";
        ctx.fillText(a, 0, 100);
    }
    $(document).keypress(function (e) {
        // 回车键事件
        if (e.which == 13) {
            $('#btnLogin').click();
        }
    });
    //粒子背景特效
    // $('body').particleground({
    //     dotColor: '#E8DFE8',
    //     lineColor: '#133b88'
    // });
    $('input[name="pwd"]').focus(function () {
        $(this).attr('type', 'password');
    });
    $('input[type="text"]').focus(function () {
        $(this).prev().animate({ 'opacity': '1' }, 200);
    });
    $('input[type="text"],input[type="password"]').blur(function () {
        $(this).prev().animate({ 'opacity': '.5' }, 200);
    });
    $('input[name="login"],input[name="pwd"]').keyup(function () {
        var Len = $(this).val().length;
        if (!$(this).val() == '' && Len >= 5) {
            $(this).next().animate({
                'opacity': '1',
                'right': '30'
            }, 200);
        } else {
            $(this).next().animate({
                'opacity': '0',
                'right': '20'
            }, 200);
        }
    });
    var open = 0;

    layui.use('layer', function () {

        $('#btnLogin').click(function () {
            method = "login";
            var login = $('input[name="login"]').val();
            var pwd = $('input[name="pwd"]').val();
            var code = $('input[name="code"]').val();
            if (login == '') {
                ErroAlert('请输入您的账号');
            } else if (pwd == '') {
                ErroAlert('请输入密码');
            } else if (code == '' || code.length != 4) {
                ErroAlert('输入验证码');
            } else if(code.toUpperCase() != CodeVal.toUpperCase()) {
                ErroAlert('验证码错误');
            } else {
                //认证中..
                $('.login').addClass('test'); //倾斜特效
                setTimeout(function () {
                    $('.login').addClass('testtwo'); //平移特效
                }, 300);
                setTimeout(function () {
                    $('.authent').show().animate({ right: -320 }, {
                        easing: 'easeOutQuint',
                        duration: 600,
                        queue: false
                    });
                    $('.authent').animate({ opacity: 1 }, {
                        duration: 200,
                        queue: false
                    }).addClass('visible');
                }, 500);

                //登录
                var JsonData = {
                    "userName": login,
                    "password": pwd,
                    "method": method
                };
                //此处做为ajax内部判断
                var url = "/test/loginRequest";

                AjaxPost(url, JsonData,
                    function () {
                        //ajax加载中
                    },
                    function (data) {
                        //ajax返回
                        //认证完成
                        setTimeout(function () {
                            $('.authent').show().animate({ right: 90 }, {
                                easing: 'easeOutQuint',
                                duration: 600,
                                queue: false
                            });
                            $('.authent').animate({ opacity: 0 }, {
                                duration: 200,
                                queue: false
                            }).addClass('visible');
                            $('.login').removeClass('testtwo'); //平移特效
                        }, 2000);
                        setTimeout(function () {
                            $('.authent').hide();
                            $('.login').removeClass('test');
                            if (data.success) {
                                //登录成功
                                $('.login div').fadeOut(100);
                                $('.success').fadeIn(1000);
                                $('.success').html(data.message);
                                //跳转操作
                                window.location = "/view/hotel/list?user=" + login;
                            } else {
                                ErroAlert(data.message);
                            }
                        }, 2400);
                    })
            }
        });

    })

    $('#btnRegister').click(function () {
        method = "register";
        var login = $('input[name="login"]').val();
        var pwd = $('input[name="pwd"]').val();
        var code = $('input[name="code"]').val();
        if (login == '') {
            ErroAlert('请输入您的账号');
        } else if (pwd == '') {
            ErroAlert('请输入密码');
        } else if (code == '' || code.length != 4) {
            ErroAlert('输入验证码');
        } else if(code.toUpperCase() != CodeVal.toUpperCase()) {
            ErroAlert('验证码错误');
        } else {
            //认证中..
            $('.login').addClass('test'); //倾斜特效
            setTimeout(function () {
                $('.login').addClass('testtwo'); //平移特效
            }, 300);
            setTimeout(function () {
                $('.authent').show().animate({ right: -320 }, {
                    easing: 'easeOutQuint',
                    duration: 600,
                    queue: false
                });
                $('.authent').animate({ opacity: 1 }, {
                    duration: 200,
                    queue: false
                }).addClass('visible');
            }, 500);

            //登录
            var JsonData = {
                "userName": login,
                "password": pwd,
                "method": method
            };
            //此处做为ajax内部判断
            var url = "/test/loginRequest";

            AjaxPost(url, JsonData,
                function () {
                    //ajax加载中
                },
                function (data) {
                    //ajax返回
                    //认证完成
                    setTimeout(function () {
                        $('.authent').show().animate({ right: 90 }, {
                            easing: 'easeOutQuint',
                            duration: 600,
                            queue: false
                        });
                        $('.authent').animate({ opacity: 0 }, {
                            duration: 200,
                            queue: false
                        }).addClass('visible');
                        $('.login').removeClass('testtwo'); //平移特效
                    }, 2000);
                    setTimeout(function () {
                        $('.authent').hide();
                        $('.login').removeClass('test');
                        if (data.success) {
                            //登录成功
                            $('.login div').fadeOut(100);
                            $('.success').fadeIn(1000);
                            $('.success').html(data.message);
                            //跳转操作
                            window.location = "/view/hotel/list?user=" + login;
                        } else {
                            ErroAlert(data.message);
                        }
                    }, 2400);
                })
        }
    });
    var fullscreen = function () {
        elem = document.body;
        if (elem.webkitRequestFullScreen) {
            elem.webkitRequestFullScreen();
        } else if (elem.mozRequestFullScreen) {
            elem.mozRequestFullScreen();
        } else if (elem.requestFullScreen) {
            elem.requestFullscreen();
        } else {
            //浏览器不支持全屏API或已被禁用
        }
    }
    if(ajaxmockjax == 1){
        $.mockjax({
            url: 'Ajax/Login',
            status: 200,
            responseTime: 50,
            responseText: {"Status":"ok","Text":"登录成功<br /><br />欢迎回来"}
        });
        $.mockjax({
            url: 'Ajax/LoginFalse',
            status: 200,
            responseTime: 50,
            responseText: {"Status":"Erro","Erro":"账号名或密码或验证码有误"}
        });
    }
</script>



<div class="layui-layer-move"></div>
</body>
</html>
