package com.ectrip.tourcard.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.tourcard.model.CardQuery;
import com.ectrip.tourcard.model.PassPeople;
import com.ectrip.tourcard.service.IPassPeopleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "passPeople")
@Api(tags = "旅游卡身份信息附加相关接口")
public class PassPeopleController {
	private static final Logger LOGGER = LogManager.getLogger(PassPeopleController.class);
	
	@Autowired
	private IPassPeopleService passPeopleService;
	
	
	//people.cardCode这个参数必须赋值，因为必须知道是哪张旅游卡的身份附加信息
	@PostMapping(value = "v1/list")
	@ApiOperation(value = "身份附加信息列表查询")
	public ResponseBean getList(@RequestBody(required=true) PassPeople people, @RequestParam(required=false) Integer pageSize,@RequestParam(required=false) Integer page){
		
		if (pageSize == null) {
			pageSize = 20;
		}
		if (page == null) {
			page = 1;
		}
		
		PaginationSupport ps = passPeopleService.getPassPeopleList(people, page, pageSize, null);
		
		return new ResponseBean(200, "操作成功", ps);
		
	}
	
	@GetMapping(value = "v1/get/{id}")
	@ApiOperation(value = "根据ID获取旅游卡身份信息附加")
	public ResponseBean findPassPeopleById(@PathVariable Long id) {
		
		PassPeople people = passPeopleService.getPassPeople(id);
		
		return new ResponseBean(200, "操作成功", people);
	}
	
	@PostMapping(value = "v1/save")
	@ApiOperation(value = "保存身份信息附加")
	public ResponseBean addPassPeople(@RequestBody PassPeople people) {
		
		if(("".equals(people.getIdentityNum()) || people.getIdentityNum()==null)  ||  ("".equals(people.getName()) || people.getName()==null)){
			return new ResponseBean(400, "请将身份信息填写完整");
		}
		
		if(!checkIdentityNum(people.getIdentityNum())){
			return new ResponseBean(400, "身份证号码不合法");
		}
		
		// 得到最大主键
		long maxId = passPeopleService.getMaxPk("id", "PassPeople");
		people.setId(maxId + 1);
		passPeopleService.insertPassPeople(people);
		
		return new ResponseBean(200, "保存成功");
	}
	
	@PutMapping(value = "v1/update")
	@ApiOperation(value = "修改身份信息附加")
	public ResponseBean updatePassPeople(@RequestBody PassPeople people) {
		
		if(("".equals(people.getIdentityNum()) || people.getIdentityNum()==null)  ||  ("".equals(people.getName()) || people.getName()==null)){
			return new ResponseBean(400, "请将身份信息填写完整");
		}
		
		if(!checkIdentityNum(people.getIdentityNum())){
			return new ResponseBean(400, "身份证号码不合法");
		}
		
		passPeopleService.updatePassPeople(people);
		
		return new ResponseBean(200, "修改成功");
	}
	
	
	@PutMapping(value = "v1/delete/{id}")
	@ApiOperation(value = "删除身份信息附加")
	public ResponseBean deletePassPeople(@PathVariable Long id) {
		
		
		PassPeople people = passPeopleService.getPassPeople(id);
		passPeopleService.deletePassPeople(people);
		
		return new ResponseBean(200, "修改成功");
	}
	
	
	@PostMapping(value = "v1/importExcel", consumes="multipart/*", headers="content-type=multipart/form-data")
	@ApiOperation(value = "导入身份信息附加")
	public ResponseBean importData(@RequestParam MultipartFile excelFile, @RequestParam String cardCode){
		
		
		//表格数据存放数据结构
		List<List<String>> dataList = new ArrayList<List<String>>();
		
		try {
			
			InputStream is = excelFile.getInputStream();
//			InputStream is = new FileInputStream(excelFile);
			
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
			
			if(sheet.getLastRowNum()==0){
				
				return new ResponseBean(400, "导入失败,请填写数据后再导入");
			}else if(sheet.getRow(0).getLastCellNum()<2){
				
				return new ResponseBean(400, "导入失败,请使用正确模板导入");
			}
			
			for(int i=1; i<=sheet.getLastRowNum(); i++){//i=1标题不写入
				HSSFRow row = sheet.getRow(i);
				
				//根据excel顺序依次为：旅游卡代码;姓名;身份证号
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
		
		
		
		
		/*校验待导入数据*/
		int count=0;
		Set<String> identityNumSet = new HashSet<String>();
		for (List<String> list : dataList) {
			count++;
			
			//检查对应的旅游卡是否存在
//			String cardCode = list.get(0);
//			TourCard tourCard = tourCardService.getTourCardByCode(cardCode);
//			if(tourCard==null){
//				this.addActionError(getText("导入失败,系统不存在的旅游卡代码["+ cardCode +"]"));
//				return INPUT;
//			}
			
			//检查身份证是否合法，是否已经导入
			String identityNum = list.get(1);
			if(!checkIdentityNum(identityNum)){
				return new ResponseBean(400, "身份证号码["+ identityNum +"]不合法");
			}
			PassPeople passPeople = passPeopleService.getPassPeopleByIdAndCode(identityNum, cardCode);
			if(passPeople != null){
				return new ResponseBean(400, "导入失败,身份证号["+ identityNum +"]已存在");
			}
			
			//检查导入表中是否有重复数据
			identityNumSet.add(identityNum);
			if(count != identityNumSet.size()){
				return new ResponseBean(400, "导入失败,表格中有重复的身份证号");
			}
			
		}
		
		
		//校验通过，存入数据库
		PassPeople people = new PassPeople();
		for (List<String> list : dataList) {
			// 得到最大主键
			long maxId = passPeopleService.getMaxPk("id", "PassPeople");
			people.setId(maxId + 1);
			people.setCardCode(cardCode);
			people.setName(list.get(0));
			people.setIdentityNum(list.get(1));
			
			passPeopleService.insertPassPeople(people);
		}
		
		
		
		return new ResponseBean(200, "导入成功");
	}
	
	
	public boolean checkIdentityNum(String idNum){
		
		boolean matches18 = idNum.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
		boolean matches15 = idNum.matches("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$");
		
		return (matches18 || matches15);
	}
}
