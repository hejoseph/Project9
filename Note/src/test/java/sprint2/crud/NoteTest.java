package sprint2.crud;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import sprint2.model.Note;
import sprint2.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void noteTest() {
        Note note = new Note("a","b","c","d","e","f");
//
//        // Save
        note = noteRepository.save(note);
        Assert.assertNotNull(note.getId());
        Assert.assertEquals(note.getDoctorName(),"c");
//
//        // Update
        note.setDoctorName("aa");
        note = noteRepository.save(note);
        Assert.assertEquals(note.getDoctorName(), "aa");
//
//        // Find
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
