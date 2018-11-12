package com.ectrip.sys.other.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.other.AnnualCardOption;
import com.ectrip.sys.other.dao.idao.IAnnualCardDao;

/**
 * Created by cxh on 2016/05/23.
 */
public class AnnualCardDao extends GenericDao implements IAnnualCardDao {

    public PaginationSupport queryAnnualCardByOption(AnnualCardOption option,int page,int pageSize,String url){

        StringBuffer hsql = new StringBuffer();
        String date = Tools.getDays();
        hsql.append("select new map(t.sztickettypename as sztickettypename,s.szticketprintno as szticketprintno,s.myzj as myzj," +
                "s.name1 as name,s.zjno1 as mobile,s.dtstartdate as dtstartdate,s.dtenddate as dtenddate)");
        if(option.isHistory()){//��ʷ����
            hsql.append(" from Stssoldtickettablist s,");
        }else{
            hsql.append(" from Stssoldtickettab s,");
        }
        hsql.append("Edmtickettypetab t where s.itickettypeid = t.itickettypeid and t.bycategorytype = '0014' ");
        if(!StringUtils.isBlank(option.getCredentials())){
            hsql.append("and s.myzj = '"+option.getCredentials()+"' ");
        }
        if(!StringUtils.isBlank(option.getName())){
            hsql.append("and s.name1 = '"+option.getName()+"' ");
        }
        if(!StringUtils.isBlank(option.getMobile())){
            hsql.append("and s.zjno1 = '"+option.getMobile()+"' ");
        }
        if(option.getValid() == 1){//��Ч�꿨
            hsql.append("and ((substr(s.dtstartdate,1,10) <= '"+ date +"' and substr(s.dtenddate,1,10) >= '"+ date +"') ");
        }else if(option.getValid() == -1){//��Ч�꿨
            hsql.append(" and substr(s.dtmakedate,1,10) >= '"+ date +"' and substr(s.dtenddate,1,10) <= '"+ date +"' ");
        }
        hsql.append(" order by s.id.isalesvoucherid desc ");
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null){
            List<Map<String,Object>> list = ps.getItems();
            if(list != null && !list.isEmpty()){
                for (int i = 0;i < list.size();i++){
                    Map<String,Object> map = list.get(i);
                    String beginDate = map.get("dtstartdate").toString();
                    String endDate = map.get("dtenddate").toString();
                    if(date.compareTo(beginDate) >= 0 && date.compareTo(endDate) <= 0){
                        map.put("isvalid",1);
                    }else{
                        map.put("isvalid",-1);
                    }
                }
            }
        }
        return ps;
    }

    public List queryAnnualCardByOption(AnnualCardOption option){

        StringBuffer hsql = new StringBuffer();
        String date = Tools.getDays();
        hsql.append("select new map(t.sztickettypename as sztickettypename,s.szticketprintno as szticketprintno,s.myzj as myzj," +
                "s.name1 as name,s.zjno1 as mobile,s.dtstartdate as dtstartdate,s.dtenddate as dtenddate)");
        hsql.append(" from Stssoldtickettab s,");
        hsql.append("Edmtickettypetab t where s.itickettypeid = t.itickettypeid and t.bycategorytype = '0014' ");
        if(!StringUtils.isBlank(option.getCredentials())){
            hsql.append("and s.myzj = '"+option.getCredentials()+"' ");
        }
        if(!StringUtils.isBlank(option.getName())){
            hsql.append("and s.name1 = '"+option.getName()+"' ");
        }
        if(!StringUtils.isBlank(option.getMobile())){
            hsql.append("and s.zjno1 = '"+option.getMobile()+"' ");
        }
        if(option.getValid() == 1){//��Ч�꿨
            hsql.append("and ((substr(s.dtstartdate,1,10) <= '"+ date +"' and substr(s.dtenddate,1,10) >= '"+ date +"') ");
        }else if(option.getValid() == -1){//��Ч�꿨
            hsql.append(" and substr(s.dtmakedate,1,10) >= '"+ date +"' and substr(s.dtenddate,1,10) <= '"+ date +"' ");
        }
        hsql.append(" order by s.id.isalesvoucherid desc ");
        List newList = this.find(hsql.toString());

        String sql = hsql.toString().replace("Stssoldtickettab","Stssoldtickettablist");
        List oldList = this.find(sql);

        List list = new ArrayList();
        if(newList != null && !newList.isEmpty()){
            list.addAll(newList);
        }
        if(oldList != null && !oldList.isEmpty()){
            list.addAll(oldList);
        }
        return list;
    }
}
