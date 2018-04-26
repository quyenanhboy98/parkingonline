<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <title>USER PROFILE</title>
        <link rel="stylesheet" href="assets/stylesheets/profileuser.css" />
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
    </head>

    <body>
        <!-- start: search & user box -->
        <div id="container">
            <div class="box-area">
                <div class="box-header">
                    <h1>THÔNG TIN</h1>
                </div>

                <c:set var="user" value="${sessionScope.USERDTO}" />
                <c:set var="error" value="${requestScope.ERROR}"/>

                <form action="updateUser" method="POST">
                    <!-- Form -->
                    <div class="box-body">
                        <div class="col-md-12">
                            <div class="col-md-6">
                                <h4>Số Điện Thoại</h4>
                                <div class="input-group col-md-12">
                                    <div class="input-group-addon">
                                        <span>
                                            <icon class="fa fa-phone"></icon>
                                        </span>
                                    </div>
                                    <input type="text" class="form-control" name="phone" value="${user.phone}" readonly="readonly" />
                                </div>
                            </div>

                            <div class="col-md-6">
                                <h4>Tên Đầy Đủ</h4>
                                <div class="input-group col-md-12">
                                    <div class="input-group-addon">
                                        <span>
                                            <icon class="fa fa-pencil"></icon>
                                        </span>
                                    </div>

                                    <input type="text" class="form-control" name="fullname" value="${user.fullName}" />

                                </div>
                                <label style="color: red;">${error.nameIsEmpty}</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="col-md-6">
                                <h4>Email</h4>
                                <div class="input-group col-md-12">
                                    <div class="input-group-addon">
                                        <span>
                                            <icon class="fa fa-envelope"></icon>
                                        </span>
                                    </div>
                                    <input type="text" class="form-control" name="email" value="${user.email}" />
                                </div>
                                <label style="color: red;">${error.emailIsEmpty}</label>
                            </div>

                            <div class="col-md-6">
                                <h4>Địa Chỉ</h4>
                                <div class="input-group col-md-12">
                                    <div class="input-group-addon">
                                        <span>
                                            <icon class="fa fa-map-marker"></icon>
                                        </span>
                                    </div>
                                    <input type="text" class="form-control" name="address" value="${user.address}" />
                                </div>
                                <label style="color: red;">${error.addressIsEmpty}</label>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <div class="col-md-6">
                                <h4>Ngày Sinh</h4>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="fa fa-calendar"></i>
                                    </span>
                                    <input type="text" id="datepicker" name="dob" class="form-control" value="${user.dob}" readonly="readonly">
                                </div>
                                <label style="color: red;">${error.dobIsWrongFormation}</label>
                            </div>

                            <div class="col-md-3">
                                <h4>Giới Tính</h4>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="fa fa-mars"></i>
                                    </span>
                                    <c:if test="${user.sex == true}">
                                        <select class="col-md-12 form-control" name="sex">
                                            <option value="Male">Nam</option>
                                            <option value="Female">Nữ</option>
                                        </select>
                                    </c:if>

                                    <c:if test="${user.sex == false}">
                                        <select class="col-md-12 form-control" name="sex">
                                            <option value="Female">Nữ</option>
                                            <option value="Male">Nam</option>
                                        </select>
                                    </c:if>
                                </div>
                            </div>
                            <div class="col-md-3">
                                <h4>Điểm</h4>
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="fa fa-usd"></i>
                                    </span>
                                    <input type="text" name="point" class="form-control" value="${user.point}" readonly="readonly">
                                </div>
                                <label style="color: red;"></label>
                            </div>
                        </div>
                        <div class="box-footer">
                            <div id="button-left">
                                <input type="hidden" name="srcPage" value="profileuser.jsp" />
                                <input  type="submit" value="Cập Nhật" class="btn btn-primary"/>
                            </div>
                            <div id="button-right">                 
                                <button type="button"  class="btn btn-primary" onclick="location.href = 'changepass.jsp'">Đổi Mật Khẩu</button>
                            </div>

                        </div>
                    </div>
                </form>
                <c:if test="${error.createError != null}">
                    <div id="myModal" class="modal">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="popup-status">LỖI</h2>
                            </div>
                            <div class="modal-body">
                                <h4 style="font-family: monospace; color: red">${error.createError}</h4>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${error == null && requestScope.SUCCESS != null}">
                    <div id="myModal" class="modal">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="popup-status">TRẠNG THÁI</h2>
                            </div>
                            <div class="modal-body">
                                <h4 style="font-family: monospace; color: blue">${requestScope.SUCCESS }</h4>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                            </div>
                        </div>
                    </div>
                </c:if> 
            </div>
    </body>
    <!-- Vendor -->
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

</html>