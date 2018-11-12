package com.ectrip.base.util;

import java.math.BigInteger;

/**
 * 进制转换
 * @author hezhihong
 */
public class DecimalUtil {



/**
 * 十进制转换中把字符转换为数
 * Describe:
 * @author:hezhihong
 * @param ch 
 * @return
 * return:int
 * Date:2014-5-5
 */
public static int changeDec(char ch)
    {
        int num = 0;
        if(ch >= 'A' && ch <= 'Z')
            num = ch - 'A' + 10;
        else if(ch >= 'a' && ch <= 'z')
            num = ch - 'a' + 36;
        else
            num = ch - '0';
        return num;
    }

	/**
	 * 任意进制转换为10进制
	 * Describe:
	 * @author:hezhihong
	 * @param input 转换的字符
	 * @param base 进制
	 * @return
	 * return:BigInteger
	 * Date:2014-5-5
	 */
    public static BigInteger toDecimal(String input, int base)
    {
        BigInteger Bigtemp = BigInteger.ZERO, temp = BigInteger.ONE;
        int len = input.length();
        for(int i = len - 1; i >= 0; i--)
        {
            if(i != len - 1)
                temp = temp.multiply(BigInteger.valueOf(base));
            int num = changeDec(input.charAt(i));
            Bigtemp = Bigtemp.add(temp.multiply(BigInteger.valueOf(num)));
        }
        //System.out.println(Bigtemp);
        //return Bigtemp;
        return Bigtemp;
    }

   /**
    * 数字转换为字符
    * Describe:
    * @author:hezhihong
    * @param temp
    * @return
    * return:char
    * Date:2014-5-5
    */
   public static char changToNum(BigInteger temp)
    {
        int n = temp.intValue();

        if(n >= 10 && n <= 35)
            return (char) (n - 10 + 'A');

        else if(n >= 36 && n <= 61)
            return (char)(n - 36 + 'a');

        else
            return (char)(n + '0');
    }

   	/**
   	 * 十进制转换为任意进制
   	 * Describe:
   	 * @author:hezhihong
   	 * @param Bigtemp 十进制数
   	 * @param base 要转换的进制
   	 * @return
   	 * return:String
   	 * Date:2014-5-5
   	 */
    public static String toAnyConversion(BigInteger Bigtemp, BigInteger base)
    {
        String ans = "";
        while(Bigtemp.compareTo(BigInteger.ZERO) != 0)
        {
            BigInteger temp = Bigtemp.mod(base);
            Bigtemp = Bigtemp.divide(base);
            char ch = changToNum(temp);
            ans = ch + ans;
        }
        //return ans;
        return ans;
    }
    /**
     * 字符转换成数字
     * Describe:
     * @author:hezhihong
     * @param str
     * @return
     * return:int
     * Date:2014-5-5
     */
    public static int setzm(String str){
		int base=0;
		if(str.equals("A")){
			base=25;
		}
		if(str.equals("B")){
			base=26;
		}
		if(str.equals("C")){
			base=27;
		}
		if(str.equals("D")){
			base=28;
		}
		if(str.equals("E")){
			base=29;
		}
		if(str.equals("F")){
			base=30;
		}
		if(str.equals("G")){
			base=31;
		}
		if(str.equals("H")){
			base=32;
		}
		if(str.equals("I")){
			base=33;
		}
		if(str.equals("J")){
			base=34;
		}
		if(str.equals("K")){
			base=35;
		}
		if(str.equals("V")){
			base=10;
		}
		if(str.equals("L")){
			base=11;
		}
		if(str.equals("Z")){
			base=12;
		}
		if(str.equals("X")){
			base=13;
		}
		if(str.equals("N")){
			base=14;
		}
		return base;
	}
    /**
     * 数字转换成字符
     * Describe:
     * @author:hezhihong
     * @param base
     * @return
     * return:String
     * Date:2014-5-5
     */
    public static String getzm(int base){
		String str=null;
		switch (base) {
		case 25:
			str="A";
			break;
		case 26:
			str="B";
			break;
		case 27:
			str="C";
			break;
		case 28:
			str="D";
			break;
		case 29:
			str="E";
			break;
		case 30:
			str="F";
			break;
		case 31:
			str="G";
			break;
		case 32:
			str="H";
			break;
		case 33:
			str="I";
			break;
		case 34:
			str="J";
			break;
		case 35:
			str="K";
			break;
		case 10:
			str="V";
			break;
		case 11:
			str="L";
			break;
		case 12:
			str="Z";
			break;
		case 13:
			str="X";
			break;
		case 14:
			str="N";
			break;
		default:
			str="";
			break;
		}
		return str;
	}
    /**
     * 递归算法
     * Describe:
     * @author:hezhihong
     * @param array
     * @param i
     * @return
     * return:int
     * Date:2014-5-5
     */
    public static int getSF(char[] array, int i){
		char a = array[i];
		if(i == 0)
			return array[i];
		i--;
		return a ^ getSF(array,i);
	}

	/**
	 * 异或算法
	 * Describe:
	 * @author:hezhihong
	 * @param str 要异或的信息
	 * @param twobarcode 密钥
	 * @return
	 * return:String
	 * Date:2014-5-5
	 */
	public static String getmdecimal(String str){
			String key="PETWOBAR";//密钥
			int b=0;
				char[] array=str.toCharArray();//获取字符数组
			 	char [] keyarray=key.toCharArray();
			 	b=getSF(array,str.length()-1)^getSF(keyarray,key.length()-1);
		 return b+"";
	}
}
