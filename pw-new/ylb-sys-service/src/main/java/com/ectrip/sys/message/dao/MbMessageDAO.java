package com.ectrip.sys.message.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.ec.model.order.MOrder;
import com.ectrip.ec.model.order.TOrderlist;
import com.ectrip.ec.model.order.YOrderlist;
import com.ectrip.ec.model.user.Custom;
import com.ectrip.sys.message.dao.idao.IMbMessageDAO;
import com.ectrip.sys.model.syspar.Contmessage;
import com.ectrip.sys.model.syspar.Mbmessage;
import com.ectrip.ticket.model.provider.Edmtickettypetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;
@Repository
public class MbMessageDAO extends GenericDao implements IMbMessageDAO {

    /**
     * 获取短信列表 Describe:
     *
     * @auth:huangyuqi
     * @param rzti开始时间
     * @param ldti结束时间
     * @param page页码
     * @param pageSize每页显示数
     * @param url访问路径
     * @return return:List Date:2011-11-24
     */
    public PaginationSupport queryMessageList(String rzti, String ldti, int page, int pageSize, String url) {
        PaginationSupport ps = null;
        String hsql = "select new map(m.seq as seq,m.revmbno as revmbno,m.note as note,m.dtime as dtime,m.stime as stime,m.isok as isok,m.quantity as quantity ) from Mbmessage m where substr(m.dtime,1,10) >='"
                + rzti + "' and substr(m.dtime,1,10)<='" + ldti + "' order by m.seq desc";
        ps = this.findPage(hsql, pageSize, page, url);
        return ps;
    }

    /**
     * 增加短信 Describe:
     *
     * @auth:huangyuqi
     * @param message短信实体
     *            return:void Date:2011-11-24
     */
    public void insertMessage(Mbmessage message) {
        // 获取最大编号
        Long seq = this.getMaxPk("seq", new String[] {}, new String[] {}, "Mbmessage");
        message.setSeq(seq + 1);
        this.save(message);
    }

    /**
     * 增加短信 Describe:
     *
     * @auth:huangyuqi
     * @param telno
     * @param note
     *            return:void Date:2011-11-24
     */
    public void insertMessage(String telno, String note) {
        if (telno != null && !"".equals(telno)) {
            Mbmessage message = new Mbmessage();
            // 获取最大编号
            Long seq = this.getMaxPk("seq", new String[] {}, new String[] {}, "Mbmessage");
            message.setSeq(seq + 1);// 流水号
            message.setRevmbno(telno);// 手机号
            message.setNote(note);// 短信内容
            message.setDtime(Tools.getDayTimes());// 短信生成时间
            message.setQuantity(0);// 发送次数
            message.setIsok(0);// 是否发送成功
            this.save(message);
        }

    }

    /**
     *
     * Describe:新的短信放松方式,用content中的内容替换模板中的@字符,可是数组，但必须与末班中的@相对应
     *
     * @auth:yangguang
     * @param telno
     *            要发送的手机号码
     * @param type
     *            发送类型 用此参数获取发送信息的模板
     * @param content
     *            要替换的内容 ，此参数替换模板中的@字符 return:void Date:2012-3-23
     */
    public void sendMessageNew(String telno, String type, String[] content) {
        String hql = "from Contmessage where controlpoin='" + type + "' and byisuse=1 and inote1 is null";
        List list = this.find(hql);
        if (list != null && list.size() > 0) {
            Contmessage templates = (Contmessage) list.get(0);
            String sendmsg = templates.getTemplates();
            StringBuffer sends = new StringBuffer();
            if (content.length > 0) {
                String[] strarr = sendmsg.split("@");
                if (strarr != null && !"".equals(strarr) && strarr.length > 1) {
                    for (int i = 0; i < strarr.length; i++) {
                        sends.append(strarr[i]);
                        if (i < content.length) {
                            sends.append(content[i]);
                        }
                    }
                }
            }
            //假设都是订单号
            String info = sends.toString();
            if(info == null || "".equals(info)){
                info = sendmsg;
                sends = new StringBuffer(info);
            }
            try{
                if (content.length > 0 )
                {
                    String articlePage = "\\$[A-Za-z]*\\$";

                    Pattern p=Pattern.compile(articlePage);
                    Matcher m= p.matcher(sends);
                    while ( m.find()) {
                        String temp = sends.substring(m.start(),m.end());
                        info = info.replace(temp, getValue(content[0],temp)) ;
                    }




                }
            } catch(Exception e)
            {
                System.out.println("发送短信出错");
            }
            sends = null;


            /**
             *  替换模板中的内容，
             */
            Mbmessage message = new Mbmessage();
            // 获取最大编号
            Long seq = this.getMaxPk("seq", new String[] {}, new String[] {}, "Mbmessage");
            message.setSeq(seq + 1);// 流水号
            message.setRevmbno(telno);// 手机号
            message.setNote(info);// 短信内容
            message.setDtime(Tools.getDayTimes());// 短信生成时间
            message.setQuantity(0);// 发送次数
            message.setIsok(0);// 是否发送成功
            this.save(message);
        }

    }

