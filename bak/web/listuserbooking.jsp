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
                            DANH SÁCH ĐẶT CHỖ
                        </header>


                        <div class="body-box">
                            <div class="col-sm-12" style="margin-top: 1%;" id="searchform">
                                <form action="searchListBookingOfUser">
                                    <div class="col-md-6">
                                        <h4>Ngày đặt - Từ ngày</h4>
                                        <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-time"></i>
                                            </span>
                                            <input id="fromdate" type="text" class="form-control input-small" name="fromDate" readonly="readonly" value="${param.fromDate}">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <h4>Ngày đặt - Đến ngày</h4>
                                        <div class="input-group bootstrap-timepicker timepicker col-sm-12">
                                            <span class="input-group-addon">
                                                <i class="glyphicon glyphicon-time"></i>
                                            </span>
                                            <input id="todate" type="text" class="form-control input-small" name="toDate" readonly="readonly" value="${param.toDate}">
                                        </div>
                                    </div>
                                    <div class="col-md-4" style="margin-top: 2%;">
                                        <input type="submit" class="btn btn-primary btn-login ClickMe" value="Tìm" />
                                    </div>
                                </form>
                            </div>


                            <div class="table-responsive data-table" style="padding-top: 20px;">
                                <table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="example">
                                    <colgroup>
                                        <col style="width: 2%;" />
                                        <col style="width: 3%;" />
                                        <col style="width: 15%;" />
                                        <col style="width: 15%;" />
                                        <col style="width: 15%;" />
                                        <col style="width: 15%;" />
                                        <col style="width: 15%" />
                                        <col style="width: 5%" />
                                        <col style="width: 5%;" />
                                        <col style="width: 5%;" />
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th>STT.</th>
                                            <th>ID</th>
                                            <th>Bãi xe</th>
                                            <th>Giờ đặt chỗ</th>
                                            <th>Giờ hết hạn</th>
                                            <th>Giờ vào</th>
                                            <th>Giờ ra</th>
                                            <th>Trạng thái</th>
                                            <th style="text-align: center;">Huỷ</th>
                                            <th style="text-align: center;">Xem đường đi</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="booking" items="${requestScope.LISTBOOKING}" varStatus="counter">
                                            <tr>
                                                <td style="padding-top: 25px;">${counter.index + 1}</td>
                                                <td style="padding-top: 25px;">${booking.bookingID}</td>
                                                <td style="padding-top: 25px;">${booking.parkName}</td>
                                                <td style="padding-top: 25px;">${booking.bookTime}</td>
                                                <td style="padding-top: 25px;">${booking.expiredTime}</td>
                                                <td style="padding-top: 25px;">${booking.checkinTime}</td>
                                                <td style="padding-top: 25px;">${booking.checkoutTime}</td>

                                                <c:if test="${booking.status == 1}">
                                                    <td style="padding-top: 10px; text-align: center;">
                                                        <i class="fab fa-bimobject" style="font-size: 35px"></i>
                                                    </td>
                                                </c:if>
                                                <c:if test="${booking.status == 2}">
                                                    <td style="padding-top: 10px; text-align: center;">
                                                        <i class="fas fa-spinner" style="font-size: 35px"></i>
                                                    </td>
                                                </c:if>
                                                <c:if test="${booking.status == 3}">
                                                    <td style="padding-top: 10px; text-align: center;">
                                                        <i class="fas fa-check-square" style="font-size: 35px"></i>
                                                    </td>
                                                </c:if>
                                                <c:if test="${booking.status == 4}">
                                                    <td style="padding-top: 10px; text-align: center;">
                                                        <i class="far fa-times-circle" style="font-size: 35px"></i>
                                                    </td>
                                                </c:if>
                                                <c:if test="${booking.status == 5}">
                                                    <td style="padding-top: 10px; text-align: center;">
                                                        <i class="fas fa-hourglass-end" style="font-size: 35px"></i>
                                                    </td>
                                                </c:if>


                                                <c:if test="${booking.status != 1}">
                                                    <td style="text-align:center; padding-top: 15px;"></td>
                                                    <td style="text-align:center; padding-top: 15px;"></td>
                                                </c:if>

                                                <c:if test="${booking.status == 1}">

                                                    <td style="text-align:center; padding-top: 15px;">
                                                        <button class="btn btn-primary" type="button" onclick="checkCancelBooking('${booking.bookingID}', '${sessionScope.USERDTO.phone}', '${booking.parkID}')">
                                                            <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                                        </button>
                                                    </td>

                                                    <td style="text-align:center; padding-top: 15px;">
                                                        <button class="btn btn-primary" type="button" onclick="getDirection('${booking.bookingID}', '${booking.parkID}')" id="directon"
                                                            style="display: none">
                                                            <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                                        </button>
                                                    </td>

                                                </c:if>

                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>



                <div id="myModal" class="modal" style="display: none">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status" id="statusbookingpopup">Xem đường đi</h2>
                        </div>
                        <div class="modal-body" id="bodybookingpopup">
                            <h3 style="font-family: monospace; color: black; display: none" id="messagebooking"></h3>
                            <div id="map" style="display: none"></div>
                        </div>
                        <div class="modal-footer" class="col-md-12">
                            <div class="col-md-10" id="buttonbookingpopup">
                            </div>
                            <div class="col-md-2">
                                <input style="width: 80px" type="button" class="btn btn-default" value="Tắt" id="input-close" />
                            </div>
                        </div>
                    </div>
                </div>
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
            <script src="assets/javascripts/fontawesome-all.js"></script>

            </html>