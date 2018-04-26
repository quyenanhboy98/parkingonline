<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <!-- Font-Awesome -->
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <!-- Checkbox -->
        <link rel="stylesheet" href="assets/stylesheets/checkbox.css" />
        <!-- Css -->
        <link rel="stylesheet" href="assets/stylesheets/list.css">
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/findpark.css" />
        <link rel="stylesheet" href="assets/stylesheets/pagination.css" />
        <link rel="stylesheet" href="assets/stylesheets/table.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/mymap.css" />
        <script src="assets/vendor/jquery/jquery.js"></script>
    </head>

    <body>
        <div id="container">

            <div id="data-box">

                <header>
                    DANH SÁCH BÃI XE
                </header>

                <div class="body-box">

                    <div class="col-md-12" style="margin-bottom: 0%;">
                        <form action="getParkDistanceMatrix">



                            <div class="col-md-4 pushdown">
                                <input id="lat" name="lat" type="hidden" value="" />
                                <input id="lng" name="lng" type="hidden" value="" />
                                <input name="location" type="text" class="form-control" placeholder="Nơi bạn muốn đến" value="${param.location}" />
                            </div>


                            <div class="col-md-4 unique pushdown">
                                <c:if test="${param.sortByPrice.equals('on')}">
                                    <select class="form-control" name="sortByPrice">
                                        <option value="off">Theo khoảng cách</option>
                                        <option value="on" selected="selected">Theo giá cả</option>
                                    </select>
                                </c:if>
                                <c:if test="${!param.sortByPrice.equals('on')}">
                                    <select class="form-control" name="sortByPrice">
                                        <option value="off" selected="selected">Theo khoảng cách</option>
                                        <option value="on" >Theo giá cả</option>
                                    </select>
                                </c:if>


                            </div>

                            <div class="col-md-4 pushdown">
                                <select class="form-control" name="range">
                                    <option value="500">500m</option>
                                    <option value="1000">1Km</option>
                                    <option value="3000">3Km</option>
                                    <option value="5000" selected="selected">5Km</option>
                                </select>
                            </div>


                            <div class="col-md-6" style="margin-top: 3%; margin-bottom: 1%;">

                                <input type="submit" class="btn btn-primary clickMeOne" name="find" value="GẦN TÔI" />

                                <input type="submit" class="btn btn-primary search-button clickMe" name="find" value="TÌM" />

                            </div>
                        </form>
                    </div>
                    <div class="data-table">
                        <table class="" id="example">
                            <colgroup>
                                <col style="width: 30%;" />
                                <col style="width: 35%;" />
                                <col style="width: 35%;" />
                            </colgroup>
                            <thead>
                            <th></th>
                            </thead>
                            <tbody>
                                <c:forEach var="park" items="${requestScope.PARKDISTANCE}" varStatus="counter">
                                    <tr>
                                        <td>
                                            <div class="father">
                                                <div class="son">
                                                    <div class="first-son-unique">
                                                        <b><h3>${park.parkName}</h3></b> 
                                                    </div>
                                                    <div class="first-son-unique">
                                                        <p>
                                                            Trống ${park.emptySlot}/${park.slot}
                                                        </p>
                                                    </div>
                                                    <div class="first-son-unique push-down">

                                                        <p class="special">
                                                            Thời gian tới ${park.duration}
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="son">
                                                    <div class="first-son push-down">
                                                        <p>

                                                            Khoảng cách tới ${park.distance}
                                                        </p>
                                                    </div>
                                                    <div class="first-son push-down">
                                                        <p>

                                                            ${park.price} VNĐ

                                                        </p>
                                                    </div>
                                                    <div class="first-son push-down">
                                                        <button class="btn btn-primary" type="button" onclick="getDirection('${park.parkID}')">
                                                            ĐƯỜNG ĐI
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="son">
                                                    <div class="second-son">
                                                        <p class="address"> ${park.parkAddress} </p>
                                                    </div>
                                                    <div class="first-son">
                                                        <!--<button class="btn btn-primary" type="button" onclick="checkBooking('${sessionScope.USERDTO.phone}', '${park.parkID}', '${park.duration}')">-->
                                                        <button class="btn btn-primary" type="button" onclick="setBookingTime('${park.parkID}', '${park.duration}')">
                                                            ĐẶT BÃI
                                                        </button>
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

            <div class="myMap" id="myModel" style="display: none">
                <div class="map-content">
                    <div class="map-header">
                        <span class="close" style="width: 50px; height: 20px; margin-top: 1%; margin-right: 5%; font-size: 25px;" onclick="closeMap()">ĐÓNG</span>
                        <h2 class="popup-status">ĐƯỜNG ĐI</h2>
                    </div>
                    <div class="map-body" id="map">

                    </div>
                </div>
            </div>

            <div class="myMap" id="myModel2" style="display: none">
                <div class="map-content" style="width: 20%; height: 20%;">
                    <div class="map-header">
                        <span class="close" style="width: 30px; height: 20px; margin-top: 4%; margin-right: 13%; font-size: 20px;" onclick="closeMap()">ĐÓNG</span>
                        <h2 class="popup-status">GIỜ ĐẶT</h2>
                    </div>
                    <div class="map-body" style="width: 100%; height: 20%;">
                        <div style="float: left; margin-top: 5%; margin-left: 2%; margin-right: 2%; width: 50%">
                            <input id="bookingtime" type="text" class="form-control" readonly="readonly" value=""> 
                        </div>
                        <div style="float: right; width: 45%">
                            <input id="popupparkid" type="hidden" class="form-control" readonly="readonly" value=""> 
                            <input id="popupparkduration" type="hidden" class="form-control" readonly="readonly" value=""> 
                            <button class="btn btn-primary" type="button" onclick="checkBooking('${sessionScope.USERDTO.phone}')">
                                CHỌN
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <c:if test="${requestScope.ERROR == null && requestScope.NOTFOUND == null}">
                <div id="myModal" class="modal" style="display: none">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 id="messagebookingpopup" style="font-family: consolas; color: black"></h4>
                        </div>
                        <div class="modal-footer" class="col-md-12">
                            <div class="col-md-10" id="buttonbookingpopup">
                            </div>
                            <div class="col-md-2">
                                <input style="width: 80px" type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${requestScope.ERROR != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">LỖI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: consolas; color: black">${requestScope.ERROR}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${requestScope.NOTFOUND != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">KHÔNG TÌM THẤY</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: consolas; color: black">${requestScope.NOTFOUND}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${requestScope.NOTFOUND == null}">
                <script>
                    $(() => {
                        $('#container').css('height', '200vh');
                    });
                </script>
            </c:if>
        </div>
    </body>
    <script src="assets/vendor/jquery/jquery.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
    <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
    <script src="assets/javascripts/popup.js"></script>
    <script src="assets/javascripts/fontawesome-all.js"></script>
    <script src="assets/vendor/jquery/jquery.js"></script>
    <script src="assets/javascripts/moment.js"></script>
    <script src="assets/javascripts/collapse.js"></script>
    <script src="assets/javascripts/transition.min.js"></script>
    <script src="assets/javascripts/bootstrap-datetimepicker.min.js"></script>
    <script src="assets/javascripts/dateptimepicker.js"></script>
    <script src="assets/javascripts/ajax.js" type="text/javascript"></script>
    <script>
                    $('select[name="range"]').find('option[value="${param.range}"]').attr("selected", true);
    </script>
    <script>
        function setBookingTime(parkID, duration) {
            document.getElementById("myModel2").style.display = "block";
            document.getElementById("popupparkid").value = parkID;
            document.getElementById("popupparkduration").value = duration;
            document.getElementById("bookingtime").innerHTML = "Giờ đặt xe<br/><input type=\"text\" class=\"input-append date\" id=\"bookingtime\" readonly=\"readonly\" value=\"\">";
        }
    </script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable({
                "pageLength": 3

            });
        });
    </script>
    <script src="assets/javascripts/datatable.js"></script>
    <script>
        function closeMap() {
            document.getElementById("myModel").style.display = "none";
            document.getElementById("myModel2").style.display = "none";
        }
    </script>
</html>