package sprint2.crud;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import sprint2.model.Note;
import sprint2.repository.NoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class NoteTest {

    @Mock
    private NoteRepository noteRepository;

    @Test
    public void noteTest() {
        Note note = new Note("a","b","c","d","e","f");
        given(noteRepository.save(note)).willAnswer(invocation -> invocation.getArgument(0));
// Save
        note = noteRepository.save(note);
        Assert.assertEquals(note.getDoctorName(),"c");
//
//        // Update
        note.setDoctorName("aa");
        given(noteRepository.save(note)).willAnswer(invocation -> invocation.getArgument(0));
        note = noteRepository.save(note);
        Assert.assertEquals(note.getDoctorName(), "aa");
//
//        // Find
        List<Note> list = new ArrayList<>();
        list.add(note);
        given(noteRepository.findAll()).willReturn(list);
        List<Note> listResult = noteRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);
//
//        // Delete
        String id = note.getId();
        noteRepository.delete(note);
        Optional<Note> noteList = noteRepository.findById(id);
        Assert.assertFalse(noteList.isPresent());
    }



}
