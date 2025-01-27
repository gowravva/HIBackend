package com.healthins.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthins.model.RelationModel;

public interface RelationRepository extends JpaRepository<RelationModel, Long> {
    List<RelationModel> findByCustomerId(String customerId);

    Optional<RelationModel> findByCustomerIdAndRelationName(String customerId, String relationName);
}
