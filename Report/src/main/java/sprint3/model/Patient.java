package sprint3.model;

import lombok.Data;

@Data
public class Patient {
    private Integer id;
    private String firstname;
    private String surname;
    private String dob;
    private String address;
    private String phone;
    private String gender;

    public Patient(){

    }

    public Patient(Integer id, String firstname, String surname, String dob, String address, String phone, String gender) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }
}
