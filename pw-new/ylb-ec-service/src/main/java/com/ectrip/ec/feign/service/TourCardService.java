package com.ectrip.ec.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.ectrip.tourcard.TourCardServiceApi;

@FeignClient("tourcard-service")
public interface TourCardService extends TourCardServiceApi{

}
