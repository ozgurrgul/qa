package qa.utils.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AnswerIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnswerId {

    String message() default "Geçersiz answer id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}