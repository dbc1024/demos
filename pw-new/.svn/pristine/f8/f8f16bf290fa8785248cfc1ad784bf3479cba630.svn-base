package com.ectrip.base.util;

import java.util.Vector;
import java.util.Collections;
import java.util.Comparator;

public class ResultBean implements java.io.Serializable {

	private Vector selectResult = new Vector(); // 存放查询结果集
	

	private int columnCount;
	private int rowsCount;
	/**
	 * 分页显示的起始数
	 */
	private int startNum = 0;
	/**
	 * 分页显示的结束数
	 */
	private int endNum = 0;
	/**
	 * 总共的页面数
	 */
	private int totalPages; //
	/**
	 * 满足条件的总记录数
	 */
	private int totalNumber = 0; //
	/**
	 * 每页显示的条数
	 */
	private int eachPageNumber = 0; //
	/**
	 * 当前显示的页数
	 */
	private int currentPage; //
	/**
	 * 存放字段名的数组
	 */
	private String[] columnNames; //

	/**
	 * ResultBean constructor comment.
	 */
	public ResultBean() {
		super();
	}

	/**
	 * 临时，没有同步8个属性
	 * 
	 * @param newRow
	 */
	public void addRow(String[] newRow) {

		selectResult.addElement(newRow);
		rowsCount = selectResult.size();
		columnCount = newRow.length;

	}

	/**
	 * 删除bean的一行记录，没有同步8个属性
	 * 
	 * @param i
	 */
	public void removeRow(int i) {
		selectResult.removeElementAt(i);
		// 2000-10-10
		rowsCount = selectResult.size();
		//
	}

	public String[] getRow(int i) {

		return (String[]) selectResult.elementAt(i);
	}

