<%-- 
    Document   : authorTablePage
    Created on : Oct 10, 2016, 9:00:09 PM
    Author     : Gladwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Authors</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="MainStyleSheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <header>
        <h1>Author Table</h1>
        <h3>
            <a href="http://localhost:8080/bookWebAppv3.3/index.html">Home</a>
            <a href="AuthorController?action=list">Manage Authors</a>
        </h3>
    </header>
   <%-- <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link href="MainStyleSheet.css" rel="stylesheet" type="text/css"/>
        <title>Author Table</title>
    </head>--%>
    <body>
        <form method="POST" action="AuthorController?action=addEditDelete">
            <div>
            <table align="center">
                <th></th>
                <th align="left" class="tableHead">ID</th>
                <th align="left" class="tableHead">Author Name</th>
                <th align="right" class="tableHead">Date Added</th>
               
                <c:forEach var="i" items="${authors}">
                    <tr>
                        <td><input type="checkbox" name="authorId" value="${i.authorId}" /></td>
                        <td align="left">${i.authorId}</td>
                        <td align="left">${i.authorName}</td>
                        <td align="right"><fmt:formatDate pattern="MM/dd/yyyy" value="${i.dateAdded}"></fmt:formatDate></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        <div style="text-align:center;">
            <input type="submit" name="submit" value="Add/Edit" align="center"/>&nbsp;
            <input type="submit" name="submit" value="Delete" align="center"/>
        </div>
    </form>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    </body>
</html>
