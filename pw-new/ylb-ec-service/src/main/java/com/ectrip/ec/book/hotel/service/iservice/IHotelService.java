package com.ectrip.ec.book.hotel.service.iservice;

import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.book.hotel.model.HotelDTO;
import com.ectrip.ec.book.ticket.model.SearchKey;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Hotelprovider;

public interface IHotelService {

	/**
	 * ���ݷ����������ҳ����е���
	 * Describe:
	 * @auth:huangyuqi
	 * @param pdtp
	 * @return
	 * return:List
	 * Date:2011-11-28
	 */
	public List getHotelSourceList(String pdtp);
	/**
	 * 
	 * Describe:��ѯ�������̲�Ʒ(�Ƶ�)
	 * @author:chenxinhao
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2012-11-7
	 */
	public PaginationSupport getHotelProductList(String rzti, String ldti, String lgtp, int page, int pageSize, String url);

	/**
	 * ��ѯ��ĳ�����µķ����̲�Ʒ
	 * Describe:
	 * @auth:huangyuqi
	 * @param regionId��Դ��
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-28
	 */
	public PaginationSupport getHotelProductList(Long regionId,String rzti,String ldti,String lgtp,int page,int pageSize,String url);
	/**
	 * ��ѯ�Ƶ�
	 * Describe:
	 * @auth:huangyuqi
	 * @param provider
	 * @param price
	 * @param product
	 * @param rzti��ס����
	 * @param ldti�������
	 * @param pageҳ��
	 * @param pageSizeÿҳ��ʾ��
	 * @param url��ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2011-11-30
	 */
	public PaginationSupport getHotelProductSearchList(Esbscenicareatab provider,Hotelprovider hotelpro,String rzti,String ldti,String lgtp,int page,int pageSize,String url);
	
	/**
	 * 
	 * Describe:�鿴ĳ�����̵Ĳ�Ʒ��Ϣ
	 * @auth:lijingrui
	 * @param iscenicid
	 * @param rzti
	 * @param ldti
	 * @return
	 * return:Esbscenicareatab
	 * Date:Feb 15, 2012
	 */
	public Esbscenicareatab getHotelTicketduct(Long iscenicid,String rzti,String ldti,String lgtp) throws Exception;
	
	/**
	 * 
	 * Describe:�Ƶ������еľ����Ƽ�
	 * @auth:lijingrui
	 * @param szregionalid
	 * @param lgtp
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:Mar 2, 2012
	 */
	public PaginationSupport shopAllticketcenter(Long szregionalid,String lgtp,int page,int pageSize,String url);

	/**
	 * 
	 * Describe:��ʾ���еľƵ��Ʒ��Ϣ����Ѷ����ҳʹ�ã�
	 * @auth:lijingrui
	 * @param rzti
	 * @param ldti
	 * @param lgtp
	 * @return
	 * return:List
	 * Date:Mar 12, 2012
	 */
	public List queryHotelList(String rzti,String ldti,String lgtp,Long rowid);
	
	/**
	 * ��װ������װ��שMODEL
	 * @param orid
	 * @param custom
	 * @param hotels
	 * @param lpr
	 * @return
	 */
	public OrderCombinDTO combinationOrder(String orid, Custom custom, List<HotelDTO> hotels, LprPojo lpr);

	public PaginationSupport getHotelProductList(SearchKey keys,String rzti,String ldti,String lgtp,int page, int pageSize, String url) throws Exception;

	public List searchProduct(Long iscenicid, String rzti) ;
	
	public List searchAddress(Long regionalid);
	
	public List getHotHotel(Long iregionalid,Long iscenicid,int top);
	
	public List getSpecialHotel(int top);
	
	public Edmtickettypetab getHotel(Long hotelid) throws Exception;
	/**
	 * *
	 * Describe:��ѯ�Ƽ������̣���Ѷ��ר��
	 * @see com.ectrip.book.hotel.dao.idao.IHotelDAO#queryProviders(java.lang.String, java.lang.String, int)
	 * @param keys �ؼ��֣��Զ��Ÿ���
	 * @param type ����������
	 * @param top 
	 * @return
	 * @author chenxinhao
	 * Date:2013-12-24
	 */
	public List queryProviders(String keys,String type,int top);
	
	public List getHotJinqu();
	
	public List getHotJingqu(String scenictype,int top);
	
	
	public Esbscenicareatab getHotelTicketduct(Long iscenicid, String rzti, String ldti, Long ibusinessid) throws Exception;
	public PaginationSupport getSpecialHotel(Long ibusinessid,int top,List hotelIds,int pageSize, int startInt, String url,Boolean isHqyatu);
	
	public boolean hadOrder(String usid);
}

