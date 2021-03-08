package sprint2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sprint2.model.Visit;
import sprint2.model.VisitDto;
import sprint2.service.VisitService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class VisitRestController {

    private static final Logger logger = LogManager.getLogger(VisitRestController.class);

    private final VisitService visitService;

    public VisitRestController(VisitService visitService) {this.visitService = visitService;}


//    @GetMapping("/visits")
//    public List<Visit> getAllVisit() {
//        return visitService.findAll();
//    }

    @GetMapping("/visits")
    public List<Visit> getAllVisitFromPatient(@RequestParam String patientId) {
        return visitService.getVisitsFromPatient(patientId);
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide! GpsUtil";
    }

}
