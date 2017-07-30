package qa.repository;

import org.springframework.data.repository.CrudRepository;
import qa.domain.Post;
import qa.domain.PostVote;
import qa.domain.Tag;
import qa.domain.User;

/**
 * Created by ozgur on 7/29/17.
 */
public interface PostVoteRepository extends CrudRepository<PostVote, Long> {

    PostVote findByUserAndPost(User user, Post post);

}
