package sprint3.service;

import org.springframework.stereotype.Service;
import sprint3.model.Note;
import sprint3.model.Patient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ReportService {

    private final String[] triggers = {"Hémoglobine A1C", "Microalbumine","Taille","Poids","Fumeur","Anormal","Cholestérol","Vertige","Rechute", "Réaction","Anticorps"};

    private final NoteService noteService;

    private final PatientService patientService;

    public ReportService(NoteService noteService, PatientService patientService){
        this.noteService = noteService;
        this.patientService = patientService;
    }

    public String generateReport(String patientId){
        List<Note> notes = noteService.getNotesFromPatient(patientId);
        if(notes==null){
            return "None";
        }
        Patient patient = patientService.getPatient(patientId);
        return detectRisk(notes, patient);
    }

    private String detectRisk(List<Note> notes, Patient patient){
        String result = "None";
        String text = "";

        for(Note note : notes){
            text += note.getTitle()+ " " + note.getText();
        }

        String pattern = "";
        for(String s : triggers){
            pattern+="|"+s;
        }
        pattern = pattern.substring(1);

        int nb = getNbMatch(text,pattern);
        int age = calculteAge(patient.getDob());

        if(age>30){
            if(nb>=8){
                result = "Early Onset";
            }else if(nb>=6){
                result = "In Danger";
            }else if(nb>=2){
                result = "Borderline";
            }
        }else {
            if(patient.getGender().equals("M")){
                if(nb>=5){
                    result = "Early Onset";
                }else if(nb>=3){
                    result = "In Danger";
                }
            }

            if(patient.getGender().equals("F")){
                if(nb>=7){
                    result = "Early Onset";
                }else if(nb>=4){
                    result = "In Danger";
                }
            }
        }

        return result;

    }


    /**
     *
     * @param birthDate
     * @return
     */
    public static int calculteAge(String birthDate) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(birthDate, formatter);
        return Period.between(localDate, today).getYears();
    }

    public static int getNbMatch(String line, String pattern) {
        line = line.toLowerCase().trim();
        pattern = pattern.toLowerCase().trim();
        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object.
        Matcher m = r.matcher(line);
        int from = 0;
        int count = 0;
        while (m.find(from)) {
            count++;
            from = m.start() + 1;
        }
        return count;
    }

}
