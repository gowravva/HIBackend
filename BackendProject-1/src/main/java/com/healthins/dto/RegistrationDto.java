package com.healthins.dto;

public class RegistrationDto {
	    private String fullName;
	    private String mobileNo;
	    private String email;
	    private String gender;
	    private String customerId;
	    
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public RegistrationDto(String fullName, String mobileNo, String email, String gender, String customerId) {
			super();
			this.fullName = fullName;
			this.mobileNo = mobileNo;
			this.email = email;
			this.gender = gender;
			this.customerId = customerId;
		}
		public RegistrationDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	   
	}