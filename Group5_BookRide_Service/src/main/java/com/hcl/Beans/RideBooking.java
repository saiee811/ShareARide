package com.hcl.Beans;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitialize"}) 
public class RideBooking 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id;
	
	@ManyToOne 
	@JoinColumn(name = "user", referencedColumnName = "email")
	private User user;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ride", referencedColumnName = "id")
	private Ride ride;
	
	private LocalDateTime localdate;
	
	private RidebookingStatus status;

	public RideBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RideBooking(int id, com.hcl.Beans.User user, com.hcl.Beans.Ride ride, LocalDateTime localdate,
			RidebookingStatus status) {
		super();
		this.id = id;
		this.user = user;
		this.ride = ride;
		this.localdate = localdate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public LocalDateTime getLocaldate() {
		return localdate;
	}

	public void setLocaldate(LocalDateTime localdate) {
		this.localdate = localdate;
	}

	public RidebookingStatus getStatus() {
		return status;
	}

	public void setStatus(RidebookingStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RideBooking [id=" + id + ", user=" + user + ", ride=" + ride + ", localdate=" + localdate + ", status="
				+ status + "]";
	}
	
	
	
}
