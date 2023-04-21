package com.hcl.Service;

import org.springframework.beans.factory.annotation.Autowired;


import com.hcl.Beans.User;

import com.hcl.Repo.UserRepo;

public class UserServiceImpl implements UserService 
{
	
	@Autowired
	UserRepo repo;

	@Override
	public boolean Login(User user) 
	{
		try
		{
			repo.login(user.getEmail(), user.getPassword());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	
	}
	
	
	@Override
	public User userRegister(User u) 
	{
		//u.setPassword(encoder.encode(u.getPassword()));
		return repo.save(u);
	}
	

	/*@Override
	public String Register(User user) 
	{
			try
			{
				repo.save(user);
				return "logged in succesddfully";
			}
			catch(Exception e)
			{
				return "Invalid Credentials";
			}
		
	}*/

}
