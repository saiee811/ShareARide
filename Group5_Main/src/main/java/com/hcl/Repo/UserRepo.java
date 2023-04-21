package com.hcl.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hcl.Beans.User;



@Repository
public interface UserRepo extends JpaRepository<User, String>
{

	@Query("select user from User user where user.email = ?1 and user.password = ?2")
	public User login(String usermail, String password);

}
