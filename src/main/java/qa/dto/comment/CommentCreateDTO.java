package qa.dto.comment;

import org.hibernate.validator.constraints.NotBlank;
import qa.utils.validator.PostId;

/**
 * Created by ozgur on 7/29/17.
 */
public class CommentCreateDTO {

    @PostId
    public Long postId;

    @NotBlank(message = "Yorum içeriği boş bırakılamaz")
    public String content;

}
