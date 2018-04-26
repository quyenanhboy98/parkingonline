<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
            <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
            <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
            <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
            <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
            <link rel="stylesheet" href="assets/stylesheets/popup.css" />
            <link rel="stylesheet" href="assets/stylesheets/addemployee.css">

        </head>

        <body onload="getWorkingTimeList()">
            <form action="addEmployee" method="POST">
                <div id="containner" style="margin-top: 20px;">

                    <h1 style="margin-left: 25px; color: #0074ad">THÊM NHÂN VIÊN</h1>
                    <c:set var="error" value="${requestScope.ERROR}" />

                    <div class="col-md-12">
                        <div class="col-md-6">
                            <h4>Số Điện Thoại</h4>
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
                            <h4>Tên Đầy Đủ</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-pencil"></icon>
                                    </span>
                                </div>
                                <input type="text" class="form-control" name="fullName" value="${param.fullName}" />
                            </div>
                            <label style="color: red;">${error.nameIsEmpty}</label>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="col-md-6">
                            <h4>Mật Khẩu</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-lock"></icon>
                                    </span>
                                </div>
                                <input type="password" class="form-control" name="password" />
                            </div>
                            <label style="color:#EA2027">${error.passwordIsEmpty}</label>
                        </div>

                        <div class="col-md-6">
                            <h4>Xác Nhận Mật Khẩu</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-lock"></icon>
                                    </span>
                                </div>
                                <input type="password" class="form-control" name="confirm" />
                            </div>
                            <label style="color:#EA2027">${error.confirmIsNotMatch}</label>
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
                                <input type="text" class="form-control" name="email" value="${param.email}" />
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
                                <input type="text" class="form-control" name="address" value="${param.address}" />
                            </div>
                            <label style="color: red;">${error.addressIsEmpty}</label>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="col-md-6">
                            <h4>Bãi Xe</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-car"></icon>
                                    </span>
                                </div>
                                <c:if test="${sessionScope.USERDTO.role == 1}">
                                    <select class="col-md-12 form-control" name="parkID" id="parkList" onchange="getWorkingTimeList()">
                                        <c:forEach items="${requestScope.LISTPARK}" var="dto">
                                            <c:if test="${dto.parkID.equals(param.parkID)}">
                                                <option value="${dto.parkID}" selected="selected">${dto.parkName}</option>
                                            </c:if>
                                            <c:if test="${!dto.parkID.equals(param.parkID)}">
                                                <option value="${dto.parkID}">${dto.parkName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </c:if>
                                <c:if test="${sessionScope.USERDTO.role == 3}">
                                    <select class="col-md-12 form-control" name="parkID" readonly="readonly" id="parkList" onchange="getWorkingTimeList()">
                                        <option value="${sessionScope.USERDTO.parkID}" selected="selected">${sessionScope.USERDTO.parkName}</option>
                                    </select>
                                </c:if>
                            </div>
                            <label style="color: red;"></label>
                        </div>

                        <div class="col-md-6">
                            <h4>Ca Làm Việc</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-times-circle"></icon>
                                    </span>
                                </div>
                                <select class="col-md-12 form-control" name="workingTimeID" id="workingTimeList">
                                    <option value="NWKT">Không có ca làm việc</option>
                                </select>

                            </div>
                            <label style="color: red;"></label>
                        </div>
                    </div>

                    <div class="col-md-12">

                        <div class="col-md-6">
                            <h4>Ngày Sinh</h4>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input type="text" id="datepicker" name="dob" class="form-control" value="${param.dob}" readonly="readonly">
                            </div>
                            <label style="color: red;">${error.dobIsWrongFormation}</label>
                        </div>

                        <div class="col-md-3">
                            <h4>Giới Tính</h4>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-mars"></i>
                                </span>
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

                        <div class="col-md-3">
                            <h4>Chức Vụ</h4>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-mars"></i>
                                </span>

                                <c:if test="${sessionScope.USERDTO.role == 3}">
                                    <select class="col-md-12 form-control" name="role">
                                        <option value="Staff">Nhân viên</option>
                                    </select>
                                </c:if>

                                <c:if test="${sessionScope.USERDTO.role == 1}">
                                    <c:if test="${!param.role.equals('Staff')}">
                                        <select class="col-md-12 form-control" name="role">
                                            <option value="Manager">Quản lí</option>
                                            <option value="Staff">Nhân viên</option>
                                        </select>
                                    </c:if>

                                    <c:if test="${param.role.equals('Staff')}">
                                        <select class="col-md-12 form-control" name="role">
                                            <option value="Staff">Nhân viên</option>
                                            <option value="Manager">Quản lí</option>
                                        </select>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2" style="margin-top: 20px; margin-left: 15px;">
                        <input type="hidden" value="addemployee.jsp" name="srcPage" />
                        <input style="width: 150px; height: 45px;" type="submit" value="THÊM NHÂN VIÊN" class="btn btn-primary" />
                    </div>
                </div>


            </form>

            <c:if test="${error.phoneIsExisted != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: red">${error.phoneIsExisted}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="ĐÓNG" id="input-close" />
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${error.createError != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: red">${error.createError}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="ĐÓNG" id="input-close" />
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
                            <h4 style="font-family: monospace; color: blue">${requestScope.SUCCESS}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="ĐÓNG" id="input-close" onclick="location.href = 'GetListParkForEmployee?srcPage=addemployee.jsp'" />
                        </div>
                    </div>
                </div>
            </c:if>

            <!--JS-->
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
        </body>

        </html>