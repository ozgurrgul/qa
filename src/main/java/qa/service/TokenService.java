package qa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Token;
import qa.domain.User;
import qa.repository.TokenRepository;

/**
 * Created by ozgur on 7/30/17.
 */
@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Transactional
    public User getUserFromToken(String tokenString) {


        if(tokenString == null) {
            return null;
        }

        Token token = tokenRepository.findTokenByTokenValue(tokenString);

        if(token == null) {
            return null;
        }

        User user = null;

        try {
            user = token.getUser();
        } catch (Exception e) {
            return null;
        }

        if(user == null) {
            return null;
        }

        return user;
    }

}
