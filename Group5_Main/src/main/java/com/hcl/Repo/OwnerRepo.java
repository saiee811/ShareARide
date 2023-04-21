package com.hcl.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.hcl.Beans.Owner;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, String> 
{

		@Query("select owner from Owner owner where owner.email = ?1 and owner.password = ?2")
	public Owner login(String usermail, String password);

		@Query("select owner from Owner owner")
		public List<Owner> availabelOwners();
}
