package com.its.sims.controller;

import com.its.sims.model.AjaxResult;
import com.its.sims.model.Resource;
import com.its.sims.services.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by csz on 2017/9/13.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    IResourceService resourceService;

    @RequestMapping("/index")
    public String index(){
        return "resource/resource";
    }


    @RequestMapping("/getAll")
    @ResponseBody
    public List<Resource> getAll(){

        List<Resource> resources = resourceService.queryAll(null);

        return resources;
    }

    @ResponseBody
    @RequestMapping("/edit")
    public AjaxResult edit(Resource resource) {
        AjaxResult result = new AjaxResult();

        resourceService.update(resource);
        result.setData(resource);


        return result;

    }

    @ResponseBody
    @RequestMapping("/delete")
    public AjaxResult delete(Long id) {
        AjaxResult result = new AjaxResult();

        resourceService.delete(id);


        return result;
    }

    @ResponseBody
    @RequestMapping("/create")
    public AjaxResult create(Resource resource) {
        AjaxResult result = new AjaxResult();

        //执行完这条语句后，ID已经赋值到resource中，返回值是影响条目数
        Long id = resourceService.create(resource);
        //resource.setId(id);

        result.setData(resource);


        return result;
    }
}
