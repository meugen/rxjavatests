package meugeninua.rxjavatests.client.model.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Result<T> {

    static <T> Result<T> withData(final T data) {
        return new Result<>(data, null);
    }

    static <T> Result<T> withError(final Throwable throwable) {
        return new Result<>(null, throwable);
    }

    private final T data;
    private final Throwable throwable;

    private Result(final T data, final Throwable throwable) {
        this.data = data;
        this.throwable = throwable;
    }

    @NonNull
    public T getData() {
        if (data == null) {
            throw new IllegalStateException("No data found", throwable);
        }
        return data;
    }

    public boolean isError() {
        return throwable != null;
    }

    @Nullable
    public Throwable getError() {
        return throwable;
    }
}
