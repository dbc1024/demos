package com.ectrip.sys.syspar.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.dao.GenericDao;
import com.ectrip.base.dao.idao.IGenericDao;
import com.ectrip.base.service.GenericService;
import com.ectrip.sys.model.syspar.Adzone;
import com.ectrip.sys.syspar.model.Advertisement;
import com.ectrip.upload.model.Upfilev5;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ectrip.base.exception.LogicException;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.Tools;
import com.ectrip.sys.model.syspar.Esyspar;
import com.ectrip.sys.model.syspar.Sysparv5;
import com.ectrip.sys.model.syspar.Sysparv5Id;
import com.ectrip.sys.syspar.dao.ISysparDao;
import com.ectrip.sys.syspar.service.ISysparService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copy Right Information : Ectrip Package : com.ectrip.syspar.service ClassName :SysparService.java JDK version used : jdk1.5 User : likai
 * Version : Modification history :2009-3-22 08:56:38
 */
@Service
public class SysparService extends GenericService implements ISysparService {
	

    ISysparDao sysparDao;

	@Autowired
	private void setSysparDao(ISysparDao sysparDao){
		this.sysparDao = sysparDao;
		super.setGenericDao(sysparDao);
	}



//	@Autowired
//	private IGenericDao genericDao;


    /**
     * *
     * Describe:
     * @see com.ectrip.system.syspar.service.iservice.ISysparService#sysList(java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String)
     * @param pmky
     * @param pmcd
     * @param path
     * @param page
     * @param pagesize
     * @param url
     * @return
     * @author huying
     * Date:2015-6-24
     */
    public PaginationSupport sysList(String pmky,String pmcd,String path,int page,int pagesize,String url){
    	return sysparDao.sysList(pmky, pmcd, path, page, pagesize, url);
    }
    public PaginationSupport SysparQueryList(String pmky, String pmcd, String path, int page, int pagesize, String url) {
	return sysparDao.sysparQueryList(pmky, pmcd, path, page, pagesize, url);
    }
    
    public Sysparv5 getValueById(String key, String code) {
    	return (Sysparv5) sysparDao.get(Sysparv5.class, new Sysparv5Id(
                "NPTK", "01"));
    }

    @Transactional(propagation= Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED,rollbackFor=Exception.class)
    public void save(Adzone adzone){
		sysparDao.save(adzone);
	}

    public List findAll(String pmky, String systp, String type, boolean isvalue, boolean isAll) {
	return sysparDao.findAll(pmky, systp, type, isvalue, isAll);
    }

