package com.ectrip.ec.book.rentrl.news.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.rentrl.Carlinedetailtab;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;

public interface ICarlineDao extends IGenericDao{
	/**
	 * ͨ���⳵·�߲�ѯ���еĳ���
	 * findByLine(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-17 ����11:16:58
	 * @since  1.0.0
	 */
	public List findByLine(String pmcd);
	
	/**
	 * �õ����е��⳵·��
	 * findAllLine(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-17 ����02:46:16
	 * @since  1.0.0
	 */
	public List findAllLine();
	
	/**
	 * �����õ��⳵·��
	 * searchLine(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-20 ����10:56:02
	 * @since  1.0.0
	 */
	public List searchLine(String bycategorytype);
	
	/**
	 * ��ȡ��·�����еĳ���
	 * searchLineByType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param bycategorytype
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-23 ����09:14:41
	 * @since  1.0.0
	 */
	public List searchLineByType(String bycategorytype);
	
	/**
	 * �������ҳ���ͼƬ�л�
	 * findAllImg(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-23 ����10:01:48
	 * @since  1.0.0
	 */
	public List findAllImg(Long iscenicid,String itickettypeid);
	
	/**
	 * ѡ��ͬ�ĳ��ͼ��ز�ͬ����Ϣ
	 * changeCarType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-23 ����11:26:41
	 * @since  1.0.0
	 */
	public List changeCarType(Long iscenicid,String bycategorytype,String enddate,String stratdate);
	public List changeCarType(Long iscenicid,String bycategorytype,String enddate,String stratdate,Long ibusinessid );
	
	/**
	 * ��������
	 * carTypeDetail(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-23 ����02:38:39
	 * @since  1.0.0
	 */
	public List carTypeDetail(Long iscenicid,Long itickettypeid);
	
	/**
	 * ��ѯ·�������г��͵�ͼƬ����۸�
	 * findCarType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param bycategorytype
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-24 ����11:57:21
	 * @since  1.0.0
	 */
	public List findCarType(String bycategorytype);
	
	/**
	 * ��ѯ������(������)
	 * findCarType(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-24 ����04:49:26
	 * @since  1.0.0
	 */
	public List findCarType(Long iscenicid);
	
	/**
	 * ��ѯ����ͼƬ
	 * findCarTypeImg(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-24 ����04:58:40
	 * @since  1.0.0
	 */
	public List findCarTypeImg(Long iscenicid);
	
	/**
	 * ��ѯ���·��
	 * findLineRelated(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-24 ����05:22:16
	 * @since  1.0.0
	 */
	public List findLineRelated(Long iscenicid);
	
	/**
	 * ����Ԥ��
	 * reserveCar(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @param itickettypeid
	 * @return 
	 * List
	 * @exception 
	 * @date:2013-12-25 ����03:10:41
	 * @since  1.0.0
	 */
	public List reserveCar(Long iscenicid,String bycategorytype,String date);
	/**
	 * ��鵱���Ƿ��м۸�
	 * checkPrice(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @param bycategorytype
	 * @param date
	 * @return 
	 * Double
	 * @exception 
	 * @date:2013-12-31 ����02:19:50
	 * @since  1.0.0
	 */
	public List checkPrice(Long iscenicid,String bycategorytype,String date);
	/**
	 * ��ȡ�۸�
	 * getTicketPrice(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param itickettypeid
	 * @param tourDate
	 * @param icrowdkind
	 * @param ibusinessid
	 * @param groupno
	 * @return 
	 * Edmcrowdkindpricetab
	 * @exception 
	 * @date:2014-1-3 ����03:05:26
	 * @since  1.0.0
	 */
	public Edmcrowdkindpricetab getCartypePrice(String itickettypeid,
			String tourDate, String icrowdkind, String ibusinessid,
			String groupno);
	
	/**
	 * ��ȡ����
	 * getSafeList(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-1-4 ����10:33:59
	 * @since  1.0.0
	 */
	public List getSafeList(String date);
	
	/**
	 * �����̡�·������ȡ��Ʒ
	 * findCarList(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param iscenicid
	 * @param bycategorytype
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-1-16 ����09:44:57
	 * @since  1.0.0
	 */
	public List findCarList(Long iscenicid,String bycategorytype);
	
	public Carlinedetailtab findCarlinedetailtab(String pmcd);
	
	/**
	 * ·������ѯ������
	 * findCarListByDate(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param bycategorytype
	 * @param date
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-1-22 ����04:58:16
	 * @since  1.0.0
	 */
	public List findCarListByDate(String bycategorytype,String date);
	
	
	public List findLineRelatedbyibusinessid(Long iscenicid,Long ibusinessid);
}
