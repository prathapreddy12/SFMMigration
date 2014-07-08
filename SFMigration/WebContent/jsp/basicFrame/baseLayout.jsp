<%-- 
    Document   : baseLayout
    Created on : Dec 19, 2008, 1:28:41 AM
    Author     : eswar@vaannila.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><tiles:getAsString name="title" ignore="true" /></title>
            <link rel="stylesheet" type="text/css" href="./css/style1.css">
    </head>
    <body>
<div class="content">
		<div class="top_block title">
			<div class="content">
                             <tiles:insert attribute="header" ignore="true" />
			</div>
		</div>
		<div class="background leftMenu">
		</div>
		<div class="left_block leftMenu">
			<div class="content">
                             <tiles:insert attribute="menu" />
			</div>
		</div>
		<div class="background body">
		</div>
		<div class="center_block body">
			<div class="content">
                             <tiles:insert attribute="body" />
			</div>
		</div>
		<div class="bottom_block footer">
			<div class="content">
                             <tiles:insert attribute="footer" />
			</div>
		</div>
	</div>