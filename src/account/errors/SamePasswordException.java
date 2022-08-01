package account.errors;

public class SamePasswordException extends RuntimeException{
    public SamePasswordException(String message) {
        super(message);
    }
}
