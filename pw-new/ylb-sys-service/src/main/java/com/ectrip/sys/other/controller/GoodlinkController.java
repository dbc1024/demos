package com.ectrip.sys.other.controller;

import com.ectrip.base.service.iservice.IGenericService;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.model.employee.Esfemployeetab;
import com.ectrip.sys.model.other.Goodlink;
import com.ectrip.sys.model.syspar.Syslog;
import com.ectrip.sys.other.service.iservice.IGoodlinkService;
import com.ectrip.sys.syspar.service.ISysparService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ectrip.ec.model.notebook.Notebook.ADD;
import static com.ectrip.ec.model.notebook.Notebook.EDIT;

@RestController
public class GoodlinkController {

    @Autowired
    private IGoodlinkService goodlinkService;

    @Autowired
    private ISysparService sysparService;

    private ResponseBean responseBean;
    /**
     * 获取网页类型
     * @return
     */
    @RequestMapping("/goodlink/getGoodlinkType")
    @ApiOperation(value = "获取网页类型")
    public ResponseBean getGoodlinkType() {
        // 获取网页类型列表
        List wytp = sysparService.query("WZTP", " systp = 1");
        responseBean = new ResponseBean(200, "成功", wytp );

        return responseBean;
    }

    /**
     * 网页链接保存、修改
     * @return
     * @throws Exception
     */
    @RequestMapping("/goodlink/saveGoodlink")
    @ApiOperation(value = "网页链接保存、修改")
    public ResponseBean saveGoodlink(@RequestParam("strutsAction") Long strutsAction, @RequestParam("goodlink") Goodlink goodlink) throws Exception {

        // 获取当前登录用户
        Syslog syslog = new Syslog();
//        Esfemployeetab employee = (Esfemployeetab) getRequest().getSession()
//                .getAttribute("employee");
//        syslog.setIemployeeid(employee.getIemployeeid());
//        syslog.setSzemployeename(employee.getSzemployeename());

        // 保存数据
        if (strutsAction == ADD) {
            goodlink.setSeq(goodlinkService.getMaxPk("seq", "Goodlink") + 1);
            goodlinkService.addGoodlink(goodlink, syslog);
        }
        if (strutsAction == EDIT) {
            goodlinkService.updateGoodlink(goodlink, syslog);
        }

        responseBean = new ResponseBean(200, "成功", null);
        return responseBean;
    }


    /**
     * 获取页面信息
     *
     * @throws Exception
     *
     */
    @RequestMapping("/goodlink/queryGoodlink")
    @ApiOperation(value = "网页链接保存、修改")
    public ResponseBean queryGoodlink(@RequestParam("seq") Long seq) throws Exception {
        // 判断是否接收到传递参数
        if (seq == null || "".equals(seq)) {
            return new ResponseBean(400, "请输入网页编号", null);
        }
        Goodlink goodlink = this.goodlinkService.getGoodlinkById(seq);

        return new ResponseBean(200, "成功", goodlink);
    }

    @RequestMapping("/goodlink/deleteGoodlink")
    @ApiOperation(value = "删除网页链接")
    public ResponseBean  deleteGoodlink(@RequestParam("goodlink") Goodlink goodlink) throws Exception {
        // 获取当前登录用户
        Syslog syslog = new Syslog();
//        Esfemployeetab employee = (Esfemployeetab) getRequest().getSession()
//                .getAttribute("employee");
//        syslog.setIemployeeid(employee.getIemployeeid());
//        syslog.setSzemployeename(employee.getSzemployeename());

        // 删除
        goodlinkService.deleteGoodlink(goodlink, syslog);

        return new ResponseBean(200, "删除成功", null);

    }



}
