package com.ectrip.sys.employee.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.base.action.BaseController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.StringUtil;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.employee.dto.EsfDeptTabDto;
import com.ectrip.sys.employee.service.IESFDeptTabService;
import com.ectrip.sys.model.employee.Esfdeptpoststab;
import com.ectrip.sys.model.employee.Esfdepttab;
import com.ectrip.sys.model.employee.Galcompanyinfotab;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "系统服务模块-部门管理相关接口")
@RequestMapping(value = "depts")
public class ESFDeptTabController extends BaseController{
	
	private static final Logger LOGGER = LogManager.getLogger(ESFDeptTabController.class);
	@Autowired
	private IESFDeptTabService deptTabService;
	/**
	 * 分页查看部门信息
	 */
	@GetMapping(value = "/v1/showAllDepts")
	@ApiOperation(value = "分页查看部门信息")
	public ResponseBean showAllDepts(@RequestParam(required = false) Long ideptid,@RequestParam Long icompanyinfoid,@RequestParam int pageSize,@RequestParam int page){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			if(ideptid == null || ideptid == 0L){
				ideptid = 0L;
				ps=deptTabService.findPage(ideptid,icompanyinfoid,pageSize, page, null);
			}else{//
				Esfdepttab dept = (Esfdepttab) this.deptTabService.get(Esfdepttab.class, ideptid);
				/*deptid = dept.getIdeptid().toString();
				deptComId = dept.getIcompanyinfoid().toString();
				ilevel = dept.getIlevel()+"";
				
				parentid = dept.getIparentid().toString();*/
				
				ps=deptTabService.findPage(dept.getIdeptid(),dept.getIcompanyinfoid(),pageSize, page, null);
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("查询部门列表信息异常:"+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}
	/**
	* @Title: showUpDepts  
	* @Description: TODO 查看上一级部门
	* @param @return    参数  
	* @return ResponseBean    返回类型  
	* @throws
	 */
	@GetMapping(value = "/v1/showUpDepts")
	@ApiOperation(value = "分页查看部门信息")
	public ResponseBean showUpDepts(@RequestParam(name="iLevel")String iLevel,@RequestParam(name="deptComId") @ApiParam(name="deptComId",value="部门与企业关联的Id")String deptComId,
									@RequestParam(name="page")int page,@RequestParam(name="pageSize")int pageSize){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			if(iLevel != null && !"".equals(iLevel) && deptComId != null && !"".equals(deptComId)){
				Galcompanyinfotab gal = (Galcompanyinfotab) this.deptTabService.get(Galcompanyinfotab.class, new Long(deptComId));
				deptComId = gal.getIcompanyinfoid()+"";
				Galcompanyinfotab company = new Galcompanyinfotab();
				company.setIcompanyinfoid(new Long(deptComId));
				ps = deptTabService.findPage2(new Long(iLevel),new Long(deptComId),pageSize, page, null);
				iLevel = (new Long(iLevel)-1)+"";
				if(new Long(iLevel)==0||new Long(iLevel)==-1){
					iLevel=1+"";
				}
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("查看上一级部门信息异常:"+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}
	/**
	* @Title: viewDeptInfo  
	* @Description: TODO 查看部门详细信息
	* @param @param ideptId
	* @param @return    参数  
	* @return ResponseBean    返回类型  
	* @throws
	 */
	@GetMapping(value="/v1/viewDeptInfo")
	@ApiOperation(value = "查看部门详细信息")
	public ResponseBean viewDeptInfo(@RequestParam(name="ideptId")Long ideptId){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		EsfDeptTabDto esfDeptTabDto = new EsfDeptTabDto();
		try {
			if(ideptId != null){
				Esfdepttab dept = (Esfdepttab)this.deptTabService.get(Esfdepttab.class, ideptId);
				Galcompanyinfotab company = (Galcompanyinfotab)this.deptTabService.get(Galcompanyinfotab.class, dept.getIcompanyinfoid());
				List<?> plist = deptTabService.allPostsById(dept.getIdeptid());
				List<Long> selList = new ArrayList<Long>();
				for(int i=0;i<plist.size();i++){
					Esfdeptpoststab esfdeptpoststab = (Esfdeptpoststab) plist.get(i);
					selList.add(esfdeptpoststab.getIpostsid());
				}
				esfDeptTabDto.setEsfdepttab(dept);
				esfDeptTabDto.setCompanyName(company.getSzcompanyname());
				esfDeptTabDto.setPostsTabs(plist);
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("查看上一级部门信息异常:"+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(esfDeptTabDto);
		return result;
	}
	/**
	* @Title: saveDeptInfo  
	* @Description: TODO 新增或修改部门信息
	* @param @param flag 标识新增：1 修改： 2
	* @param @param dept 部门信息
	* @param @param deptComId 企业id
	* @param @param parentId 父部门Id
	* @param @param selListVal 岗位id 数组 类型：Long
	* @param @return    参数  
	* @return ResponseBean    返回类型  
	* @throws
	 */
	@GetMapping(value="/v1/saveDeptInfo")
	@ApiOperation(value = "新增或修改部门详细信息")
	public ResponseBean saveDeptInfo(@RequestParam("flag")Integer flag,@RequestBody Esfdepttab dept,@RequestParam("deptComId")Long deptComId,
									 @RequestParam("parentId")Long parentId,@RequestParam("selListVal")String selListVal){
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			List<String> selList = Arrays.asList(selListVal);
			if(flag == ADD){//增加操作
				
				deptTabService.insertDept(dept,deptComId,parentId,selList);
			}else if(flag == EDIT){//修改操作
				Esfdepttab edp = (Esfdepttab)this.deptTabService.get(Esfdepttab.class, dept.getIdeptid());
				if(edp.getByisuse() == 0){
					List<?> slist = deptTabService.allPostsById(dept.getIdeptid());
					for(Object o:slist){
						selList.add(((Esfdeptpoststab)o).getIpostsid()+"");
					}
				}
				if(!edp.getSzdeptname().equals(dept.getSzdeptname())){
					boolean isuse = this.deptTabService.volidateSole(new String[]{"icompanyinfoid"},new Long[]{new Long(deptComId)},new String[]{"szdeptname"}, new String[]{dept.getSzdeptname()}, "Esfdepttab");
					if(!isuse){
						msg = "此企业下的部门名称已存在！";
						return new ResponseBean(400, msg);
					}
				}
				dept.setIcompanyinfoid(new Long(deptComId));
				deptTabService.update(dept, dept.getIdeptid(),selList);
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("新增或修改部门信息异常:"+StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}	
	
	/**
	* @Title: deleteDept  
	* @Description: TODO 删除部门信息
	* @param @param deptId
	* @param @return    参数  
	* @return ResponseBean    返回类型  
	* @throws
	 */
	@GetMapping(value="/v1/deleteDept")
	@ApiOperation(value = "删除部门信息")
	public ResponseBean deleteDept(@RequestParam(name = "deptId") Long deptId) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		try {
			Esfdepttab dept = (Esfdepttab) this.deptTabService.get(Esfdepttab.class, deptId);
			if (dept != null) {
				// 获取下级部门
				List<?> dlist = deptTabService.selectNextDept(dept.getIdeptid());
				if (dlist.size() == 0) {// 无下级部门
					// 获取员工
					List<?> elist = deptTabService.selectEmpFromDept(dept.getIdeptid());
					if (elist.size() == 0) {// 无员工

						deptTabService.deleteDept(Esfdepttab.class, dept.getIdeptid());
					} else {
						// 提示先删除员工
						msg = "部门下存在员工，请先删除员工";
						return new ResponseBean(code, msg);
					}
					deptTabService.deleteDept(Esfdepttab.class, dept.getIdeptid());
				} else {
					// 提示先删除下级部门
					msg = "部门下存在下级，请先删除下级";
					return new ResponseBean(code, msg);
				}
			}
		} catch (Exception e) {
			code = 400;
			msg = "接口异常";
			LOGGER.info("新增或修改部门信息异常:" + StringUtil.toString_02(e));
		}
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
