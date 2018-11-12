package com.ectrip.ec.userxfjf.service.iservice;

import java.util.List;

import com.ectrip.ec.model.user.Userjfxfgz;



public interface IUserjfxfgzSerivce {
	/**
	 * ��ȡ���л������ѹ���
	 * @return
	 */
	public List getAlljfxfgz();
	
	/**
	 * ��ȡ�������ֵ
	 * @return
	 */
	public List getMaxSeq();
	
	/**
	 * ���ӻ������ѹ���
	 * @param jfxfgz
	 */
	public void addjfxfgz(Userjfxfgz jfxfgz);
	
	/**
	 * ���ݱ�Ż�ȡ�������ѹ���
	 * @param seq
	 * @return
	 */
	public Userjfxfgz getBySeqJfxfgz(int seq);
	
	/**
	 * ɾ���������ѹ���
	 * @param seq
	 */
	public void deljfxfgz(int seq);
	
	/**
	 * ���»������ѹ���
	 * @param jfxfgz
	 */
	public void updatejfxfgz(Userjfxfgz jfxfgz);
	
	/**
	 * ��ѯ�����Ƿ����ظ�
	 * @param numb
	 * @return
	 */
	public List isRepNumb(int stnumb,int ednumb);
	public List isRepNumb(int stnumb,int ednumb,int seq);
}
