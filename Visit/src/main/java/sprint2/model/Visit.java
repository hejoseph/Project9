package sprint2.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Visit {

    @Id
    private String id;
    private String date;
    private String doctorName;
    private String comment;
    private String patientId;


    public Visit(String id, String date, String doctorName, String comment, String patientId) {
        this.id = id;
        this.date = date;
        this.doctorName = doctorName;
        this.comment = comment;
        this.patientId = patientId;
    }
}
