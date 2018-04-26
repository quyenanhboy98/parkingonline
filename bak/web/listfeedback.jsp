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
    </head>

    <body>
        <c:set var="user" value="${sessionScope.USERDTO}"/>
        <div id="containner" class="col-md-12">
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <h1 style="margin-left: 10px; color: #0074ad">DANH SÁCH FEEDBACK</h1>
                        <div class="body">
                            <div class="row clearfix">
                                <div class="col-sm-6" style="padding-top: 20px; float: right;">
                                    <form action="searchFeedback">
                                        <!-- search form -->
                                        <c:if test="${user.role == 3}">
                                            <input type="hidden" name="parkID" value="${user.parkID}" />
                                        </c:if>
                                        <div class="col-md-10" style="padding-bottom:  10px;">
                                            <input type="text" class="form-control" name="searchValue" value="${param.searchValue}" />
                                        </div>
                                        <div class="col-md-2" >
                                            <input type="submit" class="btn btn-primary" value="Search" />
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive" style="padding-top: 20px;">
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                                <colgroup>
                                    <col style="width: 5%;" />
                                    <col style="width: 8%;" />
                                    <col style="width: 30%;" />
                                    <col style="width: 35%;" />
                                    <col style="width: 4%" />
                                    <col style="width: 5%" />
                                    <col style="width: 10%" />
                                    <col style="width: 4%;" />
                                    <col style="width: 4%;" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Số điện thoại</th>
                                        <th>Tiêu đề</th>
                                        <th>Ngày gửi</th>
                                        <th style="text-align: center;">Detail</th>
                                        <th style="text-align: center;">Change</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="fb" items="${requestScope.LISTFEEDBACK}" varStatus="counter">
                                        <tr>
                                            <td style="padding-top: 25px;">${fb.id}</td>
                                            <td style="padding-top: 25px;">${fb.phone}</td>
                                            <td style="padding-top: 25px;">${fb.title}</td>
                                            <td style="padding-top: 25px;">${fb.feedbackTime}</td>
                                            <td style="text-align:center; padding-top: 15px;">
                                                <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal" id="${fb.message}" onclick="setPopupDetailsFeedback(this)">
                                                    <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                                </button>
                                            </td>
                                            <td style="text-align:center; padding-top: 15px;">
                                                <button class="btn btn-danger btn-lg" type="button" data-toggle="modal" data-target="#myModal" id="${fb.id}" value="This action will remove this feedback. Do you want to keep doing it?" onclick="setPopupValueFeedback(this)">
                                                    <i class="fa fa-lock" style="height: 18px; padding-top: 2px;"></i>
                                                </button>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--popup -->
        <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title">Please Confirm</h4>
                    </div>
                    <div class="modal-body">
                        <p id="text-area2">This action will remove this feedback. Do you want to keep doing it?</p>
                    </div>
                    <div class="modal-footer">
                        <div class="col-md-12">

                            <div class="col-md-10" id="remove-button">
                                <form action="deleteFeedback">
                                    <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                    <input id="text-area" type="hidden" name="feedbackID" value="${fb.id}" />
                                    <input type="hidden" name="srcPage" value="listemployee.jsp" />
                                    <button type="submit" class="btn btn-danger">Remove</button>
                                </form>
                            </div>
                            <div class="col-md-2" id="close-button">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Js -->
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/javascripts/popup.js"></script>
    </body>
</html>