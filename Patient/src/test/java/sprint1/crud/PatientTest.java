package sprint1.crud;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PatientTest {

    @Mock
    private PatientRepository patientRepository;

    @Test
    public void patientTest() {
        Patient patient = new Patient("a","b", "c","d","e","f");
        given(patientRepository.save(patient)).willAnswer(invocation -> invocation.getArgument(0));


        // Save
        patient = patientRepository.save(patient);
        Assert.assertEquals(patient.getFirstname(),"a");
////
////        // Update
        patient.setFirstname("aa");
        patient = patientRepository.save(patient);
        Assert.assertEquals(patient.getFirstname(), "aa");
////
////        // Find
//        List<Patient> listResult = patientRepository.findAll();
//        Assert.assertTrue(listResult.size() > 0);
////
////        // Delete
//        Integer id = patient.getId();
//        patientRepository.delete(patient);
//        Optional<Patient> patientList = patientRepository.findById(id);
//        Assert.assertFalse(patientList.isPresent());
    }

}
