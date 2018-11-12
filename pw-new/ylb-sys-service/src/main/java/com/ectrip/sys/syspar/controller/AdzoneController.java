package com.ectrip.sys.syspar.controller;

import static com.ectrip.ec.model.notebook.Notebook.ADD;
import static com.ectrip.ec.model.notebook.Notebook.EDIT;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.util.Tools;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.syspar.Adzone;
import com.ectrip.sys.syspar.service.ISysparService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "adzone")
@Api(tags = "系统模块-网站信息管理")
public class AdzoneController {
	@Autowired
	private ISysparService sysparService;

	
	@RequestMapping("/v1/retrieve")
	public List retrieve(String pmky) {
		return sysparService.retrieve(pmky);
	}


	@PostMapping(value = "/v1/adzoneSave")
	@ApiOperation(value = "广告位新增、修改")
	public ResponseBean adzoneSave(@RequestBody Adzone adzone, @RequestParam("strutsAction") Long strutsAction) throws IOException {
		JSONObject jsonObject = sysparService.validationObject(adzone, 1);
		if (!jsonObject.getBoolean("validate")) {
			return new ResponseBean(400, jsonObject.getString("message"), null);
		}
		if (adzone.getZonetype() == 1) {
			adzone.setZonesetting("1");
		} else if (adzone.getZonetype() == 2) {
			adzone.setZonesetting("2," + adzone.getPoppoptype() + "," + adzone.getPopleft() + "," + adzone.getPoptop()
					+ "," + adzone.getPopcookiehour() + ",");
		} else if (adzone.getZonetype() == 3) {
			adzone.setZonesetting("3," + adzone.getMoveleft() + "," + adzone.getMovetop() + "," + adzone.getMovedelta()
					+ ",");
		} else if (adzone.getZonetype() == 4) {
			adzone.setZonesetting("4," + adzone.getFixedleft() + "," + adzone.getFixedtop() + ",");
		} else if (adzone.getZonetype() == 5) {
			adzone.setZonesetting("5," + adzone.getFloattype() + "," + adzone.getFloatleft() + ","
					+ adzone.getFloattop() + ",");
		} else if (adzone.getZonetype() == 6) {
			adzone.setZonesetting("6");
			adzone.setZonesize("0x0");
		}
		adzone.setEmpid("admin");// TODO
		adzone.setUpdatetime(Tools.getDayTimes());
		if (strutsAction == ADD) {
			adzone.setCreattime(Tools.getDayTimes());
			sysparService.save(adzone);
		} else if (strutsAction == EDIT) {
			sysparService.update(adzone);
		}
		// 更新js
		sysparService.makeAdJS(adzone.getZoneid());
		String message = (strutsAction == ADD ? "新增成功" : "修改成功");

		return new ResponseBean(200, message, null);
	}

	@RequestMapping(value = "/v1/queryAdzone",method = RequestMethod.POST)
	@ApiOperation(value = "获取广告位信息")
	public ResponseBean queryAdzone(@RequestParam("adzoneId") Long adzoneId) {
		return findOneAdzone(adzoneId);
	}

	/**
	 * 根据id获取广告位信息
	 * @param adzoneId
	 * @return
	 */
	private ResponseBean findOneAdzone(Long adzoneId) {
		Adzone adzone = (Adzone) sysparService.get(Adzone.class, adzoneId);
		if (adzone == null) {

			return new ResponseBean(400, "为查找到信息", null);
		} else {
			List canshulist = null;
			if (adzone.getZonetype() != 1 || adzone.getZonetype() != 6) {
				canshulist = Tools.getReturnList(adzone.getZonesetting(), ",");
			}
			if (adzone.getZonetype() == 1 || adzone.getZonetype() == 6) {
				adzone.setPoppoptype("1");
				adzone.setPopleft("100");
				adzone.setPoptop("100");
				adzone.setPopcookiehour("0");

				adzone.setMoveleft("15");
				adzone.setMovetop("200");
				adzone.setMovedelta("0.015");

				adzone.setFixedleft("100");
				adzone.setFixedtop("100");

				adzone.setFloattype("1");
				adzone.setFloatleft("100");
				adzone.setFloattop("100");
			}
			if (adzone.getZonetype() == 2) {

				adzone.setPoppoptype((String) canshulist.get(1));
				adzone.setPopleft((String) canshulist.get(2));
				adzone.setPoptop((String) canshulist.get(3));
				adzone.setPopcookiehour((String) canshulist.get(4));

				adzone.setMoveleft("15");
				adzone.setMovetop("200");
				adzone.setMovedelta("0.015");

				adzone.setFixedleft("100");
				adzone.setFixedtop("100");

				adzone.setFloattype("1");
				adzone.setFloatleft("100");
				adzone.setFloattop("100");

			} else if (adzone.getZonetype() == 3) {

				adzone.setPoppoptype("1");
				adzone.setPopleft("100");
				adzone.setPoptop("100");
				adzone.setPopcookiehour("0");

				adzone.setMoveleft((String) canshulist.get(1));
				adzone.setMovetop((String) canshulist.get(2));
				adzone.setMovedelta((String) canshulist.get(3));

				adzone.setFixedleft("100");
				adzone.setFixedtop("100");

				adzone.setFloattype("1");
				adzone.setFloatleft("100");
				adzone.setFloattop("100");

			} else if (adzone.getZonetype() == 4) {

				adzone.setPoppoptype("1");
				adzone.setPopleft("100");
				adzone.setPoptop("100");
				adzone.setPopcookiehour("0");

				adzone.setMoveleft("15");
				adzone.setMovetop("200");
				adzone.setMovedelta("0.015");

				adzone.setFixedleft((String) canshulist.get(1));
				adzone.setFixedtop((String) canshulist.get(2));

				adzone.setFloattype("1");
				adzone.setFloatleft("100");
				adzone.setFloattop("100");

			} else if (adzone.getZonetype() == 5) {

				adzone.setPoppoptype("1");
				adzone.setPopleft("100");
				adzone.setPoptop("100");
				adzone.setPopcookiehour("0");

				adzone.setMoveleft("15");
				adzone.setMovetop("200");
				adzone.setMovedelta("0.015");

				adzone.setFixedleft("100");
				adzone.setFixedtop("100");

				adzone.setFloattype((String) canshulist.get(1));
				adzone.setFloatleft((String) canshulist.get(2));
				adzone.setFloattop((String) canshulist.get(3));
			}


			return new ResponseBean(200, "成功" , adzone);
		}
	}
}
