package com.ectrip.base.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/*
 * @author 罗鑫
 * Date:2014-01-10
 * @deprecated 该类为同步下载数据时， 转换	读取的中心服务器数据类型	成	本地数据表中数据类型	使用(通用)，不可随意改动
 * 1.以下为两中转换方法：a.将map集合转换成Bean集合；b.将map转换成Bean。请根据需求调用相应的方法。
 * 2.需注意：在"进行值封装"时，需要进行数据类型转换，该步骤不够完善，使用时请根据需要添加所需转换的数据类型，仿照已有的数据类型装换方法添加即可
 * 	2014-01-10:添加了	Object类型转Long类型；Object类型转Double类型；Object类型转String类型(此类型可不转换)
 *	2014-01-13:添加了	Object类型转Integer类型
 */
public class BeanUtil {
	
	private static Logger log = Logger.getLogger(BeanUtil.class); // 日志
	/**
	 * 转换时对map中的key里的字符串会做忽略处理的正则串
	 */
	private static final String OMIT_REG = "_";

	/**
	 * 将map集合转换成Bean集合，Bean的属性名与map的key值对应时不区分大小写，并对map中key做忽略OMIT_REG正则处理
	 * 
	 * @param <E>
	 * @param cla
	 * @param mapList
	 * @return
	 */
	public static <E> List<E> toBeanList(Class<E> cla,
			List<Map<String, Object>> mapList) {

		List<E> list = new ArrayList<E>(mapList.size());

		for (Map<String, Object> map : mapList) {
			E obj = toBean(cla, map);
			list.add(obj);
		}

		return list;
	}

	/**
	 * 将map转换成Bean，Bean的属性名与map的key值对应时不区分大小写，并对map中key做忽略OMIT_REG正则处理
	 * 
	 * @param <E>
	 * @param cla
	 * @param map
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public static <E> E toBean(Class<E> cla, Map<String, Object> map) {

		// 创建对象
		E obj = null;
		try {
			obj = cla.newInstance();
			if (obj == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			log.error("类型实例化对象失败,类型:" + cla);
			return null;
		}

		// 处理map的key
		Map<String, Object> newmap = new HashMap<String, Object>();
		for (Map.Entry<String, Object> en : map.entrySet()) {
			newmap.put(
					"set"
							+ en.getKey().trim().replaceAll(OMIT_REG, "")
									.toLowerCase(), en.getValue());
		}

		// 进行值装入
		Method[] ms = cla.getMethods();
		int count=1;//需注入值的个数
		for (Method method : ms) {
			String mname = method.getName().toLowerCase();
			if (mname.startsWith("set")) {
				Class[] clas = method.getParameterTypes();
				Object v = newmap.get(mname);
				if (v != null && !v.toString().equals("null") && clas.length == 1) {
					System.out.println("第"+count+"个需注入的值");
					System.out.println("需装入的值为---"+v);
					System.out.println("需装入值的类型为---"+clas[0]);
					System.out.println("需装入值的数据表为---"+obj);
					try {
						if(clas[0].toString().equals("class java.lang.Long")){
							System.out.println(">>>装入Long类型数据<<<");
							Long l = Long.valueOf(v.toString());
							System.out.println("属性值装入成功，装入方法："+ cla +"."+method.getName());
							method.invoke(obj, l);
						}else if(clas[0].toString().equals("class java.lang.Double")){
							System.out.println(">>>装入Double类型数据<<<");
							Double d = Double.valueOf(v.toString());
							System.out.println("属性值装入成功，装入方法："+ cla +"."+method.getName());
							method.invoke(obj, d);
						}else if(clas[0].toString().equals("class java.lang.String")){
							System.out.println(">>>装入String类型数据<<<");
							String s = String.valueOf(v);
							System.out.println("属性值装入成功，装入方法："+ cla +"."+method.getName());
							method.invoke(obj, s);
						}else if(clas[0].toString().equals("class java.lang.Integer")){
							System.out.println(">>>封装Integer类型数据<<<"+v);
							Integer i = Integer.valueOf(v.toString());							
							System.out.println("属性值装入成功，装入方法："+ cla +"."+method.getName());
							method.invoke(obj, i);
						}else if(clas[0].toString().equals("class java.util.Date")){
							System.out.println(">>>封装Date类型数据<<<");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
							String s = v.toString().replace('-', '/');
							Date dt = new Date();
							try {
								dt = sdf.parse(s);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("属性值装入成功，装入方法："+ cla +"."+method.getName());
							method.invoke(obj, dt);
						}
					}catch(Exception e){
						log.error("属性值装入失败,装入方法：" + cla + "."
								+ method.getName() + ".可装入类型" + clas[0]
								+ ";欲装入值的类型:" + v.getClass());
					}
				}else{
					System.out.println("空值，不转换");
				}
				count++;
			}
		}
		return obj;
	}

}
