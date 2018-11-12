package com.ectrip.ticket.service.util;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

public class ListToBCXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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


	public static String ListToXml(List list) {
		StringBuffer xml  = new StringBuffer();
		int rsCount =0;
		String entry="";
		Object o1  = null ;
		try {
			xml.append("<?xml version=\"1.0\" standalone=\"yes\"?> "); // XML的头部信息
			xml.append(" <DATAPACKET Version=\"2.0\"><METADATA>");

			if ( list.size() > 0)
			{
				o1  = list.get(0);
			}
			xml.append("<FIELDS>");

			Field[] fields = o1.getClass().getDeclaredFields();

			for (int i = 1; i < fields.length ; i++) {
				xml.append(" <FIELD ");
				xml.append("  attrname=\""
						+ fields[i].getName()
						+ "\"");
				xml.append("  fieldtype=\"" + "string" + "\"");
				xml.append("  SUBTYPE=\"FixedChar\"");
				xml
						.append("  WIDTH=\"50\"" + "/>");
			}
			xml.append("</FIELDS><PARAMS/></METADATA>");
			// 新添结束

			xml.append("<ROWDATA>");
			for (int row  =0; row < list.size(); row++)
			{

				rsCount = rsCount + 1;
				xml.append("<ROW ");
				// this.PrintLogs("start");
				Object f = list.get(row);
				//	Field[] fieldsmy = f.getClass().getDeclaredFields();
				for (int ii = 0; ii < fields.length; ii++) {

					String varName = fields[ii].getName();
					boolean accessFlag = fields[ii].isAccessible();
					fields[ii].setAccessible(true);
					Object o = fields[ii].get(f);
					if ( o == null )
					{
						entry="";
					}else
					{
						if ( o instanceof Integer)
						{
							entry = ""+ o;
						}
						else if ( o instanceof Float)
						{
							entry = ""+ o;

						}
						else if ( o instanceof Double)
						{
							entry = ""+ o;

						}
						else
						{
							entry = o.toString();
						}
					}
					fields[ii].setAccessible(accessFlag);

					xml.append(varName + "=\"" + entry
							+ "\" ");
				}

				xml.append("/>");

			} // while end
			xml.append("</ROWDATA></DATAPACKET>");


		} catch (Exception e) {
			System.out.print(e);
		}
		return xml.toString();
	}
}
