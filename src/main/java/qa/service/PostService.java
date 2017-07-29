package qa.service;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.*;
import qa.dto.post.PostCreateDTO;
import qa.dto.post.PostUpdateDTO;
import qa.dto.post.TagDTO;
import qa.exception.BadRequestException;
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
            tag.ifPresent(post::addTags);
        }

        return postRepository.save(post);
    }

    public Object update(PostUpdateDTO postCreateDTO, User user) {

        // TODO: postId için özel validator oluştur?
        // TODO: editSummary
        Optional<Post> postOpt = postRepository.findById(postCreateDTO.postId);

        if(postOpt.isPresent() == false) {
            throw new BadRequestException("Post bulunamadı");
        }

        Post post = postOpt.get();
        post.setTitle(postCreateDTO.title);
        post.setContent(postCreateDTO.content);

        for (TagDTO tagDTO : postCreateDTO.tags) {
            Optional<Tag> tag = tagRepository.findById(tagDTO.id);
            tag.ifPresent(post::addTags);
        }

        return postRepository.save(post);
    }
}
