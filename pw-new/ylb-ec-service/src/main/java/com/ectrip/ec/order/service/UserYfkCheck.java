package com.ectrip.ec.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.ectrip.base.util.Debug;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.app.model.Vipbalance;
import com.ectrip.ec.model.balance.Useryfk;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.order.service.iservice.IOrderService;


/**
 * @author lijin 李进，用于预会款校验
 */
public class UserYfkCheck {

    /**
     * 构造函数
     * @param iService
     */
	//调用数据库
    @SuppressWarnings("unused")
    private IOrderService iService = null;

    public UserYfkCheck(IOrderService iService) {

        this.iService = iService;
    }

    /**
     * 比较用户，余额与明细
     *
     * @param usid
     * @return
     * @throws Exception
     */
    public synchronized int yfk_blance_bj(String usid) throws Exception {
        if (iService == null) {

            Debug.print("iService  ��ʼУ��֧���� Ϊ��");
            return -1;
        }

        // currentblance 当前预付款
        // -------------------------   李进加 -------------------

        //取前余额表

        Vipbalance vipbalance = (Vipbalance) iService.get(Vipbalance.class, usid);

        if (vipbalance == null) {
        	System.out.println("该用户没有预付款余额表！");
            throw new RuntimeException("该用户没有预付款余额表！");
        }

        Double bbco = vipbalance.getPoint(); //这个方法扣除了 提现
        //System.out.print("bb=="+bb);
        // 四舍五入


        //System.out.print("select new map(sum( point * yfklb)  as spoint ) from Useryfk u where  u.usid='"+usid+"'");
        List<?> listpd = iService.find("select new map(sum( point * yfklb)  as spoint ) from Useryfk u where  u.usid='" + usid + "'");
        //�ϼ���ϸ
        if (listpd == null || listpd.size() == 0) {
            // û����ϸ��¼
            System.out.println("���û�û��Ԥ������ϸ��");
            throw new Exception("���û�û��Ԥ������ϸ��");
        } else {
            //ȡ��MAP�еĺϼ�ֵ
            Map a = (Map) listpd.get(0);
            //  System.out.print("a=="+a);
            if (a == null || a.get("spoint").equals("")) {
                // û����ϸ���϶�û�ԣ�
                System.out.println("���û�����ϸԤ������������Ϊ0��");
                throw new RuntimeException("���û�����ϸԤ������������Ϊ0��");

            } else {
                Double bb = (Double) a.get("spoint");//ȡֵ
                BigDecimal bba = new BigDecimal(bb);
                //System.out.print("bb=="+bb);
                // ��������
                double af = bba.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                BigDecimal bbcob = new BigDecimal(bbco);
                double afc = bbcob.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                // �Ƚ�����ֵ
                //System.out.print("bbc=="+bbc);
                if (af != afc) {
                    System.out.println("���û���Ԥ��������쳣����,�п��ܳ���Ԥ����û�����ʵ������");
                    throw new RuntimeException("���û���Ԥ��������쳣����,�п��ܳ���Ԥ����û�����ʵ������");
                }

            }

        }
        return 1;
    }


    /**
     * Ԥ����֧����֤����鶩��״̬
     * ֧�����
     * ֧����
     *
     * @param payid
     * @param mont
     */


