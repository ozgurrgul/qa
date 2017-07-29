package qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Answer;
import qa.domain.Post;
import qa.domain.User;
import qa.dto.post.PostCreateDTO;
import qa.repository.PostRepository;

/**
 * Created by ozgur on 7/29/17.
 */
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Transactional
    public void test() {
    }

    public Object create(PostCreateDTO postCreateDTO, User user) {
        return postCreateDTO;
    }
}
