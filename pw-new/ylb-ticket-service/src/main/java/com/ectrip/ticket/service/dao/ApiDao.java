package com.ectrip.ticket.service.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.dao.idao.IGenericJdbcDao;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.sys.model.employee.Espdutytab;
import com.ectrip.ticket.service.dao.idao.IApiDao;

/**
 * 所有的API方法都集中在这个DAO里， 在这里调用API，Spring
 * 
 * @author LiJin
 * 
 */
public class ApiDao extends GenericDao implements IApiDao {
	private boolean isdebugsql = false;

	/**
	 * 读取职责表
	 */
	public List<Espdutytab> getEspdutytab() throws Exception {
		// TODO
		String hsql = " from Espdutytab ";
		return this.find(hsql);
	}

	/**
	 * 取出CB ClientDataSet的XML 格式的字符串,目前只有int ,float 用数据形,其它一律用字符形
	 * 
	 * @return 字符串 userid 可为空 pwd 也可为空
	 * @throws SQLException
	 * @throws Exception
	 */

	public String SpringCbXML(String sql, String userid, String pwd) throws SQLException, Exception {

		System.out.println("WEB ==sql====================> " + sql);

		IGenericJdbcDao genericJdbcDao = (IGenericJdbcDao) SpringUtil.getBean("iGenericJdbcDao");
		SqlRowSet rs = genericJdbcDao.getSqlRowSet(sql);
		System.out.println("rs =========rs==============>" + rs);
		String ls_xml = this.selectBc(rs);

		// System.out.println("ls_xml="+ls_xml);
		return ls_xml;
	}

