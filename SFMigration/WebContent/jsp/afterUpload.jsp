<%-- 
    Document   : display
    Created on : 1 Jul, 2014, 11:48:17 AM
    Author     : karthi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
            <script type="text/javascript">
var dUserName ='<%=session.getAttribute("dUserName")%>';
    $(document).ready(function() {
        if(dUserName=='null')
        {
           // $('#divShowUpload').css('display','none');

        }
        else{
            $('#divRedirectToDSTLogin').css('display','none');
            $('#dusername').html("You are uploading package with this user : <b>"+dUserName+"</b>");
            $('#divDeploy').html("Click on this link to Deploy uploaded package : <b><a id='anchorDeploy' class='css_button'>Deploy</a></b>");

        }
        $("#anchorDeploy" ).click(function(){
            //alert("came");
              var ur="localhost:8080/SFMigration/Link.do?method=afterUploadDeploy";
              $.ajax({url:ur,success:function(result){
                if(result=='success')
                {
                     $("#divDeploySts").html("The files were successfully deployed");
                    $('#divDeploySts').css({"color":"green"});
                }
                else{

                     $("#divDeploySts").html("The files were not successfully deployed");
                     $('#divDeploySts').css({"color":"red"});
                    
                }
                // alert(result);
                //    $("#d1").html(result);
            }});
        });
    });
</script>
    </head>
    <body>
</br>
</br>
</br>
      &nbsp;<div id="dusername"> </div>
      &nbsp;<div id="divDeploy"> </div>
      </br>
      &nbsp;<div id="divDeploySts"> </div>

    </body>
</html>
