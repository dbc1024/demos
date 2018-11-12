package com.ectrip.ec.comment.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.ec.comment.dao.idao.ICommentDAO;
import com.ectrip.ec.comment.service.iservice.ICommentService;
import com.ectrip.ec.model.user.Hscomment;
import com.ectrip.sys.model.syspar.Syslog;

public class CommentService implements ICommentService {
	private ICommentDAO commentDao;
	
	public ICommentDAO getCommentDao() {
		return commentDao;
	}
	public void setCommentDao(ICommentDAO commentDao) {
		this.commentDao = commentDao;
	}
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
	public PaginationSupport queryCommentList(String type,int page,int pageSize,String url){
		return commentDao.queryCommentList(type, page, pageSize, url);
	}
	/**
	 * �������۱�Ų�ѯ���۶���
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq
	 * @return
	 * return:Hscomment
	 * Date:2011-12-13
	 */
	public Hscomment getHsCommentBySeq(Long seq){
		return commentDao.getHsCommentBySeq(seq);
	}
	/**
	 * ��̨�������
	 * Describe:
	 * @auth:huangyuqi
	 * @param hscomment���۶���
	 * @param syslog��־
	 * return:void
	 * Date:2011-12-13
	 */
	public void saveHscomment(Hscomment hscomment,Syslog syslog){
		commentDao.saveHscomment(hscomment, syslog);
	}
	/**
	 * ɾ������
	 * Describe:
	 * @auth:huangyuqi
	 * @param seq���۱��
	 * @param syslog������־
	 * return:void
	 * Date:2011-12-13
	 */
	public void deleteHscomment(Long seq,Syslog syslog){
		commentDao.deleteHscomment(seq, syslog);
	}
	/**
	 * ǰ̨��������
	 * Describe:
	 * @auth:huangyuqi
	 * @param hscomment
	 * return:void
	 * Date:2011-12-13
	 */
	public void SaveHscommentByPage(Hscomment hscomment){
		commentDao.SaveHscommentByPage(hscomment);
	}
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
	public PaginationSupport queryHscommentByPage(Long oeid,String ptype,int page,int pageSize,String url){
		return commentDao.queryHscommentByPage(oeid, ptype, page, pageSize, url);
	}
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
	public List queryHscomment(Long oeid,String ptype) throws IllegalAccessException, InvocationTargetException{
		return commentDao.queryHscomment(oeid, ptype);
	}
	
	public boolean findComment(String usid,String orid,Long iscenicid,String ptype){
		return this.commentDao.findComment(usid, orid,iscenicid,ptype);
	}
	
	public List findComments(String usid,Long oeid,String ptype,String orid){
		return this.commentDao.findComments(usid, oeid, ptype, orid);
	}
	public PaginationSupport findCommentsByPage(String usid,Integer pageSize,Integer page
			,Long oeid,String ptype,String orid,String url){
		return this.commentDao.findCommentsByPage(usid,pageSize,page, oeid, ptype, orid,url);
	}
	public List getTypeAndTitleList(Long oeid) {
		return this.commentDao.getTypeAndTitleList(oeid);
	}
}

