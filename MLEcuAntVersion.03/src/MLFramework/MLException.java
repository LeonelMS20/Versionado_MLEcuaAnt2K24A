package MLFramework;

public class MLException extends Exception {
    public MLException(String message) {
        super(message);
    }

    public MLException(String message, Throwable cause) {
        super(message, cause);
    }
}