	/**
	 * 根据SQL语句进行查询语句,返回LIST，LIST中存放MAP， MAP 中将存放按字段名，值的形式进行存放。 返回类型以及BC支持的格式。
	 * 
	 * @throws Exception
	 */
	public String selectBc(SqlRowSet rs) throws Exception {

		/**
		 * 如果返回为空，直接返回空
		 */
		if (rs == null) {
			return "";
		}
		String ss = "";
		// System.out.println("11111111111");
		Statement stmt = null;
		// ResultSet rs = null;
		int rsCount = 0; // 记录rs中当前所在行
		// System.out.println("222222222222222");
		StringBuffer xml = new StringBuffer();
		String entry = "";
		// System.out.println("33333333333333");
		/*
		 * <?xml version="1.0" standalone="yes"?> <DATAPACKET Version="2.0"> <METADATA> <FIELDS> <FIELD attrname="PMKY"
		 * fieldtype="string" SUBTYPE="FixedChar" WIDTH="4"/> <FIELD attrname="PMCD" fieldtype="string"
		 * SUBTYPE="FixedChar" WIDTH="4"/> <FIELD attrname="PMVL" fieldtype="string" WIDTH="100"/> <FIELD
		 * attrname="EPMV" fieldtype="string" WIDTH="100"/> </FIELDS> <PARAMS/> </METADATA> <ROWDATA> <ROW PMKY="CITY"
		 * PMCD="****" PMVL="城市" EPMV="CITY"/> <ROW PMKY="CITY" PMCD="0001" PMVL="北京" EPMV="BEIJIN"/> </ROWDATA>
		 * </DATAPACKET>
		 */
		xml.append("<?xml version=\"1.0\" standalone=\"yes\"?> "); // XML的头部信息
		xml.append(" <DATAPACKET Version=\"2.0\"><METADATA>");
		// System.out.println("666666666666");
		try {

			SqlRowSetMetaData resultsMetaData = rs.getMetaData();
			// System.out.println("resultsMetaData ====" + resultsMetaData);
			if (resultsMetaData == null) {
				return "";
			}

			int columnCount = resultsMetaData.getColumnCount();
			// System.out.println("columnCount ====" + columnCount);
			if (columnCount == 0) {
				return "";
			}

			xml.append("<FIELDS>");

			for (int i = 1; i < columnCount + 1; i++) {
				// PrintLogs(resultsMetaData.getColumnTypeName(i));
				// ;
				// System.out.println("type
				// "+resultsMetaData.getColumnTypeName(i));
				// System.out.println("columnCount ====" + i);

				// System.out.println("resultsMetaData.getColumnTypeName(i) ====" +
				// resultsMetaData.getColumnTypeName(i));

				if (resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("image") == false) {
					if (resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("int") == true
							|| resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("NUMBER")) {
						xml.append(" <FIELD ");
						xml.append("  attrname=\"" + resultsMetaData.getColumnName(i).trim() + "\"");
						xml.append(" 	fieldtype=\"r8\"/>");
					} else if (resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("float") == true) {
						xml.append(" <FIELD ");
						xml.append("  attrname=\"" + resultsMetaData.getColumnName(i).trim() + "\"");
						xml.append("  fieldtype=\"r8\"/>");
					} else if (resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("dateTime") == true) {
						xml.append(" <FIELD ");
						xml.append("  attrname=\"" + resultsMetaData.getColumnName(i).trim() + "\"");
						xml.append("  fieldtype=\"dateTime\"/>");
					} else if (resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("blob") == true) {
						xml.append(" <FIELD ");
						xml.append("  attrname=\"" + resultsMetaData.getColumnName(i).trim() + "\"");
						xml.append("  fieldtype=\"" + "string" + "\"");
						xml.append("  SUBTYPE=\"FixedChar\"");
						xml.append("  WIDTH=\"" + 1000 + "\"" + "/>");
						// System.out.println("BLOB;");
					} else {
						xml.append(" <FIELD ");
						xml.append("  attrname=\"" + resultsMetaData.getColumnName(i).trim() + "\"");
						xml.append("  fieldtype=\"" + "string" + "\"");
						xml.append("  SUBTYPE=\"FixedChar\"");
						xml.append("  WIDTH=\"" + resultsMetaData.getPrecision(i) + "\"" + "/>");
					}
				}
			}
			xml.append("</FIELDS><PARAMS/></METADATA>");
			// 新添结束

			xml.append("<ROWDATA>");
			// System.out.println("rs.next() ====" + rs.next());
			while (rs.next()) {

				rsCount = rsCount + 1;
				// System.out.println("rs.next() ====" + rsCount);
				xml.append("<ROW ");
				// this.PrintLogs("start");
				for (int i = 1; i < columnCount + 1; i++) {
					entry = "";
					// PrintLogs(resultsMetaData.getColumnTypeName(i));
					// PrintLogs("i="+(resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("blob")
					// == true));
					/*
					 * if (resultsMetaData.getColumnTypeName(i).equalsIgnoreCase("blob") == true) { //
					 * this.PrintLogs("44444444444444444444422222"); //
					 * this.PrintLogs(resultsMetaData.getColumnTypeName(i)); // this.PrintLogs("22222"); byte[] data =
					 * rs.byte[] ; String ss1 = ""; if (data != null) { ss1 = new String(data, "GBK"); }
					 * 
					 * entry = getTxtWithoutHTMLElement(ss1);
					 * 
					 * } else {
					 */

					ss = rs.getString(i);
					// this.PrintLogs("ss"+ss);
					if (ss != null && resultsMetaData.getColumnTypeName(i).equals("DATE") && ss.trim().length() != 10) {
						// 短日期，要转换
						entry = "20" + ss.replace('/', '-');
					} else {
						entry = ss;
					}

					if (resultsMetaData.getColumnTypeName(i).equals("VARCHAR")
							|| resultsMetaData.getColumnTypeName(i).equals("VARCHAR2")) {
						// this.PrintLogs("内容中有<>号:"+entry);
						entry = getTxtWithoutHTMLElement(entry);
						// this.PrintLogs("内容中有<>号:替换后:"+entry);
					}

					if (entry != null) {
						entry = entry.trim();
					} else {
						entry = ""; // 当库里字段有null时

					}
					xml.append(resultsMetaData.getColumnName(i) + "=\"" + entry + "\" ");
				}
				// FOR END
				xml.append("/>");
			}

			// } // while end
			xml.append("</ROWDATA></DATAPACKET>");

			// 关闭

		} catch (Exception e) {
			System.out.println(e);
			throw e;

		}
		// System.out.println(xml.toString());
		return xml.toString();

	}

	static String getTxtWithoutHTMLElement(String element) {
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
		// repaceEntities(txt,"&","&");
		// repaceEntities(txt,"<","<");
		// repaceEntities(txt,">",">");
		// repaceEntities(txt,""","\"");
		// repaceEntities(txt," ","");

		return txt.toString();
	}

