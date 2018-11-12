package com.ectrip.ec.model.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class ObjectParse implements IObjectParse {

	public BaseModel XmlToObject(String xml) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance(); // 取得SAXParserFactory实例
		SAXParser parser = factory.newSAXParser(); // 从factory获取SAXParser实例
		MyHandler handler = new MyHandler(); // 实例化自定义Handler
		InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
		parser.parse(is, handler); // 根据自定义Handler规则解析输入流
		return handler.getBaseModel();
	}

	public String ObjectToXml(BaseModel baseModel) throws Exception {
		SAXTransformerFactory factory = (SAXTransformerFactory) TransformerFactory.newInstance();// 获取SaxTransformerFactory实例
		TransformerHandler handler = factory.newTransformerHandler();// 从factory获取handler实例
		Transformer transformer = handler.getTransformer();// 从handler中获取transformer实例
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8"); // 设置输出采用的编码方式
		transformer.setOutputProperty(OutputKeys.INDENT, "no");// 是否自动添加额外空白
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");// 是否忽略xml声明
//		transformer.setOutputProperty(OutputKeys., value)
		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		handler.setResult(result);
		char[] ch = null;
		String uri = "";// 代表命名空间的URI 当URI无值时需要赋值空字符串
		String localName = "";// 命名空间本地名称(不包含前缀)未处理时须织空字符串
		handler.startDocument();// 开始文本
		handler.startElement(uri, localName, "ticketbook", null);// 开始创建节点
		ch = baseModel.getUsid().toCharArray();
		handler.startElement(uri, localName, "usid", null);// 开始借点 用户名
		handler.characters(ch, 0, ch.length);// 设置节点文本值
		handler.endElement(uri, localName, "usid");// 结束节点
		handler.startElement(uri, localName, "pwd", null);// 开始借点 用户名
		handler.characters("****".toCharArray(), 0,"****".length());// 设置节点文本值
		handler.endElement(uri, localName, "pwd");// 结束节点
		handler.startElement(uri, localName, "logonstatus", null);
		ch = baseModel.getLogonstatus().toCharArray();
		handler.characters(ch, 0, ch.length);// 设置logon状态
		handler.endElement(uri, localName, "logonstatus");
		
		try {
			ch = baseModel.getMsgtp().toCharArray();
			handler.startElement(uri, localName, "msgtp", null);
			handler.characters(ch, 0, ch.length);// 设置login状态码
			handler.endElement(uri, localName, "msgtp");
		} catch (Exception e) {
			// TODO: handle exception
			handler.startElement(uri, localName, "msgtp", null);
			handler.characters("null".toCharArray(), 0, "null".length());
			handler.endElement(uri, localName, "msgtp");
		}
		
		
		handler.startElement(uri, localName, "object", null);
		Map object = (Map)baseModel.getObject();
	    if(object!=null){
		  Iterator sitertor = object.keySet().iterator();
		  while (sitertor.hasNext()) {
			String skey = sitertor.next().toString();
			if (object.get(skey) != null) {
				ch = object.get(skey).toString().toCharArray();
			}else{
				ch="NULL".toCharArray(); 
			}
			handler.startElement(uri, localName, skey, null);
			handler.characters(ch, 0, ch.length);// 设置logon状态
			handler.endElement(uri, localName, skey);
		  }
		}else{
			ch="NULL".toCharArray(); 
			handler.characters(ch, 0, ch.length);// 设置logon状态
		}
		handler.endElement(uri, localName, "object");
		// 如果有状态正常且有数据则继续添加节点
		if (baseModel.getLogonstatus() != null && !"".equals(baseModel.getLogonstatus()) && "1".equals(baseModel.getLogonstatus())) {
			if (baseModel.getNodes() != null && baseModel.getNodes().size() > 0) {
				List nodes = baseModel.getNodes();
				for (int y = 0; y < nodes.size(); y++) {// 循环节点
					handler.startElement(uri, localName, "nodes", null);
					Map map = (Map) nodes.get(y);
					Iterator sitertor = map.keySet().iterator();
					while (sitertor.hasNext()) {
						String skey = sitertor.next().toString();
						if (map.get(skey) != null) {
							ch = map.get(skey).toString().toCharArray();
						}else{
							ch="NULL".toCharArray(); 
						}
						handler.startElement(uri, localName, skey, null);
						handler.characters(ch, 0, ch.length);// 设置logon状态
						handler.endElement(uri, localName, skey);
					}
					handler.endElement(uri, localName, "nodes");
				}
			}
		}
		handler.endElement(uri, localName, "ticketbook");
		handler.endDocument();
		return writer.toString();
	}

	private class MyHandler extends DefaultHandler {

		private BaseModel baseModel = null;
		private StringBuilder builder;
		private Map map;

		public BaseModel getBaseModel() {
			return baseModel;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			baseModel = new BaseModel();
			builder = new StringBuilder();
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			builder.setLength(0);
			if (qName.equals("nodes")) {
				if (baseModel.getNodes() == null) {
					baseModel.setNodes(new ArrayList<Map>());
				}
				map = new HashMap();
			}
			if(qName.equals("object")&&baseModel.getObject() == null) {
				baseModel.setObject(new Object());
				map = new HashMap();
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);
			if (qName.equals("usid")) {
				baseModel.setUsid(builder.toString());
			} else if (qName.equals("logonstatus")) {
				baseModel.setLogonstatus(builder.toString());
			} else if (qName.equals("pwd")) {
				baseModel.setPwd(builder.toString());
			} else if (qName.equals("object")) {
				baseModel.setObject(map);
			} else if (qName.equals("nodes")) {
				baseModel.getNodes().add(map);
			} else {
				if(map!=null){
					map.put(qName, builder.toString());
				}
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			builder.append(ch, start, length); // 将读取的字符数组追加到builder中
		}
	}

}
