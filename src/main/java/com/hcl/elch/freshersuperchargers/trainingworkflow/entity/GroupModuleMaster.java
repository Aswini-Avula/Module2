package com.hcl.elch.freshersuperchargers.trainingworkflow.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="group_module_master")
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GroupModuleMaster{

		@Id
		@Column(name="Id")
		String Id;
		
		@Column(name = "groupname")
		private String groupname;
		
		/*@Column(name = "groupId")
		private String groupId;*/
		
		public String getId() {
			return Id;
		}

		public void setId(String Id) {
			this.Id = Id;
		}

		public String getGroupname() {
			return groupname;
		}

		public void setGroupname(String groupname) {
			this.groupname = groupname;
		}
		
		
		
		
	
}
