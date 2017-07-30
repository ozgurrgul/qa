package qa.utils.validator;

import org.springframework.beans.factory.annotation.Autowired;
import qa.repository.PostRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ozgur on 7/30/17.
 */
public class PostIdValidator implements ConstraintValidator<PostId, Long> {

    @Autowired
    PostRepository postRepository;

    @Override
    public void initialize(PostId postId) {
    }

    @Override
    public boolean isValid(Long postId, ConstraintValidatorContext context) {
        return postRepository.findById(postId).isPresent();
    }

}