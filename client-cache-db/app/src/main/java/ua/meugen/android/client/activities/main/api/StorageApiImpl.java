package ua.meugen.android.client.activities.main.api;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.Single;
import ua.meugen.android.client.app.RestApi;
import ua.meugen.android.client.data.responses.DataResponse;
import ua.meugen.android.client.utils.RxUtils;

public class StorageApiImpl implements StorageApi {

    private static final String TAG = StorageApiImpl.class.getSimpleName();

    private static final long DELAY = 5000L;

    private final RestApi restApi;
    private final StorageDb storageDb;
    private final RxUtils rxUtils;

    private ObservableEmitter<List<String>> mEmitter;

    @Inject
    public StorageApiImpl(
            final RestApi restApi,
            final StorageDb storageDb,
            final RxUtils rxUtils) {
        this.restApi = restApi;
        this.storageDb = storageDb;
        this.rxUtils = rxUtils;
    }

    @Override
    public Observable<List<String>> storage(final int id) {
        return Observable.just(storageDb.loadItems())
                .flatMap(data -> requestApi(id, data));
    }

    private Observable<List<String>> requestApi(final int id, final List<String> data) {
        restApi.storageWithDelay(id, DELAY)
                .map(DataResponse::getData)
                .flatMap(this::storeToDb)
                .compose(rxUtils.singleAsync())
                .subscribe(this::onApiSuccess, this::onApiError);
        return Observable.create(emitter -> {
            mEmitter = emitter;
            mEmitter.onNext(data);
        });
    }

    private Single<List<String>> storeToDb(final List<String> items) {
        storageDb.storeItems(items);
        return Single.just(items);
    }

    private void onApiSuccess(final List<String> items) {
        if (mEmitter != null && !mEmitter.isDisposed()) {
            mEmitter.onNext(items);
        }
    }

    private void onApiError(final Throwable th) {
        Log.e(TAG, th.getMessage(), th);
    }
}
