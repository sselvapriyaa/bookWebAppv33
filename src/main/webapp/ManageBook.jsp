<%-- 
    Document   : ManageBook
    Created on : Nov 21, 2016, 9:08:35 PM
    Author     : Gladwin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Book Management</title>
         <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="MainStyleSheet.css" rel="stylesheet" type="text/css"/>
    </head>
     <body>
    <header>
        <h1>Manage Book</h1>
        <h3>
            
            <a href="BookController?action=list">Manage Books</a>
        </h3>
    </header>
   
        <form method="POST" action="BookController">
            <table width="500" border="1" cellspacing="0" cellpadding="4" align="center">
              
                <c:choose>
                    <c:when test="${not empty book}">
                        <tr>
                            <td style="background-color: black;color:white;" align="left">ID</td>
                            <td align="left"><input type="text" value="${book.bookId}" name="bookId" readonly/></td>
                        </tr>         
                    </c:when>
                </c:choose>
                
                <tr>
                    <td style="background-color: black;color:white;" align="left">Title</td>
                    <td align="left"><input type="text" value="${book.title}" name="title" /></td>
                </tr>
                <tr>
                    <td style="background-color: black;color:white;" align="left">ISBN</td>
                    <td align="left"><input type="text" value="${book.isbn}" name="isbn" /></td>
                </tr>
                
                <tr>
                    <td style="background-color: black;color:white;" align="left">Author</td>
                    <td align="left">
                    <select id="authorDropDown" name="authorId">
                    <c:choose>
                        <c:when test="${not empty book.authorId}">
                            <option value="">None</option>
                            <c:forEach var="author" items="${authors}">                                       
                                <option value="${author.authorId}" <c:if test="${book.authorId.authorId == author.authorId}">selected</c:if>>${author.authorName}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="author" items="${authors}" varStatus="rowCount">                                       
                                <option value="${author.authorId}" <c:if test="${rowCount.count == 1}">selected</c:if>>${author.authorName}</option>
                            </c:forEach>
                         </c:otherwise>
                    </c:choose>
                    </select>
                </td>
                </tr>
                
                <tr>
                <div style="text-align:center;">
                    <input type="submit" value="Save" name="action" />&nbsp;
                    <input type="submit" value="Cancel" name="action" />
                </div>
                <br/>
                </tr>
            </table>
        </form>
                <br>
             <div style="text-align:center;">   
            <p>Click here to go back to Home Page <a href="index.html">Home</a></p>
             </div>
             <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
   
    </body>
</html>