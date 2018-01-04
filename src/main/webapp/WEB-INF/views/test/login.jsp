<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2017/12/22
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户中心</title>
    <script type="text/javascript" src="/resources/plugins/jquery/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="/resources/js/common/common.js"></script>
</head>
<body>
<input id="inputUserName" placeholder="用户名">
<input id="inputPassword" type="password" placeholder="密码">
<button id="btnRegister">注册</button>
<button id="btnLogin">登录</button>

<script>
    $(function () {
        $('#btnRegister').click(function () {
            if ($('#inputUserName').val() == null || $('#inputUserName').val() == "") {
                alert("请输入用户名");
                return;
            }
            if ($('#inputPassword').val() == null || $('#inputPassword').val() == "") {
                alert("请输入密码");
                return;
            }
            var url = "/test/register";
            var req = {
                "userName": $('#inputUserName').val(),
                "password": $('#inputPassword').val()
            };
            var result = ajaxCommonForJson(url, "POST", req);
            if (result!= null) {
                if (result.success) {
                    window.location = "/view/hotel/list?user=" + $('#inputUserName').val();
                }
                alert(result.message);
            }
        });

        $('#btnLogin').click(function () {
            if ($('#inputUserName').val() == null || $('#inputUserName').val() == "") {
                alert("请输入用户名");
                return;
            }
            if ($('#inputPassword').val() == null || $('#inputPassword').val() == "") {
                alert("请输入密码");
                return;
            }
            var url = "/test/access";
            var req = {
                "userName": $('#inputUserName').val(),
                "password": $('#inputPassword').val()
            };
            var result = ajaxCommonForJson(url, "POST", req);
            if (result!= null) {
                if (result.success) {
                    window.location = "/view/hotel/list?user=" + $('#inputUserName').val();
                }
                alert(result.message);
            }
        });
    });
</script>
</body>
</html>
