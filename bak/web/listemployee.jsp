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
            <link rel="stylesheet" href="assets/stylesheets/pagination.css">
            <link rel="stylesheet" href="assets/stylesheets/liststaff.css">
        </head>

        <body>
            <div id="containner" class="col-md-12">
                <div class="row clearfix">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                        <div class="card">
                            <h1 style="margin-left: 10px; color: #0074ad">DANH SÁCH NHÂN VIÊN</h1>
                            <div class="body">
                                <div class="row clearfix">
                                    <form action="GetListEmployee" method="POST">
                                        <input type="hidden" name="srcPage" value="listemployee.jsp" />

                                        <div class="col-md-6" style="padding-top: 20px;">
                                            <div class="col-md-3" style="font-size: 20px">Bãi xe</div>
                                            <div class="col-md-6" style="font-size: 20px">
                                                <select class="form-control" name="parkID" onchange="this.form.submit()">

                                                    <c:if test="${sessionScope.SELECTEDPARK == null}">
                                                        <option value="" selected="selected">Tất cả</option>
                                                        <c:forEach items="${requestScope.LISTPARK}" var="dto">
                                                            <option value="${dto.parkID}">${dto.parkName}</option>
                                                        </c:forEach>
                                                    </c:if>

                                                    <c:if test="${sessionScope.SELECTEDPARK != null}">
                                                        <option value="">Tất cả</option>
                                                        <c:forEach items="${requestScope.LISTPARK}" var="dto">
                                                            <c:if test="${dto.parkID.equals(sessionScope.SELECTEDPARK)}">
                                                                <option value="${dto.parkID}" selected="selected">${dto.parkName}</option>
                                                            </c:if>
                                                            <c:if test="${!dto.parkID.equals(sessionScope.SELECTEDPARK)}">
                                                                <option value="${dto.parkID}">${dto.parkName}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>

                                                </select>
                                            </div>
                                        </div>
                                    </form>
                                    <form action="searchEmployee">
                                        <div class="col-sm-6" style="padding-top: 5px;">
                                            <!-- search form -->
                                            <input type="hidden" name="parkID" value="${sessionScope.SELECTEDPARK}" />
                                            <input type="hidden" name="srcPage" value="listemployee.jsp" />
                                            <div class="col-md-10" style="padding-bottom:  10px;">
                                                <input type="text" class="form-control" name="searchValue" value="${param.searchValue}" placeholder="Tên hoặc SĐT" />
                                            </div>
                                            <div class="col-md-2">
                                                <input type="submit" class="btn btn-primary" value="TÌM" name="btnAction" />
                                            </div>

                                        </div>
                                    </form>
                                </div>

                            </div>
                            <c:if test="${requestScope.LISTEMPTY == null}">
                                <div class="table-responsive" style="padding-top: 20px;">
                                    <table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="example">
                                        <colgroup>
                                            <col style="width: 5%;" />
                                            <col style="width: 10%;" />
                                            <col style="width: 20%;" />
                                            <col style="width: 12%;" />
                                            <col style="width: 20%;" />
                                            <col style="width: 13%;" />
                                            <col style="width: 10%;" />
                                            <col style="width: 10%;" />
                                        </colgroup>
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>SĐT</th>
                                                <th>HỌ TÊN</th>
                                                <th>CHỨC DANH</th>
                                                <th>BÃI XE</th>
                                                <th>TRẠNG THÁI</th>
                                                <th style="text-align: center;">CHI TIẾT</th>
                                                <th style="text-align: center;">ĐỔI</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="emp" items="${requestScope.LISTEMPLOYEE}" varStatus="counter">
                                                <tr>
                                                    <td style="padding-top: 25px;">${counter.index + 1}</td>
                                                    <td style="padding-top: 25px;">${emp.phone}</td>
                                                    <td style="padding-top: 25px;">${emp.fullName}</td>
                                                    <c:if test="${emp.roleName.equals('Manager')}">
                                                        <td style="padding-top: 25px;">Quản lý</td>
                                                    </c:if>
                                                    <c:if test="${!emp.roleName.equals('Manager')}">
                                                        <td style="padding-top: 25px;">Nhân viên</td>
                                                    </c:if>
                                                    <td style="padding-top: 25px;">${emp.parkName}</td>
                                                    <c:if test="${emp.active == true}">
                                                        <td style="padding-top: 25px;">Hoạt động</td>
                                                    </c:if>
                                                    <c:if test="${emp.active == false}">
                                                        <td style="padding-top: 25px;">Đã khoá</td>
                                                    </c:if>

                                                    <c:if test="${emp.phone.equals(sessionScope.USERDTO.phone)}">
                                                        <td style="text-align:center; padding-top: 15px;"></td>
                                                        <td style="text-align:center; padding-top: 15px;"></td>
                                                    </c:if>

                                                    <c:if test="${!emp.phone.equals(sessionScope.USERDTO.phone)}">
                                                        <td style="text-align:center; padding-top: 15px;">
                                                            <form action="getDetailsUser" method="POST">
                                                                <input type="hidden" name="phone" value="${emp.phone}" />
                                                                <input type="hidden" name="fullname" value="${emp.fullName}" />
                                                                <input type="hidden" name="email" value="${emp.email}" />
                                                                <input type="hidden" name="address" value="${emp.address}" />
                                                                <input type="hidden" name="dob" value="${emp.dob}" />
                                                                <input type="hidden" name="sex" value="${emp.sex}" />
                                                                <input type="hidden" name="parkID" value="${sessionScope.SELECTEDPARK}" />
                                                                <input type="hidden" name="parkIDStaff" value="${emp.parkID}" />
                                                                <input type="hidden" name="parkName" value="${emp.parkName}" />
                                                                <input type="hidden" name="timeStart" value="${emp.timeStart}" />
                                                                <input type="hidden" name="timeEnd" value="${emp.timeEnd}" />
                                                                <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                                <input type="hidden" name="srcPage" value="listemployee.jsp" />
                                                                <input type="hidden" name="fromPage" value="listemployee.jsp" />

                                                                <button class="btn btn-primary" type="submit">
                                                                    <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                                                </button>
                                                            </form>
                                                        </td>


                                                        <td style="text-align:center; padding-top: 15px;">
                                                            <c:if test="${emp.active == false}">
                                                                <button class="btn btn-danger btn-lg" type="submit" data-toggle="modal" data-target="#myModal" id="${emp.phone}" value="${emp.active}"
                                                                    onclick="setPopupValue(this)">
                                                                    <i class="fa fa-unlock" style="height: 18px; width: 18px; padding-top: 2px;"></i>
                                                                </button>
                                                            </c:if>
                                                            <c:if test="${emp.active == true}">
                                                                <button class="btn btn-danger btn-lg" type="submit" data-toggle="modal" data-target="#myModal" id="${emp.phone}" value="${emp.active}"
                                                                    onclick="setPopupValue(this)">
                                                                    <i class="fa fa-lock" style="height: 18px; width: 18px; padding-top: 2px;"></i>
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
                                                                        <div class="modal-body">
                                                                            <p>Bạn có muốn mở khoá/khoá tài khoản này ?</p>
                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <div class="col-md-12">
                                                                                <div class="col-md-10">
                                                                                    <form action="changeActiveEmployee">
                                                                                        <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                                                        <input type="hidden" name="parkID" value="${sessionScope.SELECTEDPARK}" />
                                                                                        <input type="hidden" name="srcPage" value="listemployee.jsp" />
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
                                <h3 style="font-family: monospace; color: green">${requestScope.LISTEMPTY}</h3>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Js -->
            <script src="assets/vendor/jquery/jquery.js"></script>
            <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
            <script src="assets/javascripts/popup.js"></script>
            <script>
                $(document).ready(function () {
                    $('#example').DataTable({
                        "pageLength": 10
                    });


                    $('.dataTables_paginate').on('click', function () {
                        $('html,body').animate({
                            scrollTop: 0
                        }, 0,
                        );
                    });
                });
            </script>
            <script src="assets/javascripts/datatable.js"></script>

        </body>

        </html>