<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <title>
            Parking Online
        </title>
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <!-- Bootstrap -->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <!-- Font-Awesome -->
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <!-- Checkbox -->
        <link rel="stylesheet" href="assets/stylesheets/customizeCheckbox.css" />
        <!-- Css -->
        <link rel="stylesheet" href="assets/stylesheets/login.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css">

    </head>

    <body onload="loadLoginPage()">
        <div id="containner">
            <div id="header">
                <h1>PARKING ONLINE</h1>
            </div>
            <div id="login-area">
                <div class="login-box">
                    <div class="login-header">
                        <h1 class="title">ĐĂNG NHẬP</h1>
                    </div>
                    <form action="login" method="POST">
                        <!-- Form -->
                        <div class="login-body">
                            <h2>Số Điện Thoại</h2>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-phone"></icon>
                                    </span>
                                </div>
                                <input type="text" class="form-control" name="phone" />
                                <!-- phone input text-->
                            </div>
                            <h2>Mật Khẩu</h2>
                            <div class="input-group" style="margin-bottom : 20px;">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-unlock"></icon>
                                    </span>
                                </div>
                                <input type="password" class="form-control" name="password" />
                                <!-- password input text-->
                            </div>
                            <!-- a tag forgot password -->
                            <label class="cus-checkbox">
                                <a href="recoverypassword.jsp">Quên Mật Khẩu ?</a>
                            </label>
                        </div>
                        <div class="login-foot">
                            <div class="button-login">
                                <input type="submit" class="btn btn-primary btn-login" value="Đăng Nhập" style="font-size: 15px;" />
                            </div>
                            <div class="button-login">
                                <button type="button" class="btn btn-primary btn-login" onclick="location.href = 'userregister.jsp'">
                                    Đăng Ký
                                </button>
                            </div>
                        </div>
                    </form>

                    <c:if test="${requestScope.LOGINSTATUS != null}">
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <div class="modal-header ">
                                    <h2 class="popup-status">TRẠNG THÁI</h2>
                                </div>
                                <div class="modal-body">
                                    <h4 style="font-family: monospace; color: red">${requestScope.LOGINSTATUS}</h4>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${requestScope.SUCCESS != null}">
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h2 class="popup-status">TRẠNG THÁI</h2>
                                </div>
                                <div class="modal-body">
                                    <h4 style="font-family: monospace; color: green">${requestScope.SUCCESS}</h4>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                                </div>
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
        </div>
        <!-- Js -->
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/javascripts/pageanimation.js"></script>
        <script src="assets/javascripts/popup.js"></script>
    </body>

</html>