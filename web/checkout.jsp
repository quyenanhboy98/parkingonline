<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>

    <head>
        <link rel="shortcut icon" href="assets/images/p.jpg" type="image/jpg" />
        <!--Css-->
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />
        <link rel="stylesheet" href="assets/stylesheets/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup.css" />
        <link rel="stylesheet" href="assets/stylesheets/addnew.css" />
        <script>
            function clearInput() {
                setTimeout(function () {
                    document.getElementById("cardID").value = "";
                    document.getElementById("cardID").focus();
                }, 500);
            }
        </script>
    </head>

    <body onload="clearInput()">
        <div id="container" class="col-md-12" style="margin-top: 20px;">

            <h1 style="margin-left: 10px; color: #0074ad">CHECK OUT</h1>

            <form action="CheckCardIDCheckout" method="POST">
                <div class="col-md-12">

                    <div class="col-md-6">
                        <h3>SỐ THẺ</h3>
                        <div class="input-group col-md-12">
                            <div class="input-group-addon">
                                <span>
                                    <icon class="fas fa-credit-card"></icon>
                                </span>
                            </div>
                            <input type="text" class="form-control" name="cardID" id="cardID" value="" />
                        </div>
                    </div>

                    <div class="col-md-3" style="margin-top: 5.5%;">
                        <input type="submit" class="btn btn-primary" value="CHECKOUT">
                    </div>
                </div>
            </form>

            <c:if test="${requestScope.BILLID != null}">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h2 class="popup-status">TRẠNG THÁI</h2>
                        </div>
                        <div class="modal-body">
                            <h4 style="font-family: monospace; color: black">Checkout thành công.</h4>
                        </div>
                        <div class="modal-footer">
                            <div class="col-md-9" id="buttonbookingpopup">
                                <form action="GetBillDetail">
                                    <input type="hidden" name="billID" value="${requestScope.BILLID}" />
                                    <input type="submit" class="btn btn-default" value="XEM CHI TIẾT" />
                                </form>
                            </div>
                            <div class="col-md-2">
                                <input style="width: 80px" type="button" class="btn btn-default" value="THOÁT" id="input-close" onclick="clearInput()"/>
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
                            <h4 style="font-family: monospace; color: red">${requestScope.ERROR}</h4>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" value="THOÁT" id="input-close" onclick="clearInput()"/>
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
        <script src="assets/javascripts/fontawesome-all.js"></script>
    </body>

</html>