package com.its.sims.services.impl;

import com.its.sims.mapper.OrderKeyMapper;
import com.its.sims.model.OrderNumber;
import com.its.sims.services.IOrderKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by csz on 2017/6/27.
 */
@Service
public class OrderKeyImpl implements IOrderKeyService {

    @Autowired
    OrderKeyMapper orderKeyMapper;

    @Override
    public int containOtherDay(String today) {
        return orderKeyMapper.containOtherDay(today);
    }

    @Override
    public int trancateKeys() {
        return orderKeyMapper.trancateKeys();
    }

    @Override
    public int addKey(OrderNumber orderNumber) {
        return orderKeyMapper.addKey(orderNumber);
    }
}
