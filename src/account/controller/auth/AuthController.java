package account.controller.auth;

import account.entity.User;
import account.errors.BreachedPasswordException;
import account.errors.SamePasswordException;
import account.errors.UnauthorizedAccessAttemptException;
import account.errors.UserExistsException;
import account.payload.request.ChangePasswordRequest;
import account.payload.request.SignupRequest;
import account.payload.response.ChangePasswordResponse;
import account.payload.response.SignupResponse;
import account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest request) throws UnsupportedEncodingException {
        if(userService.existsByEmail(request.getEmail())) {
            throw new UserExistsException("User exist!");
        } else if(userService.isBreached(request.getPassword())) {
            throw new BreachedPasswordException("The password is in the hacker's database!");
        } else {
            User user = userService.addUser(request);
            return ResponseEntity.ok(new SignupResponse(user.getId(), user.getName(), user.getLastname(), user.getEmail()));
        }
    }

    @PostMapping("changepass")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request, Authentication auth) {
        if(auth == null){
            throw new UnauthorizedAccessAttemptException("");
        } else if(userService.isBreached(request.getNew_password())) {
            throw new BreachedPasswordException("The password is in the hacker's database!");
        }
        User user = (User) auth.getPrincipal();
        boolean changed = userService.changePassword(user, request.getNew_password());
        if(!changed) {
            throw new SamePasswordException("The passwords must be different!");
        }
        return ResponseEntity.ok(new ChangePasswordResponse(user.getEmail(), "The password has been updated successfully"));
    }
}
