package sprint2.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import sprint2.model.Note;
import sprint2.model.NoteDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint2.service.NoteService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class NoteController {

    private static final Logger logger = LogManager.getLogger(NoteController.class);

    private final NoteService noteService;

    public NoteController(NoteService noteService) {this.noteService = noteService;}

    @PostMapping("/notes")
    public ResponseEntity addNote(@RequestBody NoteDto noteDto) {
        log.info("Request : {}", noteDto);
        noteService.saveNote(noteDto.toNote());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("/note/list")
    public String home(Model model)
    {
        model.addAttribute("notes", noteService.findAll());
        return "note/list";
    }

    //get page form add
    @GetMapping("/note/add")
    public String addBidForm(Note p) {
        return "note/add";
    }

    @GetMapping("/note/add/{patientId}")
    public String addNoteForPatient(@PathVariable("patientId") String patientId, Note note, Model model) {
        note.setPatientId(patientId);
        model.addAttribute("note",note);
        return "note/add";
    }

    @PostMapping("/note/validate")
    public String validate(@Valid Note note, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            noteService.saveNote(note);
            model.addAttribute("notes", noteService.findAll());
            logger.info("note adding validation successful");
            logger.info(note.toString());
            return "redirect:/note/list";
        }
        return "redirect:/note/add";
    }


    @GetMapping("/note/update/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Note note = noteService.findById(id);
        model.addAttribute("note", note);
        logger.info("Bidlist's updating form display successful");
        logger.info(note.toString());
        return "note/update";
    }

    @PostMapping("/note/update/{id}")
    public String updateNote(@PathVariable("id") String id, @Valid Note note,
                            BindingResult result, Model model) {
        if(result.hasErrors()) {
            logger.info("Bidlist updating failed, go back to updating form display, has errors");
            return "redirect:/note/update";
        }
        note.setId(id);
        noteService.saveNote(note);
        model.addAttribute("notes", noteService.findAll());
        logger.info("Bidlist saved successfuly, model updated");
        logger.info(note.toString());
        return "redirect:/note/list";
    }

    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable("id") String id, Model model) {
        Note note = noteService.deleteNote(id);
        model.addAttribute("notes", noteService.findAll());
        logger.info("note deleted successfuly, model updated");
        logger.info(note.toString());
        return "redirect:/note/list";
    }

}
