package sprint1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sprint1.model.Note;
import sprint1.model.Patient;
import sprint1.service.PatientService;

import java.util.List;

@RestController
@Slf4j
public class PatientRestController {
    private PatientService patientService;

    public PatientRestController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    public Patient getAllNoteFromPatient(@RequestParam String patientId) {
        return patientService.getPatient(patientId);
    }


    @RequestMapping("/")
    public String index() {
        return "Patient Controller is up";
    }

}
