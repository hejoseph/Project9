package sprint2.model;

import lombok.Data;

@Data
public class VisitDto {
    private String date;
    private String doctorName;
    private String comment;

    public Visit toVisit(){
        return Visit.builder().date(this.date)
                .doctorName(this.doctorName)
                .comment(this.comment)
                .build();
    }


}
