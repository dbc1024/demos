package com.ectrip.ticket.service.util;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * 存放XML的数据
 * @author LIJIN
 *
 */
public class XmlBean {
	private static Document document = null; //整个XML文件档句柄
	private String xmlFileName; //XML文件名称
	/**
	 * 初始文件名
	 * @param filename String
	 */
	public XmlBean(String filename) throws Exception {
		setXmlFileName(filename);
		try {
			loadfile(filename);
		} catch (Exception e) {
			System.out.print("文件不存在,或者是格式有问题" + e.toString());
			throw e;
		}

	}
	public void loadfile(String filename) throws Exception {

		try {

			SAXReader saxReader = new SAXReader();
			StringReader sr = new StringReader(filename);

			document = saxReader.read(sr);
		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;

		}
	}

	public String readXmlAttribValue(String nodename) {
		List list = document.selectNodes(nodename);

		Iterator it = list.iterator();

		String ls_values = "";

		while (it.hasNext()) {
			Element ftpElement = (Element) it.next();
			ls_values = ftpElement.getTextTrim() ;


		}

		return ls_values;
	}

	public List readXmlAttribValueList(String nodename) {
		List list = document.selectNodes(nodename);

		/*Iterator it = list.iterator();

		String ls_values = "";

		while (it.hasNext()) {
			Element ftpElement = (Element) it.next();
			ls_values = ftpElement.getTextTrim() ;
		

		}
*/
		return list;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}
}
