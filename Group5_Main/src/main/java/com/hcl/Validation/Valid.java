package com.hcl.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.hcl.Repo.OwnerRepo;
import com.hcl.Repo.RideBookingRepo;
import com.hcl.Repo.RideRepo;
import com.hcl.Repo.UserRepo;
import com.hcl.Repo.VehicleRepo;


@Configuration
public class Valid 
{
	@Autowired
	OwnerRepo orepo;
	
	@Autowired
	VehicleRepo vrepo;
	
	@Autowired
	RideRepo rrepo;
	
	@Autowired
	UserRepo urepo;
	
	@Autowired
	 RideBookingRepo brepo;
	
	public boolean bookingExist(int id)
	{
		if(brepo.existsById(id))
			return true;
		else
			return false;
	}
	
	public boolean userRepo(String id)
	{
		if(urepo.existsById(id))
			return true;
		else
			return false;
	}

	
	public boolean rideExists(int rideId)
	{
		if(rrepo.existsById(rideId))
			return true;
		else
			return false;
	}
	
	
	public boolean adminExists(String id)
	{
		if(orepo.existsById(id))
			return true;
		else
			return false;
	}
	
	
	
	public boolean isVehicleExists(int id)
	{
		if( vrepo.existsById(id))
			return true;
		else
			return false;
	}
	

}
