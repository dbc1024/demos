package com.ectrip.base.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
/**
 * Map 转换为 ResultBean 
 * 用于web service 数据交换
 * @author lijin
 *
 */
public class MapToResultBean {
	public static ResultBean toResultBean(List<Map> list){
		ResultBean bean = new ResultBean();
		int rowCount = list.size();//行数
		int columnCount = 0;//列数
		bean.setRowsCount(rowCount);
	  // System.out.println("111111111111111111111111111111");
		for (int j = 0; j < list.size(); j ++) {
			Map map =  list.get(j);
			if(j == 0){
				columnCount = map.keySet().size();
				bean.setColumnCount(columnCount);
				String[] arr = (String[]) map.keySet().toArray(new String[0]);
				bean.setColumnNames(arr);
			}
			String[] rows = new String[columnCount];
			for(int i = 0; i < columnCount; i ++){
				rows[i] = map.get(bean.getColumnName(i))+"";
			}
			bean.addRow(rows);
		}
		//System.out.println("22222222222222222222222222222222222");
		return bean;
	}
}
