package com.hcl.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.hcl.Beans.RideBooking;
import com.hcl.Beans.RidebookingStatus;
import com.hcl.Repo.RideBookingRepo;

@RestController
public class BookingServiceController 
{
	
	@Autowired
	RideBookingRepo brepo;
	
	@PostMapping("Group5_BookRide_Service") 
	public boolean BookRide(@RequestBody RideBooking book)
	{
		System.out.println("You are in Waiting state");
		book.setStatus(RidebookingStatus.WAITING);
		brepo.save(book);
		return true;
		
	}
	

}
