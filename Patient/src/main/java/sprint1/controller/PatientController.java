package sprint1.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import sprint1.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint1.service.NoteService;
import sprint1.service.PatientService;
import sprint1.service.ReportService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class PatientController {

    private static final Logger logger = LogManager.getLogger(PatientController.class);

    private final PatientService patientService;
    private final ReportService reportService;
    private final NoteService noteService;

    public PatientController(PatientService patientService, ReportService reportService, NoteService noteService) {
        this.patientService = patientService;
        this.reportService = reportService;
        this.noteService = noteService;
    }

    @PostMapping("/patients")
    public ResponseEntity addPatient(@RequestBody Patient patientDto) {
        log.info("Request : {}", patientDto);
        patientService.savePatient(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("/patient/list")
    public String home(Model model)
    {
        List<Patient> list = patientService.findAll();
        model.addAttribute("patients", list);
        return "patient/list";
    }

    @RequestMapping("/patient/history")
    public String history(Model model)
    {
        model.addAttribute("patients", patientService.findPatientsHistory());
        return "patient/history";
    }

    @RequestMapping("/patient/{id}/history")
    public String patientHistory(@PathVariable("id") Integer id, Model model) throws Exception {
        model.addAttribute("patients", patientService.findPatientHistory(id));
        return "patient/history";
    }

    @RequestMapping("/report/patient/{id}")
    public String generateReport(@PathVariable("id") Integer id, Model model) throws Exception {
        List<Patient> patients = patientService.findPatientHistory(id);
        if(patients.size()==1){
            String result = reportService.generateReport(id+"");
            patients.get(0).setReport(result);
        }
        model.addAttribute("patients", patients);
        return "patient/report";
    }


    //get page form add
    @GetMapping("/patient/add")
    public String addPatientForm(Patient p) {
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid Patient patient, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            patientService.savePatient(patient);
            model.addAttribute("patients", patientService.findAll());
            logger.info("patient adding validation successful");
            return "redirect:/patient/list";
        }
        logger.info("Error on patient list adding validation, go back to adding form");
        return "redirect:/patient/add";
    }


    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        logger.info("Patientlist's updating form display successful");
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id, @Valid Patient patient,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Patientlist updating failed, go back to updating form display, has errors");
            return "redirect:/patient/update";
        }
        patient.setId(id);
        patientService.savePatient(patient);
        model.addAttribute("patients", patientService.findAll());
        logger.info("Patientlist saved successfuly, model updated");
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id, Model model) {
        Patient patient = patientService.deletePatient(id);
        if(patient!=null){
            noteService.deleteNotesFromPatient(patient.getId()+"");
        }
        model.addAttribute("patients", patientService.findAll());
        logger.info("Patientlist deleted successfuly, model updated");
        return "redirect:/patient/list";
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatient() {
        return patientService.findAll();
    }

}
