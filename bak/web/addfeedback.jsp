<%-- 
    Document   : addfeedback
    Created on : Apr 11, 2018, 3:13:31 PM
    Author     : Jun
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="dto" value="${sessionScope.USERDTO}" />
        <form action="addFeedback">
            <table border="0">
                <tbody>
                    <tr>
                        <td>Tiêu đề</td>
                        <td>
                            <input type="text" name="title" value="" />
                            <input type="hidden" name="srcPage" value="GetListWorkingScheduleServlet?srcPage=addfeedback.jsp" />
                            <input type="hidden" name="phone" value="${dto.phone}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Bãi xe</td>
                        <td>
                            <select name="parkID">
                                <option value="None">Không</option>
                                <c:forEach items="${requestScope.LISTPARK}" var="dto">
                                    <option value="${dto.parkID}">${dto.parkName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <font color="red">*Để "Không" nếu gửi cho quản trị viên</font>
                        </td>
                    </tr>
                    <tr>
                        <td>Nội dung</td>
                        <td>
                            <textarea rows="5" cols="100" name="message"></textarea>
                            <input type="submit" value="Gửi" />
                        </td>
                    </tr>
                <label style="color: red;">${requestScope.ERROR}</label>
                <label style="color: green;">${requestScope.SUCCESS}</label>

                </tbody>
            </table>


            <br/>
            <br/>

            <br/>

        </form>

    </body>
</html>
