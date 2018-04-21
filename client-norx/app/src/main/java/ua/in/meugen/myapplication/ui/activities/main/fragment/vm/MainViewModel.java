package ua.in.meugen.myapplication.ui.activities.main.fragment.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import timber.log.Timber;
import ua.in.meugen.myapplication.model.network.Resource;
import ua.in.meugen.myapplication.model.network.ServiceApi;
import ua.in.meugen.myapplication.model.network.resp.DataResponse;
import ua.in.meugen.myapplication.ui.activities.base.fragment.vm.BaseViewModel;


public class MainViewModel extends BaseViewModel {

    public final MutableLiveData<Resource<DataResponse>> itemsLiveData;

    @Inject ServiceApi serviceApi;

    @Inject
    MainViewModel() {
        itemsLiveData = new MutableLiveData<>();
    }

    public void loadItems() {
        if (itemsLiveData.getValue() != null) {
            // Already loaded or load is started
            return;
        }
        async(this::_loadItems);
    }

    private void _loadItems() {
        itemsLiveData.postValue(Resource.loading());
        try {
            final Response<DataResponse> response = serviceApi
                    .storageWithDelay(1, 5000L)
                    .execute();
            if (response.isSuccessful()) {
                itemsLiveData.postValue(Resource.success(response.body()));
            } else {
                itemsLiveData.postValue(Resource.error(response));
            }
        } catch (IOException e) {
            Timber.d(e);
            itemsLiveData.postValue(Resource.error(e));
        }
    }
}
