package sprint2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import sprint2.model.Visit;
import sprint2.model.VisitDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint2.service.VisitService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class VisitController {

    private static final Logger logger = LogManager.getLogger(VisitController.class);

    private final VisitService visitService;

    public VisitController(VisitService visitService) {this.visitService = visitService;}

    @PostMapping("/visits")
    public ResponseEntity addVisit(@RequestBody VisitDto visitDto) {
        log.info("Request : {}", visitDto);
        visitService.saveVisit(visitDto.toVisit());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("/visit/list")
    public String home(Model model)
    {
        model.addAttribute("visits", visitService.findAll());
        return "visit/list";
    }

    //get page form add
    @GetMapping("/visit/add")
    public String addBidForm(Visit p) {
        return "visit/add";
    }

    @PostMapping("/visit/validate")
    public String validate(@Valid Visit visit, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            visitService.saveVisit(visit);
            model.addAttribute("visits", visitService.findAll());
            logger.info("visit adding validation successful");
            logger.info(visit.toString());
            return "redirect:/visit/list";
        }
        return "redirect:/visit/add";
    }


    @GetMapping("/visit/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Visit visit = visitService.findById(id);
        model.addAttribute("visit", visit);
        logger.info("Bidlist's updating form display successful");
        logger.info(visit.toString());
        return "visit/update";
    }

    @PostMapping("/visit/update/{id}")
    public String updateBid(@PathVariable("id") String id, @Valid Visit visit,
                            BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Bidlist updating failed, go back to updating form display, has errors");
            return "redirect:/visit/update";
        }
        visit.setId(id);
        visitService.saveVisit(visit);
        model.addAttribute("visits", visitService.findAll());
        logger.info("Bidlist saved successfuly, model updated");
        logger.info(visit.toString());
        return "redirect:/visit/list";
    }

    @GetMapping("/visit/delete/{id}")
    public String deleteVisit(@PathVariable("id") String id, Model model) {
        Visit visit = visitService.deleteVisit(id);
        model.addAttribute("visits", visitService.findAll());
        logger.info("visit deleted successfuly, model updated");
        logger.info(visit.toString());
        return "redirect:/visit/list";
    }

    @GetMapping("/visits")
    public List<Visit> getAllVisit() {
        return visitService.findAll();
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide! GpsUtil";
    }

}
