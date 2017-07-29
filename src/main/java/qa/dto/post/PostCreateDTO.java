package qa.dto.post;

import qa.domain.Tag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ozgur on 7/29/17.
 */
public class PostCreateDTO {

    public String message;
    public String content;

    public HashSet<Tag> tags = new HashSet<>();
}
