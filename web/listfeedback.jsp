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
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/list.css">
        <link rel="stylesheet" href="assets/stylesheets/pagination.css">
        <link rel="stylesheet" href="assets/stylesheets/listfeedback.css">

    </head>

    <body>
        <c:set var="user" value="${sessionScope.USERDTO}" />
        <div id="containner" style="margin-top: 40px;">
            <h1 style="margin-left: 10px; color: #0074ad">DANH SÁCH FEEDBACK</h1>
            <div class="body">
                <div class="row clearfix">
                    <div class="col-sm-12" style="padding-top: 20px; float: left;">
                        <form action="searchFeedback">
                            <!-- search form -->
                            <c:if test="${user.role == 3}">
                                <input type="hidden" name="parkID" value="${user.parkID}" />
                            </c:if>
                            <div class="col-md-5">
                                <div class="col-md-3">
                                    <h4>Từ Ngày</h4>
                                </div>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-8">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="fromdate" type="text" class="form-control input-small" name="fromDate" readonly="readonly" value="${param.fromDate}">
                                </div>
                            </div>
                            <div class="col-md-5">
                                <div class="col-md-4">
                                    <h4>Đến Ngày</h4>
                                </div>
                                <div class="input-group bootstrap-timepicker timepicker col-sm-8">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-time"></i>
                                    </span>
                                    <input id="todate" type="text" class="form-control input-small" name="toDate" readonly="readonly" value="${param.toDate}">
                                </div>
                            </div>
                            <div class="col-md-2">
                                <input type="submit" class="btn btn-primary btn-login" value="TÌM" style="width: 130px; height: 35px;" />
                            </div>
                        </form>
                    </div>
                </div>

                <c:if test="${requestScope.SUCCESS != null && requestScope.ERROR == null}">
                    <h4 style="margin: 2%;font-family: monospace; color: green">${requestScope.SUCCESS}</h4>
                </c:if>

                <c:if test="${requestScope.SUCCESS == null && requestScope.ERROR != null}">
                    <h4 style="margin: 2%;font-family: monospace; color: red">${requestScope.ERROR}</h4>
                </c:if>                 
            </div>
            <c:if test="${requestScope.LISTEMPTY == null}">
                <div class="table-responsive" style="padding-top: 20px;">
                    <table class="table table-bordered table-striped table-hover js-basic-example dataTable" id="example">
                        <colgroup>
                            <col style="width: 5%;" />
                            <col style="width: 15%;" />
                            <col style="width: 30%;" />
                            <col style="width: 20%;" />
                            <col style="width: 10%;" />
                            <col style="width: 10%;" />
                            <col style="width: 10%;" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>SỐ ĐIỆN THOẠI</th>
                                <th>TIÊU ĐỀ</th>
                                <th>NGÀY GỬI</th>
                                <th>HỒI ĐÁP</th>
                                <th>NỘI DUNG</th>
                                <th>XOÁ</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="fb" items="${requestScope.LISTFEEDBACK}" varStatus="counter">
                                <tr>
                                    <td style="padding-top: 25px;">${counter.index + 1}</td>
                                    <td style="padding-top: 25px;">${fb.phone}</td>
                                    <td style="padding-top: 25px;">${fb.title}</td>
                                    <td style="padding-top: 25px;">${fb.feedbackTime}</td>
                                    <c:if test="${fb.repMessage != null}" >
                                        <td style="padding-top: 1.5%; text-align: center;">
                                            <div style="display: none">0</div>
                                            <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal" id="${fb.repMessage}" onclick="setPopupDetailsFeedback(this)">
                                                <i class="fas fa-check" style="height: 25px; padding-top: 2px;"></i>
                                            </button>
                                        </td>
                                    </c:if>
                                    <c:if test="${fb.repMessage == null}" >
                                        <td style="padding-top: 1.5%; text-align: center;">
                                            <div style="display: none">1</div>
                                            <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal" id="${fb.id}" onclick="setPopupValueRepFeedback(this)">
                                                <i class="fas fa-edit" style="height: 25px; padding-top: 2px;"></i>
                                            </button>
                                        </td>
                                    </c:if>
                                    <td style="text-align:center; padding-top: 1.5%;">
                                        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal" id="${fb.message}" onclick="setPopupDetailsFeedback(this)">
                                            <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 2px;"></i>
                                        </button>
                                    </td>
                                    <td style="text-align:center; padding-top: 1.5%;">
                                        <button class="btn btn-danger btn-lg" type="button" data-toggle="modal" data-target="#myModal" id="${fb.id}" value="Bạn có muốn xoá feedback này ?"
                                                onclick="setPopupValueFeedback(this)">
                                            <i class="fa fa-trash" style="height: 20px; padding-top: 2px;"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>

            <c:if test="${requestScope.LISTEMPTY != null}">
                <h3 style="margin: 2%; font-family: monospace; color: green">${requestScope.LISTEMPTY}</h3>
            </c:if>
        </div>


        <!--popup -->
        <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                        <h4 class="modal-title" id="popuptitle"></h4>
                    </div>
                    <div class="modal-body">
                        <p id="text-area2"></p>
                        <textarea id="text-area3" style="display: none; width: 100%; height: 30%"></textarea>
                    </div>
                    <div class="modal-footer">
                        <div class="col-md-12">

                            <div class="col-md-10" id="addrep-button">
                                <form action="replyFeedback" id="replyFeedback" onsubmit="setRepMess()">
                                    <input type="hidden" name="fromDate" value="${param.fromDate}" />
                                    <input type="hidden" name="toDate" value="${param.toDate}" />
                                    <input id="text-areaReply" type="hidden" name="feedbackID" value="" />
                                    <input id="mess" type="hidden" name="mess" value="" />
                                    <button type="submit" class="btn btn-danger">GỬI</button>
                                </form>
                            </div>

                            <div class="col-md-10" id="remove-button">
                                <form action="deleteFeedback">
                                    <input type="hidden" name="fromDate" value="${param.fromDate}" />
                                    <input type="hidden" name="toDate" value="${param.toDate}" />
                                    <input id="text-area" type="hidden" name="feedbackID" value="" />
                                    <button type="submit" class="btn btn-danger">XOÁ</button>
                                </form>
                            </div>

                            <div class="col-md-2" id="close-button">
                                <button type="button" class="btn btn-default" data-dismiss="modal">THOÁT</button>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- Js -->
        <script>
            function setRepMess() {
                document.getElementById("mess").value = document.getElementById("text-area3").value;
            }
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

        <script src="assets/javascripts/datatable.js"></script>
        <script src="assets/javascripts/adminpaging.js"></script>
        <script src="assets/javascripts/fontawesome-all.js"></script>
    </body>

</html>