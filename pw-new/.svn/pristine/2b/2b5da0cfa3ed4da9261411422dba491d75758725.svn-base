package com.ectrip.ticket.util;

public class ConverToCNMoneyUtils {
    public static final char[] CNUNIT=new char[]{'圆','拾','百','仟','万',
            '拾','百','仟','亿','拾','百','仟','兆','拾','百','仟'};

    private static final char[] CNNUM={'零','壹','贰','叁','肆','伍','陆',
            '柒','捌','玖'};
    public static final char[] CNFLOAT=new char[]{'角','分','厘'};

    public static String getConverToCNMoney(String money) {
        String integerPart, floatPart = null;// 整数部分与小数部分
        if (money.contains(".")) {
            // 如果包含小数点
            int dotIndex = money.indexOf(".");
            integerPart = money.substring(0, dotIndex);
            floatPart = money.substring(dotIndex + 1);
        } else {
            integerPart = money;
        }
        char[] intcs = integerPart.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intcs.length; i++) {
            sb.append(CNNUM[Integer.parseInt(String.valueOf(intcs[i]))]);
            if (i < 16)
                sb.append(CNUNIT[intcs.length - 1 - i]);
        }
        if (floatPart != null) {
            intcs = floatPart.toCharArray();
            for (int i = 0; i < intcs.length; i++) {
                sb.append(CNNUM[Integer.parseInt(String.valueOf(intcs[i]))]);
                if (i < 3)
                    sb.append(CNFLOAT[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String jd="1#2014-04-04";
        String[] jds=jd.split("#");
        System.out.println("jds[0]="+jds[0]);
        System.out.println("jds[1]="+jds[1]);

    }

}
