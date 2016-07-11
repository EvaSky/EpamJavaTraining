package by.epam.port.exception;

/**
 * Created by Olga Shahray on 11.07.2016.
 */
public class PortException extends Exception {

    private static final long serialVersionUID = 1L;

    public PortException(String message) {
        super(message);
    }

    public PortException(Exception ex) {
        super(ex);
    }

    public PortException(String message, Exception ex) {
        super(message, ex);
    }
}
