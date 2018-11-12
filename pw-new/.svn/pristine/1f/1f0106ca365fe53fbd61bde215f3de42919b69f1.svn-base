package com.ectrip.sys.syspar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.DbConnection;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.syspar.dao.ISysDeleteDataDAO;

public class SysDeleteDataDAO extends GenericDao implements ISysDeleteDataDAO {

	/**
	 * 删除表数据*
	 * Describe:
	 * @see com.ectrip.system.syspar.dao.idao.ISysDeleteDataDAO#deleteTableData(java.lang.String[], com.ectrip.model.syspar.Syslog)
	 * @param tablename
	 * @param syslog
	 * @author huangyuqi
	 * Date:2011-12-24
	 */
	public void deleteTableData(String[] tablename,Syslog syslog){
		
		Connection con =null;
		PreparedStatement ptmts =null;
		try{
			DbConnection c = new DbConnection();
			con = c.getConnection();
			
			StringBuffer tables = new StringBuffer();
			String hsql ="";
			for (int i = 0; i < tablename.length; i++) {
				tables.append(tablename[i]);
				hsql =" delete "+tablename[i];
				ptmts = con.prepareStatement(hsql);
				int a = ptmts.executeUpdate();
			}
			
			syslog.setStlg("0182");
			syslog.setBrief("删除表数据 ："  +tables);
			syslog.setNote("删除表数据 ："+tables);
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
		}catch(NamingException e){
			e.getStackTrace();
			try {
				ptmts.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("抛出个运行时异常");
			}
			throw new RuntimeException("SysDeleteDataDAO类出现异常！");
		}catch(SQLException e){
			e.getStackTrace();
			try {
				ptmts.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("抛出个运行时异常");
			}
			throw new RuntimeException("SysDeleteDataDAO类出现异常！");
		}finally{
			try {
				ptmts.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("抛出个运行时异常");
			}
		}
	}

	
	/**
	 * 根据表类型删除数据
	 * Describe:
	 * @auth:huangyuqi
	 * @param tabletype
	 * @param syslog
	 * return:void
	 * Date:2011-12-24
	 */
	public void deleteTableDataByType(String tabletype,Syslog syslog){
		Connection con =null;
		PreparedStatement ptmts =null;
		ResultSet rs =null;
		try{
			DbConnection c = new DbConnection();
			con = c.getConnection();
			String hsql="";
			
			String sql= " select pmva as pmva from sysparv5 where pmky='STDA' and spmcd ='"+tabletype+"' and isvalue=1  order by pmvb ";
			
			ptmts = con.prepareStatement(sql);
			rs = ptmts.executeQuery();
			StringBuffer tables= new StringBuffer();
			while(rs.next()){
				String table = rs.getString("pmva");	
				if("Sysparv5".toUpperCase().equals(table.toUpperCase())){
					continue;
				}else if("Espdutytab".toUpperCase().equals(table.toUpperCase())){
					continue;
				}else if("galsourceregiontab".toUpperCase().equals(table.toUpperCase())){
					continue;
				}else{
					tables.append(table+",");
					if("esfemployeetab".toUpperCase().equals(table.toUpperCase())){
						hsql=" delete "+table+" where empid <> 'admin' ";
					}else{
						hsql=" delete "+table;
					}
					ptmts = con.prepareStatement(hsql);
					int a = ptmts.executeUpdate();
				}
			}			
			
			syslog.setStlg("0182");
			syslog.setBrief("删除表数据 ："  +tabletype);
			syslog.setNote("删除表数据 ："+tables);
			syslog.setLogdatetime(Tools.getDayTimes());
			Long logid = this.getMaxPk("logid", "Syslog");
			syslog.setLogid(logid + 1);
			this.save(syslog);
			
		}catch(NamingException e){
			e.getStackTrace();
			try {
				rs.close();
				ptmts.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("抛出个运行时异常");
			}
			throw new RuntimeException("SysDeleteDataDAO类出现异常！");
		}catch(SQLException e){
			e.getStackTrace();
			try {
				rs.close();
				ptmts.close();
				con.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("抛出个运行时异常");
			}
			throw new RuntimeException("SysDeleteDataDAO类出现异常！");
		}finally{
			try {
				rs.close();
				ptmts.close();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException("抛出个运行时异常");
			}
		}
	}
	
}

