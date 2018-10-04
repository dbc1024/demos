package com.its.sims.mapper;

import com.its.sims.model.Resource;

/**
 * Created by csz on 2017/9/13.
 */
public interface ResourceMapper extends BaseMapper<Resource>{


    Long create(Resource resource);

}
