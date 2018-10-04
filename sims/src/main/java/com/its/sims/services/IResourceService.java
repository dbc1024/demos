package com.its.sims.services;

import com.its.sims.model.Resource;

/**
 * Created by csz on 2017/9/13.
 */
public interface IResourceService extends IBaseService<Resource> {

    Long create(Resource resource);
}
