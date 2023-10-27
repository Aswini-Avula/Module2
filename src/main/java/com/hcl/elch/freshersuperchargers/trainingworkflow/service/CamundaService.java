package com.hcl.elch.freshersuperchargers.trainingworkflow.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.CamundaUserDTO;

@Service
public class CamundaService {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String camundaUserUrl = "http://localhost:9002/engine-rest/user";
	private final String camundaGroupUrl = "http://localhost:9002/engine-rest/group";

	protected final static Logger log = LogManager.getLogger(CamundaService.class.getName());


	@Autowired
	private IdentityService identityService;

	public String getAllCamundaUsers() {
		return restTemplate.getForObject(camundaUserUrl, String.class);
	}

	public String getAllCamundaGroups() {
		return restTemplate.getForObject(camundaGroupUrl, String.class);
	}

	public String addUser(CamundaUserDTO userDto) {
		String response="no";
		log.info("Adding Camunda Users...");
		try {
		User user = identityService.newUser(userDto.getUserId());
		user.setPassword(userDto.getPassword());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		// Save the user to the Camunda database
		identityService.saveUser(user);
		response="yes";
		log.info("Camunda-User added Successfully....");
		}catch(Exception e) {
			log.info("Exception occurs while adding Camunda User :- {}",e);
		}
		return response;

	}

	@Transactional
	public String updateUser(String userId, CamundaUserDTO userDto) {
		String response="no";
		try {
			
			User existingUser = identityService.createUserQuery().userId(userId).singleResult();
			if (existingUser != null) {
				response="yes";
//				existingUser.setPassword(userDto.getPassword());
//				existingUser.setFirstName(userDto.getFirstName());
//				existingUser.setLastName(userDto.getLastName());
				
			
				existingUser.setEmail(userDto.getEmail());
				identityService.saveUser(existingUser);
				log.info("Camunda User updated successfully...");
			}else {
				log.error("Camunda User with Id :- {} doesn't exist...!",userId);
			}

		} catch (Exception e) {
			// Handle exceptions, e.g., validation errors or database issues
			log.error("Unable to delete Camunda User...");
			throw new RuntimeException("Error updating user.", e);
		}
		return response;
	}

	@Transactional
	public String deleteUser(String userId) {
		String response="no";
		try {
			
			log.info("Deleting Camunda Users...");
			User existingUser = identityService.createUserQuery().userId(userId).singleResult();
			if (existingUser != null) {
				response="yes";
				identityService.deleteUser(userId);
				log.info("Camunda User with Id :- {} deleted..",existingUser);
			}else {
				log.error("Camunda User with Id :- {} doesn't exist...!",userId);
			}
		} catch (Exception e) {
			// Handle exceptions, e.g., database issues
			log.error("Something went wrong while deleting camunda user :- {}",e);
			throw new RuntimeException("Error deleting user.", e);

		}
		return response;
	}
}
