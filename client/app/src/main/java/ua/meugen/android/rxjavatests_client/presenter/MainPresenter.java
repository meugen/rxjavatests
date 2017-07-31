package ua.meugen.android.rxjavatests_client.presenter;

import android.util.Log;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ua.meugen.android.rxjavatests_client.model.responses.DataResponse;
import ua.meugen.android.rxjavatests_client.presenter.rest.ModelApi;
import ua.meugen.android.rxjavatests_client.view.MainView;

/**
 * @author meugen
 */

public class MainPresenter implements MvpPresenter<MainView> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    private static final long DELAY = 5000L;
    private static final int COUNT = 100;

    private final ModelApi modelApi;

    private MainView view;
    private CompositeSubscription compositeSubscription;

    @Inject
    public MainPresenter(final ModelApi modelApi) {
        this.modelApi = modelApi;
    }

    @Override
    public void attachView(final MainView view) {
        this.view = view;
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView(final boolean retainInstance) {
        this.view = null;
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
            compositeSubscription = null;
        }
    }

    public void setup(final boolean reload) {
        view.showProgress();
        Observable<DataResponse> observable = modelApi.getDataObservable();
        if (reload || observable == null) {
            observable = modelApi.dataWithDelay(DELAY, COUNT);
        }
        Subscription subscription = observable
                .subscribe(this::onDataSucess, this::onDataError);
        compositeSubscription.add(subscription);
    }

    public void onDataError(final Throwable e) {
        Log.e(TAG, e.getMessage(), e);
    }

    public void onDataSucess(final DataResponse response) {
        Log.i(TAG, "onDataSucess(" + response + ")");
        view.showData(response.getData());
    }
}
