package com.hcl.elch.freshersuperchargers.trainingworkflow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestJAVA;
import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.HackerrankTestPython;

public interface HackerranktestPythonRepo extends JpaRepository<HackerrankTestPython, Long>{
	public HackerrankTestPython getById(long id);

	//public HackerrankTestPython getBySapId(Long id);
}
