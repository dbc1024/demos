package com.its.sims.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by csz on 2017/8/18.
 */
public class VerificationCodeUtil {

    private Integer width;  //验证码图片宽度
    private Integer height; //验证吗图片高度
    private Integer num;    //验证码数字字母的个数
    private String code;    //生成验证码一组字符串

    private static final Random ran = new Random();//随机数

    private static VerificationCodeUtil verificationCodeUtil;

    /**
     * 通过默认构造初始化参数
     */
    private VerificationCodeUtil(){
        width = 100;
        height = 50;
        code = "123456789adcdefghijklmnopqrstuvwxyz";
        num = 4;
    }


    public static VerificationCodeUtil getInstance(){
        if(verificationCodeUtil==null){
            verificationCodeUtil=new VerificationCodeUtil();
        }
        return verificationCodeUtil;
    }

    public void setVerificationCodeUtil(Integer width, Integer height, Integer num, String code) {
        this.width = width;
        this.height = height;
        this.num = num;
        this.code = code;
    }

    public void setVerificationCodeUtil(Integer width, Integer height, Integer num) {
        this.width = width;
        this.height = height;
        this.num = num;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static Random getRan() {
        return ran;
    }


    public String createVerificationCode(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append(code.charAt(ran.nextInt(code.length())));
        }
        return sb.toString();
    }

    public BufferedImage createVerificationCodeImg(String verificationCode){
        // 创建BufferedImage对象
        BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = img.createGraphics();// 创建画笔
        graphic.setColor(Color.WHITE);// 设置颜色
        graphic.fillRect(0, 0, width, height);//是用预定的颜色填充一个矩形，得到一个着色的矩形块。
        graphic.setColor(Color.WHITE);
        graphic.drawRect(0, 0, width - 1, height - 1);// 画正方形

        // 设置字体 风格 风格 高度
        Font font = new Font("宋体", Font.BOLD + Font.ITALIC,(int) (height * 0.8));
        graphic.setFont(font);
        for (int i = 0; i < num; i++) {
            // 随机设置字体RGB颜色
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255),ran.nextInt(255)));
            // 画出验证码
            graphic.drawString(String.valueOf(verificationCode.charAt(i)), i* (width / num) + 2, (int) (height * 0.8));
        }
        for (int i = 0; i < (width + height); i++) {
            // 随机设置字体RGB颜色
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255),ran.nextInt(255)));
            // 生成干扰点
            graphic.drawOval(ran.nextInt(width), ran.nextInt(height), 1, 1);
        }
        for(int i = 0; i <2; i++){
            // 随机设置字体RGB颜色
            graphic.setColor(new Color(ran.nextInt(255), ran.nextInt(255),ran.nextInt(255)));
            // 随机生成干扰线
            //graphic.drawLine(0, ran.nextInt(height), width,ran.nextInt(height));
        }
        return img;
    }

}
