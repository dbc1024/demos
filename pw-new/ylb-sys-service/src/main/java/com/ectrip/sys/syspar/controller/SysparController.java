package com.ectrip.sys.syspar.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.syspar.service.ISysparService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "syspar")
@Api(tags = "系统模块-系统参数信息管理")
public class SysparController {
	
	private static final Logger LOGGER = LogManager.getLogger(SysparController.class);
	@Autowired
	private ISysparService sysparService;
	
	/**
	 * 系统参数列表
	 * 
	 * @return
	 */
	@GetMapping(value = "v1/sysparViewList")
	@ApiOperation(value = "获取系统参数列表")
	public ResponseBean sysparViewList(@RequestParam(required = false) String pmky,@RequestParam(required = false) String pmcd,@RequestParam(required = false) String path,@RequestParam int page,@RequestParam int pageSize) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			Sysparv5 sysparv5 = new Sysparv5();
			if (pmky == null || "".equals(pmky)){// 标识为空时
				sysparv5.setSystp("0");// 参数级别为0表示第一级
			} else {// 标识不为空
				// 根据标识与参数码查询对象
//			sysparv5 = this.sysparService.find(sysparv5.getPmky(), sysparv5.getPmcd());
				sysparv5 = (Sysparv5) this.sysparService.get(Sysparv5.class, new Sysparv5Id(pmky, pmcd));
				sysparv5.setPmky(sysparv5.getId().getPmky());
				sysparv5.setPmcd(sysparv5.getId().getPmcd());
				int next = 0;
				if (path.equals("up")) {// 上一级
					if (sysparv5.getSystp().equals("1")) {// 当级别为1时表示第二级
						sysparv5.setSystp("0");
					}
				} else {// 下一级
					next = Integer.parseInt(sysparv5.getSystp()) + 1;
					sysparv5.setSystp(String.valueOf(next));
				}
			}
			ps = this.sysparService.SysparQueryList(sysparv5.getPmky(), sysparv5.getPmcd(), path, page, pageSize, null);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
			LOGGER.info("获取系统参数信息列表异常", e.getCause());
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}
	
	

	/**
	 * 查询
	 * 
	 * @return
	 */
	@GetMapping(value = "v1/sysparQueryList")
	@ApiOperation(value = "系统参数查询")
	public ResponseBean sysparQueryList(@RequestParam String pmky, @RequestParam int page,@RequestParam int pageSize) {
		ResponseBean result = new ResponseBean();
		int code = 200;
		String msg = "请求成功";
		PaginationSupport ps = null;
		try {
			String hsql = "from Sysparv5 where systp='0' and id.pmky like '" + pmky.toUpperCase()
					+ "%' order by id.pmky,id.pmcd";
			ps = this.sysparService.findPage(hsql, pageSize, page, null);
		} catch (Exception e) {
			e.printStackTrace();
			code = 400;
			msg = "接口异常";
			LOGGER.info("查询系统参数信息列表异常", e.getCause());
		}
		result.setCode(code);
		result.setMsg(msg);
		result.setData(ps);
		return result;
	}
	
	//pmcds数据形式pmcds="12,541,625,51"
	@GetMapping(value = "v1/listByPmcds")
	@ApiOperation(value = "根据pmcd集合获取系统参数列表(供其他服务调用)")
	public List getSysparamsByPmcds(@ApiParam("pmcds数据格式[12,541,625,51]") @RequestParam String pmcds) {
		
		return sysparService.getSysparamsByPmcds(pmcds);
	}
	
	//pmcds数据形式pmcds="12,541,625,51"
	@GetMapping(value = "v1/listByPmkyAndPmcds")
	@ApiOperation(value = "根据pmcd集合和pmky值获取系统参数列表(供其他服务调用)")
	public List getSysparamsByPmkyAndPmcds(@RequestParam String pmky,@ApiParam("pmcds数据格式[12,541,625,51]") @RequestParam String pmcds) {
		
		return sysparService.getSysparamsByPmkyAndPmcds(pmky, pmcds);
	}
	
	//pmvbs数据形式pmvbs="12,541,625,51"
	@GetMapping(value = "v1/listByParms")
	@ApiOperation(value = "根据多个参数值pmky,pmcd,pmvbs,spmcd获取系统参数列表(供其他服务调用)")
	public List getSysparamsByParms(@RequestParam(required=false) String pmky,@RequestParam(required=false)  String pmcd,@RequestParam(required=false)  String pmvbs,@RequestParam(required=false)  String spmcd) {
		
		return sysparService.getSysparamsByParms(pmky, pmcd,pmvbs,spmcd);
	}
	
	@GetMapping("v1/findSysparByPmky")
	@ApiOperation(value = "根据参数值pmky,pmcd获取系统参数列表(供其他服务调用)")
	public List<Sysparv5> findSysparByPmky(@RequestParam("pmky") String pmky, @RequestParam("pmcd") String pmcd){
		return sysparService.findSysparBypmky(pmky, pmcd);
	}
	
	@GetMapping(value = "v1/queryByPmkyPmcdwhere")
	@ApiOperation(value = "根据pmky,pmcdwhere获取系统参数列表(供其他服务调用)")
	public List queryByPmkyPmcdwhere(@RequestParam("pmky") String pmky, @RequestParam("pmcdwhere") String pmcdwhere) {
		
		return sysparService.query(pmky, pmcdwhere);
	}
	@ApiOperation(value = "根据pmky,pmcd集合获取系统参数(供其他服务调用)")
	@PostMapping(value = "findOne")
	public Sysparv5 findSysparv5(@RequestParam("pmky") String pmky, @RequestParam("pmcd") String pmcd) {
		
		return sysparService.findOne(pmky, pmcd);
	}
	
	@GetMapping(value = "retrieve")
	@ApiOperation(value = "原系统中retrieve方法(供其他服务调用)")
	public List retrieve(@RequestParam("pmky") String pmky) {
		
		return sysparService.retrieve(pmky);
	}
	
	@PostMapping("/findOneById")
	public Sysparv5 findOneById(@RequestParam("name") String name, @RequestParam("code") String code) {
		return sysparService.getValueById(name, code);
	}
	
	@GetMapping(value = "v1/find")
	public List find(@RequestParam("query") String query) {
		return sysparService.find(query);
	}
}
