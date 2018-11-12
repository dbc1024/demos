package com.ectrip.ec.order.common.business.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ec.model.order.TOrderId;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.TOrderlistId;
import com.ectrip.ec.model.order.TZorderlist;
import com.ectrip.ec.model.order.TZorderlistId;
import com.ectrip.ec.model.order.YOrder;
import com.ectrip.ec.model.order.YOrderId;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.order.YOrderlistId;
import com.ectrip.ec.model.order.YZorderlist;
import com.ectrip.ec.model.order.YZorderlistId;
import com.ectrip.ec.model.order.common.OrderCombinDTO;
import com.ectrip.ec.model.order.common.OrderProduct;
import com.ectrip.ec.model.ticket.LprPojo;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.ec.ticket.dao.idao.ITicketDAO;
import com.ectrip.ticket.model.provider.Edmcrowdkindpricetab;
import com.ectrip.ticket.model.provider.Edmticketcomposepricetab;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Edpcrowdkindtab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
import com.ectrip.ticket.model.provider.Ordercs;

public class OrderCombin {
    private MOrder morder;// ������

    private List<TOrder> torders;// �����̶���

    private List<TOrderlist> torderlists;// ������Ʒ�б�

    private List<TZorderlist> zorderlists;// ������Ʒ���б�

    private ITicketDAO ticketDao;

    public OrderCombin(OrderCombinDTO combinDto) {
        morder = new MOrder();
        torders = new ArrayList<TOrder>();
        torderlists = new ArrayList<TOrderlist>();
        zorderlists = new ArrayList<TZorderlist>();
        ticketDao = (ITicketDAO) SpringUtil.getBean("ticketDao");
        combinToOrder(combinDto);
    }

