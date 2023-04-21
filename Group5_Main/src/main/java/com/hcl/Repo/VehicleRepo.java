package com.hcl.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hcl.Beans.Ride;
import com.hcl.Beans.Vehicle;



@Repository
public interface VehicleRepo extends  JpaRepository<Vehicle, Integer> 
{
	@Query("select vehicle from Vehicle vehicle")
	public List<Vehicle> availabelVehicles();

	@Query("update Vehicle vehicle set vehicle.price = ?1 where vehicle.vehicle_Id = ?2")
	public int update(Double price, Integer id);

	@Query("select vehicle from Vehicle vehicle where vehicle.type = ?1")
	public List<Vehicle> getVehicleByType(int vehicleType);

	@Query("select vehicle from Vehicle vehicle where vehicle.vehicle_Id = ?1")
	public List<Vehicle> getVehicleById(int vehicleId);

	
}