    public void sendMessage(String telno, String ip, String type, String[] content) {
        String hql = "from Contmessage where controlpoin='" + type + "' and byisuse=1 and inote1 is null";
        List list = this.find(hql);
        if (list != null && list.size() > 0) {
            Contmessage templates = (Contmessage) list.get(0);
            String sendmsg = templates.getTemplates();
            StringBuffer sends = new StringBuffer();
            if (content.length > 0) {
                String[] strarr = sendmsg.split("@");
                if (strarr != null && !"".equals(strarr) && strarr.length > 1) {
                    for (int i = 0; i < strarr.length; i++) {
                        sends.append(strarr[i]);
                        if (i < content.length) {
                            sends.append(content[i]);
                        }
                    }
                }
            }
            //假设都是订单号
            String info = sends.toString();
            if(info == null || "".equals(info)){
                info = sendmsg;
                sends = new StringBuffer(info);
            }
            try{
                if (content.length > 0 )
                {
                    String articlePage = "\\$[A-Za-z]*\\$";

                    Pattern p=Pattern.compile(articlePage);
                    Matcher m= p.matcher(sends);
                    while ( m.find()) {
                        String temp = sends.substring(m.start(),m.end());
                        info = info.replace(temp, getValue(content[0],temp)) ;
                    }




                }
            } catch(Exception e)
            {
                System.out.println("发送短信出错");
            }
            sends = null;


            /**
             *  替换模板中的内容，
             */
            Mbmessage message = new Mbmessage();
            // 获取最大编号
            Long seq = this.getMaxPk("seq", new String[] {}, new String[] {}, "Mbmessage");
            message.setSeq(seq + 1);// 流水号
            message.setRevmbno(telno);// 手机号
            message.setNote(info);// 短信内容
            message.setDtime(Tools.getDayTimes());// 短信生成时间
            message.setQuantity(0);// 发送次数
            message.setIsok(0);// 是否发送成功
            message.setIp(ip);
            message.setType(type);
            this.save(message);
        }

    }

    /**
     * 读取订单修改
     * @param orid
     * @return
     */

