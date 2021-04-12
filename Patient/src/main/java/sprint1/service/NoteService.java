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
//        this.serviceUrl = "http://localhost:9031";
        this.restTemplate = new RestTemplate();
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

//        ResponseEntity<Note[]> response = restTemplate.getForEntity(serviceUrl+"/notes?patientId={patientId}", Note[].class,patientId);
//        List<Note> notes = Arrays.asList(response.getBody());
//        if(notes.size()==0){
//            return null;
//        }
//        return notes;
    }

}
