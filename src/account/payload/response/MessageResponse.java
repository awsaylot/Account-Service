package account.payload.response;

public class MessageResponse {
    private String message;

    private String timestamp = "data";
    private int status = 400;
    private String error = "Bad Request";
    private String path = "/api/auth/signup";


    public MessageResponse(String message) {
        this.message = message;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
