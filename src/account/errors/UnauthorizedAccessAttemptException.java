package account.errors;

public class UnauthorizedAccessAttemptException extends RuntimeException{
    public UnauthorizedAccessAttemptException(String message) {
        super(message);
    }
}
