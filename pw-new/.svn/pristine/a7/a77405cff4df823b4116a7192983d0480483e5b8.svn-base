package com.ectrip.sys.message.controller;

import java.util.regex.Pattern;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.base.util.WebContant;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.message.service.iservice.IMbMessageService;
import com.ectrip.sys.model.syspar.Mbmessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 短信管理
 * @author huangyuqi
 */
@RestController
@RequestMapping("message")
@Api(tags = "电商管理-手机短信-信息管理")
public class MessageController extends BaseController{
	private static final Logger LOGGER = LogManager.getLogger(MessageController.class);
	
	@Autowired
	private IMbMessageService mbmessageService;

	/**
	 * 短信列表查询
	 */
	@GetMapping(value = "v1/list")
	@ApiOperation(value = "短信列表查询")
	public ResponseBean QueryMessageList(@RequestParam(required=false) String rzti,@RequestParam(required=false) String ldti,
			@RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
		
		if(null==pageSize) {
			pageSize=20;
		}
		if(null==page) {
			page=1;
		}
		
		try {
			if (rzti != null && !"".equals(rzti)) {
				Pattern p = Pattern
						.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
				boolean b = p.matcher(rzti).matches();
				if (b == false) {
					 return new ResponseBean(ERROR_CODE_400, "error.rzti.duplicate");//起始日期输入有错误，请输入yyyy-mm-dd格式的有效日期
				}
			}
			if (ldti != null && !"".equals(ldti)) {
				Pattern p = Pattern
						.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))");
				boolean b = p.matcher(ldti).matches();
				if (b == false) {
					return new ResponseBean(ERROR_CODE_400, "error.ldti.duplicate");//截止日期输入有错误，请输入yyyy-mm-dd格式的有效日期
				}
			}
			if (rzti.compareTo(ldti) > 0) {
				return new ResponseBean(ERROR_CODE_400, "error.date.rzti.big.ldti");//起始日期不能大于截止日期"
			}
			
			long daynumb = Tools.getDayNumb(rzti, ldti) - 1;
			if (daynumb > 30) {
				return new ResponseBean(ERROR_CODE_400, "error.date.maxday");//日期时间段间隔最多为30天	
			}
			
			PaginationSupport ps = mbmessageService.queryMessageList(rzti, ldti, page, pageSize, null);
			
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", ps);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("短信列表查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "短信列表查询异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	@GetMapping(value = "v1/detail/{seq}")
	@ApiOperation(value = "短信详情")
	public ResponseBean getMessageDetail(@PathVariable Long seq) {
		
		try {
			if(seq==null){
				return new ResponseBean(ERROR_CODE_400, "error.mbmessage.seq.required");//短信流水号不能为空
			}
			
			Mbmessage messageDetail = mbmessageService.getMessageDetail(seq);
			return new ResponseBean(SUCCESS_CODE_200, "操作成功", messageDetail);
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("短信详情查询异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "短信详情查询异常", "异常信息："+ e.getMessage());
		}
	}
	
	@GetMapping(value = "v1/repeatSend/{seq}")
	@ApiOperation(value = "重发短信")
	public ResponseBean repeatSend(@PathVariable Long seq) {
		
		try {
			if(seq==null){
				return new ResponseBean(ERROR_CODE_400, "error.mbmessage.seq.required");//短信流水号不能为空
			}
			
			Mbmessage message = mbmessageService.getMessageDetail(seq);
			
			message.setQuantity(message.getQuantity()+1);
			message.setIsok(0);
			
			mbmessageService.updateMessage(message);
			
			return new ResponseBean(SUCCESS_CODE_200, "success.mbmessage.second"+ WebContant.CORPNAME);//短信重发成功
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("重发短信异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "重发短信异常", "异常信息："+ e.getMessage());
		}
		
	}
	
	
	@PostMapping(value = "v1/updateAndrepeatSend")
	@ApiOperation(value = "修改内容并重发短信")
	public ResponseBean updateAndrepeatSend(@RequestParam Long seq, @RequestParam String note) {
		
		try {
			if(seq==null){
				return new ResponseBean(ERROR_CODE_400, "error.mbmessage.seq.required");//短信流水号不能为空
			}
			
			if(note==null || "".equals(note)){
				return new ResponseBean(ERROR_CODE_400, "error.mbmessage.note.required");//短信内容不能为空
			}
			if(note!=null && !"".equals(note)){
				if(note.length()>=200){
					return new ResponseBean(ERROR_CODE_400, "error.mbmessage.note.length");//短信内容字数不能超过200字
				}			
			}
			
			Mbmessage message = mbmessageService.getMessageDetail(seq);
			
			message.setNote(note);
			message.setQuantity(message.getQuantity()+1);
			message.setIsok(0);
			
			mbmessageService.updateMessage(message);
			
			return new ResponseBean(SUCCESS_CODE_200, "success.mbmessage.second"+ WebContant.CORPNAME);//短信重发成功
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("重发短信异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "重发短信异常", "异常信息："+ e.getMessage());
		}
	}
	
	
	/**
	 *  按指定客户发送并保存短信
	 */
	@PostMapping(value = "v1/customMessage")
	@ApiOperation(value = "按指定客户发送并保存短信")
	public ResponseBean customMessage(@RequestParam String revmbno, @RequestParam String note){
		
		try {
			if(revmbno==null || "".equals(revmbno)){
				return new ResponseBean(ERROR_CODE_400, "error.mbmessage.telno.required");//接收手机号不能为空
			}
			
			Pattern p= Pattern.compile("^ \\d{3}-\\d{8}|\\d{4}-\\d{7}|\\d{4}-\\d{8}$");
			Pattern p1 = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");
			boolean d = p.matcher(revmbno).matches();
			boolean e = p1.matcher(revmbno).matches();
			if (d == false && e == false) {
				return new ResponseBean(ERROR_CODE_400, "手机号码格式不对，请输入如：13212342345");// 手机号码格式不对，请输入如：13212342345
			}
			
			
			if(note==null || "".equals(note)){
				return new ResponseBean(ERROR_CODE_400, "error.mbmessage.note.required");//短信内容不能为空
			}
			if(note.length()>=200){
				return new ResponseBean(ERROR_CODE_400, "error.mbmessage.note.length");//短信内容字数不能超过200字
			}			
			
			
			//保存数据
			mbmessageService.insertMessage(revmbno, note);
			return new ResponseBean(SUCCESS_CODE_200, "success.mbmessage.add"+ WebContant.CORPNAME);//短信重发成功
		} catch (Exception e) {
			e.printStackTrace();
            LOGGER.error("按指定客户发送并保存短信异常:"+ StringUtil.toString_02(e));
            return new ResponseBean(ERROR_CODE_400, "按指定客户发送并保存短信异常", "异常信息："+ e.getMessage());
		}
		
	}
	
}
