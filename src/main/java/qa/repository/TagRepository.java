package qa.repository;

import org.springframework.data.repository.CrudRepository;
import qa.domain.Post;
import qa.domain.Tag;

/**
 * Created by ozgur on 7/29/17.
 */
public interface TagRepository extends CrudRepository<Tag, Long> {
}
