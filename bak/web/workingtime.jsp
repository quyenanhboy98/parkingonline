<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Add Working Time</title>


        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />

        <script>
            function getDetailsWorkingTime(timeStart, timeEnd, workingTimeID) {
                var txtTimeStart = document.getElementById("timestart");
                txtTimeStart.value = timeStart.toString().substring(0, 8);
                var txtTimeEnd = document.getElementById("timeend");
                txtTimeEnd.value = timeEnd.toString().substring(0, 8);
                var txtWorkingTimeID = document.getElementById("workingTimeID");
                txtWorkingTimeID.value = workingTimeID;
            }

            function preUpdate() {
                var preTimeStart = document.getElementById("preTimeStart");
                preTimeStart.value = document.getElementById("timestart").value;

                var preTimeEnd = document.getElementById("preTimeEnd");
                preTimeEnd.value = document.getElementById("timeend").value;

                var preWorkingTimeID = document.getElementById("preWorkingTimeID");
                preWorkingTimeID.value = document.getElementById("workingTimeID").value;

                var preParkID = document.getElementById("preParkIDUpdate");
                preParkID.value = document.getElementById("parkID").value;
            }

            function preCreate() {
                var preParkID = document.getElementById("preParkIDCreate");
                preParkID.value = document.getElementById("parkID").value;
            }

            function preDelete() {
                var preParkIDDelete = document.getElementById("preParkIDDelete");
                preParkIDDelete.value = document.getElementById("parkID").value;
                alert(document.getElementById("parkID").value);
            }

            function preAdd() {
                var preParkIDAdd = document.getElementById("preParkIDAdd");
                preParkIDAdd.value = document.getElementById("parkID").value;
            }

        </script>
    </head>

    <body on>
        <!-- Popup -->
        <c:if test="${not empty requestScope.ERROR.createError}">
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="popup-status">Status</h2>
                    </div>
                    <div class="modal-body">
                        <h3 style="font-family: monospace; color: red">${requestScope.ERROR.createError}</h3>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" value="Close" id="input-close" />
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.SUCCESS}">
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="popup-status">Status</h2>
                    </div>
                    <div class="modal-body">
                        <h3 style="font-family: monospace; color: green">${requestScope.SUCCESS}</h3>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" value="Close" id="input-close" />
                    </div>
                </div>
            </div>
        </c:if>
        <c:set var="USERDTO" value="${sessionScope.USERDTO}" />
        <div class="container col-md-12">

            <h1 style="margin-left: 10px; color: #0074ad">ADD NEW WORKING TIME</h1>

            <div class="col-md-5">

                <div class="col-md-12">
                    <h4>Tên bãi xe</h4>
                    <div class="input-group col-md-12">
                        <div class="input-group-addon">
                            <span>
                                <icon class="fa fa-car"></icon>
                            </span>
                        </div>
                        <c:if test="${USERDTO.role == 1}">
                            <form action="GetListWorkingSchedule" method="POST">
                                <input id="srcPage" type="hidden" name="srcPage" value="workingtime.jsp" />
                                <select id="parkID" class="col-md-12 form-control" name="parkID" onchange="this.form.submit()">
                                    <c:if test="${sessionScope.SELECTEDPARK == null}" >
                                        <c:forEach items="${requestScope.LISTPARK}" var="dto">
                                            <option value="${dto.parkID}">${dto.parkName}</option>
                                        </c:forEach>
                                    </c:if>

                                    <c:if test="${sessionScope.SELECTEDPARK != null}" >
                                        <c:forEach items="${requestScope.LISTPARK}" var="dto">
                                            <c:if test="${dto.parkID.equals(sessionScope.SELECTEDPARK)}" >
                                                <option value="${dto.parkID}" selected="selected">${dto.parkName}</option>
                                            </c:if>
                                            <c:if test="${!dto.parkID.equals(sessionScope.SELECTEDPARK)}" >
                                                <option value="${dto.parkID}">${dto.parkName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </form>
                        </c:if>
                        <c:if test="${USERDTO.role == 3}">
                            <input id="parkID" type="hidden" name="parkID" value="${USERDTO.parkID}" />
                            <input class="col-md-12 form-control" type="text" name="parkName" value="${USERDTO.parkName}" readonly="readonly" />
                        </c:if>

                    </div>
                </div>

                <form action="addWorkingTime" onsubmit="preAdd()">

                    <c:if test="${sessionScope.SELECTEDPARK != null}" >
                        <input type="hidden" name="parkID" value="${sessionScope.SELECTEDPARK}" />
                    </c:if>

                    <c:if test="${sessionScope.SELECTEDPARK == null}" >
                        <input type="hidden" name="parkID" value="${requestScope.LISTPARK[0].parkID}" />
                    </c:if>

                    <div class="col-md-12">
                        <h4>Mã ca làm việc</h4>
                        <div class="input-group col-md-12">
                            <input id="workingTimeID" class="col-md-12 form-control" value="${requestScope.PARKIDNEW}" readonly="readonly" type="text" name="workingTimeID" />
                        </div>
                        <label style="color: red;">${requestScope.WORKINGTIMEEMPTY}</label>

                    </div>

                    <div class="col-md-12">
                        <h4>Giờ bắt đầu</h4>
                        <div class="input-group bootstrap-timepicker timepicker">
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-time"></i>
                            </span>
                            <input id="timestart" type="text" class="form-control input-small" name="timeStart" readonly="readonly" value="${param.timeStart}">
                        </div>
                        <label style="color: red;">${requestScope.TIMESTARTERROR}</label>

                    </div>


                    <div class="col-md-12">
                        <h4>Giờ kết thúc</h4>
                        <div class="input-group bootstrap-timepicker timepicker">
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-time"></i>
                            </span>
                            <input id="timeend" type="text" class="form-control input-small" name="timeEnd" readonly="readonly" value="${param.timeEnd}">
                        </div>
                        <label style="color: red;">${requestScope.TIMEENDERROR}</label>

                    </div>

                    <!-- AddWorking button -->
                    <input type="hidden" name="srcPage" value="workingtime.jsp" />
                    <div class="col-md-4" style="margin-top: 20px">
                        <input id="preParkIDAdd" type="hidden" name="parkID" value="" />
                        <input style="width: 150px; height: 45px;" type="submit" value="Thêm mới" class="btn btn-primary"/>
                    </div>
                </form>


                <!-- Tạo mới button -->
                <form action="CreateNewWorkingTime" onsubmit="preCreate()">
                    <input id="preParkIDCreate" type="hidden" name="parkID" value="" />
                    <input type="hidden" name="srcPage" value="workingtime.jsp" />
                    <div class="col-md-4" style="margin-top: 20px">
                        <input type="hidden" value="#" name="" />
                        <input style="width: 150px; height: 45px;" type="submit" value="Tạo mới" class="btn btn-primary" />
                    </div>
                </form>

                <!-- Update button -->
                <form action="UpdateWorkingTime" onsubmit="preUpdate()">
                    <input id="preParkIDUpdate" type="hidden" name="parkID" value="" />
                    <input id="preWorkingTimeID" type="hidden" name="workingTimeID" value="" />
                    <input id="preTimeStart" type="hidden" name="timeStart" value="" />
                    <input id="preTimeEnd" type="hidden" name="timeEnd" value="" />
                    <input type="hidden" name="srcPage" value="workingtime.jsp" />
                    <div class="col-md-4" style="margin-top: 20px">
                        <input type="hidden" value="#" name="" />
                        <input style="width: 150px; height: 45px;" type="submit" value="Cập nhật" class="btn btn-primary" />
                    </div>
                </form>

            </div>

            <!-- Table -->
            <div class="col-md-7 table-reponsive" style="margin-top: 40px;">
                <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                    <colgroup>
                        <col style="width: 5%;" />
                        <col style="width: 30%;" />
                        <col style="width: 30%;" />
                        <col style="width: 5%;" />
                        <col style="width: 5%" />
                    </colgroup>
                    <thead>
                        <tr>
                            <th>Mã</th>
                            <th>Giờ bắt đầu</th>
                            <th>Giờ kết thúc</th>
                            <th>Chi tiết</th>
                            <th>Xóa</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="wkt" items="${requestScope.LISTWORKINGTIME}">
                            <tr>
                                <td style="padding-top: 19px;">${wkt.ID}</td>
                                <td style="padding-top: 19px;">${wkt.timeStart}</td>
                                <td style="padding-top: 19px;">${wkt.timeEnd}</td>
                                <td>
                                    <button class="btn btn-primary" type="submit" style="margin-left: 10px;" onclick="getDetailsWorkingTime('${wkt.timeStart}', '${wkt.timeEnd}', '${wkt.ID}')">
                                        <i class="fa fa-file" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                    </button>
                                </td>

                                <td>
                                    <form action="DeleteWorkingTime" onsubmit="preDelete()">
                                        <input type="hidden" id="preParkIDDelete" name="parkID" value="${param.parkID}" />
                                        <input type="hidden" name="srcPage" value="workingtime.jsp" />
                                        <input type="hidden" name="workingTimeID" value="${wkt.ID}" />
                                        <button class="btn btn-danger" type="submit" style="margin-left: 10px;">
                                            <i class="fa fa-trash" style="height: 25px; width: 20px; padding-top: 7px;"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>

    <script src="assets/vendor/jquery/jquery.js"></script>
    <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
    <script src="assets/javascripts/moment.js"></script>
    <script src="assets/javascripts/collapse.js"></script>
    <script src="assets/javascripts/transition.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
    <script src="assets/javascripts/bootstrap-datetimepicker.min.js"></script>
    <script src="assets/javascripts/popup.js"></script>
    <script src="assets/javascripts/dateptimepicker.js"></script>
    <script src="assets/javascripts/ajax.js" type="text/javascript"></script>


</html>