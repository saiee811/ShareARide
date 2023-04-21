package com.hcl.Repo;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.hcl.Beans.Ride;
import com.hcl.Beans.RideBooking;
import com.hcl.Beans.RidebookingStatus;
import com.hcl.Beans.User;

@Repository
public interface RideBookingRepo extends JpaRepository<RideBooking, Integer>
{

	@Modifying
	@Transactional
	@Query("update RideBooking booking set booking.status = ?1 where booking.id = ?2")
	public int UpdateStatus(RidebookingStatus status, int id);

	@Modifying
	@Transactional
	@Query("delete from RideBooking booking where  booking.ride = ?1")
	public int deleteBookings(Ride ride);

	@Query("select booking from RideBooking booking where booking.user = ?1")
	public List<RideBooking> getByUser(User user);

	@Query("select booking from RideBooking booking where booking.status = ?1")
	public List<RideBooking> getBookingByStatus(int status);

}
