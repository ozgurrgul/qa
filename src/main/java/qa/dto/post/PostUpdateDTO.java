package qa.dto.post;

import qa.utils.validator.BasePostId;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostUpdateDTO extends PostCreateDTO {

    @BasePostId
    public String basePostId;

}
