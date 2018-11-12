package com.ectrip.sys.syspar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Galsourceregiontab;
import com.ectrip.sys.syspar.dao.ISourceRegionDAO;

@Repository
public class SourceRegionDAO extends GenericDao implements ISourceRegionDAO {
	
	/**
	 *查询客源地列表
	 */
	public PaginationSupport listAllPage(Long prentid,String lever,String next,int page,int pagesize,String url){
	
		String hsql="";
		//表示第一层进入
		if(prentid==null || "".equals(prentid)){
			hsql=" from Galsourceregiontab sor where ilevel= "+Integer.parseInt(lever)+" order by iregionalid ";
		}else{			
			if("up".equals(next)){//上一级				
				Galsourceregiontab source = querySource(prentid);
				hsql=" from Galsourceregiontab sor where sor.ilevel="+(Integer.parseInt(lever))+" and sor.iparentid='"+source.getIparentid()+"' order by iregionalid";
				
			}else{//下一级
				hsql=" from Galsourceregiontab sor where sor.ilevel="+(Integer.parseInt(lever))+" and sor.iparentid='"+prentid+"' order by iregionalid";
			}
		}
		
		return super.findPage(hsql, pagesize, page, url);
	}
	/**
	 * 根据客源地编号查询出客源地信息
	 */
	public Galsourceregiontab querySource(Long sourceid){
		Galsourceregiontab sourceregion = new Galsourceregiontab();
		sourceregion =(Galsourceregiontab) super.get(Galsourceregiontab.class,sourceid);
		return sourceregion;
	}
	/**
	 * 更改层序号
	 */
	public void updateIleverseq(Long ilever,Long ileverseq,Long sourceid){
		//先判断此层序号是否已经被用，如果有，则更改些层以上的所有数据
		List isuse = this.getIleverseqList(ilever, ileverseq,sourceid);
		if(isuse.size()>=1){//表示已经存在
			
			String hsql=" from Galsourceregiontab where ilevel="+ilever+" and ilevelsequence>="+ileverseq;
			List list = super.find(hsql);
			if(list.size()>=1){
				for (int i = 0; i < list.size(); i++) {
					Galsourceregiontab source = (Galsourceregiontab)list.get(0);
					source.setIlevelsequence(source.getIlevelsequence()+1);
					super.update(source);
				}
			}
			
		}
		
	}
	
	public List getIleverseqList(Long ilever,Long ilerverseq,Long sourceid){
		String sql=" from Galsourceregiontab where ilevel="+ilever+" and ilevelsequence="+ilerverseq;
		List list = this.find(sql);
		if(list.size()>=1){
			Galsourceregiontab source = (Galsourceregiontab)list.get(0);
			if(sourceid==source.getIregionalid()){
				list.remove(0);
			}
		}
		
		return list;
	}
	
	/**
	 * 获敢最大的主键
	 * @return
	 */
	public Long getMaxObjectId(String tablename,String colums){
			Long seq=0L;
			String sql=" select max("+colums+") as seq from "+tablename;
			List list = super.find(sql); 
			if(list.size()==0){
				seq=0L;
			}
			if(list.size()>=1){			
				 seq = Long.parseLong(list.get(0) == null ? "0" :list.get(0).toString());
			}		
		
		return seq;
	}	
	/**
	 * 获敢最大的层序号
	 * @return
	 */
	public Long getMaxIleverSeq(String tablename,String colums,String ilever){
		Long seq=0L;
		String sql=" select max("+colums+") as seq from "+tablename+" where ilevel="+ilever;
		List list = super.find(sql); 
		if(list.size()==0){
			seq=0L;
		}
		if(list.size()>=1){
			 seq = Long.parseLong(list.get(0) == null ? "0" :list.get(0).toString());
		}		
		return seq;
	}
	
	public boolean retiveSource(Long soureid){
		boolean isuse = false;
		String hsql=" from Galsourceregiontab where iparentid="+soureid;
		List list = this.find(hsql);
		if(list.size()>0){
			isuse=true;
		}
		return isuse;
	}
	
	public List getAllSourceRegion(){
		List<Galsourceregiontab> list = new ArrayList<Galsourceregiontab>();
		String hsql=" select new map( iregionalid as iregionalid,szinnercode as szinnercode,szinnername as szinnername,szregionalcode as szregionalcode,szregionalname as szregionalname) from Galsourceregiontab where ilevel=2 and irootid in (select iregionalid from Galsourceregiontab where szregionalname='中国')";
		list = this.find(hsql);
		return list;
	}
	
	public List SourceRegionJson(Long regionid){
		List<Galsourceregiontab> list = new ArrayList<Galsourceregiontab>();
		String hsql=" select new map( g.iregionalid as szregionalid,g.szregionalname as szregionalname,case bisleaf when '0' then 'true' when '1' then 'false' end  as hasnext) from Galsourceregiontab g where g.iparentid="+regionid;
		list = this.find(hsql);
		return list;
	}
	
	
	public List getAllAreas() {
		List<Galsourceregiontab> list = new ArrayList<Galsourceregiontab>();
		String hsql=" select new map( area.iregionalid as id, area.iparentid as parentId, area.ilevel as ilevel, area.szregionalname as areaName) from Galsourceregiontab area where area.ilevel<4";
		list = this.find(hsql);
		return list;
	}
	public List getFourLeavel() {
		List<Galsourceregiontab> list = new ArrayList<Galsourceregiontab>();
		String hsql=" select new map( area.iregionalid as id, area.iparentid as parentId) from Galsourceregiontab area where area.ilevel=4";
		list = this.find(hsql);
		return list;
	}
}
