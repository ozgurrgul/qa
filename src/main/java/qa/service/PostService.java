package qa.service;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.*;
import qa.dto.post.CommentCreateDTO;
import qa.dto.post.*;
import qa.exception.BadRequestException;
import qa.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ozgur on 7/29/17.
 */
@Service
public class PostService {

    @Autowired PostRepository postRepository;
    @Autowired BasePostRepository basePostRepository;
    @Autowired AnswerRepository answerRepository;
    @Autowired TagRepository tagRepository;
    @Autowired CommentRepository commentRepository;
    @Autowired BasePostRevisionRepository basePostRevisionRepository;

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

        BasePost post = basePostRepository.findById(postCreateDTO.basePostId).get();
        BasePostRevision revision = new BasePostRevision();

        revision.setUser(user);
        revision.setApproved(false);
        revision.setTitle(postCreateDTO.title);
        revision.setContent(postCreateDTO.content);
        revision.setBasePostId(post.getId());

        /*
        * for (TagDTO tagDTO : postCreateDTO.tags) {
            Optional<Tag> tag = tagRepository.findById(tagDTO.tagId);
            //tag.ifPresent(post::addTags);
        }
        */

        basePostRevisionRepository.save(revision);

        return revision;
    }

    @Transactional
    public Object answer(PostAnswerDTO postAnswerDTO, User user){

        Post parent = postRepository.findById(postAnswerDTO.postId).get();

        Answer answer = new Answer();
        answer.setContent(postAnswerDTO.content);
        answer.setUser(user);

        answerRepository.save(answer);

        parent.addAnswer(answer);

        postRepository.save(parent);

        return answer;
    }

    @Transactional
    public Object comment(CommentCreateDTO commentCreateDTO, User user) {

        BasePost parent = basePostRepository.findById(commentCreateDTO.basePostId).get();

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setContent(commentCreateDTO.content);

        commentRepository.save(comment);

        parent.addComment(comment);

        basePostRepository.save(parent);

        return comment;
    }

    @Transactional
    public Object accept(AcceptAnswerDTO acceptAnswerDTO, User user) {

        Answer answer = answerRepository.findById(acceptAnswerDTO.answerId).get();

        if(answer.isAccepted()) {
            return true;
        }

        answer.setAccepted(true);
        answerRepository.save(answer);

        return true;
    }

    @Transactional
    public Object list() {
        Iterable<Post> posts = postRepository.findAll();
        return posts;
    }

    @Transactional
    public Object mergeRevision(MergeRevisionDTO mergeRevisionDTO, User user) {

        Optional<BasePostRevision> revisionOpt = basePostRevisionRepository.findById(mergeRevisionDTO.basePostId);

        if(revisionOpt.isPresent() == false) {
            throw new BadRequestException("Revizyon bulunamadı");
        }

        BasePostRevision rev = revisionOpt.get();

        if(rev.isApproved()) {
            throw new BadRequestException("Bu revizyon zaten merge edilmiş");
        }

        Optional<BasePost> updatingBasePostOpt = basePostRepository.findById(rev.getBasePostId());

        if(updatingBasePostOpt.isPresent() == false) {
            throw new BadRequestException("Update edilecek basePost bulunamadı");
        }

        BasePost updatingBasePost = updatingBasePostOpt.get();
        updatingBasePost.setTitle(rev.getTitle());
        updatingBasePost.setContent(rev.getContent());
        updatingBasePost.setLastEditor(rev.getUser());

        rev.setApproved(true);
        basePostRevisionRepository.save(rev);

        basePostRepository.save(updatingBasePost);

        return "merged";
    }

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Object revisions(String basePostId) {
        return basePostRevisionRepository.findByBasePostId(basePostId);
    }

}
