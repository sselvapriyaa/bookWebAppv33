<%-- 
    Document   : authorHomepage
    Created on : Oct 10, 2016, 8:57:03 PM
    Author     : Gladwin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="MainStyleSheet.css" rel="stylesheet" type="text/css"/>
        <title>Manage Authors</title>
    </head>
    <body>
        <form method="POST" action="AuthorController?action=list">
            <input type="submit" name="submit" value="View Authors">
        </form>
    </body>
</html>
