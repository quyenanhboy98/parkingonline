<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

    <head>
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <!--Css-->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <script>
            function GetBookingValue() {
                var rs = '${requestScope.BOOKING}';
                if (rs.length !== 0) {
                    document.getElementById("bookingID").value = "${requestScope.BOOKING.bookingID}";
                    document.getElementById("bookTime").value = "${requestScope.BOOKING.bookTime}";
                }
            }
        </script>
    </head>

    <body>
        <div id="container" class="col-md-12">

            <h1 style="margin-left: 10px; color: #0074ad">CHECK OUT</h1>

            <form action="CheckoutBooking" method="POST">
                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Booking ID</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-phone"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="bookingID" id="bookingID" value="" onchange="checkBookingIDCheckout()"/>  
                        </div>
                    </div>

                    <div class="col-md-6">
                        <h4>SĐT</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-lock"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" readonly="readonly" name="phone" id="phone" value=""/>  
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Giờ đặt chỗ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-phone"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" readonly="readonly" name="bookTime" id="bookTime" value=""/>  
                        </div>
                    </div>

                    <div class="col-md-6">
                        <h4>Phí thanh toán</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-lock"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" readonly="readonly" name="price" id="price" value=""/>  
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Giờ vào</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-phone"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" readonly="readonly" name="checkinTime" id="checkinTime" value=""/>  
                        </div>
                    </div>

                    <div class="col-md-6">
                        <h4>Giờ ra</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-lock"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" readonly="readonly" name="checkoutTime" id="checkoutTime" value=""/>  
                        </div>
                    </div>
                </div>

                <div class="col-md-3" style="margin-top: 20px;">
                    <input type="submit" class="btn btn-primary" value="Checkout">
                </div>
            </form>

            <c:if test="${requestScope.BILLID != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Thành công</h2>
                        </div>
                        <div class="modal-body">
                            <h3 style="font-family: monospace; color: black">Checkout thành công.</h3>
                        </div>
                        <div class="modal-footer">
                            <div class="col-md-10" id="buttonbookingpopup">
                                <form action="GetBillDetail">
                                    <input type="hidden" name="billID" value="${requestScope.BILLID}" />
                                    <input type="submit" class="btn btn-default" value="Xem chi tiết" />
                                </form>
                            </div>
                            <div class="col-md-2">
                                <input style="width: 80px" type="button" class="btn btn-default" value="Tắt" id="input-close" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${requestScope.ERROR != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Lỗi</h2>
                        </div>
                        <div class="modal-body">
                            <h3 style="font-family: monospace; color: red">${requestScope.ERROR}</h3>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="Close" id="input-close"/>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>
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