    protected void combinToOrder(OrderCombinDTO combinDto) {
        Custom custom = (Custom) ticketDao.get(Custom.class,
                combinDto.getUsid());
        String ddzt = "00";
        if (combinDto.getAskOrder() != null
                && combinDto.getAskOrder().equals("1")) {// ���뵥δ֧������״̬Ϊ24
            ddzt = "24";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * ��������ʼ������
		 */
        morder.setOrid(combinDto.getOrid());
        morder.setOrtp("01");// Ԥ����
        morder.setUsid(custom.getUsid());
        morder.setOrda(Tools.getDays());
        morder.setOrti(Tools.getNowTime());
        morder.setDdzt(ddzt);
        morder.setMont(0.0);
        morder.setZfmont(0.0);
        morder.setTpmont(0.0);
        morder.setYhamnt(0.0);
        morder.setIsa(0l);
        morder.setIsb(0l);
        morder.setIsc(0l);
        morder.setIsd(0l);
        morder.setIse(0l);
        morder.setIsf(0l);
        morder.setIsg(0l);
        morder.setIsh(0l);
        morder.setIsi(0l);
        morder.setIsj(0l);
        morder.setIsjl(0l);
        morder.setYhamnt(0.0);
        morder.setTpfs("00");
        morder.setIsallcp(0l);
        morder.setNote(combinDto.getNote());
        morder.setNotee(combinDto.getNotee());
        morder.setOrdersource(combinDto.getOrderSource());
        if(combinDto.getLprs() != null && !combinDto.getLprs().isEmpty()){
            morder.setNotej(combinDto.getLprs().get(0).getOrnote2());
        }
        HashSet groups = new HashSet(); // �����̷���
        // ��ȡ��Ʒ����
        List<OrderProduct> products = combinDto.getProducts();
        for (int x = 0; x < products.size(); x++) {// ���ݷ����̷���
            OrderProduct tic = products.get(x);
            groups.add(tic.getIscenicid());// ��ӷ�����id
        }
        Iterator ite = groups.iterator();
        while (ite.hasNext()) { // ѭ��������
            LprPojo lprpojo = null;
            Object obj = ite.next();
            // �õ�������
            Esbscenicareatab provider = (Esbscenicareatab) ticketDao.get(
                    Esbscenicareatab.class, new Long(obj.toString()));
            // �õ�Ԥ������Ϣ
            for (LprPojo lpr : combinDto.getLprs()) {
                // if (lpr.getIscenicid().equals(obj.toString()) ||
                // lpr.getIscenicid().equals(provider.getIparentid().toString()))
                // {
                lprpojo = lpr;
                // }
            }
            // ���϶��������̳�Ʊ��ϸ
            List<TOrderlist> tlists = new ArrayList<TOrderlist>();
            String sdate = ""; // ��ʼ����
            String edate = ""; // ��������
            TOrder torder = new TOrder(); // ���϶��������̳�Ʊ��Ϣ
            // ������
            torder.setId(new TOrderId(morder.getOrid(), provider.getIscenicid()));
            // ��Դ��
            if (lprpojo.getSzregionalid() == null
                    || lprpojo.getSzregionalid().equals("")) {
                torder.setIregionalid(1l);
            } else {
                torder.setIregionalid(new Long(lprpojo.getSzregionalid()));
            }

            torder.setUsid(combinDto.getUsid());
            if (provider.getScenictype().equals("07")) {

                torder.setOrfl("03");// ��·
                morder.setDdzt("95");// ��·��Ҫ���
            } else {
            	if("06".equals(provider.getScenictype())){
            		torder.setOrfl("01");// �Ƶ�
            	}else{
            		torder.setOrfl("02");// ��Ʊ
            	}
                // ����һ��ȡƱ���� 2014-04-29 11:45:44 hejiahua
                if(!StringUtils.isBlank(lprpojo.getPassword())){
                    torder.setNotea(lprpojo.getPassword());
                }else{
                    if (lprpojo != null && lprpojo.getZjlb() != null
                            && lprpojo.getZjlb().equals("01")) {
                        if (lprpojo.getZjhm() != null) {
                            torder.setNotea(lprpojo.getZjhm().substring(6, 14));
                        } else {
                            torder.setNotea(getRandom(combinDto.getOrid(),"12345678901"));
                        }
                    } else {
                        torder.setNotea(getRandom(combinDto.getOrid(),"12345678901"));

                    }
                }
            }
            torder.setDdzt(morder.getDdzt());
            torder.setIbusinessid(custom.getIbusinessid());
            torder.setSzscenicname(provider.getSzscenicname());
            torder.setScenictype(provider.getScenictype());
            torder.setDtstartdate(lprpojo.getRzti());
            torder.setDtenddate(lprpojo.getRzti());
            torder.setYhamnt(0.0);
            torder.setZfmont(0.0);
            torder.setMont(0.0);
            torder.setIsa(0l);
            torder.setIsb(0l);
            torder.setIsc(0l);
            torder.setIsd(0l);
            torder.setIse(0l);
            torder.setIsf(0l);
            torder.setIsg(0l);
            torder.setIsh(0l);
            torder.setIsi(0l);
            torder.setIsj(0l);
            torder.setIsjfjf(0l);
            // ��ӵ���
            if (lprpojo.getDaoyouid() != null
                    && !lprpojo.getDaoyouid().equals("")) {
                Custom daoyou = (Custom) ticketDao.get(Custom.class,
                        lprpojo.getDaoyouid());
                torder.setDyusid(daoyou.getUsid());
                torder.setOrnm(daoyou.getLname());
                torder.setOrzj(daoyou.getZjlb());
                torder.setOrhm(daoyou.getZjhm());
                torder.setOrph(daoyou.getMobile());
                torder.setNotea("");
            } else {
                torder.setOrnm(lprpojo.getDaoyou());
                torder.setOrzj(lprpojo.getZjlb());
                torder.setOrhm(lprpojo.getZjhm());
                torder.setOrph(lprpojo.getMobile());
            }
            // ����Ԥ�������̶���
            YOrder yorder = new YOrder();
            // ��ʼ�������̶���
            yorder.setId(new YOrderId(morder.getOrid(), torder.getId()
                    .getIscenicid()));
            yorder.setScenictype(provider.getScenictype());
            yorder.setMont(torder.getMont());
            yorder.setZfmont(torder.getZfmont());
            yorder.setYhamnt(torder.getYhamnt());
            yorder.setUsid(custom.getUsid());
            yorder.setDdzt(torder.getDdzt());
            yorder.setIbusinessid(custom.getIbusinessid());
            yorder.setTdlx(custom.getTtlb());
            yorder.setDtstartdate(torder.getDtstartdate());
            yorder.setDtenddate(torder.getDtenddate());
            yorder.setOrnm(torder.getOrnm());
            yorder.setOrzj(torder.getOrzj());
            yorder.setDyusid(torder.getDyusid());
            yorder.setOrhm(torder.getOrhm());
            yorder.setOrph(torder.getOrph());
            yorder.setFaxno(torder.getFaxno());
            yorder.setIregionalid(torder.getIregionalid());
            yorder.setTpfs("00");
            yorder.setIsj(0l);

            String sql = " from Ordercs where byisuse=1 and ibusinessid="
                    + custom.getIbusinessid() + " order by isequence ";
            List lst = ticketDao.find(sql);
            if (lst != null && lst.size() > 0) {
                for (int k = 0; k < lst.size(); k++) {
                    Ordercs ordercs = (Ordercs) lst.get(k);
                    if (ordercs.getEcs().equals("ornote1")) {
                        if (lprpojo.getOrnote1() != null
                                && !lprpojo.getOrnote1().equals("")) {
                            torder.setOrnote1(lprpojo.getOrnote1());
                        }
                    } else if (ordercs.getEcs().equals("ornote2")) {
                        if (lprpojo.getOrnote2() != null
                                && !lprpojo.getOrnote2().equals("")) {
                            torder.setOrnote2(lprpojo.getOrnote2());
                        }
                    } else if (ordercs.getEcs().equals("ornote3")) {
                        if (lprpojo.getOrnote3() != null
                                && !lprpojo.getOrnote3().equals("")) {
                            torder.setOrnote3(lprpojo.getOrnote3());
                        }
                    } else if (ordercs.getEcs().equals("ornote4")) {
                        if (lprpojo.getOrnote4() != null
                                && !lprpojo.getOrnote4().equals("")) {
                            torder.setOrnote4(lprpojo.getOrnote4());
                        }
                    } else if (ordercs.getEcs().equals("ornote5")) {
                        if (lprpojo.getOrnote5() != null
                                && !lprpojo.getOrnote5().equals("")) {
                            torder.setOrnote5(lprpojo.getOrnote5());
                        }
                    } else if (ordercs.getEcs().equals("ornote6")) {
                        if (lprpojo.getOrnote6() != null
                                && !lprpojo.getOrnote6().equals("")) {
                            torder.setOrnote6(lprpojo.getOrnote6());
                        }
                    } else if (ordercs.getEcs().equals("ornote7")) {
                        if (lprpojo.getOrnote7() != null
                                && !lprpojo.getOrnote7().equals("")) {
                            torder.setOrnote7(lprpojo.getOrnote7());
                        }
                    } else if (ordercs.getEcs().equals("ornote8")) {
                        if (lprpojo.getOrnote8() != null
                                && !lprpojo.getOrnote8().equals("")) {
                            torder.setOrnote8(lprpojo.getOrnote8());
                        }
                    } else if (ordercs.getEcs().equals("ornote9")) {
                        if (lprpojo.getOrnote9() != null
                                && !lprpojo.getOrnote9().equals("")) {
                            torder.setOrnote9(lprpojo.getOrnote9());
                        }
                    } else if (ordercs.getEcs().equals("ornote10")) {
                        if (lprpojo.getOrnote10() != null
                                && !lprpojo.getOrnote10().equals("")) {
                            torder.setOrnote10(lprpojo.getOrnote10());
                        }
                    }
                }
            }
            int ii = 1;
            int zid = 1;
            // ������Ʒ
            for (OrderProduct ticket : products) {
                // �õ���Ʊ
                Edmtickettypetab tt = (Edmtickettypetab) ticketDao.get(
                        Edmtickettypetab.class, ticket.getIticketid());
                // �۸�
                Edpcrowdkindtab crowdkind = (Edpcrowdkindtab) ticketDao.get(
                        Edpcrowdkindtab.class, ticket.getCrowdkindid());
                // ���ϳ�Ʊ��������Ʊ��ϸ
                List<TZorderlist> tzlists = new ArrayList<TZorderlist>();
                // ���϶��������̳�Ʊ��ϸ
                TOrderlist tlist = new TOrderlist();
                if (tt.getIscenicid().intValue() == provider.getIscenicid()
                        .intValue()) {
                    if (sdate.equals("")) {
                        sdate = ticket.getStartdate();
                    } else {
                        try {
                            Date d1 = sdf.parse(sdate);
                            Date d2 = sdf.parse(ticket.getStartdate());
                            if (d2.before(d1)) {
                                sdate = ticket.getStartdate();
                            }
                        } catch (ParseException e) {
                            throw new RuntimeException("����ת������");
                        }
                    }
                    if (edate.equals("")) {
                        sdate = ticket.getStartdate();
                    } else {
                        try {
                            Date d1 = sdf.parse(sdate);
                            Date d2 = sdf.parse(ticket.getStartdate());
                            if (d2.after(d1)) {
                                edate = ticket.getStartdate();
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                            throw new RuntimeException("����ת������");
                        }
                    }
                    tlist.setId(new TOrderlistId(new Long(ii++), torder.getId()
                            .getOrid(), provider.getIscenicid()));
                    tlist.setIscenicid(obj.toString());
                    tlist.setItickettypeid(ticket.getIticketid());
                    tlist.setDtstartdate(ticket.getStartdate());
                    tlist.setDtenddate(Tools.getDate(
                            ticket.getStartdate(),
                            Integer.parseInt(tt.getValidityday().toString()) - 1));
                    tlist.setNumb(ticket.getNum());
                    tlist.setIcrowdkindid(ticket.getCrowdkindid());
                    tlist.setSztickettypename(tt.getSztickettypename());
                    tlist.setSzscenicname(provider.getSzscenicname());
                    tlist.setSzcrowdkindname(crowdkind.getSzcrowdkindname());
                    Edmcrowdkindpricetab corwdkindprice = null;
                    try {
                        String note2 = ticketDao.searchJgfz(custom.getUsid(),
                                provider.getIscenicid());

                        corwdkindprice = ticketDao.getProductPrice(ticket
                                .getIticketid().toString(), custom
                                .getIbusinessid().toString(), ticket
                                .getStartdate(), ticket.getCrowdkindid()
                                .toString(), note2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("��ȡ��Ʒ�۸����");
                    }
                    tlist.setIcrowdkindpriceid(corwdkindprice
                            .getIcrowdkindpriceid());
                    tlist.setPric(corwdkindprice.getMactualsaleprice());
                    tlist.setIoffersschemeid(0l);
                    tlist.setYhnumb(0l);
                    tlist.setYhamnt(0.0);
                    tlist.setAmnt(tlist.getPric() * tlist.getNumb());// Ϊ���ʵ������Ϣ�ӵ�--
                    
                    // add by koka on 20170728
                    tlist.setStarttime(ticket.getStarttime());
                    tlist.setEndtime(ticket.getEndtime());
                    tlist.setTimeid(ticket.getTimeid());
                    List sontickets = ticketDao.getSonticketlist(corwdkindprice.getIcrowdkindpriceid());
                    for (int i = 0; i < sontickets.size(); i++) {
                        // ���Ʊ�۸���
                        Edmticketcomposepricetab sonticket = (Edmticketcomposepricetab) sontickets
                                .get(i);
                        TZorderlist zorderlist = new TZorderlist();
                        zorderlist.setId(new TZorderlistId(new Long(zid++),
                                tlist.getId().getOrderlistid(), tlist.getId()
                                .getOrid(), torder.getId()
                                .getIscenicid()));
                        zorderlist.setItickettypeid(ticket.getIticketid());// ��ƱID
                        zorderlist.setIztickettypeid(sonticket
                                .getItickettypeid());// ��ƱID
                        zorderlist.setIcrowdkindpriceid(sonticket.getId()
                                .getIcrowdkindpriceid());// Ʊ��ID
                        zorderlist.setIcrowdkindid(ticket.getCrowdkindid());// ��Ⱥ����
                        zorderlist.setZyhamnt(0.0);
                        zorderlist.setZyhnumb(new Long(0));
                        zorderlist.setDtstartdate(tlist.getDtstartdate() + " "
                                + "00:00:00");
                        zorderlist.setDtenddate(tlist.getDtenddate() + " "
                                + "23:59:59");
                        zorderlist.setTripid(new Long(0));
                        zorderlist.setIvenueareaid(new Long(0));
                        zorderlist.setIvenueid(new Long(0));
                        zorderlist.setIvenueseatsid(new Long(0));
                        zorderlist.setZpric(sonticket.getMactualsaleprice());
                        zorderlist.setZnumb(tlist.getNumb()
                                * sonticket.getNumb());
                        zorderlist.setZamnt(zorderlist.getZpric()
                                * zorderlist.getZnumb());
                        YZorderlist y_zordderlist = new YZorderlist();
                        y_zordderlist.setId(new YZorderlistId(zorderlist
                                .getId().getZorderlistid(), zorderlist.getId()
                                .getOrderlistid(),
                                zorderlist.getId().getOrid(), zorderlist
                                .getId().getIscenicid()));
                        y_zordderlist.setIcrowdkindpriceid(zorderlist
                                .getIcrowdkindpriceid());
                        y_zordderlist.setIcrowdkindid(zorderlist
                                .getIcrowdkindid());
                        y_zordderlist.setItickettypeid(zorderlist
                                .getItickettypeid());
                        y_zordderlist.setIztickettypeid(zorderlist
                                .getIztickettypeid());
                        y_zordderlist.setDtstartdate(zorderlist
                                .getDtstartdate());
                        y_zordderlist.setDtenddate(zorderlist.getDtenddate());
                        y_zordderlist.setTripid(zorderlist.getTripid());
                        y_zordderlist.setIvenueid(zorderlist.getIvenueid());
                        y_zordderlist.setIvenueareaid(zorderlist
                                .getIvenueareaid());
                        y_zordderlist.setIvenueseatsid(zorderlist
                                .getIvenueseatsid());
                        y_zordderlist.setZpric(zorderlist.getZpric());
                        y_zordderlist.setZnumb(zorderlist.getZnumb());
                        y_zordderlist.setZyhnumb(zorderlist.getZyhnumb());
                        y_zordderlist.setZyhamnt(zorderlist.getZyhamnt());
                        y_zordderlist.setZamnt(zorderlist.getZamnt());
                        y_zordderlist.setJsprice(zorderlist.getJsprice());
                        y_zordderlist.setIsa(0l);
                        y_zordderlist.setIsb(0l);
                        y_zordderlist.setIsc(0l);
                        y_zordderlist.setIsd(0l);
                        y_zordderlist.setIse(0l);
                        y_zordderlist.setIsf(0l);
                        y_zordderlist.setIsg(0l);
                        y_zordderlist.setIsh(0l);
                        y_zordderlist.setIsi(0l);
                        y_zordderlist.setIsj(0l);
                        zorderlist.setYzorderlist(y_zordderlist);
                        tzlists.add(zorderlist);
                        zorderlists.add(zorderlist);
                    }
                    tlist.setZorderlist(tzlists);
                    tlist.setIsa(0l);
                    tlist.setIsb(0l);
                    tlist.setIsc(0l);
                    tlist.setIsd(0l);
                    tlist.setIse(0l);
                    tlist.setIsf(0l);
                    tlist.setIsg(0l);
                    tlist.setIsh(0l);
                    tlist.setIsi(0l);
                    tlist.setIsj(0l);
                    tlists.add(tlist);
                    torderlists.add(tlist);
                    YOrderlist ylist = new YOrderlist();
                    ylist.setId(new YOrderlistId(
                            tlist.getId().getOrderlistid(), morder.getOrid(),
                            provider.getIscenicid()));
                    ylist.setItickettypeid(tt.getItickettypeid());
                    ylist.setIcrowdkindpriceid(tlist.getIcrowdkindpriceid());
                    ylist.setIcrowdkindid(tlist.getIcrowdkindid());
                    ylist.setDtstartdate(tlist.getDtstartdate());
                    ylist.setDtenddate(tlist.getDtenddate());
                    ylist.setPric(tlist.getPric());
                    ylist.setNumb(tlist.getNumb());
                    ylist.setAmnt(tlist.getAmnt());
                    ylist.setYhamnt(tlist.getYhamnt());
                    ylist.setYhnumb(tlist.getYhnumb());
                    ylist.setIsa(0l);
                    ylist.setIsb(0l);
                    ylist.setIsc(0l);
                    ylist.setIsd(0l);
                    ylist.setIse(0l);
                    ylist.setIsf(0l);
                    ylist.setIsg(0l);
                    ylist.setIsh(0l);
                    ylist.setIsi(0l);
                    ylist.setIsj(0l);
                    // add by koka on 20170728
                    ylist.setStarttime(ticket.getStarttime());
                    ylist.setEndtime(ticket.getEndtime());
                    tlist.setYorderlist(ylist);

                }
            }
            torder.setTorderlists(tlists);
            torder.setYorder(yorder);
            torders.add(torder);

            morder.setStdt(sdate);
            morder.setTorders(torders);
        }
    }

    public String getRandom(String orid,String phone){
        if ("".equals(phone) || phone == null)
            return "12345678";
        int left = 3;
        int right = phone.length() - 1;
        int x = 0;
        int y = 0;
        StringBuffer sb = new StringBuffer();
        String[][] password = new String[][]{
                {"93", "06", "66", "31", "48", "20", "59", "89", "67", "81"},
                {"40", "68", "74", "88", "57", "01", "91", "96", "82", "05"},
                {"16", "03", "21", "28", "50", "25", "90", "35", "07", "25"},
                {"78", "44", "17", "57", "51", "02", "06", "40", "35", "31"},
                {"86", "01", "67", "96", "50", "20", "95", "03", "11", "33"},
                {"26", "20", "43", "90", "23", "42", "33", "73", "67", "77"},
                {"53", "42", "48", "41", "34", "11", "10", "17", "18", "42"},
                {"16", "86", "21", "90", "09", "07", "50", "99", "32", "40"},
                {"03", "49", "81", "99", "20", "96", "72", "58", "00", "11"},
                {"25", "47", "24", "22", "36", "66", "61", "53", "98", "83"}};
        if ((phone.length() - 3) < 8) {
            if (phone.length() < 8) {
                int num = 8 - phone.length();
                StringBuffer add = new StringBuffer();
                for (int i = 0; i < num; i++) {
                    add.append(i);
                }
                phone += add.toString();
            }
            left = 0;
        }
        for (int i = 0; i < 4; i++) {
            x = phone.charAt(left) - '0';
            y = phone.charAt(right) - '0';
            sb.append(password[x][y]);
            left++;
            right--;
        }
        sb.append(orid.substring(orid.length()));
        return sb.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(getRandom("20160329000000001","12345678901"));
//    }

    public MOrder getMorder() {
        return morder;
    }

    public void setMorder(MOrder morder) {
        this.morder = morder;
    }

    public List<TOrder> getTorders() {
        return torders;
    }

    public void setTorders(List<TOrder> torders) {
        this.torders = torders;
    }

    public List<TOrderlist> getTorderlists() {
        return torderlists;
    }

    public void setTorderlists(List<TOrderlist> torderlists) {
        this.torderlists = torderlists;
    }

    public List<TZorderlist> getZorderlists() {
        return zorderlists;
    }

    public void setZorderlists(List<TZorderlist> zorderlists) {
        this.zorderlists = zorderlists;
    }
}
