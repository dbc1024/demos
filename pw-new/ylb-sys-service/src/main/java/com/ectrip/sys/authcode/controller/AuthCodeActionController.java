package com.ectrip.sys.authcode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ectrip.base.util.PaginationSupport;
import com.ectrip.base.util.SpringUtil;
import com.ectrip.base.util.Tools;
import com.ectrip.shiro.ResponseBean;
import com.ectrip.sys.authcode.dto.AuthCodeInputDto;
import com.ectrip.sys.authcode.service.iservice.IAuthCodeService;
import com.ectrip.sys.model.authcode.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxinhao on 2017/2/23.
 */
@RestController
@Api(tags = "二次入园接口")
public class AuthCodeActionController {

    @Autowired
    private IAuthCodeService authCodeService;

    private AuthCodeInputDto dto;//查询条件

    ResponseBean responseBean ;
//    public String search() {
//
//        url = getRequest().getContextPath() + getRequest().getServletPath();
//        if (page == 0) {
//            page = 1;
//        }
//        if (dto == null) {
//            dto = new AuthCodeInputDto();
//            dto.setStatus("00");
//            dto.setCodeType("01");
//        }
//        if (!StringUtils.isBlank(dto.getCodeType())) {
//            url += "?dto.codeType=" + dto.getCodeType();
//        }
//        if (!StringUtils.isBlank(dto.getStatus())) {
//            url += "&dto.status=" + dto.getStatus();
//        }
//        if (!StringUtils.isBlank(dto.getSearchName())) {
//            url += "&dto.searchName=" + dto.getSearchName();
//        }
////        Map codeMap = CodeType.convertToMap();
//        Map codeMap = new HashMap();
//        codeMap.put(CodeType.SYSTEM.getCode(),CodeType.SYSTEM.getName());
//        codeMap.put(CodeType.GARDEN.getCode(),CodeType.GARDEN.getName());
//        codeMap.put(CodeType.CHECKMACHINE.getCode(),CodeType.CHECKMACHINE.getName());
//        getRequest().setAttribute("codeMap", codeMap);
//        getRequest().setAttribute("codeName", CodeType.typeOf(dto.getCodeType()).getName());
//        ps = authCodeService.searchAuthCodes(dto, page, pageSize, url);
//        return SUCCESS;
//    }

    /**
     * 跳转授权码添加页面信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/authcode/addSystemCode")
    public ResponseBean addSystemCode(HttpServletRequest request, HttpServletResponse response) {
        AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
        if (authCode != null) {
            responseBean = new ResponseBean(400, "系统已注册过授权码" , null);
        }
        responseBean = new ResponseBean(200, "成功" , JSON.toJSON(authCode));

        return responseBean;
    }

    /**
     * 保存注册授权码
     * @return
     */
    @RequestMapping(name = "/authcode/saveSystemCode",method = RequestMethod.POST)
    @ApiOperation(value = "保存注册授权码")
    public ResponseBean saveSystemCode(@RequestBody JsonMessage jsonMessage) {
        int code = 400;
        String messages = "";
        if (StringUtils.isBlank(jsonMessage.getSystemcode())||jsonMessage.getSystemcode().length() != 32) {
            messages = "请输入32位系统编码";
        }

        try {
            authCodeService.saveSystemCode(jsonMessage);
            code = 200;
            messages = "注册系统授权码成功";
        } catch (Exception e) {
            e.printStackTrace();
            messages = e.getMessage();
        }

        responseBean = new ResponseBean(code, messages, null);
        return responseBean;
    }

    /**
     * 跳转生成批量授权码
     * @return
     */
    @RequestMapping("/authcode/addOtherCode")
    public ResponseBean addOtherCode() {
        int code = 400;
        String messages = "";
        Map codeMap = new HashMap();

        AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
        if (authCode == null) {
            messages = "该系统未注册过授权码";
        }else {
            code = 200;
            codeMap.put(CodeType.SYSTEM.getCode(),CodeType.SYSTEM.getName());
            codeMap.put(CodeType.GARDEN.getCode(),CodeType.GARDEN.getName());
            codeMap.put(CodeType.CHECKMACHINE.getCode(),CodeType.CHECKMACHINE.getName());

        }

        responseBean = new ResponseBean(code , messages, JSON.toJSON(codeMap));

        return responseBean;
    }

