/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tm.migration.actions;

/**
 *
 * @author karthi
 */
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

public class FileUploadForm extends ActionForm{

	private FormFile file;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
	   HttpServletRequest request) {

	    ActionErrors errors = new ActionErrors();

	    if( getFile().getFileSize()== 0){
	       errors.add("common.file.err",
	    	new ActionMessage("error.common.file.required"));
	       return errors;
	    }

//	    //only allow textfile to upload
//	    if(!"text/plain".equals(getFile().getContentType())){
//	        errors.add("common.file.err.ext",
//	    	 new ActionMessage("error.common.file.textfile.only"));
//	        return errors;
//	    }
//            System.out.println("getFile().getContentType()       ->"+getFile().getContentType());
//	    if(!"application/xml".equals(getFile().getContentType())){
//	        errors.add("common.file.err.ext",
//	    	 new ActionMessage("error.common.file.textfile.only"));
//	        return errors;
//	    }
	    //only allow textfile to upload
//	    if(!"text/xml".equals(getFile().getContentType())){
//	        errors.add("common.file.err.ext",
//	    	 new ActionMessage("error.common.file.xmlfile.only"));
//	        return errors;
//	    }
//	    if(!"application/zip".equals(getFile().getContentType())){
//	        errors.add("common.file.err.ext",
//	    	 new ActionMessage("error.common.file.xmlfile.only"));
//	        return errors;
//	    }
//application/zip
            //file size cant larger than 10kb
	    System.out.println(getFile().getFileSize());
	    if(getFile().getFileSize() > 10240){ //10kb
	       errors.add("common.file.err.size",
		    new ActionMessage("error.common.file.size.limit", 10240));
	       return errors;
	    }

	    return errors;
	}

}
