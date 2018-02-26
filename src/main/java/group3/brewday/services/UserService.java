package group3.brewday.services;

import group3.brewday.models.User;
import group3.brewday.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
    
    User findOne(Long id);
}