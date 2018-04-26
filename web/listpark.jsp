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
            <link rel="stylesheet" href="assets/stylesheets/listpark.css">
        </head>

        <body>
            <div id="containner" style="margin-top: 40px;">

                <h1 style="margin-left: 10px; color: #0074ad">DANH SÁCH BÃI XE</h1>
                <div class="body">
                    <div class="row clearfix">
                        <div class="col-sm-6" style="padding-top: 20px; float: left;">
                            <form action="searchPark">
                                <!-- search form -->
                                <div class="col-md-10" style="padding-bottom:  10px;">
                                    <input type="text" class="form-control" name="searchValue" value="${param.searchValue}" placeholder="Tên hoặc địa chỉ" />
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
                                <col style="width: 5%;" />
                                <col style="width: 10%;" />
                                <col style="width: 30%;" />
                                <col style="width: 35%" />
                                <col style="width: 10%;" />
                                <col style="width: 5%;" />
                                <col style="width: 5%;" />
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>ID</th>
                                    <th>TÊN</th>
                                    <th>ĐỊA CHỈ</th>
                                    <th>CHI TIẾT</th>
                                    <th>TRẠNG THÁI</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="park" items="${requestScope.LISTPARK}" varStatus="counter">
                                    <tr>
                                        <td style="padding-top: 25px;">${counter.index + 1}</td>
                                        <td style="padding-top: 25px;">${park.parkID}</td>
                                        <td style="padding-top: 25px;">${park.parkName}</td>
                                        <td style="padding-top: 25px;">${park.parkAddress}</td>

                                        <td style="text-align:center; padding-top: 15px;">
                                            <form action="getDetailsPark" method="POST">
                                                <input type="hidden" name="parkID" value="${park.parkID}" />
                                                <input type="hidden" name="slot" value="${park.slot}" />
                                                <input type="hidden" name="parkName" value="${park.parkName}" />
                                                <input type="hidden" name="parkAddress" value="${park.parkAddress}" />
                                                <input type="hidden" name="lat" value="${park.lat}" />
                                                <input type="hidden" name="lng" value="${park.lng}" />
                                                <input type="hidden" name="price" value="${park.price}" />
                                                <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                <button class="btn btn-primary" type="submit">
                                                    <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                                </button>
                                            </form>
                                        </td>

                                        <td style="text-align:center; padding-top: 15px;">
                                            <c:if test="${park.active == false}">
                                                <button class="btn btn-danger btn-lg" type="submit" data-toggle="modal" data-target="#myModal" id="${park.parkID}" value="${park.active}"
                                                    onclick="setPopupValuePark(this)">
                                                    <i class="fa fa-unlock" style="height: 18px; padding-top: 2px;"></i>
                                                </button>
                                            </c:if>

                                            <c:if test="${park.active == true}">
                                                <button class="btn btn-danger btn-lg" type="submit" data-toggle="modal" data-target="#myModal" id="${park.parkID}" value="${park.active}"
                                                    onclick="setPopupValuePark(this)">
                                                    <i class="fa fa-lock" style="height: 18px; padding-top: 2px;"></i>
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
                                                                    <form action="changeActivePark">
                                                                        <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                                        <input id="text-area" type="hidden" value="" name="parkID" />
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


            <!-- Js -->
            <script src="assets/vendor/jquery/jquery.js"></script>
            <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
            <script src="assets/javascripts/popup.js"></script>
            <script src="assets/javascripts/datatable.js"></script>
            <script src="assets/javascripts/adminpaging.js">
            </script>

        </body>

        </html>