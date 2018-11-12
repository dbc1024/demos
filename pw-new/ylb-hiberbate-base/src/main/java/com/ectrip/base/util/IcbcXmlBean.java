package com.ectrip.base.util;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
*
* <p>Title: 这个类重要处理XML文件档中的数据增加,修改删除
* <ProvderEctrip>
* <!--服务商信息-->
* <Provder name="服务商1" code="0001">  code 值必写,并且唯一,本程序不检查,如果重复在修改
* 删除,将会以先后顺序为读写顺序
*   <article level="Intermediate" date="December-2001">
*     <title>Java configuration with XML Schema</title>
*     <author>
*       <lastname>Vitaletti</lastname>
*     </author>
*   </article>
* </Provder>
* </p>
* 本程序 将会以 <yupassword>我是李进</yupassword> 这种形式保存数据.
* 而不是以 <article level="Intermediate" date="December-2001"> 这种方式保存数据,请小心.
* 一切有本程序操作就没有问题.
* 属性名称可以自己定义
*
* <p>Description: 张网电子商务</p>
*
* <p>Copyright: Copyright (c) 2006</p>
*
* <p>Company: 鼎游</p>
*
* @author 李进
* @version 1.0
*/
public class IcbcXmlBean {

	/**
	 *
	 * <p>Title: 这个类重要处理XML文件档中的数据增加,修改删除
	 * <ProvderEctrip>
	 * <!--服务商信息-->
	 * <Provder name="服务商1" code="0001">  code 值必写,并且唯一,本程序不检查,如果重复在修改
	 * 删除,将会以先后顺序为读写顺序
	 *   <article level="Intermediate" date="December-2001">
	 *     <title>Java configuration with XML Schema</title>
	 *     <author>
	 *       <lastname>Vitaletti</lastname>
	 *     </author>
	 *   </article>
	 * </Provder>
	 * </p>
	 * 本程序 将会以 <yupassword>我是李进</yupassword> 这种形式保存数据.
	 * 而不是以 <article level="Intermediate" date="December-2001"> 这种方式保存数据,请小心.
	 * 一切有本程序操作就没有问题.
	 * 属性名称可以自己定义
	 *
	 * <p>Description: 张网电子商务</p>
	 *
	 * <p>Copyright: Copyright (c) 2006</p>
	 *
	 * <p>Company: 鼎游</p>
	 *
	 * @author 李进
	 * @version 1.0
	 */

	private static Document document = null; //整个XML文件档句柄
	private String xmlFileName; //XML文件名称

	/**
	 * 初始文件名
	 * @param filename String
	 */
	public IcbcXmlBean(String filename) {
		setXmlFileName(filename);
		try {
			loadfile(filename);
		} catch (Exception e) {
			System.out.print("文件不存在,或者是格式有问题" + e.toString());
		}

	}

	public static void main(String[] args) {
		String str = "<?xml  version=\"1.0\" encoding=\"GBK\" standalone=\"no\" ?><B2CRes><interfaceName>ICBC_PERBANK_B2C</interfaceName><interfaceVersion>1.0.0.3</interfaceVersion><orderInfo><orderDate>20070725105014</orderDate><orderid>20070725105014-2134062548</orderid><amount>20</amount><curType>001</curType><merID>0200EC20000875</merID><merAcct>0200020409015029130</merAcct></orderInfo><custom><verifyJoinFlag>0</verifyJoinFlag><JoinFlag></JoinFlag><UserNum></UserNum></custom><bank><TranSerialNo></TranSerialNo><notifyDate>20070725110400</notifyDate><tranStat>1</tranStat><comment>交易成功！</comment></bank></B2CRes>";
		IcbcXmlBean xmlbean = new IcbcXmlBean(str);
		try {						
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/interfaceName"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/interfaceVersion"));
			
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/orderInfo/orderDate"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/orderInfo/orderid"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/orderInfo/amount"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/orderInfo/curType"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/orderInfo/merID"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/orderInfo/merAcct"));
			
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/custom/verifyJoinFlag"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/custom/JoinFlag"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/custom/UserNum"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/bank/TranSerialNo"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/bank/notifyDate"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/bank/tranStat"));
			System.out.println(xmlbean.readXmlAttribValue("/B2CRes/bank/comment"));
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public void loadfile(String filename) throws Exception {

		try {

			SAXReader saxReader = new SAXReader();
			StringReader sr = new StringReader(filename);

			document = saxReader.read(sr);
		} catch (Exception ex) {

			ex.printStackTrace();
			throw ex;

		}
	}

	public String readXmlAttribValue(String nodename) {
		List list = document.selectNodes(nodename);

		Iterator it = list.iterator();

		String ls_values = "";

		while (it.hasNext()) {
			Element ftpElement = (Element) it.next();
			ls_values = ftpElement.getTextTrim() ;
		}

		return ls_values;
	}

	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}

	public String getXmlFileName() {
		return xmlFileName;
	}

}
