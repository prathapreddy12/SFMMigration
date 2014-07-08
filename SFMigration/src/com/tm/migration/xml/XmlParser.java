/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tm.migration.xml;

/**
 *
 * @author karthi
 */


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.json.JSONArray;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XmlParser
{


    public JSONArray getXmlDataByNodeBasedOnExpression(String ComponentTyp,String atrTyp,String url)
                //throws IOException, ParserConfigurationException, SAXException, XPathExpressionException
	{
            
            List<String> lst = new ArrayList<String>();
		try
		{
            FileInputStream file = new FileInputStream(new File(url));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);
            XPath xPath =  XPathFactory.newInstance().newXPath();
            String expression = atrTyp;
            System.out.println("*************************");
            System.out.println(atrTyp);
            System.out.println("*************************");
            expression = "/"+ComponentTyp+"/"+atrTyp+"/fullName";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
                lst.add(nodeList.item(i).getFirstChild().getNodeValue());
            }
Collections.sort(lst);
		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
         JSONArray jsonAtr=new JSONArray(lst);
         return jsonAtr;
	}
	public static void getMetaAttributesOfParticulerComponentByNode(String args)
                //throws IOException, ParserConfigurationException, SAXException, XPathExpressionException
	{
		try
		{
            FileInputStream file = new FileInputStream(new File(args));

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =  builderFactory.newDocumentBuilder();

            Document xmlDocument = builder.parse(file);

            XPath xPath =  XPathFactory.newInstance().newXPath();
            String expression = "fields";
            System.out.println("*************************");

            System.out.println("fields");
            System.out.println("*************************");

            expression = "/componentAttributes/types/name";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
//                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
                System.out.println(nodeList.item(i).getChildNodes().item(i));
            }


		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
	}
	public static void getXmlDataByNode(String args)
                //throws IOException, ParserConfigurationException, SAXException, XPathExpressionException
	{
		try
		{
            FileInputStream file = new FileInputStream(new File(args));

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =  builderFactory.newDocumentBuilder();

            Document xmlDocument = builder.parse(file);

            XPath xPath =  XPathFactory.newInstance().newXPath();
            String expression = "fields";
            System.out.println("*************************");

            System.out.println("fields");
            System.out.println("*************************");

            expression = "/CustomObject/fields/fullName";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
            }

            System.out.println("*************************");
            System.out.println("recordTypes");

            System.out.println("*************************");
            expression = "/CustomObject/recordTypes/fullName";
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
            }
            System.out.println("*************************");
            System.out.println("validationRules");

            System.out.println("*************************");
            expression = "/CustomObject/validationRules/fullName";
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
            }

            System.out.println("*************************");
            System.out.println("webLinks");

            System.out.println("*************************");
            expression = "/CustomObject/webLinks/fullName";
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
            }
            System.out.println("*************************");
            System.out.println("listviews");
            System.out.println("*************************");
            expression = "/CustomObject/listViews/fullName";
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
            }
		}
		catch (Exception e)
		{
            e.printStackTrace();
        }
	}
	

public static void main(String args[])

        {
getMetaAttributesOfParticulerComponentByNode("F://Apache Tomcat 6.0.26//bin//Migration_SFDC_Java//componentAttributes.xml");
}

}

