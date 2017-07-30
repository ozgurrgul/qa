package qa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import qa.domain.BasePost;
import qa.domain.Post;

/**
 * Created by ozgur on 7/29/17.
 */
public interface BasePostRepository extends
                                    RevisionRepository<BasePost, Long, Integer>,
                                    CrudRepository<BasePost, Long> {

}
