package com.hcl.elch.freshersuperchargers.trainingworkflow.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CamundaUserDTO {

	private String userId;

	private String password;

	private String firstName;

	private String lastName;
	
	private String email;
	
}
