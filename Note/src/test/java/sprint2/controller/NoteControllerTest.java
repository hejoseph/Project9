package sprint2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sprint2.model.Note;
import sprint2.repository.NoteRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class NoteControllerTest {

    Note note = new Note("a","b","c","d","e","f");

    @Autowired
    private NoteRepository noteDAO;

    @Autowired
    MockMvc mockmvc;

    @PostConstruct
    public void init(){
        note = noteDAO.save(note);
    }

    @Test
    public void getNoteList() throws Exception {
        this.mockmvc.perform(get("/note/list"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addNote() throws Exception {
        this.mockmvc.perform(get("/note/add"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/html;charset=UTF-8"))
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void modifyNote() throws Exception {
//        this.mockmvc.perform(post("/note/update/"+note.getId())
//                .param("note", note)
////                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/note/list"))
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
    public void validNoteTest() throws Exception {
        this.mockmvc.perform(post("/note/validate")
                .content(asJsonString(new Note("abc","abc","abc", "firstName4", "lastName4", "email4@mail.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
//            .andExpect(status().isCreated())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void updateNoteTest() throws Exception {
        this.mockmvc.perform(post("/note/update/"+note.getId())
                .content(asJsonString(new Note("abc","abc","abc", "firstName4", "lastName4", "email4@mail.com")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
//            .andExpect(status().isCreated())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void deleteNote() throws Exception {
        this.mockmvc.perform(get("/note/delete/"+note.getId()))
//                .andExpect(MockMvcResultMatchers.status().isFound())
//                .andExpect(MockMvcResultMatchers.redirectedUrl("/note/list"))
                .andDo(MockMvcResultHandlers.print());
        Note noteFound = noteDAO.findOneById(note.getId());
        assertNull(noteFound);
    }

    @Test
    public void deleteAllNotesFromPatient() throws Exception {
        noteDAO.save(new Note("","","","","","1"));
        noteDAO.save(new Note("","","","","","1"));
        noteDAO.save(new Note("","","","","","1"));
        noteDAO.save(new Note("","","","","","1"));
        this.mockmvc.perform(get("/delete/notes?patientId="+1))
                .andDo(MockMvcResultHandlers.print());
        List<Note> notes = noteDAO.findByPatientId("1");
        assertEquals(0, notes.size());
    }


}
