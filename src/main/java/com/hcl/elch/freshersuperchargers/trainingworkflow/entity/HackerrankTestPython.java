package com.hcl.elch.freshersuperchargers.trainingworkflow.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "HackerRankTest_Python")

public class HackerrankTestPython {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String Full_Name;
	
	private String Date_Taken;
	
	private String Login_Id;
	
	private Long SapId;
	
	private String ATS_State;
	
	private String Tags;
	
	private String Invited_By;
	
	private String Ip_address;
	
	private String Percentage_Score;
	
	private String Total_Score;
	
	private String Max_Score;
	
	private String Time_Taken;
	
	private String Report_Link;
	
	private String Coding;
	
	private String MCQ;
	
	private String Plagarism;
	
	private String Attempt_Count;
	
	private String Network_Disconnect_Duration;
	
	private String Outof_Window_Duration;
	
	private String Number_Of_Window_Exists;
	
	private String Years_experience;
	
	private String Programming_Score;
	
	private String Mcq_Score;

	private String Excel_Name;
	
	private String Skill;
}
