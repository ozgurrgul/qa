package qa.repository;

import org.springframework.data.repository.CrudRepository;
import qa.domain.*;

/**
 * Created by ozgur on 7/29/17.
 */
public interface PostVoteRepository extends CrudRepository<PostVote, String> {

    PostVote findByUserAndPost(User user, BasePost post);

}
