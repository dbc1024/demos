package com.ectrip.ticket.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Vector;
import com.ectrip.base.util.ResultBean;
import com.ectrip.ticket.service.model.Ticket;
import com.ectrip.ticket.service.model.TripControl;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * 工具类
 * @author LiuJianwen
 *
 */
public class SaleUtil {


	public static final  SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
	public static final  SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
	public static final  SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final  SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM-dd");
	public static final  SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final  SimpleDateFormat format5 = new SimpleDateFormat("HH:mm");
	public static final  SimpleDateFormat format6 = new SimpleDateFormat("HH:mm:ss");
	public static final  SimpleDateFormat format7 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	public static final  String USER_DIR = System.getProperty("user.dir");



	/**
	 * 把ResultBean转换成Map
	 * @param bean 要转换的ResultBean
	 * @param id_columnNum 作为id的列
	 * @param showName_clolumnNum 要显示的列
	 * @return
	 */
	public static Map<String, String> resultBeanToMap(final ResultBean bean, String id_columnNum, String showName_clolumnNum){
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < bean.getRowsCount(); i ++) {
			map.put(bean.getResult(i, showName_clolumnNum), bean.getResult(i, id_columnNum));
		}
		return map;
	}

	/**
	 * 把ResultBean行数据转换成二维数组,方便JTABLE取值
	 * @param bean 要转换的resultBean
	 * @param array 矫正表头排序,其长度与getColumnCount()一致
	 * @return
	 */
	public static String[][] resultBeanToArray(final ResultBean bean,String[] array){
		String[][] arr = new String[bean.getRowsCount()][array.length];
		for(int i = 0; i < bean.getRowsCount(); i ++){
			for(int j = 0; j < array.length; j ++){
				arr[i][j] = bean.getResult(i, array[j]);
			}
		}
		return arr;
	}

	/**
	 * 把ResultBean的某列值转换成字符串数组，方便JCombobox取值
	 * @param bean 要转换的resultBean
	 * @param columName 字段名
	 * @return
	 */
	public static String[] resultBeanToArr(final ResultBean bean,String columName){
		String[] arr = new String[bean.getRowsCount()];
		for (int i = 0; i < arr.length; i ++) {
			arr[i] = bean.getResult(i, columName);
		}
		return arr;
	}
	/**
	 * 展示竹筏数量信息
	 * @param model
	 */
	public static void showTripCount(DefaultTableModel model,ResultBean ... beans){
		model.getDataVector().clear();//清除信息
		Vector<String> v;
		Map<String,TripControl> map = new TreeMap<String,TripControl>();//进行排序
		List<Map<String,TripControl>> list = new ArrayList<Map<String,TripControl>>();
		List<TripControl> temp;
		for (ResultBean bean : beans) {
			temp = SaleUtil.convertResultBeanToList(TripControl.class, bean);
			for (TripControl tc : temp) {
				map.put(tc.getStarttime(), tc);
			}
			list.add(SaleUtil.listToMap1("starttime",temp));
		}

		String key;
		TripControl tc;
		for(Map.Entry<String, TripControl> en : map.entrySet()){
			key = en.getKey();
			tc = en.getValue();
			v = new Vector<String>();
			v.add(tc.getTripname()+"("+key+")");
			for (Map<String,TripControl> tcmap : list) {
				tc = tcmap.get(key);
				try {
					v.add(tc==null?"/":((Integer.parseInt(tc.getSalablenumber()) - Integer.parseInt(tc.getSoldnumber()))+""));
				} catch (Exception e) {
					e.printStackTrace();
					v.add("/");
				}
			}
			model.addRow(v);
		}



	}
	/**
	 * 展示竹筏数量信息
	 * @param talbe
	 * @param colIndex 列索引
	 * @param bean
	 */
	public static void showTripCount(JTable table,Integer colIndex,ResultBean bean){
		if(colIndex == null || bean == null) return;
		int j =  bean.getRowsCount();
		for(int i = 0; i < table.getRowCount(); i ++){
			try {
				table.setValueAt(i>=j?"/":(Integer.parseInt(bean.getResult(i, "salablenumber")) - Integer.parseInt(bean.getResult(i, "soldnumber"))), i, table.convertColumnIndexToModel(colIndex));
			} catch (Exception e) {
				e.printStackTrace();
				table.setValueAt("/", i, table.convertColumnIndexToModel(colIndex));
			}
		}
		//		table.updateUI();
	}

	public static String[] resultBeanToArr(final ResultBean bean){
		String[] arr = new String[bean.getRowsCount()];
		for (int i = 0; i < arr.length; i ++) {
			arr[i] = (Integer.parseInt(bean.getResult(i, "soldnumber"))) + "/"+Integer.parseInt(bean.getResult(i, "salablenumber"));
		}
		return arr;
	}
	/**
	 * 把ResultBean 转换成 model list
	 * @param bean
	 * @param cls
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>List<T> convertResultBeanToList(ResultBean bean, Class<T> cls){
		List<T> objectList = new ArrayList<T>();
		List<Field> fieldsList = new ArrayList<Field>();
		T t = null ;
		Constructor[] cons;
		Field[] fields;

		fields = cls.getDeclaredFields();
		cons = cls.getConstructors();

		for (int i = 0;i < fields.length; i ++ ) {
			if(fields[i].getModifiers()== 2){//如果xiao于8,非静态
				fieldsList.add(fields[i]);
			}
		}
		for(int i = 0 ;i < bean.getRowsCount();i++){
			Object[] objs = new Object[fieldsList.size()];
			for(int j = 0; j < objs.length; j ++){

				objs[j] = bean.getResult(i, fieldsList.get(j).getName());
			}
			for (Constructor<T> constructor : cons) {
				try {
					t = constructor.newInstance( objs);
				} catch (Exception e) {
				}
			}
			objectList.add(t);
		}
		return objectList;
	}
	/**
	 * 把多个ResultBean 转换成 model list
	 * @param beans
	 * @param cls
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T>List<T> convertResultBeanToList(Class<T> cls,ResultBean ... beans){
		List<T> objectList = new ArrayList<T>();
		List<Field> fieldsList = new ArrayList<Field>();
		T t = null ;
		Constructor[] cons;
		Field[] fields;

		fields = cls.getDeclaredFields();
		cons = cls.getConstructors();

		for (int i = 0;i < fields.length; i ++ ) {
			if(fields[i].getModifiers()== 2){//如果xiao于8,非静态
				fieldsList.add(fields[i]);
			}
		}
		for(ResultBean bean : beans){
			for(int i = 0 ;i < bean.getRowsCount();i++){
				Object[] objs = new Object[fieldsList.size()];
				for(int j = 0; j < objs.length; j ++){
					objs[j] = bean.getResult(i, fieldsList.get(j).getName());
				}
				for (Constructor<T> constructor : cons) {
					try {
						t = constructor.newInstance( objs);
					} catch (Exception e) {
					}
				}
				objectList.add(t);
			}
		}
		return objectList;
	}



	/**
	 * 对Ticket进行包装
	 * @param tickets
	 * @return
	 */
	@SuppressWarnings("unused")
	public static List<Ticket> packTicketList(List<Ticket> tickets){
		List<Ticket> ticketList = new ArrayList<Ticket>();

		for (Ticket ticket : tickets) {
			//			if("1".equals(ticket.getIscansale())){//如果受限
			//				ResultBean bean = SaleApi.getInstance().getp
			//			}
		}

		return ticketList;
	}

	/**
	 * 把list轉換成Map
	 * @param <K>
	 * @param <T>
	 * @param key
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K,T>Map<K,T> listToMap1( K key,List<T> list){
		Map<K,T> map = new HashMap<K, T>();
		Field field;
		for (T t : list) {
			try {
				field = t.getClass().getDeclaredField((String) key);
				field.setAccessible(true);
				Object k = field.get(t);
				map.put((K) k, t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}
	/**
	 * 把list轉換成Map
	 * @param <K>
	 * @param <T>
	 * @param key
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <K,T>Map<K,T> listToMap( K key,List<T> list, Map<K,T> map){
		if(map == null) return listToMap1(key,list);
		map.clear();
		Field field;
		for (T t : list) {
			try {
				field = t.getClass().getDeclaredField((String) key);
				field.setAccessible(true);
				Object k = field.get(t);
				map.put((K) k, t);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}


	/**
	 * 根据date + trip id 对竹筏数量进行统计
	 * @param map
	 * @param dateTrip
	 */
	public static void statistics(Map<String,Integer> map,String dateTrip,int count){
		if(map.containsKey(dateTrip)){
			int count_ = map.get(dateTrip)+count;
			map.put(dateTrip, count_);
			return;
		}
		map.put(dateTrip, count);

	}
	/**
	 * 退票专用,根据条码号,进行退票数据进行组合 //"票号#产品&产品：票号#产品&产品"
	 * @param map
	 * @param printNo
	 * @param ticketTypeId
	 */
	public static void statistics(Map<String,String> map,String printNo,String ticketTypeId){
		if(map.containsKey(printNo)){

			String ticketTypeIds = map.get(printNo);
			ticketTypeIds += ("&"+ticketTypeId);
			map.put(printNo, ticketTypeIds);
			return;
		}
		map.put(printNo, ticketTypeId);

	}
	/**
	 * 把list数据转换成jtablemodel数据
	 * @param model
	 * @param list
	 * @return
	 */
	public static <T> DefaultTableModel convertBeanToTableModel(DefaultTableModel model,List<T> list) {
		try {
			model.getDataVector().clear();
			if(list.size() == 0) return model;
			List<String> fieldsList = new ArrayList<String>();
			Class<?> cls = list.get(0).getClass();
			for (Field field : cls.getDeclaredFields()) {
				if(field.getModifiers()== 2){//如果xiao于8,非静态
					fieldsList.add(field.getName());
				}
			}
			model.setColumnIdentifiers(fieldsList.toArray());

			Vector<Object> data ;
			for (T t : list) {
				data = new Vector<Object>();
				for (String cn : fieldsList) {
					data.add(cls.getDeclaredMethod("get"+cn.substring(0, 1).toUpperCase()
							+(cn.length()>1?cn.substring(1,cn.length()):"")).invoke(t));
				}
				if(data.size()>0) model.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	/**
	 * 保存properties操作
	 */
	public static void savePropertie(Properties properties,String fileName){
		OutputStream out = null;
		try {
			File file = new File(fileName);
			if(!file.exists()){
				file.createNewFile();
			}
			out = new FileOutputStream(file);
			properties.store(out, null);
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
	}
	/**
	 * 读取properties操作
	 */
	public static Properties getProperties(String fileName){
		Properties properties = new Properties();
		InputStream in = null ;
		try {
			in = new FileInputStream(fileName);
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return properties;
	}
	/**
	 * 数组拷贝
	 * @param dest 目标数组
	 * @param src 源数组
	 * @return
	 */
	public static <T>void copy(List<? super T> dest, List<? extends T> src){
		dest.clear();
		for (T t : src) {
			dest.add(t);
		}
	}
	/**
	 * 序列化
	 * @throws IOException
	 */

	public static void serializable(Object obj, String fileName){
		OutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			os = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(os);
			oos.writeObject(obj);
			oos.flush();
			os.flush();
		} catch (Exception e) {
		}finally{
			closeAll(oos,os);
		}
	}
	/**
	 * 反序列化
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */

	public static Object deserializable(String fileName){
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = new FileInputStream(fileName);
			ois = new ObjectInputStream(is);
			return ois.readObject();
		} catch (Exception e) {
			return null;
		}finally{
			closeAll(ois,is);
		}
	}
	/**
	 * 关闭流
	 * @param objs
	 */
	public static void closeAll(Object ... objs){
		for(Object obj : objs){
			if( obj != null && obj instanceof InputStream){
				try {
					((InputStream) obj).close();
				} catch (IOException e) {
				}
				continue;
			}
			if(obj != null && obj instanceof OutputStream){
				try {
					((OutputStream) obj).close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String addDays(String date,String addDay) throws ParseException{
		Date b;
		b = SaleUtil.format3.parse(date);
		long time = b.getTime() + (Long.parseLong(addDay))*24*60*60*1000;//有效期为1天，则认为只要当天有效
		return SaleUtil.format3.format(new Date(time));
	}
	/**
	 *
	 * Describe: 放置时间
	 * @author liujianwen
	 * return:void
	 * Date:2012-7-11
	 */
	public static void putDatePrice(Map<String,String> map,String date,String meg){
		if(map.containsKey(date)){//如果包含此日期
			String mc = 	map.get(date);
			map.put(date, mc+";"+meg);
		}else{
			map.put(date, meg);
		}
	}


}
