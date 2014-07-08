<%-- 
    Document   : metaData
    Created on : 14 Jun, 2014, 5:28:09 PM
    Author     : karthi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>  
<!--         <script src="../js/jquery.min.js"></script>-->
    <style>

  .odd{background-color: white;}

  .even{background-color: gray;}

</style>
    </head>
    <body>
        <!--        <select id='MetaDataComponents'>
            <option value='select'>--select--</option>
        </select>​-->
<table>
<tr><td>
<div id="divContainerInBody" class="cp" style="width: 660px;overmargin-left: auto ;margin-right:inherit;height: 300px; border: 4px dashed rgb(30, 69, 138); background-color: rgb(229, 237, 250); padding: 18px; color: rgb(0, 33, 87); font-size: inherit; font-weight: inherit; font-family: inherit; font-style: inherit; text-decoration: inherit; border-radius: 8px; box-shadow: 0px 0px 10px 0px rgb(51, 51, 51);" align="center">
  <div id="divRedirectToSRCLogin">Login Required Before Component selection Click on this link <a href="Link.do?method=sourceLoginCredentialsPage">SFDC Source Login</a></div>
  <div id="divShowComponent">
    Select Component:
    
    <select id="MetaDataComponents" class="select">
    <option value="">--select--</option>
    <option value="AccountSharingRules">AccountSharingRules</option>
    <option value="AnalyticSnapshot">AnalyticSnapshot</option>
    <option value="ApexClass">ApexClass</option>
    <option value="ApexComponent">ApexComponent</option>
    <option value="ApexPage">ApexPage</option>
    <option value="ApexTrigger">ApexTrigger</option>
    <option value="AppMenu">AppMenu</option>
    <option value="ApprovalProcess">ApprovalProcess</option>
    <option value="AssignmentRules">AssignmentRules</option>
    <option value="AuthProvider">AuthProvider</option>
    <option value="AutoResponseRules">AutoResponseRules</option>
    <option value="CallCenter">CallCenter</option>
    <option value="CampaignSharingRules">CampaignSharingRules</option>
    <option value="CaseSharingRules">CaseSharingRules</option>
    <option value="Community">Community</option>
    <option value="ConnectedApp">ConnectedApp</option>
    <option value="ContactSharingRules">ContactSharingRules</option>
    <option value="CustomApplication">CustomApplication</option>
    <option value="CustomApplicationComponent">CustomApplicationComponent</option>
    <option value="CustomLabels">CustomLabels</option>
    <option value="CustomObject">CustomObject</option>
    <option value="CustomObjectSharingRules">CustomObjectSharingRules</option>
    <option value="CustomObjectTranslation">CustomObjectTranslation</option>
    <option value="CustomPageWebLink">CustomPageWebLink</option>
    <option value="CustomSite">CustomSite</option>
    <option value="CustomTab">CustomTab</option>
    <option value="Dashboard">Dashboard</option>
    <option value="DataCategoryGroup">DataCategoryGroup</option>
    <option value="Document">Document</option>
    <option value="EmailTemplate">EmailTemplate</option>
    <option value="EscalationRules">EscalationRules</option>
    <option value="FlexiPage">FlexiPage</option>
    <option value="Flow">Flow</option>
    <option value="Group">Group</option>
    <option value="HomePageComponent">HomePageComponent</option>
    <option value="HomePageLayout">HomePageLayout</option>
    <option value="InstalledPackage">InstalledPackage</option>
    <option value="Layout">Layout</option>
    <option value="LeadSharingRules">LeadSharingRules</option>
    <option value="Letterhead">Letterhead</option>
    <option value="OpportunitySharingRules">OpportunitySharingRules</option>
    <option value="PermissionSet">PermissionSet</option>
    <option value="PostTemplate">PostTemplate</option>
    <option value="Profile">Profile</option>
    <option value="Queue">Queue</option>
    <option value="QuickAction">QuickAction</option>
    <option value="RemoteSiteSetting">RemoteSiteSetting</option>
    <option value="Report">Report</option>
    <option value="ReportType">ReportType</option>
    <option value="Role">Role</option>
    <option value="SamlSsoConfig">SamlSsoConfig</option>
    <option value="Scontrol">Scontrol</option>
    <option value="Settings">Settings</option>
    <option value="SharingSet">SharingSet</option>
    <option value="SiteDotCom">SiteDotCom</option>
    <option value="StaticResource">StaticResource</option>
    <option value="SynonymDictionary">SynonymDictionary</option>
    <option value="UserSharingRules">UserSharingRules</option>
    <option value="Workflow">Workflow</option>
</select>


<!--    <div id="dataSelect" name="dataSelect" style="width: 410px;overmargin-left: auto ;margin-right:inherit;height: 295px;">-->
    <div id="divMainComponentDataSelect" name="divMainComponentDataSelect" class="CSSTableGenerator" style="display:none">
    </div>
    <div id="divSubComponentDataSelectAtributes" name="divSubComponentDataSelectAtributes" class="CSSTableGenerator" style="display:none">
    </div>
    <div id="divXmlDataDisplay" name="divXmlDataDisplay" class="CSSTableGenerator" style="display:none">
    </div>
    <div  style="visibility:hidden" id="genaratePackageCntDiv" name="genaratePackageCntDiv">
    </div>
