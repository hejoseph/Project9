package sprint1.service;

import sprint1.model.Patient;
import org.springframework.stereotype.Service;
import sprint1.model.Visit;
import sprint1.repository.PatientRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final VisitService visitService;

    public PatientService(PatientRepository patientRepository, VisitService visitService) {
        this.patientRepository = patientRepository;
        this.visitService = visitService;
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient deletePatient(Integer id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
        patientRepository.delete(patient);
        return patient;
    }

    public Patient findById(Integer id) {
        return patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
    }

    public List<Patient> findPatientsHistory() {
        List<Patient> patients = patientRepository.findAll();
//        HashMap<String, Visit> visits = visitService.getAllVisits();
        for(Patient patient : patients){
            List<Visit> visits = visitService.getVisitsFromPatient(patient.getId()+"");
            patient.setVisits(visits);

        }
        return patients;
    }

    public List<Patient> findPatientHistory(Integer patientId) throws Exception {
        List<Patient> patients = new ArrayList<>();
        Patient p = patientRepository.findById(patientId).orElseThrow(()->new Exception(""));
        if (p != null) {
            patients.add(p);
        }

        for(Patient patient : patients){
            List<Visit> visits = visitService.getVisitsFromPatient(patient.getId()+"");
            patient.setVisits(visits);

        }
        return patients;
    }
}
