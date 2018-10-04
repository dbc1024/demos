package com.its.sims.controller;

import com.its.sims.model.AjaxResult;
import com.its.sims.model.Student;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by csz on 2017/8/22.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @ResponseBody()
    @RequestMapping("/upload")
    public AjaxResult upload(MultipartFile file, HttpServletRequest request){
        AjaxResult result = new AjaxResult();

        //获得原始文件名
        String filename = file.getOriginalFilename();

        //生成随机新文件名
        String newfilename = UUID.randomUUID() + filename;

        //获得存储路径
        String realPath = request.getServletContext().getRealPath("/img");

        //转为file路径
        File deskFile = new File(realPath, newfilename);

        File parentFile = deskFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        try {
            FileOutputStream output = new FileOutputStream(deskFile);
            IOUtils.copy(file.getInputStream(), output);
            output.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map imgUrlMap = new HashMap();
        imgUrlMap.put("imgUrl","/img/"+newfilename);

        result.setData(imgUrlMap);

        return result;
    }


    @RequestMapping("/download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response){

        String realPath = request.getServletContext().getRealPath("/img");
        File file = new File(realPath, fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition","attachment;fileName=" + fileName);// 设置文件名

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();

                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }



}
