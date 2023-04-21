package com.hcl.RequestResponse;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor 
@Builder
public class UserResponse 
{
	String token;
	private String msg;
	
}