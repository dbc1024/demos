package com.ectrip.base.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import com.ectrip.base.dao.idao.IGenericJdbcDao;

/**
 * 执行纯JDBC的SPRING支持 所有的需要调用纯JDBC的地主，
 * 通过本DAO进行。 其它地方一律调用这个DAO中的方法。
 * 
 * @author 李进
 * 
 */
public class GenericJdbcDao implements IGenericJdbcDao {
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * 运行纯的SQL查询语句。
	 */
	public void insert(String sql) {
		// TODO Auto-generated method stub
		jdbcTemplate.execute(sql);

	}

	/**
	 * 根据SQL语句进行查询语句,返回LIST，LIST中存放MAP， MAP 中将存放按字段名，值的形式进行存放。
	 */
	public List select(String where) {

		return (List) jdbcTemplate.query(where, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {

				// 在这里转换为LIST

				List list = new ArrayList();
				ResultSetMetaData rsm = resultSet.getMetaData();
				while (resultSet.next()) {

					// ResultSetMetaData 接口创建一个对象，可使用该对象找出 ResultSet 中的各列的类型和属性。
					int size = rsm.getColumnCount(); // 每行列数
					HashMap row = new HashMap();
					for (int j = 1; j <= size; j++) {
						row.put(rsm.getColumnLabel(j), resultSet.getObject(j));
					}
					list.add(row);

				}

				return list;

			}
		});
	}

	/**
	 * 根据条件返回 SqlRowSet
	 * 
	 * @param where
	 * @return
	 */
	public SqlRowSet getSqlRowSet(String where) {
		SqlRowSet rs = null;

		try {
			rs = jdbcTemplate.queryForRowSet(where, null);
		} catch (Exception e) {
			System.out.println(" SqlRowSet===>"+e.toString());
			return null;
		}
		return rs;
	}

	/**
	 * 根据SQL语句进行查询语句,返回LIST，LIST中存放MAP， MAP 中将存放按字段名，值的形式进行存放。 返回类型以及BC支持的格式。
	 * 
	 * @throws Exception
	 */
	public String selectBc(String where) throws Exception {
		SqlRowSet rs = null;

		try {
			rs = jdbcTemplate.queryForRowSet(where, null);
		} catch (Exception e) {
			return "";
		}

		/**
		 * 如果返回为空，直接返回空
		 */
		if (rs == null) {
			return "";
		}
		String ss = "";
		System.out.println("11111111111");
		Statement stmt = null;
		// ResultSet rs = null;
		int rsCount = 0; // 记录rs中当前所在行
		System.out.println("222222222222222");
		StringBuffer xml = new StringBuffer();
		String entry = "";
		System.out.println("33333333333333");
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
		System.out.println("666666666666");
		try {

			SqlRowSetMetaData resultsMetaData = rs.getMetaData();
			System.out.println("resultsMetaData ====" + resultsMetaData);
			if (resultsMetaData == null) {
				return "";
			}

			int columnCount = resultsMetaData.getColumnCount();
			System.out.println("columnCount ====" + columnCount);
			if (columnCount == 0) {
				return "";
			}

			xml.append("<FIELDS>");

			for (int i = 1; i < columnCount + 1; i++) {
				// PrintLogs(resultsMetaData.getColumnTypeName(i));
				// ;
				// System.out.println("type
				// "+resultsMetaData.getColumnTypeName(i));
				System.out.println("columnCount ====" + i);

				System.out.println("resultsMetaData.getColumnTypeName(i) ====" + resultsMetaData.getColumnTypeName(i));

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
			//System.out.println("rs.next() ====" + rs.next());
			while (rs.next()) {

				rsCount = rsCount + 1;
				System.out.println("rs.next() ====" + rsCount);
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

	/**
	 * 运行SQL语句
	 */
	public void update(String how) {
		jdbcTemplate.execute(how);

	}

	/**
	 * 运行SQL语句
	 */
	public void execute(String how) {
		jdbcTemplate.execute(how);

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

		return txt.toString().replaceAll("[\\n]", "").replace("[&nbsp]", "").replaceAll("[;]", "");
	}

}