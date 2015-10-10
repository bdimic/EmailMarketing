/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aquest.emailmarketing.web.service;

import com.opencsv.CSVReader;
import com.sun.org.apache.bcel.internal.util.ClassPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author bdimic
 */
public class ImportService {
    public void ImportFile(String listfilename, InputStream filename, String separator) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        CSVReader reader = new CSVReader(new InputStreamReader(filename), separator.charAt(0));
        boolean isFirstLine = true;
        String[] firstLine = null;
        String[] nextLine;
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        Element rootElement = doc.createElement("Contacts");
        doc.appendChild(rootElement);
        while ((nextLine = reader.readNext()) != null) {
            if(isFirstLine) {
                firstLine = nextLine;
                isFirstLine = false;
            } else {   
                Element rowElement = doc.createElement("contact");
                rootElement.appendChild(rowElement);
                for(int i=0; i < firstLine.length; i++) {
                    String header = firstLine[i];
                    String value = "";
                    System.out.println(header);
                    System.out.println(nextLine[i]);
                    System.out.println(value);
                    
                    if(i < nextLine.length) {
                        value = nextLine[i];
                    }
                    
                    Element curElement = doc.createElement(header);
                    curElement.appendChild(doc.createTextNode(value));
                    rowElement.appendChild(curElement);
                }
            }
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        String path = ClassPath.getClassPath();
        System.out.println(path);
        StreamResult result = new StreamResult(new File("C:\\Users\\Bojan\\Documents\\"+listfilename+".xml"));
        transformer.transform(source, result);
    }
}
