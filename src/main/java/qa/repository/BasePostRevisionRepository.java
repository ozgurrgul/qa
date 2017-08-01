package qa.repository;

import org.springframework.data.repository.CrudRepository;
import qa.domain.BasePost;
import qa.domain.BasePostRevision;

import java.util.List;

/**
 * Created by ozgur on 7/29/17.
 */
public interface BasePostRevisionRepository extends CrudRepository<BasePostRevision, String> {

    List<BasePost> findByBasePostId(String basePostId);

}
