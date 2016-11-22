<%-- 
    Document   : ManageAuthor
    Created on : Oct 2, 2016, 6:43:41 PM
    Author     : Gladwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Author Management</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="MainStyleSheet.css" rel="stylesheet" type="text/css"/>
    </head>
     <body>
    <header>
        <h1>Manage Author</h1>
        <h3>
            
            <a href="AuthorController?action=list">Manage Authors</a>
        </h3>
    </header>
   
        <form method="POST" action="AuthorController">
            <table width="500" border="1" cellspacing="0" cellpadding="4" align="center">
                <c:choose>
                    <c:when test="${not empty author}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">ID</td>
                            <td align="left"><input type="text" value="${author.authorId}" name="authorId" readonly/></td>
                        </tr>         
                    </c:when>
                </c:choose>            
                  <c:choose>
                    <c:when test="${not empty author.bookSet}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">Books</td>
                            <td>
                                <select id="booksDropDown" name="bookId">
                                    <c:forEach var="book" items="${author.bookSet}" varStatus="rowCount">                                       
                                        <option value="${book.bookId}">${book.title}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td style="background-color: black;color:white;" align="left">Books</td>
                            <td>None</td>
                        </tr>
                    </c:otherwise>
                </c:choose>    
                                           
                                            
                <tr>
                    <td style="background-color: black;color:white;" align="left">Name</td>
                    <td align="left"><input type="text" value="${author.authorName}" name="authorName" /></td>
                </tr>
                
                <c:choose>
                    <c:when test="${not empty author}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">Date Added</td>
                            <td align="left"><input type="text" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${author.dateAdded}'/>" name="dateAdded" readonly /></td>
                        </tr>         
                    </c:when>
                </c:choose>
                </table>
                
                <br>
                 <div style="text-align:center;">
                    <input type="submit" value="Save" name="action" />&nbsp;
                    <input type="submit" value="Cancel" name="action" />
                  </div>                 
            
        </form>
                <br>
                 <div style="text-align:center;">
                 <p>Click here to go back to Home Page <a href="index.html">Home</a></p>
                 </div>
                  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    </body>
</html>