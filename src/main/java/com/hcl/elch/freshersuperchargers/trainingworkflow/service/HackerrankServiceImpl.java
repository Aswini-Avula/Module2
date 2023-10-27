package com.hcl.elch.freshersuperchargers.trainingworkflow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestDE;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJavaScript;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestPython;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Task;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDERepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestDevopsRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJAVARepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestJavaScriptRepo;
import com.hcl.elch.freshersuperchargers.trainingworkflow.repo.HackerranktestPythonRepo;

@Service
public class HackerrankServiceImpl {
	
	@Autowired
	private HackerranktestDevopsRepo Drepo;
	
	@Autowired
	private HackerranktestJAVARepo Jrepo;
	
	@Autowired
	private HackerranktestPythonRepo Prepo;
	
	@Autowired
	private HackerranktestJavaScriptRepo Jsrepo;
	
	@Autowired
	private HackerranktestDERepo Derepo;
	
	

	public HackeerankTestDevops getBydId(long id){
	     List<HackeerankTestDevops> l=Drepo.findAll();
	     HackeerankTestDevops h1 = new HackeerankTestDevops();
	     for(HackeerankTestDevops h:l)
	     {
	    	 if(h.getSapId().equals(id))
	    	 {
	    		 h1=h;
	    	 }
	     }
	     return h1; 
	}
	
	public HackerrankTestJAVA getByjId(long id){
		  List<HackerrankTestJAVA> l=Jrepo.findAll();
		  HackerrankTestJAVA h1 = new HackerrankTestJAVA();
		     for(HackerrankTestJAVA h:l)
		     {
		    	 if(h.getSapId().equals(id))
		    	 {
		    		 h1=h;
		    	 }
		     }
		     return h1;
	}
	
	public HackerrankTestPython getBypId(long id){
		List<HackerrankTestPython> l=Prepo.findAll();
		HackerrankTestPython h1 = new HackerrankTestPython();
		     for(HackerrankTestPython h:l)
		     {
		    	 if(h.getSapId().equals(id))
		    	 {
		    		 h1=h;
		    	 }
		     }
		     return h1;
	}
	
	public HackerrankTestJavaScript getByjsId(long id){
		List<HackerrankTestJavaScript> l=Jsrepo.findAll();
		HackerrankTestJavaScript h1 = new HackerrankTestJavaScript();
		     for(HackerrankTestJavaScript h:l)
		     {
		    	 if(h.getSapId().equals(id))
		    	 {
		    		 h1=h;
		    	 }
		     }
		     return h1;
	}
	
	public HackerrankTestDE getBydeId(long id){
		List<HackerrankTestDE> l=Derepo.findAll();
		HackerrankTestDE h1 = new HackerrankTestDE();
		     for(HackerrankTestDE h:l)
		     {
		    	 if(h.getSapId().equals(id))
		    	 {
		    		 h1=h;
		    	 }
		     }
		     return h1;
	}
	
	
	

}
