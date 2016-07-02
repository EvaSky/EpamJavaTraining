package by.epam.news.service.exception;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
