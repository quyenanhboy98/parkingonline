<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Checkin</title>

        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/addnew.css" />
        <script>
            function GetBookingValue() {
                console.log("in");
                var rs = '${requestScope.BOOKING}';
                if (rs.length !== 0) {
                    document.getElementById("bookingID").value = "${requestScope.BOOKING.bookingID}";
                    document.getElementById("bookTime").value = "${requestScope.BOOKING.bookTime}";
                } else {
                    document.getElementById("cardID").value = "";
                    document.getElementById("bookingID").value = "";
                    document.getElementById("bookTime").value = "";
                }

                setTimeout(function () {
                    if (document.getElementById("bookingID").value.length > 0) {
                        document.getElementById("cardID").focus();
                    } else {
                        document.getElementById("phone").value = "";
                        document.getElementById("phone").focus();
                    }
                }, 500);
            }
        </script>
    </head>

    <body onload="GetBookingValue()">
        <div id="container" class="col-md-12" style="margin-top: 20px;">

            <h1 style="margin-left: 10px; color: #0074ad">CHECK IN</h1>

            <form action="CheckPhoneForCheckin" method="POST">
                <div class="col-md-12" style="float: left;"> 
                    <div class="col-md-4">
                        <h3>SỐ ĐIỆN THOẠI</h3>
                        <input type="text" class="form-control" id="phone" name="phone" value="${param.phone}" autofocus/>
                    </div>
                    <div class="col-md-3" style="margin-top: 5.5%;">
                        <input type="submit" class="btn btn-primary" value="CHỌN"  />
                    </div>
                </div>
            </form>

            <form action="CheckinBooking" method="POST">
                <div class="col-md-12" style="float: left;">

                    <div class="col-md-3">
                        <h3>SỐ THẺ</h3>
                        <input type="text" class="form-control" name="cardID" id="cardID" value="" autofocus/>
                    </div>

                    <div class="col-md-3">
                        <h3>GIỜ ĐẶT CHỖ</h3>
                        <input type="text" class="form-control" readonly="readonly" name="bookTime" id="bookTime" value="" />
                    </div>

                    <div class="col-md-3" style="margin-top: 5.5%;">
                        <input type="hidden" class="form-control" readonly="readonly" name="bookingID" id="bookingID" value="" />
                        <input type="submit" class="btn btn-primary" value="CHECKIN">
                    </div>
                </div>
            </form>




            <c:if test="${requestScope.ERROR != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Lỗi</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: red">${requestScope.ERROR}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" onclick="GetBookingValue()"/>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${requestScope.SUCCESS != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Thành công</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: black">${requestScope.SUCCESS}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" onclick="GetBookingValue()"/>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${requestScope.NOTBOOKING != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">THÔNG BÁO</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: black">${requestScope.NOTBOOKING}</h4>
                        </div>
                        <div class="modal-footer">
                            <div class="col-md-9">
                                <form action="BookingPark">
                                    <input type="hidden" name="phone" value="${param.phone}" />
                                    <input type="hidden" name="parkID" value="${sessionScope.USERDTO.parkID}" />
                                    <input type="hidden" name="duration" value="10 phút" />
                                    <input type="hidden" name="srcPage" value="CheckPhoneForCheckinServlet" />
                                    <input type="submit" style="width: 100px" class="btn btn-default" value="ĐẶT CHỖ" onclick="GetBookingValue()"/>
                                </form>
                            </div>
                            <div class="col-md-2">
                                <input style="width: 60px" type="button" class="btn btn-default" value="THOÁT" id="input-close" onclick="GetBookingValue()"/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>
    </body>



    <script src="assets/vendor/jquery/jquery.js"></script>
    <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
    <script src="assets/javascripts/popup.js"></script>
    <script src="assets/javascripts/ajax.js" type="text/javascript"></script>
    <script src="assets/javascripts/fontawesome-all.js"></script>

</html>