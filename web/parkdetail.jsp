<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

    <head>
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/parkdetail.css" />
    </head>

    <body>

        <div id="containner" class="col-md-12">

            <h1 style="margin-left: 15px; color: #0074ad">CHI TIẾT BÃI XE</h1>

            <form action="updatePark" method="POST">

                <c:set var="error" value="${requestScope.ERROR}" />
                <c:set var="park" value="${requestScope.PARK}" />
                <div class="col-md-12">

                    <div class="col-md-6 row">
                        <h4>Tên bãi</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-pencil"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="parkName" value="${park.parkName}" />
                        </div>
                        <label style="color: red;">${error.parkNameIsEmpty}</label>
                        <label style="color: red;">${error.parkNameIsWrongFormat}</label>
                    </div>

                    <div class="col-md-6">
                        <h4>Địa chỉ bãi</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-road"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="parkAddress" id="parkAddress" value="${park.parkAddress}" />
                        </div>
                        <label style="color:#EA2027">${error.parkAddressIsEmpty}</label>
                        <label style="color:#EA2027">${error.parkAddressIsWrongFormat}</label>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="col-md-2 row">
                        <h4>ID Bãi xe</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-automobile"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="parkID" value="${park.parkID}" readonly="readonly" />
                        </div>
                        <label style="color: red;">${error.parkIDIsEmtpy}</label>
                    </div>

                    <div class="col-md-2">
                        <h4>Số chỗ đậu</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-sliders"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="slot" value="${park.slot}" />
                            <input type="hidden" class="form-control" name="oldSlot" value="${park.slot}" />
                        </div>
                        <label style="color:#EA2027">${error.parkSpaceIsWrongFormat}</label>
                    </div>

                    <div class="col-md-2">
                        <h4>Phí/giờ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-money"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="price" value="${park.price}" />
                        </div>
                        <label style="color:#EA2027">${error.parkCostIsWrongFormat}</label>
                    </div>

                    <div class="col-md-3">
                        <h4>Vĩ độ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-map-marker"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="lat" id="lat" value="${park.lat}" readonly="readonly" />
                            <input type="hidden" class="form-control" name="oldLat" value="${park.lat}" />
                        </div>
                        <label style="color: red;">${error.parkLatitudeIsWrongFormat}</label>
                    </div>

                    <div class="col-md-3">
                        <h4>Kinh độ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-map-marker"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="lng" id="lng" value="${park.lng}" readonly="readonly" />
                            <input type="hidden" class="form-control" name="oldLng" value="${park.lng}" />
                        </div>
                        <label style="color: red;">${error.parkLongtitudeIsWrongFormat}</label>
                    </div>
                </div>



                <div class="col-md-12">
                    <div id="map">

                    </div>
                </div>
                <div class="col-md-12">

                    <input type="submit" value="CẬP NHẬT" class="btn btn-primary" />

                    <!-- </form> -->


                    <c:if test="${sessionScope.USERDTO.role != 3}">

                        <%--<c:url value="searchValue" var="myURL">--%>
                        <%--<c:param name="searchValue" value="${param.searchValue}" />--%>
                        <%--</c:url>--%>
                        <c:url value="searchEmployee" var="myURL">
                            <c:param name="parkID" value="${park.parkID}" />
                            <c:param name="searchValue" value="" />
                            <c:param name="srcPage" value="listemployee.jsp" />
                        </c:url>

                        <!--                            <button type="button" class="btn btn-primary">
                                                        <a href="${myURL}">QUAY LẠI</a>
                                                    </button>-->


                        <button type="button" class="btn btn-primary">
                            <a href="${myURL}">DANH SÁCH NV</a>
                        </button>


                    </c:if>

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
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
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
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
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
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
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
                    center: {lat: ${ park.lat }, lng: ${ park.lng }}
                });

                var marker = new google.maps.Marker({
                    map: map,
                    position: {lat: ${ park.lat }, lng: ${ park.lng }}
                });
                markers.push(marker);

                map.setZoom(17);
                map.panTo(marker.position);

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
    </body>

</html>