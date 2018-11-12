package com.ectrip.ec.custom.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.util.Tools;
import com.ectrip.ec.custom.dao.idao.IJourneyDAO;
import com.ectrip.ec.model.order.TOrder;
import com.ectrip.ticket.model.afcset.Esbgardengatetab;
import com.ectrip.ticket.model.permitenter.Esbjourneytab;
import com.ectrip.ticket.model.permitenter.Esbtripequiptab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

public class JourneyDAO extends GenericDao implements IJourneyDAO {

	/**
	 * ��ѯ��ʼ԰�Ż���ͷ
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryJourneryTop(){
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append("select new map(isceniaid as isceniaid,iscenicaname as iscenicaname,'true' as flag) from Esbtripequiptab where byisstend=0 and byisuse = 1 group by isceniaid,iscenicaname ");
		list = this.find(hsql.toString());
		
		return list;
	}
	/**
	 * ��ѯ�����г̵�
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryEsbtripequiptabListAll(){
		List list = new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Esbtripequiptab where byisuse = 1 ");
		List xinglist = this.find(hsql.toString());
		if(xinglist!=null && xinglist.size() >= 1 ){
			Esbtripequiptab pequ =null;
			for (int i = 0; i < xinglist.size(); i++) {
				pequ= (Esbtripequiptab)xinglist.get(i);
				if(pequ!=null){				
					Esbtripequiptab t1 = new Esbtripequiptab();
					Esbtripequiptab t2 = new Esbtripequiptab();
					
					t1.setIsceniaid(pequ.getIsceniaid());
					t1.setItripid(pequ.getItripid());
					t1.setIscenicaname(pequ.getIscenicaname());	
					t1.setSztripdate(pequ.getSztripdate());
				
					t2.setIsceniaid(pequ.getIscenibid());
					t2.setItripid(pequ.getItripid());
					t2.setIscenicaname(pequ.getIscenicbname());
					t2.setSztripdate(pequ.getSztripdate());
					
					if(0L==pequ.getByisstend()){
						t1.setFlag(true);
					}else{
						t1.setFlag(false);
					}
					t2.setFlag(false);
					
					
					
					boolean isa =false;
					boolean isb = false;
					if(list!=null && list.size()>=1){
						for (int j = 0; j < list.size(); j++) {
							Esbtripequiptab tr = (Esbtripequiptab)list.get(j);
							if(tr.getIsceniaid().equals(t1.getIsceniaid())){
								isa = true;
								break;
							}
						}
					}
					if(!isa){
						list.add(t1);
					}
					if(list!=null && list.size()>=1){
						for (int j = 0; j < list.size(); j++) {
							Esbtripequiptab tr = (Esbtripequiptab)list.get(j);
							if(tr.getIsceniaid().equals(t2.getIsceniaid())){
								isb = true;
								break;
							}
						}
					}
					
					
					if(!isb){
						list.add(t2);
					}
				}
			}
		}
		return list;
	}
	/**
	 * ������ʼ���ѯ������
	 * Describe:
	 * @auth:huangyuqi
	 * @param tripId ��ʼ��
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryListTripequiepById(Long tripId){
		List list =new ArrayList();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Esbtripequiptab where isceniaid="+tripId+" and byisuse = 1");
		List tripList = this.find(hsql.toString());
		if(tripList!=null && tripList.size()>=1){
			Esbtripequiptab pequ =null;
			for (int i = 0; i < tripList.size(); i++) {
				pequ= (Esbtripequiptab)tripList.get(i);
				if(pequ!=null){	
					Esbtripequiptab t1 = new Esbtripequiptab();
					
					t1.setIsceniaid(pequ.getIscenibid());
					t1.setItripid(pequ.getItripid());
					t1.setIscenicaname(pequ.getIscenicbname());	
					t1.setSztripdate(pequ.getSztripdate());
					
					
					Long isa =0L;
					Long isb =0L;
					Long xing = 0L;
					if(t1.getSzdata()!=null && !"".equals(t1.getSzdata())){
						isa = Long.parseLong(t1.getSzdata());
					}
					if(t1.getSzdatb()!=null && !"".equals(t1.getSzdatb())){
						isb = Long.parseLong(t1.getSzdatb());
					}
					if(t1.getSztripdate()!=null && !"".equals(t1.getSztripdate())){
						xing = Long.parseLong(t1.getSztripdate());
					}
					
					Long times = isa + xing;
					
					t1.setSztripdate(times.toString());
					t1.setFlag(false);
					
					list.add(t1);
				}
			}
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" from Esbtripequiptab where  iscenibid ="+tripId+" and byisuse = 1");
		List triptopList = this.find(sql.toString());
		if(triptopList!=null && triptopList.size()>=1){
			Esbtripequiptab pequ =null;
			for (int i = 0; i < triptopList.size(); i++) {
				pequ= (Esbtripequiptab)triptopList.get(i);
				if(pequ!=null){	
					Esbtripequiptab t1 = new Esbtripequiptab();
					
					t1.setIsceniaid(pequ.getIsceniaid());
					t1.setItripid(pequ.getItripid());
					t1.setIscenicaname(pequ.getIscenicaname());	
					t1.setSztripdate(pequ.getSztripdate());
					
					
					Long isa =0L;
					Long isb =0L;
					Long xing = 0L;
					if(t1.getSzdata()!=null && !"".equals(t1.getSzdata())){
						isa = Long.parseLong(t1.getSzdata());
					}
					if(t1.getSzdatb()!=null && !"".equals(t1.getSzdatb())){
						isb = Long.parseLong(t1.getSzdatb());
					}
					if(t1.getSztripdate()!=null && !"".equals(t1.getSztripdate())){
						xing = Long.parseLong(t1.getSztripdate());
					}
					
					Long times = isa + xing;
					
					t1.setSztripdate(times.toString());
					if(0L==pequ.getByisstend()){
						t1.setFlag(true);
					}else{
						t1.setFlag(false);
					}
					
					list.add(t1);
				}
			}
		}
		
		return list;
	}
	/**
	 * �����г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esbjouneyList
	 * @return
	 * return:int
	 * Date:2012-2-24
	 */
	public void addEsbjourney(List esbjouneyList){
		
		Long isby = this.getMaxPk("isby", "Esbjourneytab");
		Esbtripequiptab esbjour=null;
		Esbtripequiptab esbj =null;
		if(esbjouneyList!=null && esbjouneyList.size()>=1){
			for (int i =0; i < esbjouneyList.size(); i++) {
				Esbjourneytab jour = (Esbjourneytab)esbjouneyList.get(i);
				Long seq = this.getMaxPk("seq", "Esbjourneytab");
				jour.setSeq(seq+1);
				if(jour.getNumb()==null || "".equals(jour.getNumb()) || 0L==jour.getNumb()) {
					jour.setNumb(1L);
				}
				jour.setIsatime(jour.getIsatime()+" "+jour.getIsa()+":"+jour.getIsadate());
				jour.setIsbtime(jour.getIsbtime()+" "+jour.getIsb()+":"+jour.getIsbdate());
				jour.setIsby(isby+1);
				
				Long isatype = 0L;
				Long isbtype = 0L;
				esbjour = this.queryatobtype(jour.getIsceniaid(), jour.getIscenibid());
				
				if(esbjour != null){
					if(0L==esbjour.getByisstend()){
						isatype = 1L;
					}else{
						isatype = 2L;
					}
					if(1L==esbjour.getByisvenue()){
						isbtype = 2L;
					}else{
						isbtype = 3L;
					}
				}else{
					esbj = this.querybtoatype(jour.getIsceniaid(), jour.getIscenibid());
					if(esbj != null){
						if(1L==esbj.getByisvenue()){
							isatype = 2L;
						}else{
							isatype = 3L;
						}
						if(0L==esbj.getByisstend()){
							isbtype = 1L;
						}else{
							isbtype = 2L;
						}
					}
				}
				
				jour.setIsatype(isatype);//���ͱ�־��1����ڣ�2�����㣬3 ��ͷ��
				jour.setIsbtype(isbtype);
				
				
				this.save(jour);
			}
		}
	}
	
