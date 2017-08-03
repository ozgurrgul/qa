package qa.service;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.*;
import qa.dto.post.CommentCreateDTO;
import qa.dto.post.*;
import qa.exception.BadRequestException;
import qa.repository.*;
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

    //TODO: notify post owner
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

    //TODO: notify basePost owner
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

    //TODO: notify answer owner
    @Transactional
    public Object accept(AcceptAnswerDTO acceptAnswerDTO, User user) {

        Answer answer = answerRepository.findById(acceptAnswerDTO.answerId).get();

        if(answer.isAccepted()) {
            return true;
        }

        Post post = postRepository.findById(acceptAnswerDTO.postId).get();

        if(post.getUser().equals(user) == false) {
            throw new BadRequestException("Sadece kendi postlarınıza yapılan cevapları kabul edebilirsiniz.");
        }

        answer.setAccepted(true);
        answerRepository.save(answer);

        return true;
    }

    @Transactional
    public Page<Post> listIndexPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional
    public Object acceptRevision(MergeRevisionDTO mergeRevisionDTO, User user) {

        Optional<BasePostRevision> revisionOpt = basePostRevisionRepository.findById(mergeRevisionDTO.basePostId);

        if(revisionOpt.isPresent() == false) {
            throw new BadRequestException("Revizyon bulunamadı");
        }

        BasePostRevision rev = revisionOpt.get();

        if(rev.isApproved()) {
            throw new BadRequestException("Bu revizyon zaten kabul edilmiş");
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

        return true;
    }

    @Transactional
    public Object revisions(String basePostId) {
        return basePostRevisionRepository.findByBasePostId(basePostId);
    }

    @Transactional
    public Post getPostById(String postId) {

        Optional<Post> postOpt =  postRepository.findById(postId);
        if(postOpt.isPresent() == false) {
            return null;
        }

        return postOpt.get();
    }

    @Transactional
    public BasePost getBasePostById(String basePostId) {

        Optional<BasePost> postOpt =  basePostRepository.findById(basePostId);
        if(postOpt.isPresent() == false) {
            return null;
        }

        return postOpt.get();
    }
}
