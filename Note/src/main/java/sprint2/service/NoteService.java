package sprint2.service;

import sprint2.model.Note;
import org.springframework.stereotype.Service;
import sprint2.repository.NoteRepository;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note deleteNote(String id) {
        Note note = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
        noteRepository.delete(note);
        return note;
    }

    public Note findById(String id) {
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
    }

    public List<Note> getNotesFromPatient(String patientId) {
        return noteRepository.findByPatientId(patientId);
    }
}
