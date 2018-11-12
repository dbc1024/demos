package com.ectrip.tourcard.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.ectrip.sys.SysparServiceApi;

@FeignClient("sys-service")
public interface SysService extends SysparServiceApi{

}
