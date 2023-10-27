package com.hcl.elch.freshersuperchargers.trainingworkflow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJavaScript;

public interface HackerranktestJavaScriptRepo  extends JpaRepository<HackerrankTestJavaScript, Long>{
	public HackerrankTestJavaScript getById(long id);

	//public HackerrankTestJavaScript getBySapId(long id);

	
}
