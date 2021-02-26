package sprint2.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Note {

    @Id
    private String id;
    private String date;
    private String doctorName;
    private String title;
    private String text;

    public Note(String id, String date, String doctorName, String title, String text) {
        this.id = id;
        this.date = date;
        this.doctorName = doctorName;
        this.title = title;
        this.text = text;
    }
}
