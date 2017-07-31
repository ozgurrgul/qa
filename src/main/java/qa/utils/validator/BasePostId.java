package qa.utils.validator;

import org.w3c.dom.events.UIEvent;
import qa.domain.Post;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BasePostIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface BasePostId {

    String message() default "Geçersiz basePost id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}