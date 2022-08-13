package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Exception;

public class DuplicateManagerException extends RuntimeException {
    public DuplicateManagerException() {
        super();
    }
    public DuplicateManagerException(String message, Throwable cause) {
        super(message, cause);
    }
    public DuplicateManagerException(String message) {
        super(message);
    }
    public DuplicateManagerException(Throwable cause) {
        super(cause);
    }
}