	public Esbtripequiptab queryatobtype (Long isa,Long isb){
		Esbtripequiptab jour = null;
		String hsql = " from Esbtripequiptab where isceniaid ="+isa+" and iscenibid="+isb;
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			jour = (Esbtripequiptab)list.get(0);
		}
		return jour;			
	}
	
	public Esbtripequiptab querybtoatype (Long isa,Long isb){
		Esbtripequiptab jour = null;
		String sql =  " from Esbtripequiptab where iscenibid ="+isa+" and isceniaid="+isb;
		List list2 = this.find(sql);
		if(list2!=null && list2.size()>=1){
			jour = (Esbtripequiptab)list2.get(0);
		}
		return jour;			
	}
	
	/**
	 * �޸��г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param esbjouneyList
	 * @return
	 * return:int
	 * Date:2012-2-24
	 */
	public void updateEsbjourney(List esbjouneyList){
		if(esbjouneyList!=null && esbjouneyList.size()>=1){
			
			String orid = ((Esbjourneytab)esbjouneyList.get(0)).getOrid();//����
			Long isby = ((Esbjourneytab)esbjouneyList.get(0)).getIsby();//����
			this.deleteEsbjourney(orid, isby);//ɾ��
			//Ϊ�˷�ֹ�޸�ʱ���ӻ���٣���ʵ����ɾ����������ʽ	
			Esbtripequiptab esbjour=null;
			Esbtripequiptab esbj =null;
			
			for (int i =0 ; i <esbjouneyList.size(); i++) {
				Esbjourneytab jour = (Esbjourneytab)esbjouneyList.get(i);
				Long seq = this.getMaxPk("seq", "Esbjourneytab");
				jour.setSeq(seq+1);
				if(jour.getNumb()==null || "".equals(jour.getNumb()) || 0L==jour.getNumb()) {
					jour.setNumb(1L);
				}
				jour.setIsatime(jour.getIsatime()+" "+jour.getIsa()+":"+jour.getIsadate());
				jour.setIsbtime(jour.getIsbtime()+" "+jour.getIsb()+":"+jour.getIsbdate());
				jour.setIsby(isby);
				
				Long isatype = 0L;
				Long isbtype = 0L;
				esbjour = this.queryatobtype(jour.getIsceniaid(), jour.getIscenibid());				
				if(esbjour != null){
					if(0L==esbjour.getByisstend()){
						isatype = 1L;
					}else{
						isatype = 2L;
					}
					if(1L==esbjour.getByisvenue()){
						isbtype = 2L;
					}else{
						isbtype = 3L;
					}
				}else{
					esbj = this.querybtoatype(jour.getIsceniaid(), jour.getIscenibid());
					if(esbj != null){
						if(1L==esbj.getByisvenue()){
							isatype = 2L;
						}else{
							isatype = 3L;
						}
						if(0L==esbj.getByisstend()){
							isbtype = 1L;
						}else{
							isbtype = 2L;
						}
					}
				}
				
				jour.setIsatype(isatype);//���ͱ�־��1����ڣ�2�����㣬3 ��ͷ��
				jour.setIsbtype(isbtype);
				
				this.save(jour);
			}
		}
	}
		
	/**
	 * ɾ���г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param isby
	 * return:void
	 * Date:2012-3-6
	 */
	public void deleteEsbjourney(String orid,Long isby){
		String hsql =" from Esbjourneytab where orid='"+orid+"' and isby="+isby;
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			for (int i = 0; i < list.size(); i++) {
				Esbjourneytab jour = (Esbjourneytab)list.get(i);
				if(jour!=null){
					this.delete(jour);
				}
			}
		}
	}
	/**
	 * ��ѯ�г���Ϣ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @param isby
	 * return:void
	 * Date:2012-3-6
	 */
	public List queryJourList(String orid,Long isby){
		List<Esbjourneytab> list = new ArrayList();
		String hsql =" from Esbjourneytab where orid='"+orid+"' and isby="+isby+" order  by seq ";
		list = this.find(hsql);
		if(list!=null && list.size()>=1){
			for(Esbjourneytab e:list){
				if(e.getIsceniaid()!=null && !"".equals(e.getIsceniaid()) && 0L!=e.getIsceniaid()){
					Long isa = e.getIsceniaid();
					if(e.getIsatype()==1L || e.getIsatype()==3L){//԰��
						String grasql = " from Esbgardengatetab where id.igardengateid ="+isa;						
						Esbgardengatetab grade =null;
						List gradelist = this.find(grasql);
						if(gradelist!=null && gradelist.size()>=1){
							grade = (Esbgardengatetab)gradelist.get(0);
						}
						if(grade!=null){
							e.setSzsceinaname(grade.getSzgardengatename());
						}
					}else if(e.getIsatype()==2L){//����
						Esbscenicareatab proa = (Esbscenicareatab)this.get(Esbscenicareatab.class, isa);
						if(proa!=null){
							e.setSzsceinaname( proa.getSzscenicname());
						}
					}
				}
				if(e.getIscenibid()!=null && !"".equals(e.getIscenibid()) && 0L!=e.getIscenibid()){
					
					Long isb = e.getIscenibid();		
					if(e.getIsbtype()==1L || e.getIsbtype()==3L){//԰��
						String grasql = " from Esbgardengatetab where id.igardengateid ="+isb;						
						Esbgardengatetab grade =null;
						List gradelist = this.find(grasql);
						if(gradelist!=null && gradelist.size()>=1){
							grade = (Esbgardengatetab)gradelist.get(0);
						}
						if(grade!=null){
							e.setSzsceinbname(grade.getSzgardengatename());
						}
					}else if(e.getIsbtype()==2L){//����
						
						Esbscenicareatab prob = (Esbscenicareatab)this.get(Esbscenicareatab.class, isb);
						if(prob!=null){
							e.setSzsceinbname( prob.getSzscenicname());
						}
					}
				}
				
				e.setIsa(e.getIsatime().substring(11,13));
				e.setIsadate(e.getIsatime().substring(14,16));
				e.setIsatime(e.getIsatime().substring(0,10));
				
				e.setIsb(e.getIsbtime().substring(11,13));					
				e.setIsbdate(e.getIsbtime().substring(14,16));	
				e.setIsbtime(e.getIsbtime().substring(0,10));
				
			}
		}
		return list;
	}

	
	/**
	 * ��ѯ�����г���ϸ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:List
	 * Date:2012-2-24
	 */
	public List queryJourneyList(String orid){
		List<Esbjourneytab> list = new ArrayList<Esbjourneytab>();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Esbjourneytab where orid='"+orid+"' order  by isby desc,seq ");
		list = this.find(hsql.toString());
		List maplist = new ArrayList();
		Map map = new HashMap();
		StringBuffer str = new StringBuffer();
		
		String stra="";
		String strb="";
		String strall ="";
		if(list!=null && list.size()>=1){
		
			for (int i = 0; i < list.size(); i++) {
				Esbjourneytab e = (Esbjourneytab)list.get(i);
				
				if(e.getIsceniaid()!=null && !"".equals(e.getIsceniaid()) && 0L!=e.getIsceniaid()){
					Long isa = e.getIsceniaid();
					if(e.getIsatype()==1L || e.getIsatype()==3L){//԰��
						String grasql = " from Esbgardengatetab where id.igardengateid ="+isa;						
						Esbgardengatetab grade =null;
						List gradelist = this.find(grasql);
						if(gradelist!=null && gradelist.size()>=1){
							grade = (Esbgardengatetab)gradelist.get(0);
						}
						if(grade!=null){
							stra= grade.getSzgardengatename()+"["+e.getIsatime()+"]";
						}
					}else if(e.getIsatype()==2L){//����
						Esbscenicareatab proa = (Esbscenicareatab)this.get(Esbscenicareatab.class, isa);
						if(proa!=null){
							stra= proa.getSzscenicname()+"["+e.getIsatime()+"]";
						}
					}
				}
				if(e.getIscenibid()!=null && !"".equals(e.getIscenibid()) && 0L!=e.getIscenibid()){
					
					Long isb = e.getIscenibid();		
					if(e.getIsbtype()==1L || e.getIsbtype()==3L){//԰��
						String grasql = " from Esbgardengatetab where id.igardengateid ="+isb;						
						Esbgardengatetab grade =null;
						List gradelist = this.find(grasql);
						if(gradelist!=null && gradelist.size()>=1){
							grade = (Esbgardengatetab)gradelist.get(0);
						}
						if(grade!=null){
							strb= grade.getSzgardengatename()+"["+e.getIsbtime()+"]";
						}
					}else if(e.getIsbtype()==2L){//����
						
						Esbscenicareatab prob = (Esbscenicareatab)this.get(Esbscenicareatab.class, isb);
						if(prob!=null){
							strb = prob.getSzscenicname()+"["+e.getIsbtime()+"]";
						}
					}
				}
				
				strall = stra+"����"+strb;
				
				if(map==null || map.size()<1){
					map.put(e.getIsby(), strall);
				}else{
					for (int j = 0; j < map.size(); j++) {
						
						if(map.get(e.getIsby()) !=null && !"".equals(map.get(e.getIsby()))){
							map.put(e.getIsby(), map.get(e.getIsby())+"����"+strall);
							break;
						}else if(j==map.size()-1){
							map.put(e.getIsby(), strall);
							break;
						}
					}
				}
				strall="";
			}
		}
		
		maplist.add(map);
		
		return maplist;
	}
	/**
	 * �ж��Ƿ��ǳ����
	 * Describe:
	 * @auth:huangyuqi
	 * @param isceniaId
	 * @return
	 * return:List
	 * Date:2012-2-25
	 */
	public List queryIsFristJourney(Long isceniaId){
		List list = new ArrayList();
		String hsql=" from Esbtripequiptab where isceniaid="+isceniaId+" and byisstend=0 ";
		list = this.find(hsql);
		return list;
	}
	
	/**
	 * ��������֮���������ʱ��
	 * Describe:
	 * @auth:huangyuqi
	 * @param issceniaid
	 * @param isscenibid
	 * @return
	 * return:Long
	 * Date:2012-2-27
	 */
	public Esbtripequiptab queryJourneryTimes(Long issceniaid,Long isscenibid){
		Esbtripequiptab esbtripequiptab = new Esbtripequiptab();
		StringBuffer hsql = new StringBuffer();
		hsql.append(" from Esbtripequiptab where isceniaid="+issceniaid+" and iscenibid="+isscenibid);
		List list = this.find(hsql.toString());
		if(list!=null && list.size()>=1){
			esbtripequiptab = (Esbtripequiptab)list.get(0);
		}
		return esbtripequiptab;
	}
	
	/**
	 * �õ�������Ч����
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:int
	 * Date:2012-3-2
	 */
	public int queryOrderDates(String orid){
		int num = 1;
		String hsql=" from TOrder t where orid='"+orid+"' and scenictype='01'";
		List list = this.find(hsql);
		if(list!=null && list.size()>=1 ){
			for (int i = 0; i < list.size(); i++) {
				TOrder torder = (TOrder)list.get(i);
				String start = torder.getDtstartdate();
				String end = torder.getDtenddate();
				int dates = Tools.getDayNumb(start, end);
				if(dates>num){
					num = dates;
				}
			}
		}
		return num;
	}
	/**
	 * �ж϶������Ƿ�����Ʊ
	 * Describe:
	 * @auth:huangyuqi
	 * @param orid
	 * @return
	 * return:boolean
	 * Date:2012-3-6
	 */
	public boolean queryIsTickets(String orid){
		boolean isok = false;
		String hsql=" from TOrder where orid='"+orid+"' ";
		List list = this.find(hsql);
		if(list!=null && list.size()>=1){
			for (int i = 0; i < list.size(); i++) {
				TOrder torder = (TOrder)list.get(i);
				if("01".equals(torder.getScenictype()) || "02".equals(torder.getScenictype()) || "03".equals(torder.getScenictype()) || "04".equals(torder.getScenictype()) || "05".equals(torder.getScenictype())){
					isok = true;
					break;
				}
			}			
		}
		return isok;
	}
}

