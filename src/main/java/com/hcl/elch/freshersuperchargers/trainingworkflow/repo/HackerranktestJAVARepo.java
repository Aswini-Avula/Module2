package com.hcl.elch.freshersuperchargers.trainingworkflow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;

public interface HackerranktestJAVARepo  extends JpaRepository<HackerrankTestJAVA, Long>{

	
	
	public HackerrankTestJAVA  getById(long id);


	//public HackerrankTestJAVA getBySapId(long id);

	
		
	
}
