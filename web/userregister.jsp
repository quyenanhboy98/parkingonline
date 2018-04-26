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
        <!-- Css -->
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/userRegister.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />


    </head>

    <body onload="loadRegisterPage()">
        <div id="containner">
            <div id="header">
                <h1>PARKING ONLINE</h1>
            </div>
            <div id="login-area">
                <div class="login-box">

                    <div class="login-header">
                        <h1 class="title">ĐĂNG KÝ</h1>
                    </div>

                    <form action="addUser" method="POST">

                        <c:set var="error" value="${requestScope.ERROR}" />

                        <div class="login-body">

                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <h4>Số điện thoại</h4>
                                    <div class="input-group col-md-12">
                                        <div class="input-group-addon">
                                            <span>
                                                <icon class="fa fa-phone"></icon>
                                            </span>
                                        </div>
                                        <input type="text" class="form-control" name="phone" value="${param.phone}" />
                                    </div>
                                    <label style="color: red;">${error.phoneInputErrorFormat}</label>
                                </div>

                                <div class="col-md-6">
                                    <h4>Tên đầy đủ</h4>
                                    <div class="input-group col-md-12">
                                        <div class="input-group-addon">
                                            <span>
                                                <icon class="fab fa-amilia"></icon>
                                            </span>
                                        </div>
                                        <input type="text" class="form-control" name="fullName" value="${param.fullName}" />
                                    </div>
                                    <label style="color: red;">${error.nameIsEmpty}</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <h4>Mật khẩu</h4>
                                    <div class="input-group col-md-12">
                                        <div class="input-group-addon">
                                            <span>
                                                <icon class="fas fa-lock"></icon>
                                            </span>
                                        </div>
                                        <input type="password" class="form-control" name="password" />
                                    </div>
                                    <label style="color: red;">${error.passwordIsEmpty}</label>
                                </div>

                                <div class="col-md-6">
                                    <h4>Nhập lại mật khẩu</h4>
                                    <div class="input-group col-md-12">
                                        <div class="input-group-addon">
                                            <span>
                                                <icon class="fas fa-lock"></icon>
                                            </span>
                                        </div>
                                        <input type="password" class="form-control" name="confirm" />
                                    </div>
                                    <label style="color: red;">${error.confirmIsNotMatch}</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <h4>Email</h4>
                                    <div class="input-group col-md-12">
                                        <div class="input-group-addon">
                                            <span>
                                                <icon class="fas fa-envelope"></icon>
                                            </span>
                                        </div>
                                        <input type="text" class="form-control" name="email" value="${param.email}" />
                                    </div>
                                    <label style="color: red;">${error.emailIsEmpty}</label>
                                </div>

                                <div class="col-md-6">
                                    <h4>Địa chỉ</h4>
                                    <div class="input-group col-md-12">
                                        <div class="input-group-addon">
                                            <span>
                                                <icon class="fas fa-map-marker"></icon>
                                            </span>
                                        </div>
                                        <input type="text" class="form-control" name="address" value="${param.address}" />
                                    </div>
                                    <label style="color: red;">${error.addressIsEmpty}</label>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <h4>Ngày sinh</h4>
                                    <div class="input-group col-md-12">
                                        <div class="input-group-addon">
                                            <span>
                                                <icon class="fas fa-birthday-cake"></icon>
                                            </span>
                                        </div>
                                        <input type="text" id="datepicker" class="form-control" name="dob" value="${param.dob}" readonly="readonly" />
                                    </div>
                                    <label style="color: red;">${error.dobIsWrongFormation}</label>
                                </div>

                                <div class="col-md-6">
                                    <h4>Giới tính</h4>
                                    <c:if test="${!param.sex.equals('Female')}">
                                        <select class="col-md-12 form-control" name="sex">
                                            <option value="Male">Nam</option>
                                            <option value="Female">Nữ</option>
                                        </select>
                                    </c:if>

                                    <c:if test="${param.sex.equals('Female')}">
                                        <select class="col-md-12 form-control" name="sex">
                                            <option value="Female">Nữ</option>
                                            <option value="Male">Nam</option>
                                        </select>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                        <div class="login-foot">
                            <input type="hidden" value="userregister.jsp" name="srcPage" />
                            <div class="button-first-login">
                                <input type="submit" class="btn btn-primary btn-login first-button" value="ĐĂNG KÝ" />
                            </div>
                            <div class="button-login">
                                <button type="button" class="btn btn-primary btn-login" onclick="location.href = 'login.jsp'">
                                    ĐĂNG NHẬP
                                </button>
                            </div>


                        </div>

                    </form>

                    <c:if test="${error.phoneIsExisted != null}">
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h2 class="popup-status">Lỗi</h2>
                                </div>
                                <div class="modal-body">
                                    <h4 style="font-family: monospace; color: red">${error.phoneIsExisted}</h4>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" value="Close" id="input-close" />
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${error.createError != null}">
                        <div id="myModal" class="modal">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h2 class="popup-status">Lỗi</h2>
                                </div>
                                <div class="modal-body">
                                    <h4 style="font-family: monospace; color: red">${error.createError}</h4>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" value="Close" id="input-close" />
                                </div>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <!-- Js -->

        <script src="assets/javascripts/pageanimation.js"></script>
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
        <script src="assets/javascripts/moment.js"></script>
        <script src="assets/javascripts/collapse.js"></script>
        <script src="assets/javascripts/transition.min.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/javascripts/bootstrap-datetimepicker.min.js"></script>
        <script src="assets/javascripts/popup.js"></script>
        <script src="assets/javascripts/dateptimepicker.js"></script>
        <script src="assets/javascripts/ajax.js" type="text/javascript"></script>
        <script src="assets/javascripts/fontawesome-all.js"></script>
    </body>

</html>