    public void insert(Sysparv5 sysparv5) throws LogicException {
	String hsql = "";
	String pmcd = "";
	List list = null;
	if (sysparv5.getSystp().equals("0")) {
	    pmcd = "****";
	    hsql = "from Sysparv5 where id.pmky=?";
	    list = this.sysparDao.find(hsql, new Object[] { sysparv5.getPmky().toUpperCase() });
	    if (list == null || list.size() == 0) {
		sysparv5.setId(new Sysparv5Id(sysparv5.getPmky().toUpperCase(), pmcd));
	    } else {
		throw new LogicException("参数重复，请重新确认系统参数");
	    }
	} else {
	    if (sysparv5.getPmcd() == null || sysparv5.getPmcd().equals("")) {
		hsql = "select new map(max(id.pmcd) as maxpmcd) from Sysparv5 where id.pmky=? and systp<>'0'";

		list = this.sysparDao.find(hsql, new Object[] { sysparv5.getPmky() });

		if (((Map<String, Object>) list.get(0)).get("maxpmcd") == null) {
		    pmcd = "0000";
		} else {
		    pmcd = ((Map<String, Object>) list.get(0)).get("maxpmcd").toString();
		}

		if (Tools.validation(pmcd, Tools.NUMBSTRING) == false) {
		    throw new LogicException("该系统参数已有的参数码存在英文，系统不能自动维护其系统码，请根据上下文参数码自行录入系统参数码");
		}
		/**
		 * hsql = "select new map(max(cast( id.pmcd as int )) as maxpmcd) from Sysparv5 where id.pmky=? and systp<>'0'"; list =
		 * this.sysparDao.find(hsql, new Object[] { sysparv5.getPmky() }); if (((Map<String, Object>) list.get(0)).get("maxpmcd") ==
		 * null) { pmcd = "0000"; } else { pmcd = ((Map<String, Object>) list.get(0)).get("maxpmcd").toString(); }
		 */

		int ipmcd = Integer.parseInt(pmcd) + 1;

		if (pmcd.length() == 2) {
		    if (ipmcd < 10) {
			pmcd = "0" + String.valueOf(ipmcd);
		    } else {
			pmcd = String.valueOf(ipmcd);
		    }
		} else if (pmcd.length() == 3) {
		    if (ipmcd < 10) {
			pmcd = "00" + String.valueOf(ipmcd);
		    } else if (ipmcd < 100) {
			pmcd = "0" + String.valueOf(ipmcd);
		    } else {
			pmcd = String.valueOf(ipmcd);
		    }
		} else {

		    if (ipmcd < 10) {
			pmcd = "000" + String.valueOf(ipmcd);
		    } else if (ipmcd < 100) {

			pmcd = "00" + String.valueOf(ipmcd);
		    } else if (ipmcd < 1000) {
			pmcd = "0" + String.valueOf(ipmcd);
		    } else {
			pmcd = String.valueOf(ipmcd);
		    }
		}

		sysparv5.setId(new Sysparv5Id(sysparv5.getPmky().toUpperCase(), pmcd));
	    } else {
		Sysparv5 oldSysparv5 = (Sysparv5) this.sysparDao.get(Sysparv5.class, new Sysparv5Id(sysparv5.getPmky().toUpperCase(),
			sysparv5.getPmcd()));
		if (oldSysparv5 == null) {
		    sysparv5.setId(new Sysparv5Id(sysparv5.getPmky().toUpperCase(), sysparv5.getPmcd()));
		} else {
		    throw new LogicException("参数码重复，请重新确认参数码");
		}
	    }
	}
	this.sysparDao.save(sysparv5);
    }
    public Sysparv5 findOne(String pmky, String pmcd) {
	List list = sysparDao.find("from Sysparv5 where pmky=? and pmcd=?", new Object[] { pmky, pmcd });
	if (list.size() == 0) {
	    return null;
	} else {
	    return (Sysparv5) list.get(0);
	}
    }

    public List findAllQuery(String pmky, String systp, String type, boolean isvalue, boolean isAll) throws Exception {
	List list = new ArrayList();
	List findList = sysparDao.findAll(pmky, systp, type, isvalue, isAll);
	if (findList.size() == 0) {
	    return null;
	} else {
	    Sysparv5 syspar = null;
	    for (int i = 0; i < findList.size(); i++) {
		syspar = new Sysparv5();
		if (i == 0) {
		    Sysparv5 allSyspar = new Sysparv5();
		    allSyspar.setPmcd("0000");// 所有状态
		    allSyspar.setPmva("所有状态");
		    list.add(allSyspar);
		    BeanUtils.populate(syspar, (Map) findList.get(i));
		    list.add(syspar);
		} else {
		    BeanUtils.populate(syspar, (Map) findList.get(i));
		    list.add(syspar);
		}
	    }
	    return list;
	}
    }

    public List findEsyspar(String pmky, String spmcd) {
	List returnList = new ArrayList();
	String hsql = "from Sysparv5 where id.pmky=? and spmcd=? order by id.pmcd";
	List list = sysparDao.find(hsql, new Object[] { pmky, spmcd });
	if (list == null || list.size() == 0) {
	    return null;
	} else {
	    Sysparv5 syspar = null;
	    Esyspar esyspar = null;
	    for (int i = 0; i < list.size(); i++) {
		syspar = new Sysparv5();
		esyspar = new Esyspar();
		syspar = (Sysparv5) list.get(i);
		if (sysparDao.find(hsql, new Object[] { syspar.getId().getPmky(), syspar.getId().getPmcd() }).size() == 0) {
		    esyspar.setHaveson(new Long(0));
		    esyspar.setHasnext(0l);
		} else {
		    esyspar.setHaveson(new Long(1));
		    esyspar.setHasnext(1l);
		}
		esyspar.setPmky(syspar.getId().getPmky());
		esyspar.setPmcd(syspar.getId().getPmcd());
		esyspar.setSpmcd(syspar.getSpmcd());
		esyspar.setPmva(syspar.getPmva());
		returnList.add(esyspar);
	    }
	}
	return returnList;
    }

