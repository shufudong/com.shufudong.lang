package com.shufudong.lang.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @ClassName: XmlUtils
 * @Description: [中]读写xml文件的工具类
 * @author ShuFuDong
 * @date 2015年8月21日 下午12:55:49
 * 
 */
public class XmlUtils {
    private static final String XMLNS_XSI = "xmlns:xsi";
    private static final String XSI_SCHEMA_LOCATION = "xsi:schemaLocation";
    private static final String LOGIC_YES = "yes";
    private static final String DEFAULT_ENCODE = "UTF-8";
    private static final String REG_INVALID_CHARS = "&#\\d+;";

    /**
     * [中] 创建一个型的Document实例
     * 
     * @param @throws XmlException [中] 设定文件
     * @return Document [中] 返回类型
     * @throws
     */
    public static Document newDocument() throws XmlException {
        Document doc = null;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .newDocument();
        } catch (ParserConfigurationException e) {
            throw new XmlException(XmlUtils.class,e);
        }
        return doc;
    }

    /**
     * [中] 解析xml文件内容，并返回一个Document实例
     * 
     * @param @param file [中] xml文件
     * @param @throws XmlException [中] 设定文件
     * @return Document [中] Document对象的实例
     * @throws
     */
    public static Document getDocument(File file) throws XmlException {
        InputStream in = getInputStream(file);
        return getDocument(in);
    }

    /**
     * [中] 解析输入流，并返回一个Document实例
     * 
     * @param @param in [中] 输入流
     * @param @throws XmlException [中] 设定文件
     * @return Document [中] Document对象的实例
     * @throws
     */
    public static Document getDocument(InputStream in) throws XmlException {
        Document doc = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            doc = builder.parse(in);
        } catch (ParserConfigurationException e) {
            throw new XmlException(XmlUtils.class,e);
        } catch (SAXException e) {
            throw new XmlException(XmlUtils.class,e);
        } catch (IOException e) {
            throw new XmlException(XmlUtils.class,e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // nothing to do
                }
            }
        }
        return doc;
    }

    /**
     * [中] 在一个Document中创建根元素
     * 
     * @param @param tagName [中] 根元素名
     * @param @throws XmlException [中] 设定文件
     * @return Element [中] 返回类型
     * @throws
     */
    public static Element createRootElement(String tagName) throws XmlException {
        Document doc = newDocument();
        Element root = doc.createElement(tagName);
        doc.appendChild(root);
        return root;
    }

    /**
     * [中] 从一个输入流中获取根元素
     * 
     * @param @param in [中] 输入流
     * @param @throws XmlException [中] 设定文件
     * @return Element [中]根元素
     * @throws
     */
    public static Element getRootElementFromStream(InputStream in)
            throws XmlException {
        return getDocument(in).getDocumentElement();
    }

    /**
     * [中] 从一个xml文件中获取根元素
     * 
     * @param @param file [中] xml文件
     * @param @throws XmlException [中] 设定文件
     * @return Element [中] 根元素
     * @throws
     */
    public static Element getRootElementFromFile(File file) throws XmlException {
        return getDocument(file).getDocumentElement();
    }

    /**
     * [中] 将一个包含XML格式字符串的String类型，转换为根元素
     * 
     * @param @param payload [中] 包含XML格式字符串的String
     * @param @throws XmlException [中] 含有XML解析错误信息
     * @return Element [中] 文档的根元素
     * @throws
     */
    public static Element getRootElementFromString(String payload)
            throws XmlException {
        if (payload == null || payload.trim().length() < 1) {
            throw new XmlException(XmlException.XML_PAYLOAD_EMPTY);
        }
        byte[] bytes = null;
        try {
            bytes = payload.getBytes(DEFAULT_ENCODE);
        } catch (UnsupportedEncodingException e) {
            throw new XmlException(XmlException.XML_ENCODE_ERROR);
        }
        InputStream in = new ByteArrayInputStream(bytes);
        return getDocument(in).getDocumentElement();
    }

    /**
     * [中] 从父元素中获取子元素列表
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] 子元素标签名称
     * @return List<Element> [中] 解析文档的根元素列表
     * @throws
     */
    public static List<Element> getElements(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        List<Element> elements = new ArrayList<Element>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                elements.add((Element) node);
            }
        }

        return elements;
    }

    /**
     * [中] 从父元素中获取元素
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] 元素标签名称
     * @return Element [中] 解析文档的根元素
     * @throws
     */
    public static Element getElement(Element parent, String tagName) {
        List<Element> children = getElements(parent, tagName);

        if (children.isEmpty()) {
            return null;
        } else {
            return children.get(0);
        }
    }

    /**
     * [中] 从父元素中获取元素列表
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] 元素标签名称
     * @return List<Element> [中] 解析文档的根元素列表
     * @throws
     */
    public static List<Element> getChildElements(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        List<Element> elements = new ArrayList<Element>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element && node.getParentNode() == parent) {
                elements.add((Element) node);
            }
        }
        return elements;
    }

    /**
     * [中] 从父元素中获取子元素
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] 子元素标签名称
     * @return Element [中] 解析文档的根元素列表
     * @throws
     */
    public static Element getChildElement(Element parent, String tagName) {
        List<Element> children = getChildElements(parent, tagName);
        if (children.isEmpty()) {
            return null;
        } else {
            return children.get(0);
        }
    }

    /**
     * [中] 获取给定父元素下的标记名称的子元素的值。 如果标记名称中有多个值，将返回第一个值
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] 标记名称
     * @return String [中] 第一子元素的值，如果不存在标记为空.
     */
    public static String getElementValue(Element parent, String tagName) {
        String value = null;
        Element element = getElement(parent, tagName);
        if (element != null) {
            value = element.getTextContent();
        }
        return value;
    }

    /**
     * [中] 追加子元素到父元素中
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] 子元素名称
     * @return Element [中] 已经追加子元素后的父元素
     */
    public static Element appendElement(Element parent, String tagName) {
        Element child = parent.getOwnerDocument().createElement(tagName);
        parent.appendChild(child);
        return child;
    }

    /**
     * [中] 追加子元素及其值到父元素
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] 子元素名称
     * @param @param value [中] 子元素的值
     * @return Element [中] 子元素
     */
    public static Element appendElement(Element parent, String tagName,
            String value) {
        Element child = appendElement(parent, tagName);
        child.setTextContent(value);
        return child;
    }

    /**
     * [中] 追加子元素到父元素中
     * 
     * @param @param parent [中] 父元素
     * @param @param child [中] 子元素
     * @return void
     */
    public static void appendElement(Element parent, Element child) {
        Node tmp = parent.getOwnerDocument().importNode(child, true);
        parent.appendChild(tmp);
    }

    /**
     * [中] 追加CDATA元素到父元素中
     * 
     * @param @param parent [中] 父元素
     * @param @param tagName [中] CDATA元素名称
     * @param @param value [中] CDATA元素值
     * @return Element [中] 添加CDATA元素后的父元素
     * @throws
     */
    public static Element appendCDATAElement(Element parent, String tagName,
            String value) {
        Element child = appendElement(parent, tagName);
        if (value == null) { // avoid "null" word in the XML payload
            value = "";
        }
        Node cdata = child.getOwnerDocument().createCDATASection(value);
        child.appendChild(cdata);
        return child;
    }

    /**
     * [中] 将节点/元素实例转换为xml字符串
     * 
     * @param @param node [中] 需要转换的节点/元素实例
     * @param @throws XmlException [中] 由 {$link
     *        javax.xml.transform.TransformerException} 抛出的异常
     * @return String [中] xml字符串
     * @throws
     */
    public static String childNodeToString(Node node) throws XmlException {
        String payload = null;
        try {
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            Properties props = tf.getOutputProperties();
            props.setProperty(OutputKeys.OMIT_XML_DECLARATION, LOGIC_YES);
            tf.setOutputProperties(props);
            StringWriter writer = new StringWriter();
            tf.transform(new DOMSource(node), new StreamResult(writer));
            payload = writer.toString();
            payload = payload.replaceAll(REG_INVALID_CHARS, " ");
        } catch (TransformerException e) {
            throw new XmlException(XmlException.XML_TRANSFORM_ERROR);
        }
        return payload;
    }

    /**
     * [中] 将节点/文档/元素的实例转换为XML。
     * 
     * @param @param node [中] 需要转换的节点/文档/元素的实例
     * @param @throws XmlException [中] 由 {$link
     *        javax.xml.transform.TransformerException} 抛出的异常
     * @return String [中] xml字符串
     * @throws
     */
    public static String nodeToString(Node node) throws XmlException {
        String payload = null;
        try {
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            Properties props = tf.getOutputProperties();
            props.setProperty(OutputKeys.INDENT, LOGIC_YES);
            props.setProperty(OutputKeys.ENCODING, DEFAULT_ENCODE);
            tf.setOutputProperties(props);
            StringWriter writer = new StringWriter();
            tf.transform(new DOMSource(node), new StreamResult(writer));
            payload = writer.toString();
            payload = payload.replaceAll(REG_INVALID_CHARS, " ");
        } catch (TransformerException e) {
            throw new XmlException(XmlException.XML_TRANSFORM_ERROR);
        }
        return payload;
    }

    /**
     * [中] 将一个xml文件转换为字符串
     * 
     * @param @param file [中] xml文件的实例
     * @param @throws XmlException [中] 由 {$link
     *        javax.xml.transform.TransformerException} 抛出的异常
     * @return String [中] xml字符串
     * @throws
     */
    public static String xmlToString(File file) throws XmlException {
        Element root = getRootElementFromFile(file);
        return nodeToString(root);
    }

    /**
     * [中] 将一个xmlwe文件的输入流转换为字符串
     * 
     * @param @param in [中] xmlwe文件的输入流
     * @param @throws XmlException [中] 由 {$link
     *        javax.xml.transform.TransformerException} 抛出的异常
     * @return String [中] xml字符串
     * @throws
     */
    public static String xmlToString(InputStream in) throws XmlException {
        Element root = getRootElementFromStream(in);
        return nodeToString(root);
    }

    /**
     * [中] 将节点/文档/元素保存到文件中
     * 
     * @param @param doc [中] 要保存的节点/文档/元素
     * @param @param file [中] 文件实例
     * @param @throws XmlException [中]
     *        文档保存失败，将抛出消息为"xml.transform.error"的XmlException异常.
     * @throws
     */
    public static void saveToXml(Node doc, File file) throws XmlException {
        OutputStream out = null;
        try {
            Transformer tf = TransformerFactory.newInstance().newTransformer();
            Properties props = tf.getOutputProperties();
            props.setProperty(OutputKeys.METHOD, XMLConstants.XML_NS_PREFIX);
            props.setProperty(OutputKeys.INDENT, LOGIC_YES);
            tf.setOutputProperties(props);
            DOMSource dom = new DOMSource(doc);
            out = getOutputStream(file);
            Result result = new StreamResult(out);
            tf.transform(dom, result);
        } catch (TransformerException e) {
            throw new XmlException(XmlException.XML_TRANSFORM_ERROR);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // nothing to do
                }
            }
        }
    }

    /**
     * [中] 用指定的schema文件验证xml节点/文档/元素
     * 
     * @param @param doc [中] 需要验证的xml节点/文档/元素
     * @param @param schemaFile [中] scheam文件的实例
     * @param @throws XmlException [中]
     *        文档验证失败，将抛出消息为"xml.validate.error"的XmlException异常; [中]
     *        scheam文件读取错误，将抛出消息为"xml.read.error"的XmlException异常;
     * @throws
     */
    public static void validateXml(Node doc, File schemaFile)
            throws XmlException {
        validateXml(doc, getInputStream(schemaFile));
    }

    /**
     * [中] 用指定的schema文件验证xml节点/文档/元素
     * 
     * @param @param doc [中] 需要验证的xml节点/文档/元素
     * @param @param schemaStream [中] scheam文件流
     * @param @throws XmlException [中]
     *        文档验证失败，将抛出消息为"xml.validate.error"的XmlException异常; [中]
     *        scheam文件读取错误，将抛出消息为"xml.read.error"的XmlException异常.
     * @throws
     */
    public static void validateXml(Node doc, InputStream schemaStream)
            throws XmlException {
        try {
            Source source = new StreamSource(schemaStream);
            Schema schema = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(source);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(doc));
        } catch (SAXException e) {
            throw new XmlException(XmlException.XML_VALIDATE_ERROR);
        } catch (IOException e) {
            throw new XmlException(XmlException.XML_READ_ERROR);
        } finally {
            if (schemaStream != null) {
                try {
                    schemaStream.close();
                } catch (IOException e) {
                    // nothing to do
                }
            }
        }
    }

    /**
     * [中] 将xml内容转换为XSL格式的XHTML/HTML字符串
     * 
     * @param @param payload [中] 需要转换的xml内容载体
     * @param @param xsltFile [中] xml风格的文件
     * @param @throws XmlException [中]
     *        转换失败，将抛出消息为"xml.transform.error"的XmlException异常.
     * @return String [中] 转换后的XHTML/HTML格式的字符串
     * @throws
     */
    public static String xmlToHtml(String payload, File xsltFile)
            throws XmlException {
        String result = null;
        try {
            Source template = new StreamSource(xsltFile);
            Transformer transformer = TransformerFactory.newInstance()
                    .newTransformer(template);
            Properties props = transformer.getOutputProperties();
            props.setProperty(OutputKeys.OMIT_XML_DECLARATION, LOGIC_YES);
            transformer.setOutputProperties(props);
            StreamSource source = new StreamSource(new StringReader(payload));
            StreamResult sr = new StreamResult(new StringWriter());
            transformer.transform(source, sr);

            result = sr.getWriter().toString();
        } catch (TransformerException e) {
            throw new XmlException(XmlException.XML_TRANSFORM_ERROR);
        }
        return result;
    }

    /**
     * [中] 为元素设置命名空间
     * 
     * @param @param element [中] 元素
     * @param @param namespace [中] 命名空间
     * @param @param schemaLocation [中] scheam文件的URI位置
     * @throws
     */
    public static void setNamespace(Element element, String namespace,
            String schemaLocation) {
        element.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI,
                XMLConstants.XMLNS_ATTRIBUTE, namespace);
        element.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, XMLNS_XSI,
                XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
        element.setAttributeNS(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI,
                XSI_SCHEMA_LOCATION, schemaLocation);
    }

    /**
     * [中] 对合法的xml字符串进行编码
     */
    public static String encodeXml(String payload) throws XmlException {
        Element root = createRootElement(XMLConstants.XML_NS_PREFIX);
        root.setTextContent(payload);
        return childNodeToString(root.getFirstChild());
    }

    private static InputStream getInputStream(File file) throws XmlException {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new XmlException(XmlException.FILE_NOT_FOUND);
        }
        return in;
    }

    private static OutputStream getOutputStream(File file) throws XmlException {
        OutputStream in = null;
        try {
            in = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new XmlException(XmlException.FILE_NOT_FOUND);
        }
        return in;
    }
}
