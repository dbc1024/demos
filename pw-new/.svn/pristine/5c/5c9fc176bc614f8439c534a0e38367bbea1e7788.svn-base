package com.ectrip.base.util;

import java.lang.reflect.Field;
import java.util.List;

/**
*
*
*List 转换成 ResultBean,用于 WEB SERVICE 进行数据交换.
* <p>Description: 张网电子商务</p>
*
* <p>Copyright: Copyright (c) 2006</p>
*
* <p>Company: 鼎游</p>
*
* @author 李进
* @version 1.0
*/
public class ListToResultBean {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 将List 中存和的Bean 转找为　Result Bean
	 * 用于ＷＥＢ　ＳＥＲＶＩＣＥ　进行数据交换
	 * 
	 * @param list
	 * @return
	 */
	public static ResultBean ToResultBean(List list) {
		 System.out.println("111111111111111111111111111111");
		ResultBean rs = new ResultBean();
		int rsCount = 0;
		String entry = "";
		Object o1 = null;
		try {

			if (list.size() > 0) {
				o1 = list.get(0);
			}
			rs.setRowsCount(list.size()); // 设置行数

			Field[] fields = o1.getClass().getDeclaredFields();

			rs.setColumnCount(fields.length); // 设置字段数

			String filed[] = new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				filed[i] = fields[i].getName();

			}

			rs.setColumnNames(filed); // 设置字段名

			for (int row = 0; row < list.size(); row++) {

				String[] values = new String[fields.length];
				rsCount = rsCount + 1;

				Object f = list.get(row);
				// Field[] fieldsmy = f.getClass().getDeclaredFields();
				for (int ii = 0; ii < fields.length; ii++) {

					String varName = fields[ii].getName();
					boolean accessFlag = fields[ii].isAccessible();
					fields[ii].setAccessible(true);
					Object o = fields[ii].get(f);
					if (o == null) {
						entry = "";
					} else {
						if (o instanceof Integer) {
							entry = "" + o;
						} else if (o instanceof Float) {
							entry = "" + o;

						} else if (o instanceof Double) {
							entry = "" + o;

						} else {
							entry = o.toString();
						}
					}
					fields[ii].setAccessible(accessFlag);
					values[ii] = entry;

				}
				

				rs.addRow(values);


			} // while end

		} catch (Exception e) {
			System.out.print(e);
		}
		 System.out.println("2222222222222222222222222");
		return rs;
	}
}