    public String returnType(String pdtp) {
	if (pdtp.equals("01")) {
	    return "a";
	} else if (pdtp.equals("02")) {
	    return "b";
	} else if (pdtp.equals("03")) {
	    return "c";
	} else if (pdtp.equals("04")) {
	    return "d";
	} else
	    return null;
    }

    public List findOneList(String pmky, String spmcd) {
	return sysparDao.findOneList(pmky, spmcd);
    }

    public Map findDoubleList(String pmky, String spmcd) {
	return sysparDao.findDoubleList(pmky, spmcd);
    }

    public List retrieve(String pmky) {
	return sysparDao.retrieve(pmky);
    }

    public List query(String pmky, String pmcdwhere) {
	return sysparDao.query(pmky, pmcdwhere);
    }

    /**
     * 查看产品种类* Describe:
     * 
     * @see com.ectrip.system.syspar.service.iservice.ISysparService#querypdtpList(java.lang.String)
     * @param pdtp
     * @param type票类
     *            （01基本票，02套票）
     * @return
     * @author huangyuqi Date:2011-9-23
     */
    public List querypdtpList(String pdtp, String type) {
	return sysparDao.querypdtpList(pdtp, type);
    }

    /**
     * 根据类型，名称查询系统参数 Describe:
     * 
     * @auth:huangyuqi
     * @param pmky
     * @param pmva
     * @return return:Sysparv5 Date:2011-10-10
     */
    public Sysparv5 findSysOne(String pmky, String pmva) {
	return sysparDao.findSysOne(pmky, pmva);
    }

    public ISysparDao getSysparDao() {
	return sysparDao;
    }
    
    public PaginationSupport getSeatList(String orid, int page, int pageSize, String url) {
		// TODO Auto-generated method stub
		return sysparDao.getSeatList(orid, page, pageSize, url);
	}

	public List retrieve(String pmky, Long type) {
		return sysparDao.retrieve(pmky,type);
	}


