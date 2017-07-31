package ua.meugen.android.rxjavatests_client.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;
import ua.meugen.android.rxjavatests_client.model.responses.DataResponse;
import ua.meugen.android.rxjavatests_client.view.adapters.DataAdapter;
import ua.meugen.android.rxjavatests_client.viewmodel.rest.ModelApi;

/**
 * @author meugen
 */

public class MainViewModel
        implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private static final long DELAY = 5000L;
    private static final int COUNT = 100;

    public final ObservableInt progressVisibility
            = new ObservableInt(View.GONE);
    public final ObservableInt recyclerViewVisibility
            = new ObservableInt(View.VISIBLE);
    public final DataAdapter adapter;

    private final ModelApi modelApi;

    private CompositeSubscription compositeSubscription;

    @Inject
    public MainViewModel(final Context context, final ModelApi modelApi) {
        this.modelApi = modelApi;
        this.adapter = new DataAdapter(context);
        this.compositeSubscription = new CompositeSubscription();
    }

    public void reset() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
            compositeSubscription = null;
        }
    }

    @Override
    public void onRefresh() {
        setup(true);
    }

    private void showProgress() {
        progressVisibility.set(View.VISIBLE);
        recyclerViewVisibility.set(View.GONE);
    }

    private void showData(final List<String> data) {
        progressVisibility.set(View.GONE);
        recyclerViewVisibility.set(View.VISIBLE);
        adapter.swapData(data);
    }

    public void setup(final boolean reload) {
        showProgress();

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
        showData(response.getData());
    }
}
