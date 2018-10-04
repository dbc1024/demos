package com.its.sims.mapper;

import com.its.sims.model.OrderNumber;
import org.apache.ibatis.annotations.Param;

/**
 * Created by csz on 2017/6/27.
 */
public interface OrderKeyMapper {


    int containOtherDay(@Param("orderDate") String today);


    int trancateKeys();


    int addKey(OrderNumber orderNumber);

}
