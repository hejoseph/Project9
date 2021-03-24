package sprint3.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sprint3.model.Note;
import sprint3.model.Patient;

import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    public RestTemplate restTemplate;
    public String serviceUrl;
//    private final WebClient webClient;

    public PatientService() {
        this.serviceUrl = "http://localhost:8090";
        this.restTemplate = new RestTemplate();
//        this.webClient = WebClient.create(this.serviceUrl);
    }

    public Patient getPatient(String patientId){
        ResponseEntity<Patient> response = restTemplate.getForEntity(serviceUrl+"/patient?patientId={patientId}", Patient.class,patientId);
        return response.getBody();
    }

}