    /**
     * 保存批量授权
     * @return
     */
    @RequestMapping("/authcode/saveOtherCode")
    public ResponseBean saveOtherCode() {
        int code = 400;
        String messages = "";
        List list = authCodeService.searchNoAuthItems(dto.getCodeType());
        if (list != null && !list.isEmpty()) {
            AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
            JsonMessage message = new JsonMessage();
            try {
                message = JSON.parseObject(Tools.getStrByBlob(authCode.getJsonMessage()),JsonMessage.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (int i = 0; i < list.size(); i++) {
                Map<String,Object> map = (Map<String, Object>) list.get(i);
                OtherCodePojo pojo = new OtherCodePojo();
                pojo.setCodename(map.get("name").toString());
                pojo.setLength("6");
                pojo.setMake(map.get("code").toString());
                pojo.setPrefix(CodeTypePrefix.typeOf(dto.getCodeType()).getName());
                pojo.setCodetype(dto.getCodeType());
                try{
                    authCodeService.saveOtherCode(authCode.getAuthCode(),message.getSignkey(),pojo);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            code = 200;
            messages = "成功";
        } else {
            messages = "无可授权的数据";
        }

        responseBean = new ResponseBean(code, messages, null);
        return responseBean;
    }
//
//    public String registerCheckMachine(){
//        AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
//        if(authCode == null){
//            this.addActionError("该系统未注册过授权码");
//            return INPUT;
//        }
//        List<AuthCode> authCodes = authCodeService.findAuthCodes(CodeType.CHECKMACHINE.getCode(),"00");
//        try{
//            authCodeService.registerCheckMachine(authCode.getAuthCode(),authCodes);
//        }catch (Exception e){
//            this.addActionError(e.getMessage());
//            return INPUT;
//        }
//        return SUCCESS;
//    }

    /**
     * 跳转批量注册百度设备组
     * @return
     */
    @RequestMapping("/authcode/registerGarden")
    public ResponseBean registerGarden(){
        int code = 400;
        String messages = "";
        AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
        if(authCode == null){
            messages = "该系统未注册过授权码";
        }
        List<AuthCode> authCodes = authCodeService.findAuthCodes(CodeType.GARDEN.getCode(),"00");
        if(authCodes != null && !authCodes.isEmpty()){
            try{
                authCodeService.registerGarden(authCode.getAuthCode(),authCodes);
                code = 200;
                messages = "有可注册园门信息";
            }catch (Exception e){
                messages = e.getMessage();
            }
        }else{
            messages = "无可注册园门信息";
        }

        responseBean = new ResponseBean(code, messages, null);
        return responseBean;
    }

//    public String bindMachine(){
//        AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
//        if(authCode == null){
//            this.addActionError("该系统未注册过授权码");
//            return INPUT;
//        }
//        List<AuthCode> authCodes = authCodeService.findAuthCodes(CodeType.GARDEN.getCode(),"01");
//        try{
//            authCodeService.bindMachine(authCode.getAuthCode(),authCodes);
//        }catch (Exception e){
//            this.addActionError(e.getMessage());
//            return INPUT;
//        }
//        return SUCCESS;
//    }
//
//    public String editCheckMachineCode(){
//        AuthCode authCode = (AuthCode) genericService.get(AuthCode.class,dto.getId());
//        if(authCode == null){
//            this.addActionError("该设备未授权,不可注册");
//            return INPUT;
//        }
//        if(!CodeType.CHECKMACHINE.getCode().equalsIgnoreCase(authCode.getCodeType())){
//            this.addActionError("非检票设备不可注册");
//            return INPUT;
//        }
//        if("01".equalsIgnoreCase(authCode.getBaiduStatus())){
//            this.addActionError("该设备已注册,请勿重复注册");
//            return INPUT;
//        }
//        Esbaccessequiptab acc = authCodeService.findCheckMachine(Long.parseLong(authCode.getOrginId()));
//        if(acc == null){
//            this.addActionError("设备不存在或已删除");
//            return INPUT;
//        }
//        dto.setId(authCode.getId());
//        checkMachineCode = authCode.getAuthCode();
//        getRequest().setAttribute("name",acc.getSzaccessequipname());
//        return SUCCESS;
//    }
//
//    public String saveCheckMachineCode(){
//        AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
//        if(StringUtils.isBlank(checkMachineCode)){
//            this.addActionError("检票设备码不能为空");
//            return INPUT;
//        }
//        AuthCode machineAuthCode = (AuthCode) genericService.get(AuthCode.class,dto.getId());
//        machineAuthCode.setAuthCode(checkMachineCode);
//        try{
//            authCodeService.registerCheckMachine(authCode.getAuthCode(),machineAuthCode);
//        }catch (RuntimeException e1){
//            this.addActionError(e1.getMessage());
//            return INPUT;
//        }catch (Exception e){
//            this.addActionError(e.getMessage());
//            return INPUT;
//        }
//        return SUCCESS;
//    }
//
//    public String cancelCheckMachine(){
//        AuthCode authCode = authCodeService.getAuthCode(CodeType.SYSTEM.getCode(), null);
//        AuthCode machineAuthCode = (AuthCode) genericService.get(AuthCode.class,dto.getId());
//        if(!"01".equalsIgnoreCase(machineAuthCode.getBaiduStatus())){
//            this.addActionError("未注册设备不可注销");
//            return INPUT;
//        }
//        try{
//            authCodeService.cancelCheckMachine(authCode.getAuthCode(),machineAuthCode);
//        }catch (RuntimeException e1){
//            this.addActionError(e1.getMessage());
//            return INPUT;
//        }catch (Exception e){
//            this.addActionError(e.getMessage());
//            return INPUT;
//        }
//        return SUCCESS;
//    }
}
