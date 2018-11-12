package com.ectrip.sys.backuplogin.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.service.GenericService;
import com.ectrip.sys.backuplogin.dao.IOperationDao;
import com.ectrip.sys.backuplogin.dao.impl.OperationDao;
import com.ectrip.sys.backuplogin.service.IOperationService;
import com.ectrip.sys.model.backupadmin.Operation;



/**
 * 读取后台菜单的服务 李进于，2013-01-18 修改，增加了对菜单的控制。
 * 李进于 2013-4-19 日对读出的职责编号加入了MD5校验，防止用户修改OPID
 * 
 *
 * @author LiJin
 * 
 */
@Service
public class OperationService extends GenericService implements IOperationService {
	/**
	 * 读取用户对应的所有菜单
	 */
	// 数据操作DAO
	@Autowired
	private IOperationDao operationDao;

	public List getAllEmployeeOperation(String empid) throws Exception {
		// TODO Auto-generated method stub
		return operationDao.getAllEmployeeOperation(empid);
	}

	/**
	 * 读取所有菜单
	 */
	public List getAllOperation() throws Exception {
		// TODO Auto-generated method stub
		return operationDao.getAllOperation();
	}

	/**
	 * 真正读取数据的方法 ，在这个方法中调用DOAod进行读取。
	 * 
	 * @param srcpath
	 *            是上级路径
	 * @param empid
	 *            是员工编号
	 * @param popid
	 *            是上级职责编号
	 */
	public Document createXMLByBackColumn(String srcpath, String empid, BigDecimal popid) {
		// TODO Auto-generated method stub
		// String Rootnode = "Roottree";
		
		//System.out.println("OperationService int licstr ");
		
		Element root, chilnode;
		root = new Element("Roottree");

		List list = operationDao.findbyempid(empid, popid);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {

				Operation cp = (Operation) list.get(i);
				chilnode = new Element("tree");

				chilnode.setAttribute("t", cp.getId().getOpname());
				//李进修改，2013-3-19 日修改
				String actionname = this.getActionName(cp.getId().getOpurl());
				com.ectrip.base.util.EctripMd5 md5 = new com.ectrip.base.util.EctripMd5(cp.getId().getOpid().toString()+actionname);
				md5.calc();
				
				String ls_md5  = md5.toString();
			   //将 opid=11 改为 opid=11&md5str=xxxxxxxxx
			     
				chilnode.setAttribute("a", cp.getId().getOpurl()
						+ ((cp.getId().getOpurl().indexOf("?") > 0) ? "&opid=" + cp.getId().getOpid()+"&md5str="+ls_md5 : "?opid="
								+ cp.getId().getOpid()+"&md5str="+ls_md5));
				//修改结束
				chilnode.setAttribute("id", String.valueOf(cp.getId().getOpid()));
				chilnode.setAttribute("pid", String.valueOf(cp.getId().getPopid()));
				String ischile = "0";
				List sonlist = operationDao.findbyempid(empid, popid);
				if (operationDao.findbyempid(empid, cp.getId().getOpid()) != null
						&& operationDao.findbyempid(empid, cp.getId().getOpid()).size() > 0) {
					ischile = "1";
				}
				chilnode.setAttribute("s", srcpath + ".action?operation.id.popid=" + cp.getId().getOpid());
				chilnode.setAttribute("c", ischile);
				//何安洲新增，判断，只有一级菜单才返回图片
				/*System.out.println("何安洲测试菜单图片显示："+" "+String.valueOf(cp.getId().getOpjb()));*/
				if(String.valueOf(cp.getId().getOpjb()).equals("1")){
					chilnode.setAttribute("urlf",String.valueOf(cp.getId().getPicurlf()));
					chilnode.setAttribute("urls",String.valueOf(cp.getId().getPicurls()));
				}
				root.addContent(chilnode);
			}

		}
		Document doc = new Document(root);
		return doc;

	}

	/**
	 * 真正读取数据的方法 ，在这个方法中调用DOAod进行读取。
	 * 
	 * @param srcpath
	 *            是上级路径
	 * @param empid
	 *            是员工编号
	 * @param popid
	 *            是上级职责编号
	 */
	public Document createXMLByBackColumnLic(String srcpath, String empid, BigDecimal popid, String licstr) {
		// TODO Auto-generated method stub
		// String Rootnode = "Roottree";
		//System.out.println("OperationService int licstr " +licstr );
		Element root, chilnode;
		root = new Element("Roottree");
		
	
		List list = operationDao.findbyempid(empid, popid, licstr);

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {

				Operation cp = (Operation) list.get(i);
				//
				// 如果LIC中没有定义，将不显示，有定义才显示。
				//if (isExitsEmputy(adminlogin.emptuy, cp.getId().getOpid())) {

					//
					chilnode = new Element("tree");

					chilnode.setAttribute("t", cp.getId().getOpname());
					//李进修改，2013-3-19 日修改
					String actionname = getActionName(cp.getId().getOpurl());
					
					//System.out.println("actionname srrvicr="+actionname);
					com.ectrip.base.util.EctripMd5 md5 = new com.ectrip.base.util.EctripMd5(cp.getId().getOpid().toString()+actionname);
					md5.calc();
					
					String ls_md5  = md5.toString();
					//将 opid=11 改为 opid=11&md5str=xxxxxxxxx
					chilnode.setAttribute("a", cp.getId().getOpurl()
							+ ((cp.getId().getOpurl().indexOf("?") > 0) ? "&opid=" + cp.getId().getOpid()+"&md5str="+ls_md5 : "?opid="
									+ cp.getId().getOpid()+"&md5str="+ls_md5));
					/*
					chilnode.setAttribute("a", cp.getId().getOpurl()
							+ ((cp.getId().getOpurl().indexOf("?") > 0) ? "&opid=" + cp.getId().getOpid() : "?opid="
									+ cp.getId().getOpid()));
									*/
					chilnode.setAttribute("id", String.valueOf(cp.getId().getOpid()));
					chilnode.setAttribute("pid", String.valueOf(cp.getId().getPopid()));
					String ischile = "0";
					//李进于 2013-01-18 修改
					// System.out.println("OperationService " );
					List sonlist = operationDao.findbyempid(empid, popid , licstr);
					if (operationDao.findbyempid(empid, cp.getId().getOpid()) != null
							&& operationDao.findbyempid(empid, cp.getId().getOpid()).size() > 0) {
						ischile = "1";
					}
					chilnode.setAttribute("s", srcpath + ".action?operation.id.popid=" + cp.getId().getOpid());
					chilnode.setAttribute("c", ischile);
					//何安洲新增，判断，只有一级菜单才返回图片
					if(String.valueOf(cp.getId().getOpjb()).equals("1")){
						chilnode.setAttribute("urlf",String.valueOf(cp.getId().getPicurlf()));
						chilnode.setAttribute("urls",String.valueOf(cp.getId().getPicurls()));
					}
					
					root.addContent(chilnode);
				}
			//}

		}
		Document doc = new Document(root);
		return doc;

	}

	public List findbyempid(String empid, BigDecimal popid) {
		// TODO Auto-generated method stub
		return operationDao.findbyempid(empid, popid);
	}

	/**
	 * 根据职责，进行比较，对数组进比较
	 * 
	 * @param emputy
	 * @param opid
	 * @return
	 */
	boolean isExitsEmputy(String emputy, BigDecimal opid) {
		// 直正的比较程序，
		String[] empu = emputy.split(",");
		// 首先对数据进行排序
		if (opid.intValue() == 0) {
			return true;
		}
		for (int i = 0; i < empu.length; i++) {
			System.out.println("职责：" + empu[i] + "     ===opid " + opid.intValue());
			if (Integer.valueOf(empu[i]).intValue() == opid.intValue()) {
				System.out.println("职责：true" + empu[i] + "     ===opid " + opid.intValue());
				return true;
			}

		}
		// System.out.println("职责：true"+ empu[i]+ "     ===opid "+ opid.intValue() );
		return false;

	}

	/**
	 * 二分查找法进行查找。
	 * 
	 * @param arr
	 * @param num
	 * @return
	 */
	public int splitBy2(String arr[], int num) {
		int search = num;// 记录要查找的元素
		int lower = 0; // 记录第一个元素
		int temp = arr.length - 1;
		int index = -1;
		while (lower <= temp) {
			index = (lower + temp) / 2;// 记录中间元素，用两边之和除2.
			int currentValue = Integer.valueOf(arr[index]);
			if (currentValue == search) {// 如果得到的数与要查找的数相等则break退出;
				break;
			} else if (currentValue < search) {// 如果得到的数要小于查找的数、就用下标加1;否则减一
				lower = index + 1;
			} else {
				temp = index - 1;
			}
		}
		if (lower <= temp) {
			return 1;
		} else {
			return -1;
		}

	}

	/**
	 * 最新的读取LIC 字符串
	 */
	public List findbyempid(String empid, BigDecimal popid, String licid) {
		// TODO Auto-generated method stub
		return operationDao.findbyempid(empid, popid, licid);
	}
	/**
	 * 根据URL找到直正的ACTION 名称
	 * @param url
	 * @return
	 */
	public static String getActionName(String url) {
		//System.out.println("url = " + url);
		return url ;
	/*	String a = url;

		int ab = a.indexOf("action");

	//	System.out.println("url = " + url);
		
		System.out.println("ab = " + ab);
		if (ab == 0) {
			return "";
		}
		if ( ab < 0)
		{
			//如果为根菜单
			 return "rootmenu";
		}
		int abc = a.substring(0, ab).lastIndexOf("/");
		if (abc == 0) {
			return "";

		}
		if (ab < abc) {
			return "";
		}
		//System.out.println("abc = " + abc);
		String s = a.substring(abc + 1, ab - 1);
		//System.out.println("s = " + s);
		return s;*/

	}


}
