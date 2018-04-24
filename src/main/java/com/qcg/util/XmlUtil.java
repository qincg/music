package com.qcg.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * xml解析工具类
 */
public class XmlUtil {
    private final String FILE_URL = XmlUtil.class.getClassLoader().getResource("") + "db.xml";
    Element rootElement = null;
    public XmlUtil() {
        SAXReader saxReader = new SAXReader();
        try {
            LogUtil.getLogger().trace(FILE_URL);
            URL url = new URL(FILE_URL);
            String fileName = url.getFile();
            File file = new File(fileName);
            Document document = saxReader.read(file);
            rootElement = document.getRootElement();
        }catch (MalformedURLException e){
            e.printStackTrace();
        } catch (DocumentException e){
            e.printStackTrace();
        }
    }

    public String getValue(String elementName){
        Element element = rootElement.element(elementName);
        return element.getText();
    }
}
