package sprint1.model;

import lombok.Data;

@Data
public class Note {
    private String id;
    private String date;
    private String doctorName;
    private String title;
    private String text;
    private String visitId;

    public Note(){

    }

    public Note(String id, String date, String doctorName, String title, String text, String visitId) {
        this.id = id;
        this.date = date;
        this.doctorName = doctorName;
        this.title = title;
        this.text = text;
        this.visitId = visitId;
    }
}
