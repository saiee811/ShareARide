package com.hcl.Beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Entity
public class Vehicle 
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vehicle_Id;
	private String vehicle_name;
	private Integer  type;
	private Double price;
	private String members;
	
	@JsonIgnore 
	@OneToMany(mappedBy = "vehicle", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "vehicle", referencedColumnName = "id")
	private List<Ride> ridelist;

	public Vehicle() 
	{
		super();
	}

	public Vehicle(Integer vehicle_Id, String vehicle_name, Integer type, Double price, String members,
			List<Ride> ridelist) {
		super();
		this.vehicle_Id = vehicle_Id;
		this.vehicle_name = vehicle_name;
		this.type = type;
		this.price = price;
		this.members = members;
		this.ridelist = ridelist;
	}

	public Integer getVehicle_Id() {
		return vehicle_Id;
	}

	public void setVehicle_Id(Integer vehicle_Id) {
		this.vehicle_Id = vehicle_Id;
	}

	public String getVehicle_name() {
		return vehicle_name;
	}

	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	public List<Ride> getRidelist() {
		return ridelist;
	}

	public void setRidelist(List<Ride> ridelist) {
		this.ridelist = ridelist;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicle_Id=" + vehicle_Id + ", vehicle_name=" + vehicle_name + ", type=" + type + ", price="
				+ price + ", members=" + members + ", ridelist=" + ridelist + "]";
	}

	

}