	/**
	 * 根据HIBNATE转换为XML
	 * 
	 * @param Sql
	 * @return
	 */
	public String HibNateToBcXml(String Sql) {
		StringBuffer xml = new StringBuffer();
		try {
			System.out.println("run HibNateToBcXml====>");
		
			System.out.println("genericDao=====>"+SpringUtil.class);
			IGenericDao genericDao = (GenericDao) SpringUtil.getBean("genericDao");
			System.out.println("genericDao=====>"+genericDao);
			System.out.println("HibNateToBcXml=====>");
			// C++ Builder XML 头
			xml.append("<?xml version=\"1.0\" standalone=\"yes\"?> "); // XML的头部信息
			xml.append(" <DATAPACKET Version=\"2.0\"><METADATA>");
			System.out.println("Sql=====>"+Sql);
			List<Map> list = genericDao.findBySqlToMap(Sql);
			xml.append("<FIELDS>");
			if (list.size() > 0) {
				System.out.println("HibNateToBcXml====1111111111=>");
				Map map = (Map) list.get(0);
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();

					Object key = entry.getKey();
					Object val = entry.getValue();
					xml.append(" <FIELD ");
					xml.append("  attrname=\"" + key.toString().trim() + "\"");
					xml.append("  fieldtype=\"" + "string" + "\"");
					xml.append("  SUBTYPE=\"FixedChar\"");
					xml.append("  WIDTH=\"" + 200 + "\"" + "/>");

				}

			}
			xml.append("</FIELDS><PARAMS/></METADATA>");
			// 新添结束

			xml.append("<ROWDATA>");
			// System.out.println("rs.next() ====" + rs.next());
            
			for (Map map : list) {
				xml.append("<ROW ");
				System.out.println("HibNateToBcXml===33333==>");
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					System.out.println("HibNateToBcXml===33333==>");
					Object key = entry.getKey();
					Object val = entry.getValue();
					xml.append(key.toString() + "=\"" + val.toString().trim() + "\" ");
				}
				xml.append("/>");
			}

			// } // while end
			xml.append("</ROWDATA></DATAPACKET>");

		} catch (Exception e) {
			System.out.println("run Exception");
			System.out.println(e.toString());
			xml = new StringBuffer();
		}
		return xml.toString();
	}
	
	/**
	 * 根据HIBNATE转换为XML
	 * 
	 * @param Sql
	 * @return
	 */
	public String HibnateToBcXml(String Sql) {
		StringBuffer xml = new StringBuffer();
		try {
			System.out.println("run HibNateToBcXml====>");
		
			System.out.println("genericDao=====>"+SpringUtil.class);
			IGenericDao genericDao = (GenericDao) SpringUtil.getBean("genericDao");
			System.out.println("genericDao=====>"+genericDao);
			System.out.println("HibNateToBcXml=====>");
			// C++ Builder XML 头
			xml.append("<?xml version=\"1.0\" standalone=\"yes\"?> "); // XML的头部信息
			xml.append(" <DATAPACKET Version=\"2.0\"><METADATA>");
			System.out.println("Sql=====>"+Sql);
			List<Map> list = genericDao.find(Sql);
			xml.append("<FIELDS>");
			if (list.size() > 0) {
				System.out.println("HibNateToBcXml====1111111111=>");
				Map map = (Map) list.get(0);
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();

					Object key = entry.getKey();
					Object val = entry.getValue();
					xml.append(" <FIELD ");
					xml.append("  attrname=\"" + key.toString().trim() + "\"");
					xml.append("  fieldtype=\"" + "string" + "\"");
					xml.append("  SUBTYPE=\"FixedChar\"");
					xml.append("  WIDTH=\"" + 200 + "\"" + "/>");

				}

			}
			xml.append("</FIELDS><PARAMS/></METADATA>");
			// 新添结束

			xml.append("<ROWDATA>");
			// System.out.println("rs.next() ====" + rs.next());
            
			for (Map map : list) {
				xml.append("<ROW ");
				System.out.println("HibNateToBcXml===33333==>");
				Iterator iter = map.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					System.out.println("HibNateToBcXml===33333==>");
					Object key = entry.getKey();
					Object val = entry.getValue();
					xml.append(key.toString() + "=\"" + val.toString().trim() + "\" ");
				}
				xml.append("/>");
			}

			// } // while end
			xml.append("</ROWDATA></DATAPACKET>");

		} catch (Exception e) {
			System.out.println("run Exception");
			System.out.println(e.toString());
			xml = new StringBuffer();
		}
		return xml.toString();
	}
}
