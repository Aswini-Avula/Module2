package com.hcl.elch.freshersuperchargers.trainingworkflow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackeerankTestDevops;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestDE;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Task;

public interface HackerranktestDERepo  extends JpaRepository<HackerrankTestDE, Long> {

	HackerrankTestDE getById(long id);

	//HackerrankTestDE getBySapId(long id);
	

}
