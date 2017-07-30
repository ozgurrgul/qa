package qa.service;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.*;
import qa.dto.comment.CommentCreateDTO;
import qa.dto.post.*;
import qa.exception.BadRequestException;
import qa.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by ozgur on 7/29/17.
 */
@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    BasePostRepository basePostRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public Object create(PostCreateDTO postCreateDTO, User user) {

        Post post = new Post();
        post.setTitle(postCreateDTO.title);
        post.setContent(postCreateDTO.content);
        post.setUser(user);

        for (TagDTO tagDTO : postCreateDTO.tags) {
            Optional<Tag> tag = tagRepository.findById(tagDTO.tagId);
            tag.ifPresent(post::addTags);
        }

        postRepository.save(post);

        return post;
    }

    @Transactional
    public Object update(PostUpdateDTO postCreateDTO, User user) {

        Optional<BasePost> postOpt = basePostRepository.findById(postCreateDTO.postId);
        BasePost post = postOpt.get();
        post.setTitle(postCreateDTO.title);
        post.setContent(postCreateDTO.content);
        post.setLastEditor(user);

        for (TagDTO tagDTO : postCreateDTO.tags) {
            Optional<Tag> tag = tagRepository.findById(tagDTO.tagId);
            //tag.ifPresent(post::addTags);
        }

        basePostRepository.save(post);

        return post;
    }

    @Transactional
    public Object answer(PostAnswerDTO postAnswerDTO, User user) {

        Optional<Post> postOpt = postRepository.findById(postAnswerDTO.postId);
        Post parent = postOpt.get();

        Answer answer = new Answer();
        answer.setContent(postAnswerDTO.content);
        answer.setUser(user);

        parent.addAnswer(answer);
        parent.setAnswerCount(parent.getAnswerCount() + 1);

        answerRepository.save(answer);
        postRepository.save(parent);

        return answer;
    }

    @Transactional
    public Object comment(CommentCreateDTO commentCreateDTO, User user) {

        Optional<Post> parent = postRepository.findById(commentCreateDTO.postId);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setContent(commentCreateDTO.content);
        comment.setParent(parent.get());

        return commentRepository.save(comment);
    }

    @Transactional
    public Object list() {
        Iterable<Post> posts = postRepository.findAll();
        return posts;
    }

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Object revisions(Long postId) {

        AuditReader auditReader = AuditReaderFactory.get(em);
        List<Number> revisions = auditReader.getRevisions(BasePost.class, postId);
        List<BasePost> posts = new ArrayList<>();

        for (Number revision : revisions) {
            BasePost postRevision = auditReader.find(BasePost.class, postId, revision);
            posts.add(postRevision);
        }

        return posts;
    }

}
