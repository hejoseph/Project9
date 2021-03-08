package sprint1.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Visit {
    private String id;
    private String date;
    private String doctorName;
    private String comment;
    private String patientId;

    private List<Note> notes;

    public Visit(){

    }

    public Visit(String id, String date, String doctorName, String comment) {
        this.id = id;
        this.date = date;
        this.doctorName = doctorName;
        this.comment = comment;
        this.notes = new ArrayList<>();
    }
}
