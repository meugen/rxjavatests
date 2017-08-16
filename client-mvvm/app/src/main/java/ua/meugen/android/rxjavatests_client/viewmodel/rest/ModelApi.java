package ua.meugen.android.rxjavatests_client.viewmodel.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import ua.meugen.android.rxjavatests_client.model.responses.DataResponse;

@Singleton
public class ModelApi {

    private final RestApi restApi;
    private Observable<DataResponse> dataObservable;
    private Disposable disposable;

    @Inject
    public ModelApi(final RestApi restApi) {
        this.restApi = restApi;
    }

    public Observable<DataResponse> dataWithDelay(
            final long delay, final int count) {
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
        BehaviorSubject<DataResponse> dataSubject
                = BehaviorSubject.create();
        disposable = restApi.dataWithDelay(delay, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSubject::onNext, dataSubject::onError);
        dataObservable = dataSubject.hide();
        return dataObservable;
    }

    public Observable<DataResponse> getDataObservable() {
        return dataObservable;
    }
}
