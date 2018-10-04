package com.its.sims;

import com.its.sims.model.OrderNumber;
import com.its.sims.services.IOrderKeyService;
import com.its.sims.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by csz on 2017/9/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class IOrderKeyServiceTest {

    @Autowired
    IOrderKeyService orderKeyService;

    @Test
    public void orderNumber() throws Exception {

        String today = DateUtil.getToday();

        int count = orderKeyService.containOtherDay(today);
        if(count>0){
            orderKeyService.trancateKeys();
        }

        OrderNumber orderNumber = new OrderNumber(today);
        orderKeyService.addKey(orderNumber);

        System.out.println(orderNumber.getId());

        String orderNum = today+"777"+"00000"+orderNumber.getId();

        System.out.println(orderNum);
    }





}