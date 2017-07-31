package qa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import qa.domain.Answer;
import qa.domain.Post;

import java.util.List;

/**
 * Created by ozgur on 7/29/17.
 */
public interface AnswerRepository extends CrudRepository<Answer, String> {


}
