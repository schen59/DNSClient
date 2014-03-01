package exception;

/**
 * Common exception class.
 * @author Shaofeng Chen
 * @since 2/17/14
 */
public class DNSClientException extends RuntimeException {

    public DNSClientException(String message) {
        super(message);
    }

    public DNSClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
