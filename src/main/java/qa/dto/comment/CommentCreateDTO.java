package qa.dto.comment;

import org.hibernate.validator.constraints.NotBlank;
import qa.utils.validator.BasePostId;

/**
 * Created by ozgur on 7/29/17.
 */
public class CommentCreateDTO {

    @BasePostId
    public String basePostId;

    @NotBlank(message = "Yorum içeriği boş bırakılamaz")
    public String content;

}
