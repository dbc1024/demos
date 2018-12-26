package regexTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class IdnumRegex {

	@Test
	public void regexIdnumber() {
		
		String idnum = "511521199202071919";
		
		//��ʽһ
		//18λ
		Pattern regex18 = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
		Matcher matcher18 = regex18.matcher(idnum); 
		System.out.println(matcher18.matches());
		//15λ
		Pattern regex15 = Pattern.compile("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$");
		Matcher matcher15 = regex15.matcher(idnum); 
		System.out.println(matcher15.matches());
		
		
		//��ʽ����18λ��15λһ��
		System.out.println(idnum.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$") || idnum.matches("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$"));
	}

}
