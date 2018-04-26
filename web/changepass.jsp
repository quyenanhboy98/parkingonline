<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

    <head>
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <!--Css-->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/stylesheets/customizeCheckbox.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/changepass.css" />
    </head>

    <body>
        <div id="container" class="col-md-12" style="margin-top: 20px;">

            <h1 style="margin-left: 10px; color: #0074ad">ĐỔI MẬT KHẨU</h1>

            <c:set var="error" value="${requestScope.ERROR}"/>
            <c:set var="user" value="${sessionScope.USERDTO}" />

            <form action="changePassword" method="POST">

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Số điện thoại</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-phone"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" readonly="readonly" name="phone" value="${user.phone}"/>  
                        </div>
                    </div>

                    <div class="col-md-6">
                        <h4>Mật khẩu hiện tại</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-lock"></icon>
                                </span>
                            </div>
                            <input type="password" class="form-control" name="current"/>
                        </div>
                        <label style="color:#EA2027">${error.currentPasswordIsEmpty}</label>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Mật khẩu mới</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-lock"></icon>
                                </span>
                            </div>
                            <input type="password" class="form-control" name="password"/>
                        </div>
                        <label style="color:#EA2027">${error.passwordIsEmpty}</label>
                    </div>

                    <div class="col-md-6">
                        <h4>Xác nhận mật khẩu</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-lock"></icon>
                                </span>
                            </div>
                            <input type="password" class="form-control" name="confirm"/>
                        </div>
                        <label style="color:#EA2027">${error.confirmIsNotMatch}</label>
                    </div>
                </div>

                <div class="col-md-3" style="margin-top: 20px;">
                    <input type="submit" class="btn btn-primary" value="ĐỔI MẬT KHẨU">
                </div>

            </form>

            <c:if test="${error.currentPasswordIsWrong != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: red">${error.currentPasswordIsWrong}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close"/>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${error.createError != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">LỖI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: red">${error.createError}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close"/>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${error == null && requestScope.SUCCESS != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: blue">${requestScope.SUCCESS }</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close"/>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>
        <!--JS-->
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/javascripts/fontawesome-all.js"></script>
        <script src="assets/javascripts/popup.js"></script>
    </body>

</html>