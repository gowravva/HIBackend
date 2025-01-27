package com.healthins.repo;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.healthins.model.PaymentModel;

	public interface PaymentRepo extends JpaRepository<PaymentModel, Long> {
	    
	}

	 


