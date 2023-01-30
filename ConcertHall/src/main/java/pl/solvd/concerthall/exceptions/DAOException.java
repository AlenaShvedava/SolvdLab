package pl.solvd.concerthall.exceptions;

public class DAOException extends ServiceException {
    public DAOException (String message, Throwable cause){
        super(message, cause);
    }

}