    String getValue(String orid, String filedname) {
        String ls_value = "";
        filedname = filedname.substring(1, filedname.length() - 1);
        try {
            List list = getTOrderList(orid);

            if (list != null && list.size() > 0) {
                Map m = (Map) list.get(0);
                ls_value = m.get(filedname)==null?filedname:m.get(filedname).toString();
            }

        } catch (Exception e) {
            ls_value = filedname;
        }
        return ls_value;
    }
    /**
     * 取值
     * @param orid
     * @return
     */
    List getTOrderList(String orid) {
        String hql="select new map(t.isi as isi,t.id.orid as orid,t.id.iscenicid as iscenicid,t.usid as usid,t.ddzt as ddzt,t.dyusid as dyusid,t.scenictype as scenictype,e.szscenicname as szscenicname,t.dtstartdate as dtstartdate,t.dtenddate as dtenddate,t.mont as mont,t.zfmont as zfmont,t.orph as orph,t.ornm as ornm,t.orhm as orhm,t.notea as notea) from TOrder t,Esbscenicareatab e where t.id.orid='"
                + orid + "' and t.id.iscenicid=e.iscenicid";
        List list = this.find(hql);
        if(list!=null&&!list.isEmpty()){
            for(int i=0;i<list.size();i++){
                Map map = (Map) list.get(i);
                MOrder morder =  (MOrder) get(MOrder.class, orid);
                Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
                Esbscenicareatab provider = (Esbscenicareatab) this.get(Esbscenicareatab.class, iscenicid);
                map.put("telno", provider.getSzphone());
                String rzti = map.get("dtstartdate").toString();
                String ldti = map.get("dtenddate").toString();
                map.put("ryear", rzti.substring(0, 4));
                map.put("rmonth", rzti.substring(5, 7));
                map.put("rday", rzti.substring(8, 10));
                map.put("lyear", ldti.substring(0, 4));
                map.put("lmonth", ldti.substring(5, 7));
                map.put("lday", ldti.substring(8, 10));
                map.put("noteg", morder.getNoteg()) ;
                map.put("pwd",map.get("notea")==null?"无":map.get("notea").toString());



                String sql = "";
                if(provider.getScenictype().equals("06")){
                    sql = "select max(tl.numb) as numb from TOrderlist tl where tl.id.orid='"+orid+"' and tl.id.iscenicid="+iscenicid;
                }else{
                    sql = "select sum(tl.numb) as numb from TOrderlist tl where tl.id.orid='"+orid+"' and tl.id.iscenicid="+iscenicid;
                }
                List list2 = this.find(sql);
                int num = 0;
                if(list2!=null&&!list2.isEmpty()){
                    Object o = list2.get(0);
                    if(o!=null){
                        num = Integer.parseInt(o.toString());
                    }
                }
                map.put("numb", num);
                sql = "from TOrderlist tl where tl.id.orid='"+orid+"' and tl.id.iscenicid="+iscenicid;
                list2 = this.find(sql);
                if(list2!=null&&!list2.isEmpty()){
                    TOrderlist torderlist = (TOrderlist) list2.get(0);
                    Edmtickettypetab ticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, torderlist.getItickettypeid());
                    map.put("productname", ticket.getSztickettypename());
                }
            }
        }else{//退订单查询
            hql = "select new map(m.srid as srid,m.orid as orid,y.id.iscenicid as iscenicid,s.szscenicname as szscenicname,m.tpmont as tpmont,m.zfmont as zfmont,nvl(m.tpsx,0) as tpsx,y.dtstartdate as dtstartdate,y.dtenddate as dtenddate) from MOrder m,YOrder y,Esbscenicareatab s where m.orid = y.id.orid and y.id.iscenicid = s.iscenicid and m.orid = '"+orid+"' ";
            list = this.find(hql);
            if(list!=null&&!list.isEmpty()){
                for(int i=0;i<list.size();i++){
                    Map map = (Map) list.get(i);
                    Long iscenicid = Long.parseLong(map.get("iscenicid").toString());
                    Esbscenicareatab provider = (Esbscenicareatab) this.get(Esbscenicareatab.class, iscenicid);
                    map.put("telno", provider.getSzphone());
                    map.put("stmont", Double.parseDouble(map.get("tpmont").toString())-Double.parseDouble(map.get("tpsx").toString()));
                    String rzti = map.get("dtstartdate").toString();
                    String ldti = map.get("dtenddate").toString();
                    map.put("ryear", rzti.substring(0, 4));
                    map.put("rmonth", rzti.substring(5, 7));
                    map.put("rday", rzti.substring(8, 10));
                    map.put("lyear", ldti.substring(0, 4));
                    map.put("lmonth", ldti.substring(5, 7));
                    map.put("lday", ldti.substring(8, 10));
                    String sql = "";
                    if(provider.getScenictype().equals("06")){
                        sql = "select max(tl.numb) as numb from YOrderlist tl where tl.id.orid='"+orid+"' and tl.id.iscenicid="+iscenicid;
                    }else{
                        sql = "select sum(tl.numb) as numb from YOrderlist tl where tl.id.orid='"+orid+"' and tl.id.iscenicid="+iscenicid;
                    }
                    List list2 = this.find(sql);
                    int num = 0;
                    if(list2!=null&&!list2.isEmpty()){
                        Object o = list2.get(0);
                        if(o!=null){
                            num = Integer.parseInt(o.toString());
                        }
                    }
                    map.put("numb", num);
                    sql = "from YOrderlist tl where tl.id.orid='"+orid+"' and tl.id.iscenicid="+iscenicid;
                    list2 = this.find(sql);
                    if(list2!=null&&!list2.isEmpty()){
                        YOrderlist yorderlist = (YOrderlist) list2.get(0);
                        Edmtickettypetab ticket = (Edmtickettypetab) this.get(Edmtickettypetab.class, yorderlist.getItickettypeid());
                        map.put("productname", ticket.getSztickettypename());
                    }
                }
            }
        }
        return list;
    }


    /**
     * 修改短信 Describe:
     *
     * @auth:huangyuqi
     * @param message
     *            return:void Date:2011-11-24
     */
    public void updateMessage(Mbmessage message) {
        this.update(message);
    }

    /**
     * 根据订单号发送信息 Describe:
     *
     * @auth:huangyuqi
     * @param orid订单号
     * @param telno
     *            电话号
     * @param note
     *            短信内容 return:void Date:2011-12-8
     */
    public void saveMessageByOrid(String orid, String telno, String note) {
        StringBuffer content = new StringBuffer();
        content.append(WebContant.CORPNAME + ":");
        content.append(note);

        Mbmessage message = new Mbmessage();
        // 获取最大编号
        Long seq = this.getMaxPk("seq", new String[] {}, new String[] {}, "Mbmessage");
        message.setSeq(seq + 1);// 流水号
        message.setRevmbno(telno);// 手机号
        message.setNote(content.toString());// 短信内容
        message.setDtime(Tools.getDayTimes());// 短信生成时间
        message.setQuantity(0);// 发送次数
        message.setIsok(0);// 是否发送成功
        this.save(message);
    }

    /**
     * 注册时发送信息 Describe:
     *
     * @auth:huangyuqi
     * @param usid
     *            return:void Date:2011-12-8
     */
    public void saveMessageRegister(String usid) {

        StringBuffer content = new StringBuffer();
        String strlgtp = "";
        Custom custom = (Custom) this.get(Custom.class, usid);
        if ("01".equals(custom.getLgtp())) {
            content.append("尊敬的" + usid + " 用户，您好：恭喜您注册成为" + WebContant.DOMAINAME + "用户，您注册的类别是：散客用户");
        } else {
            content.append("尊敬的" + usid + " 用户，您好：恭喜您注册成为" + WebContant.DOMAINAME + "用户，您注册的类别是：团体用户");
            if ("01".equals(custom.getTtlb())) {
                content.append(",团体类别为：旅行社");
            } else if ("02".equals(custom.getTtlb())) {
                content.append(",团体类别为：导游");
            }
        }

        Mbmessage message = new Mbmessage();
        // 获取最大编号
        Long seq = this.getMaxPk("seq", new String[] {}, new String[] {}, "Mbmessage");
        message.setSeq(seq + 1);// 流水号
        message.setRevmbno(custom.getMobile());// 手机号
        message.setNote(content.toString());// 短信内容
        message.setDtime(Tools.getDayTimes());// 短信生成时间
        message.setQuantity(0);// 发送次数
        message.setIsok(0);// 是否发送成功
        this.save(message);

    }

    /**
     * 修改密码时发送信息 Describe:
     *
     * @auth:huangyuqiz
     * @param usid
     *            return:void Date:2011-12-8
     */
    public void saveMebByEidtUserInfo(String usid) {

        StringBuffer content = new StringBuffer();
        String strlgtp = "";
        Custom custom = (Custom) this.get(Custom.class, usid);
        content.append(WebContant.DOMAINAME + ":");
        if ("01".equals(custom.getLgtp())) {
            content.append("尊敬的" + usid + " 用户，您好：您的密码已经更改成功！");
        } else {
            content.append("尊敬的" + usid + " 用户，您好：您的密码已经更改成功！");
        }

        Mbmessage message = new Mbmessage();
        // 获取最大编号
        Long seq = this.getMaxPk("seq", new String[] {}, new String[] {}, "Mbmessage");
        message.setSeq(seq + 1);// 流水号
        message.setRevmbno(custom.getMobile());// 手机号
        message.setNote(content.toString());// 短信内容
        message.setDtime(Tools.getDayTimes());// 短信生成时间
        message.setQuantity(0);// 发送次数
        message.setIsok(0);// 是否发送成功
        this.save(message);

    }


    public static void main(String[] args) {
        System.out.println("dffgfj@hjhgj".split("@").length);
    }

    public PaginationSupport queryMessageList(String mobile, String contentMsg,
                                              String rzti, String ldti, int page, int pageSize, String url) {
        // TODO Auto-generated method stub
        return null;
    }

	public List getMessagelist(String today) {
		List list = new ArrayList();
		String hsql = " from Mbmessage m where substr(m.dtime,1,10) >='"
                + today + "' and substr(m.dtime,1,10)<='" + today + "' and m.isok=0 and m.quantity <=3 order by m.seq desc";
		list = this.find(hsql);

		return list;
	}
	
	
	public int queryByTelType(String telno, String type){
		int count = 0;
		String hsql = " select count(*) as monum from Mbmessage where substr(dtime, 0, 10) = to_char(sysdate, 'yyyy-mm-dd') and isok = 1 ";
		hsql += " and revmbno='";
		hsql +=	 telno;
		hsql +=  "' and type='";
		hsql +=	 type+"' ";
		List list = this.find(hsql);
		if(list!=null&&list.size()>0){
			count = Integer.parseInt(String.valueOf(list.get(0)));
		}

		return count;
	}
}
