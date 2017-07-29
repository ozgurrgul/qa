package qa.dto.auth;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by ozgur on 7/8/17.
 */
public class AuthLoginDTO {

    @NotBlank(message = "E-mail boş olamaz")
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "Must be valid e-mail")
    public String email;

    @NotBlank(message = "Şifre boş olamaz")
    @Length(min = 8, message = "Şifre minimum 8 karakterli olmalı")
    public String password;

}
