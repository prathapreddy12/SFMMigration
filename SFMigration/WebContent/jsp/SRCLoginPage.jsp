<%--
    Document   : loginPage
    Created on : 14 Jun, 2014, 2:16:41 PM
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
<!--<script src="../js/jquery.min.js"></script>-->



        <script>
    $(document).ready(function() {

    //change paragraph text colour to green

  $("#credentialBTN").click(function(){   //alert("   ");
  //$('#credentialBTN').css({"color":"green"});

 $('#credentialBTN').removeClass('css_button').addClass('css_buttonDisable');
 $("#credentialBTN").attr('value', 'Connecting ....');
 $('#credentialBTN').attr("disabled", true);
//  $('jQuery selector').css({"css property name":"css_buttonDisable"});
  var sName=$("#sourceUserName").val();
      var sPWD=$("#SourcePWD").val();
      var dName=$("#destinationUserName").val();
      var dPWD=$("#destinationPWD").val();
  var ur="http://localhost:8080/SFMigration/Link.do?method=srcLoginCredentialsSubmit&sName="+sName+"&sPWD="+sPWD;
  $.ajax({url:ur,success:function(result){
          if(result=='success')
              {
           $("#credentialBTN").attr('value', 'Connection Successful');
          $('#credentialBTN').css({"color":"green"});
              }
              else{
           $("#credentialBTN").attr('value', 'Invalid Credential');
              }
         // alert(result);
//    $("#d1").html(result);
    }});
        });
});
</script>

    </head>
    <body>

        <form name="loginFM" method="post" action="" class="wufoo  page" accept-charset="UTF-8" autocomplete="off" enctype="multipart/form-data" method="post" novalidate>



<!--SalesforceLogin -->

</br>
</br>
</br>
</br>

<div>
<div id="srcTitle" align="center" title="Source Login" >Source Login</div></br>
            <div id="builder-model" class="cp" style="width: 400px;margin-left: auto ; margin-right: auto ;height: 127px; border: 4px dashed rgb(30, 69, 138); background-color: rgb(229, 237, 250); padding: 18px; color: rgb(0, 33, 87); font-size: inherit; font-weight: inherit; font-family: inherit; font-style: inherit; text-decoration: inherit; border-radius: 8px; box-shadow: 0px 0px 10px 0px rgb(51, 51, 51);" align="center">

                <table>
                    <tr>

                        <td>User Name</td>
                        <td>
                            <input class="text" type="text" id="sourceUserName" name="sourceUserName" value="sateesh.chava@techmahindra.com">
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input class="text" type="text" id="SourcePWD" name="SourcePWD" value="abc123456Ii7BP2QsTX77zvchBQP4txc8"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            </br>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input class="css_button" type="button" id="credentialBTN" name="credentialBTN" value="login" /> </td>
                    </tr>
                </table>

<div id="d1" name="d1"></div>

            </div>


           </div>
        </form>
    </body>
</html>
