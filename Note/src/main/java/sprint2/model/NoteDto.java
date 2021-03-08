package sprint2.model;

import lombok.Data;

@Data
public class NoteDto {
    private String date;
    private String doctorName;
    private String title;
    private String text;

    public Note toNote(){
        return Note.builder().date(this.date)
                .doctorName(this.doctorName)
                .title(this.title)
                .text(this.text)
                .build();
    }


}
