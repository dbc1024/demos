package com.ectrip.tourcard.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ectrip.base.util.DesEncryptUtil;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.PinYinUtil;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.tourcard.model.CardDetailQuery;
import com.ectrip.tourcard.model.CardQuery;
import com.ectrip.tourcard.model.TourCard;
import com.ectrip.tourcard.model.TourCardDetail;
import com.ectrip.tourcard.service.ITourCardDetailService;
import com.ectrip.tourcard.service.ITourCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "tourCardDetail")
@Api(tags = "旅游卡明细相关接口")
public class TourCardDetailController {
	private static final Logger LOGGER = LogManager.getLogger(TourCardDetailController.class);
	
	@Autowired
	private ITourCardDetailService tourCardDetailService;
	@Autowired
	private ITourCardService tourCardService;

	@PostMapping(value = "v1/list")
	@ApiOperation(value = "旅游卡明细列表查询")
	public ResponseBean getList(@RequestBody(required=false) CardDetailQuery cardDetailQuery, @RequestParam(required=false) Integer pageSize, @RequestParam(required=false) Integer page){
		
		if (pageSize == null) {
			pageSize = 20;
		}
		if (page == null) {
			page = 1;
		}
		
		if(cardDetailQuery == null) {
			cardDetailQuery = new CardDetailQuery();
			cardDetailQuery.setStatus(3L);	//对应前台的“全部(3)”
			cardDetailQuery.setStartCreateTime(this.getToday());
			cardDetailQuery.setEndCreateTime(this.getToday());
		}
		
		PaginationSupport ps = tourCardDetailService.getTourCardDetailList(cardDetailQuery, page, pageSize, null);
		
		return new ResponseBean(200, "操作成功", ps);
	}
	
