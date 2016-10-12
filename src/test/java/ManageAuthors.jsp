<%-- 
    Document   : ManageAuthors
    Created on : Oct 2, 2016, 6:34:33 PM
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
        <h1>Manage Authors</h1>
        <h3>
            <a href="http://localhost:8080/bookWebAppv3.3/index.html">Home</a>
            <a href="AuthorController?action=list">Manage Authors</a>
        </h3>
    </header>
    <body>
        <form method="POST" action="AuthorController?action=addEditDelete ">
        <div>
            <input type="submit" value="Add/Edit" name="submit" align="center"/>&nbsp;
            <input type="submit" value="Delete" name="submit" align="center"/>
        </div>
        <br/>
        
        <div>
            <table align="center">
                <tr style="background-color: black;color:white;">
                    <th align="left" class="tableHead">ID</th>
                    <th align="left" class="tableHead">Author Name</th>
                    <th align="right" class="tableHead">Date Added</th>
                </tr>
                <c:forEach var="a" items="${authors}" varStatus="rowCount">
                    <c:choose>
                        <c:when test="${rowCount.count % 2 == 0}">
                            <tr style="background-color: white;">
                        </c:when>
                        <c:otherwise>
                            <tr style="background-color: #D3D3D3">
                        </c:otherwise>
                    </c:choose>
                                <td>
                                    <input type="checkbox" name="authorId" value="${a.authorId}" />
                                </td>
                                <td align="left">
                                    ${a.authorName}
                                </td>
                                <td align="right">
                                    <fmt:formatDate pattern="M/d/yyyy" value="${a.dateAdded}"></fmt:formatDate>
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
        <p> <a href="index.html">Home Page</a></p>
        <c:if test="${errMsg != null}">
                <p style="font-weight: bold;color: red;width:500px;">Sorry, data could not be retrieved:<br>
                ${errMsg}</p>
            </c:if>
              <%--  <p><a href="mailto:${applicationScope['webmaster-email']}">Click to Contact Webmaster</a></p>--%>
         <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   

    </body>
</html>
