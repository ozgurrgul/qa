package qa.dto.post;

import qa.utils.validator.BasePostId;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostAnswerDTO {

    @BasePostId
    public Long postId;
    public String content;

}
