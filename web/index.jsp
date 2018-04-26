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
        <!-- Web Fonts  -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">

        <!-- Vendor CSS -->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />

        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/magnific-popup/magnific-popup.css" />

        <!-- Specific Page Vendor CSS -->
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.theme.css" />
        <link rel="stylesheet" href="assets/vendor/morris.js/morris.css" />

        <!-- Theme CSS -->
        <link rel="stylesheet" href="assets/stylesheets/theme.css" />

        <!-- Skin CSS -->
        <link rel="stylesheet" href="assets/stylesheets/skins/default.css" />

        <!-- Theme Custom CSS -->
        <link rel="stylesheet" href="assets/stylesheets/theme-custom.css">

        <!-- Head Libs -->
        <script src="assets/vendor/modernizr/modernizr.js"></script>

        <!-- MySelf -->
        <link rel="stylesheet" href="assets/stylesheets/themes-bonus.css" />
        <link href="assets/stylesheets/loadFrame.css" rel="stylesheet" type="text/css" />

        <script>
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
                if ('${sessionScope.USERDTO.role}' === '1') {
                    xhttp.open("POST", "LogoutLockedUserAjax?phone=" + phone, true);
                } else {
                    xhttp.open("POST", "LogoutLockedParkAjax?phone=" + phone, true);
                }

                xhttp.send();
            }

            window.setInterval(function () {
                autocheckActiveUser('${sessionScope.USERDTO.phone}');
            }, 1000 * 60 * 3);
        </script>

    </head>

    <body>
        <c:set var="user" value="${sessionScope.USERDTO}" />
        <section class="body">

            <!-- start: header -->
            <header class="header">
                <div class="logo-container">
                    <a class="logo">
                        <img src="assets/images/logo.png" width="200" alt="Porto Admin" />
                    </a>
                    <div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
                        <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
                    </div>
                </div>

                <!-- start: search & user box -->
                <div class="header-right">
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

                                <c:if test="${user.role != 1}">
                                    <c:url var="url" value="GetListWorkingTimeForEmployee">
                                        <c:param name="parkIDStaff" value="${user.parkID}" />
                                        <c:param name="srcPage" value="userdetails.jsp" />
                                    </c:url>
                                    <li>
                                        <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('${url}')" />
                                        <i class="fa fa-user" style="margin-right: 5px;"></i>Xem hồ sơ</a>
                                    </li>
                                </c:if>
                                <c:if test="${user.role == 1}">
                                    <li>
                                        <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('userdetails.jsp')">
                                            <i class="fa fa-user" style="margin-right: 5px;"></i>Xem hồ sơ</a>
                                    </li>
                                </c:if>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="#" onclick="loadIframe('changepass.jsp?phone=${user.phone}')" />
                                    <i class="fas fa-exchange-alt" style="margin-right: 5px;"></i>Đổi mật khẩu</a>
                                </li>
                                <li>
                                    <a role="menuitem" tabindex="-1" href="logout">
                                        <i class="fa fa-power-off" style="margin-right: 2px;"></i> Đăng xuất</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- end: search & user box -->
            </header>
            <!-- end: header -->

            <div class="inner-wrapper">
                <!-- start: sidebar -->
                <aside id="sidebar-left" class="sidebar-left">

                    <div class="sidebar-header">
                        <div class="sidebar-title">
                            DANH MỤC
                        </div>
                        <div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
                            <i class="fa fa-bars" aria-label="Toggle sidebar"></i>
                        </div>
                    </div>

                    <div class="nano">
                        <div class="nano-content">
                            <nav id="menu" class="nav-main" role="navigation">
                                <ul class="nav nav-main">


                                    <c:if test="${user.role == 1}">

                                        <li class="nav-parent">
                                            <a>
                                                <i class="fa fa-users" aria-hidden="true"></i>
                                                <span>Quản lý người dùng</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('adduser.jsp')">
                                                        <icon class="fa fa-user-plus" style="margin-right: 5px;"></icon>Thêm người dùng
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListUser')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Danh sách người dùng
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="nav-parent">
                                            <a>
                                                <i class="fa fa-car" aria-hidden="true" style="margin-right: 4px;"></i>
                                                <span>Quản lí bãi xe</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('addpark.jsp')">
                                                        <icon class="fa fa-plus" style="margin-right: 5px;"></icon>Thêm bãi xe
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListPark')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Danh sách bãi xe
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListWorkingSchedule?srcPage=workingtime.jsp')">
                                                        <icon class="fas fa-edit" style="margin-right: 5px;"></icon>Ca làm việc
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="nav-parent">
                                            <a>
                                                <i class="fas fa-user-circle" aria-hidden="true" style="margin-right: 4px;"></i>
                                                <span>Quản lí nhân sự</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListParkForEmployee?srcPage=addemployee.jsp')">
                                                        <icon class="fa fa-user-plus" style="margin-right: 5px;"></icon>Thêm nhân viên
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListEmployee?srcPage=listemployee.jsp')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Danh sách nhân viên
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fas fa-bookmark" aria-hidden="true" style="margin-right: 7px;"></i>
                                                <span>Quản lí đặt chỗ</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListBooking')">
                                                        <icon class="fas fa-history" style="margin-right: 5px;"></icon>Lịch sử đặt chỗ
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fas fa-money-bill-alt" aria-hidden="true" style="margin-right: 0px;"></i>
                                                <span>Quản lí hóa đơn</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListBills')">
                                                        <icon class="fas fa-list-ol" style="margin-right: 5px;"></icon>Lịch sử thanh toán
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                    </c:if>

                                    <c:if test="${user.role == 3}">
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fas fa-user-circle" aria-hidden="true"></i>
                                                <span>Quản lí nhân sự</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListParkForEmployee?srcPage=addemployee.jsp')">
                                                        <icon class="fa fa-user-plus" style="margin-right: 5px;"></icon>Thêm nhân viên
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListEmployee?srcPage=liststaff.jsp&parkID=${user.parkID}')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Danh sách nhân viên
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fa fa-car" aria-hidden="true" style="margin-right: 4px;"></i>
                                                <span>Quản lí bãi xe</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetDetailsPark?parkID=${user.parkID}')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Cập nhật bãi xe
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListWorkingSchedule?srcPage=workingtime.jsp&parkID=${USERDTO.parkID}')">
                                                        <icon class="fas fa-edit" style="margin-right: 5px;"></icon>Ca làm việc
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fa fa-car" aria-hidden="true"></i>
                                                <span>Checkin - Checkout</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('checkin.jsp')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Checkin
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('checkout.jsp')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Checkout
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fa fa-users" aria-hidden="true"></i>
                                                <span>Quản lý đặt chỗ</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListBooking?parkID=${sessionScope.USERDTO.parkID}')">
                                                        <icon class="fas fa-history" style="margin-right: 5px;"></icon>Lịch sử đặt chỗ
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fas fa-money-bill-alt" aria-hidden="true"></i>
                                                <span>Quản lí hóa đơn</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('GetListBills?parkID=${sessionScope.USERDTO.parkID}')">
                                                        <icon class="fas fa-list-ol" style="margin-right: 5px;"></icon>Lịch sử thanh toán
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                    </c:if>

                                    <c:if test="${user.role == 4}">
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fa fa-car" aria-hidden="true"></i>
                                                <span>Checkin - Checkout</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('checkin.jsp')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Checkin
                                                    </a>
                                                </li>
                                            </ul>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <a href="#" onclick="loadIframe('checkout.jsp')">
                                                        <icon class="fa fa-th-list" style="margin-right: 5px;"></icon>Checkout
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>
                                    </c:if>
                                    <c:if test="${user.role == 3 || user.role == 1}">
                                        <li class="nav-parent">
                                            <a>
                                                <i class="fa fa-archive" aria-hidden="true" style="margin-right: 4px;"></i>
                                                <span>Feedback</span>
                                            </a>
                                            <ul class="nav nav-children">
                                                <li>
                                                    <c:url var="feedbackURL" value="GetListFeedback">
                                                        <c:if test="${user.role == 3}">
                                                            <c:param name="parkID" value="${user.parkID}" />
                                                        </c:if>
                                                    </c:url>
                                                    <a href="#" onclick="loadIframe('${feedbackURL}')">

                                                        <icon class="fas fa-list-ol" style="margin-right: 5px;"></icon>Danh sách Feedback
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>

                                    </c:if>
                                </ul>
                            </nav>

                            <hr class="separator" />

                        </div>

                </aside>

                <section role="main" class="content-body">

                    <header class="page-header">
                        <h2>HỆ THỐNG QUẢN LÝ BÃI XE - ĐẶT XE TRỰC TUYẾN</h2>
                    </header>

                    <div class="over-lap"> </div>
                    <iframe id="loadSite" style="width: 105%; height: 100vh;" >

                    </iframe>


            </div>
        </section>


        <!-- Loading Page -->
        <script src="assets/javascripts/loadPage.js"></script>
        <!-- Vendor -->
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/vendor/nanoscroller/nanoscroller.js"></script>

        <!-- Specific Page Vendor -->
        <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>

        <!-- Theme Base, Components and Settings -->
        <script src="assets/javascripts/theme.js"></script>

        <!-- Theme Custom -->
        <script src="assets/javascripts/theme.custom.js"></script>

        <!-- Theme Initialization Files -->
        <script src="assets/javascripts/theme.init.js"></script>
        <script src="assets/javascripts/fontawesome-all.js"></script>
        <script src="assets/javascripts/userbox.js" type="text/javascript"></script>
        <!-- Examples -->
        <!-- <script src="assets/javascripts/dashboard/examples.dashboard.js"></script> -->
    </body>

</html>