<!--<br>-->
<input class="css_button" type="button" id="genaratePackageCnt" name="genaratePackageCnt"  value="append" style="display:none">
<input class="css_button" type="button" id="genaratePackage" name="genaratePackage"  value="genaratePackage" style="display:none">
<input class="css_button" type="button" id="hideSecondDiv" name="hideSecondDiv"  value="hideSecondDiv" align="right" style="display:none">
<!--<input class="css_button" type="button" id="downloadFileActionBTN" name="downloadFileActionBTN"  value="download Package" align="right">-->
<div id="divDownloadFileFromServer" style="display:none">Download file from server -
<html:link action="/DownloadIt">download package</html:link>
</div>
</div>
</div>
</td>
<td>
<div id="metaAtr" style="overmargin-left: auto ;width:170px;height:350px;border-radius: 8px; box-shadow: -1px 0px 3px 3px rgb(51, 51, 51) inset;border:1px #1e458a groove;">
				</div>
			
    </td></tr>
</table>

    </body>
    <script>
        var sUserName ='<%=session.getAttribute("SuserName")%>';

    $(document).ready(function() {
        if(sUserName=='null')
        {
            $('#divShowComponent').css('display','none');
        }
        else{
            $('#divRedirectToSRCLogin').css('display','none');
                    }
    });
        //href='javascript:show_more_menu();'
