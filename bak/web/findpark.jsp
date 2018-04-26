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
            <script src="assets/vendor/jquery/jquery.js"></script>

        </head>

        <body>
            <div id="container">

                <div id="data-box">

                    <header>
                        DANH SÁCH BÃI XE
                    </header>

                    <div class="body-box">

                        <div class="col-md-10 col-md-offset-1" style="margin-bottom: 1%;">
                            <div class="col-md-12">
                                <h3>Giờ Đặt</h3>
                                <input type="text" class="form-control input-small" id="bookingtime" readonly="readonly" value="">
                            </div>
                        </div>
                        <div class="col-md-10 col-md-offset-1" id="searchform" style="margin-bottom: 1%;">
                            <form action="getParkDistanceMatrix">
                                <div class="col-md-6">
                                    <input id="lat" name="lat" type="hidden" value="" />
                                    <input id="lng" name="lng" type="hidden" value="" />
                                    <input name="location" type="text" class="form-control" placeholder="Tìm kiếm bãi đậu xe" value="${param.location}" />
                                </div>

                                <div class="col-md-3 unique">
                                    <c:if test="${param.sortByPrice == null}">
                                        <table class="table-fixed">
                                            <tbody class="table-fixed">
                                                <tr class="table-fixed">
                                                    <td class="table-fixed">Sắp theo giá</td>
                                                    <td class="table-fixed" style="text-align: center;">
                                                        <input type="checkbox" name="sortByPrice" value="ON" class="my-checkbox" style="z-index: 1;" />
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </c:if>
                                    <c:if test="${param.sortByPrice != null}">
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>Sắp theo giá</td>
                                                    <td style="text-align: center;">
                                                        <input type="checkbox" name="sortByPrice" value="ON" checked="checked" class="my-checkbox" />
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </c:if>
                                </div>

                                <div class="col-md-3">
                                    <select class="form-control" name="range">
                                        <option value="300">300m</option>
                                        <option value="500">500m</option>
                                        <option value="1000">1Km</option>
                                        <option value="2000">2Km</option>
                                        <option value="5000" selected="selected">5Km</option>
                                    </select>
                                </div>


                                <div class="col-md-6" style="margin-top: 5%; margin-bottom: 1%;">
                                   
                                        <input type="submit" class="btn btn-primary clickMeOne" name="find" value="Tìm" />
                                    
                                        <input type="submit" class="btn btn-primary search-button clickMe"  name="find" value="Tìm gần tôi" />
                                        
                                        <!--<input type="submit" class="btn btn-primary search-button clickMe"  name="find" value="Tìm hết" />-->
                                    
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
                                                        <div class="first-son-unique push-down-h">
                                                            <i class="fas fa-sort-numeric-down"></i>
                                                            ${counter.index + 1}
                                                        </div>
                                                        <div class="first-son-unique">
                                                            <p>
                                                                <strong>${park.parkName}</strong>
                                                            </p>
                                                        </div>
                                                        <div class="first-son-unique push-down">

                                                            <p class="special">
                                                                Trống
                                                                ${park.emptySlot}/${park.slot}
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="son">
                                                        <div class="first-son push-down">
                                                            <p>
                                                                <i class="fas fa-clock"></i>
                                                                ${park.duration}
                                                            </p>
                                                        </div>
                                                        <div class="first-son push-down">
                                                            <p>
                                                                <i class="fas fa-road"></i>
                                                                ${park.distance}
                                                            </p>
                                                        </div>
                                                        <div class="first-son push-down">
                                                            <p>
                                                                <i class="fas fa-money-bill-alt"></i>
                                                                ${park.price} VNĐ
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="son">
                                                        <div class="second-son">
                                                            <p class="address"> ${park.parkAddress} </p>
                                                        </div>
                                                        <div class="first-son">
                                                            <button class="btn btn-primary" type="button" onclick="checkCurrentBooking('${sessionScope.USERDTO.phone}', '${park.parkID}', '${park.duration}')">
                                                                Đặt
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
                <c:if test="${requestScope.ERROR == null && requestScope.NOTFOUND == null}">
                    <div id="myModal" class="modal" style="display: none">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="popup-status">Trạng Thái</h2>
                            </div>
                            <div class="modal-body">
                                <h3 id="messagebookingpopup" style="font-family: consolas; color: black"></h3>
                            </div>
                            <div class="modal-footer" class="col-md-12">
                                <div class="col-md-10" id="buttonbookingpopup">
                                </div>
                                <div class="col-md-2">
                                    <input style="width: 80px" type="button" class="btn btn-default" value="Tắt" id="input-close" />
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${requestScope.ERROR != null}">
                    <div id="myModal" class="modal">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="popup-status">Lỗi</h2>
                            </div>
                            <div class="modal-body">
                                <h3 style="font-family: consolas; color: black">${requestScope.ERROR}</h3>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" value="Close" id="input-close" />
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${requestScope.NOTFOUND != null}">
                    <div id="myModal" class="modal">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h2 class="popup-status">Không tìm thấy</h2>
                            </div>
                            <div class="modal-body">
                                <h3 style="font-family: consolas; color: black">${requestScope.NOTFOUND}</h3>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" value="Close" id="input-close" />
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
            $(document).ready(function () {
                $('#example').DataTable({
                    "pageLength": 3

                });
            });
        </script>
        <script src="assets/javascripts/datatable.js"></script>

        </html>