<%-- 
    Document   : bill
    Created on : Apr 2, 2018, 6:08:12 PM
    Author     : Yuu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/bill.css" />
        <title>JSP Page</title>
    </head>
    <body>
        <c:set value="${requestScope.EMP}" var="emp" />
        <c:set value="${requestScope.BOOKING}" var="booking" />
        <c:set value="${requestScope.USER}" var="user" />
        <c:set value="${requestScope.PARK}" var="park" />
        <c:set value="${requestScope.BILL}" var="bill" />
        <div class="receipt-content">
            <div class="container bootstrap snippet">
                <div class="row">
                    <div class="col-md-12">
                        <div class="invoice-wrapper">
                            <div class="intro">
                                <h2>HOÁ ĐƠN THANH TOÁN ĐỖ XE</h2>
                            </div>

                            <div class="payment-info">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <span>Bill No.</span>
                                        <strong>${bill.billID}</strong>
                                    </div>
                                </div>
                            </div>

                            <div class="payment-details">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <span>Bãi xe</span>
                                        <strong>
                                            ${park.parkName}
                                        </strong>
                                        <p>
                                            ${park.parkAddress}
                                        </p>
                                    </div>
                                    <div class="col-sm-6 text-right">
                                        <span>Khách Hàng</span>
                                        <strong>
                                            ${user.fullName}
                                        </strong>
                                        <p>
                                            ${user.address} <br/>
                                            <a href="#">
                                                ${user.email}
                                            </a>
                                        </p>
                                    </div>
                                </div>
                            </div>

                            <div class="line-items">
                                <div class="headers clearfix">
                                    <div class="row">
                                        <div class="col-xs-7">Nội Dung</div>
                                        <div class="col-xs-5 text-right">Giá</div>
                                    </div>
                                </div>
                                <div class="items">
                                    <div class="row item">
                                        <div class="col-xs-7 desc">
                                            Giờ vào
                                        </div>
                                        <div class="col-xs-5 amount text-right">
                                            ${booking.checkinTime}
                                        </div>
                                    </div>
                                    <div class="row item">
                                        <div class="col-xs-7 desc">
                                            Giờ ra
                                        </div>
                                        <div class="col-xs-5 amount text-right">
                                            ${booking.checkoutTime}
                                        </div>
                                    </div>
                                    <div class="row item">
                                        <div class="col-xs-7 desc">
                                            Điểm cộng
                                        </div>
                                        <div class="col-xs-5 amount text-right">
                                            ${bill.point} Điểm
                                        </div>
                                    </div>
                                    <div class="row item">
                                        <div class="col-xs-7 desc">
                                            Phí thanh toán
                                        </div>
                                        <div class="col-xs-5 amount text-right">
                                            ${bill.money} VNĐ
                                        </div>
                                    </div>
                                </div>
                                <div class="total text-right">
                                    <p class="extra-notes">
                                        <strong>Thông tin nhân viên thanh toán</strong>
                                        SĐT: ${emp.phone}<br/>
                                        Tên: ${emp.fullName}
                                    </p>
                                </div>
                                <div class="print">
                                    <a href="#">
                                        <i class="fa fa-print"></i>
                                        In hoá đơn
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>                    
    </body>
</html>
