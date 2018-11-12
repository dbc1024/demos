package com.ectrip.ticket.cyt.common.util;

import com.ectrip.ticket.cyt.common.XmlHeaderParam;
import com.ectrip.ticket.cyt.model.request.RequestHeader;
import com.ectrip.ticket.cyt.model.response.ResponseHeader;

public class DoXmlParamUtil {

	/**
	 * 获取XML格式头部信息参数
	 * Describe:
	 * @author:huhaopeng
	 * @param paramName String OTA区分标识 ( qunar, ectrip )
	 * @return
	 * return:XmlHeaderParam
	 * Date:2014-4-24
	 */
	public static  XmlHeaderParam getXmlHeaderParam(String paramName){
		XmlHeaderParam headerParam = null;

		if("qunar".equals(paramName)){
			headerParam = new XmlHeaderParam(
					ConstUtils.Qunar.Header.NAMESPACE_REQUEST,
					ConstUtils.Qunar.Header.NAMESPACE_RESPONSE,
					ConstUtils.Qunar.Header.ATTRIBUTE_XSI,
					ConstUtils.Qunar.Header.ATTRIBUTE_XMLNS);
		}else if("ectrip".equals(paramName)){
			headerParam = new XmlHeaderParam(
					"http://tour.ectrip.com/2014/QMResponseSchema",
					"http://tour.ectrip.com/2014/QMResponseSchema",
					"http://tour.ectrip.com/2014/QMResponseSchema QMRequestDataSchema-1.1.0.xsd",
					"http://www.w3.org/2001/XMLSchema-instance");
		}

		return  headerParam;
	}

	/**
	 * 获取请求 OTA header
	 * Describe:
	 * @author:huhaopeng
	 * @param createUser String 创建人
	 * @param supplierIdentity String 供应商CODE
	 * @return
	 * return:Object
	 * Date:2014-4-24
	 */
	public static RequestHeader getOTARequestHeader(String createUser, String supplierIdentity,String version){
		RequestHeader header = new RequestHeader();
		header.setApplication("tour.ectrip.com");
		header.setProcessor("DataExchangeProcessor");
		header.setVersion(version);
		header.setCreateUser(createUser);
		header.setSupplierIdentity(supplierIdentity);
		return header;
	}

	/**
	 * 获取请求 OTO header
	 * Describe:
	 * @author:huhaopeng
	 * @param createUser String 创建人
	 * @param supplierIdentity String 供应商CODE
	 * @return
	 * return:Object
	 * Date:2014-4-24
	 */
	public static RequestHeader getOTORequestHeader(String createUser, String supplierIdentity){
		RequestHeader header = new RequestHeader();
		header.setApplication("tour.ectrip.com");
		header.setProcessor("DataExchangeProcessor");
		header.setVersion("1.0.0");
		header.setCreateUser(createUser);
		header.setSupplierIdentity(supplierIdentity);
		return header;
	}

	/**
	 * 获取请求 Qunar header
	 * Describe:
	 * @author:huhaopeng
	 * @param createUser String 创建人
	 * @param supplierIdentity String 供应商CODE
	 * @return
	 * return:Object
	 * Date:2014-4-24
	 */
	public static RequestHeader getQunarRequestHeader(String createUser, String supplierIdentity){
		RequestHeader header = new RequestHeader();
		header.setApplication("Qunar.Menpiao.Agent");
		header.setProcessor("SupplierDataExchangeProcessor");
		header.setVersion("v2.0.0");
		header.setCreateUser(createUser);
		header.setSupplierIdentity(supplierIdentity);
		return header;
	}

	/**
	 * 获取 响应 OTA header
	 * Describe:
	 * @author:huhaopeng
	 * @param headerParam
	 * @return
	 * return:Object
	 * Date:2014-4-24
	 */
	public static Object getOTAResponseHeader(String createUser, String code, String description){
		ResponseHeader header = new ResponseHeader();
		header.setApplication("tour.ectrip.com");
		header.setProcessor("DataExchangeProcessor");
		header.setVersion("1.0.0");
		header.setCreateUser(createUser);
		header.setCode(code);
		header.setDescribe(description);
		return header;
	}

	/**
	 * 获取 响应 OTO header
	 * Describe:
	 * @author:huhaopeng
	 * @param headerParam
	 * @return
	 * return:Object
	 * Date:2014-4-24
	 */
	public static Object getOTOResponseHeader(String createUser, String code, String description){
		ResponseHeader header = new ResponseHeader();
		header.setApplication("tour.ectrip.com");
		header.setProcessor("DataExchangeProcessor");
		header.setVersion("1.0.0");
		header.setCreateUser(createUser);
		header.setCode(code);
		header.setDescribe(description);
		return header;
	}

	/**
	 * 获取 响应 Qunar header
	 * Describe:
	 * @author:huhaopeng
	 * @param headerParam
	 * @return
	 * return:Object
	 * Date:2014-4-24
	 */
	public static Object getQunarResponseHeader(String createUser, String code, String description){
		ResponseHeader header = new ResponseHeader();
		header.setApplication("Qunar.Menpiao.Agent");
		header.setProcessor("SupplierDataExchangeProcessor");
		header.setVersion("v2.0.0");
		header.setCreateUser(createUser);
		header.setCode(code);
		header.setDescribe(description);
		return header;
	}
}

