package sprint3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import sprint3.model.Note;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NoteService {

    public RestTemplate restTemplate;

    @Value("${note.url}")
    public String serviceUrl;
    private WebClient webClient;

    public NoteService() {
        this.restTemplate = new RestTemplate();

    }

    public List<Note> getNotesFromPatient(String patientId){
        this.webClient = WebClient.create(this.serviceUrl);
        Mono<List<Note>> stream = this.webClient
                .get()
                .uri("/notes?patientId={patientId}", patientId)
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Note>>(){});

        return stream.block();
    }


}
