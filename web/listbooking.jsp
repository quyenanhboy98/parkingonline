<%-- 
    Document   : listbooking
    Created on : Mar 30, 2018, 11:06:12 PM
    Author     : Yuu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <!-- Font-Awesome -->
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <!-- Checkbox -->
        <link rel="stylesheet" href="assets/stylesheets/customizeCheckbox.css" />
        <!-- Css -->
        <link rel="stylesheet" href="assets/stylesheets/list.css">
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/pagination.css" />
        <link rel="stylesheet" href="assets/stylesheets/listbooking.css" />
        <link rel="stylesheet" href="assets/stylesheets/themes-bonus.css" />

    </head>

    <body>

        <div id="containner" class="col-md-12" style="margin-top: 20px;">


            <h1 style="margin-left: 10px; color: #0074ad">LỊCH SỬ ĐẶT CHỖ</h1>
            <div class="body">
                <div class="row clearfix">
                    <div id="searchform" class="col-md-12" style="padding-top: 20px; float: left; display:  block">
                        <form action="searchListBooking">
                            <div class="col-md-3">
                                <h4>Bãi Xe</h4>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>

                                    <c:if test="${sessionScope.USERDTO.role != 1}">
                                        <select name="parkID" class="form-control col-md-12">
                                            <option value="${sessionScope.USERDTO.parkID}">${sessionScope.USERDTO.parkName}</option>
                                        </select>
                                    </c:if>

                                    <c:if test="${sessionScope.USERDTO.role == 1}">
                                        <select name="parkID" class="form-control col-md-12">
                                            <option value="">Tất cả</option>
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

                                </div>
                            </div>
                            <div class="col-md-2">
                                <h4>Từ Ngày</h4>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="fromdate" type="text" class="form-control input-small" name="fromDate" readonly="readonly" value="${param.fromDate}">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <h4>Đến Ngày</h4>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="todate" type="text" class="form-control input-small" name="toDate" readonly="readonly" value="${param.toDate}">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <h4>Trạng Thái</h4>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-star"></i>
                                    </span>
                                    <select name="status" class="form-control">
                                        <option value="" selected="selected">Tất cả</option>
                                        <option value="1">Đang đặt</option> 
                                        <option value="2">Checkin</option> 
                                        <option value="3">Hoàn thành</option> 
                                        <option value="4">Đã huỷ</option> 
                                        <option value="5">Hết hạn</option> 
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-3" style="margin-top: 3.8%; margin-bottom: 2%;">
                                <input type="submit" class="btn btn-primary btn-login" value="TÌM" style="width: 130px; height: 35px;" />
                            </div>
                        </form>
                    </div>

                </div>
                <div class="table-responsive" style="padding-top: 20px;">
                    <table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="example">
                        <colgroup>
                            <col style="width: 5%;" />
                            <col style="width: 10%;" />
                            <col style="width: 20%;" />
                            <col style="width: 15%;" />
                            <col style="width: 15%;" />
                            <col style="width: 15%;" />
                            <col style="width: 15%" />
                            <col style="width: 5%" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>STT.</th>
                                <th>SĐT ĐẶT</th>
                                <th>BÃI XE</th>
                                <th>GIỜ ĐẶT</th>
                                <th>GIỜ HẾT HẠN</th>
                                <th>GIỜ VÀO</th>
                                <th>GIỜ RA</th>
                                <th>TRẠNG THÁI</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="booking" items="${requestScope.LISTBOOKING}" varStatus="counter">
                                <tr>
                                    <td style="padding-top: 25px;">${counter.index + 1}</td>
                                    <td style="padding-top: 25px;">${booking.phone}</td>
                                    <td style="padding-top: 25px;">${booking.parkName}</td>
                                    <td style="padding-top: 25px;">${booking.bookTime}</td>
                                    <td style="padding-top: 25px;">${booking.expiredTime}</td>
                                    <td style="padding-top: 25px;">${booking.checkinTime}</td>
                                    <td style="padding-top: 25px;">${booking.checkoutTime}</td>

                                    <c:if test="${booking.status == 1}">
                                        <td style="padding-top: 1.5%; text-align: center;">
                                            <div style="display: none">${booking.status}</div>
                                            <i class="fab fa-bimobject" style="font-size: 35px"></i>
                                        </td>
                                    </c:if>
                                    <c:if test="${booking.status == 2}">
                                        <td style="padding-top: 1.5%; text-align: center;">
                                            <div style="display: none">${booking.status}</div>
                                            <i class="fas fa-spinner" style="font-size: 35px"></i>
                                        </td>
                                    </c:if>
                                    <c:if test="${booking.status == 3}">
                                        <td style="padding-top: 1.5%; text-align: center;">
                                            <div style="display: none">${booking.status}</div>
                                            <i class="fas fa-check-square" style="font-size: 35px"></i>
                                        </td>
                                    </c:if>
                                    <c:if test="${booking.status == 4}">
                                        <td style="padding-top: 1.5%; text-align: center;">
                                            <div style="display: none">${booking.status}</div>
                                            <i class="far fa-times-circle" style="font-size: 35px"></i>
                                        </td>
                                    </c:if>
                                    <c:if test="${booking.status == 5}">
                                        <td style="padding-top: 1.5%; text-align: center;">
                                            <div style="display: none">${booking.status}</div>
                                            <i class="fas fa-hourglass-end" style="font-size: 35px"></i>
                                        </td>
                                    </c:if>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>


            </div>
            <c:if test="${not empty requestScope.LISTEMPTY}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Trạng thái</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: blue">${requestScope.LISTEMPTY}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="Đóng" id="input-close" />
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </body>

    <script src="assets/vendor/jquery/jquery.js"></script>
    <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
    <script src="assets/javascripts/moment.js"></script>
    <script src="assets/javascripts/collapse.js"></script>
    <script src="assets/javascripts/transition.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
    <script src="assets/javascripts/bootstrap-datetimepicker.min.js"></script>
    <script src="assets/javascripts/popup.js"></script>
    <script src="assets/javascripts/dateptimepicker.js"></script>

    <script src="assets/javascripts/adminpaging.js"></script>
    <script src="assets/javascripts/ajax.js" type="text/javascript"></script>
    <script src="assets/javascripts/datatable.js" type="text/javascript"></script>
    <script src="assets/javascripts/fontawesome-all.js"></script>

    <script>
        $('select[name="status"]').find('option[value="${param.status}"]').attr("selected", true);
    </script>

</html>