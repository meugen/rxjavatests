package ua.meugen.android.rxjavatests_client.viewmodel;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import org.reactivestreams.Subscription;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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

    private CompositeDisposable compositeDisposable;

    @Inject
    public MainViewModel(final Context context, final ModelApi modelApi) {
        this.modelApi = modelApi;
        this.adapter = new DataAdapter(context);
        this.compositeDisposable = new CompositeDisposable();
    }

    public void reset() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
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
        Disposable disposable = observable
                .subscribe(this::onDataSucess, this::onDataError);
        compositeDisposable.add(disposable);
    }

    public void onDataError(final Throwable e) {
        Log.e(TAG, e.getMessage(), e);
    }

    public void onDataSucess(final DataResponse response) {
        showData(response.getData());
    }
}
