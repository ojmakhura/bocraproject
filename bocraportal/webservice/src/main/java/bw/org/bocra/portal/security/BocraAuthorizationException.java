package bw.org.bocra.portal.security;

public class BocraAuthorizationException extends RuntimeException {
    
    public BocraAuthorizationException() {
        super();
    }

    public BocraAuthorizationException(String message) {
        super(message);
    }

}