	public JSONObject validationObject(Object object, int type) {
    	boolean validate = false;
		JSONObject jsonObject = new JSONObject();
    	Map<String, Object> map = new HashMap<String, Object>();
		// 1 adzone
		if (type == 1) {
			Adzone adzone = (Adzone) object;
			if (adzone.getZonename() == null || adzone.getZonename().equals("")) {
				jsonObject.put("validate", validate);
				jsonObject.put("message", "adzone.zonename.required");
				return jsonObject;
			}
			if (adzone.getZoneintro() == null || adzone.getZoneintro().equals("")) {
				jsonObject.put("validate", validate);
				jsonObject.put("message", "adzone.zoneintro.required");
				return jsonObject;
			}
			if (adzone.getZonetype() == 2) {
				if (adzone.getPoppoptype() == null || adzone.getPoppoptype().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.poppoptype.required");
					return jsonObject;
				}
				if (adzone.getPopleft() == null || adzone.getPopleft().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.popleft.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getPopleft(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.popleft.error");
						return jsonObject;
					}
				}
				if (adzone.getPoptop() == null || adzone.getPoptop().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.poptop.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getPoptop(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.poptop.error");
						return jsonObject;
					}
				}
				if (adzone.getPopcookiehour() == null || adzone.getPopcookiehour().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.popcookiehour.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getPopcookiehour(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.popcookiehour.error");
						return jsonObject;
					}
				}
			}
			if (adzone.getZonetype() == 3) {
				if (adzone.getMoveleft() == null || adzone.getMoveleft().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.moveleft.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getMoveleft(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.moveleft.error");
						return jsonObject;
					}
				}
				if (adzone.getMovetop() == null || adzone.getMovetop().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.movetop.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getMovetop(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.movetop.error");
						return jsonObject;
					}
				}
				if (adzone.getMovedelta() == null || adzone.getMovedelta().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.movedelta.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getMovedelta(), Tools.ZHENG_DOUBLE) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.movedelta.error");
						return jsonObject;
					} else {
						if (Double.valueOf(adzone.getMovedelta()).doubleValue() > 1.0
								|| Double.valueOf(adzone.getMovedelta()).doubleValue() < 0.0) {
							jsonObject.put("validate", validate);
							jsonObject.put("message", "adzone.movedelta.error1");
							return jsonObject;
						}
					}
				}
			}
			if (adzone.getZonetype() == 4) {
				if (adzone.getFixedleft() == null || adzone.getFixedleft().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.fixedleft.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getFixedleft(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.fixedleft.error");
						return jsonObject;
					}
				}
				if (adzone.getFixedtop() == null || adzone.getFixedtop().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.fixedtop.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getFixedtop(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.fixedtop.error");
						return jsonObject;
					}
				}
			}
			if (adzone.getZonetype() == 5) {
				if (adzone.getFloattype() == null || adzone.getFloattype().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.floattype.required");
					return jsonObject;
				}
				if (adzone.getFloatleft() == null || adzone.getFloatleft().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.floatleft.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getFloatleft(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.floatleft.error");
						return jsonObject;
					}
				}
				if (adzone.getFloattop() == null || adzone.getFloattop().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.floattop.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getFloattop(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.floattop.error");
						return jsonObject;
					}
				}
			}
			if (adzone.getZonetype() == 6) {
				if (adzone.getZonewidth() == null || adzone.getZonewidth().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.zonewidth.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getZonewidth(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.zonewidth.error");
						return jsonObject;
					}
				}
				if (adzone.getZoneheight() == null || adzone.getZoneheight().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "adzone.zoneheight.required");
					return jsonObject;
				} else {
					if (Tools.validation(adzone.getZoneheight(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "adzone.zoneheight.error");
						return jsonObject;
					}
				}
			}
		} else {
			Advertisement ad = (Advertisement) object;
			if (ad.getAdname() == null || ad.getAdname().equals("")) {
				jsonObject.put("validate", validate);
				jsonObject.put("message", "advertisement.adname.required");
				return jsonObject;
			}
			if (String.valueOf(ad.getZoneid()) == null || String.valueOf(ad.getZoneid()).equals("")) {
				jsonObject.put("validate", validate);
				jsonObject.put("message", "advertisement.zoneid.required");
				return jsonObject;
			}
			if (ad.getAdtype() == 1) {
				if (ad.getStrimgurl() == null || ad.getStrimgurl().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.strimgurl.required");
					return jsonObject;
				}
				if (ad.getImgwidth() == null || ad.getImgwidth().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.imgwidth.required");
					return jsonObject;
				} else {
					if (Tools.validation(ad.getImgwidth(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "advertisement.imgwidth.error");
						return jsonObject;
					}
				}
				if (ad.getImgheight() == null || ad.getImgheight().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.imgheight.required");
					return jsonObject;
				} else {
					if (Tools.validation(ad.getImgheight(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "advertisement.imgheight.error");
						return jsonObject;
					}
				}
				if (ad.getAdintro() == null || ad.getAdintro().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.adintro.required");
					return jsonObject;
				}
			}
			if (ad.getAdtype() == 2) {
				if (ad.getStrflashurl() == null || ad.getStrflashurl().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.strflashurl.required");
					return jsonObject;
				}
				if (ad.getFlashwidth() == null || ad.getFlashwidth().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.flashwidth.required");
					return jsonObject;
				} else {
					if (Tools.validation(ad.getFlashwidth(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "advertisement.flashwidth.error");
						return jsonObject;
					}
				}
				if (ad.getFlashheight() == null || ad.getFlashheight().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.flashheight.required");
					return jsonObject;
				} else {
					if (Tools.validation(ad.getFlashheight(), Tools.ZHENG_INTEGER) == false) {
						jsonObject.put("validate", validate);
						jsonObject.put("message", "advertisement.flashheight.error");
						return jsonObject;
					}
				}
			}
			if (ad.getAdtype() == 3) {
				if (ad.getAdtext() == null || ad.getAdtext().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.adtext.required");
					return jsonObject;
				}
			}
			if (ad.getAdtype() == 4) {
				if (ad.getAdcode() == null || ad.getAdcode().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.adcode.required");
					return jsonObject;
				}
			}
			if (ad.getAdtype() == 5) {
				if (ad.getWebfileurl() == null || ad.getWebfileurl().equals("")) {
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.webfileurl.required");
					return jsonObject;
				}
			}
			if(ad.getPriority()==null||ad.getPriority().equals("")){
				jsonObject.put("validate", validate);
				jsonObject.put("message", "advertisement.priority.required");
				return jsonObject;
			}else{
				Pattern p = Pattern.compile("^[1-9][0-9]*$");
				boolean b = p.matcher(ad.getPriority().toString()).matches();
				if(b==false){
					jsonObject.put("validate", validate);
					jsonObject.put("message", "advertisement.priority.error");
					return jsonObject;
				}
			}
		}
		jsonObject.put("validate", true);
		jsonObject.put("message", "成功");
		return jsonObject;
	}

	public void makeAdJS(long zoneid) throws IOException {
		String path = "/system/syspar/ad/adjs/";// 生成广告JS的绝对路径
		String templatePath = "/system/syspar/ad/adtemplate/";
		String fileContent = "";// 模板内容
		StringBuffer stringBuffer = new StringBuffer();
		Adzone adzone = (Adzone) this.get(Adzone.class, zoneid);
		if (adzone == null) {
			return;
		} else {
			// 广告版位停用，生成相应广告版位的JS js内容为空
			if (adzone.getActive() == 0) {
				Tools.mikeFile(path, adzone.getZoneid() + ".js", "");
				return;
			}
			// 广告版位启用，生成相应广告版位的JS
			else {
				List advertisementlist = this
						.find(
								"from Advertisement where zoneid=? and passed=1 order by priority desc,updatetime desc,creattime desc,adid",
								new Object[] { adzone.getZoneid() });
				if (advertisementlist.size() == 0) {
					Tools.mikeFile(path, adzone.getZoneid() + ".js", "");
					return;
				} else {
					String ZoneAd = "ZoneAD_" + adzone.getZoneid();
					List canshulist = null;
					// 矩形广告版位
					if (adzone.getZonetype() == 1) {
						// adzone 信息
						stringBuffer
								.append("var " + ZoneAd
										+ " = new BannerZoneAD(\"" + ZoneAd
										+ "\");\n");
						stringBuffer.append(ZoneAd + ".ZoneID = "
								+ adzone.getZoneid() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneWidth = "
								+ adzone.getZonewidth() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneHeight = "
								+ adzone.getZoneheight() + ";\n");
						stringBuffer.append(ZoneAd + ".ShowType = "
								+ adzone.getShowtype() + ";\n");
						stringBuffer.append("\n");
						// adzone 信息

						fileContent = Tools.fileReader(templatePath
								+ "Template_Banner.js");
					}
					// 弹出广告版位
					if (adzone.getZonetype() == 2) {
						// adzone 信息
						stringBuffer.append("var " + ZoneAd
								+ " = new PopZoneAD(\"" + ZoneAd + "\");\n");
						stringBuffer.append(ZoneAd + ".ZoneID = "
								+ adzone.getZoneid() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneWidth = "
								+ adzone.getZonewidth() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneHeight = "
								+ adzone.getZoneheight() + ";\n");
						stringBuffer.append(ZoneAd + ".ShowType = "
								+ adzone.getShowtype() + ";\n");

						canshulist = Tools.getReturnList(adzone
								.getZonesetting(), ",");

						stringBuffer.append(ZoneAd + ".PopType = "
								+ (String) canshulist.get(1) + ";\n");
						stringBuffer.append(ZoneAd + ".Left = "
								+ (String) canshulist.get(2) + ";\n");
						stringBuffer.append(ZoneAd + ".Top = "
								+ (String) canshulist.get(3) + ";\n");
						stringBuffer.append(ZoneAd + ".CookieHour = "
								+ (String) canshulist.get(4) + ";\n");
						stringBuffer.append("\n");
						// adzone 信息

						fileContent = Tools.fileReader(templatePath
								+ "Template_Pop.js");
					}
					// 随屏移动广告版位
					if (adzone.getZonetype() == 3) {
						// adzone 信息
						stringBuffer.append("var " + ZoneAd
								+ " = new MoveZoneAD(\"" + ZoneAd + "\");\n");
						stringBuffer.append(ZoneAd + ".ZoneID = "
								+ adzone.getZoneid() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneWidth = "
								+ adzone.getZonewidth() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneHeight = "
								+ adzone.getZoneheight() + ";\n");
						stringBuffer.append(ZoneAd + ".ShowType = "
								+ adzone.getShowtype() + ";\n");

						canshulist = Tools.getReturnList(adzone
								.getZonesetting(), ",");

						stringBuffer.append(ZoneAd + ".Left = "
								+ (String) canshulist.get(1) + ";\n");
						stringBuffer.append(ZoneAd + ".Top = "
								+ (String) canshulist.get(2) + ";\n");
						stringBuffer.append(ZoneAd + ".Delta = "
								+ (String) canshulist.get(3) + ";\n");
						stringBuffer.append("\n");
						// adzone 信息

						fileContent = Tools.fileReader(templatePath
								+ "Template_Move.js");

					}
					// 固定广告版版位
					if (adzone.getZonetype() == 4) {
						// adzone 信息
						stringBuffer.append("var " + ZoneAd
								+ " = new FixedZoneAD(\"" + ZoneAd + "\");\n");
						stringBuffer.append(ZoneAd + ".ZoneID = "
								+ adzone.getZoneid() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneWidth = "
								+ adzone.getZonewidth() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneHeight = "
								+ adzone.getZoneheight() + ";\n");
						stringBuffer.append(ZoneAd + ".ShowType = "
								+ adzone.getShowtype() + ";\n");

						canshulist = Tools.getReturnList(adzone
								.getZonesetting(), ",");

						stringBuffer.append(ZoneAd + ".Left = "
								+ (String) canshulist.get(1) + ";\n");
						stringBuffer.append(ZoneAd + ".Top = "
								+ (String) canshulist.get(2) + ";\n");
						stringBuffer.append("\n");
						// adzone 信息

						fileContent = Tools.fileReader(templatePath
								+ "Template_Fixed.js");
					}
					// 飘浮移动广告版位
					if (adzone.getZonetype() == 5) {
						// adzone 信息
						stringBuffer.append("var " + ZoneAd
								+ " = new FloatZoneAD(\"" + ZoneAd + "\");\n");
						stringBuffer.append(ZoneAd + ".ZoneID = "
								+ adzone.getZoneid() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneWidth = "
								+ adzone.getZonewidth() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneHeight = "
								+ adzone.getZoneheight() + ";\n");
						stringBuffer.append(ZoneAd + ".ShowType = "
								+ adzone.getShowtype() + ";\n");

						canshulist = Tools.getReturnList(adzone
								.getZonesetting(), ",");

						stringBuffer.append(ZoneAd + ".FloatType = "
								+ (String) canshulist.get(1) + ";\n");
						stringBuffer.append(ZoneAd + ".Left = "
								+ (String) canshulist.get(2) + ";\n");
						stringBuffer.append(ZoneAd + ".Top = "
								+ (String) canshulist.get(3) + ";\n");
						stringBuffer.append("\n");
						// adzone 信息

						fileContent = Tools.fileReader(templatePath
								+ "Template_Float.js");

					}
					// 文字代码广告版位
					if (adzone.getZonetype() == 6) {
						// adzone 信息
						stringBuffer.append("var " + ZoneAd
								+ " = new CodeZoneAD(\"" + ZoneAd + "\");\n");
						stringBuffer.append(ZoneAd + ".ZoneID = "
								+ adzone.getZoneid() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneWidth = "
								+ adzone.getZonewidth() + ";\n");
						stringBuffer.append(ZoneAd + ".ZoneHeight = "
								+ adzone.getZoneheight() + ";\n");
						stringBuffer.append(ZoneAd + ".ShowType = "
								+ adzone.getShowtype() + ";\n");
						stringBuffer.append("\n");
						// adzone 信息

						fileContent = Tools.fileReader(templatePath
								+ "Template_Code.js");
					}

					// advertisement 信息
					Advertisement advertisement = null;
					for (int i = 0; i < advertisementlist.size(); i++) {
						advertisement = new Advertisement();
						advertisement = (Advertisement) advertisementlist
								.get(i);
						// 图片
						if (advertisement.getImgurl() != 0) {
							Upfilev5 upfile = (Upfilev5) this.get(
									Upfilev5.class, advertisement.getImgurl());
							advertisement.setStrimgurl(upfile.getUpadder()
									+ "m" + upfile.getUpfilename());
						}
						stringBuffer.append("var objAD = new ObjectAD();\n");
						stringBuffer.append("objAD.ADID = "
								+ advertisement.getAdid() + ";\n");
						stringBuffer.append("objAD.ADType = "
								+ advertisement.getAdtype() + ";\n");
						stringBuffer.append("objAD.ADName = \""
								+ advertisement.getAdname() + "\";\n");
						if (advertisement.getImgurl() == 0) {
							stringBuffer.append("objAD.ImgUrl = \"\";\n");
						} else {
							stringBuffer.append("objAD.ImgUrl = \""
									+ advertisement.getStrimgurl() + "\";\n");
						}
						if (advertisement.getImgwidth() == null
								|| advertisement.getImgwidth().equals("")) {
							stringBuffer.append("objAD.ImgWidth =0;\n");
						} else {
							stringBuffer.append("objAD.ImgWidth = "
									+ advertisement.getImgwidth() + ";\n");
						}
						if (advertisement.getImgheight() == null
								|| advertisement.getImgheight().equals("")) {
							stringBuffer.append("objAD.ImgHeight =0;\n");
						} else {
							stringBuffer.append("objAD.ImgHeight = "
									+ advertisement.getImgheight() + ";\n");
						}
						stringBuffer.append("objAD.FlashWmode = "
								+ advertisement.getFlashwmode() + ";\n");

						if (advertisement.getAdtype() == 4) {
							stringBuffer
									.append("objAD.ADIntro = \""
											+ advertisement.getAdintro().trim()
											.replaceAll("\"", "\\\\\"")
											.replaceAll("\r\n", "")
											+ "\";\n");
						} else {
							stringBuffer.append("objAD.ADIntro = \""
									+ advertisement.getAdintro() + "\";\n");
						}

						stringBuffer.append("objAD.LinkUrl = \""
								+ advertisement.getLinkurl() + "\";\n");
						stringBuffer.append("objAD.LinkTarget = "
								+ advertisement.getLinktarget() + ";\n");
						stringBuffer.append("objAD.LinkAlt = \""
								+ advertisement.getLinkalt() + "\";\n");
						stringBuffer.append("objAD.Priority = "
								+ advertisement.getPriority() + ";\n");

						if (advertisement.getCountview() == null
								|| advertisement.getCountview().equals("")) {
							stringBuffer.append("objAD.CountView = 0;\n");
						} else if (advertisement.getCountview().equals("yes")) {
							stringBuffer.append("objAD.CountView = 1;\n");
						}
						if (advertisement.getCountclick() == null
								|| advertisement.getCountclick().equals("")) {
							stringBuffer.append("objAD.CountClick = 0;\n");
						} else if (advertisement.getCountclick().equals("yes")) {
							stringBuffer.append("objAD.CountClick = 1;\n");
						}
						stringBuffer
								.append("objAD.InstallDir = \"/advertisement/\";\n");
						stringBuffer.append("objAD.ADDIR = \"adjs\";\n");

						stringBuffer.append(ZoneAd + ".AddAD(objAD);\n");
						stringBuffer.append("\n");
					}
					// advertisement 信息

					stringBuffer.append(ZoneAd + ".Show();\n");
				}
				String adContent = fileContent.substring(0, fileContent
						.length() - 1)
						+ new String(stringBuffer.toString().getBytes("UTF-8"),"UTF-8");

				Tools.mikeFile(path, adzone.getZoneid() + ".js", adContent);
			}
		}
	}



	@Override
	public List getSysparamsByPmcds(String pmcds) {
		return sysparDao.getSysparamsByPmcds(pmcds);
	}
	@Override
	public List getSysparamsByPmkyAndPmcds(String pmky,String pmcds) {
		return sysparDao.getSysparamsByPmkyAndPmcds(pmky, pmcds);
	}

	public List<Sysparv5> findSysparBypmky(String pmky, String pmcd){
//		List list = sysparDao.find("from Sysparv5 where pmky=? and pmcd=? and isvalue=1", new Object[] { pmky, pmcd });
		return sysparDao.findSysparBypmky(pmky,pmcd);
	}

	@Override
	public List getSysparamsByParms(String pmky, String pmcd, String pmvbs, String spmcd) {
		return sysparDao.getSysparamsByParms(pmky, pmcd,pmvbs,spmcd);
	}

	@Override
	public List find(String query) {
		return sysparDao.find(query);
	}
}
