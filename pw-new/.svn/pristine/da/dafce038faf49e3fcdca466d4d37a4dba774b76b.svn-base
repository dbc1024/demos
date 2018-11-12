package com.ectrip.ec.line.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;

public interface ILineDao extends IGenericDao {
	/**
	 * �����ѯ��·
	 * findByLineType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param type
	 *        ���
	 * @param isImage
	 * 		   �Ƿ���ͼƬ
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-2-14 ����10:00:48
	 * @since  1.0.0
	 */
	public List findByLineType(String type,boolean isImage,String condition);
	
	/**
	 * ����
	 * searchLine(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param searchKey �ؼ���
	 * @param searchString  ��������
	 * @param title  ����
	 * @return List ���
	 * @exception 
	 * @date:2014-2-17 ����10:22:02
	 * @since  1.0.0
	 */
	public  PaginationSupport  searchLine(String searchKey, String searchString,
			 String title,int pageSize,String startIndex,String url);
	
	/**
	 * ��ѯͼƬ
	 * findImg(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-2-17 ����10:58:52
	 * @since  1.0.0
	 */
	public List findImg(Long itickettypeid);
	
	/**
	 * id��ѯ
	 * findByItickettypeid(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-2-17 ����03:11:41
	 * @since  1.0.0
	 */
	public List findByItickettypeid(Long itickettypeid);
	
	/**
	 * �Ƽ���·
	 * tJLine(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-2-18 ����10:25:23
	 * @since  1.0.0
	 */
	public List tJLine();
	
	/**
	 * ��ѯѡ�����ڵļ۸�
	 * findPriceByDate(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid
	 * @param date
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-2-19 ����10:45:33
	 * @since  1.0.0
	 */
	public List findPriceByDate(Long itickettypeid,String date);
	
	/**
	 * ����޸�
	 * updateLineNumb(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param youngcount
	 * @param childcount
	 * @param itickettypeid
	 * @param date
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2014-2-28 ����10:53:03
	 * @since  1.0.0
	 */
	public void updateLineNumb(int num,Long icrowdkindpriceid,String orid);
	/**
	 * ֧���ɹ��޸Ŀ��
	 * updateLineNumbByPay(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param orid
	 * @return 
	 * int
	 * @exception 
	 * @date:2014-2-28 ����02:53:04
	 * @since  1.0.0
	 */
	public boolean updateLineNumbByPay(String orid);
	/**
	 * ֧��֮ǰ�����
	 * checkLineNumbByPay(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param orid
	 * @return 
	 * boolean
	 * @exception 
	 * @date:2014-2-28 ����03:25:49
	 * @since  1.0.0
	 */
	public boolean checkLineNumbByPay(String orid);
	
	public List getJsonTJLine(String top,String keyword);
}
