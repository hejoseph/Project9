package sprint1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import sprint1.model.Patient;
import sprint1.repository.PatientRepository;

import javax.annotation.PostConstruct;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class PatientControllerTest {

    Patient patient = new Patient("a","b","c","d","e","f");

    @Autowired
    PatientRepository patientDAO;

    @Autowired
    MockMvc mockmvc;

    @PostConstruct
    public void init(){
        patient = patientDAO.save(patient);
    }

    @Test
    public void getPatientList() throws Exception {
        this.mockmvc.perform(get("/patient/list"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addPatient() throws Exception {
        this.mockmvc.perform(get("/patient/add"))
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

//    @Test
//    public void modifyPatient() throws Exception {
//        this.mockmvc.perform(post("/patient/update/"+patient.getId())
//                .param("patient", patient)
////                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/patient/list"))
//                .andDo(MockMvcResultHandlers.print());
//
//
//    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validPatientTest() throws Exception {
        this.mockmvc.perform(post("/patient/validate")
            .content(asJsonString(new Patient("abc","abc","abc", "firstName4", "lastName4", "email4@mail.com")))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
//            .andExpect(status().isCreated())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void updatePatientTest() throws Exception {
        this.mockmvc.perform(post("/patient/update/"+patient.getId())
                .content(asJsonString(new Patient("abc","abc","abc", "firstName4", "lastName4", "email4@mail.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
//            .andExpect(status().isCreated())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void deletePatient() throws Exception {
        this.mockmvc.perform(get("/patient/delete/"+patient.getId()))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/patient/list"))
                .andDo(MockMvcResultHandlers.print());
        Patient patientFound = patientDAO.findOneById(patient.getId());
        assertNull(patientFound);
    }



}
