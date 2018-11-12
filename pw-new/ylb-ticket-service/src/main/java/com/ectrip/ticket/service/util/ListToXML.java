package com.ectrip.ticket.service.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListToXML {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.List list = new ArrayList();



	}

	public static void setAllComponentsName(Object f) {
		Field[] fields = f.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			try {
				String varName = fields[i].getName();
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object o = fields[i].get(f);
				System.out.println("传入的对象中包含一个如下的属性：" + varName + " = " + o);
				fields[i].setAccessible(accessFlag);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void setAllComponentsNamea(Object f) {
		Field[] fields = f.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			try {
				String varName = fields[i].getName();
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object o = fields[i].get(f);
				System.out.println("传入的对象中包含一个如下的属性：" + varName + " = " + o);
				fields[i].setAccessible(accessFlag);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static StringBuffer ListToXml(List list) {
		StringBuffer xml = new StringBuffer();
		int rsCount = 0;
		String entry = "";
		Object o1 = null;
		try {
			xml.append("<?xml version=\"1.0\" standalone=\"yes\"?> "); // XML的头部信息
			xml.append(" <DATAPACKET Version=\"2.0\"><METADATA>");

			if (list.size() > 0) {
				o1 = list.get(0);
			}
			xml.append("<FIELDS>");

			Field[] fields = o1.getClass().getDeclaredFields();

			for (int i = 1; i < fields.length; i++) {
				xml.append(" <FIELD ");
				xml.append("  attrname=\"" + fields[i].getName() + "\"");
				xml.append("  fieldtype=\"" + "string" + "\"");
				xml.append("  SUBTYPE=\"FixedChar\"");
				xml.append("  WIDTH=\"50\"" + "/>");
			}
			xml.append("</FIELDS><PARAMS/></METADATA>");
			// 新添结束

			xml.append("<ROWDATA>");
			for (int row = 0; row < list.size(); row++) {

				rsCount = rsCount + 1;
				xml.append("<ROW ");
				// this.PrintLogs("start");
				Object f = list.get(row);
				// Field[] fieldsmy = f.getClass().getDeclaredFields();
				for (int ii = 0; ii < fields.length; ii++) {

					String varName = fields[ii].getName();
					boolean accessFlag = fields[ii].isAccessible();
					fields[ii].setAccessible(true);
					Object o = fields[ii].get(f);
					if (o == null) {
						entry = "";
					} else {
						if (o instanceof Integer) {
							entry = "" + o;
						} else if (o instanceof Float) {
							entry = "" + o;

						} else if (o instanceof Double) {
							entry = "" + o;

						} else {
							entry = o.toString();
						}
					}
					fields[ii].setAccessible(accessFlag);

					xml.append(varName + "=\"" + entry + "\" ");
				}

				xml.append("/>");

			} // while end
			xml.append("</ROWDATA></DATAPACKET>");

		} catch (Exception e) {
			System.out.print(e);
		}
		return xml;
	}

	public static StringBuffer generateXML(List list) {
		return generateXML(list, 1);
	}

	/**
	 * @param 根所LIST返回XML
	 *            rs输入的结果集 oneUpper 将首字母转为大写
	 * @return String 返回XML串
	 * @exception SQLException
	 */
	public static StringBuffer generateXML(List list, int oneUpper) {
		StringBuffer buffer = new StringBuffer();
		try {

			if (list == null)
				return buffer;
			Object o1 = null;
			buffer.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>"); // XML的头部信息
			buffer.append("<ResultSet>");
			if (list.size() > 0) {
				o1 = list.get(0);
			}
			buffer.append("<FIELDS>");

			Field[] fields = o1.getClass().getDeclaredFields();

			// 对放回的全部数据逐一处理
			for (int id = 0; id < list.size(); id++) {
				// 格式为row id , col name, col context
				Object f = list.get(id);
				buffer.append("<row id=\"").append(id).append("\">");
				for (int i = 0; i < fields.length; i++) {

					String entry = "";
					String varName = fields[i].getName();
					boolean accessFlag = fields[i].isAccessible();
					fields[i].setAccessible(true);
					Object o = fields[i].get(f);
					if (o == null) {
						entry = "";
					} else {
						if (o instanceof Integer) {
							entry = "" + o;
						} else if (o instanceof Float) {
							entry = "" + o;

						} else if (o instanceof Double) {
							entry = "" + o;

						} else {
							entry = o.toString();
						}
					}
					fields[i].setAccessible(accessFlag);
					buffer.append("<col name=\"" + (oneUpper == 1 ? ServiceUtil.oneUpper(varName) : varName) + "\">");
					buffer.append(entry.trim());

					buffer.append("</col>");
				}
				buffer.append("</row>");
			}
			buffer.append("</ResultSet>");

			return buffer;
		} catch (Exception e)

		{
			return buffer;
		}
	}

	public static StringBuffer generateListToXMLNoHead(List list, int oneUpper) {
		StringBuffer buffer = new StringBuffer();
		try {

			if (list == null)
				return buffer;
			Object o1 = null;
			// buffer.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>"); // XML的头部信息
			buffer.append("<ResultSet>");
			if (list.size() > 0) {
				o1 = list.get(0);
			}
			buffer.append("<FIELDS>");

			Field[] fields = o1.getClass().getDeclaredFields();

			// 对放回的全部数据逐一处理
			for (int id = 0; id < list.size(); id++) {
				// 格式为row id , col name, col context
				Object f = list.get(id);
				buffer.append("<row id=\"").append(id).append("\">");
				for (int i = 0; i < fields.length; i++) {

					String entry = "";
					String varName = fields[i].getName();
					boolean accessFlag = fields[i].isAccessible();
					fields[i].setAccessible(true);
					Object o = fields[i].get(f);
					if (o == null) {
						entry = "";
					} else {
						if (o instanceof Integer) {
							entry = "" + o;
						} else if (o instanceof Float) {
							entry = "" + o;

						} else if (o instanceof Double) {
							entry = "" + o;

						} else {
							entry = o.toString();
						}
					}
					fields[i].setAccessible(accessFlag);
					buffer.append("<col name=\"" + (oneUpper == 1 ? ServiceUtil.oneUpper(varName) : varName) + "\">");
					buffer.append(entry.trim());

					buffer.append("</col>");
				}
				buffer.append("</row>");
			}
			buffer.append("</ResultSet>");

			return buffer;
		} catch (Exception e)

		{
			return buffer;
		}
	}

	/**
	 * This method gets the value of the specified column 通用的读取结果集某一列的值并转化为String表达
	 *
	 * @param ResultSet
	 *            rs 输入的纪录集
	 * @param int colNum 第几列
	 * @param int type 数据类型
	 */
	private String getValue(ResultSet rs, int colNum, String type) throws SQLException {
		Object value = null;

		if (type.equals("varchar2") || type.equals("varchar"))

			value = getTxtWithoutHTMLElement(rs.getString(colNum));
		else
			value = rs.getObject(colNum);

		if (value != null)
			return value.toString().trim();
		return "null";
	}

	private String getTxtWithoutHTMLElement(String element) {
		// String reg="<[^<|^>]+>";
		// return element.replaceAll(reg,"");

		if (null == element || "".equals(element.trim())) {
			return element;
		}

		Pattern pattern = Pattern.compile("<[^<|^>]*>");
		Matcher matcher = pattern.matcher(element);
		StringBuffer txt = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			if (group.matches("<[\\s]*>")) {
				matcher.appendReplacement(txt, group);
			} else {

				matcher.appendReplacement(txt, "");
			}
		}
		matcher.appendTail(txt);

		return txt.toString().replaceAll("[\\n]", "").replace("[&nbsp]", "").replaceAll("[;]", "")
				.replaceAll("[']", "").replaceAll("[\"]", "");
	}
}
