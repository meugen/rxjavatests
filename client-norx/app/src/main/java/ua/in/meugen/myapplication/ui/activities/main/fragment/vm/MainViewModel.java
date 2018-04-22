package ua.in.meugen.myapplication.ui.activities.main.fragment.vm;

import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Provider;

import ua.in.meugen.myapplication.model.actions.AppActionApi;
import ua.in.meugen.myapplication.model.actions.items.ItemsRequest;
import ua.in.meugen.myapplication.model.network.Resource;
import ua.in.meugen.myapplication.model.network.resp.ItemsResponse;
import ua.in.meugen.myapplication.ui.activities.base.fragment.vm.BaseViewModel;


public class MainViewModel extends BaseViewModel {

    public final MutableLiveData<Resource<ItemsResponse>> itemsLiveData;

    @Inject Provider<AppActionApi<Resource<ItemsResponse>, ItemsRequest>> actionApiProvider;

    @Inject
    MainViewModel() {
        this.itemsLiveData = new MutableLiveData<>();
    }

    public void loadItems() {
        if (itemsLiveData.getValue() != null) {
            // Already loaded or load is started
            return;
        }
        final AppActionApi<Resource<ItemsResponse>, ItemsRequest> actionApi
                = actionApiProvider.get();
        actionApi.attachLiveData(itemsLiveData);
        actionApi.attachRequest(new ItemsRequest(1, 5000L));
        async(actionApi);
    }
}
