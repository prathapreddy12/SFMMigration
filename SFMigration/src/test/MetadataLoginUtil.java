package test;


import com.sforce.soap.enterprise.wsc.EnterpriseConnection;
import com.sforce.soap.enterprise.wsc.LoginResult;
import com.sforce.soap.metadata.wsc.DescribeMetadataObject;
import com.sforce.soap.metadata.wsc.DescribeMetadataResult;
import com.sforce.soap.metadata.wsc.FileProperties;
import com.sforce.soap.metadata.wsc.ListMetadataQuery;
import com.sforce.soap.metadata.wsc.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
/**
* Login utility.
*/
    
public class MetadataLoginUtil
{
MetadataConnection metadataConnection=null;
    public static LoginResult loginTest(String userName,String PWD) throws ConnectionException {
      String URL = "https://login.salesforce.com/services/Soap/c/30.0";
      https://www.salesforce.com
return  loginToSalesforce(userName,PWD,URL);
}
public static MetadataConnection login(String userName,String password) throws ConnectionException {
        //final String USERNAME ="sateesh.chava@techmahindra.com";
	//final String PASSWORD ="abc123456gi1U5SXhMzrt1WDRF9PU4yo8";
final String URL = "https://login.salesforce.com/services/Soap/c/30.0";
final LoginResult loginResult = loginToSalesforce(userName, password, URL);
return createMetadataConnection(loginResult);
}
private static MetadataConnection createMetadataConnection(
final LoginResult loginResult) throws ConnectionException {
final ConnectorConfig config = new ConnectorConfig();
config.setServiceEndpoint(loginResult.getMetadataServerUrl());
config.setSessionId(loginResult.getSessionId());
return new MetadataConnection(config);
}
private static LoginResult loginToSalesforce(
final String username,
final String password,
final String loginUrl) throws ConnectionException {
final ConnectorConfig config = new ConnectorConfig();
config.setAuthEndpoint(loginUrl);
config.setServiceEndpoint(loginUrl);
config.setManualLogin(true);
return (new EnterpriseConnection(config)).login(username, password);
}
public JSONArray describeMetadata(String userName,String pwd) {
     List<String> jsonValues = new ArrayList<String>();
    try {
this.metadataConnection = MetadataLoginUtil.login(userName,pwd);
double apiVersion = 30.0;
// Assuming that the SOAP binding has already been established.
DescribeMetadataResult res =metadataConnection.describeMetadata(apiVersion);

StringBuffer sb = new StringBuffer();
if(res != null && res.getMetadataObjects().length > 0) {
for(DescribeMetadataObject obj : res.getMetadataObjects()) {
   jsonValues.add(obj.getXmlName());
     //jsonValues.add(n.getFullName());
}
} else {
sb.append("Failed to obtain metadata types.");
}
System.out.println(sb.toString());
} catch (ConnectionException ce) {
ce.printStackTrace();
}

    Collections.sort(jsonValues);
JSONArray metadataJsonArray = new JSONArray(jsonValues);
return metadataJsonArray;
}
    public JSONArray listMetadata(String userName, String pwd, String componentTYP) {
       List<String> jsonValues = new ArrayList<String>();
        try {
            ListMetadataQuery query = new ListMetadataQuery();
            query.setType(componentTYP.trim());
            double asOfVersion =30.0;
            System.out.println("userName-"+userName+"-");
            System.out.println("pwd-"+pwd+"-");
            System.out.println("componentTYP-"+componentTYP+"-");
            FileProperties[] lmr = MetadataLoginUtil.login(userName.trim(), pwd.trim()).listMetadata(
                    new ListMetadataQuery[]{query}, asOfVersion);
            if (lmr != null) {
                for (FileProperties n : lmr) {
//                    metadataJsonArray.put(n.getFullName());
                    jsonValues.add(n.getFullName());
                   System.out.println("FUL NAME IS ---------->"+n.getFullName());
                }
            }
        } catch (ConnectionException ce) {
            ce.printStackTrace();
        }
Collections.sort(jsonValues);
JSONArray metadataJsonArray = new JSONArray(jsonValues);
        return metadataJsonArray;
    }
    public static void main(String args[])
    {
    new MetadataLoginUtil().listMetadata("sateesh.chava@techmahindra.com","abc123456Ii7BP2QsTX77zvchBQP4txc8","ApexTrigger");

    }
}