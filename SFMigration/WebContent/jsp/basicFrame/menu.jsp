<%-- 
    Document   : menu
    Created on : Dec 19, 2008, 1:38:32 AM
    Author     : eswar@vaannila.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            </head>
    <body>
        <form name="loginId" id="loginId" method="post">
        &nbsp;&nbsp;<a id="loginId" href="Link.do?method=sourceLoginCredentialsPage" >SFDC Source Login</a><br>
        &nbsp;&nbsp;<a id="loginId" href="Link.do?method=destinationLoginCredentialsPage" >SFDC Destination Login</a><br>
        &nbsp;&nbsp;<a href="Link.do?method=describeMetaDataComponentsPage" >Generate Package</a><br>
        &nbsp;&nbsp;<a href="Link.do?method=UploadPage" >Upload Packages</a><br>
        &nbsp;&nbsp;<a href="Link.do?method=UploadPage" >Upload Packages as ZIP</a><br>
<!--        <a href="Link.do?method=costomObjects" >Custom Objects </a><br>-->
<!--        <a href="Link.do?method=profiles" >profiles</a>-->
        </form>
    </body>
</html>
