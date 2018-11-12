package com.ectrip.sys.message.dao.idao;

import java.util.List;

import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.sys.model.syspar.Mbmessage;

public interface IMbMessageDAO extends IGenericDao {

    /**
     * ��ȡ�����б�
     * Describe:
     * @auth:huangyuqi
     * @param rzti��ʼʱ��
     * @param ldti����ʱ��
     * @param pageҳ��
     * @param pageSizeÿҳ��ʾ��
     * @param url����·��
     * @return
     * return:List
     * Date:2011-11-24
     */
    public  PaginationSupport queryMessageList(String rzti,String ldti,int page,int pageSize,String url);
    /**
     * ���Ӷ���
     * Describe:
     * @auth:huangyuqi
     * @param message����ʵ��
     * return:void
     * Date:2011-11-24
     */
    public void insertMessage(Mbmessage message);
    /**
     * ���Ӷ���
     * Describe:
     * @auth:huangyuqi
     * @param telno
     * @param note
     * return:void
     * Date:2011-11-24
     */
    public void insertMessage(String telno,String note);
    /**
     * �޸Ķ���
     * Describe:
     * @auth:huangyuqi
     * @param message
     * return:void
     * Date:2011-11-24
     */
    public void updateMessage(Mbmessage message);

    /***
     *
     * Describe:�µĶ��ŷ��ɷ�ʽ,��content�е������滻ģ���е�@�ַ�,�������飬��������ĩ���е�@���Ӧ
     * @auth:yangguang
     * @param telno  Ҫ���͵��ֻ�����
     * @param type �������� �ô˲�����ȡ������Ϣ��ģ��
     * @param content Ҫ�滻������ ���˲����滻ģ���е�@�ַ�
     * return:void
     * Date:2012-3-23
     */
    public void sendMessageNew(String telno,String type,String[] content);

    public void sendMessage(String telno, String ip, String type, String[] content);
    
	public List getMessagelist(String today);
	
	public int queryByTelType(String telno, String type);
}

