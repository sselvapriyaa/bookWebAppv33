<%-- 
    Document   : ManageBooks
    Created on : Nov 21, 2016, 9:09:33 PM
    Author     : Gladwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Manage Books</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="MainStyleSheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <header>
        <h1>Manage Books</h1>
        <h3>
            
            <a href="BookController?action=list">Manage Books</a>
        </h3>
    </header>
    
             <form method="POST" action="BookController?action=addEditDelete">
        <div>
            <input type="submit" value="Add/Edit" name="submit" align="center"/>&nbsp;
            <input type="submit" value="Delete" name="submit" align="center"/>
        </div>
        <br/>
        
        <div>
            <table align="center">
                <tr style="background-color: black;color:white;">
                    <th align="left" class="tableHead">ID</th>
                    <th align="left" class="tableHead">Title</th>
                    <th align="right" class="tableHead">ISBN</th>
                    <th align="right" class="tableHead">Author</th>
                    
                </tr>
               
                <c:forEach var="b" items="${books}" varStatus="rowCount">
                    <c:choose>
                        <c:when test="${rowCount.count % 2 == 0}">
                            <tr style="background-color: white;">
                        </c:when>
                        <c:otherwise>
                            <tr style="background-color: #D3D3D3">
                        </c:otherwise>
                    </c:choose>
                                <td><input type="checkbox" name="bookId" value="${b.bookId}" /></td>
                                <td align="left">${b.title}</td>
                                <td align="left">${b.isbn}</td>
                                <td align="left">
                                    <c:choose>
                                        <c:when test="${not empty b.authorId}">
                                            ${b.authorId.authorName}
                                        </c:when>
                                        <c:otherwise>
                                            None
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                </c:forEach>
            </table>
        </div>
        <br>
        <div style="text-align:center;">
            <input type="submit" value="Add/Edit" name="submit" align="center"/>&nbsp;
            <input type="submit" value="Delete" name="submit" align="center"/>
        </div>
        </form>
        <br>
        <div style="text-align:center;">
         <p>Click here to go back to Home Page <a href="index.html">Home</a></p>
        </div>
                       <c:if test="${errMsg != null}">
                <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
            </c:if>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    </body>
</html>