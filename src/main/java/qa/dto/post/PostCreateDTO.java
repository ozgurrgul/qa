package qa.dto.post;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import qa.domain.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostCreateDTO {

    @NotEmpty(message = "Başlık boş bırakılamaz")
    public String title;

    @NotEmpty(message = "Gönderi içeriği bol bırakılamaz")
    public String content;

    public HashSet<TagDTO> tags = new HashSet<>();

}
