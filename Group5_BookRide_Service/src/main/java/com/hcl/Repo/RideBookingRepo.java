package com.hcl.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.hcl.Beans.RideBooking;
import com.hcl.Beans.RidebookingStatus;



public interface RideBookingRepo extends JpaRepository<RideBooking, Integer> 
{
	@Query("update RideBooking bookings set bookings.status = ?1 where bookings.id = ?2")
	public boolean UpdateStatus(RidebookingStatus status, int id);

}
