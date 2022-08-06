package springeshop.website.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import springeshop.website.domain.User;
import springeshop.website.dto.UserDTO;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
    void save(User user);
    List<UserDTO> getAll();

    User findByEmail(String email);
    void updateProfile(UserDTO userDTO);
}
