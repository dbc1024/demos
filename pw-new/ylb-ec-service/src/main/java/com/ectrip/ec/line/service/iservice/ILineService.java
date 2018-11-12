package com.ectrip.ec.line.service.iservice;

import java.util.List;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.line.LineModel;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;

public interface ILineService extends IGenericService{
	public List findByLineType(String type, boolean isImage,String condition);
	/**
	 * ����
	 * searchLine(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param searchKey �ؼ���
	 * @param searchString  ��������
	 * @param itickettypeid  id
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
	public OrderCombinDTO getOrderCombinDTO(String orid,Custom custom,LineModel lineModel, LprPojo lpr);
	
	/**
	 * ֧���ɹ��޸Ŀ��
	 * updateLineNumbByPay(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @param orid
	 * @return 
	 * boolean
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
	
	/**
	 * ����JSON�Ƽ���·
	 * getJsonTJLine(������һ�仰�����������������)
	 * (����������������������� �C ��ѡ)
	 * @auth hejiahua
	 * @return 
	 * List
	 * @exception 
	 * @date:2014-3-7 ����03:46:11
	 * @since  1.0.0
	 */
	public List getJsonTJLine(String top,String keyword);
}