	/*查询某条数据详情*/
	@GetMapping(value = "v1/get/{detailId}")
	@ApiOperation(value = "根据ID获取旅游卡明细")
	public ResponseBean queryTourCardDetail(@PathVariable Long detailId){
		
		TourCardDetail tourCardDetail = tourCardDetailService.getTourCardDetail(detailId);
		
		//判断是否限制次数
		TourCard tourCard = tourCardService.getTourCardByCode(tourCardDetail.getCode());
		if(tourCard.getTimesFlag()==-1){
			tourCardDetail.setEffectiveTimes(-1L);
		}
		//遮蔽显示身份证号码
		if(StringUtil.isNotEmpty(tourCardDetail.getIdentityNum())){
			tourCardDetail.setMarkedIdentityNum(StringUtil.markIdNumber(DesEncryptUtil.decrypt(tourCardDetail.getIdentityNum())));
		}
		
		return new ResponseBean(200, "操作成功", tourCardDetail);
	}
	
	
	/*删除某条数据*/
	@DeleteMapping(value = "v1/delete/{detailId}")
	@ApiOperation(value = "根据ID删除旅游卡明细")
	public ResponseBean deleteTourCardDetail(@PathVariable Long detailId){
//		Esfemployeetab esfemployeetab = (Esfemployeetab)ActionContext.getContext().getSession().get("employee");
//		if(esfemployeetab==null || "".equals(esfemployeetab)){
//			return "login";
//		}
		
		Syslog syslog = new Syslog();
//		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		syslog.setIemployeeid(1L);
		
		TourCardDetail tourCardDetail = tourCardDetailService.getTourCardDetail(detailId);
		
		//只有历史卡可以删除
		if(tourCardDetail.getHistoryCardFlag()==1){
			
			if(tourCardDetail.getStatus()==1){//表示已被绑定，不可删除
				return new ResponseBean(400, "此卡已被绑定，不可删除");
			}
			
			tourCardDetail.setStatus(-1L);	//逻辑删除，以-1表示
			tourCardDetailService.updateTourCardDetail(tourCardDetail, syslog);
		}else{
			
			return new ResponseBean(400, "非历史卡不可删除");
		}
		
		return new ResponseBean(200, "操作成功");
	}
	
	
	/** 旅游卡明细导入*/
	@PostMapping(value = "v1/importExcel", consumes="multipart/*", headers="content-type=multipart/form-data")
	@ApiOperation(value = "导入旅游卡明细")
	public ResponseBean importExcel(@RequestParam MultipartFile excelFile) throws Exception{
		
//		Esfemployeetab esfemployeetab = (Esfemployeetab)ActionContext.getContext().getSession().get("employee");
//		if(esfemployeetab==null || "".equals(esfemployeetab)){
//			return "login";
//		}
		
		Syslog syslog = new Syslog();
//		syslog.setIemployeeid(esfemployeetab.getIemployeeid());
		syslog.setIemployeeid(1L);
		
		//表格数据存放数据结构
		List<List<String>> dataList = new ArrayList<List<String>>();
		
		
		try {
			InputStream is = excelFile.getInputStream();
//			InputStream is = new FileInputStream(excelFile);
			
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			if(sheet.getLastRowNum()==0){
				return new ResponseBean(400, "导入失败,请使用正确的模板和数据");
				
			}else if(sheet.getRow(0).getLastCellNum()<8){
				return new ResponseBean(400, "导入失败,请使用正确模板导入");
			}
			
			
			for(int i=1; i<=sheet.getLastRowNum(); i++){//i=1标题不写入
				HSSFRow row = sheet.getRow(i);
				
				//根据excel顺序依次为：旅游卡代码;银行卡卡号;姓名;身份证号;单位/学校;工号/学号 ;旅游卡生效时间;旅游卡截止时间;已使用次数
				//2018-1-2,需求更改：去掉银行卡卡号，新顺序：旅游卡代码;姓名;身份证号;单位/学校;工号/学号 ;旅游卡生效时间;旅游卡截止时间;已使用次数
				List<String> dataValues = new ArrayList();
				
				for(int m=0; m<row.getLastCellNum(); m++){
					
					String stringValue = "";
					
					//row.getCell((short)m)可能为null，提前处理
					if(row.getCell((short)m)!=null){
						int type = row.getCell((short)m).getCellType();
						
						if(type == HSSFCell.CELL_TYPE_STRING){
							stringValue = row.getCell((short)m).getStringCellValue();
						}else if(type == HSSFCell.CELL_TYPE_NUMERIC){
							stringValue = String.valueOf(row.getCell((short)m).getNumericCellValue()).split("\\.")[0];
						}else if(type == HSSFCell.CELL_TYPE_FORMULA){
							stringValue = String.valueOf(row.getCell((short)m).getCellFormula());
						}else if(type == HSSFCell.CELL_TYPE_BOOLEAN){
							stringValue = String.valueOf(row.getCell((short)m).getBooleanCellValue());
						}else if(type == HSSFCell.CELL_TYPE_ERROR){
							stringValue = String.valueOf(row.getCell((short)m).getErrorCellValue());
						}
						
						stringValue = stringValue.trim();
						
						if("".equals(stringValue)){
							
							return new ResponseBean(400, "导入失败,表格中有空值");
						}
						
					}else {
						return new ResponseBean(400, "导入失败,表格中有空值");
					}
					
					dataValues.add(stringValue);
					
				}
				
				dataList.add(dataValues);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		/*封装旅游卡明细数据列表*/
		List<TourCardDetail> tourCardDetailList = new ArrayList<TourCardDetail>();
		
		//存入导入表中每个身份证号包含的卡号，用于判断导入表中是否存在重复数据
		Map<String,Map<String,String>> idNum2cardCode = new HashMap<String, Map<String,String>>();
		
		TourCard tourCard;
		
		for (List<String> list : dataList) {
			TourCardDetail tourCardDetail = new TourCardDetail();
			
			String cardCode = list.get(0);
			
			tourCard = tourCardService.getTourCardByCode(cardCode);
			if(tourCard==null){
				return new ResponseBean(400, "导入失败,系统不存在的旅游卡代码["+ cardCode +"]");
			}
			
			//tourCardDetail.setId();id赋值放到循环插入数据库的时候获取并赋值，因为id是手动增加，不是数据库自增
			
			//生成旅游卡卡号
			String orid = tourCardDetailService.getMaxNo("");
			String cardNumber = PinYinUtil.getFirst2PinYinHeadChar(tourCard.getName()) + orid;
			
			//旅游卡卡号赋值
			tourCardDetail.setCardNumber(cardNumber);
			
			//默认值赋值，旅游卡关联值填充
			tourCardDetail.setImageAddr(tourCard.getImageAddr());
			tourCardDetail.setHistoryCardFlag(1L);//历史卡标志
			tourCardDetail.setStatus(0L);	//未开通
//			tourCardDetail.setCreateMan(esfemployeetab.getSzemployeename());
			tourCardDetail.setCreateMan("admin");
			tourCardDetail.setCreateTime(this.getNow());
			tourCardDetail.setScenics(tourCard.getScenics());
			tourCardDetail.setName(tourCard.getName());
			tourCardDetail.setProfitNum(tourCard.getProfitNum());
			tourCardDetail.setPrice(tourCard.getPrice());
			tourCardDetail.setDescpt(tourCard.getDescpt());
			tourCardDetail.setTimesFlag(tourCard.getTimesFlag());
			
			//excel表格数据赋值begin
			tourCardDetail.setCode(cardCode);//0
//			String cardNum = list.get(1);
//			if(!cardNum.matches("^[0-9]*$")){
//				this.addActionError(getText("导入失败,银行卡号只能输入数字"));
//				return INPUT;
//			}
//			tourCardDetail.setBankCardNum(cardNum);
			tourCardDetail.setUsername(list.get(1));
			//判断身份证号是否合法
			String idNum = list.get(2);
			boolean matches18 = idNum.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
			boolean matches15 = idNum.matches("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$");

			if(!(matches18 || matches15)){
				return new ResponseBean(400, "导入失败,身份证["+ idNum + "]不合法");
			}

			tourCardDetail.setIdentityNum(idNum);
			tourCardDetail.setOrganization(list.get(3));
			tourCardDetail.setWorkNum(list.get(4));
			
			//验证时间格式及前后顺序
			String startDate = list.get(5);
			String endDate = list.get(6);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			try {
				Date startDate1 = format.parse(startDate);
				Date endDate1 = format.parse(endDate);
				
				if(startDate1.after(endDate1)){
					return new ResponseBean(400, "导入失败,截止日期必须大于生效日期");
				}
			} catch (ParseException e) {
				return new ResponseBean(400, "导入失败,时间格式有误");
			}
			tourCardDetail.setPeriodStartDate(startDate);
			tourCardDetail.setPeriodEndDate(endDate);
			
			try {
				
				tourCardDetail.setUsedTimes(Long.parseLong(list.get(7)));
			} catch (Exception e) {
				return new ResponseBean(400, "导入失败,已使用次数只能输入数字");
			}
			
			//判断是否需要限制次数，如果限制才存入有效次数和剩余次数
			if(tourCard.getTimesFlag()==0){
				if(Long.parseLong(list.get(7))>tourCard.getEffectiveTimes()){
					return new ResponseBean(400, "导入失败,已使用次数大于总次数");
				}
				
				tourCardDetail.setEffectiveTimes(tourCard.getEffectiveTimes());
				tourCardDetail.setLeaveTimes(tourCard.getEffectiveTimes()-Long.parseLong(list.get(7)));
			}
			
			tourCardDetailList.add(tourCardDetail);
			
			//表格数据自查重
			Map<String, String> cardCodeMap = idNum2cardCode.get(tourCardDetail.getIdentityNum());
			if(cardCodeMap==null){
				cardCodeMap = new HashMap<String, String>();
				cardCodeMap.put(tourCardDetail.getCode(), "exist");
				idNum2cardCode.put(tourCardDetail.getIdentityNum(), cardCodeMap);
			}else{
				String codeExist = cardCodeMap.get(tourCardDetail.getCode());
				if("".equals(codeExist) || codeExist==null){
					cardCodeMap.put(tourCardDetail.getCode(), "exist");
				}else{
					
					return new ResponseBean(400, "导入失败,表格中存在重复数据");
				}
			}
			
			
			//表格数据与系统数据查重
			List<TourCardDetail> detailList = tourCardDetailService.getDetailsByIdnumAndCode(DesEncryptUtil.encrypt(tourCardDetail.getIdentityNum()), tourCardDetail.getCode());
			if(detailList!=null && detailList.size()>0){
				
				return new ResponseBean(400, "导入失败,表格中存在系统已有数据");
			}
			
		}
		
		
		/*将旅游卡明细列表插入数据库*/
		for (TourCardDetail tourCardDetail : tourCardDetailList) {
			
			long maxId = tourCardDetailService.getMaxPk("id", "TourCardDetail");
			tourCardDetail.setId(maxId+1);
			tourCardDetail.setIdentityNum(DesEncryptUtil.encrypt(tourCardDetail.getIdentityNum()));//身份证号码加密

			tourCardDetailService.insertTourCardDetail(tourCardDetail, syslog);
		}
		
		return new ResponseBean(200, "导入成功");
	}
	
	
	@PostMapping(value = "v1/listAll")
	@ApiOperation(value = "根据条件查询所有旅游卡明细(供其他服务调用)")
	public List getListAll(@RequestParam(required=false) String cardNumber, @RequestParam(required=false) String cardName ,@RequestParam(required=false) String profitNum){
		
		return tourCardDetailService.getListAll(cardNumber, cardName, profitNum);
	}
	
	@GetMapping(value = "v1/getCardDetailsByCardNumbers")
	@ApiOperation(value = "根据旅游卡号集合查询所有旅游卡明细(供其他服务调用)")
	public List getCardDetailsByCardNumbers(@RequestParam String cardNumbers) {
		
		return tourCardDetailService.getCardDetailsByCardNumbers(cardNumbers);
	}
	
	
	
	
	public String getNow(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = df.format(new Date());
		return today;
	 }
	
	public String getToday(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String today = df.format(new Date());
		return today;
	  
	}
	
}
