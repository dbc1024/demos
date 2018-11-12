package com.ectrip.base.util;

/**
 * 生成和取SQL 2011 -12 -17 修改 在数据里生成 orid_sequence 在数据里生成 zhifu_sequence 目前支持ORACLE, 如果要用DB2以及SQL SERVER 要进行测试
 * 
 * @author lijin
 */
public class MakeOridsequence {
	/**
	 * 生成
	 * 
	 * @throws Exception
	 */
	public static void makeSequence() throws Exception {
		String ishsql = "select   *   from   user_sequences  where sequence_name ='ORID_SEQUENCE'";
		String hsql1 = " drop  SEQUENCE orid_sequence";
		String datatypestr = WebContant.DatabaseType.equalsIgnoreCase("oracle") ? "" : "  AS bigint ";

		StringBuffer sb = new StringBuffer();
		sb.append(" CREATE SEQUENCE orid_sequence  ");
		sb.append(datatypestr);
		sb.append("　　INCREMENT BY 1 　　START WITH 1 　　NOMAXVALUE 　　NOCYCLE  ");
		String qsql = "select orid_sequence.nextval  from dual";
		java.sql.Connection conn = null;
		DbConnection dbcon = new DbConnection();
		try {
			conn = dbcon.getConnection();
			java.sql.Statement stmt = conn.createStatement();
			java.sql.ResultSet rs = stmt.executeQuery(ishsql);
			if (rs.next()) {
				stmt.execute(hsql1);
			}
			stmt.execute(sb.toString());
			stmt.execute(qsql);

			stmt.close();
			sb = null;
		} catch (Exception e) {
			throw e;
		} finally {
			dbcon.close(conn);
			dbcon = null;
			// //session.close();
		}

	}
	
	/**
	 * 生成
	 * 
	 * @throws Exception
	 */
	public static void makeSequenceSpring() throws Exception {
		String ishsql = "select   *   from   user_sequences  where sequence_name ='ORID_SEQUENCE'";
		String hsql1 = " drop  SEQUENCE orid_sequence";
		String datatypestr = WebContant.DatabaseType.equalsIgnoreCase("oracle") ? "" : "  AS bigint ";

		StringBuffer sb = new StringBuffer();
		sb.append(" CREATE SEQUENCE orid_sequence  ");
		sb.append(datatypestr);
		sb.append("　　INCREMENT BY 1 　　START WITH 1 　　NOMAXVALUE 　　NOCYCLE  ");
		String qsql = "select orid_sequence.nextval  from dual";
		java.sql.Connection conn = null;
		DbConnection dbcon = new DbConnection();
		try {
			conn = dbcon.getConnection();
			java.sql.Statement stmt = conn.createStatement();
			java.sql.ResultSet rs = stmt.executeQuery(ishsql);
			if (rs.next()) {
				stmt.execute(hsql1);
			}
			stmt.execute(sb.toString());
			stmt.execute(qsql);

			stmt.close();
			sb = null;
		} catch (Exception e) {
			throw e;
		} finally {
			dbcon.close(conn);
			dbcon = null;
			// //session.close();
		}

	}

	/**
	 * 生成支付流水,产生流号
	 * 
	 * @throws Exception
	 */
	public static void makeZhuFuSequence() throws Exception {

		String ishsql = "select   *   from   user_sequences  where sequence_name ='ZHIFU_SEQUENCE'";
		String hsql1 = " drop  SEQUENCE zhifu_sequence";

		String datatypestr = WebContant.DatabaseType.equalsIgnoreCase("oracle") ? "" : "  AS bigint ";

		StringBuffer sb = new StringBuffer();

		sb.append(" CREATE SEQUENCE zhifu_sequence  ");
		sb.append(datatypestr);
		sb.append("　　INCREMENT BY 1 　　START WITH 1 　　NOMAXVALUE 　　NOCYCLE  ");

		String qsql = "select zhifu_sequence.nextval  from dual";
		java.sql.Connection conn = null;
		DbConnection dbcon = new DbConnection();
		try {
			conn = dbcon.getConnection();
			java.sql.Statement stmt = conn.createStatement();
			java.sql.ResultSet rs = stmt.executeQuery(ishsql);
			if (rs.next()) {
				stmt.execute(hsql1);
			}

			stmt.execute(sb.toString());
			stmt.execute(qsql);

			stmt.close();

			sb = null;

		} catch (Exception e) {
			throw e;
		} finally {
			dbcon.close(conn);
			dbcon = null;
			// //session.close();
		}

	}
}
