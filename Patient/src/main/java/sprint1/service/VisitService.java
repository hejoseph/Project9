package sprint1.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sprint1.model.Note;
import sprint1.model.Visit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Service
public class VisitService {

    private final NoteService noteService;

    public RestTemplate restTemplate;
    public String serviceUrl;
//    private final WebClient webClient;

    public VisitService() {
        this.serviceUrl = "http://localhost:8080";
        this.restTemplate = new RestTemplate();
//        this.webClient = WebClient.create(this.serviceUrl);
        this.noteService = new NoteService();
    }

    public HashMap<String, Visit> getAllVisits(){
//        Mono<List<Visit>> stream = this.webClient
//                .get()
//                .uri("/visits")
//                .retrieve().bodyToMono(new ParameterizedTypeReference<List<Visit>>(){});
//        return stream.block();
        ResponseEntity<Visit[]> response = restTemplate.getForEntity(serviceUrl+"/visits", Visit[].class);
        HashMap<String, Visit> visits = new HashMap<>();
        for(Visit visit : response.getBody()){
            visits.put(visit.getPatientId(), visit);
        }
        return visits;
    }

    public List<Visit> getVisitsFromPatient(String patientId){
        ResponseEntity<Visit[]> response = restTemplate.getForEntity(serviceUrl+"/visits?patientId={patientId}", Visit[].class,patientId);
        List<Visit> visits = Arrays.asList(response.getBody());
        if(visits.size()==0){
            return null;
        }

        for(Visit visit : visits){
            List<Note> notes = noteService.getNotesFromVisit(visit.getId());
            visit.setNotes(notes);
        }
        return visits;
    }

}
