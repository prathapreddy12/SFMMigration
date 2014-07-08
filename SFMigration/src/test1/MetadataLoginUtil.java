package test1;


import com.sforce.soap.enterprise.wsc.EnterpriseConnection;
import com.sforce.soap.enterprise.wsc.LoginResult;
import com.sforce.soap.metadata.wsc.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
/**
* Login utility.
*/

public class MetadataLoginUtil
{
public static MetadataConnection login() throws ConnectionException {
final String USERNAME = "sfdc.og3@sf.com";
// This is only a sample. Hard coding passwords in source files is a bad practice.
final String PASSWORD = "salesforceog3RJqg2jVLU8d9LFWeU4dqQJygx";
final String URL = "https://login.salesforce.com/services/Soap/c/30.0";
final LoginResult loginResult = loginToSalesforce(USERNAME, PASSWORD, URL);
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



public static void main(String args[])
{
	try{
	MetadataLoginUtil.login();
	System.out.println("came");
	}
	catch(Exception e)
	{
		
		e.printStackTrace();
	}
	//MetadataLoginUtil ut=new MetadataLoginUtil();
       

}



}