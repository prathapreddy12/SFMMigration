/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.migration.actions;

import com.sforce.soap.enterprise.wsc.GetUserInfoResult;

import com.tm.migration.SFDCConnections.FileBasedDeployAndRetrieve;
import com.tm.migration.SFDCConnections.MetadataLoginUtil;
import com.tm.migration.componentinfo.ComponentAttributes;
import com.tm.migration.xml.WriteXMLFile;
import com.tm.migration.xml.XmlParser;
import com.tm.migration.zip.UnZip;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.json.JSONArray;
import org.json.JSONObject;
//session.setAttribute("metadataConnectionSesAtr",new MetadataLoginUtil().login(duserName, SuserPWD));
/**
 *
 * @author sateesh
 */
public class LinkAction extends DispatchAction {
    //to retrive the sub level data
    //to retrive sublevel data
    //tasks package.xml construction,retrive function,unzip function ,json construction
    public synchronized void getMetadataSublevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("came inside");
        HttpSession session = request.getSession(true);
        String userName = session.getAttribute("SuserName").toString();
        String userPWD = session.getAttribute("SuserPWD").toString();
        System.out.println("username" + userName);
        System.out.println("userPwd" + userPWD);
        String metaMainId = request.getParameter("metaMainId");
        String metaMainTyp = request.getParameter("metaMainTyp");
        WriteXMLFile xmlWrite = new WriteXMLFile();
//                xmlWrite.getMetadataSublevelBL(metaMainTyp,metaMainId);
        new WriteXMLFile().getMetadataSublevelBL("CustomObject", "Account");
        FileBasedDeployAndRetrieve retriveObj = new FileBasedDeployAndRetrieve();
        retriveObj.retrieveZip(userName, userPWD);
        String INPUT_ZIP_FILE = userName+"\\components.zip";
        String OUTPUT_FOLDER = userName+"\\components\\";
        UnZip unZip = new UnZip();
        unZip.unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER);
        XmlParser xp = new XmlParser();
        String url = userName+"\\components\\unpackaged\\objects\\" + metaMainId + ".object";
        System.out.println("url is " + url);
        xp.getXmlDataByNode(url);
    }

//this function for main compoponents package constution
    public synchronized void generatePackageWithDivText(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(true);
        String userName = session.getAttribute("SuserName").toString();
        String userPWD = session.getAttribute("SuserPWD").toString();
        String divText = request.getParameter("divText");
        Map<String, ArrayList> map = StringSplit.StringSplCode(divText);
        WriteXMLFile xmlWrite = new WriteXMLFile();
        String XMLData=xmlWrite.writeData(userName, map);
        System.out.println("XMLData-------->"+XMLData);
         response.getWriter().write(XMLData);
//        System.out.println("divText is " + divText);
//        FileBasedDeployAndRetrieve retriveObj = new FileBasedDeployAndRetrieve();
//        retriveObj.retrieveZip(userName, userPWD);
    }
    public synchronized void afterUploadDeploy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("----------------------------came ----------");
        HttpSession session = request.getSession(true);
        String dUserName = session.getAttribute("dUserName").toString();
        String dUserPWD = session.getAttribute("dUserPWD").toString();
        String SuserName = session.getAttribute("SuserName").toString();
        String SuserPWD = session.getAttribute("SuserPWD").toString();
        System.out.println("SuserName======>" + SuserName);
        System.out.println("Spwd===========>" + SuserPWD);
        FileBasedDeployAndRetrieve retriveObj = new FileBasedDeployAndRetrieve();
        String status = retriveObj.retrieveZipAfterUpload(dUserName,dUserPWD,SuserName, SuserPWD);
        response.getWriter().write(status);
    }
