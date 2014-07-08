package com.tm.migration.SFDCConnections;
import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import com.sforce.soap.metadata.wsc.*;
import com.sforce.soap.enterprise.wsc.*;
import com.sforce.soap.metadata.wsc.*;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
/**
* Sample that logs in and shows a menu of retrieve and deploy metadata options.
*/
public class FileBasedDeployAndRetrieve
{
private MetadataConnection metadataConnection;
private static final String ZIP_FILE = "components.zip";
// manifest file that controls which components get retrieved
private static final String MANIFEST_FILE = "package.xml";
private static final double API_VERSION = 29.0;
//one second in milliseconds
private static final long ONE_SECOND = 1000;
//maximum number of attempts to deploy the zip file
private static final int MAX_NUM_POLL_REQUESTS = 50;
private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
public static void main(String[] args) throws Exception {
FileBasedDeployAndRetrieve sample = new FileBasedDeployAndRetrieve();
//sample.run();
sample.retrieveZip("sateesh.chava@techmahindra.com","abc123456gi1U5SXhMzrt1WDRF9PU4yo8");
}
public FileBasedDeployAndRetrieve() {
}

private void run() throws Exception {   //imp i am commented this file
//this.metadataConnection = MetadataLoginUtil.login("");
//Show the options to retrieve or deploy until user exits
String choice = getUsersChoice();
while (choice != null && !choice.equals("99")) {
if (choice.equals("1")) {
retrieveZip();
} else if (choice.equals("2")) {
deployZip("","","");
} else {
break;
}
//show the options again
choice = getUsersChoice();
}
}
/*
* Utility method to present options to retrieve or deploy.
*/
private String getUsersChoice() throws IOException {
System.out.println(" 1: Retrieve");
System.out.println(" 2: Deploy");
System.out.println("99: Exit");
System.out.println();
System.out.print("Enter 1 to retrieve, 2 to deploy, or 99 to exit: ");
// wait for the user input.
String choice = reader.readLine();
return choice != null ? choice.trim() : "";
}
    private String deployZip(String dUserName,String dPwd,String path) throws Exception {
        String deployStatus = "";
        try {
            this.metadataConnection = MetadataLoginUtil.login(dUserName,dPwd);
            byte zipBytes[] = readZipFile(path);
            DeployOptions deployOptions = new DeployOptions();
            deployOptions.setPerformRetrieve(false);
            deployOptions.setRollbackOnError(true);
            AsyncResult asyncResult = metadataConnection.deploy(zipBytes, deployOptions);
            DeployResult result = waitForDeployCompletion(asyncResult.getId());
            if (!result.isSuccess()) {
                printErrors(result, "Final list of failures:\n");
                deployStatus = "failure";
                throw new Exception("The files were not successfully deployed");
            } else {
                deployStatus = "success";
            }
            System.out.println("The file " + path + ZIP_FILE + " was successfully deployed\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deployStatus;
    }





/*
* Read the zip file contents into a byte array.
*/
private byte[] readZipFile(String path) throws Exception {
byte[] result = null;
// We assume here that you have a deploy.zip file.
//See the retrieve sample for how to retrieve a zip file.
File zipFile = new File(path+ZIP_FILE);
if (!zipFile.exists() || !zipFile.isFile()) {
throw new Exception("Cannot find the zip file for deploy() on path:"
+ zipFile.getAbsolutePath());
}
FileInputStream fileInputStream = new FileInputStream(zipFile);
try {
ByteArrayOutputStream bos = new ByteArrayOutputStream();
byte[] buffer = new byte[4096];
int bytesRead = 0;
while (-1 != (bytesRead = fileInputStream.read(buffer))) {
bos.write(buffer, 0, bytesRead);
}
result = bos.toByteArray();
} finally {
fileInputStream.close();
}
return result;
}
/*
* Print out any errors, if any, related to the deploy.
* @param result - DeployResult
*/
private void printErrors(DeployResult result, String messageHeader) {
DeployDetails details = result.getDetails();
StringBuilder stringBuilder = new StringBuilder();
if (details != null) {
DeployMessage[] componentFailures = details.getComponentFailures();
for (DeployMessage failure : componentFailures) {
String loc = "(" + failure.getLineNumber() + ", " + failure.getColumnNumber();
if (loc.length() == 0 && !failure.getFileName().equals(failure.getFullName()))
{
loc = "(" + failure.getFullName() + ")";
}
stringBuilder.append(failure.getFileName() + loc + ":"
+ failure.getProblem()).append('\n');
}
RunTestsResult rtr = details.getRunTestResult();
if (rtr.getFailures() != null) {
for (RunTestFailure failure : rtr.getFailures()) {
String n = (failure.getNamespace() == null ? "" :
(failure.getNamespace() + ".")) + failure.getName();
stringBuilder.append("Test failure, method: " + n + "." +
failure.getMethodName() + " -- " + failure.getMessage() +
" stack " + failure.getStackTrace() + "\n\n");
}
}
if (rtr.getCodeCoverageWarnings() != null) {
for (CodeCoverageWarning ccw : rtr.getCodeCoverageWarnings()) {
stringBuilder.append("Code coverage issue");
if (ccw.getName() != null) {
String n = (ccw.getNamespace() == null ? "" :
(ccw.getNamespace() + ".")) + ccw.getName();
stringBuilder.append(", class: " + n);
}
stringBuilder.append(" -- " + ccw.getMessage() + "\n");
}
}
}
if (stringBuilder.length() > 0) {
stringBuilder.insert(0, messageHeader);
System.out.println(stringBuilder.toString());
}
}
public void retrieveZip(String userName,String pwd) throws Exception {
    String path="SFDC\\"+userName+"\\upload";

    try{
    File f=new File(path+"\\components.zip");
    if(f.exists())
    {
        if (f.delete()) {
            System.out.println("File deleted ");
        }
        }
    }
    catch(Exception ex)
    {
    ex.printStackTrace();
    }
    this.metadataConnection = MetadataLoginUtil.login(userName,pwd);
RetrieveRequest retrieveRequest = new RetrieveRequest();
retrieveRequest.setApiVersion(API_VERSION);
setUnpackaged(path,retrieveRequest);
AsyncResult asyncResult = metadataConnection.retrieve(retrieveRequest);
asyncResult = waitForRetrieveCompletion(asyncResult);
RetrieveResult result =
metadataConnection.checkRetrieveStatus(asyncResult.getId());
// Print out any warning messages
StringBuilder stringBuilder = new StringBuilder();
if (result.getMessages() != null) {
for (RetrieveMessage rm : result.getMessages()) {
	System.out.println("------>"+result.getMessages());
stringBuilder.append(rm.getFileName() + " - " + rm.getProblem() + "\n");
}
}
if (stringBuilder.length() > 0) {
System.out.println("Retrieve warnings:\n" + stringBuilder);
}
System.out.println("Writing results to zip file");

File resultsfolder = new File("SFDC\\"+userName+"\\upload");
  if(!resultsfolder.exists()){
	    	resultsfolder.mkdirs();
	    }
File resultsFile = new File("SFDC\\"+userName+"\\upload\\"+ZIP_FILE);
FileOutputStream os = new FileOutputStream(resultsFile);
try {
os.write(result.getZipFile());
} finally {
os.close();
}
}
//this function written by sateesh
public String retrieveZipAfterUpload(String sUserName,String sPwd,String dUserName,String dPwd) {
String status="";
    try{
 String path="SFDC\\"+dUserName+"\\upload\\";
//    File f=new File(path+"\\components.zip");
    File f=new File(path+ZIP_FILE);
    if (!f.exists()) {
        f.delete();
        System.out.println("File deleted ");
    }
this.metadataConnection = MetadataLoginUtil.login(sUserName,sPwd);
RetrieveRequest retrieveRequest = new RetrieveRequest();
retrieveRequest.setApiVersion(API_VERSION);
setUnpackaged(path,retrieveRequest);
AsyncResult asyncResult = metadataConnection.retrieve(retrieveRequest);
asyncResult = waitForRetrieveCompletion(asyncResult);
RetrieveResult result =
metadataConnection.checkRetrieveStatus(asyncResult.getId());
// Print out any warning messages
StringBuilder stringBuilder = new StringBuilder();
if (result.getMessages() != null) {
for (RetrieveMessage rm : result.getMessages()) {
	//System.out.println("------>"+result.getMessages());
stringBuilder.append(rm.getFileName() + " - " + rm.getProblem() + "\n");
}
}
if (stringBuilder.length() > 0) {
System.out.println("Retrieve warnings:\n" + stringBuilder);
}
System.out.println("Writing results to zip file");
File resultsFile = new File("SFDC\\"+dUserName+"\\upload\\"+ZIP_FILE);
FileOutputStream os = new FileOutputStream(resultsFile);
try {
os.write(result.getZipFile());
} finally {
os.close();
}
status=deployZip(dUserName,dPwd,path);
    }
    catch(Exception e){
        status="The files were not successfully deployed";
       e.printStackTrace();
      }
    
   return status;
}





private void retrieveZip() throws Exception {
RetrieveRequest retrieveRequest = new RetrieveRequest();
retrieveRequest.setApiVersion(API_VERSION);
setUnpackaged("",retrieveRequest);
AsyncResult asyncResult = metadataConnection.retrieve(retrieveRequest);
asyncResult = waitForRetrieveCompletion(asyncResult);
RetrieveResult result =
metadataConnection.checkRetrieveStatus(asyncResult.getId());
// Print out any warning messages
StringBuilder stringBuilder = new StringBuilder();
if (result.getMessages() != null) {
for (RetrieveMessage rm : result.getMessages()) {
	System.out.println("------>"+result.getMessages());
stringBuilder.append(rm.getFileName() + " - " + rm.getProblem() + "\n");
}
}
if (stringBuilder.length() > 0) {
System.out.println("Retrieve warnings:\n" + stringBuilder);
}
System.out.println("Writing results to zip file");
File resultsFile = new File(ZIP_FILE);
FileOutputStream os = new FileOutputStream(resultsFile);
try {
os.write(result.getZipFile());
} finally {
os.close();
}
}
private DeployResult waitForDeployCompletion(String asyncResultId) throws Exception {
int poll = 0;
long waitTimeMilliSecs = ONE_SECOND;
DeployResult deployResult;
boolean fetchDetails;
do {
Thread.sleep(waitTimeMilliSecs);
// double the wait time for the next iteration
waitTimeMilliSecs *= 2;
if (poll++ > MAX_NUM_POLL_REQUESTS) {
throw new Exception(
"Request timed out. If this is a large set of metadata components, " +
"ensure that MAX_NUM_POLL_REQUESTS is sufficient.");
}
// Fetch in-progress details once for every 3 polls
fetchDetails = (poll % 3 == 0);
deployResult = metadataConnection.checkDeployStatus(asyncResultId, fetchDetails);
System.out.println("Status is: " + deployResult.getStatus());
if (!deployResult.isDone() && fetchDetails) {
printErrors(deployResult, "Failures for deployment in progress:\n");
}
}
while (!deployResult.isDone());
if (!deployResult.isSuccess() && deployResult.getErrorStatusCode() != null) {
throw new Exception(deployResult.getErrorStatusCode() + " msg: " +
deployResult.getErrorMessage());
}
if (!fetchDetails) {
// Get the final result with details if we didn't do it in the last attempt.
deployResult = metadataConnection.checkDeployStatus(asyncResultId, true);
}
return deployResult;
}
private AsyncResult waitForRetrieveCompletion(AsyncResult asyncResult) throws Exception
{
int poll = 0;
long waitTimeMilliSecs = ONE_SECOND;
while (!asyncResult.isDone()) {
Thread.sleep(waitTimeMilliSecs);
// double the wait time for the next iteration
waitTimeMilliSecs *= 2;
if (poll++ > MAX_NUM_POLL_REQUESTS) {
throw new Exception(
"Request timed out. If this is a large set of metadata components, " +
"ensure that MAX_NUM_POLL_REQUESTS is sufficient.");
}
asyncResult = metadataConnection.checkStatus(
new String[]{asyncResult.getId()})[0];
System.out.println("Status is: " + asyncResult.getState());
}
if (asyncResult.getState() != AsyncRequestState.Completed) {
throw new Exception(asyncResult.getStatusCode() + " msg: " +
asyncResult.getMessage());
}
return asyncResult;
}
private void setUnpackaged(String path,RetrieveRequest request) throws Exception {
	// Edit the path, if necessary, if your package.xml file is located elsewhere
    System.out.println("setUnpacked function path is "+path+"\\"+MANIFEST_FILE);
	File unpackedManifest = new File(path+"\\"+MANIFEST_FILE);
	System.out.println("Manifest file : " + unpackedManifest.getAbsolutePath());
	if (!unpackedManifest.exists() || !unpackedManifest.isFile()) {
	throw new Exception("Should provide a valid retrieve manifest " +
	"for unpackaged content. Looking for " +
	unpackedManifest.getAbsolutePath());
	}
	// Note that we use the fully quualified class name because
	// of a collision with the java.lang.Package class
	com.sforce.soap.metadata.wsc.Package p = parsePackageManifest(unpackedManifest);
	request.setUnpackaged(p);
	}
private com.sforce.soap.metadata.wsc.Package parsePackageManifest(File file)
throws ParserConfigurationException, IOException, SAXException {
com.sforce.soap.metadata.wsc.Package packageManifest = null;
List<PackageTypeMembers> listPackageTypes = new ArrayList<PackageTypeMembers>();
DocumentBuilder db =
DocumentBuilderFactory.newInstance().newDocumentBuilder();
InputStream inputStream = new FileInputStream(file);
Element d = db.parse(inputStream).getDocumentElement();
for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
if (c instanceof Element) {
Element ce = (Element) c;
NodeList nodeList = ce.getElementsByTagName("name");
if (nodeList.getLength() == 0) {
continue;
}
String name = nodeList.item(0).getTextContent();
NodeList m = ce.getElementsByTagName("members");
List<String> members = new ArrayList<String>();
for (int i = 0; i < m.getLength(); i++) {
Node mm = m.item(i);
members.add(mm.getTextContent());
}
PackageTypeMembers packageTypes = new PackageTypeMembers();
packageTypes.setName(name);
packageTypes.setMembers(members.toArray(new String[members.size()]));
listPackageTypes.add(packageTypes);
}
}
packageManifest = new com.sforce.soap.metadata.wsc.Package();
    System.out.println("---------------------------------------------------------> "+listPackageTypes);
PackageTypeMembers[] packageTypesArray =
new PackageTypeMembers[listPackageTypes.size()];
packageManifest.setTypes(listPackageTypes.toArray(packageTypesArray));
packageManifest.setVersion(API_VERSION + "");
return packageManifest;
}

// written by me
public void listMetadata() {
  try {
    ListMetadataQuery query = new ListMetadataQuery();
    query.setType("CustomObject");
  //  this.metadataConnection = MetadataLoginUtil.login();
    //query.setFolder(null);
    double asOfVersion = 30.0;
    // Assuming that the SOAP binding has already been established.
    FileProperties[] lmr = metadataConnection.listMetadata(
        new ListMetadataQuery[] {query}, asOfVersion);
    if (lmr != null) {
      for (FileProperties n : lmr) {
          
        System.out.println("Component fullName:------------------> " + n.getFullName());
        System.out.println("Component type: ---------------------->" + n.getType());
      }
    }
  } catch (ConnectionException ce) {
    ce.printStackTrace();
  }
}
}