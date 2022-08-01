package account.controller.business;

import account.entity.User;
import account.errors.UnauthorizedAccessAttemptException;
import account.payload.response.EmployeePaymentResponse;
import account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/empl")
public class EmployeeController {

    @Autowired
    UserService userService;

    @GetMapping("payment")
    public ResponseEntity<?> getEmployeePay(Authentication auth) {
        if(auth == null){
            throw new UnauthorizedAccessAttemptException("");
        }
        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(new EmployeePaymentResponse(user.getId(), user.getName(), user.getLastname(), user.getEmail()));
    }
}
