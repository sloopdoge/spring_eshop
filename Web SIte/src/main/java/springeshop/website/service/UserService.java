package springeshop.website.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springeshop.website.dto.UserDTO;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
}
