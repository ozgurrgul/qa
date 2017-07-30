package qa.dto.post;

import qa.utils.validator.PostId;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostUpdateDTO extends PostCreateDTO {

    @PostId
    public Long postId;

}
