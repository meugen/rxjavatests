package ua.in.meugen.myapplication.model.actions;

import android.arch.lifecycle.MutableLiveData;

public interface AppActionApi<Resp, Req> extends Runnable {

    void attachLiveData(MutableLiveData<Resp> liveData);

    void attachRequest(Req req);
}
