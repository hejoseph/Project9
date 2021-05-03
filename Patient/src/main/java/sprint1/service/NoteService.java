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
public class NoteService {

    public RestTemplate restTemplate;

    @Value("${note.url}")
    public String serviceUrl;
    private WebClient webClient;

    public NoteService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean deleteNotesFromPatient(String patientId){
        this.webClient = WebClient.create(this.serviceUrl);
        Mono<Boolean> stream = this.webClient
                .get()
                .uri("/delete/notes?patientId={patientId}", patientId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<Boolean>(){});

        return stream.block();
    }

    public List<Note> getNotesFromPatient(String patientId){
        System.out.println(this.serviceUrl);
        this.webClient = WebClient.create(this.serviceUrl);
        Mono<List<Note>> stream = this.webClient
                .get()
                .uri("/notes?patientId={patientId}", patientId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Note>>(){});

        return stream.block();

    }

}
