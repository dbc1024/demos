package com.ectrip.ticket.service.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

@SuppressWarnings( { "rawtypes" })
public class XmlConverUtil {
	/**
	 *
	 * Describe:把map转换成xml
	 *
	 * @author:chenxinhao
	 * @param map
	 * @return return:String Date:2014-2-18
	 */
	public static String maptoXml(Map map) {
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("ResultSet");

		Element nodeElement = nodesElement.addElement("row");
		for (Object obj : map.keySet()) {
			Element keyElement = nodeElement.addElement("col");
			keyElement.addAttribute("name", String.valueOf(obj));
			String value = String.valueOf(map.get(obj));
			if (value == null || value.equals("null") || value.equals("NULL")) {
				value = "";
			}
			keyElement.setText(value);

		}
		return doc2String(document);
	}

	/**
	 *
	 * Describe:把list转换成xml
	 *
	 * @author:chenxinhao
	 * @param list
	 *            list<map>格式
	 * @return return:String Date:2014-2-18
	 */
	public static String listtoXml(List list) {
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("ResultSet");
		for (Object o : list) {
			Element nodeElement = nodesElement.addElement("row");
			for (Object obj : ((Map) o).keySet()) {
				Element keyElement = nodeElement.addElement("col");
				keyElement.addAttribute("name", String.valueOf(obj));
				String value = String.valueOf(((Map) o).get(obj));
				if (value == null || value.equals("null")
						|| value.equals("NULL")) {
					value = "";
				}
				keyElement.setText(value);
			}
		}
		return doc2String(document);
	}

	/**
	 * <row><col name=\"key1\">value1</col><col name=\"key2\">value2</col></row>
	 * Describe: 把xml转换成map
	 *
	 * @author:chenxinhao
	 * @param xml
	 * @return return:Map Date:2014-2-18
	 */
	@SuppressWarnings("unchecked")
	public static Map xmltoMap(String xml) {
		try {
			Map map = new HashMap();
			Document document = DocumentHelper.parseText(xml);
			Element nodeElement = document.getRootElement();
			List node = nodeElement.elements();
			for (Iterator it = node.iterator(); it.hasNext();) {
				Element elm = (Element) it.next();
				String text = "";
				if (elm.getText() != null && !elm.getText().equals("")
						&& !elm.getText().equals("null")) {
					text = elm.getText();
				}
				map.put(elm.attributeValue("name").toLowerCase(), text);
				elm = null;
			}
			node = null;
			nodeElement = null;
			document = null;
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * <ResultSet><row><col name=\"key1\">value1</col><col
	 * name=\"key2\">value2</col></row><row><col name=\"key1\">value1</col><col
	 * name=\"key2\">value2</col></row></ResultSet> Describe:把xml转换成list
	 *
	 * @author:chenxinhao
	 * @param xml
	 * @return return:List Date:2014-2-18
	 */
	public static List xmltoList(String xml) {
		try {
			List<Map> list = new ArrayList<Map>();
			Document document = DocumentHelper.parseText(xml);
			Element nodesElement = document.getRootElement();
			List nodes = nodesElement.elements();
			for (Iterator its = nodes.iterator(); its.hasNext();) {
				Element nodeElement = (Element) its.next();
				Map map = xmltoMap(nodeElement.asXML());
				list.add(map);
				map = null;
			}
			nodes = null;
			nodesElement = null;
			document = null;
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String doc2String(Document document) {
		String s = "";
		try {
			// 使用输出流来进行转化
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// 使用UTF-8编码
			OutputFormat format = new OutputFormat(" ", true, "UTF-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		sb
				.append("<row><col name=\"key1\">电风扇地方</col><col name=\"key2\">烦都烦死分</col></row>");
		Map map = xmltoMap(sb.toString());
		if (map != null) {
			System.out.println(map.get("key1"));
			System.out.println(map.get("key2"));
		} else {
			System.out.println("null");
		}
	}
}
