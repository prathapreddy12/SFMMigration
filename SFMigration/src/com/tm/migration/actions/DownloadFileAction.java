/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tm.migration.actions;

/**
 *
 * @author karthi
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class DownloadFileAction extends Action{

   @Override
   public ActionForward execute(ActionMapping mapping, ActionForm form,
     HttpServletRequest request, HttpServletResponse response)
     throws Exception {
     //return an application file instead of html page
     //response.setContentType("application/octet-stream");
       response.setHeader("Content-Type", "application/xml;charset=utf-8");
  response.setHeader("Content-Encoding", "UTF-8");
//  response.setHeader("Content-Length", "" + xml_UTF_8.length() /*data.length ???*/);
     response.setHeader("Content-Disposition","attachment;filename=package.xml");
     try
     {
        HttpSession session = request.getSession(true);
        String userName = session.getAttribute("SuserName").toString();
        String userPWD = session.getAttribute("SuserPWD").toString();
       	//Get it from file system
       	FileInputStream in =
      		new FileInputStream(new File("SFDC\\" + userName + "\\package.xml"));
//      		new FileInputStream(new File("D:\\superfish.zip"));

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
