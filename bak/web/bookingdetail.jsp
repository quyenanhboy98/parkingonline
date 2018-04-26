<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <title>BOOKING DETAIL</title>
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

                <c:set var="booking" value="${requestScope.BOOKINGDTO}"/>

                <!-- Form -->
                <div class="box-body">
                    <div class="col-md-12">
                        <div class="col-md-6">
                            <h4>ID</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-phone"></icon>
                                    </span>
                                </div>
                                <input type="text" class="form-control" name="bookingID" value="${booking.bookingID}" readonly="readonly" />
                            </div>
                        </div>

                        <div class="col-md-6">
                            <h4>BÃI XE</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-pencil"></icon>
                                    </span>
                                </div>
                                <input type="text" class="form-control" name="parkName" value="${booking.parkName}" readonly="readonly" />
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="col-md-6">
                            <h4>GIỜ ĐẶT CHỖ</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-times"></icon>
                                    </span>
                                </div>
                                <input type="text" class="form-control" name="bookTime" value="${booking.bookTime}" readonly="readonly"/>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <h4>GIỜ HẾT HẠN</h4>
                            <div class="input-group col-md-12">
                                <div class="input-group-addon">
                                    <span>
                                        <icon class="fa fa-times"></icon>
                                    </span>
                                </div>
                                <input type="text" class="form-control" name="expiredTime" value="${booking.expiredTime}" readonly="readonly"/>
                            </div>
                        </div>
                    </div>

                    <div class="box-footer">
                        <div id="button-left">
                            <input  type="submit" value="Cập nhật" class="btn btn-primary"/>
                        </div>
                        <div id="button-right">                 
                            <button type="button"  class="btn btn-primary" onclick="location.href = 'changepass.jsp'">Đổi mật khẩu</button>
                        </div>

                    </div>
                </div>

                <c:if test="${error.createError != null}">
                    <div id="myModal" class="modal">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="popup-status">TRẠNG THÁI</h2>
                            </div>
                            <div class="modal-body">
                                <h3 style="font-family: monospace; color: red">${error.createError}</h3>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" value="Close" id="input-close" />
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