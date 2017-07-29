package qa.dto.post;

import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostUpdateDTO extends PostCreateDTO {

    @NotBlank(message = "postId boş bırakılamaz")
    public Long postId;

    @NotBlank(message = "Düzenleme nedeni boş bırakılamaz")
    public String editSummary;

}
