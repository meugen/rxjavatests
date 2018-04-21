package ua.in.meugen.myapplication.model.network;

import android.support.annotation.IntDef;

import retrofit2.HttpException;
import retrofit2.Response;

public class Resource<T> {

    public static <T> Resource<T> success(final T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(final Response<T> response) {
        return error(new HttpException(response));
    }

    public static <T> Resource<T> error(final Throwable th) {
        return new Resource<>(ERROR, null, th);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(LOADING, null, null);
    }

    @IntDef({SUCCESS,ERROR,LOADING})
    public @interface Status {}
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int LOADING = 3;

    public final int status;
    public final T data;
    public final Throwable error;

    private Resource(
            final int status,
            final T data,
            final Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }
}
