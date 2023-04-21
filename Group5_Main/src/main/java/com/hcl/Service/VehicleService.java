package com.hcl.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hcl.Beans.Vehicle;
import com.hcl.Repo.VehicleRepo;



@Service
public class VehicleService
{
	@Autowired
	VehicleRepo vrepo;
	
	
	public Vehicle updateVehicle(Integer id,  Vehicle vehicle) 
	{
		Vehicle p=null;

		Optional<Vehicle> v=  vrepo.findById(id);

		if(v.isPresent()) {
			p=	vrepo.save(vehicle);
		}
		return p;

	}
	

	
	
	
}
