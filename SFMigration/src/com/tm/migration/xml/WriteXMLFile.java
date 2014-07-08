/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tm.migration.xml;

/**
 *
 * @author karthi
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {
    public void getMetadataSublevelBL(String datatyp,String typeMembers) {
	  try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Package");
		doc.appendChild(rootElement);
                Attr attr_id = doc.createAttribute("xmlns");
		attr_id.setValue("http://soap.sforce.com/2006/04/metadata");
		rootElement.setAttributeNode(attr_id);

                Element types = doc.createElement("types");
                rootElement.appendChild(types);

                Element members = doc.createElement("members");
		members.appendChild(doc.createTextNode(typeMembers));
		types.appendChild(members);

                Element name = doc.createElement("name");
		//name.appendChild(doc.createTextNode("CustomObject"));
		name.appendChild(doc.createTextNode(datatyp));
		types.appendChild(name);
		// shorten way
		// staff.setAttribute("id", "1");
		// firstname elements
		Element version = doc.createElement("version");
		version.appendChild(doc.createTextNode("30.0"));
		types.appendChild(version);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("package.xml"));
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
//              transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//              transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//              transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		transformer.transform(source, result);
		System.out.println("File saved!");
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
    public String writeData(String userName, Map<String, ArrayList> mp) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Package");
            doc.appendChild(rootElement);
            Attr attr_id = doc.createAttribute("xmlns");
            attr_id.setValue("http://soap.sforce.com/2006/04/metadata");
            rootElement.setAttributeNode(attr_id);
            int size = mp.size();
            for (Map.Entry<String, ArrayList> entry : mp.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());

                Element types = doc.createElement("types");
                rootElement.appendChild(types);
                Iterator subLst = entry.getValue().iterator();
                while (subLst.hasNext()) {
                    Element members = doc.createElement("members");
                    members.appendChild(doc.createTextNode(subLst.next().toString()));
                    types.appendChild(members);
                }

                Element name = doc.createElement("name");
                //name.appendChild(doc.createTextNode("CustomObject"));
                name.appendChild(doc.createTextNode(entry.getKey()));
                types.appendChild(name);
                // shorten way
                // staff.setAttribute("id", "1");
                // firstname elements
                if (size == 1) {
                    Element version = doc.createElement("version");
                    version.appendChild(doc.createTextNode("30.0"));
                    types.appendChild(version);
                }
                size = size - 1;
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            Boolean sts = new File("SFDC").mkdir();
            if (sts) {
                System.out.println(sts + " File created successfully");
            }
            Boolean sts1 = new File("SFDC\\" + userName).mkdir();
            if (sts) {
                System.out.println(userName + " File created successfully");
            }
            System.out.println("Folder creation status is ---------------> " + sts);
            StreamResult result = new StreamResult(new File("SFDC\\" + userName + "\\package.xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return readPackageXML("SFDC\\" + userName + "\\package.xml");
    }

     public String readPackageXML(String fileName) {
 		BufferedReader br = null;
                StringBuffer sb=new StringBuffer();
 		try {
 			String sCurrentLine;
 			br = new BufferedReader(new FileReader(fileName));
 			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				sb.append(sCurrentLine+"<br>");
			}
 		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
                return sb.toString();
 	}
	public static void main(String argv[]) {
	  try {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("Package");
		doc.appendChild(rootElement);
                Attr attr_id = doc.createAttribute("xmlns");
		attr_id.setValue("http://soap.sforce.com/2006/04/metadata");
		rootElement.setAttributeNode(attr_id);

                Element types = doc.createElement("types");
                rootElement.appendChild(types);

                Element members = doc.createElement("members");
		members.appendChild(doc.createTextNode("*"));
		types.appendChild(members);

                Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode("CustomObject"));
		types.appendChild(name);
		// shorten way
		// staff.setAttribute("id", "1");
		// firstname elements
		Element version = doc.createElement("version");
		version.appendChild(doc.createTextNode("30.0"));
		types.appendChild(version);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("package.xml"));
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
		System.out.println("File saved!");
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
