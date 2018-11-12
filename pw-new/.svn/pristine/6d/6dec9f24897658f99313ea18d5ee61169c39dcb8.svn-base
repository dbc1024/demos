package com.ectrip.ec.model.ticket;

import java.util.Comparator;
import java.util.List;

public class TicketComparator implements Comparator<Object>{
	public int compare(Object o1, Object o2) {
		OrderPojo t1=(OrderPojo) o1;
		OrderPojo t2=(OrderPojo) o2;
		return (t1.getParentticketid()+t1.getTourdate()+t1.getProductcontrolid()+t1.getTicketid().toString()).compareTo(t2.getParentticketid()+t2.getTourdate()+t2.getProductcontrolid()+t2.getTicketid().toString());
	}
	
	
	public boolean SonticketEquals(List<OrderPojo> list1,List<OrderPojo>  list2){
		if(list1==null||list2==null){
			return false;
		}
		if(list1.size()<1||list2.size()<1){
			return false;
		}
		boolean result=true;
		OrderPojo t=null;
		for(int i=0;i<list1.size();i++){
			t=list1.get(i);
			if(!t.myEquals(list2.get(i))){
				return false;
			}
		}
		return result;
	}
	
	public boolean SonticketDaysEquals(List<OrderPojo> list1,List<OrderPojo>  list2){
		if(list1==null||list2==null){
			return false;
		}
		if(list1.size()<1||list2.size()<1){
			return false;
		}
		boolean result=true;
		OrderPojo t=null;
		for(int i=0;i<list1.size();i++){
			t=list1.get(i);
			if(!t.myEqualsSameDays(list2.get(i))){
				return false;
			}
		}
		return result;
	}
}

