<%--
    Document   : fileupload
    Created on : 1 Jul, 2014, 11:35:15 AM
    Author     : karthi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript">
var dUserName ='<%=session.getAttribute("dUserName")%>';

    $(document).ready(function() {
        if(dUserName=='null')
        {
            $('#divShowUpload').css('display','none');

        }
        else{

            $('#divRedirectToDSTLogin').css('display','none');
            $('#dusername').html("You are uploading package with this user : <b>"+dUserName+"</b>");

        }
    });
</script>
</head>
<body>

<!--<h1><bean:message key="label.common.title" /></h1>-->
<!--<h1><!=session.getAttribute("name")></h1>-->

<html:messages id="err_name" property="common.file.err">
<div style="color:red">
	<bean:write name="err_name" />
</div>
</html:messages>
<html:messages id="err_name" property="common.file.err.ext">
<div style="color:red">
	<bean:write name="err_name" />
</div>
</html:messages>
<html:messages id="err_name" property="common.file.err.size">
<div style="color:red">
	<bean:write name="err_name" />
</div>
</html:messages>
<html:form action="/Upload" method="post" enctype="multipart/form-data">
    </br>
  <div id="srcTitle" align="center" title="Source Login" >Upload Package As XML</div>
 </br>
 </br>
<div id="dusername" align="center"></div>
</br></br>
   <div id="builder-model" class="cp" style="width: 450px;margin-left: auto ; margin-right: auto ;height: 127px; border: 4px dashed rgb(30, 69, 138); background-color: rgb(229, 237, 250); padding: 18px; color: rgb(0, 33, 87); font-size: inherit; font-weight: inherit; font-family: inherit; font-style: inherit; text-decoration: inherit; border-radius: 8px; box-shadow: 0px 0px 10px 0px rgb(51, 51, 51);" align="center">
       <div id="divRedirectToDSTLogin">Login Required  Before Upload  Click on this link <a href="Link.do?method=destinationLoginCredentialsPage">SFDC Destination Login</a></div>
  <div id="divShowUpload">
      
       <table>
           <tr><td><bean:message key="label.common.file.label"/>:</td><td><html:file property="file" size="30" /></td></tr>
           <tr><td></td><td></td></tr>
           <tr><td></td><td></td></tr>
           <tr><td></td><td></td></tr>
           <tr><td></td><td></br></br></td></tr>
           <tr><td>&nbsp;</td><td><html:submit property="css_button" styleClass="css_button">
       <bean:message key="label.common.button.submit" />
   </html:submit></td></tr>
   
       </table>

            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            
            </br>
            </br>
            </br>
            
      </div>
</div>

</html:form>


</body>
</html>