package ua.in.meugen.myapplication.ui.activities.base.fragment.vm;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import javax.inject.Inject;

import timber.log.Timber;

public abstract class BaseViewModel extends ViewModel {

    @Inject ExecutorService executorService;

    private final List<Future<?>> futures;

    protected BaseViewModel() {
        this.futures = new ArrayList<>();
    }

    protected final void async(final Runnable r) {
        final Future<?> future =  executorService.submit(r);
        futures.add(future);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Timber.d("onCleared() method called");
        Timber.d("Future list size is %d", futures.size());
        for (Future<?> future : futures) {
            if (!future.isCancelled() && !future.isDone()) {
                future.cancel(true);
                Timber.d("%s has cancelled", future);
            } else {
                Timber.d("%s no need to cancel", future);
            }
        }
        futures.clear();
        Timber.d("onCleared() method completed");
    }
}
