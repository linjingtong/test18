package cn.wolfcode.exception;

/**
 * Created by seemygo on 2018/2/26.
 */
public class Username_NullException extends RuntimeException {

    public Username_NullException() {
    }

    public Username_NullException(String message) {
        super(message);
    }

    public Username_NullException(String message, Throwable cause) {
        super(message, cause);
    }
}
