package account.errors;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String message) {
        super(message);
    }
}
