<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

    <head>
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/addnew.css" />
    </head>

    <body>

        <div id="containner" class="col-md-12" style="margin-top: 20px;">

            <h1 style="margin-left: 15px; color: #0074ad">THÔNG TIN CHI TIẾT</h1>

            <form <c:if test="${sessionScope.USERDTO.role != 3 && requestScope.LISTTIME == null}">
                    action="updateUser"
                </c:if>
                    
                <c:if test="${sessionScope.USERDTO.role == 4}">
                    action="updateUser"
                </c:if>

                <c:if test="${sessionScope.USERDTO.role == 3 || requestScope.LISTTIME != null}">
                    action="updateEmployee"
                </c:if>

                method="POST">
                <c:set var="user" value="${requestScope.PROFILEUSER}" />
                <c:if test="${user == null}">
                    <c:set var="user" value="${sessionScope.USERDTO}" />
                </c:if>
                <c:set var="error" value="${requestScope.ERROR}" />

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Số Điện Thoại</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-phone"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="phone" value="${user.phone}" readonly="readonly" />
                        </div>
                    </div>

                    <div class="col-md-6">
                        <h4>Tên Đầy Đủ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-pencil"></icon>
                                </span>
                            </div>

                            <input type="text" class="form-control" name="fullname" value="${user.fullName}" />

                        </div>
                        <label style="color: red;">${error.nameIsEmpty}</label>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Email</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-envelope"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="email" value="${user.email}" />
                        </div>
                        <label style="color: red;">${error.emailIsEmpty}</label>
                    </div>

                    <div class="col-md-6">
                        <h4>Địa Chỉ</h4>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fa fa-map-marker"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="address" value="${user.address}" />
                        </div>
                        <label style="color: red;">${error.addressIsEmpty}</label>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="col-md-6">
                        <h4>Ngày Sinh</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </span>
                            <input type="text" id="datepicker" name="dob" class="form-control" value="${user.dob}" readonly="readonly">
                        </div>
                        <label style="color: red;">${error.dobIsWrongFormation}</label>
                    </div>

                    <div class="col-md-3">
                        <h4>Giới Tính</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-mars"></i>
                            </span>
                            <c:if test="${user.sex == true}">
                                <select class="col-md-12 form-control" name="sex">
                                    <option value="Male">Nam</option>
                                    <option value="Female">Nữ</option>
                                </select>
                            </c:if>

                            <c:if test="${user.sex == false}">
                                <select class="col-md-12 form-control" name="sex">
                                    <option value="Female">Nữ</option>
                                    <option value="Male">Nam</option>
                                </select>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <h4>Trạng Thái</h4>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="fa fa-flag"></i>
                            </span>
                            <c:if test="${user.active == true}">
                                <input class="form-control" value="Hoạt động" readonly="readonly">
                            </c:if>
                            <c:if test="${user.active == false}">
                                <input class="form-control" value="Đã khoá" readonly="readonly">
                            </c:if>

                        </div>
                    </div>
                </div>

                <c:if test="${sessionScope.USERDTO.role == 3 || sessionScope.USERDTO.role == 4 || (sessionScope.USERDTO.role == 1 
                              && requestScope.LISTTIME != null)}">
                      <div class="col-md-12">
                          <div class="col-md-6">
                              <h4>Bãi Xe</h4>
                              <div class="input-group col-md-12">
                                  <div class="input-group-addon">
                                      <span>
                                          <icon class="fas fa-location-arrow"></icon>
                                      </span>
                                  </div>
                                  <input type="text" class="form-control" name="parkName" value="${user.parkName}" readonly="readonly" />

                              </div>
                          </div>
                          <div class="col-md-6">
                              <h4>Ca Làm Việc</h4>
                              <div class="input-group">
                                  <span class="input-group-addon">
                                      <i class="fa fa-mars"></i>
                                  </span>
                                  <c:if test="${sessionScope.USERDTO.role != 4}" >
                                      <select class="form-control" name="workingTimeID">
                                          <c:set var="listTime" value="${requestScope.LISTTIME}" />
                                          <c:if test="${empty listTime}">
                                              <c:set var="listTime" value="${requestScope.LISTWORKINGTIME}" />
                                          </c:if>
                                          <c:if test="${not empty listTime}">
                                              <c:if test="${user.timeStart == null}">
                                                  <option value="NWKT" selected="selected">Không có ca làm việc</option>
                                                  <c:forEach items="${listTime}" var="dto">
                                                      <option value="${dto.ID}">${dto.timeStart} - ${dto.timeEnd}</option>
                                                  </c:forEach>
                                              </c:if>
                                              <c:if test="${user.timeStart != null}">
                                                  <option value="NWKT">Không có ca làm việc</option>
                                                  <c:forEach items="${listTime}" var="dto">
                                                      <c:if test="${user.timeStart.equals(dto.timeStart) && user.timeEnd.equals(dto.timeEnd)}">
                                                          <option value="${dto.ID}" selected="selected">${dto.timeStart} - ${dto.timeEnd}</option>
                                                      </c:if>
                                                      <c:if test="${!user.timeStart.equals(dto.timeStart) || !user.timeEnd.equals(dto.timeEnd)}">
                                                          <option value="${dto.ID}">${dto.timeStart} - ${dto.timeEnd}</option>
                                                      </c:if>
                                                  </c:forEach>
                                              </c:if>
                                          </c:if>
                                          <c:if test="${empty listTime}">
                                              <option value="NWKT" selected="selected">Không có ca làm việc</option>
                                          </c:if>
                                      </select>
                                  </c:if>

                                  <c:if test="${sessionScope.USERDTO.role == 4}" >
                                      <input type="text" class="form-control" value="${sessionScope.USERDTO.timeStart} - ${sessionScope.USERDTO.timeEnd}" readonly="readonly" />
                                  </c:if>

                              </div>
                          </div>
                      </div>
                </c:if>


                <div class="col-md-2">
                    <input type="hidden" name="role" value="${user.role}" />

                    <c:if test="${param.srcPage.equals('listemployee.jsp') || param.srcPage.equals('liststaff.jsp')}">
                        <input type="hidden" name="srcPage" value="GetListWorkingTimeForEmployeeServlet" />
                    </c:if>
                    <c:if test="${param.srcPage.equals('listuser.jsp') || param.srcPage.equals('userdetails.jsp')
                                  || param.srcPage.equals('GetListWorkingTimeForEmployeeServlet')}">
                          <input type="hidden" name="srcPage" value="${param.srcPage}" />
                    </c:if>
                    <c:if test="${not empty param.parkIDStaff}">
                        <input type="hidden" name="parkID" value="${param.parkIDStaff}" />
                    </c:if>
                    <c:if test="${not empty param.parkID}">
                        <input type="hidden" name="parkID" value="${param.parkID}" />
                    </c:if>
                    <input type="hidden" name="searchValue" value="${param.searchValue}" />
                    <input type="hidden" name="fromPage" value="${param.fromPage}" />
                    <input style="width: 150px; height: 45px; margin-top: 20px;" type="submit" value="CẬP NHẬT" class="btn btn-primary" />
                </div>
            </form>



            <c:if test="${param.srcPage.equals('listemployee.jsp') || param.srcPage.equals('liststaff.jsp') 
                          || param.srcPage.equals('GetListWorkingTimeForEmployeeServlet')}">
                  <!--                  <form action="searchEmployee" method="POST">
                                        <input type="hidden" name="parkID" value="${user.parkID}" />
                                        <input type="hidden" name="searchValue" value="${param.searchValue}" />
                  <c:if test="${param.srcPage.equals('liststaff.jsp') || param.fromPage.equals('liststaff.jsp')}">
                      <input type="hidden" name="srcPage" value="liststaff.jsp" />
                  </c:if>
                  <c:if test="${!param.srcPage.equals('liststaff.jsp') || param.fromPage.equals('listemployee.jsp')}">
                      <input type="hidden" name="srcPage" value="listemployee.jsp" />
                  </c:if>
                  <input type="hidden" name="fromPage" value="${param.frompage}" />

                  <div class="col-md-2">
                      <input style="width: 150px; height: 45px;margin-top: 20px;" type="submit" value="QUAY LẠI" class="btn btn-primary" />
                  </div>
              </form>-->
            </c:if>

            <c:if test="${param.srcPage.equals('listuser.jsp')}">
                <!--                <form action="searchUser" method="POST">
                                    <input type="hidden" name="searchRole" value="${sessionScope.SELECTEDROLE}" />
                                    <input type="hidden" name="searchValue" value="${param.searchValue}" />
                                    <input type="hidden" name="fromPage" value="${param.frompage}" />
                
                                    <div class="col-md-2">
                                        <input style="width: 150px; height: 45px; margin-top: 20px;" type="submit" value="QUAY LẠI" class="btn btn-primary" />
                                    </div>
                                </form>-->
            </c:if>


            <c:if test="${error.createError != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">LỖI</h2>
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
                            <h4 style="font-family: monospace; color: blue">${requestScope.SUCCESS }</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" />
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <!--JS-->
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
    </body>

</html>