package qa.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PostIdValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PostId {

    String message() default "Geçersiz post id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}