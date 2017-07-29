package qa.repository;

import org.springframework.data.repository.CrudRepository;
import qa.domain.Post;

/**
 * Created by ozgur on 7/29/17.
 */
public interface PostRepository extends CrudRepository<Post, Long> {
}
