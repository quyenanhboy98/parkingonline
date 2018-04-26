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
        <link rel="stylesheet" href="assets/stylesheets/listuserbill.css" />
        <link rel="stylesheet" href="assets/stylesheets/pagination.css" />
        <style>
            #map {
                height: 350px;
                width: 550px
            }
        </style>
        <script>

            var currentPos;

            function initialize() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        currentPos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };
                        var direction = document.getElementById("directon");
                        if (direction !== null)
                            direction.style.display = "block";
                    });
                }

                drawMap();
            }

            function drawMap() {
                directionsDisplay = new google.maps.DirectionsRenderer();

                navigator.geolocation.getCurrentPosition(function (position) {

                    var pos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);

                    var mapOptions = {
                        zoom: 15,
                        center: pos
                    };

                    map = new google.maps.Map(document.getElementById('map'), mapOptions);
                    directionsDisplay.setMap(map);
                });
            }



            function calcRoute(latP, lngP) {
                var directionsService = new google.maps.DirectionsService();
                geocoder = new google.maps.Geocoder();
                desPos = {
                    lat: latP,
                    lng: lngP
                };
                var request = {
                    origin: currentPos,
                    destination: desPos,
                    travelMode: 'DRIVING'
                };
                directionsService.route(request, function (result, status) {
                    if (status === 'OK') {
                        directionsDisplay.setDirections(result);
                    }
                });
            }

            function isAdded() {
                var rs = '${requestScope.SUCCESS}';
                if (rs.length !== 0) {
                    document.getElementById("statusbookingpopup").innerText = "Thông báo";
                    document.getElementById("messagebooking").innerText = rs;
                    document.getElementById("messagebooking").style.display = "block";
                    document.getElementById("myModal").style.display = "block";
                }
            }

        </script>
    </head>

    <body onload="isAdded()">


        <div id="container">

            <div id="data-box">

                <header>
                    LỊCH SỬ THANH TOÁN
                </header>


                <div class="body-box">
                    <div class="col-sm-12" style="margin-top: 1%;" id="searchform">
                        <form action="searchListBillsOfUser">
                            <div class="col-md-4">
                                <div class="col-md-4 row">
                                    <h4>Ngày Vào</h4>
                                </div>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-7">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="fromdate" type="text" class="form-control input-small" name="fromDate" readonly="readonly" value="${param.fromDate}">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="col-md-4 row">
                                    <h4>Ngày Ra</h4>
                                </div>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-7">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="todate" type="text" class="form-control input-small" name="toDate" readonly="readonly" value="${param.toDate}">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <input type="submit" class="btn btn-primary btn-login ClickMe" value="TÌM" />
                            </div>
                        </form>
                    </div>


                    <div class="table-responsive data-table" style="padding-top: 20px;">
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
                                    <th>GIỜ ĐẶT CHỖ</th>
                                    <th>GIỜ VÀO</th>
                                    <th>GIỜ RA</th>
                                    <th>PHÍ THANH TOÁN</th>
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
                                </tbody>
                                <c:set var="total" value="${total + bill[counter.index].money}" />
                            </c:forEach>
                        </table>
                        <h4 style="margin: 2%;font-family: monospace; color: green">Tổng số tiền đã thanh toán là ${total} VND</h4>
                    </div>
                </div>
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
</div>

</body>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB8nz4AoPyn5FeuOSI1wDd0o0cbmu621jc&callback=initialize">
</script>
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
<script src="assets/javascripts/datatable.js" type="text/javascript"></script>
<script src="assets/javascripts/listuserbill.js" type="text/javascript"></script>

</html>