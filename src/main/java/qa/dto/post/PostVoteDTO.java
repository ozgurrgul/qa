package qa.dto.post;

import qa.domain.VoteType;
import qa.utils.validator.PostId;

/**
 * Created by ozgur on 7/30/17.
 */
public class PostVoteDTO {

    @PostId
    public Long postId;
    public VoteType voteType;

}
