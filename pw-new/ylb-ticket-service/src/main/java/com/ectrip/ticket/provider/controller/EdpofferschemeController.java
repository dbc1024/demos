package com.ectrip.ticket.provider.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.feign.service.SysService;
import com.ectrip.ticket.provider.service.IEdpofferschemetabService;

import io.swagger.annotations.Api;

/**
 * 优惠方案 处理
 * @author jiyong
 *
 */
@RestController
@RequestMapping(value = "offerscheme")
@Api(tags = "票务服务-票务信息设置-优惠方案管理")
public class EdpofferschemeController {
	
	private static final Logger LOGGER = LogManager.getLogger(EdpofferschemeController.class);
	@Autowired
	private SysService sysService;
	@Autowired
	private IEdpofferschemetabService edpofferschemetabService;
	
	/**
	 * Description:获得优惠方案列表
	 * 
	 * @return
	 * @author Jzhenhua<br>
	 *         Date 2011-10-24
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public String OfferschemaList() {/*
		

		// 获得登录员工
		Esfemployeetab employee = sysService.currentUser();
		// 获取当前登录用户所管理的服务商
		List providers=this.esbticketService.findListesbticket(employee, "01",0,"01");
//		List providers = this.genericService.getProviderList(employee
//				.getScenics());
//		if (provider == null || provider.getIscenicid() == null
//				|| provider.getIscenicid().equals("")) {
//			if (providers != null && providers.size() > 0) {
//				provider = new Esbscenicareatab();
//				BeanUtils.populate(provider, (Map) providers.get(0));
//			}
//		}
		// 设置服务商下拉列表
		getRequest().setAttribute("prdlist", providers);
		// 获得业务类型下拉列表
		getRequest().setAttribute("businesslist",
				this.edpofferschemetabService.getBusinessList());
		// 判断查询类型
		if (radiobutton != 0) {
			// 如果选择服务商查询
			if (radiobutton == 1) {
				// 设置业务类型和优惠名称为null
				this.edpofferschemetab.setIbusinessid(null);
				this.edpofferschemetab.setSzoffersschemename(null);
				// 追加路径
				sburl.append("?edpofferschemetab.iscenicid="
						+ edpofferschemetab.getIscenicid() + "&radiobutton=1");
			} else if (radiobutton == 2) {
				// 按业务类型查询设置服务商河优惠名称为空
				this.edpofferschemetab.setIscenicid(null);
				this.edpofferschemetab.setSzoffersschemename(null);
				// 追加路径
				sburl
						.append("?edpofferschemetab.ibusinessid="
								+ edpofferschemetab.getIbusinessid()
								+ "&radiobutton=2");
			} else if (radiobutton == 3) {
				// 判断是否有输入查询条件
				if (null == edpofferschemetab.getSzoffersschemename()
						|| "".equals(edpofferschemetab.getSzoffersschemename()
								.trim())) {
					this.addActionError(getText("优惠方案名称不能为空！"));
				}

				// 按优惠方案名称查询设置服务商和业务类型为空
				this.edpofferschemetab.setIscenicid(null);
				this.edpofferschemetab.setIbusinessid(null);
				// 追加路径
				sburl.append("?edpofferschemetab.szoffersschemename="
						+ edpofferschemetab.getSzoffersschemename()
						+ "&radiobutton=3");
			}
		}

		

		// 获取列表值
		this.ps = this.edpofferschemetabService.getEdpofferschemetabListView(
				edpofferschemetab, employee.getScenics(), page, pageSize, url);

		
	*/
		return null;
	}
}
