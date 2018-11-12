package com.ectrip.ticket.provider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ectrip.ticket.provider.service.ITripService;

@RestController
@RequestMapping(value="trip")
public class TripController {
	@Autowired
	private ITripService tripService;
	
	@GetMapping(value="v1/getTripByTripids")
	public List getTripByTripids(@RequestParam("tripids") String tripids) {
		return tripService.getTripByTripids(tripids);
	}
}
