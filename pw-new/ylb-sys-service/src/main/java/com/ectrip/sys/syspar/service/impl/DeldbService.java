package com.ectrip.sys.syspar.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.ectrip.base.service.GenericService;
import com.ectrip.base.util.DbConnection;
import com.ectrip.sys.syspar.service.IDeldbService;

public class DeldbService extends GenericService implements IDeldbService {
	public void delDB(List sqllist){
		DbConnection dbConnection = new DbConnection();
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = dbConnection.getConnection();
			if(sqllist!=null&&sqllist.size()>0){
				for(int i=0;i<sqllist.size();i++){
					String sql = (String) sqllist.get(i);
					pst = conn.prepareStatement(sql);
					pst.execute();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				pst.close();
				dbConnection.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

