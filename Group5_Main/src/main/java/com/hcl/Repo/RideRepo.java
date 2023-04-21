package com.hcl.Repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.Beans.Ride;
import com.hcl.Beans.Vehicle;


@Repository
public interface RideRepo extends JpaRepository<Ride, Integer> {

	@Query("select ride from Ride ride where ride.vehicle.type = ?1")
	public List<Ride> showRideByType(int type);

	@Query("select ride from Ride ride order by ride.vehicle.price")
	public List<Ride> orderByPrice();
	
	
	@Query("select ride from Ride ride where ride.source = ?1 and ride.destination = ?2 and ride.date = ?3 and ride.vehicle = ?4 order by ride.vehicle.price")
	public List<Ride> showRides(String source, String destination, LocalDateTime date, Vehicle vehicle);

	@Query("select ride from Ride ride where ride.source = ?1 and ride.destination = ?2")
	public List<Ride> getRidesByLocations(String source, String destination);
}
