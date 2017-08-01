package qa.utils.validator;

import org.springframework.beans.factory.annotation.Autowired;
import qa.exception.BadRequestException;
import qa.repository.AnswerRepository;
import qa.repository.PostRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ozgur on 7/30/17.
 */
public class AnswerIdValidator implements ConstraintValidator<AnswerId, String> {

    @Autowired
    AnswerRepository answerRepository;
    private AnswerId answerId;

    @Override
    public void initialize(AnswerId answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean isValid(String answerId, ConstraintValidatorContext context) {

        if(answerId == null) {
            //TODO:
            //throw new BadRequestException(this.answerId.message());
        }

        return answerRepository.findById(answerId).isPresent();

    }

}