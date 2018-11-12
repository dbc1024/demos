package com.ectrip.ec.comment.service.iservice;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.model.user.Hscomment;
import com.ectrip.sys.model.syspar.Syslog;

/**
 * ����service�ӿ�
 * @author huangyuqi
 */
public interface ICommentService {
	/**
	 * ��ȡ�����б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param type
	 * @param page
	 * @param pageSize
	 * @param url
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-13
	 */
	public PaginationSupport queryCommentList(String type,int page,int pageSize,String url);
	/**
	 * �������۱�Ų�ѯ���۶���
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @return
	 * return:Hscomment
	 * Date:2011-12-13
	 */
	public Hscomment getHsCommentBySeq(Long seq);
	/**
	 * ��̨�������
	 * Describe:
	 * @auth:huangyuqi
	 * @param hscomment���۶���
	 * @param syslog��־
	 * return:void
	 * Date:2011-12-13
	 */
	public void saveHscomment(Hscomment hscomment,Syslog syslog);
	/**
	 * ɾ������
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq���۱��
	 * @param syslog������־
	 * return:void
	 * Date:2011-12-13
	 */
	public void deleteHscomment(Long seq,Syslog syslog);
	/**
	 * ǰ̨��������
	 * Describe:
	 * @auth:huangyuqi
	 * @param hscomment���۶���
	 * return:void
	 * Date:2011-12-13
	 */
	public void SaveHscommentByPage(Hscomment hscomment);
	/**
	 * ǰ̨��ʾ�б�
	 * Describe:
	 * @auth:huangyuqi
	 * @param oeid���۶�����
	 * @param ptype��������
	 * @param pageҳ��
	 * @param pageSizeҳ����
	 * @param url���ʵ�ַ
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-13
	 */
	public PaginationSupport queryHscommentByPage(Long oeid,String ptype,int page,int pageSize,String url);
	/**
	 * ǰ̨��ʾ�б�(�޷�ҳ)
	 * Describe:
	 * @auth:huangyuqi
	 * @param oeid //���۶�����
	 * @param ptype //�������ͣ�01�����̣�02��Ʒ��03���£�04������
	 * @return
	 * return:PaginationSupport
	 * Date:2011-12-13
	 */
	public List queryHscomment(Long oeid,String ptype) throws IllegalAccessException, InvocationTargetException;
	
	public boolean findComment(String usid,String orid,Long iscenicid,String ptype);
	
	public List findComments(String usid,Long oeid,String ptype,String orid);
	
	/**
	 * ��ҳ��ѯ����
	 * @param usid���۶�����
	 * @param pageSizeҳ����
	 * @param pageҳ��
	 * @param oeid���۶�����
	 * @param ptype��������
	 * @param orid
	 * @param url���ʵ�ַ
	 * @return
	 */
	public PaginationSupport findCommentsByPage(String usid,Integer pageSize,Integer page,Long oeid,String ptype,String orid,String url);
	
	/**
	 * �������۶���id��ѯ����ͱ���
	 * @param oeid ���۶�����
	 * @return
	 */
	public List getTypeAndTitleList(Long oeid);
}

