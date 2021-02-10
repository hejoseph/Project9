package sprint1.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Patient {

    @Id
    private String id;
    private String firstname;
    private String surname;
    private String dob;
    private String address;
    private String phone;
    private String gender;

    public Patient(String id, String firstname, String surname, String dob, String address, String phone, String gender) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }
}
