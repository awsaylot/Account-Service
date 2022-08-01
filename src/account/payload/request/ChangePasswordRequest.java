package account.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePasswordRequest {
    @NotBlank
    @Size(min = 12, message = "Password length must be 12 chars minimum!")
    String new_password;

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
