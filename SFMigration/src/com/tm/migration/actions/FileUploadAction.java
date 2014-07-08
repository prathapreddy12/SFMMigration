/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tm.migration.actions;

/**
 *
 * @author karthi
 */
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class FileUploadAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
            HttpSession session = request.getSession(true);
            String userName = session.getAttribute("dUserName").toString();
            String userPWD = session.getAttribute("dUserPWD").toString();
            FileUploadForm fileUploadForm = (FileUploadForm) form;
	    FormFile file = fileUploadForm.getFile();
            System.out.println("duser name is ---------->"+userName);
	    //Get the servers upload directory real path name
//	    String filePath =
//               getServlet().getServletContext().getRealPath("/") +"upload";
           String filePath ="SFDC\\" + userName + "\\upload";
	    //create the upload folder if not exists
	    File folder = new File(filePath);
	    if(!folder.exists()){
	    	folder.mkdirs();
	    }

	    String fileName = file.getFileName();

	    if(!("").equals(fileName)){

	        System.out.println("Server path:" +filePath);
	        File newFile = new File(filePath, fileName);

	        if(!newFile.exists()){
	          FileOutputStream fos = new FileOutputStream(newFile);
	          fos.write(file.getFileData());
	          fos.flush();
	          fos.close();
	        }

	        request.setAttribute("uploadedFilePath",newFile.getAbsoluteFile());
	        request.setAttribute("uploadedFileName",newFile.getName());
	    }
		return mapping.findForward("success");
	}
}
