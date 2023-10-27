package com.hcl.elch.freshersuperchargers.trainingworkflow.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Assessment;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Modules;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.AssessmentRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.ModuleRepo;

@Service
public class AssessmentService {
	
	@Autowired
	private AssessmentRepo assessmentRepo;

	@Autowired
	private ModuleRepo moduleRepo;
	
	protected final static Logger log = LogManager.getLogger(AssessmentService.class.getName());

	
	public List<String> findAssesmentById(long id) {
		log.info("Fetching Assessment links by module_id .... ");
		if(assessmentRepo.findByModuleId(id).size()==0) {
			log.error("Given module_id doesn't exist in assessment_master table..");
			return assessmentRepo.findAssesment(id);
		}
		else {
			log.info("Assessment link fetched...");
			//System.out.println("id :- "+id +" Assessment "+assessmentRepo.findByModuleId(id));
			return assessmentRepo.findAssesment(id);
			
		}
		
	}
	
	public List<Map<String,Object>> getAllAssesmentLink() {
		log.info("Fetching all Assessment link....");
//		List<Map<String,Object>> ans= new ArrayList<>();
//		List<Modules>module=moduleRepo.findAll();
//		for(int i=0;i<module.size();i++) {
//			Map<String,Object>mp=new HashMap<>();
//			List<String> assesment = assessmentRepo.findAssesment(module.get(i).getModuleId());
//			mp.put("Module_Id", module.get(i).getModuleId());
//			mp.put("links", assesment);
//			ans.add(mp);
//		}
		List<Map<String,Object>> ans= new ArrayList<>();
		List<Long>module=assessmentRepo.findDistinctModuleId();
		for(int i=0;i<module.size();i++) {
			Map<String,Object>mp=new HashMap<>();
			List<String> assesment = assessmentRepo.findAssesment(module.get(i));
			mp.put("Module_Id", module.get(i));
			mp.put("links", assesment);
			ans.add(mp);
		}
		log.info("All Assessment link fetched...");
		return ans;
	}
	
	public Assessment addLinks(Assessment a) {
		// TODO Auto-generated method stub
		try {
		log.info("Adding Assessment links... ");
		if(a==null) {
			log.error("Assessment object is Null... :- {}");
			return a;
		}//else if(moduleRepo.getByModuleId(a.getId())==null) {
//		else if(!moduleRepo.getAllModuleId().contains(a.getModuleId())) {
//			log.error("Given Module Id :- {} does not exist...",a.getModuleId());
//			System.out.println(" Assessment "+moduleRepo.getAllModuleId());
//			
//				return null;
//			}
			log.info("Assessment link is added successfully...");
			return assessmentRepo.save(a);
		
		
		}catch(Exception e) {
			log.error("Exception Occurs while saving Links...",e.toString());
			//System.out.println(" Assessment "+moduleRepo.getAllModuleId());
			return null;
			
		}

		
		
	}

	

}
