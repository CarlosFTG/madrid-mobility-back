package com.madridmobility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madridmobility.services.IBusService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/buses")
public class BusesController {
	
	@Autowired
	IBusService busService;
	
	

}
