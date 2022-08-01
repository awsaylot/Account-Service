package account.service;

import account.entity.User;
import account.payload.request.SignupRequest;
import account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {


    List<String> breachedPasswords = new ArrayList<>(Arrays.asList("PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
            "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
            "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember"
    ));

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User addUser(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getEmail().toLowerCase());
        user.setEmail(signupRequest.getEmail().toLowerCase());
        user.setName(signupRequest.getName());
        user.setLastname(signupRequest.getLastname());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        return userRepository.save(user);
    }

    public boolean changePassword(User user, String password) {
        if(encoder.matches(password, user.getPassword())){
            return false;
        }
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        return true;
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email.toLowerCase());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameIgnoreCase(s);

        if (user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException(String.format("Username[%s] not found"));
        }
    }

    public boolean isBreached(String password) {
        if (breachedPasswords.contains(password)) {
            return true;
        }
        return false;
    }
}