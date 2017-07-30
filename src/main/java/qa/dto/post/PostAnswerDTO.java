package qa.dto.post;

import org.hibernate.validator.constraints.NotEmpty;
import qa.utils.PostId;

import java.util.HashSet;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostAnswerDTO {

    @PostId
    public Long postId;
    public String content;

}
