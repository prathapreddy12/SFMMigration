/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tm.migration.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author karthi
 */
public class StringSplit {
public static Map StringSplCode(String totalString)
    {
      Map<String,ArrayList> map=new HashMap<String,ArrayList> ();
        String totalString1 = "CustomObject@@@@OrderNo__c@@@Opportunity@@@SalesOrder__c@@@@Profile@@@@Partner Community User@@@ContractManager@@@@";
        String str1[] = totalString.split("@@@@");
        System.out.println("STR1" + str1.length);
        String storeKey=null;
        for (int i = 0; i <= str1.length - 1; i++) {
            if ((i % 2) == 0) {
                // even
                System.out.print("even");
                System.out.println("-------->" + str1[i]);
                storeKey = str1[i];
            } else {
                System.out.print("odd");
                String strvalAr[]=str1[i].split("@@@");
               ArrayList subValList=new ArrayList();
                for(int ii = 0; ii <= strvalAr.length - 1; ii++) {
                subValList.add(strvalAr[ii].trim());
                }
                System.out.println("-------->" + str1[i]);
                map.put(storeKey.trim(),subValList);
                storeKey=null;
                //strVal=str1[i].replaceAll("@@@",",");
//             Astr1[i].split(",");
                       }
        }
        System.out.println("map is "+map);
return map;
}
}
