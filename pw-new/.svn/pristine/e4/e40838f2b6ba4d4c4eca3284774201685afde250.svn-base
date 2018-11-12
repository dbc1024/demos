package com.ectrip.sys.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.message.dao.idao.IMbMessageDAO;
import com.ectrip.sys.message.service.iservice.IMbMessageService;
import com.ectrip.sys.model.syspar.Mbmessage;

@Service
public class MbMessageService implements IMbMessageService {

	@Autowired
    private IMbMessageDAO mbmessageDao;

    
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
    public  PaginationSupport queryMessageList(String rzti,String ldti,int page,int pageSize,String url){
        return mbmessageDao.queryMessageList(rzti, ldti, page, pageSize, url);
    }
    /**
     * 增加短信
     * Describe:
     * @auth:huangyuqi
     * @param message短信实体
     * return:void
     * Date:2011-11-24
     */
    public void insertMessage(Mbmessage message){
        mbmessageDao.insertMessage(message);
    }
    /**
     * 增加短信
     * Describe:
     * @auth:huangyuqi
     * @param telno
     * @param note
     * return:void
     * Date:2011-11-24
     */
    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void insertMessage(String telno,String note){
        if(telno!=null && !"".equals(telno)){
            mbmessageDao.insertMessage(telno, note);
        }
    }
    /**
     * 修改短信
     * Describe:
     * @auth:huangyuqi
     * @param message
     * return:void
     * Date:2011-11-24
     */
    @Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void updateMessage(Mbmessage message){
        mbmessageDao.updateMessage(message);
    }
    @Transactional(propagation=Propagation.SUPPORTS,readOnly=false, isolation=Isolation.READ_COMMITTED, rollbackFor=Exception.class)
    public void sendMessageNew(String telno,String type,String[] content){
        mbmessageDao.sendMessageNew(telno, type, content);
    }

    public void sendMessage(String telno, String ip, String type, String[] content) {
        this.mbmessageDao.sendMessage(telno, ip, type, content);
    }
    
    public int queryByTelType(String telno, String type){
    	return this.mbmessageDao.queryByTelType(telno, type);
    }
	@Override
	public Mbmessage getMessageDetail(Long seq) {
		// TODO Auto-generated method stub
		return (Mbmessage)this.mbmessageDao.get(Mbmessage.class, seq);
	}
}

