package com.hcl.Controller;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.hcl.Beans.Owner;
import com.hcl.Beans.Ride;
import com.hcl.Beans.RideBooking;
import com.hcl.Beans.RidebookingStatus;
import com.hcl.Beans.User;
import com.hcl.Beans.Vehicle;
import com.hcl.Repo.OwnerRepo;
import com.hcl.Repo.RideBookingRepo;
import com.hcl.Repo.RideRepo;
import com.hcl.Repo.UserRepo;
import com.hcl.Repo.VehicleRepo;
import com.hcl.Service.VehicleService;
import com.hcl.Validation.Valid;



@RestController
@RequestMapping("/Owner")

public class OwnerController 
{
	@Autowired
	VehicleRepo vrepo;
	
	@Autowired
	OwnerRepo orepo;
	
	@Autowired
	Valid valid;
	
	@Autowired
	RideRepo rrepo;
	
	@Autowired
	UserRepo urepo;
	
	@Autowired 
	VehicleService service;
	
	@Autowired
	RideBookingRepo brepo;
	
	
	
	
	
	
	@PostMapping("/OwnerLogin")
	public String login(@RequestParam String usermail, @RequestParam String password)
	{
		Owner own = orepo.login(usermail, password);
		if(own != null)
		{
		    return "You have Loggedin Successfull";
		}
		
		else
			return "Invalid Login Details";
	}
	
	
	@PostMapping(value="/Register",consumes=MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<Owner> addOwner(@RequestBody Owner own) 
	{
		
			Owner own1 =orepo.save(own);
			return new ResponseEntity<>(own1,HttpStatus.CREATED);
				
	}
	
	
	
	@GetMapping("/getVehicles")
	public List<Vehicle> getVehicles()
	{
		
		return vrepo.availabelVehicles();
	}
	
	

	@PutMapping("/UpdateVehicle/{id}")
	public Vehicle updateVehicle(@PathVariable String id,@RequestBody Vehicle vehicle)
	{
		return service.updateVehicle(Integer.parseInt(id), vehicle);
	}

	
	@DeleteMapping("/DeleteVehicle/{id}")
	public String deleteVehicle(@PathVariable Integer id)
	{
		try
		{
			System.out.println(vrepo.findById(id));
			if(valid.isVehicleExists(id) == true)
			{
				//rrepo.deleterides(vrepo.getById(id));
				vrepo.deleteById(id);
				return "Vehicle Deleted Successfully";
			}
			else
				return "Invalid VehicleId";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "failed to delete";
		}
	}
	
	@PostMapping(value="/AddVehicle",consumes=MediaType.APPLICATION_JSON_VALUE)
	public String addVehicle(@RequestBody Vehicle vehicle)
	{
		vrepo.save(vehicle);
		return "Vehicle Added Successfully";
	}
	
	
	
	@PostMapping("/SearchVehicleById")
	public Vehicle searchVehicle(@RequestParam int id)
	{
		if(valid.isVehicleExists(id) == true)
		{
		Vehicle vehicle = vrepo.getById(id);
		 return vehicle;
		}
		else
			return null;
	}
	
	@GetMapping("/getOwner")
	public List<Owner> getOwner()
	{
		
		return orepo.availabelOwners();
	}
	
	@PostMapping("/getVehicleByType")
	public List<Vehicle> getVehiclesByType(@RequestParam int type)
	{
		
		return vrepo.getVehicleByType(type);
	}

		
	@PostMapping("/AddRide")
	public String ride(@RequestParam String source, @RequestParam String destination, @RequestParam int vehicleId, @RequestParam String datetime)
	{
		if(valid.isVehicleExists(vehicleId))
		{
			try
			{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
				LocalDateTime dateTime = LocalDateTime.parse(datetime, formatter);
				Ride ride = new Ride();
				ride.setDate(dateTime);
				ride.setSource(source);
				ride.setDestination(destination);
				ride.setVehicle(vrepo.getById(vehicleId));
				rrepo.save(ride);
				return "Ride Added Successfully";
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return "failed to search";
			}
		}
		else
			return "Invalid Vehicle ID";
	}
	
	
	@GetMapping("/getBookings")
	public List<RideBooking> getBookings()
	{
		return brepo.findAll();
	}
	
	@PostMapping("/UpdateBookingStatus")
	public String updateBooking(@RequestParam int status, @RequestParam int BId)
	{
		if(valid.bookingExist(BId))
		{
		if(status == 1)
		{
			brepo.UpdateStatus(RidebookingStatus.CONFIRMED, BId);
			return "Updated Booking Status as Confirmed";
			
		}
		else if(status == 2)
		{
			brepo.UpdateStatus(RidebookingStatus.CANCELLED, BId);
			return "Updated Booking Status as Cancelled";
		}
		else
			return "Not a valid request";
		}
		else
			return "Booking Id not found";
	}
	
	
	@PostMapping("/CancelRide")
	public String cancelRide(@RequestParam int rideId)
	{
		if(valid.rideExists(rideId))
		{
		brepo.deleteBookings(rrepo.getById(rideId));
		rrepo.deleteById(rideId);
		return "Ride Cancelled Successfully";
		}
		else
			return "Invalid RideId";
	}
	

	@DeleteMapping("/DeleteUser")
	public String deleteUser(@RequestParam String userId)
	{
		if(valid.userRepo(userId))
		{
		urepo.deleteById(userId);
		return "User Deleted Successfully";
		}
		else
			return "User Id not found";
	}
	
	
	@PostMapping("/GetUsers")
	public List<User> getusers()
	{
		return urepo.findAll();
	}
	
	@PostMapping("/logout")
	public String logout(@RequestParam String usermail, @RequestParam String password)
	{
		if(orepo.login(usermail, password) != null)
			return "Logout Successfull";
		else
			return "Logout Failed";
	}
	
	@GetMapping("/getBookingsByStatus")
	public List<RideBooking> getBookingByStatus(@RequestParam int Status)
	{
		return brepo.getBookingByStatus(Status);
	}

}
