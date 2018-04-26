<%-- 
    Document   : addfeedback
    Created on : Apr 11, 2018, 3:13:31 PM
    Author     : Jun
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Feedback</title>
        <link rel="stylesheet" href="assets/stylesheets/font-feedback.css" />
        <link rel="stylesheet" href="assets/stylesheets/feedback.css" />
        <link rel="stylesheet" href="assets/stylesheets/popup-bottom.css" />
        <link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.css" />
        <link rel="stylesheet" href="assets/vendor/jquery-ui/jquery-ui.css" />


    </head>

    <body>
        <c:set var="dto" value="${sessionScope.USERDTO}" />
        <div id="form-main">
            <!-- popup -->
            <div id="form-div">
                <form class="form" id="form1" action="addFeedback">

                    <p class="name">
                        <input name="title" type="text" class="validate[required,custom[onlyLetter],length[0,100]] feedback-input" placeholder="Tiêu đề"
                               id="name" />
                        <input type="hidden" name="srcPage" value="GetListWorkingScheduleServlet?srcPage=addfeedback.jsp" />
                        <input type="hidden" name="phone" value="${dto.phone}" />
                    </p>

                    <p class="email">
                        <select name="parkID" class="validate[required,custom[email]] feedback-input" name="email" id="email">
                            <option value="None">Quản trị viên</option>
                            <c:forEach items="${requestScope.LISTPARK}" var="dto">
                                <option value="${dto.parkID}">${dto.parkName}</option>
                            </c:forEach>
                        </select>
                    </p>

                    <p class="text">
                        <textarea name="message" class="validate[required,length[6,300]] feedback-input" id="comment" placeholder="Nội dung"></textarea>
                    </p>


                    <div class="submit">
                        <input type="submit" value="GỬI" id="button-blue" />
                        <div class="ease"></div>
                    </div>
                    <c:if test="${not empty requestScope.SUCCESS}">
                        <label class="popup" style="color:green; font-size: 25px">${requestScope.SUCCESS}</label>

                    </c:if>
                    <c:if test="${not empty requestScope.ERROR}">
                        <label class="popup" style="color: red; font-size: 25px ">${requestScope.ERROR}</label>
                    </c:if>

                </form>


            </div>
        </div>





        <script src="assets/vendor/jquery/jquery.js"></script>
        <script src="assets/javascripts/popup.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.js"></script>
        <script src="assets/vendor/jquery-ui/jquery-ui.js"></script>
        <script src="assets/javascripts/fontawesome-all.js"></script>


    </body>

</html>