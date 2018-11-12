package com.ectrip.ticket.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.ectrip.base.util.ResultBean;

public class ResultBeanToJson {

	/**将ResultBean 数据转换为JSON
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		ResultBean rs = new ResultBean();

		rs.setColumnCount(2);
		rs.setColumnNames(new String[] { "returnstats", "message" });
		rs.addRow(new String[] { "22121", "6666" });
		rs.addRow(new String[] { "0001", "a001" });
		rs.addRow(new String[] { "0002", "a002" });

		ResultBeanToJson.ResultToJson(rs);
	}

	/**
	 * 结果集转到JSON
	 * @return
	 */
	public static String ResultToJson(ResultBean rs) throws Exception{

		List list  = new ArrayList();
		for ( int i =0; i < rs.getRowsCount(); i++)
		{

			Map m = new HashMap();
			String[]  colnames = rs.getColumnNames() ;
			String[]  values   = rs.getRow(i);
			for ( int a =0 ; a < colnames.length ; a++)
			{
				m .put(colnames[a], values[a]);
			}

			list.add(m);

		}

		String requestParam = JSON.toJSONString(list);
		System.out.println( "requestParam="+requestParam);
		return requestParam;

		//return   JSON.toJSONString(list);
	}

}
