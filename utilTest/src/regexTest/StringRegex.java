package regexTest;

import org.junit.Test;

public class StringRegex {

	@Test
	public void test1() {
		String str = "Select a excellent person from the teacher to be the president, we will creat e a new world";
		String replacedStr = str.replaceAll("(?i)(select|update|insert|delete|from|create|drop|alter)", "SQL_KEYWORD");
		System.out.println(replacedStr);
		
	}

}
