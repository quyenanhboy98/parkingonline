<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
            <!-- Bootstrap -->
            <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
            <!-- Font-Awesome -->
            <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
            <!-- Checkbox -->
            <link rel="stylesheet" href="assets/stylesheets/customizeCheckbox.css" />
            <!-- Css -->
            <link rel="stylesheet" href="assets/stylesheets/list.css">
            <link rel="stylesheet" href="assets/stylesheets/pagination.css">
            <link rel="stylesheet" href="assets/stylesheets/listuser.css">
            <script>
                function setSelectedRole() {
                    var role = "${sessionScope.SELECTEDROLE}";
                    if (role.length === 0) {
                        document.getElementById("roleAll").style.color = "red";
                    } else {
                        document.getElementById("role" + role).style.color = "red";
                        document.getElementById("roleAll").style.color = "";
                    }
                }
            </script>
        </head>

        <body onload="setSelectedRole()">
            <div id="containner" class="col-md-12" style="margin-top: 40px;">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <h1 style="margin-left: 0px; color: #0074ad">DANH SÁCH NGƯỜI DÙNG</h1>
                            <div class="body">
                                <div class="row clearfix">
                                    <div class="col-md-6 row" style="padding-top: 20px;">
                                        <div class="col-md-4" style="font-size: 20px">
                                            <a href="GetListUser" class="tag-a" id="roleAll">Tất Cả</a>
                                        </div>
                                        <div class="col-md-4" style="font-size: 20px">
                                            <a href="GetListUser?selectedRole=User" class="tag-a" id="roleUser">Người Dùng</a>
                                        </div>
                                        <div class="col-md-4" style="font-size: 20px">
                                            <a href="GetListUser?selectedRole=Administrator" class="tag-a" id="roleAdministrator">Quản Trị Viên</a>
                                        </div>
                                    </div>
                                    <div class="col-sm-6" style="padding-top: 20px;">
                                        <form action="searchUser">
                                            <!-- search form -->
                                            <input type="hidden" name="searchRole" value="${sessionScope.SELECTEDROLE}" />
                                            <div class="col-md-10" style="padding-bottom:  10px;">
                                                <input type="text" class="form-control" name="searchValue" value="${param.searchValue}" placeholder="Tên hoặc SĐT" />
                                            </div>
                                            <div class="col-md-2">
                                                <input type="submit" class="btn btn-primary" value="TÌM" name="btnAction" />
                                            </div>
                                        </form>
                                    </div>
                                </div>

                            </div>
                            <c:if test="${requestScope.LISTEMPTY == null}">
                                <div class="table-responsive" style="padding-top: 20px;">
                                    <table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="example">
                                        <colgroup>
                                            <col style="width: 3%;" />
                                            <col style="width: 10%;" />
                                            <col style="width: 15%;" />
                                            <col style="width: 20%;" />
                                            <col style="width: 8%;" />
                                            <col style="width: 10%;" />
                                        </colgroup>
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>SĐT</th>
                                                <th>HỌ TÊN</th>
                                                <th>CHỨC DANH</th>
                                                <th>CHI TIẾT</th>
                                                <th>TRẠNG THÁI</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="user" items="${requestScope.LISTUSER}" varStatus="counter">
                                                <tr>
                                                    <td style="padding-top: 2.5%;">${counter.index + 1}</td>
                                                    <td style="padding-top: 2.5%;">${user.phone}</td>
                                                    <td style="padding-top: 2.5%;">${user.fullName}</td>
                                                    <c:if test="${user.roleName.equals('User')}">
                                                        <td style="padding-top: 2.5%;">Người dùng</td>
                                                    </c:if>
                                                    <c:if test="${!user.roleName.equals('User')}">
                                                        <td style="padding-top: 2.5%;">Quản trị viên</td>
                                                    </c:if>

                                                    <c:if test="${user.phone.equals(sessionScope.USERDTO.phone)}">
                                                        <td style="text-align:center; padding-top: 15px;"></td>
                                                        <td style="text-align:center; padding-top: 15px;"></td>
                                                    </c:if>

                                                    <c:if test="${!user.phone.equals(sessionScope.USERDTO.phone)}">
                                                        <td style="text-align:center; padding-top: 15px;">
                                                            <form action="getDetailsUser" method="POST">
                                                                <input type="hidden" name="phone" value="${user.phone}" />
                                                                <input type="hidden" name="fullname" value="${user.fullName}" />
                                                                <input type="hidden" name="email" value="${user.email}" />
                                                                <input type="hidden" name="address" value="${user.address}" />
                                                                <input type="hidden" name="dob" value="${user.dob}" />
                                                                <input type="hidden" name="sex" value="${user.sex}" />
                                                                <input type="hidden" name="role" value="${user.role}" />
                                                                <input type="hidden" name="point" value="${user.point}" />
                                                                <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                                <input type="hidden" name="srcPage" value="listuser.jsp" />
                                                                <button class="btn btn-primary" type="submit">
                                                                    <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                                                </button>
                                                            </form>
                                                        </td>


                                                        <td style="text-align:center; padding-top: 15px;">
                                                            <c:if test="${user.active == false}">
                                                                <button class="btn btn-danger btn-lg" type="submit" data-toggle="modal" data-target="#myModal" id="${user.phone}" value="${user.active}"
                                                                    onclick="setPopupValueUser(this)">
                                                                    <i class="fa fa-unlock" style="height: 18px;width: 18px ;padding-top: 2px;"></i>
                                                                </button>
                                                            </c:if>

                                                            <c:if test="${user.active == true}">
                                                                <button class="btn btn-danger btn-lg" type="submit" data-toggle="modal" data-target="#myModal" id="${user.phone}" value="${user.active}"
                                                                    onclick="setPopupValueUser(this)">
                                                                    <i class="fa fa-lock" style="height: 18px; width: 18px ;padding-top: 2px;"></i>
                                                                </button>
                                                            </c:if>
                                                            <!--popup -->

                                                            <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
                                                                <div class="modal-dialog" role="document">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                <span aria-hidden="true">&times;</span>
                                                                            </button>
                                                                            <h4 class="modal-title">XÁC NHẬN</h4>
                                                                        </div>
                                                                        <div class="modal-body" id="popupmessage">
                                                                            <p></p>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <div class="col-md-12">
                                                                                <div class="col-md-10">
                                                                                    <form action="changeActiveUser">
                                                                                        <input type="hidden" name="searchRole" value="${sessionScope.SELECTEDROLE}" />
                                                                                        <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                                                        <input id="text-area" type="hidden" value="" name="phone" />
                                                                                        <input id="text-area2" type="hidden" value="" name="active" />
                                                                                        <button type="submit" class="btn btn-danger">ĐỔI</button>
                                                                                    </form>
                                                                                </div>
                                                                                <div class="col-md-2">
                                                                                    <button type="button" class="btn btn-default" data-dismiss="modal">THOÁT</button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>


                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>

                            <c:if test="${requestScope.LISTEMPTY != null}">
                                <h4 style="font-family: monospace; color: green">${requestScope.LISTEMPTY}</h4>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </body>
        <!-- Js -->
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/javascripts/popup.js"></script>
        <script src="assets/javascripts/datatable.js"></script>
        <script src="assets/javascripts/adminpaging.js"></script>

        </html>