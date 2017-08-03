package qa.dto.post;

import qa.domain.VoteType;
import qa.utils.validator.BasePostId;

/**
 * Created by ozgur on 7/30/17.
 */
public class BasePostVoteDTO {

    @BasePostId
    public String basePostId;
    public VoteType voteType;

}
