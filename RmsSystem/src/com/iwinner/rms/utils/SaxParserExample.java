package com.iwinner.rms.utils;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParserExample {

    public static void main (String argv []) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            InputStream    xmlInput  =
                new FileInputStream("data\\sax-example.xml");

            SAXParser      saxParser = factory.newSAXParser();
            SaxHandler handler   = new SaxHandler();
            saxParser.parse(xmlInput, handler);

            for(Driver driver : handler.drivers){
                System.out.println(driver);
            }
        } catch (Throwable err) {
            err.printStackTrace ();
        }
    }
}