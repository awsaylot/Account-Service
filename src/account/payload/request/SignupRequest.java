package account.payload.request;

import javax.validation.constraints.*;
import javax.validation.constraints.Pattern;

public class SignupRequest {
    @NotBlank
    String name;
    @NotBlank
    String lastname;
    @NotBlank
    @Pattern(regexp = "\\w+(@acme.com)$")
    String email;
    @NotBlank
    @Size(min = 12, message = "Password length must be 12 chars minimum!")
    String password;

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
