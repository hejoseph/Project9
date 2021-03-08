package sprint1.repository;

import sprint1.model.Patient;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;


@Component
public interface PatientRepository extends JpaRepository<Patient,Integer> {
}