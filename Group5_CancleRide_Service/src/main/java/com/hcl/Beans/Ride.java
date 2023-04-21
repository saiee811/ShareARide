package com.hcl.Beans;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
@Entity
public class Ride 
{
	@Id
	private int id;
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime date;
	private String source;
	private String destination;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY) //@ManyToOne relation between ride and vehicle, one vehicle can have many rides
	@JoinColumn(name = "vehicle", referencedColumnName = "id")
	private Vehicle vehicle;

	public Ride() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ride(int id, LocalDateTime date, String source, String destination, Vehicle vehicle) {
		super();
		this.id = id;
		this.date = date;
		this.source = source;
		this.destination = destination;
		this.vehicle = vehicle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", date=" + date + ", source=" + source + ", destination=" + destination
				+ ", vehicle=" + vehicle + "]";
	}
	
	


}
