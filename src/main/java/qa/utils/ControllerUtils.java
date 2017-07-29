package qa.utils;

import org.springframework.security.core.Authentication;
import qa.domain.User;

import java.util.stream.Collectors;

/**
 * Created by ozgur on 7/22/17.
 */
public class ControllerUtils {

    public static String getIp() {
        return "ip";
    }

    public static User getUser(Authentication authentication) {

        if(authentication == null) {
            return null;
        }

        if(authentication.getPrincipal() == null) {
            return null;
        }

        return (User) authentication.getPrincipal();
    }

}
