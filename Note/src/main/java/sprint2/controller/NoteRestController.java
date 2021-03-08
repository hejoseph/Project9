package sprint2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sprint2.model.Note;
import sprint2.model.NoteDto;
import sprint2.service.NoteService;

import javax.validation.Valid;
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
    public List<Note> getAllNoteFromVisit(@RequestParam String visitId) {
        return noteService.getNotesFromVisit(visitId);
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from TourGuide! GpsUtil";
    }



}
