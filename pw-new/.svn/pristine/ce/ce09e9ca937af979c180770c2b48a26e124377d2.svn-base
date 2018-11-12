package com.ectrip.base.util;

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

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * ������
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
	 * ��ResultBeanת����Map
	 * @param bean Ҫת����ResultBean
	 * @param id_columnNum ��Ϊid����
	 * @param showName_clolumnNum Ҫ��ʾ����
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
	 * ��ResultBean������ת���ɶ�ά����,����JTABLEȡֵ
	 * @param bean Ҫת����resultBean
	 * @param array ������ͷ����,�䳤����getColumnCount()һ��
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
	 * ��ResultBean��ĳ��ֵת�����ַ������飬����JComboboxȡֵ
	 * @param bean Ҫת����resultBean
	 * @param columName �ֶ���
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
	 * չʾ��������Ϣ
	 * @param talbe
	 * @param colIndex ������
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
	 * ��ResultBean ת���� model list
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
			if(fields[i].getModifiers()== 2){//���xiao��8,�Ǿ�̬
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
	 * �Ѷ��ResultBean ת���� model list
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
			if(fields[i].getModifiers()== 2){//���xiao��8,�Ǿ�̬
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
	 * ��list�D�Q��Map
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
	 * ��list�D�Q��Map
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
	 * ����date + trip id ������������ͳ��
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
	 * ��Ʊר��,���������,������Ʊ���ݽ������ //"Ʊ��#��Ʒ&��Ʒ��Ʊ��#��Ʒ&��Ʒ"
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
	 * ��list����ת����jtablemodel����
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
				if(field.getModifiers()== 2){//���xiao��8,�Ǿ�̬
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
	 * ����properties����
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
	 * ��ȡproperties����
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
	 * ���鿽��
	 * @param dest Ŀ������
	 * @param src Դ����
	 * @return
	 */
	public static <T>void copy(List<? super T> dest, List<? extends T> src){
		dest.clear();
		for (T t : src) {
			dest.add(t);
		}
	}
	/**
	 * ���л�
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
	 * �����л�
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
	 * �ر���
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
		long time = b.getTime() + (Long.parseLong(addDay))*24*60*60*1000;//��Ч��Ϊ1�죬����ΪֻҪ������Ч
		return SaleUtil.format3.format(new Date(time));
	}
	/**
	 * 
	 * Describe: ����ʱ��
	 * @author liujianwen
	 * return:void
	 * Date:2012-7-11
	 */
	public static void putDatePrice(Map<String,String> map,String date,String meg){
		if(map.containsKey(date)){//�������������
			String mc = 	map.get(date);
			map.put(date, mc+";"+meg);
		}else{
			map.put(date, meg);
		}
	}


}
