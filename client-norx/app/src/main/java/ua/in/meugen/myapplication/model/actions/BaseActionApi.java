package ua.in.meugen.myapplication.model.actions;

import android.arch.lifecycle.MutableLiveData;

import java.lang.ref.WeakReference;

import timber.log.Timber;

public abstract class BaseActionApi<Resp, Req> implements AppActionApi<Resp, Req> {

    private WeakReference<MutableLiveData<Resp>> ref;
    private Req req;

    @Override
    public final void attachLiveData(final MutableLiveData<Resp> liveData) {
        this.ref = new WeakReference<>(liveData);
    }

    @Override
    public final void attachRequest(final Req req) {
        this.req = req;
    }

    @Override
    public final void run() {
        try {
            internalRun(this.req);
        } catch (InterruptedException e) {
            Timber.d(e);
        }
    }

    protected final void postValue(final Resp value) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException("Thread was interrupted.");
        }
        final MutableLiveData<Resp> liveData = ref.get();
        if (liveData == null) {
            throw new InterruptedException("No strong reference to live data");
        }
        liveData.postValue(value);
    }

    protected abstract void internalRun(final Req req) throws InterruptedException;
}
