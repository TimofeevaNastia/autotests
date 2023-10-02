package ru.mytest.db;

public class AutotestSqlException extends RuntimeException {

    /**
     *
     * @param e a {@link Throwable} object.
     */
    public AutotestSqlException(Throwable e) {
        super(e);
    }

    /**
     *
     * @param message a {@link String} object.
     * @param e a {@link Throwable} object.
     */
    public AutotestSqlException(String message, Throwable e) {
        super(message, e);
    }

    /**
     *
     * @param message a {@link String} object.
     */
    public AutotestSqlException(String message) {
        super(message);
    }
}
