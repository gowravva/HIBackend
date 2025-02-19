package com.healthins.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthins.model.RegistrationModel;

public interface HealthInsurenceRepository extends JpaRepository<RegistrationModel, Long> {
    Optional<RegistrationModel> findByCustomerId(String customerId);

	Optional<RegistrationModel> findByEmail(String email);

	Optional<RegistrationModel> findByMobileNo(String mobileNo);

	Optional<RegistrationModel> findByMobileNoOrEmail(String mobileNo, String email);
}
