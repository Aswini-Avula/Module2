package com.hcl.elch.freshersuperchargers.trainingworkflow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hcl.elch.freshersuperchargers.trainingworkflow.entity.Modules;

public interface ModuleRepo extends JpaRepository<Modules, Long>{
	Modules getBymoduleName(String Name);
	Modules getByModuleId(long id);
	
	//Fetching all module id
	@Query(value="SELECT e.moduleId FROM Modules e",nativeQuery = true)
	List<Long> getAllModuleId();

}
