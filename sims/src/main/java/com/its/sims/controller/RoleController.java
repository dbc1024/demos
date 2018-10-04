package com.its.sims.controller;

import com.its.sims.model.AjaxResult;
import com.its.sims.model.Resource;
import com.its.sims.model.Role;
import com.its.sims.services.IResourceService;
import com.its.sims.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by csz on 2017/9/15.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IResourceService resourceService;

    @Autowired
    IRoleService roleService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("role/role");

        List<Role> roles = roleService.queryAll(null);
        mv.addObject("roles",roles);


        return mv;
    }

    @RequestMapping("/create")
    public String create(Role role){

        roleService.add(role);

        return "forward:/role/index";
    }

    @RequestMapping("/permission")
    public String permission(){
        return "permission/permission";
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List<Resource> getAll(Long roleId){

        List<Resource> resources = resourceService.queryAll(null);

        Role role = roleService.query(roleId);
        String resourceIds = role.getResourceIds();

        if(resourceIds != null){
            String[] split = resourceIds.split(";");

            for (int i=0;i<split.length;i++) {

                for (int m=0;m<resources.size();m++) {


                    if(split[i].equals(resources.get(m).getId().toString())){
                        resources.get(m).setChecked(true);
                        break;
                    }
                }
            }
        }



        return resources;
    }

    @ResponseBody
    @RequestMapping("/checkedNodes")
    public AjaxResult checkedNodes(Long roleId, String resourceIds){

        AjaxResult result = new AjaxResult();
        //保存string字符串到数据库
        //add(roleIds);
        Role role = new Role();
        role.setId(roleId);
        role.setResourceIds(resourceIds);
        roleService.update(role);


        return result;

    }
}
