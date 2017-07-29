package qa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import qa.domain.Post;

/**
 * Created by ozgur on 7/29/17.
 */
public interface PostRepository extends RevisionRepository<Post, Long, Integer>, CrudRepository<Post, Long> {


}
