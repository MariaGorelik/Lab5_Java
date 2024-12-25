package dao;

public class DAOException extends Exception {

    private static final long serialVersionUID = 5424909736760729785L;

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