    public synchronized int ValideYfkPay(String orid, double mont, long currentyfkzfseq) throws RuntimeException, Exception {


        //�����Ҫ֧�����Ϊ0�������ж�
        if (Tools.float45(0.00d, mont)) {
            Debug.print("ValideYfkPay  �ۿ���Ϊ0������ҪУ��");
            return 1;
        }

        if (iService == null) {

            Debug.print("iService  ��ʼУ��֧���� Ϊ��");
            return -1;
        }

        Debug.print("ValideYfkPay ��ʼУ�鶩���� == " + orid);
        int is_return = 1;
        boolean ib_true = Debug.isDebuggingOn();
        Debug.setDebuggingOn(true);
        /*

		//����֧������
		Jszx jszx   = (Jszx) iService.get(Jszx.class,seq);
		//�ȽϽ��
		if (!Tools.float45(jszx.getAmount(), Float.valueOf(mont).doubleValue())) {

			throw new RuntimeException("֧����ˮ��ʵ�۽�һ��");
		}
		String orid = jszx.getOrid() ;
		*/
        List<?> list2 = iService.find(" From MOrder where orid ='" + orid
                + "'");
        Debug.print("UserYfkCheck.ValideYfkPay ����У�鶩��:" + orid);
        //����������Ҳ���ǳ�ֵ�����Ƿ�����

        if (list2 == null || list2.size() == 0) {
            Debug.print("UserYfkCheck.ValideYfkPay û���������");
            is_return = -1;
            throw new RuntimeException(" û���������");
        } else {
            Debug.print("UserYfkCheck.ValideYfkPay �ҵ�����:" + orid);
            // �Ƚ�֧���Լ���ֵ�Ľ��
            MOrder morder = (MOrder) list2.get(0);
            Debug.print("UserYfkCheck.ValideYfkPay ����Ƿ��Ѿ�֧��:" + orid);
            if (morder.equals("02")) {
                is_return = -1;
                throw new RuntimeException("�����Ѿ�֧����");
            }
            Debug.print("UserYfkCheck.ValideYfkPay ���Ԥ������ˮ:" + orid + " ״̬Ϊ��02");

            //	select ortp  from m_order group by ortp
            // 01  Ԥ��
            // 02  �˶�
            // 03  ����

            String ortp = morder.getOrtp();  //�����������

            int yfklb = 1;
            if (ortp.equals("02")) {
                yfklb = 1;
            } else {
                yfklb = -1;
            }

            List<?> listyfk = iService.find(" From Useryfk where usid <> '000000' and orderid ='" + orid + "' and yfklb = " + yfklb);

            if (listyfk == null || listyfk.size() == 0) {
                is_return = -1;
                throw new RuntimeException("û���ж�Ӧ��Ԥ�����¼û�У�");
            } else {
                // �ȽϽ�����֧���Ų�ѯ
                Debug.print("UserYfkCheck.ValideYfkPay ���ҿۿ��¼:" + orid);
                Useryfk yfk = (Useryfk) listyfk.get(0);
                String yfkusid = yfk.getUsid();
                Debug.print("UserYfkCheck.ValideYfkPay �����Ҫ֧���Ϳۿ���");
                if (!Tools.float45(yfk.getPoint(), mont)) {
                    is_return = -1;
                    Debug.print("UserYfkCheck.ValideYfkPay ��Ҫ֧���Ϳۿ�����");
                    throw new RuntimeException(" ��Ҫ֧���Ϳۿ�����");
                }
                Debug.print("UserYfkCheck.ValideYfkPay ֧�������ۿ���һ�£�ͨ����");
                Debug.print("UserYfkCheck.ValideYfkPay �Ƚ��û���");

                //����û�
                //��������ѣ����� 2016-01-09 �������ʼ
                if (ortp.equals("02"))  //�������Ʊ�������Ʊ������
                {

                    //������ֻ֧��һ���˶�������ȡһ���˶�������

                    List<?> listyfktpsx = iService.find(" From Useryfk where usid <> '000000' and orderid ='" + orid + "' and yfklb = -1");

                    if (listyfktpsx == null || listyfktpsx.size() == 0) {
                        Debug.print("UserYfkCheck.ValideYfkPay �˵�����" + orid + "���˵�û�������ѣ�");
                        //���������ѣ�ֱ������
                    } else {

                        Debug.print("UserYfkCheck.ValideYfkPay �˵�����" + orid + "���˵��������ѣ�");
                        Useryfk yfktpsx = (Useryfk) listyfktpsx.get(0);
                        Debug.print("UserYfkCheck.ValideYfkPay ��������ѿۿ���");

                        //�ۿ����������ѱȽ�

                        if (!Tools.float45(yfktpsx.getPoint(), morder.getTpsx())) {
                            is_return = -1;
                            Debug.print("UserYfkCheck.ValideYfkPay Ԥ����ۿ��" + yfktpsx.getPoint());
                            Debug.print("UserYfkCheck.ValideYfkPay �����������ѿۿ��" + morder.getTpsx());
                            Debug.print("UserYfkCheck.ValideYfkPay �����ѿۿ���Ϳۿ�����");
                            throw new RuntimeException(" �����ѿۿ���Ϳۿ�����");
                        }

                        Debug.print("UserYfkCheck.ValideYfkPay �����ѿۿ�����ۿ���һ�£�ͨ����");
                    }

                    listyfktpsx = null;
                }

                // ��������ѣ����� 2016-01-09 �������

                Debug.print("UserYfkCheck.ValideYfkPay  yfkusid            =" + yfkusid);
                Debug.print("UserYfkCheck.ValideYfkPay  morder.getZfusid() =" + morder.getZfusid());

                if (!yfk.getUsid().equals(morder.getZfusid())) {
                    is_return = -1;

                    Debug.print("UserYfkCheck.ValideYfkPay  yfkusid            =" + yfkusid);
                    Debug.print("UserYfkCheck.ValideYfkPay  morder.getZfusid() =" + morder.getZfusid());
                    Debug.print("UserYfkCheck.ValideYfkPay  ֧����ˮ��������Ԥ��������֧���û���Ų�һ��");
                    throw new RuntimeException(" ֧����ˮ��������Ԥ��������֧���û���Ų�һ��");

                }
                Debug.print("UserYfkCheck.ValideYfkPay �Ƚ��û�ͨ��");
                //�����
                Debug.print("UserYfkCheck.ValideYfkPay �ȽϽ�");
                if (!Tools.float45(yfk.getPoint(), morder.getZfmont())) {
                    Debug.print("UserYfkCheck.ValideYfkPay  ʵ�۽���붩��֧����");
                    throw new RuntimeException("ʵ�۽���붩��֧����");
                }
                Debug.print("UserYfkCheck.ValideYfkPay �ȽϽ��ͨ��");

                //�����
                Debug.print("UserYfkCheck.ValideYfkPay �Ƚ���ˮ��֧��������Ԥ������ˮ���Ƿ�һ�£�");
				   
				 
				   
				 /*  if (!(yfk.getSeq()+"").equals(morder.getPayorid())) {
					    Debug.print("UserYfkCheck.ValideYfkPay  ʵ�۽���붩��֧����");
					    Debug.print("UserYfkCheck.ValideYfkPay �򸶿���ˮ��"+yfk.getSeq());
						Debug.print("UserYfkCheck.ValideYfkPay jszx����֧����ˮ��"+morder.getPayorid());
						throw new RuntimeException("�Ƚ���ˮ��֧��������Ԥ������ˮ�Ų�һ�£�");
					}
				   Debug.print("UserYfkCheck.ValideYfkPay �Ƚ�֧��������Ԥ������ˮ��ͨ��");
				 */

            }
            listyfk = null; //�ͷű�����
        }


        is_return = 1;
        // throw new RuntimeException(" У�鲻�ϸ��������");
        Debug.print("ValideYfkPay У����ɣ� " + orid + "ͨ��������");
        Debug.setDebuggingOn(ib_true);
        return is_return;
    }
}
