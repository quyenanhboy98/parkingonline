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
        <link rel="stylesheet" href="assets/stylesheets/addpark.css">

    </head>

    <body>
        <div id="containner" style="margin-top: 20px;">

            <h1 style="margin-left: 10px; color: #0074ad">THÊM BÃI XE</h1>

            <form action="addPark" method="POST">

                <c:set var="error" value="${requestScope.ERROR}" />
                <div class="col-md-12">

                    <div class="col-md-6 row">
                        <h4>Tên Bãi</h4>
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
                        <h4>Địa Chỉ Bãi</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-road"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" id="parkAddress" name="parkAddress" value="${param.parkAddress}" />
                        </div>
                        <label style="color:#EA2027">${error.parkAddressIsEmpty}</label>
                        <label style="color:#EA2027">${error.parkAddressIsWrongFormat}</label>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-2 row">
                        <h4>ID Bãi Xe</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-automobile"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="parkID" value="${param.parkID}" />
                        </div>
                        <label style="color: red;">${error.parkIDIsEmtpy}</label>
                        <label style="color: red;">${error.parkIDIsWrongFormat}</label>
                    </div>

                    <div class="col-md-2">
                        <h4>Số Chỗ Đậu</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-sliders"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="slot" value="${param.slot}" />
                        </div>
                        <label style="color:#EA2027">${error.parkSpaceIsWrongFormat}</label>
                    </div>

                    <div class="col-md-2">
                        <h4>Phí / Giờ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-money"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="price" value="${param.price}" />
                        </div>
                        <label style="color:#EA2027">${error.parkCostIsWrongFormat}</label>
                    </div>

                    <div class="col-md-3">
                        <h4>Vĩ Độ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-map-marker"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" id="lat" name="lat" value="${param.lat}" readonly="readonly" />
                        </div>
                        <label style="color: red;">${error.parkLatitudeIsWrongFormat}</label>
                    </div>

                    <div class="col-md-3">
                        <h4>Kinh Độ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-map-marker"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" id="lng" name="lng" value="${param.lng}" readonly="readonly" />
                        </div>
                        <label style="color: red;">${error.parkLongtitudeIsWrongFormat}</label>
                    </div>
                </div>

                <div class="col-md-12">
                    <div id="map">

                    </div>
                </div>

                <div class="col-md-6">
                    <div class="col-md-2" style="margin-top: 20px">
                        <input style="width: 150px; height: 45px;" type="submit" value="THÊM BÃI XE" class="btn btn-primary" />
                    </div>
                </div>



            </form>

            <c:if test="${error.parkIDIsExisted != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: red">${error.parkIDIsExisted}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="ĐÓNG" id="input-close" />
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${error.createError != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: red">${error.createError}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="ĐÓNG" id="input-close" />
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${error == null && requestScope.SUCCESS != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: blue">${requestScope.SUCCESS}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="ĐÓNG" id="input-close" onclick="location.href = 'addpark.jsp'"/>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>
        <!--JS-->
        <script>
            var markers = [];

            function clearMarkers() {
                for (var i = 0; i < markers.length; i++) {
                    markers[i].setMap(null);
                }
            }
            function initMap() {
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 10,
                    center: {lat: 10.8230989, lng: 106.6296638}
                });
                var geocoder = new google.maps.Geocoder();

                document.getElementById('parkAddress').addEventListener('change', function () {
                    geocodeAddress(geocoder, map);
                });
            }

            function geocodeAddress(geocoder, resultsMap) {
                clearMarkers();
                markers = [];
                var address = document.getElementById('parkAddress').value;
                if (address.length <= 0) {
                    document.getElementById('lat').value = "";
                    document.getElementById('lng').value = "";
                    return;
                }
                geocoder.geocode({'address': address}, function (results, status) {
                    if (status === 'OK') {
                        resultsMap.setCenter(results[0].geometry.location);
                        var marker = new google.maps.Marker({
                            map: resultsMap,
                            position: results[0].geometry.location
                        });
                        markers.push(marker);

                        document.getElementById('lat').value = results[0].geometry.location.lat();
                        document.getElementById('lng').value = results[0].geometry.location.lng();
                        
                        resultsMap.setZoom(17);
                        resultsMap.panTo(marker.position);
                    } else {
                        document.getElementById('lat').value = "";
                        document.getElementById('lng').value = "";
                        alert('Lỗi geocode: ' + status);
                    }
                });
            }
        </script>
        <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB8nz4AoPyn5FeuOSI1wDd0o0cbmu621jc&callback=initMap">
        </script>
        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/javascripts/popup.js"></script>
        <script src="assets/javascripts/ajax.js" type="text/javascript"></script>

    </body>

</html>