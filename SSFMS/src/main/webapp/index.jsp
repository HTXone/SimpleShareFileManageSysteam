<%--
  Created by IntelliJ IDEA.
  User: 29254
  Date: 2020/12/20
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>登录 | 共享云端</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- favicon
		============================================ -->
    <link rel="shortcut icon" type="image/x-icon" href="img/factory1.ico">
    <!-- Google Fonts
		============================================ -->
    <link href="https://fonts.googleapis.com/css?family=Play:400,700" rel="stylesheet">
    <!-- Bootstrap CSS
		============================================ -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Bootstrap CSS
		============================================ -->
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- owl.carousel CSS
		============================================ -->
    <link rel="stylesheet" href="css/owl.carousel.css">
    <link rel="stylesheet" href="css/owl.theme.css">
    <link rel="stylesheet" href="css/owl.transitions.css">
    <!-- animate CSS
		============================================ -->
    <link rel="stylesheet" href="css/animate.css">
    <!-- normalize CSS
		============================================ -->
    <link rel="stylesheet" href="css/normalize.css">
    <!-- main CSS
		============================================ -->
    <link rel="stylesheet" href="css/main.css">
    <!-- morrisjs CSS
		============================================ -->
    <link rel="stylesheet" href="css/morrisjs/morris.css">
    <!-- mCustomScrollbar CSS
		============================================ -->
    <link rel="stylesheet" href="css/scrollbar/jquery.mCustomScrollbar.min.css">
    <!-- metisMenu CSS
		============================================ -->
    <link rel="stylesheet" href="css/metisMenu/metisMenu.min.css">
    <link rel="stylesheet" href="css/metisMenu/metisMenu-vertical.css">
    <!-- calendar CSS
		============================================ -->
    <link rel="stylesheet" href="css/calendar/fullcalendar.min.css">
    <link rel="stylesheet" href="css/calendar/fullcalendar.print.min.css">
    <!-- forms CSS
		============================================ -->
    <link rel="stylesheet" href="css/form/all-type-forms.css">
    <!-- style CSS
		============================================ -->
    <link rel="stylesheet" href="style.css">
    <!-- responsive CSS
		============================================ -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- modernizr JS
		============================================ -->
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    <script src="vue.js"></script>
</head>

<body>
<!--[if lt IE 8]>
<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->
<div class="error-pagewrap">
    <div class="error-page-int">
        <div class="text-center m-b-md custom-login">
            <h3>请登录</h3>
            <p>After you log in, you can use the best web page!</p>
        </div>
        <div class="content-error">
            <div class="hpanel">
                <div class="panel-body">
                    <form action="user/login.do" id="loginForm"  method="post">
                        <div class="form-group">
                            <label class="control-label" for="username">用户名</label>
                            <c:if test="${loginfail}">
                                <span style="margin-left: 10px;color: #ff0000;">用户名或密码错误</span>
                            </c:if>
                            <input type="text" placeholder="请输入8-12位数字与字母组成的用户名" title="Please enter you username" required="" value="" name="username" id="username" class="form-control">
                            <span class="help-block small">你独一无二的用户名</span>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="password">密码</label>
                            <input type="password" title="Please enter your password" placeholder="请输入你的密码" required="" value="" name="password" id="password" class="form-control">
                            <span class="help-block small">这个世界上“最复杂”的密码</span>
                        </div>
                        <button class="btn btn-success btn-block loginbtn" id="click1">登录</button>
                        <a class="btn btn-default btn-block" href="register.html">注册</a>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- jquery
    ============================================ -->
<script src="/js/vendor/jquery-1.12.4.min.js"></script>
<!-- bootstrap JS
    ============================================ -->
<script src="/js/bootstrap.min.js"></script>
<!-- wow JS
    ============================================ -->
<script src="/js/wow.min.js"></script>
<!-- price-slider JS
    ============================================ -->
<script src="/js/jquery-price-slider.js"></script>
<!-- meanmenu JS
    ============================================ -->
<script src="/js/jquery.meanmenu.js"></script>
<!-- owl.carousel JS
    ============================================ -->
<script src="/js/owl.carousel.min.js"></script>
<!-- sticky JS
    ============================================ -->
<script src="/js/jquery.sticky.js"></script>
<!-- scrollUp JS
    ============================================ -->
<script src="/js/jquery.scrollUp.min.js"></script>
<!-- mCustomScrollbar JS
    ============================================ -->
<script src="/js/scrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="/js/scrollbar/mCustomScrollbar-active.js"></script>
<!-- metisMenu JS
    ============================================ -->
<script src="/js/metisMenu/metisMenu.min.js"></script>
<script src="/js/metisMenu/metisMenu-active.js"></script>
<!-- tab JS
    ============================================ -->
<script src="/js/tab.js"></script>
<!-- icheck JS
    ============================================ -->
<script src="/js/icheck/icheck.min.js"></script>
<script src="/js/icheck/icheck-active.js"></script>
<!-- plugins JS
    ============================================ -->
<script src="/js/plugins.js"></script>
<!-- main JS
    ============================================ -->
<script src="/js/main.js"></script>
</body>

</html>
<script type="text/javascript">


</script>
<script>

</script>