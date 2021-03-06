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


            <h1 style="margin-left: 10px; color: #0074ad">LỊCH SỬ THANH TOÁN</h1>
            <div class="body">
                <div class="row clearfix">
                    <div id="searchform" class="col-md-12" style="padding-top: 20px; float: left; display:  block">
                        <form action="searchListBills">
                            <div class="col-md-4">
                                <h4>Bãi xe</h4>
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
                            <div class="col-md-3">
                                <h4>Từ Ngày</h4>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="fromdate" type="text" class="form-control input-small" name="fromDate" readonly="readonly" value="${param.fromDate}">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <h4>Đến Ngày</h4>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="todate" type="text" class="form-control input-small" name="toDate" readonly="readonly" value="${param.toDate}">
                                </div>
                            </div>
                            <div class="col-md-2" style="margin-top: 3.8%; margin-bottom: 2%;">
                                <input type="submit" class="btn btn-primary btn-login" value="Tìm" style="width: 130px; height: 35px;" />
                            </div>
                        </form>
                    </div>

                </div>
                <div class="table-responsive" style="padding-top: 20px;">
                    <table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="example">
                        <colgroup>
                            <col style="width: 5%;" />
                            <col style="width: 20%;" />
                            <col style="width: 20%;" />
                            <col style="width: 20%;" />
                            <col style="width: 20%;" />
                            <col style="width: 10%" />
                            <col style="width: 5%;" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>STT.</th>
                                <th>BÃI XE</th>
                                <th>GIỜ ĐẶT</th>
                                <th>GIỜ VÀO</th>
                                <th>GIỜ RA</th>
                                <th>PHÍ</th>
                                <th>CHI TIẾT</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set value="${requestScope.LISTBILL}" var="bill" />
                            <c:set var="total" value="0" />
                            <c:forEach var="booking" items="${requestScope.LISTBOOKING}" varStatus="counter">
                                <tr>
                                    <td style="padding-top: 25px;">${counter.index + 1}</td>
                                    <td style="padding-top: 25px;">${booking.parkName}</td>
                                    <td style="padding-top: 25px;">${booking.bookTime}</td>
                                    <td style="padding-top: 25px;">${booking.checkinTime}</td>
                                    <td style="padding-top: 25px;">${booking.checkoutTime}</td>
                                    <td style="padding-top: 25px;">${bill[counter.index].money}</td>

                                    <td style="text-align:center; padding-top: 15px;">
                                        <form action="GetBillDetail">
                                            <input type="hidden" name="billID" value="${bill[counter.index].billID}" />
                                            <button class="btn btn-primary" type="submit">
                                                <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                            </button>
                                        </form>

                                    </td>
                                </tr>
                                <c:set var="total" value="${total + bill[counter.index].money}" />
                            </c:forEach>
                        </tbody>
                    </table>
                    <h4 style="margin: 2%;font-family: monospace; color: green">Tổng số doanh thu là ${total} VND</h4>
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
    <script src="assets/javascripts/ajax.js" type="text/javascript"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable({
                "pageLength": 10

            });
        });
    </script>
    <script src="assets/javascripts/datatable.js" type="text/javascript"></script>
    <script src="assets/javascripts/fontawesome-all.js"></script>


</html>