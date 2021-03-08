package sprint2.repository;

import sprint2.model.Visit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VisitRepository extends MongoRepository<Visit,String> {
    List<Visit> findByPatientId(String patientId);
}