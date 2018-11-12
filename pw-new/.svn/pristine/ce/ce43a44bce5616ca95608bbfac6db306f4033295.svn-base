package com.ectrip.sys.message.service.iservice;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Mbmessage;

public interface IMbMessageService {

    /**
     * 获取短信列表
     * Describe:
     * @auth:huangyuqi
     * @param rzti开始时间
     * @param ldti结束时间
     * @param page页码
     * @param pageSize每页显示数
     * @param url访问路径
     * @return
     * return:List
     * Date:2011-11-24
     */
    public  PaginationSupport queryMessageList(String rzti,String ldti,int page,int pageSize,String url);
    /**
     * 增加短信
     * Describe:
     * @auth:huangyuqi
     * @param message短信实体
     * return:void
     * Date:2011-11-24
     */
    public void insertMessage(Mbmessage message);
    /**
     * 增加短信
     * Describe:
     * @auth:huangyuqi
     * @param telno
     * @param note
     * return:void
     * Date:2011-11-24
     */
    public void insertMessage(String telno,String note);
    /**
     * 修改短信
     * Describe:
     * @auth:huangyuqi
     * @param message
     * return:void
     * Date:2011-11-24
     */
    public void updateMessage(Mbmessage message);

    public void sendMessageNew(String telno,String type,String[] content);

    public void sendMessage(String telno, String ip, String type, String[] content);
    
    //根据手机号、类型获取发送短信次数
    public int queryByTelType(String telno, String type);
    
    public Mbmessage getMessageDetail(Long seq);
    
}

