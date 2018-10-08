package pinyinTest;

import org.junit.Test;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinTest {

	@Test
	public void getPinyin() {
		
		String[] pinyinArray =PinyinHelper.toHanyuPinyinStringArray('单');

		for(int i = 0; i < pinyinArray.length; ++i){
			System.out.println(pinyinArray[i]);
		}
		
		
		HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		
		String[] pinyinArray1 = null;

		try{
			pinyinArray1 = PinyinHelper.toHanyuPinyinStringArray('单', format);
		}catch(BadHanyuPinyinOutputFormatCombination e){
			e.printStackTrace();
		}

		for(int i = 0; i < pinyinArray1.length; ++i){
			System.out.println(pinyinArray1[i]);
		}
	}
	
	
	/*HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
	　　// UPPERCASE：大写  (ZHONG)
	　　// LOWERCASE：小写  (zhong)
	　　format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	　　// WITHOUT_TONE：无音标  (zhong)
	　　// WITH_TONE_NUMBER：1-4数字表示英标  (zhong4)
	　　// WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常）  (zhòng)
	　　format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
	　　// WITH_V：用v表示ü  (nv)
	　　// WITH_U_AND_COLON：用"u:"表示ü  (nu:)
	　　// WITH_U_UNICODE：直接用ü (nü)
	　　format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
	　　String[] pinyin = PinyinHelper.toHanyuPinyinStringArray('重', format);
	　　toHanyuPinyinStringArray如果传入的字符不是汉字不能转换成拼音，那么会直接返回null。
	　　虽然pinyin4j很好用，但是还是有局限的。以上代码只能获取单个汉字的拼音，但是不能获取一个包含多音字的词的拼音。例如“重庆”，无法判断到底是“chongqing”还是“zhongqing”，pinyin4j不能通过上下文来判断多音字的读音。
	　　所以，在获取一个包含多音字的词语的读音，可以返回一个列表，正确的读音只能是人工判断选择。*/
	@Test
	public void chinese2pinyin() {
		String str = "我有故事，你有趣吗？";
		
		HanyuPinyinOutputFormat format= new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		
		
		String convert = "";
        for (int j=0; j<str.length(); j++)
        {
            char word = str.charAt(j);
            String[] pinyinArray;
			try {
				pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,format);
				
				if (pinyinArray != null) {
	                convert += pinyinArray[0];
	            } else{
	                convert += word;
	            }
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
		
		System.out.println(convert);
		
	}

}
