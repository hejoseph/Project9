package sprint2.repository;

import sprint2.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface NoteRepository extends MongoRepository<Note,String> {
}