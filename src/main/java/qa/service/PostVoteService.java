package qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.*;
import qa.dto.post.BasePostVoteDTO;
import qa.repository.BasePostRepository;
import qa.repository.PostVoteRepository;

/**
 * Created by ozgur on 7/29/17.
 */
@Service
public class PostVoteService {

    @Autowired
    PostVoteRepository postVoteRepository;

    @Autowired
    BasePostRepository basePostRepository;

    @Transactional
    public Object vote(BasePostVoteDTO basePostVoteDTO, User user) {

        BasePost post = basePostRepository.findById(basePostVoteDTO.postId).get();
        PostVote postVote = postVoteRepository.findByUserAndPost(user, post);
        VoteType voteTypeRequest = basePostVoteDTO.voteType;

        // create new vote
        if(postVote == null) {
            if(voteTypeRequest == VoteType.UP_VOTE) post.upVote(user);
        }


        return basePostVoteDTO;
    }

}
