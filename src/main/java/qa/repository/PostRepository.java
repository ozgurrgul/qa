package qa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import qa.domain.Post;

import java.util.List;

/**
 * Created by ozgur on 7/29/17.
 */
public interface PostRepository extends CrudRepository<Post, String> {

    Page<Post> findAll(Pageable pageable);

}
