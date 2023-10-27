package com.hcl.elch.freshersuperchargers.trainingworkflow.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Assessment;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.CamundaUserDTO;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Category;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Feedback;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Modules;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.POC_Feedback;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Task;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.workflow;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.ModuleRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.TaskRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.AssessmentService;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.CamundaService;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.CategoryService;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.FeedbackService;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.ModuleService;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.TaskServiceImpl;
import com.hcl.elch.freshersuperchargers.trainingworkflow.service.WorkflowService;

@RestController
@RequestMapping("/workflow/")
public class MasterController {
	
//	private final RestTemplate restTemplate = new RestTemplate();
//
//	private final String camundaApiUrl = "http://localhost:9002/engine-rest/user";

	protected final static Logger log = LogManager.getLogger(MasterController.class.getName());


	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private WorkflowService workflowService;
	
	@Autowired
	private AssessmentService assessmentService; 
	
	@Autowired
	private ModuleService moduleService; 
	
	@Autowired
	private TaskServiceImpl taskService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private CamundaService camundaService;

//	@GetMapping("/check")
//	public String test() {
//		return "working...";
//	}
	
// *********************************Assessment End Points *********************************************
	@GetMapping("/allAssesments")
	public ResponseEntity<?> getAllAssesmentLink(){
		List<Map<String,Object>> ans=assessmentService.getAllAssesmentLink();
		if(ans.isEmpty()) {
			return ResponseEntity.ok("Record Not Found..!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ans);
	}
	
	@GetMapping("/assesments/{id}")
	public ResponseEntity<?> getAssesmentLinks(@PathVariable long id){
		List<String> ans=assessmentService.findAssesmentById(id);
		if (ans.isEmpty()) {
	        return ResponseEntity.ok("Something went wrong..");
	    }
		return ResponseEntity.status(HttpStatus.OK).body(ans);
	}
	
	//Adding Assessment Links for modules in assessment table
	@PostMapping("/addAssesmentLink")
	public ResponseEntity<?> addAssesmentlinks(@RequestBody Assessment a){
		Assessment assessment= assessmentService.addLinks(a);
		if(assessment==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Wrong....! Not able to add Links");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Assessment links Added successfully...");
	}
// *********************************Category & Workflow End Points *********************************************
	
	//Fetching all categories from Category table
	@GetMapping("/categories")
	public ResponseEntity<?> getAllcategory(){
		List<Category> res=categoryService.getAllCatetory();
		if(res.isEmpty()) {
			return ResponseEntity.ok("Record Not Found..!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(res);		
	}
	
	//Fetching list of modules by category from workflow table
	@GetMapping("/category-module")
	public ResponseEntity<?> getModuleByCat(){
		List<Map<String,Object>>ans= workflowService.getModuleByCat();	
		if(ans.isEmpty()) {
			return ResponseEntity.ok("Record Not Found..!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(ans);	
		
	}
	
	// *********************************Modules End Points *********************************************

	//Fetching All Modules
	@GetMapping("/modules")
	public ResponseEntity<?> getModules(){
		List<Modules> modules= moduleService.getModules();
		if(modules.isEmpty()) {
			return ResponseEntity.ok("Record Not Found in module table..!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(modules);	
		
	}
	
	//Adding New Modules in Module table.
	@PostMapping("/addModules")
	public ResponseEntity<?> addModule(@RequestBody Modules m){
		String modules= moduleService.addModule(m);
		if(modules==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(modules);
		}
		return ResponseEntity.status(HttpStatus.OK).body(modules);	
		
	}
	//Updating Modules
	@PutMapping("/module-update/{id}")
	public ResponseEntity<?> updateModule(@PathVariable long id, @RequestBody Modules m){
		String modules= moduleService.update(m,id);
		if(modules==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(modules);
		}
		return ResponseEntity.status(HttpStatus.OK).body(modules);	
		
	}
	
	// *********************************Task End Points *********************************************
	
	//Fetching all task records
	@GetMapping("/tasks")
	public ResponseEntity<?> getUsersTasks(){
		List<Task> tasks= taskService.getAllTasks();
		if(tasks.isEmpty()) {
			return ResponseEntity.ok("Record Not Found..!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(tasks);
		
		
	}
	//Fetching task record by sap_id
	@GetMapping("/tasks/{id}")
	public ResponseEntity<?> getUsersTasks(@PathVariable long id){
		List<Task> tasks= taskService.getTaskBySap_Id(id);
		if(tasks.isEmpty()) {
			return ResponseEntity.ok("Record Not Found for sap_Id "+id);
		}
		return ResponseEntity.status(HttpStatus.OK).body(tasks);
		
		
	}
	
	//  *********************************FeedBack End Points *********************************************
	
	
	@GetMapping("/taskFeedback")
	public ResponseEntity<?> getTasksFeedback(){
		List<Feedback> feedback= feedbackService.getTaskFeedback();
		if(feedback.isEmpty()) {
			return ResponseEntity.ok("Record Not Found in Table..!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(feedback);
	}

	@GetMapping("/pocFeedback")
	public ResponseEntity<?> getPocFeedback(){
		List<POC_Feedback> feedback= feedbackService.getPocFeedback();
		if(feedback.isEmpty()) {
			return ResponseEntity.ok("Record Not Found in Table!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(feedback);		
	}
	
  //  *********************************Approver/SME End Points *********************************************
	
	//Fetching all Camunda Users
	@GetMapping("/camunda-users")
	public ResponseEntity<String> getAllCamundaUsers() {
		log.info("Fetching Camunda Users...");
		String response = camundaService.getAllCamundaUsers();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

	}
	
	//Fetching all Camunda Groups
	
	@GetMapping("/camunda-groups")
	public ResponseEntity<String> getAllCamundaGroups() {
		log.info("Fetching Camunda Groups...");
		String response = camundaService.getAllCamundaGroups();
		
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(response);

	}
	
	// Adding Camunda Users
	@PostMapping("/add-camundaUser")
	public ResponseEntity<String> addUser(@RequestBody CamundaUserDTO userDto) {
		
			String response=camundaService.addUser(userDto);
			if(response.equals("yes")) {
			//log.info("Camunda User Added Successfully...");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("User added successfully....");
		}
		else {
			//log.error("Exception occurs while adding Camunda user :- {}",ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Exception Occurs while adding User ");
		}
		
	}

	// Update Camunda User

	@PutMapping("/update-camundaUser/{userId}")
	public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody CamundaUserDTO userDto) {
		
			String response=camundaService.updateUser(userId, userDto);
			if(response.equals("yes")) {
				//log.info("Camunda User Updated Successfully...");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Updated successfully....");
			}

			else {
				// log.error("Exception occurs while Updating Camunda user :- {}",ex);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Some Exception Occurs while updating...");
			}
		}
	

	// Delete Camunda User
	@DeleteMapping("/delete-camundaUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable String userId) {
		
			String response=camundaService.deleteUser(userId);
			//log.info("Camunda User Deleted Successfully...");
			if(response.equals("yes"))
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("User Deleted successfully....");

			//log.error("Exception occurs while Deleting Camunda user :- {}",ex);
			else
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some Exception Occurs while deleting..");
		
		
	}

}
