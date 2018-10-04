package com.its.sims.controller;

import com.its.sims.annotation.SystemLogger;
import com.its.sims.model.AjaxResult;
import com.its.sims.model.Student;
import com.its.sims.model.config.BaseConfig;
import com.its.sims.services.IStudentService;
import com.its.sims.utils.VerificationCodeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by csz on 2017/7/26.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    IStudentService studentService;



    @RequestMapping("/loginPage")
    public String list() {
        return "common/login";
    }


    /**
     * 登录验证
     * @param verCode
     * @param username
     * @param password
     * @param session
     * @param request
     * @param response
     * @return
     */
    @ResponseBody()
    @RequestMapping("/checkLogin")
    public AjaxResult checkLogin(String verCode, String username, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        AjaxResult result = new AjaxResult();
        
        String sIMS_NAME = BaseConfig.SIMS_NAME;
        System.out.println(sIMS_NAME);

        if(StringUtils.isBlank(verCode)){
            result.setSuccess(false);
            result.setDescription("请输入验证码！");

            return result;
        }else {
            String sessionVerCode = (String) session.getAttribute("verCode");
            if(sessionVerCode == null){
                result.setSuccess(false);
                result.setDescription("验证码过期！");
                return result;
            }
            if(!verCode.equals(sessionVerCode)){
                result.setSuccess(false);
                result.setDescription("验证码错误！");
                return result;
            }


        }

        Student stu = studentService.queryByPhone(username);
        if(stu==null){
            result.setDescription("不存在此用户！");

        }else{
            if(!password.equals(stu.getPassword())){
                result.setSuccess(false);
                result.setDescription("用户名密码不匹配");
            }else{

            	
                result.setDescription("登陆成功！");

                //登录成功后，将用户信息存入cookie中
//                Cookie cookie = new Cookie("cookieUser", stu.toString());
//                cookie.setMaxAge(1*60*60);
//                response.addCookie(cookie);
                session.setAttribute("sessionUser", stu);
                session.setMaxInactiveInterval(1*60*60);

            }
        }

        return result;
    }


    /**
     * 生成验证码图片
     * @param response
     * @param session
     */

    @RequestMapping("/getVerCode")
    public void getVerCode(HttpServletResponse response, HttpSession session){
        OutputStream out = null;

        //设置响应类型
        response.setContentType("image/jpg");

        //获取验证码工具类实例
        VerificationCodeUtil vCodeUtil = VerificationCodeUtil.getInstance();

        //获取生成验证码图片的字符串
        String code = vCodeUtil.createVerificationCode();

        //生成验证码图片
        BufferedImage img = vCodeUtil.createVerificationCodeImg(code);

        //将字符串放入session中，以备后续比较操作
        session.setAttribute("verCode", code);
        session.setMaxInactiveInterval(1*60);

        try {
            out = response.getOutputStream();
            //通过ImageIo写出图片
            ImageIO.write(img, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @RequestMapping("/logout")
    public void logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.removeAttribute("sessionUser");

        response.sendRedirect("/login/loginPage");

    }


}