//this function for salesforce login credential purpes and values setting in session

    public synchronized void srcLoginCredentialsSubmit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession(true);
        System.out.println("came to this fun11111111111");
        String SuserName = request.getParameter("sName");
        String SuserPWD = request.getParameter("sPWD");
        String duserName = request.getParameter("");
        String duserPwd = request.getParameter("");
        
        System.out.println("SuserName======>" + SuserName);
        System.out.println("Spwd===========>" + SuserName);
        try{
         GetUserInfoResult userinfoResult = MetadataLoginUtil.loginTest(SuserName, SuserPWD).getUserInfo();
        if(userinfoResult.getUserName().length()>1)
        {
            session.setAttribute("SuserName", SuserName);
            session.setAttribute("SuserPWD", SuserPWD);
            System.out.println("loginUser"+userinfoResult.getUserName());

        response.getWriter().write("success");
        }
        }
        catch(Exception ee){
            ee.printStackTrace();
        response.getWriter().write("failure");
        }
           }
    public synchronized void dstLoginCredentialsSubmit(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession(true);
        System.out.println("came to this fun11111111111");
        String dUserName = request.getParameter("dName");
        String dUserPWD = request.getParameter("dPWD");
        System.out.println("SuserName======>" + dUserName);
        System.out.println("Spwd===========>" + dUserPWD);
        try{
         GetUserInfoResult userinfoResult = MetadataLoginUtil.loginTest(dUserName, dUserPWD).getUserInfo();
        if(userinfoResult.getUserName().length()>1)
        {
            session.setAttribute("dUserName", dUserName);
            session.setAttribute("dUserPWD", dUserPWD);
            //session.setAttribute("dUserName", userinfoResult.getUserName());
            System.out.println("loginUser"+userinfoResult.getUserName());

        response.getWriter().write("success");
        }
        }
        catch(Exception ee){
            ee.printStackTrace();
        response.getWriter().write("failure");
        }
           }
//this function for loading salesforce login page

    public synchronized ActionForward sourceLoginCredentialsPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("sourceLoginCredentialsPage");
    }
    public synchronized ActionForward destinationLoginCredentialsPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("destinationLoginCredentialsPage");
    }
//this function for loading component page display

    public synchronized ActionForward describeMetaDataComponentsPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("describeMetaDataComponentsPage");
    }
