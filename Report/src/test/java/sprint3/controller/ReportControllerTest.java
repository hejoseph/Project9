package sprint3.controller;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sprint3.model.Note;
import sprint3.model.Patient;
import sprint3.service.NoteService;
import sprint3.service.PatientService;
import sprint3.service.ReportService;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ReportControllerTest {

    @Autowired
    MockMvc mockmvc;


    @Mock
    NoteService noteService;

    @Mock
    PatientService patientService;

    ReportService reportService;

//    @PostConstruct
//    public void init(){
//
//    }

    @Test
    public void generateReportTest() throws Exception {
        Note note = new Note("id","date","name","","","1");
        Note note2 = new Note("id","date","name","","","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);
        notes.add(note2);

        Patient patient = new Patient(1,"","","1970-03-01","","","F");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "None");
    }

    @Test
    public void age30AndNb8() throws Exception {
        Note note = new Note("id","date","name","Taille","Taille Taille Taille Taille Taille Taille Taille","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);

        Patient patient = new Patient(1,"","","1970-01-01","","","F");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "Early Onset");
    }

    @Test
    public void age30AndNb6() throws Exception {
        Note note = new Note("id","date","name","Taille","Taille Taille Taille Taille Taille","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);

        Patient patient = new Patient(1,"","","1970-01-01","","","F");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "In Danger");
    }

    @Test
    public void age30AndNb2() throws Exception {
        Note note = new Note("id","date","name","Taille","Taille","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);

        Patient patient = new Patient(1,"","","1970-01-01","","","F");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "Borderline");
    }

    @Test
    public void ageBelow30MaleAndNb5() throws Exception {
        Note note = new Note("id","date","name","Taille","Taille Taille Taille Taille","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);

        Patient patient = new Patient(1,"","","1999-01-01","","","M");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "Early Onset");
    }

    @Test
    public void ageBelow30MaleAndNb3() throws Exception {
        Note note = new Note("id","date","name","Taille","Taille Taille","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);

        Patient patient = new Patient(1,"","","1999-01-01","","","M");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "In Danger");
    }

    @Test
    public void ageBelow30FemaleAndNb7() throws Exception {
        Note note = new Note("id","date","name","Taille","Taille Taille Taille Taille Taille Taille","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);

        Patient patient = new Patient(1,"","","1999-01-01","","","M");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "Early Onset");
    }

    @Test
    public void ageBelow30FemaleAndNb4() throws Exception {
        Note note = new Note("id","date","name","Taille","Taille Taille Taille","1");
        List<Note> notes = new ArrayList<Note>(){};
        notes.add(note);

        Patient patient = new Patient(1,"","","1999-01-01","","","M");

        Mockito.when(noteService.getNotesFromPatient(Mockito.anyString())).thenReturn(notes);
        Mockito.when(patientService.getPatient(Mockito.anyString())).thenReturn(patient);
        reportService = new ReportService(noteService, patientService);
        String result = reportService.generateReport("1");
        assertEquals(result, "In Danger");
    }

}
