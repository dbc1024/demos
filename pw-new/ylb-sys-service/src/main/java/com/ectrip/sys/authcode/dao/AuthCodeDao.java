package com.ectrip.sys.authcode.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.authcode.dao.idao.IAuthCodeDao;
import com.ectrip.sys.authcode.dto.AuthCodeInputDto;
import com.ectrip.sys.model.authcode.AuthCode;
import com.ectrip.sys.model.authcode.CodeType;
import com.ectrip.ticket.model.afcset.Esbaccessequiptab;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import org.springframework.stereotype.Repository;

/**
 * Created by chenxinhao on 2017/2/23.
 */
@Repository
public class AuthCodeDao extends GenericDao implements IAuthCodeDao {

    public PaginationSupport searchProviderAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(iscenicid as code,szscenicname as name) from Esbscenicareatab where byisuse = 1 ");
        if(!StringUtils.isBlank(dto.getSearchName())){
            hsql.append(" szscenicname like '%"+dto.getSearchName()+"%' ");
        }
        hsql.append(" order by scenictype,iordernumber desc");
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null && !ps.getItems().isEmpty()){
            List list = ps.getItems();
            for (int i = 0;i<list.size();i++){
                Map map = (Map) list.get(i);
                map.put("codeTypeName",CodeType.PROVIDER.getName());
                map.put("codeType",CodeType.PROVIDER.getCode());
                //��ȡ��Ȩ��Ϣ
                AuthCode authCode = getAuthCode(CodeType.PROVIDER.getCode(),map.get("code").toString());
                if(authCode != null){
                    map.put("id",authCode.getId());
                    map.put("authCode",authCode.getAuthCode());
                    map.put("status",1);
                    map.put("baiduStatus",authCode.getBaiduStatus());
                    map.put("dtmakedate",authCode.getDtmakedate());
                }else{
                    map.put("id",0);
                    map.put("status",0);
                    map.put("baiduStatus","00");
                }
            }
        }
        return ps;
    }

    public PaginationSupport searchProductAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(itickettypeid as code,sztickettypename as name) from Edmtickettypetab where byisuse = 1 ");
        if(!StringUtils.isBlank(dto.getSearchName())){
            hsql.append(" sztickettypename like '%"+dto.getSearchName()+"%' ");
        }
        hsql.append(" order by isequence desc");
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null && !ps.getItems().isEmpty()){
            List list = ps.getItems();
            for (int i = 0;i<list.size();i++){
                Map map = (Map) list.get(i);
                map.put("codeTypeName",CodeType.PRODUCT.getName());
                map.put("codeType",CodeType.PRODUCT.getCode());
                //��ȡ��Ȩ��Ϣ
                AuthCode authCode = getAuthCode(CodeType.PRODUCT.getCode(),map.get("code").toString());
                if(authCode != null){
                    map.put("id",authCode.getId());
                    map.put("authCode",authCode.getAuthCode());
                    map.put("status",1);
                    map.put("baiduStatus",authCode.getBaiduStatus());
                    map.put("dtmakedate",authCode.getDtmakedate());
                }else{
                    map.put("id",0);
                    map.put("status",0);
                    map.put("baiduStatus","00");
                }
            }
        }
        return ps;
    }

    public PaginationSupport searchPriceAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(p.icrowdkindpriceid as code,t.sztickettypename as productName,p.icrowdkindpricecode as priceCode) from Edmcrowdkindpricetab p,Edmtickettypetab t where " +
                " p.byisuse = 1 and t.itickettypeid = p.itickettypeid ");
        if(!StringUtils.isBlank(dto.getSearchName())){
            hsql.append(" t.sztickettypename like '%"+dto.getSearchName()+"%' ");
        }
        hsql.append(" order by p.isequence desc");
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null && !ps.getItems().isEmpty()){
            List list = ps.getItems();
            for (int i = 0;i<list.size();i++){
                Map map = (Map) list.get(i);
                map.put("name",map.get("productName").toString()+"("+map.get("priceCode").toString()+")");
                map.put("codeTypeName",CodeType.PRICE.getName());
                map.put("codeType",CodeType.PRICE.getCode());
                //��ȡ��Ȩ��Ϣ
                AuthCode authCode = getAuthCode(CodeType.PRICE.getCode(),map.get("code").toString());
                if(authCode != null){
                    map.put("id",authCode.getId());
                    map.put("authCode",authCode.getAuthCode());
                    map.put("status",1);
                    map.put("dtmakedate",authCode.getDtmakedate());
                    map.put("baiduStatus",authCode.getBaiduStatus());
                }else{
                    map.put("id",0);
                    map.put("status",0);
                    map.put("baiduStatus","00");
                }
            }
        }
        return ps;
    }

    public PaginationSupport searchGardenAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(id.igardengateid as code,szgardengatename as name) from Esbgardengatetab where byisuse = 1 ");
        if(!StringUtils.isBlank(dto.getSearchName())){
            hsql.append(" szgardengatename like '%"+dto.getSearchName()+"%' ");
        }
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null && !ps.getItems().isEmpty()){
            List list = ps.getItems();
            for (int i = 0;i<list.size();i++){
                Map map = (Map) list.get(i);
                map.put("codeTypeName",CodeType.GARDEN.getName());
                map.put("codeType",CodeType.GARDEN.getCode());
                //��ȡ��Ȩ��Ϣ
                AuthCode authCode = getAuthCode(CodeType.GARDEN.getCode(),map.get("code").toString());
                if(authCode != null){
                    map.put("id",authCode.getId());
                    map.put("authCode",authCode.getAuthCode());
                    map.put("status",1);
                    map.put("dtmakedate",authCode.getDtmakedate());
                    map.put("baiduStatus",authCode.getBaiduStatus());
                }else{
                    map.put("id",0);
                    map.put("status",0);
                    map.put("baiduStatus","00");
                }
            }
        }
        return ps;
    }

    public PaginationSupport searchCheckMachineAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(id.iaccessequipid as code,szaccessequipname as name) from Esbaccessequiptab where byisuse = 1 ");
        if(!StringUtils.isBlank(dto.getSearchName())){
            hsql.append(" szaccessequipname like '%"+dto.getSearchName()+"%' ");
        }
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null && !ps.getItems().isEmpty()){
            List list = ps.getItems();
            for (int i = 0;i<list.size();i++){
                Map map = (Map) list.get(i);
                map.put("codeTypeName",CodeType.CHECKMACHINE.getName());
                map.put("codeType",CodeType.CHECKMACHINE.getCode());
                //��ȡ��Ȩ��Ϣ
                AuthCode authCode = getAuthCode(CodeType.CHECKMACHINE.getCode(),map.get("code").toString());
                if(authCode != null){
                    map.put("id",authCode.getId());
                    map.put("authCode",authCode.getAuthCode());
                    map.put("status",1);
                    map.put("dtmakedate",authCode.getDtmakedate());
                    map.put("baiduStatus",authCode.getBaiduStatus());
                }else{
                    map.put("id",0);
                    map.put("status",0);
                    map.put("baiduStatus","00");
                }
            }
        }
        return ps;
    }

    public PaginationSupport searchStationAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(iticketstationid as code,szstationname as name) from Esbticketstationtab where byisuse = 1 ");
        if(!StringUtils.isBlank(dto.getSearchName())){
            hsql.append(" szstationname like '%"+dto.getSearchName()+"%' ");
        }
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null && !ps.getItems().isEmpty()){
            List list = ps.getItems();
            for (int i = 0;i<list.size();i++){
                Map map = (Map) list.get(i);
                map.put("codeTypeName",CodeType.STATION.getName());
                map.put("codeType",CodeType.STATION.getCode());
                //��ȡ��Ȩ��Ϣ
                AuthCode authCode = getAuthCode(CodeType.SALEMACHINE.getCode(),map.get("code").toString());
                if(authCode != null){
                    map.put("id",authCode.getId());
                    map.put("authCode",authCode.getAuthCode());
                    map.put("status",1);
                    map.put("dtmakedate",authCode.getDtmakedate());
                    map.put("baiduStatus",authCode.getBaiduStatus());
                }else{
                    map.put("id",0);
                    map.put("status",0);
                    map.put("baiduStatus","00");
                }
            }
        }
        return ps;
    }

    public PaginationSupport searchSaleMachineAuthCode(AuthCodeInputDto dto, int page, int pageSize, String url){
        StringBuffer hsql = new StringBuffer();
        hsql.append("select new map(iticketwinid as code,szticketwinname as name) from Esbticketwintab where byisuse = 1 ");
        if(!StringUtils.isBlank(dto.getSearchName())){
            hsql.append(" szticketwinname like '%"+dto.getSearchName()+"%' ");
        }
        PaginationSupport ps = this.findPage(hsql.toString(),pageSize,page,url);
        if(ps != null && !ps.getItems().isEmpty()){
            List list = ps.getItems();
            for (int i = 0;i<list.size();i++){
                Map map = (Map) list.get(i);
                map.put("codeTypeName",CodeType.SALEMACHINE.getName());
                map.put("codeType",CodeType.SALEMACHINE.getCode());
                //��ȡ��Ȩ��Ϣ
                AuthCode authCode = getAuthCode(CodeType.SALEMACHINE.getCode(),map.get("code").toString());
                if(authCode != null){
                    map.put("id",authCode.getId());
                    map.put("authCode",authCode.getAuthCode());
                    map.put("status",1);
                    map.put("dtmakedate",authCode.getDtmakedate());
                    map.put("baiduStatus",authCode.getBaiduStatus());
                }else{
                    map.put("id",0);
                    map.put("status",0);
                    map.put("baiduStatus","00");
                }
            }
        }
        return ps;
    }

    public AuthCode getAuthCode(String codeType, String orginId){
        String hsql = "from AuthCode where codeType = '"+codeType+"' ";
        if(!StringUtils.isBlank(orginId)){
            hsql += " and orginId = '"+orginId+"' ";
        }
        List list = this.find(hsql.toString());
        if(list != null && !list.isEmpty()){
            return (AuthCode) list.get(0);
        }
        return null;
    }

    public List searchProvider(String codeType){
        String hsql = "select new map(iscenicid as code,szsceniccode as name) from Esbscenicareatab where iscenicid not in (select orginId from AuthCode where codeType = '"+codeType+"') and byisuse = 1 ";
        return this.find(hsql);
    }

    public List searchProduct(String codeType){
        String hsql = "select new map(itickettypeid as code,sztickettypecode as name) from Edmtickettypetab where itickettypeid not in (select orginId from AuthCode where codeType = '"+codeType+"') and byisuse = 1";
        return this.find(hsql);
    }

    public List searchPrice(String codeType){
        String hsql = "select new map(icrowdkindpriceid as code,icrowdkindpricecode as name) from Edmcrowdkindpricetab where icrowdkindpriceid not in (select orginId from AuthCode where codeType = '"+codeType+"') and byisuse = 1";
        return this.find(hsql);
    }

    public List searchGarden(String codeType){
        String hsql = "select new map(id.igardengateid as code,szgardengatecode as name) from Esbgardengatetab where id.igardengateid not in (select orginId from AuthCode where codeType = '"+codeType+"') and byisuse = 1";
        return this.find(hsql);
    }

    public List searchCheckMachine(String codeType){
        String hsql = "select new map(id.iaccessequipid as code,szaccessequipcode as name) from Esbaccessequiptab where id.iaccessequipid not in (select orginId from AuthCode where codeType = '"+codeType+"') and byisuse = 1";
        return this.find(hsql);
    }

    public List searchStation(String codeType){
        String hsql = "select new map(iticketstationid as code,szstationcode as name) from Esbticketstationtab where iticketstationid not in (select orginId from AuthCode where codeType = '"+codeType+"') and byisuse = 1";
        return this.find(hsql);
    }

    public List searchSaleMachine(String codeType){
        String hsql = "select new map(iticketwinid as code,szticketwincode as name) from Esbticketwintab where iticketwinid not in (select orginId from AuthCode where codeType = '"+codeType+"') and byisuse = 1";
        return this.find(hsql);
    }

    public Esbaccessequiptab findCheckMachine(Long accid){
        List list = this.find("from Esbaccessequiptab where id.iaccessequipid = " + accid);
        if(list != null && !list.isEmpty()){
            return (Esbaccessequiptab) list.get(0);
        }
        return null;
    }

    public Esbgardengatetab findGarden(Long gardenId){
        List list = this.find("from Esbgardengatetab where id.igardengateid = " + gardenId);
        if(list != null && !list.isEmpty()){
            return (Esbgardengatetab) list.get(0);
        }
        return null;
    }

    public List<Esbaccessequiptab> findCheckMachines(Long gardenId){
        return this.find("from Esbaccessequiptab where id.igardengateid = "+gardenId);
    }
}