//this function for loading component page display

    public synchronized ActionForward UploadPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("UploadPage");
    }
    //this function for to load salesforce main meta components

    public synchronized void describeMetaDataComponentsLoad(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("describeMetaDataComponentsLoad it is inside");
        HttpSession session = request.getSession();
        String userName = session.getAttribute("SuserName").toString();
        String userPWD = session.getAttribute("SuserPWD").toString();
        MetadataLoginUtil metaLoginUtil = new MetadataLoginUtil();
        try {
            JSONArray jsonAr = metaLoginUtil.describeMetadata(userName, userPWD);
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("describeMetaDataComponents", jsonAr);
            response.getWriter().write(jsonObj.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//this function will list the components what are available for particular component

    public synchronized void metaDataAttributeCheckBox(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jsonObj = new JSONObject();
        JSONObject jsonObjComntAtr = new JSONObject();
          try {
            HttpSession session = request.getSession();
            String userName = session.getAttribute("SuserName").toString();
            String userPWD = session.getAttribute("SuserPWD").toString();
            MetadataLoginUtil metaLoginUtil = new MetadataLoginUtil();
            String componentType = request.getParameter("componentType");
            String componentName = request.getParameter("componentName");
            String componentAtr = request.getParameter("componentAtr");
            System.out.println("component typr" + componentType);
            System.out.println("componentName" + componentName);
            System.out.println("componentAtr" + componentAtr);
           Map<String,ArrayList> map=new HashMap<String,ArrayList>();
           ArrayList lst=new  ArrayList();
           lst.add(componentName);
        map.put(componentType,lst);
        new WriteXMLFile().writeData(userName, map);
        FileBasedDeployAndRetrieve retriveObj = new FileBasedDeployAndRetrieve();
        retriveObj.retrieveZip(userName, userPWD);
        String INPUT_ZIP_FILE ="SFDC\\"+userName+"\\components.zip";
        String OUTPUT_FOLDER ="SFDC\\"+userName+"\\components\\";
        UnZip unZip = new UnZip();
        unZip.unZipIt(INPUT_ZIP_FILE, OUTPUT_FOLDER);
        XmlParser xp = new XmlParser();
        String url ="SFDC\\"+ userName+"\\components\\unpackaged\\objects\\"+componentName+".object";
        System.out.println("url is " + url);
        JSONArray jar=xp.getXmlDataByNodeBasedOnExpression(componentType,componentAtr,url);
              System.out.println("jar is "+jar);
        jsonObj.put("metaDataAttributeCheckBox",jar);
//            jsonObjComntAtr.put("jsonObjComnt",jsonAr);
//            jsonObj.put("metaDataCheckBox", jsonObjComntAtr);
        } catch (Exception e) {
            e.printStackTrace();
        }
//    MetaReadUtil mt=new MetaReadUtil();
//   JSONObject obj=mt.getCustomObjects();
//    response.getWriter().write(obj.toString());
        response.getWriter().write(jsonObj.toString());
    }
    public synchronized void metaDataCheckBox(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JSONObject jsonObj = new JSONObject();
        JSONObject jsonObjComntAtr = new JSONObject();
        try {
            HttpSession session = request.getSession();
            String userName = session.getAttribute("SuserName").toString();
            String userPWD = session.getAttribute("SuserPWD").toString();
            MetadataLoginUtil metaLoginUtil = new MetadataLoginUtil();
            String componentType = request.getParameter("componentType");
            System.out.println("component typr" + componentType);
            JSONArray jsonAr = new MetadataLoginUtil().listMetadata(userName,userPWD,componentType);
//            JSONArray jsonAr = new MetadataLoginUtil().listMetadata("sateesh.chava@techmahindra.com","abc123456Ii7BP2QsTX77zvchBQP4txc8","CustomObject");
            JSONArray jsonArForComponent = new ComponentAttributes().getComponentAttributes(componentType);

            System.out.println("------------------>jsonAr----"+jsonAr);
            System.out.println("------------------>"+jsonArForComponent);
            jsonObjComntAtr.put("jsonObjComntAtr",jsonArForComponent);
            jsonObjComntAtr.put("jsonObjComnt",jsonAr);
            jsonObj.put("metaDataCheckBox", jsonObjComntAtr);
        } catch (Exception e) {
            e.printStackTrace();
        }
//    MetaReadUtil mt=new MetaReadUtil();
//   JSONObject obj=mt.getCustomObjects();
//    response.getWriter().write(obj.toString());
        response.getWriter().write(jsonObj.toString());
    }
//    public void generatePackagesXML(JSONObject jsonObj, HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        System.out.println("came to this fun");
//        response.setContentType("application/json");
////        MetaReadUtil mt = new MetaReadUtil();
////        JSONObject obj = mt.getCustomObjects();
//        System.out.println(jsonObj);
//        response.getWriter().write("data writing completed");
//    }
//    public ActionForward profiles(ActionMapping mapping, ActionForm  form,
//            HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//
//        return mapping.findForward("office");
//    }
    //    public ActionForward basicLogin(ActionMapping mapping, ActionForm  form,HttpServletRequest request, HttpServletResponse response)throws Exception
//    {
//System.out.print("came to login");
//        //loginpage
//return mapping.findForward("loginpage");
//    }
  
   public ActionForward downloadFileAction(ActionMapping mapping, ActionForm form,
     HttpServletRequest request, HttpServletResponse response)
     throws Exception {
     //return an application file instead of html page
     response.setContentType("application/octet-stream");
     response.setHeader("Content-Disposition","attachment;filename=superfish.zip");
     try
     {
         System.out.println("came inside");
         
       	//Get it from file system
       	FileInputStream in =
      		new FileInputStream(new File("D:\\superfish.zip"));
        //Get it from web path
        //jndi:/localhost/StrutsExample/upload/superfish.zip
        //URL url = getServlet().getServletContext()
        //               .getResource("upload/superfish.zip");
        //InputStream in = url.openStream();

        //Get it from bytes array
        //byte[] bytes = new byte[4096];
        //InputStream in = new ByteArrayInputStream(bytes);
        ServletOutputStream out = response.getOutputStream();
        byte[] outputByte = new byte[4096];
        //copy binary content to output stream
        while(in.read(outputByte, 0, 4096) != -1){
        	out.write(outputByte, 0, 4096);
        }
        in.close();
        out.flush();
        out.close();
     }catch(Exception e){
    	e.printStackTrace();
   }
   return null;
  }
}
