package com.ectrip.tourcard.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.ectrip.ec.EcServiceApi;

@FeignClient("ec-service")
public interface EcService extends EcServiceApi{

}
