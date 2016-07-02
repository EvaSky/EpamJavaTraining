package by.epam.matrixmult.exception;

/**
 * Created by Olga Shahray on 30.06.2016.
 */
public class MatrixException extends Exception {

    public MatrixException() {
    }

    public MatrixException(String message) {
        super(message);
    }

    public MatrixException(String message, Throwable cause) {
        super(message, cause);
    }
}
