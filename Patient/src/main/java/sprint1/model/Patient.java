package sprint1.model;

import sun.reflect.generics.visitor.Visitor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
//import com.project9.Sprint2.Visit;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String surname;
    private String dob;
    private String address;
    private String phone;
    private String gender;

    @Transient
    private List<Visit> visits;

    public Patient(String firstname, String surname, String dob, String address, String phone, String gender) {
        this.firstname = firstname;
        this.surname = surname;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
        this.visits = new ArrayList<>();
    }

    public Patient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
