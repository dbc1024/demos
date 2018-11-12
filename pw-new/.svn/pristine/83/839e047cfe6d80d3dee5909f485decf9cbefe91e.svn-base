package com.ectrip.ticket.cyt.service.iservice;

public interface ICytService {

    /**
     * ����У��
     * @param xml
     * @param securityType
     * @return
     */
    public String checkPmsData(String xml, String securityType);

    /**
     * �����糡����
     * @param xml
     * @param securityType
     * @return
     */
	public String createComplexOrder(String xml, String securityType);
	/**
	 * �������� Describe:
	 * 
	 * @author: huhaopeng
	 * @param xml
	 *            String
	 * @param securityType
	 *            String
	 * @return String return:String Date:2014-2-19
	 * @throws javax.xml.bind.JAXBException
	 */
	public String createOrder(String xml, String securityType);

	/**
	 * ��ȡ������Ϣ��qunar�� Describe:
	 *
	 * @author: huhaopeng
	 * @param xml
	 *            String
	 * @param securityType
	 *            String
	 * @return String return:String Date:2014-2-24
	 * @throws javax.xml.bind.JAXBException
	 */
	public String queryOrder(String xml, String securityType);

	/**
	 * ֧������ Describe:
	 *
	 * @author: huhaopeng
	 * @param xml
	 *            String
	 * @param securityType
	 *            String
	 * @return String return:String Date:2014-2-19
	 * @throws javax.xml.bind.JAXBException
	 */
	public String payOrder(String xml, String securityType);

	/**
	 * �û������˿qunar�� Describe:
	 *
	 * @author: huhaopeng
	 * @param xml
	 *            String
	 * @param securityType
	 *            String
	 * @return String return:String Date:2014-2-19
	 * @throws javax.xml.bind.JAXBException
	 */
	public String applyOrderRefundByUser(String xml, String securityType);

	/**
	 * ��֤�����������󷽷�������response��Ϣ Describe:
	 * 
	 * @author:huhaopeng
	 * @param code
	 *            String qunar������
	 * @param desc
	 *            String ��������
	 * @return return:String Date:2014-3-25
	 */
	public String getErrorResponseJson(String code, String desc,
                                       String securityType);
	
	public String pushOrder(String xml, String securityType);
}

