package qa.dto.post;

import qa.utils.validator.AnswerId;
import qa.utils.validator.PostId;

/**
 * Created by ozgur on 7/31/17.
 */
public class AcceptAnswerDTO {

    @AnswerId
    public String answerId;

    @PostId
    public String postId;
}
