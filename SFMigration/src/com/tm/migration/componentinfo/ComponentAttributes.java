/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tm.migration.componentinfo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author karthi
 */
public class ComponentAttributes {

    public synchronized JSONArray getComponentAttributes(String component)
    {

        JSONArray jsonAr=null;

        if("CustomObject".equalsIgnoreCase(component))
        {
            jsonAr=new JSONArray();
        jsonAr.put("fields");
        jsonAr.put("recordTypes");
        jsonAr.put("listViews");
        jsonAr.put("validationRules");
        jsonAr.put("validationRules");
        jsonAr.put("webLinks");
        }
 else if("Profile".equalsIgnoreCase(component))
        {
      jsonAr=new JSONArray();
        jsonAr.put("Profile");

        }
 else if("ApexComponent".equalsIgnoreCase(component))
        {

        }
 else if("ApexPage".equalsIgnoreCase(component))
        {

        }
 else if("AppMenu".equalsIgnoreCase(component))
        {

        }
 else if("ApprovalProcess".equalsIgnoreCase(component))
        {

        }
 else if("ConnectedApp".equalsIgnoreCase(component))
        {

        }
 else if("CustomApplication".equalsIgnoreCase(component))
        {

        }
 else if("Role".equalsIgnoreCase(component))
        {
   jsonAr=new JSONArray();
        jsonAr.put("Role");

        }
 else if("Workflow".equalsIgnoreCase(component))
        {
   jsonAr=new JSONArray();
        jsonAr.put("Workflow");

        }


        return jsonAr;
    }


}
