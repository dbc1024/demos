package com.ectrip.sys.message.controller;

import com.ectrip.base.util.WebContant;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.message.dao.MbMessageDAO;
import com.ectrip.sys.message.dao.idao.IMbMessageDAO;
import com.ectrip.sys.message.service.MbMessageService;
import com.ectrip.sys.message.service.iservice.IMbMessageService;
import com.ectrip.sys.model.syspar.Mbmessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

import static com.ectrip.ec.model.notebook.Notebook.ADD;
import static com.ectrip.ec.model.notebook.Notebook.EDIT;

@RestController
public class MessageSendController {

	@Autowired
	private IMbMessageDAO mbMessageDAO;
	@Autowired
	private IMbMessageService mbMessageService;

	private ResponseBean responseBean;

	/**
	 * 获取短信内容
	 * @param mbmessage
	 * @return
	 */
	@RequestMapping("/message/sendSecondMessage")
	@ApiOperation(value = "获取短信内容")
	public ResponseBean sendSecondMessage(@RequestParam("mbmessage") Mbmessage mbmessage){

		int code = 400;
		String messages = "";
		if(mbmessage.getSeq()==null && mbmessage.getSeq().equals("")){
			messages = "短信流水号不能为空";//短信流水号不能为空
		}else {
			code = 200;
			messages = "成功";
			mbmessage = (Mbmessage) mbMessageDAO.get(Mbmessage.class, mbmessage.getSeq());
		}
		responseBean = new ResponseBean(code, messages, mbmessage);

		return responseBean;
	}

	/**
	 * 修改内容重发保存
	 * Describe:
	 * @auth:huangyuqi
	 * @return
	 * return:String
	 * Date:2011-11-24
	 */
	@RequestMapping("/message/sendByNoteSave")
	@ApiOperation(value = "短信发送")
	public ResponseBean sendByNoteSave(@RequestParam("mbmessage") Mbmessage mbmessage, @RequestParam("strutsAction") Integer strutsAction ){
		int code = 200;
		String messages = "";
		if(mbmessage.getSeq()==null && mbmessage.getSeq().equals("")){
			code = 400;
			messages = "短信流水号不能为空";
		}
		if(strutsAction==EDIT){//修改内容重发
			if(mbmessage.getNote()==null || "".equals(mbmessage.getNote())){
				code = 400;
				messages = "短信内容不能为空";
			}
			if(mbmessage.getNote()!=null && !"".equals(mbmessage.getNote()) && mbmessage.getNote().length()>=200){
					code = 400;
					messages = "短信内容字数不能超过200字";
			}
		}
		if(code==400){
			responseBean = new ResponseBean(code, messages, null);
			return responseBean;
		}

		Mbmessage message = (Mbmessage) mbMessageDAO.get(Mbmessage.class, mbmessage.getSeq());
		if(strutsAction==ADD){//重发
			message.setQuantity(message.getQuantity()+1);
			message.setIsok(0);
		}else if(strutsAction==EDIT){//修改内容重发
			message.setNote(mbmessage.getNote());
			message.setQuantity(message.getQuantity()+1);
			message.setIsok(0);

		}
		mbMessageService.updateMessage(message);
		messages =  WebContant.CORPNAME;//短信重发成功

		responseBean = new ResponseBean(code, messages, null);
		return responseBean;
	}

	/**
	 * 指定用户发送短信
	 * @return
	 */
	@RequestMapping("/message/sendMessageSave")
	@ApiOperation(value = "指定用户发送短信")
	public ResponseBean sendMessageSave(@RequestParam("mbmessage") Mbmessage mbmessage){
		int code = 200;
		String messages = "";
		if(mbmessage.getRevmbno()==null || "".equals(mbmessage.getRevmbno())){
			code = 400;
			responseBean = new ResponseBean(code, "接收手机号不能为空", null);
			return responseBean;
		}
		if (mbmessage.getRevmbno()!= null && !"".equals(mbmessage.getRevmbno())) {
			System.out.println("===========");
			Pattern p= Pattern.compile("^ \\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{4}-\\d{8}$");
			Pattern p1 = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
			boolean d = p.matcher(mbmessage.getRevmbno()).matches();
			boolean e = p1.matcher(mbmessage.getRevmbno()).matches();

			if (d == false && e == false) {
				code = 400;
				responseBean = new ResponseBean(code, "手机号码格式不对，请输入如：13212342345", null);
				return responseBean;
			}
		}

		if(mbmessage.getNote()==null || "".equals(mbmessage.getNote())){
			code = 400;
			responseBean = new ResponseBean(code, "短信内容不能为空", null);
			return responseBean;
		}

		if(mbmessage.getNote().length()>=200){
			code = 400;
			responseBean = new ResponseBean(code, "短信内容字数不能超过200字", null);
			return responseBean;
		}

		//保存数据
		mbMessageService.insertMessage(mbmessage.getRevmbno(), mbmessage.getNote());
		responseBean = new ResponseBean(code, WebContant.CORPNAME, null);
		return responseBean;
	}

}