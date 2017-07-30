package qa.dto.post;

import qa.domain.VoteType;
import qa.utils.validator.BasePostId;

/**
 * Created by ozgur on 7/30/17.
 */
public class PostVoteDTO {

    @BasePostId
    public Long postId;
    public VoteType voteType;

}
