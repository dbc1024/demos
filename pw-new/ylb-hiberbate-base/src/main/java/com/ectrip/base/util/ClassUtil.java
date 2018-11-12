package com.ectrip.base.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.ectrip.job.ijob.RunEngine;
/**
 * 根据类加载同包下面的类
 * @author lijin
 *
 */
public class ClassUtil {
	public static void main(String[] args) {
		try {
			System.out.println("接口实现类：");

			for (Class<?> c : getAllAssignedClass(RunEngine.class)) {

				Object o = ClassLoader.getSystemClassLoader().loadClass(c.getName()).newInstance();
				RunEngine re = (RunEngine) o;
				re.jobrun();
				System.out.println(c);

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 报表引擎入口程序　
	 */
	public static int runMain() {
		int rc_id = -1;
		try {
			System.out.println("开始运行");
			int js = 0;
			for (Class<?> c : getAllAssignedClass(RunEngine.class)) {
				js++;

				Object o = c.newInstance();
				if (o != null) {
					System.out.println("查到引擎：" + c.getName());
					RunEngine re = (RunEngine) o;
					re.jobrun();
				}
				System.out.println(c);
				rc_id++;
			}
			if (js == 0) {
				System.out.println("引擎　接口放在同一个包中。");
			}
			return rc_id;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 获取同一路径下所有子类或接口实现类
	 * 
	 * @param intf
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> getAllAssignedClass(Class<?> cls) throws IOException, ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c : getClasses(cls)) {
			if (cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}
		return classes;
	}

	/**
	 * 取得当前类路径下的所有类
	 * 
	 * @param cls
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> getClasses(Class<?> cls) throws IOException, ClassNotFoundException {
		String pk = cls.getPackage().getName();
		String path = pk.replace('.', '/');
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL url = classloader.getResource(path);
		return getClasses(new File(url.getFile()), pk);
	}

	/**
	 * 迭代查找类
	 * 
	 * @param dir
	 * @param pk
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> getClasses(File dir, String pk) throws ClassNotFoundException {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!dir.exists()) {
			return classes;
		}
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				classes.addAll(getClasses(f, pk + "." + f.getName()));
			}
			String name = f.getName();
			if (name.endsWith(".class")) {
				classes.add(Class.forName(pk + "." + name.substring(0, name.length() - 6)));
			}
		}
		return classes;
	}

}
