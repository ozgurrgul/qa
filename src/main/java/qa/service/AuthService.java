package qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import qa.exception.BadRequestException;
import qa.dto.auth.AuthLoginDTO;
import qa.dto.auth.AuthRegisterDTO;
import qa.dto.auth.AuthResponseDTO;
import qa.domain.Token;
import qa.repository.TokenRepository;
import qa.domain.User;
import qa.repository.UserRepository;
import qa.utils.TokenUtils;

import javax.validation.Valid;

/**
 * Created by ozgur on 7/29/17.
 */

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    public AuthResponseDTO login(AuthLoginDTO authLoginDTO) {

        User tmpUser = userRepository.getByEmailAndPassword(authLoginDTO.email, authLoginDTO.password);

        if(tmpUser == null) {
            throw new BadRequestException("Wrong email or password");
        }

        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token(tokenValue);
        token.setUser(tmpUser);

        // persist token
        tokenRepository.save(token);

        return new AuthResponseDTO(tokenValue);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public AuthResponseDTO register(AuthRegisterDTO authRegisterDTO) {

        if(isEmailExists(authRegisterDTO)) {
            throw new BadRequestException("E-mail exists in database");
        }

        User tmpUser = new User(authRegisterDTO.email, authRegisterDTO.password);

        // persist user
        userRepository.save(tmpUser);

        String tokenValue = TokenUtils.randomTokenValue();
        Token token = new Token(tokenValue);
        token.setUser(tmpUser);

        // persist token
        tokenRepository.save(token);

        return new AuthResponseDTO(tokenValue);
    }

    private boolean isEmailExists(AuthRegisterDTO authRegisterModel) {
        return userRepository.getByEmail(authRegisterModel.email) != null;
    }

}
