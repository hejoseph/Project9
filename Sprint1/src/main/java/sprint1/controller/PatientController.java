package sprint1.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import sprint1.model.Patient;
import sprint1.model.PatientDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint1.service.PatientService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class PatientController {

    private static final Logger logger = LogManager.getLogger(PatientController.class);

    private final PatientService patientService;

    public PatientController(PatientService patientService) {this.patientService = patientService;}

    @PostMapping("/patients")
    public ResponseEntity addPatient(@RequestBody PatientDto patientDto) {
        log.info("Request : {}", patientDto);
        patientService.savePatient(patientDto.toPatient());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("/patient/list")
    public String home(Model model)
    {
        model.addAttribute("patients", patientService.findAll());
        return "patient/list";
    }

    //get page form add
    @GetMapping("/patient/add")
    public String addBidForm(Patient p) {
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid Patient patient, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            patientService.savePatient(patient);
            model.addAttribute("patients", patientService.findAll());
            logger.info("patient adding validation successful");
            logger.info(patient.toString());
            return "redirect:/patient/list";
        }
        logger.info("Error on bidlist adding validation, go back to adding form");
        logger.info(patient.toString());
        return "redirect:/patient/add";
    }


    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        logger.info("Bidlist's updating form display successful");
        logger.info(patient.toString());
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updateBid(@PathVariable("id") String id, @Valid Patient patient,
                            BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Bidlist updating failed, go back to updating form display, has errors");
            return "redirect:/patient/update";
        }
        patient.setId(id);
        patientService.savePatient(patient);
        model.addAttribute("patients", patientService.findAll());
        logger.info("Bidlist saved successfuly, model updated");
        logger.info(patient.toString());
        return "redirect:/patient/list";
    }

    @GetMapping("/patient/delete/{id}")
    public String deleteBid(@PathVariable("id") String id, Model model) {
        Patient patient = patientService.deletePatient(id);
        model.addAttribute("patients", patientService.findAll());
        logger.info("Bidlist deleted successfuly, model updated");
        logger.info(patient.toString());
        return "redirect:/patient/list";
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatient() {
        return patientService.findAll();
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide! GpsUtil";
    }

}