package com.api.parkingcontrol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.model.ParkingLot;
import com.api.parkingcontrol.repository.ParkingLotRepository;

@RestController
@RequestMapping("/api/parkinglot")
public class ParkingLotController {
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;

	@GetMapping
	public ResponseEntity<List<ParkingLot>> list() {
		return ResponseEntity.ok(parkingLotRepository.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ParkingLot> add(@RequestBody ParkingLot parkingLot) {
		return ResponseEntity.ok(parkingLotRepository.save(parkingLot));
	}
}
