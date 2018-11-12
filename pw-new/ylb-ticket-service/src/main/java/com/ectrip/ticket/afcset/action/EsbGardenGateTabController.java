package com.ectrip.ticket.afcset.action;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.ticket.model.provider.Esbscenicareatab;

import io.swagger.annotations.Api;

/**
 * 园门管理接口
 * @author jiyong
 *
 */
@RestController
@RequestMapping(value = "gardenGate")
@Api(tags = "票务管理-园门信息管理")
public class EsbGardenGateTabController {
	
	
	
	/**
     * 获得园门列表
     *
     * @return
     */
    public String gardenGateViewList() {/*
        StringBuffer sb = new StringBuffer(getRequest().getContextPath());

        try {
            // 获取当前登录用户
            Esfemployeetab employee = (Esfemployeetab) getRequest()
                    .getSession().getAttribute("employee");
            String scenics = "";
            if (employee.getCompanytype().equals("02")) {
                scenics = employee.getScenics();
                if (scenics == null || scenics.equals("")) {
                    this.addActionError(getText("esfemployeetab.scenics.required"));
                    return INPUT;
                }
            }

            // 获取当前登录用户所管理的服务商
            List providers = this.esbaccessequiptabService.findListesbticket(scenics);

            if (provider == null || provider.getIscenicid() == null
                    || provider.getIscenicid().equals("")) {
                if (providers != null && providers.size() > 0) {
                    provider = (Esbscenicareatab) providers.get(0);
                }
            }

            if (page == 0) {
                page = 1;
            }

            getRequest().setAttribute("prdlist", providers);

            // 路径后带参数
            if (null != esbgardengatetab && null != esbgardengatetab.getId()
                    && null != esbgardengatetab.getId().getIscenicid()
                    && esbgardengatetab.getId().getIscenicid() != 0
                    && !"".equals(esbgardengatetab.getId().getIscenicid())) {
                sb.append("?esbgardengatetab.id.iscenicid="
                        + esbgardengatetab.getId().getIscenicid());
            }
            if (null != esbgardengatetab
                    && null != esbgardengatetab.getIsgardengateid()
                    && 0 != esbgardengatetab.getIsgardengateid()
                    && !"".equals(esbgardengatetab.getIsgardengateid())) {
                sb.append("&esbgardengatetab.isgardengateid = "
                        + esbgardengatetab.getIsgardengateid());
            }

            this.url = sb.toString();

            // 获取所有园门信息
            this.ps = this.esbgardengatetabService.getGaredenGatePage(
                    this.esbgardengatetab, employee.getScenics(), pageSize,
                    page, url);

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    */ 
    	return null;
    	}
}
