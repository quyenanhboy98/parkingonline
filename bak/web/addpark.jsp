<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

    <head>
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/stylesheets/customizeCheckbox.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />

    </head>

    <body>
        <div id="containner" class="col-md-12">

            <h1 style="margin-left: 10px; color: #0074ad">ADD NEW PARK</h1>

            <form action="addPark" method="POST">

                <c:set var="error" value="${requestScope.ERROR}" />

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Park ID</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-automobile"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="parkID" value="${param.parkID}"/>
                        </div>
                        <label style="color: red;">${error.parkIDIsEmtpy}</label>
                        <label style="color: red;">${error.parkIDIsWrongFormat}</label>
                    </div>

                    <div class="col-md-3">
                        <h4>Park Space</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-sliders"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="slot" value="${param.slot}"/>
                        </div>
                        <label style="color:#EA2027">${error.parkSpaceIsWrongFormat}</label>
                    </div>

                    <div class="col-md-3">
                        <h4>Cost per hour</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-money"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="price" value="${param.price}"/>
                        </div>
                        <label style="color:#EA2027">${error.parkCostIsWrongFormat}</label>
                    </div>
                </div>

                <div class="col-md-12">

                    <div class="col-md-6">
                        <h4>Park name</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-pencil"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="parkName" value="${param.parkName}" />
                        </div>
                        <label style="color: red;">${error.parkNameIsEmpty}</label>
                        <label style="color: red;">${error.parkNameIsWrongFormat}</label>
                    </div>

                    <div class="col-md-6">
                        <h4>Park Address</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-road"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="parkAddress" value="${param.parkAddress}"/>
                        </div>
                        <label style="color:#EA2027">${error.parkAddressIsEmpty}</label>
                        <label style="color:#EA2027">${error.parkAddressIsWrongFormat}</label>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Latitude</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-map-marker"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="lat" value="${param.lat}" />
                        </div>
                        <label style="color: red;">${error.parkLatitudeIsWrongFormat}</label>
                    </div>

                    <div class="col-md-6">
                        <h4>Longtitude</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-map-marker"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="lng" value="${param.lng}" />
                        </div>
                        <label style="color: red;">${error.parkLongtitudeIsWrongFormat}</label>
                    </div>
                </div>             

                <div class="col-md-2" style="margin-top: 20px">
                    <input style="width: 150px; height: 45px;" type="submit" value="Add park" class="btn btn-primary" />
                </div>
            </form>

            <c:if test="${error.parkIDIsExisted != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Status</h2>
                        </div>
                        <div class="modal-body">
                            <h3 style="font-family: monospace; color: red">${error.parkIDIsExisted}</h3>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="Close" id="input-close"/>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${error.createError != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Status</h2>
                        </div>
                        <div class="modal-body">
                            <h3 style="font-family: monospace; color: red">${error.createError}</h3>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="Close" id="input-close"/>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${error == null && requestScope.SUCCESS != null}" >
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">Status</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: blue">${requestScope.SUCCESS}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="Close" id="input-close"/>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>
        <!--JS-->
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/javascripts/popup.js"></script>
    </body>
</html>