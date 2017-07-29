package qa.dto.auth;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by ozgur on 7/8/17.
 */
public class AuthRegisterDTO extends AuthLoginDTO {

    // @NotBlank(message = "İsim boş olamaz")
    // @Length(max = 200)
    // public String displayName;

}
