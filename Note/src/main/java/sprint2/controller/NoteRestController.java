package sprint2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import sprint2.model.Note;
import sprint2.service.NoteService;

import java.util.List;

@RestController
@Slf4j
public class NoteRestController {

    private static final Logger logger = LogManager.getLogger(NoteRestController.class);

    private final NoteService noteService;

    public NoteRestController(NoteService noteService) {this.noteService = noteService;}

//    @GetMapping("/notes")
//    public List<Note> getAllNote() {
//        return noteService.findAll();
//    }

    @GetMapping("/notes")
    public List<Note> getAllNoteFromPatient(@RequestParam String patientId) {
        return noteService.getNotesFromPatient(patientId);
    }

    @GetMapping("/delete/notes")
    public Boolean deleteAllNoteFromPatient(@RequestParam String patientId) {
        return noteService.deleteNotesFromPatient(patientId);
    }

    @RequestMapping("/")
    public String index() {
        return "Note Controller is up";
    }



}