function show_more_subAtr(subMetaAtrVal)
{
 var componentType=$("#MetaDataComponents").val();
 var componentName=$('input[name="obj"]:checked').val();
 var componentAtr=subMetaAtrVal;
//$('input[name="obj"]:checked').each(function() {
// subMetaName=this.value;
//});
$('#divMainComponentDataSelect').css('display','none');
$(document).ready(function() {
 var ur="http://localhost:8080/Link.do?method=metaDataAttributeCheckBox&componentType="+componentType+"&componentName="+componentName+"&componentAtr="+componentAtr;
$.getJSON(ur,{id: $(this).val(), ajax: 'true'}, function(j){
        var htmlTxt='<table cellspacing="0"><tbody><tr><td>Serial No</td><td>Select</td><td>'+componentAtr+'</td></tr>';
   var metaAtrTable='';
   $.each(j.metaDataAttributeCheckBox,function(i,obj){
    var rNo=(i+1);
                var chkBx="<input name='obj' id='chk_" +i + "' type='checkbox' value='" + obj + "'/>"
                var lnk="<a id='mainRef' href='#' onclick=show_more_menu('"+obj+"')>"+obj+"</a>";
               htmlTxt=htmlTxt+"<tr><td>"+rNo+"</td><td>"+chkBx+"</td><td>"+lnk+"</td><tr>";
  }); 
         htmlTxt=htmlTxt+'</tbody></table>';
  //alert(metaAtrTable);
   $('#divSubComponentDataSelectAtributes').append(htmlTxt);
//   $('#hideSecondDiv').append(htmlTxt);
   $('#divSubComponentDataSelectAtributes').css('display','');
$('#hideSecondDiv').css('display','');
    })});
//alert(mainMetaTyp+"--"+subMetaName+"--"+subMetaAtr);
}
function show_more_menu(val)
{
   alert("val is --->"+val);
  $(document).ready(function() {

//  alert($(this).val());
//alert(document.getElementById("mainRef").value)
    });


}
    $(document).ready(function() {
$( "#hideSecondDiv" ).click(function() {
    $('#divSubComponentDataSelectAtributes').css('display','none');
    $('#divMainComponentDataSelect').css('display','');
});


  $("#downloadFileActionBTN").click(function(){
//alert(text);
  
  var ur="http://localhost:8080/Link.do?method=downloadFileAction";

  $.ajax({url:ur,success:function(result){
          //alert(result);
   // $("#d1").html(result);

    }});
        });
//dynamic combo loading code end
$( "#displaySecondDiv" ).click(function() {

$('#divMainComponentDataSelect').css('display','none');

$('#genaratePackageCnt').css('display','');
$('#genaratePackage').css('display','');
});

$( "#MetaDataComponents" ).change(function() {
    $("#divMainComponentDataSelect").empty();
    $("#metaAtr").empty();
    var selval=$("select#MetaDataComponents").val();
    if(selval!='')
    {
    //listParticularMetaComponentwithCheckBox
  var ur="http://localhost:8080/SFMigration/Link.do?method=metaDataCheckBox&componentType="+selval;
 alert(ur);
$.getJSON(ur,{id: $(this).val(), ajax: 'true'}, function(j){
        var htmlTxt='<table cellspacing="0"><tbody><tr><td width="7">Serial No</td><td width="5">Select</td><td width="30">'+selval+'</td></tr>';
        var k='';
        //alert(j.metaDataCheckBox.jsonObjComntAtr);
     for (var i = 0, result1 = j.metaDataCheckBox.jsonObjComnt; i < result1.length; i++) {
                var rNo=(i+1);
                if(rNo % 2 == 0)
{
  //alert ('is even');
   var chkBx="<input name='obj' id='chk_" +i + "' type='checkbox' value='" + result1[i] + "'/>"
                var lnk="<a id='mainRef' href='#' onclick=show_more_menu('"+result1[i]+"')>"+result1[i]+"</a>";
               htmlTxt=htmlTxt+"<tr><td>"+rNo+"</td><td>"+chkBx+"</td><td>"+lnk+"</td><tr>";
}
else
{
  //alert('is odd');
   var chkBx="<input name='obj' id='chk_" +i + "' type='checkbox' value='" + result1[i] + "'/>"
                var lnk="<a id='mainRef' href='#' onclick=show_more_menu('"+result1[i]+"')>"+result1[i]+"</a>";
               htmlTxt=htmlTxt+"<tr><td class='odd' align='center'>"+rNo+"</td><td class='odd' align='center'>"+chkBx+"</td><td class='odd'>"+lnk+"</td><tr>";
}
               
              }
     htmlTxt=htmlTxt+'</tbody></table>';
   $('#divMainComponentDataSelect').append(htmlTxt);
   var metaAtrTxt='';
//     for (var i1 = 0, result11 = j.metaDataCheckBox.jsonObjComntAtr; i1 < result1.length; i1++) {
//                var rNo=(i+1);
//                var lnk1="<a id='mainRef' href='#' onclick=show_more_menu('"+result11[i1]+"')>"+result11[i1]+"</a>";
//               metaAtrTxt=metaAtrTxt+"&nbsp"+rNo+"&nbsp"+lnk1+"</br>";
//            }
//alert(j.metaDataCheckBox.jsonObjComntAtr);
   $.each(j.metaDataCheckBox.jsonObjComntAtr,function(i,obj){
//    $("div").html("<p>Symbol="+obj.Symbol+" Time="+obj.Time+" Bid="+obj.Bid+"</p>");

       var rNo=(i+1);
                var lnk1="<a id='mainRef' href='#' onclick=show_more_subAtr('"+obj+"')>"+obj+"</a>";
               metaAtrTxt=metaAtrTxt+"&nbsp"+rNo+"&nbsp"+lnk1+"</br>";
  });
//  alert(metaAtrTxt);
   $('#metaAtr').append(metaAtrTxt);
//   $('#builder-model').show();
   //builder-model
//   $("#builder-model").css("display","none"); 
   $('#divMainComponentDataSelect').css('display','');
   $('#genaratePackageCnt').css('display','');
  
    })
    }

});
 $("#genaratePackageCnt").click(function() {
loadedRecords = {}
 $('#genaratePackageCntDiv').append($('#MetaDataComponents').val()+"@@@@");
 //alert($('input[name="obj"]:checked').length)
 var len=$('input[name="obj"]:checked').length;
 if(len<=0)
     {
         alert("Please Select Atleast One Component");
return false;
     }
$('input[name="obj"]:checked').each(function() {
   //loadedRecords.push(this.value);
 //loadedRecords.push(this.value,this.value);
  //alert(this.value);
  if(len==1)
      {
  $('#genaratePackageCntDiv').append(this.value+"@@@@");
      }
      else{
  $('#genaratePackageCntDiv').append(this.value+"@@@");
  len--;
      }

});

$("#divMainComponentDataSelect").empty();
 $('#genaratePackage').css('display','');
    });

  $("#genaratePackage").click(function(){
var text = $('#genaratePackageCntDiv').text();
//alert(text);
  var ur="http://localhost:8080/SFMigration/Link.do?method=generatePackageWithDivText&divText="+text;
  $.ajax({url:ur,success:function(result){
          //alert(result);
   // $("#d1").html(result);

    $('#divDownloadFileFromServer').css('display','');
    $('#divMainComponentDataSelect').css('display','none');
    $('#divXmlDataDisplay').css('display','');
    $("#divXmlDataDisplay").text(result);

    }});
        });
  $("#subLevel").click(function(){

//  alert("came inside");

  var ur="http://localhost:8080/SFMigration/Link.do?method=getMetadataSublevel&metaMainId=Account&metaMainTyp"+$('#MetaDataComponents').val();
//  var ur="http://124.123.200.2:8084/SFMigration/Link.do?method=getMetadataSublevel&metaMainId=Account&metaMainTyp"+$('#MetaDataComponents').val();
  $.ajax({url:ur,success:function(result){
  //        alert(result);
   // $("#d1").html(result);
    }});
        });
$('subDataLink').click( function() {
   // alert("sssssssss");
   return false;


});

$('a').click(function(){
  // alert($(this).text());
})




});
       </script>
</html>
