package sprint1.repository;

import sprint1.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PatientRepository extends MongoRepository<Patient,String> {
}