package sprint1.model;

import lombok.Data;

@Data
public class PatientDto {
    private String id;
    private String firstname;
    private String surname;
    private String dob;
    private String address;
    private String phone;
    private String gender;

    public Patient toPatient(){
        return Patient.builder().firstname(this.firstname)
                .surname(this.surname)
                .dob(this.dob)
                .address(this.address)
                .phone(this.phone)
                .gender(this.gender)
                .build();
    }


}
