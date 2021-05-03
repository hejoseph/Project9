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

    public List<Note> getNotesFromPatient(String patientId){

        System.out.println("[Report] try getting notes");
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
