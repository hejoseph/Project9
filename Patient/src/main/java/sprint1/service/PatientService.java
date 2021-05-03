package sprint1.service;

import sprint1.model.Note;
import sprint1.model.Patient;
import org.springframework.stereotype.Service;
import sprint1.repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final NoteService noteService;

    public PatientService(PatientRepository patientRepository, NoteService noteService) {
        this.patientRepository = patientRepository;
        this.noteService = noteService;
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
        System.out.println("[PATIENT LIST]");
//        HashMap<String, Visit> visits = noteService.getAllVisits();
        for(Patient patient : patients){
            System.out.println("[Try getting notes]");
            List<Note> notes = noteService.getNotesFromPatient(patient.getId()+"");
            patient.setNotes(notes);
            System.out.println("[notes got it]");
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
            List<Note> notes = noteService.getNotesFromPatient(patient.getId()+"");
            patient.setNotes(notes);

        }
        return patients;
    }

    public Patient getPatient(String patientId) {
        return patientRepository.findById(Integer.parseInt(patientId)).orElseThrow(()->new IllegalArgumentException("patient id="+patientId+" not found"));
    }
}
