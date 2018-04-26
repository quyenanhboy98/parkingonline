<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html class="fixed">

    <head>

        <!-- Basic -->
        <meta charset="UTF-8">

        <title>Parking Online</title>

        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <script src="assets/javascripts/jquery-3.3.1.min.js"></script>
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/theme-user.css" />
        <link href="assets/stylesheets/loadFrame.css" rel="stylesheet" type="text/css"/>
        <script>

            function initialize() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(function (position) {
                        currentPos = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };
                        setCurrentPos(currentPos.lat, currentPos.lng);
                        document.getElementById("findpark").style.display = "block";
                    });
                }

            }

            function setCurrentPos(lat, lng) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                        var rs = xhttp.responseText;
                        if (rs === "Done") {
                            console.log("SetCurrentPos Done");
                        } else {
                            console.log("SetCurrentPos Error");
                        }
                    }

                };
                xhttp.open("POST", "SetCurrentPosAjax?lat=" + lat + "&lng=" + lng, true);
                xhttp.send();
            }

            function autocheckActiveUser(phone) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                        var rs = xhttp.responseText;
                        if (rs === "Locked") {
                            console.log("autocheckActiveUser locked");
                            window.location.replace("login.jsp");
                        } else if (rs === "Error") {
                            window.location.replace("login.jsp");
                            console.log("autocheckActiveUser error");
                        }
                    }

                };
                xhttp.open("POST", "LogoutLockedUserAjax?phone=" + phone, true);
                xhttp.send();
            }

            window.setInterval(function () {
                autocheckActiveUser('${sessionScope.USERDTO.phone}');
                initialize();
            }, 1000 * 60 * 3);
        </script>
    </head>

    <body>

        <c:set var="user" value="${sessionScope.USERDTO}" />
        <section class="body">

            <!-- start: header -->
            <header class="header">
                <div class="logo-container" style="padding-top: 10px;">
                    <a class="logo">
                        <img src="assets/images/logo.png" width="200" alt="Porto Admin" />
                    </a>
                </div>

                <!-- start: search & user box -->
                <div class="header-right" id="profileuser">
                    <div id="userbox" class="userbox">
                        <a href="#" data-toggle="dropdown" class="box">
                            <div class="profile-picture">
                                <img src="assets/images/useravatar.png" alt="" />
                            </div>
                            <div class="profile-info">
                                <span class="name">${user.phone}</span>
                                <!-- Full Name User-->
                                <span class="role">${user.roleName}</span>
                                <!--Role -->
                            </div>


                        </a>

                        <div class="dropdown-menu">
                            <ul class="list-unstyled">
                                <li class="divider"></li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('profileuser.jsp')">
                                        <i class="fa fa-user"></i> Xem hồ sơ</a>
                                </li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('barcode.jsp?code=${user.phone}')">     
                                        <i class="fa fa-barcode"></i> Barcode</a>
                                </li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('CheckCurrentBooking')" style="display: none;" id="findpark">
                                        <i class="fa fa-location-arrow"></i> Tìm bãi xe</a>
                                </li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('GetListBookingOfUser')">
                                        <i class="fa fa-history"></i> Lịch sử đặt chỗ</a>
                                </li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('GetListBillsOfUser')">
                                        <i class="far fa-money-bill-alt"></i> Lịch sử thanh toán</a>
                                </li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('GetListParkForFeedback?srcPage=addfeedback.jsp')">
                                        <i class="fas fa-comments"></i> Gửi phản hồi</a>
                                </li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="logout">
                                        <i class="fa fa-power-off"></i> Đăng xuất</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- end: search & user box -->
            </header>
            <!-- end: header -->

            <div class="box-area">
                <div class="over-lap"> </div>
                    <iframe id="loadSite" src="map.jsp">

                    </iframe>  
               
            </div>
            <!-- end: page -->
        </section>
    </body>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB8nz4AoPyn5FeuOSI1wDd0o0cbmu621jc&callback=initialize">
    </script>
    <!-- JS -->
    <script src="assets/javascripts/loadPage.js"></script>
    <script src="assets/vendor/jquery/jquery.js"></script>
    <script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
    <script src="assets/javascripts/fontawesome-all.js"></script>
    <script src="assets/javascripts/userbox.js" type="text/javascript"></script>

</html>