package qa.utils.validator;

import org.springframework.beans.factory.annotation.Autowired;
import qa.repository.BasePostRepository;
import qa.repository.PostRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ozgur on 7/30/17.
 */
public class PostIdValidator implements ConstraintValidator<PostId, String> {

    @Autowired
    PostRepository postRepository;

    @Override
    public void initialize(PostId basePostId) {
    }

    @Override
    public boolean isValid(String postId, ConstraintValidatorContext context) {
        return postRepository.findById(postId).isPresent();
    }

}