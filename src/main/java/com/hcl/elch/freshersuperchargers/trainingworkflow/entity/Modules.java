package com.hcl.elch.freshersuperchargers.trainingworkflow.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name="Modules")
public class Modules  implements Serializable{
	
	@Id
	@Column(name="moduleId")
	long moduleId;
	
	@Column(name = "moduleName")
	private String moduleName;
	
	@Column(name="Exam")
	private String Exam;
	
	@Column(name="groupId")
	private String groupId;
	
	
	/*@ManyToOne()
	@JoinColumn(name = "groupId", referencedColumnName="Id")
	private GroupModuleMaster groupId;*/
	
	//@OneToOne
	//private GroupModuleMaster groupId;
	
	@Column(name="duration")
	private int duration;
	
	@Column(name="POC")
	private String POC;

}
