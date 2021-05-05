package sprint1.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sprint1.model.Note;

import java.util.Arrays;
import java.util.List;

@Service
public class ReportService {

    public RestTemplate restTemplate;

    @Value("${report.url}")
    public String serviceUrl;
    private WebClient webClient;

    public ReportService() {
        this.serviceUrl = "http://localhost:9032";
        this.restTemplate = new RestTemplate();
    }

    public String generateReport(String patientId){
        System.out.println("[Patient] try getting report");
        this.webClient = WebClient.create(this.serviceUrl);
        Mono<String> stream = this.webClient
                .get()
                .uri("/report/patient/{patientId}", patientId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<String>(){});

        return stream.block();
    }

}
