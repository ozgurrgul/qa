package qa.repository;

/**
 * Created by ozgur on 7/5/17.
 */

import org.springframework.data.repository.CrudRepository;
import qa.domain.Token;

public interface TokenRepository extends CrudRepository<Token, String> {
    Token findTokenByTokenValue(String tokenValue);
}
