package com.hcl.Controller;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.hcl.Beans.Ride;
import com.hcl.Beans.RideBooking;
import com.hcl.Beans.User;
import com.hcl.Beans.Vehicle;
import com.hcl.Repo.RideBookingRepo;
import com.hcl.Repo.RideRepo;
import com.hcl.Repo.UserRepo;
import com.hcl.Repo.VehicleRepo;
import com.hcl.Validation.Valid;



@RestController
@RequestMapping("/User")
public class UserController 
{
	
	
	@Autowired
	RideRepo rrepo;
	
	@Autowired
	VehicleRepo vrepo;
	
	@Autowired
	UserRepo urepo;
	
	@Autowired
	RideBookingRepo brepo;
	
	@Autowired
	Valid valid;
	
	@Bean
	public RestTemplate restTemplate() 
	{
	    return new RestTemplate();
	}

	
	HttpHeaders headers = new HttpHeaders();
	
	
	
	@PostMapping("/GetRidesByLocations")
	public List<Ride> getRidesByLocations(@RequestParam String source,@RequestParam String destination)
	{
		return rrepo.getRidesByLocations( source, destination);
	}
	
	
	
	@GetMapping("/getRides")
	public List<Ride> getVehicles(@RequestParam int rideType)
	{
		if(rideType == 3)
			return rrepo.orderByPrice();
		else if(rideType == 4 || rideType == 2)
			return rrepo.showRideByType(rideType);
		else
			return null;
		
	}
	
	@PostMapping("/getRidesByType")
	public List<Ride> getRides(@RequestParam String source, @RequestParam String destination, @RequestParam String localDate, @RequestParam int type)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime dateTime = LocalDateTime.parse(localDate, formatter);
		List<Vehicle> vehiclelist = vrepo.getVehicleByType(type);
		List<Ride> rideList = new ArrayList<Ride>();
		for(Vehicle vehicle : vehiclelist)
		{
			System.out.println(vehicle.getVehicle_Id());
			List<Ride> ridelist = rrepo.showRides(source, destination, dateTime, vehicle);
			for(Ride ride: ridelist)
				rideList.add(ride);
			System.out.println(ridelist.size());
		}
		return rideList;
	}
	
	@PostMapping("/BookRide")
	public String bookRide(@RequestParam int rideId, @RequestParam String userId)
	{
		if(valid.rideExists(rideId) && valid.userRepo(userId))
		{
			RideBooking booking = new RideBooking();
			booking.setRide(rrepo.findById(rideId).get());
			booking.setUser(urepo.findById(userId).get());
			booking.setLocaldate(LocalDateTime.now());
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<RideBooking> entity = new HttpEntity<RideBooking>(booking);
			boolean flag =  restTemplate().postForObject("http://localhost:8082/Group5_BookRide_Service", entity, boolean.class);
			if(flag == true)
				return "Booking Success In Waiting State";
			else
				return "Failed to Book";
		}
		else
			return "Invalid User Id or Ride Id";
	}
	
	@GetMapping("/MyBookings")
	public List<RideBooking> getBookings(@RequestParam String userId)
	{
		if(valid.userRepo(userId))
		{
			User user = urepo.getById(userId);
			return brepo.getByUser(user);
		}
		else
			return null;
	}
	
	
	@PostMapping("/CancelRide")
	public String bookRide(@RequestParam int bookingId)
	{
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Integer> entity = new HttpEntity<Integer>(bookingId);
		RideBooking booking =  restTemplate().postForObject("http://localhost:8081/Group5_CancleRide_Service/"+ bookingId, entity, RideBooking.class);
		if(booking != null)
			return "Booking cancellation is successful";
		else
			return "Invalid Booking ID";
	}
	

}
