package com.ectrip.sys.message.service;

import org.springframework.stereotype.Service;

import com.ectrip.sys.message.dao.idao.IMbMessageDAO;

/**
 * 短信发送
 * 
 * @author oukoka
 */
@Service
public class MessageSendService {

	private IMbMessageDAO mbMessageDAO;

	public IMbMessageDAO getMbMessageDAO() {
		return mbMessageDAO;
	}

	public void setMbMessageDAO(IMbMessageDAO mbMessageDAO) {
		this.mbMessageDAO = mbMessageDAO;
	}
//	@SuppressWarnings("rawtypes")
//	public void run(){
//		System.out.println("短信发送开始执行" + Tools.getDayTimes());
//
//		try {
//
//			String today = Tools.getDate(Tools.getDays(), 0);
//
//			List messageList = mbMessageDAO.getMessagelist(today);
//			if (messageList != null && messageList.size() >= 1) {
//				for (int i = 0; i < messageList.size(); i++) {
//					Mbmessage meg =(Mbmessage) messageList.get(i);
//					//请根据实际 appid 和 appkey 进行开发，以下只作为演示 sdk 使用
//					int appid = 1400033170;
//					String appkey = "3787941c1e92cea9aab7ed5fa47a5f9f";
//
//					//初始化单发
//					SmsSingleSender singleSender = new SmsSingleSender(appid, appkey);
//					SmsSingleSenderResult singleSenderResult;
//
//					//普通单发
//					singleSenderResult = singleSender.send(0, "86", meg.getRevmbno(), meg.getNote(), "", "");
//					System.out.println(singleSenderResult);
//
//
//					if("OK".equals(singleSenderResult.errMsg)){
//						meg.setIsok(1);
//						meg.setQuantity(meg.getQuantity()+1);
//					}else{
//						meg.setIsok(0);
//						meg.setQuantity(meg.getQuantity()+1);
//					}
//					if(singleSenderResult.result!=1014&&singleSenderResult.result!=1025){
//						mbMessageDAO.update(meg);
//					}
//
//
//					//Mbmessage meg =(Mbmessage) messageList.get(i);
//
//
//
//
//				}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException(e.getMessage());
//		}
//		System.out.println("短信发送结束执行" + Tools.getDayTimes());
//	}

}
