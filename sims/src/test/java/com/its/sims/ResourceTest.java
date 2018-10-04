package com.its.sims;

import com.its.sims.model.Resource;
import com.its.sims.model.Role;
import com.its.sims.services.IResourceService;
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
public class ResourceTest {


    @Autowired
    IResourceService resourceService;




    @Test
    public void query(){

        System.out.println(resourceService.query(1L));
    }

    @Test
    public void update(){

        Resource resource = new Resource();
        resource.setId(1L);
        resource.setName("系统管理");

        resourceService.update(resource);
    }

    @Test
    public void queryAll(){

        System.out.println(resourceService.queryAll(null));
    }



}
