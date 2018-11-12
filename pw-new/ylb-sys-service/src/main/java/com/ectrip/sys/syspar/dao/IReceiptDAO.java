package com.ectrip.sys.syspar.dao;

public interface IReceiptDAO {
	
	public String getSalesVoucher(String isalevoucherid, String iscenicid,int type);
	public String getordermessage(String orid, String iscenicid,int type);

}
