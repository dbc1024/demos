package language;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SomeTest {

	@Test
	public void testEquals() {
		Long num1 = new Long(666666L);
		Long num2 = new Long(666666L);

		System.out.println(num1.equals(num2));
	}

	@Test
	public void testForeach() {
		
		String[] numArr = {"0","00","000","0000","00000"};
		
		for (String num : numArr) {
			num = "newNum0000000";
		}
		
		for (String num : numArr) {
			System.out.println(num);
		}
		
		
		String[] numArr2 = {"1","11","111","1111","11111"};
		for (int i = 0; i < numArr2.length; i++) {
			numArr2[i] = "newNum1111111";
		}
		
		
		
		for (String num : numArr2) {
			System.out.println(num);
		}
	}
	
	@Test
	public void testArr() {
		
		int[][] a;
		a = new int[3][];
		
		System.out.println(a.length);
		
	}
	
	@Test
	public void testArrList() {
		String url1 = "www.sso.com:8080";
		String url2 = "www.pw.com:8081";
		String url3 = "www.fx.com:8082";
		
		String urls = url1 + ";";
		urls = urls + url2 + ";";
		urls = urls + url3 + ";";
		
		
		String[] split = urls.split(";");
		System.out.println(split.length);
		System.out.println(split);
		
		
	}
	
	@Test
	public void testString() {
		String str = null;
		
		str = str + "cccc";
		
		System.out.println(str);
		
		
	}
	
	
	//���ۣ���������ѭ���Ӵ�list��size�����ᱨ��
	@Test
	public void testListAdd() {
		List<String> strs = new ArrayList<>();
		strs.add("1");
		
		for (int i = 0; i < strs.size(); i++) {
			
			//if(i==0){
				strs.add("2");
			//}
		}
		
		System.out.println(strs);
		
		
	}
	
	
	//���ۣ�foreeach�����в��ܸı�list�Ĵ�С
	@Test
	public void testListAdd1() {
		List<String> strs = new ArrayList<>();
		strs.add("1");
		
//		for (int i = 0; i < strs.size(); i++) {
//			
//			//if(i==0){
//				strs.add("2");
//			//}
//		}
		
		int i = 0;
		for (String string : strs) {
			i++;
			strs.add("2");
		}
		
		System.out.println(strs);
		
		
	}
	
	//���ۣ�forѭ���Ĺ����п��Ըı�list��size�����ᱨ���������׳��߼�����
	@Test
	public void testListRemove() {
		List<String> strs = new ArrayList<>();
		strs.add("0");
		strs.add("1");
		strs.add("2");
		strs.add("3");
		strs.add("4");
		strs.add("5");
		
		for (int i = 0; i < strs.size(); i++) {
			
			if(i%2==0){
				strs.remove(i);
			}
		}
		
		System.out.println(strs);
		
		
	}
	
	@Test
	public void testLiandeng() {
		
		int a;
		
		boolean flag = ((a=3) == 1);
		
		System.out.println(flag);
		

		int i=1;
		int j;
		j=(i++);
		System.out.println(i);
		System.out.println(j);
	
		
	}
	
	
	@Test
	public void testFuzhi() {
		
		int i=1;
		int j;
		j=i++;
		System.out.println(i);
		System.out.println(j);
	
		
	}
	
	
	@Test
	public void testGuiZe() {

		int i = 9;
		switch(i){
		default:System.out.println("default");
			
			case 0:System.out.println("zero");
			break;
			case 1:System.out.println("one");
			case 2:System.out.println("two");
			
			
		}
		
	}

}
