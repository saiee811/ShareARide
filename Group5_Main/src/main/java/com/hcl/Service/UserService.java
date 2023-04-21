package com.hcl.Service;

import com.hcl.Beans.User;


public interface UserService 
{
	public boolean Login(User user);
	User userRegister(User u);
}
