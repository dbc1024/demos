package com.ectrip.ticket.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ticket.provider.service.IVenueareaService;


@RestController
@RequestMapping(value = "venuearea")
public class VenueareaController {
	@Autowired
	private IVenueareaService venueareaService;
	
	@GetMapping(value="v1/getVenueareaByIvenueids")
	public List getVenueareaByIvenueids(@RequestParam("ivenueids") String ivenueids) {
		return venueareaService.getVenueareaByIvenueids(ivenueids);
	}
}
