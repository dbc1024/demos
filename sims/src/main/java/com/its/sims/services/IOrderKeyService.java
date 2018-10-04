package com.its.sims.services;

import com.its.sims.model.OrderNumber;
import org.apache.ibatis.annotations.Param;

/**
 * Created by csz on 2017/6/27.
 */
public interface IOrderKeyService {

    int containOtherDay(String today);


    int trancateKeys();


    int addKey(OrderNumber orderNumber);
}
