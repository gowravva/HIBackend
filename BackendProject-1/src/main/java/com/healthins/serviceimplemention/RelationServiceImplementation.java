package com.healthins.serviceimplemention;
import java.util.List;
	import java.util.Optional;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.stereotype.Service;

	import com.healthins.dto.RelationDto;
	import com.healthins.model.RegistrationModel;
	import com.healthins.model.RelationModel;
	import com.healthins.repo.HealthInsurenceRepository;
	import com.healthins.repo.RelationRepository;
	import com.healthins.serviceint.RelationService;

	@Service
	public class RelationServiceImplementation  implements RelationService {

	    @Autowired
	    private RelationRepository repository;

	    @Autowired
	    private HealthInsurenceRepository repo;

	    @Override
	    public ResponseEntity<?> saveHealthins(RelationDto relation) {
	        try {
	            // Check if the customer exists
	            Optional<RegistrationModel> customerData = repo.findByCustomerId(relation.getCustomerId());

	            if (!customerData.isPresent()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("Customer not found for the given ID: " + relation.getCustomerId());
	            }

	            // Check if the relation already exists
	            Optional<RelationModel> existingRelation = repository.findByCustomerIdAndRelationName(
	                    relation.getCustomerId(), relation.getRelationName());

	            if (existingRelation.isPresent()) {
	                return ResponseEntity.status(HttpStatus.CONFLICT)
	                        .body("Relation with the same name already exists for this customer.");
	            }

	            // Save new relation
	            RelationModel relationModel = new RelationModel();
	            relationModel.setCustomerId(relation.getCustomerId());
	            relationModel.setRelationName(relation.getRelationName());
	            relationModel.setAgeOfTheRelation(relation.getAgeOfTheRelation());
	            relationModel.setRelationPersonName(relation.getRelationPersonName());
	            relationModel.setDisease(relation.getDisease());

	            repository.save(relationModel);

	            return ResponseEntity.status(HttpStatus.CREATED).body("Relation saved successfully.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("An error occurred while saving the relation: " + e.getMessage());
	        }
	    }

	    @Override
	    public ResponseEntity<?> getAllRelations() {
	        try {
	            List<RelationModel> relations = repository.findAll();
	            if (relations.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No relation data found.");
	            }
	            return ResponseEntity.status(HttpStatus.OK).body(relations);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("An error occurred while fetching relations: " + e.getMessage());
	        }
	    }

	    @Override
	    public ResponseEntity<?> checkCustomerDetails(RelationDto relation) {
	        try {
	            Optional<RegistrationModel> customerData = repo.findByCustomerId(relation.getCustomerId());

	            if (customerData.isPresent()) {
	                return saveHealthins(relation);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("No customer details found for the given ID.");
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("An error occurred while checking customer details: " + e.getMessage());
	        }
	    }

	    @Override
	    public ResponseEntity<?> customerDetails(String customerId) {
	        try {
	            List<RelationModel> customerData = repository.findByCustomerId(customerId);

	            if (customerData.isEmpty()) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                        .body("No customer details found for the given ID: " + customerId);
	            }
	            return ResponseEntity.status(HttpStatus.OK).body(customerData);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("An error occurred while fetching customer details: " + e.getMessage());
	        }
	    }

		@Override
		public ResponseEntity<?> saveHealthIns(RelationDto relation) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResponseEntity<?> getAllRelations(String customerId) {
			// TODO Auto-generated method stub
			return null;
		}
	}



