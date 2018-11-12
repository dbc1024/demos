package com.ectrip.ticket.cyt.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.util.WebContant;
import com.ectrip.ticket.cyt.common.to.UpLoadReturn;

/**
 * 文件上传工具类
 *
 */
public class FileUploadUtils {

    private static Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);
//    public String upload(byte[] bytes) throws IOException {
//
//        //获取文件map
//        //将请求的数据转成
//        File file = new File("");
//        CloseableHttpClient httpClient = HttpClient.createDefault();
//        UpLoadReturn upLoadReturn = null;
//        String desc = null;
//        try {
//            HttpPost httpPost = new HttpPost(ConfigConstant.IMG_URL);
//            httpPost.addHeader("Content-Type", "jpg");
//            httpPost.addHeader("Content-Type", "jpeg");
//            httpPost.addHeader("Content-Type", "png");
//            httpPost.addHeader("Content-Type", "image/jpeg");
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setMode(HttpMultipartMode.STRICT);
//
//
//                ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bytes);
//                httpPost.setEntity(byteArrayEntity);
//
//            CloseableHttpResponse response = httpClient.execute(httpPost);
//            try {
//                HttpEntity resEntity = response.getEntity();
//                if(resEntity != null){
//                    upLoadReturn=(UpLoadReturn) JSON.parseObject(EntityUtils.toString(resEntity),UpLoadReturn.class);
//                }
//                EntityUtils.consume(resEntity);
//            } catch (IOException ioe){
//                ExceptionLog.print("文件上传请求异常", ioe);
//            } catch (Exception e) {
//                ExceptionLog.print("文件上传请求异常", e);
//            } finally {
//                response.close();
//            }
//        } catch(Exception ex){
//            desc = ex.getMessage();
//            ExceptionLog.print("error", ex);
//        } finally {
//            httpClient.close();
//        }
//        return null != upLoadReturn ? ConfigConstant.IMG_URL+ upLoadReturn.getInfo().getMd5():("404_"+desc);
//    }

    public static String post(File file) throws IOException {
        PostMethod method = new PostMethod(WebContant.GetKeyValue("IMGHOST"));
        HttpClient client = new HttpClient();
        String fileName = file.getName();
        method.setRequestHeader("Content-Type", fileName.substring(fileName.lastIndexOf(".") + 1));
        InputStream inputStream = new FileInputStream(file);
        method.setRequestEntity(new InputStreamRequestEntity(inputStream));
        int HttpCode = client.executeMethod(method);
        if (HttpCode != HttpStatus.SC_OK)
            logger.error("Invalid HttpStatus: " + HttpCode);
        String jsonStr = method.getResponseBodyAsString();
        UpLoadReturn upLoadReturn = JSONObject.parseObject(jsonStr, UpLoadReturn.class);
        try{
            inputStream.close();
            file.delete();
        }catch (Exception e){
            logger.warn("上传的临时文件删除失败！");
        }
        if(upLoadReturn.isRet()){
            logger.info(jsonStr);
            return upLoadReturn.getInfo().getMd5();
        }else{
            logger.error("上传失败 : " + jsonStr);
            return "";
        }
    }
}