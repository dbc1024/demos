package com.ectrip.ec.order.dao.idao;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ticket.model.venuemarketing.Productcontrol;

public interface IPayOrderDAO extends IGenericDao{

    /**
     *
     * Describe:���ݶ�����Ż�ȡ��Ʒ��ϸ(������Ʊ�����Ʊ�ټ��ϻ���Ʊ,�ϲ�����ͬ��Ʊ,��ͬ���˴Σ�ͬ��Ʒ��ͬ�ļ۸�)
     * @auth:yangguang
     * @param orid
     * @return
     * return:List<TZorderlist>
     * Date:2011-11-16
     */
    public List getTZorderlistByOrid(String orid);

    /**
     *
     * Describe:���������� ���й��˴μ���
     * @auth:yangguang
     * @param orid
     * @param zorderlist
     * return:void
     * Date:2011-11-18
     */
    public void updateStock(String orid,TZorderlist zorderlist);

    /**
     *
     * Describe:��ȡ���� ��Ʒ�����б� ���ϲ�ͬ��Ʒ��ͬ�۸�  ��ƱֻҪ��ͬһ���������ӽ��кϲ� ���˴�֮���ʱ������  ֻҪ��ͬһ���Ҳ�ϲ�
     * 			ֻҪ��ͬһ��ͬ��Ʒ��ȫ���ϲ� �������
     * @auth:yangguang
     * @param orid
     * @return
     * return:List
     * Date:2011-11-18
     */
    public List getTZorderlist(String orid);


    /**
     *
     * Describe:�����տ���
     * @auth:yangguang
     * @param orid
     * @param date
     * @param itickettypeid
     * @param iscenicid
     * @param numb
     * @return
     * return:boolean
     * Date:2011-11-18
     */
    public void updateDayControl(String orid,String date,String itickettypeid,String iscenicid,int numb);
    /**
     *
     * Describe:����Ʊ�д��ж����ѡ��������(һ����˵����������)����Ʊ,
     * @auth:yangguang
     * @param orid
     * @param itickettypeid
     * @return
     * return:List
     * Date:2011-11-18
     */
    public List getNestTicketDate(String orid,String itickettypeid);


    /**
     *
     * Describe:��ȡ����״̬
     * @auth:yangguang
     * @param orid
     * @param mont
     * @param bank
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * return:int -1:����������  -2:������֧��,�����ٽ���֧�� -3:���������֧������
     * Date:2011-11-18
     */
    public int getPayStatus(String orid,String mont, String bank) throws IllegalAccessException, InvocationTargetException;


    /**
     *
     * Describe:��֤��Ʒ�տ���
     * @auth:yangguang
     * @param orid
     * @param orderlist
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * return:boolean
     * Date:2011-11-18
     */
    public boolean validateDayControl(String orid, TOrderlist orderlist)throws IllegalAccessException, InvocationTargetException;

    /**
     *
     * Describe:��֤��Ʒ�˴ο���
     * @auth:yangguang
     * @param zorderlist
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * return:boolean
     * Date:2011-11-18
     */
    public boolean validateTripControl(TZorderlist zorderlist) throws IllegalAccessException,InvocationTargetException;


    /**
     *
     * Describe:��ȡ��Ʒ�˴ο���
     * @auth:yangguang
     * @param itickettypeid
     * @param tourdate
     * @param ivenueid
     * @param ivenueareaid
     * @param tripid
     * @return
     * return:Productcontrol
     * Date:2011-11-18
     */
    public Productcontrol getProductcontrol(String itickettypeid,String tourdate,String ivenueid,String ivenueareaid,String tripid) throws IllegalAccessException, InvocationTargetException;


    /**
     *
     * Describe:��ȡ��Ʒ�տ���
     * @auth:yangguang
     * @param itickettypeid
     * @param tourdate
     * @return
     * return:Productcontrol
     * Date:2011-11-18
     */
    public Productcontrol getProductcontrol(String itickettypeid,String tourdate) throws IllegalAccessException, InvocationTargetException;

    public List getTorderByOrid(String orid);

    public TZorderlist getTZorderlistbyT(TOrderlistId id);
    public List getTorder(String orid);
    public List getYorder(String orid);
    public List getTorderlistByTorder(TOrder torder);
    public List getYorderlistByTorder(TOrder torder);
    public List getOrderFilmSeat(String orid);
}

