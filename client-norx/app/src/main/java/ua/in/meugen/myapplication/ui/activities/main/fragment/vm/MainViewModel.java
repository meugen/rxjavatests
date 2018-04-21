package ua.in.meugen.myapplication.ui.activities.main.fragment.vm;

import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;

import ua.in.meugen.myapplication.model.actions.AppActionApi;
import ua.in.meugen.myapplication.model.actions.items.ItemsRequest;
import ua.in.meugen.myapplication.model.network.Resource;
import ua.in.meugen.myapplication.model.network.resp.DataResponse;
import ua.in.meugen.myapplication.ui.activities.base.fragment.vm.BaseViewModel;


public class MainViewModel extends BaseViewModel {

    public final MutableLiveData<Resource<DataResponse>> itemsLiveData;

    @Inject AppActionApi<Resource<DataResponse>, ItemsRequest> actionApi;

    @Inject
    MainViewModel() {
        itemsLiveData = new MutableLiveData<>();
    }

    public void loadItems() {
        if (itemsLiveData.getValue() != null) {
            // Already loaded or load is started
            return;
        }
//        final AppActionApi<Resource<DataResponse>, ItemsRequest> actionApi
//                = itemsActionApi.get();
        actionApi.attachLiveData(itemsLiveData);
        actionApi.attachRequest(new ItemsRequest(1, 5000L));
        async(actionApi);
    }
}
