package qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Post;
import qa.domain.Tag;
import qa.domain.User;
import qa.dto.post.PostCreateDTO;
import qa.dto.post.TagDTO;
import qa.repository.PostRepository;
import qa.repository.TagRepository;

import java.util.Optional;

/**
 * Created by ozgur on 7/29/17.
 */
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    TagRepository tagRepository;

    @Transactional
    public void test() {}

    @Transactional
    public Object create(PostCreateDTO postCreateDTO, User user) {

        Post post = new Post();
        post.setTitle(postCreateDTO.title);
        post.setContent(postCreateDTO.content);
        post.setUser(user);

        for (TagDTO tagDTO : postCreateDTO.tags) {

            Optional<Tag> tag = tagRepository.findById(tagDTO.id);

            if(tag.isPresent()) {
                post.addTags(tag.get());
            }

        }

        return postRepository.save(post);
    }
}
