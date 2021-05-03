package sprint1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sprint1.model.Note;
import sprint1.model.Patient;
import sprint1.repository.PatientRepository;
import sprint1.service.NoteService;
import sprint1.service.PatientService;
import sprint1.service.ReportService;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@SpringBootTest
////@Transactional
//@AutoConfigureMockMvc(addFilters = false)
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//@WebMvcTest
//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@TestPropertySource(
//        locations = "classpath:application-test.properties")
public class PatientControllerTest {

    Patient patient = new Patient("a","b", "c","d","e","f");

    @MockBean
    PatientRepository patientRepository;
//
    @MockBean
    ReportService reportService;
    @MockBean
    NoteService noteService;
    @Autowired
    PatientService patientService;

    @Autowired
    MockMvc mockmvc;


    @PostConstruct
    public void init() throws Exception {
//        patient = patientDAO.save(patient);

//        given(patientDAO.save(patient));
        patient.setId(1);
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        given(patientRepository.findAll()).willReturn(patientList);
        
        //given(patientService.findPatientHistory(anyInt())).willReturn(patientList);
        given(patientRepository.findById(anyInt())).willReturn(java.util.Optional.ofNullable(patient));

        List<Note> notes = new ArrayList<>();
        notes.add(new Note("1","","","","",""));
        given(noteService.getNotesFromPatient(anyString())).willReturn(notes);


        given(reportService.generateReport(anyString())).willReturn("report Result");
        
        given(patientRepository.findOneById(Mockito.anyInt())).willReturn(null);
    }

    @Test
    public void getPatientList() throws Exception {
        this.mockmvc.perform(get("/patient/list"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addPatientPage() throws Exception {
        this.mockmvc.perform(get("/patient/add"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updatePage() throws Exception {
        given(patientRepository.findById(patient.getId())).willReturn(java.util.Optional.ofNullable(patient));
        this.mockmvc.perform(get("/patient/update/"+patient.getId()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void patientHistory() throws Exception {
        this.mockmvc.perform(get("/patient/history"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void specificPatientHistory() throws Exception {
        this.mockmvc.perform(get("/patient/"+ patient.getId()+"/history"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void generateReportTest() throws Exception {

        this.mockmvc.perform(get("/report/patient/"+patient.getId()))
//                .param("patient", patient)
////                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/patient/list"))
//                .andDo(MockMvcResultHandlers.print());
        ;
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validPatientTest() throws Exception {
        patient = new Patient("a","b", "c","d","e","f");
        String patientString = asJsonString(patient);
        this.mockmvc.perform(post("/patient/validate")
            .content(patientString)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
//            .andExpect(status().isCreated())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void addPatientTest() throws Exception {
        patient = new Patient("a","b", "c","d","e","f");
        String patientString = asJsonString(patient);
        this.mockmvc.perform(post("/patients")
                .content(patientString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
//            .andExpect(status().isCreated())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void updatePatientTest() throws Exception {
        this.mockmvc.perform(post("/patient/update/"+patient.getId())
                .content(asJsonString(patient))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
//            .andExpect(status().isCreated())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void deletePatient() throws Exception {
        given(patientService.findAll()).willReturn(new ArrayList<>());
//        given(patientRepository.deletePatient(anyInt())).willReturn(patient);
        given(noteService.deleteNotesFromPatient(anyString())).willReturn(Boolean.TRUE);
        this.mockmvc.perform(get("/patient/delete/"+patient.getId()))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/patient/list"))
                .andDo(MockMvcResultHandlers.print());
        Patient patientFound = patientRepository.findOneById(patient.getId());
        assertNull(patientFound);
    }



}
