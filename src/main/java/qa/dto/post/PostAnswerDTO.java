package qa.dto.post;

import qa.utils.validator.PostId;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostAnswerDTO {

    @PostId
    public Long postId;
    public String content;

}
