<%-- 
    Document   : view_records
    Created on : Feb 19, 2015, 8:32:46 AM
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    Object tableData = request.getAttribute("list");
    if(tableData == null){
        response.sendRedirect("errorPage.jsp");
        out.print("No Data Available");
    }

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css.css">
        <title>Records</title>
    </head>

    <body>
        <div id="login"><fieldset>Logged In as ${login}</fieldset></div>
        <table id="data">
            <thead>
            <th>Hotel ID</th>
            <th>Hotel Name</th>
            <th>Hotel Street</th>
            <th>Hotel City</th>
            <th>Hotel State</th>
        </thead>
        <tbody>
            <c:forEach var="hotel" items="${list}" >
                <tr>
                    <td>${hotel.hotel_id}</td>
                    <td>${hotel.hotel_name}</td>
                    <td>${hotel.street}</td>
                    <td>${hotel.city}</td>
                    <td>${hotel.state}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Form To Edit A Record -->    
    <div id="masterContainer">
        
        <div id="edit_recordContainer" class="formContainers">
            <form id="edit_record" name="edit_record" method="POST" action="editRec">
                <fieldset>
                    <legend>Edit Record</legend>

                    <table id="editRecordForm" class="tableForms" name="editRecordForm">
                        <tr>
                            <td>Record #:</td>
                            <td><input type="text" id="primary_key" name="primary_key" /></td>
                        </tr>

                        <tr>
                            <td>Edit Column: </td>
                            <td><input type="text" id="column" name="column" /></td>
                        </tr>

                        <tr>
                            <td>New Value: </td>
                            <td><input type="text" id="value" name="value" /></td>
                        </tr>

                        <tr>
                            <td style="text-align: center;"><input type="submit" id="submit" name ="submit" /></td>
                        </tr>
                    </table>

                </fieldset>
            </form>
        </div>

        <!-- Form To Delete A Record -->

        <div id="delete_recordContainer" class="formContainers">
            <form id="delete" name="delete" method="POST" action="delete">
                <fieldset>
                    <legend>Delete Record</legend>
                    <table class="tableForms">
                        <tr>Which Record # To Delete: <input type="text" id="primaryKey" name="primaryKey" /></tr>
                        <td style="padding-top:2%;"><input style="float:left;" type="submit" name="submit" id="submit" /></td>
                    </table>
                </fieldset>
            </form>
        </div> 

        <!-- Form To Create A Record -->

        <div id="create_recordContainer" class="formContainers">
            <form id="newRecordForm" name="createForm" method="POST" action="createRec">
                <fieldset>
                    <legend>Create New</legend>
                    <table>
                        <tr>
                            <td>Enter Hotel Name:</td>
                            <td><input type="text" id="hotel_name" name="hotel_name" /></td>
                        </tr>
                        
                        <tr>
                            <td>Enter Hotel Street:</td>
                            <td><input type="text" id="street" name="street" /></td>    
                        </tr>
                        
                        <tr>
                            <td>Enter Hotel City:</td>
                            <td><input type="text" id="city" name="city" /></td>
                        </tr>
                        
                        <tr>
                            <td>Enter Hotel State:</td>
                            <td><input type="text" id="state" name="state" /></td>
                        </tr>
                        <tr><td><input type="submit" id="submit" name ="submit" /></td></tr>
                    </table>
                </fieldset>
            </form>
        </div>


    </div>      
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</body>
</html>