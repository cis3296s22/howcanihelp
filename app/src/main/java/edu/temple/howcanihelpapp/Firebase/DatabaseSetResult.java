package edu.temple.howcanihelpapp.Firebase;

/**
 * Result from an attempted "setValue" for a Firebase database request.
 */
public class DatabaseSetResult<T> {
    /**
     * A reason as to why a database "set" has failed.
     */
    public enum DatabaseSetFailReason {
        None,
    }

    private T setData;
    private DatabaseSetFailReason failReason = DatabaseSetFailReason.None;

    public DatabaseSetResult(DatabaseSetFailReason failReason) {
        this.failReason = failReason;
        this.setData = null;
    }

    public DatabaseSetResult(T setData) {
        this.setData = setData;
    }

    public T getSetData() {
        return this.setData;
    }

    public DatabaseSetFailReason getFailReason() {
        return this.failReason;
    }

    public boolean isSuccessful() {
        return this.setData != null;
    }
}
