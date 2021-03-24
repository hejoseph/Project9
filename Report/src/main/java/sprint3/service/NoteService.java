package sprint3.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sprint3.model.Note;
import java.util.Arrays;
import java.util.List;

@Service
public class NoteService {

    public RestTemplate restTemplate;
    public String serviceUrl;
//    private final WebClient webClient;

    public NoteService() {
        this.serviceUrl = "http://localhost:8081";
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

    public List<Note> getNotesFromPatient(String patientId){
        ResponseEntity<Note[]> response = restTemplate.getForEntity(serviceUrl+"/notes?patientId={patientId}", Note[].class,patientId);
        List<Note> notes = Arrays.asList(response.getBody());
        if(notes.size()==0){
            return null;
        }
        return notes;
    }

}