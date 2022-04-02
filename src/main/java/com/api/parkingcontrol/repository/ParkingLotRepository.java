package com.api.parkingcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parkingcontrol.model.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>{

}