	/**
	 * 返回结果集的列数. Creation date: (2000-8-12 14:12:07)
	 * 
	 * @return int
	 */
	public int getColumnCount() {
		return columnCount;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:06:14)
	 * 
	 * @return int
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:04:21)
	 * 
	 * @return int
	 */
	public int getEachPageNumber() {
		return eachPageNumber;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:02:33)
	 * 
	 * @return int
	 */
	public int getEndNum() {
		return endNum;
	}

	/**
	 * 取到具体显示的字段值 Creation date: (2000-8-12 11:45:24)
	 * 
	 * @return java.lang.String
	 * @param rownum
	 *            int 行数
	 * @param colnum
	 *            int 列数
	 * @exception java.lang.Exception
	 *                The exception description.
	 */
	public String getResult(int rownum, int colnum) {

		if (selectResult.isEmpty()) {
			return "";

		} else {

			String[] tempRow = (java.lang.String[]) selectResult.elementAt(rownum);
			return tempRow[colnum];

		}

	}

	/**
	 * 重载getResult()方法 通过行数、字段名取到值
	 * 
	 * 
	 */
	public String getResult(int rownum, String columName) {
		int colnum = -1;
		String result = "";
		for (int j = 0; j < columnCount; j++) {

			if (columnNames[j].equalsIgnoreCase(columName)) {
				colnum = j;
				break;
			}
		}

		if (colnum != -1) {
			// 有此字段
			result = getResult(rownum, colnum);

		}

		return result;

	}

	/**
	 * 取字段名，i 表示取第几个 通过行数、字段名取到值
	 */
	public String getColumnName(int colid) {

		if (colid > columnNames.length) {
			return "";
		}
		return columnNames[colid];
	}

	/**
	 * 返回结果集的行数 Creation date: (2000-8-12 14:12:54)
	 * 
	 * @return int
	 */
	public int getRowsCount() {
		return rowsCount;
	}

	public Vector getSelectResult() {
		return selectResult;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:01:32)
	 * 
	 * @return int
	 */
	public int getStartNum() {
		return startNum;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:09:56)
	 * 
	 * @return int
	 */
	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalNumber() {
		return totalNumber;

	}

	public String[] getColumnNames() {
		return columnNames;

	}

	public void setColumnCount(int colCount) {
		columnCount = colCount;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:06:14)
	 * 
	 * @param newCurrentPage
	 *            int
	 */
	public void setCurrentPage(int newCurrentPage) {
		currentPage = newCurrentPage;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:04:21)
	 * 
	 * @param newEachPageNumber
	 *            int
	 */
	public void setEachPageNumber(int newEachPageNumber) {
		eachPageNumber = newEachPageNumber;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:02:33)
	 * 
	 * @param newEndNum
	 *            int
	 */
	public void setEndNum(int newEndNum) {
		endNum = newEndNum;
	}

	public void setRowsCount(int rCount) {
		rowsCount = rCount;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:01:32)
	 * 
	 * @param newStartNum
	 *            int
	 */
	public void setStartNum(int newStartNum) {
		startNum = newStartNum;
	}

	/**
	 * Insert the method's description here. Creation date: (2000-8-27 7:09:56)
	 * 
	 * @param newTotalPages
	 *            int
	 */
	public void setTotalPages(int newTotalPages) {
		totalPages = newTotalPages;
	}

	public void setTotalNumber(int newTotalNumbers) {
		totalNumber = newTotalNumbers;
	}

	public void setColumnNames(String[] newColumnNames) {
		columnNames = newColumnNames;
	}

	public String toCommString() {

		String dataMessage = "<begin>";

		// 获得头信息
		dataMessage = dataMessage + "<columnCount>" + getColumnCount() + "</columnCount>";
		dataMessage = dataMessage + "<rowsCount>" + getRowsCount() + "</rowsCount>";
		dataMessage = dataMessage + "<startNum>" + getStartNum() + "</startNum>";
		dataMessage = dataMessage + "<endNum>" + getEndNum() + "</endNum>";
		dataMessage = dataMessage + "<totalPages>" + getTotalPages() + "</totalPages>";
		dataMessage = dataMessage + "<totalNumber>" + getTotalNumber() + "</totalNumber>";
		dataMessage = dataMessage + "<eachPageNumber>" + getEachPageNumber() + "</eachPageNumber>";
		dataMessage = dataMessage + "<currentPage>" + getCurrentPage() + "</currentPage>";

		// 获得数据信息

		try {

			dataMessage = dataMessage + "<data>";
			for (int i = 0; i < getRowsCount(); i++) {
				dataMessage = dataMessage + "<r>";
				for (int j = 0; j < getColumnCount(); j++) {
					dataMessage = dataMessage + "<c>" + getResult(i, j) + "</c>";
				}

				dataMessage = dataMessage + "</r>";

			}
			dataMessage = dataMessage + "</data>";

		} catch (Exception e) {
			dataMessage = "<error>" + e.toString() + "</error>";

		}

		dataMessage = dataMessage + "</begin>";

		return dataMessage;

	}

	/* 李进加begin */

	/**
	 * 自己用的比较类
	 * <p>
	 * Title:
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2003
	 * </p>
	 * <p>
	 * Company:
	 * </p>
	 * 
	 * @author not attributable
	 * @version 1.0
	 */

	class MyComparator implements Comparator {
		int ColNum = 0;

		public MyComparator(int col) {
			ColNum = col;
		}

		public int compare(Object o1, Object o2) {
			String[] s1 = (String[]) o1;
			String[] s2 = (String[]) o2;
			String a = s1[ColNum];
			String b = s2[ColNum];
			return a.compareTo(b);
		}

	}

	/**
	 * 对RESULT 结果集进行排序,目前只支持一个字段的排序 排序数据列表,这个方法是用列号进行排序.如果你想且两个字段的合进 行排序,主在SQL语句中将两列加在一起.
	 * 
	 * @param ColNum
	 */

	public void sort(int ColNum) {
		if (ColNum > this.columnCount) {
			return;
		}
		Collections.sort(this.selectResult, new MyComparator(ColNum));
	}

	/**
	 * 对RESULT 结果集进行排序,目前只支持一个字段的排序 排序数据列表,这个方法是用列名进行排序.
	 * 
	 * @param ColNum
	 */

	public void sort(String columName) {
		int colnum = -1;
		for (int j = 0; j < columnCount; j++) { // 李进于 2003.6.6 日修改
			if (columnNames[j].equalsIgnoreCase(columName)) {
				colnum = j;
			}
		}

		if (colnum != -1) {
			sort(colnum);
		}
	}

	/* 李进加end */
}
