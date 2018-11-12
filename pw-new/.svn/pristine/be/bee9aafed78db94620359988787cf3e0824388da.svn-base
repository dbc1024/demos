package com.ectrip.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * <p>
 * Title:取得数据库的联结,所有应用的数据库接口都必须从这里进行数据连接的入口
 * </p>
 * <p>
 * Description: 取得数据库的联结
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: DY
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class DbConnection { 
	// 联结用的对象
	private Connection conn = null;

	private Context initCtx = null;

	// 数据源名
	// "java:comp/env/jdbc/ectrip"
	private static final String DSNAME = WebContant.JNDI;

	// private static final String DSNAME = "java:comp/env/jdbc/ectripnew";

	public Connection getConnection() throws NamingException{
		try {
			/*
			 * Hashtable parms = new Hashtable();
			 * parms.put(Context.INITIAL_CONTEXT_FACTORY,
			 * "com.ibm.websphere.naming.WsnInitialContextFactory"); Context
			 * contx = new InitialContext(parms); DataSource ds = (DataSource)
			 * contx.lookup(DSNAME);
			 */

			Context initCtx = new InitialContext();
			DataSource ds = (DataSource) initCtx.lookup(DSNAME);
			conn = ds.getConnection();
			// initCtx.close();
		
			// contx.close();
			return conn;
			// return getMsSqlConn("192.168.0.100","lushan","sa","sa");
		} catch (Exception e) {
			System.out.println("数据库连接异常！--"+DSNAME);
            e.printStackTrace();
		}
		return conn;
	}

	public Connection getConnection1() throws NamingException {
		String className = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.0.105:1521:orcl";
		String user = "zjj";
		String password = "zjj";
		try {
			Class.forName(className).newInstance();
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("数据库连接异常！--"+DSNAME);
            e.printStackTrace();
		}
		return conn;
	}

	
	public Connection getMsSqlConn(String Ip, String DataName, String Userid,
			String Pwd) throws Exception {
		Connection conn = null;
		try {
			// Class.forName("com.ibm.db2.jdbc.app.DB2Driver ").newInstance();
			String url = "jdbc:jtds:sqlserver://" + Ip + ":1433/" + DataName;
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, Userid, Pwd);

		} catch (ClassNotFoundException e) {
			throw new Exception("ClassNotFoundException");

		}
		return conn;
	}

	/**
	 * 根据数据源名称取数据源,并取出联结, 这里没有对严格的数据格式认证,请程序员小使用.
	 * 
	 * @param myDSNAME
	 *            String
	 * @return Connection
	 */
	public Connection getConnection(String myDSNAME) throws Exception  {
		try {
			
				Context initCtx = new InitialContext();
				
				DataSource ds = (DataSource) initCtx.lookup(myDSNAME);
				conn = ds.getConnection();
		//	conn = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接异常！DSNAME--" + "[java:comp/env/jdbc/"
					+ myDSNAME + "]" + e.getMessage());

		}
		return conn;
	}

	public void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭PreparedStatement失败");
			}
			pstmt = null;
		}
	}

	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭Statement失败");
			}
			stmt = null;
		}
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("关闭ResultSet失败");
			}
			rs = null;
		}
	}

	public void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * 关闭数据库联结
	 * 
	 * @param conn
	 *            Connection
	 */
	public void close(Connection conn) throws javax.naming.NamingException,
			SQLException {
		try {

			if (conn == null) {
				return;
			}

			if (conn.isClosed() == false) {
				conn.close();
				conn = null;
			}

			initCtx.close();
			initCtx = null;

		} catch (SQLException sqle) {
			System.out.println("正常情况释放连接异常！-- " + sqle);
		}
		/** 下面代码李进于2004-6-18晚上加 开始 */
		finally {
			try {
				if (conn == null) { // 如果连结对象已经无效
					return;
				}
				if (conn.isClosed() == false) { // 连接没有关闭时
					conn.close();
					conn = null;
				}
				if (initCtx == null) {
					return;
				}
				if (initCtx != null) {
					initCtx.close();
				}
			} catch (java.sql.SQLException se) {
				System.out.println("关闭数据库出现错误" + se);
			}
		}
		/** 李进加代码结束 */

	}

}
