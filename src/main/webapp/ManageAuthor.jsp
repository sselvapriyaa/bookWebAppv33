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
<head>
        <title>Author Management</title>
          <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="MainStyleSheet.css" rel="stylesheet" type="text/css"/>
    </head>
    <header>
        <h1>Manage Author</h1>
        <h3>
         <%--   <a href="http://localhost:8080/bookWebAppv3.3/index.html">Home</a>
             <p>Contact for comments <a href="mailto:${webmasterEmail}">webmaster</a></p>--%>
            <a href="AuthorController?action=list">Manage Authors</a>
        </h3>
    </header>
    <body>
         <jsp:include page="header.jsp"/>
        <form method="POST" action="AuthorController">
           
          <table width="500" border="1" cellspacing="0" cellpadding="4" align="center">
                
                      
                        <tr>
                            <td style="background-color: black;color:white;" align="left">ID</td>
                            <td align="left"><input type="text" value="${author.authorId}" name="authorId" readonly/></td>
                        </tr>         
             
                
                <tr>
                    <td style="background-color: blue;color:white;" align="left">Name</td>
                    <td align="left"><input type="text" value="${author.authorName}" name="authorName" /></td>
                </tr>
                
             
                        <tr>
                            <td style="background-color: black;color:white;" align="left">Date Added</td>
                            <td align="left"><input type="text" value="${author.dateAdded}" name="dateAdded" readonly /></td>
                        </tr>         
              
                
                <tr>
                <div>
                    <input type="submit" value="Save" name="action" />&nbsp;
                    <input type="submit" value="Cancel" name="action" />
                </div>
                <br/>
                </tr>
                
            </table>
        </form>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    </body>
</html>
