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
        <div id="containner" class="col-md-12">
            <div class="row clearfix">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="card">
                        <h1 style="margin-left: 10px; color: #0074ad">LIST PARKS</h1>
                        <div class="body">
                            <div class="row clearfix">
                                <div class="col-sm-6" style="padding-top: 20px; float: left;">
                                    <div class="col-md-2" style="font-size: 20px">Park</div> 
                                    <div class="col-md-6" style="padding-bottom:  10px;">
                                        <input type="text" class="form-control" value="${sessionScope.USERDTO.parkName}" readonly="readonly"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="table-responsive" style="padding-top: 20px;">
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                                <colgroup>
                                    <col style="width: 5%;" />
                                    <col style="width: 8%;" />
                                    <col style="width: 30%;" />
                                    <col style="width: 15%;" />
                                    <col style="width: 15%" />
                                    <col style="width: 4%;" />
                                    <col style="width: 4%;" />
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>ID</th>
                                        <th>Park ID</th>
                                        <th>Time start</th>
                                        <th>Time end</th>
                                        <th style="text-align: center;">Detail</th>
                                        <th style="text-align: center;">Remove</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="wkt" items="${requestScope.LISTWORKINGTIME}" varStatus="counter">
                                        <tr>
                                            <td style="padding-top: 25px;">${counter.index + 1}</td>
                                            <td style="padding-top: 25px;">${wkt.ID}</td>
                                            <td style="padding-top: 25px;">${wkt.parkID}</td>
                                            <td style="padding-top: 25px;">${wkt.timeStart}</td>
                                            <td style="padding-top: 25px;">${wkt.timeEnd}</td>
                                            <td style="text-align:center; padding-top: 15px;">
                                                <form action="getDetailsPark" method="POST">
                                                    <input type="hidden" name="parkID" value="${park.parkID}" />
                                                    <input type="hidden" name="parkSpace" value="${park.parkSpace}" />
                                                    <input type="hidden" name="parkName" value="${park.parkName}" />
                                                    <input type="hidden" name="parkAddress" value="${park.parkAddress}" />
                                                    <input type="hidden" name="latitude" value="${park.latitude}" />
                                                    <input type="hidden" name="longtitude" value="${park.longtitude}" />
                                                    <input type="hidden" name="costPerHour" value="${park.costPerHour}" />
                                                    <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                    <button class="btn btn-primary" type="submit">
                                                        <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                                    </button>
                                                </form>
                                            </td>

                                            <td style="text-align:center; padding-top: 15px;">
                                                <button class="btn btn-danger btn-lg" type="submit" data-toggle="modal" data-target="#myModal" id="${park.parkID}" value="${park.active}" onclick="setPopupValue(this)">
                                                    <i class="fa fa-lock" style="height: 18px; padding-top: 2px;"></i>
                                                </button>
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
                                                                <p>This action will lock/unlock this park activities and employees. Do you want to keep doing it ?</p>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <div class="col-md-12">
                                                                    <div class="col-md-10">
                                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                                    </div>
                                                                    <div class="col-md-2">
                                                                        <form action="changeActivePark">
                                                                            <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                                                            <input id="text-area" type="hidden" value="" name="parkID"/>
                                                                            <input id="text-area2" type="hidden" value="" name="active"/>
                                                                            <button type="submit" class="btn btn-danger">Lock</button>
                                                                        </form>
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