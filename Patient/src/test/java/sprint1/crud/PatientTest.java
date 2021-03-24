package sprint1.crud;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import sprint1.model.Patient;
import sprint1.repository.PatientRepository;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void patientTest() {
        Patient patient = new Patient("a","b", "c","d","e","f");
//
//        // Save
        patient = patientRepository.save(patient);
        Assert.assertNotNull(patient.getId());
        Assert.assertEquals(patient.getFirstname(),"a");
//
//        // Update
        patient.setFirstname("aa");
        patient = patientRepository.save(patient);
        Assert.assertEquals(patient.getFirstname(), "aa");
//
//        // Find
        List<Patient> listResult = patientRepository.findAll();
        Assert.assertTrue(listResult.size() > 0);
//
//        // Delete
        Integer id = patient.getId();
        patientRepository.delete(patient);
        Optional<Patient> patientList = patientRepository.findById(id);
        Assert.assertFalse(patientList.isPresent());
    }

}
