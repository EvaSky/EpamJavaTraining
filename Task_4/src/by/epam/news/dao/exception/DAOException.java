package by.epam.news.dao.exception;

/**
 * Created by Olga Shahray on 28.05.2016.
 */
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
