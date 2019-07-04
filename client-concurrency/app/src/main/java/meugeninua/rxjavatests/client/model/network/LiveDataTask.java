package meugeninua.rxjavatests.client.model.network;

import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class LiveDataTask<T> extends FutureTask<T> {

    private final MutableLiveData<Result<T>> liveData;

    public LiveDataTask(
            final Callable<T> callable,
            final MutableLiveData<Result<T>> liveData) {
        super(callable);
        this.liveData = liveData;
    }

    @Override
    protected void set(final T t) {
        super.set(t);
        liveData.postValue(Result.withData(t));
    }

    @Override
    protected void setException(final Throwable t) {
        super.setException(t);
        liveData.postValue(Result.<T>withError(t));
    }
}
