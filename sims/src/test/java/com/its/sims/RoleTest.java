package com.its.sims;

import com.its.sims.model.Role;
import com.its.sims.services.IRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by csz on 2017/6/5.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class RoleTest {


    @Autowired
    IRoleService roleService;

    @Test
    public void add(){

        Role role = new Role();
        role.setName("Teacher");
        role.setResourceIds("34;6152;6157;");

        roleService.add(role);
    }

    @Test
    public void query(){

        System.out.println(roleService.query(1L));
    }

    @Test
    public void update(){

        Role role = new Role();
        role.setId(1L);
        role.setName("Teacher");
        role.setResourceIds("34;6152;6157;32;");

        roleService.update(role);

    }

    @Test
    public void queryAll(){


    }



}
