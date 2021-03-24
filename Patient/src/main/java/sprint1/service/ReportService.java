package sprint1.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sprint1.model.Note;

import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    public RestTemplate restTemplate;
    public String serviceUrl;
//    private final WebClient webClient;

    public ReportService() {
        this.serviceUrl = "http://localhost:8082";
        this.restTemplate = new RestTemplate();
//        this.webClient = WebClient.create(this.serviceUrl);
    }

//    public HashMap<String, Visit> getAllVisits(){
////        Mono<List<Visit>> stream = this.webClient
////                .get()
////                .uri("/visits")
////                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Visit>>(){});
////        return stream.block();
//        ResponseEntity<Visit[]> response = restTemplate.getForEntity(serviceUrl+"/visits", Visit[].class);
//        HashMap<String, Visit> visits = new HashMap<>();
//        for(Visit visit : response.getBody()){
//            visits.put(visit.getPatientId(), visit);
//        }
//        return visits;
//    }

    public String generateReport(String patientId){
        ResponseEntity<String> response = restTemplate.getForEntity(serviceUrl+"/report/patient/{patientId}", String.class,patientId);
        return response.getBody();
    }

}
