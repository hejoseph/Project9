package sprint3.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sprint3.service.ReportService;

@RestController
@Slf4j
public class ReportRestController {

    private final ReportService reportService;

    public ReportRestController(ReportService reportService){
        this.reportService = reportService;
    }

    private static final Logger logger = LogManager.getLogger(ReportRestController.class);

    @RequestMapping("/report/patient/{id}")
    public String generateReport(@PathVariable("id") String patientId){
        return reportService.generateReport(patientId);
    }

}
