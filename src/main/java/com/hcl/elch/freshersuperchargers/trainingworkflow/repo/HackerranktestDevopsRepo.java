package com.hcl.elch.freshersuperchargers.trainingworkflow.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;

//import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Assessment;
//import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
//import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;

public interface HackerranktestDevopsRepo extends JpaRepository<HackeerankTestDevops, Long> {

	public HackeerankTestDevops getById(long id);

	//public HackeerankTestDevops getBySapId(long id);
}
