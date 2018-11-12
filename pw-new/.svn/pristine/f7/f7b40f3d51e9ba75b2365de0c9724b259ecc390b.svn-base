package com.ectrip.ticket.common.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;

/**
 * Created by cxh on 2015/12/4.
 */
public class OrderTask extends TimerTask {
    public void run() {
        try{
            changeDdzt();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeDdzt() throws Exception{
        IGenericDao genericDao = (IGenericDao) SpringUtil.getBean("genericDao");
        Sysparv5 sys = (Sysparv5) genericDao.get(Sysparv5.class,new Sysparv5Id("DDCS","0001"));
        long time = 30;
        if(sys != null && sys.getIsvalue() == 1 && !StringUtils.isBlank(sys.getPmva())){
            time = Long.parseLong(sys.getPmva());
        }
        String times = Tools.getDayTimes();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String daytimes = sdf.format(new Date(sdf.parse(times).getTime()-time*60*1000));
        String rzti = daytimes.substring(0, 10);
        String orti = daytimes.substring(11, 19);
        //调用电商服务接口
//        orderService.changDdzt(rzti,orti);
    }
}
