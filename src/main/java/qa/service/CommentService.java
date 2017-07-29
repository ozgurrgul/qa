package qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Comment;
import qa.domain.Post;
import qa.domain.Tag;
import qa.domain.User;
import qa.dto.comment.CommentCreateDTO;
import qa.dto.post.PostCreateDTO;
import qa.dto.post.TagDTO;
import qa.exception.BadRequestException;
import qa.repository.CommentRepository;
import qa.repository.PostRepository;
import qa.repository.TagRepository;

import java.util.Optional;

/**
 * Created by ozgur on 7/29/17.
 */
@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public Object create(CommentCreateDTO commentCreateDTO, User user) {

        Optional<Post> parent = postRepository.findById(commentCreateDTO.postId);

        if(parent.isPresent() == false) {
            throw new BadRequestException("Post bulunamadı");
        }

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setContent(commentCreateDTO.content);
        comment.setParent(parent.get());

        return commentRepository.save(comment);
    }
}
