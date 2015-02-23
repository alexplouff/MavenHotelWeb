<%-- 
    Document   : home
    Created on : Feb 22, 2015, 5:19:30 PM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css.css">
        <title>JSP Page</title>
    </head>
    <body>
      <% String login = request.getParameter("login"); %>

        <h1 style="text-align:center;"> Welcome - What would you like to do? </h1>

        <a href="hc">View All Records</a>

        <form id="login" name="login" method="POST" action="hpc" >
            <fieldset>

                <table>

                    <tr>
                        <td>Login ID: </td>
                        <td><input id="loginID" name="loginID" type="text"></td>
                    </tr>

                    <tr>
                        <td><input type="submit" id="submit" name="submit"</td>
                        <td></td>
                    </tr>
                </table>

                <a href="view_records.jsp"></a>
            </fieldset>
        </form>

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    </body>
</html>
</html>
