package qa.dto.comment;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by ozgur on 7/29/17.
 */
public class CommentCreateDTO {

    @NotBlank(message = "postId boş bırakılamaz")
    public Long postId;

    @NotBlank(message = "Yorum içeriği boş bırakılamaz")
    public String content;

}