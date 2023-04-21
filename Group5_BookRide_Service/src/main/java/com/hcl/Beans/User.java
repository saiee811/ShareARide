package com.hcl.Beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "user")
public class User 
{
	
	@Id
	private String email;
	private String name;
	private String password;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//  @JoinColumn(name = "user", referencedColumnName = "email")
	private List<RideBooking> bookings;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, String name, String password, List<RideBooking> bookings) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.bookings = bookings;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RideBooking> getBookings() {
		return bookings;
	}

	public void setBookings(List<RideBooking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", password=" + password + ", bookings=" + bookings + "]";
	}
	
	

}
