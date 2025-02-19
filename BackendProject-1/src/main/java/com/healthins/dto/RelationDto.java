package com.healthins.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class RelationDto {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long Id;
	    private String  relationName;
	    private String ageOfTheRelation;
	    private String customerId;
	    private String relationPersonName;
	    private String disease;
		public Long getId() {
			return Id;
		}
		public void setId(Long id) {
			Id = id;
		}
		public String getRelationName() {
			return relationName;
		}
		public void setRelationName(String relationName) {
			this.relationName = relationName;
		}
		public String getAgeOfTheRelation() {
			return ageOfTheRelation;
		}
		public void setAgeOfTheRelation(String ageOfTheRelation) {
			this.ageOfTheRelation = ageOfTheRelation;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getRelationPersonName() {
			return relationPersonName;
		}
		public void setRelationPersonName(String relationPersonName) {
			this.relationPersonName = relationPersonName;
		}
		public String getDisease() {
			return disease;
		}
		public void setDisease(String disease) {
			this.disease = disease;
		}

	}



