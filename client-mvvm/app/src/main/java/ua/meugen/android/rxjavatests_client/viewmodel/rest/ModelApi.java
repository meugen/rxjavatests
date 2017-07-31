package ua.meugen.android.rxjavatests_client.viewmodel.rest;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import ua.meugen.android.rxjavatests_client.model.responses.DataResponse;

@Singleton
public class ModelApi {

    private final RestApi restApi;
    private Observable<DataResponse> dataObservable;
    private Subscription subscription;

    @Inject
    public ModelApi(final RestApi restApi) {
        this.restApi = restApi;
    }

    public Observable<DataResponse> dataWithDelay(
            final long delay, final int count) {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
        BehaviorSubject<DataResponse> dataSubject
                = BehaviorSubject.create();
        subscription = restApi.dataWithDelay(delay, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSubject::onNext, dataSubject::onError);
        dataObservable = dataSubject.asObservable();
        return dataObservable;
    }

    public Observable<DataResponse> getDataObservable() {
        return dataObservable;
    }
}
