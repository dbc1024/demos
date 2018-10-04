package com.its.sims.services.impl;

import com.its.sims.mapper.BaseMapper;
import com.its.sims.mapper.ResourceMapper;
import com.its.sims.model.Resource;
import com.its.sims.model.Student;
import com.its.sims.services.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by csz on 2017/9/13.
 */
@Service
public class ResourceImpl extends BaseImpl<Resource> implements IResourceService {

    @Autowired
    ResourceMapper resourceMapper;

    @Override
    public BaseMapper<Resource> getMapper() {
        return resourceMapper;
    }

    @Override
    public Long create(Resource resource) {
        return resourceMapper.create(resource);
    }
}
