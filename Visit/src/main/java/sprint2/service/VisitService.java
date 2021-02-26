package sprint2.service;

import sprint2.model.Visit;
import org.springframework.stereotype.Service;
import sprint2.repository.VisitRepository;

import java.util.List;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit saveVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Visit deleteVisit(String id) {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidlist Id:" + id));
        visitRepository.delete(visit);
        return visit;
    }

    public Visit findById(String id) {
        return visitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
    }
}
