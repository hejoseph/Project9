package sprint3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sprint3.model.Note;
import sprint3.model.Patient;

import java.util.Arrays;
import java.util.List;

@Service
public class PatientService {

    public RestTemplate restTemplate;

    @Value("${patient.url}")
    public String serviceUrl;
    private WebClient webClient;

    public PatientService() {
//        this.serviceUrl = "http://localhost:9030";
        this.restTemplate = new RestTemplate();

    }

    public Patient getPatient(String patientId){
//        ResponseEntity<Patient> response = restTemplate.getForEntity(serviceUrl+"/patient?patientId={patientId}", Patient.class,patientId);
//        return response.getBody();

        System.out.println("[Report] try getting patient");
        this.webClient = WebClient.create(this.serviceUrl);
        Mono<Patient> stream = this.webClient
                .get()
                .uri("/patient?patientId={patientId}", patientId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<Patient>(){});

        return stream.block();
    }

}
