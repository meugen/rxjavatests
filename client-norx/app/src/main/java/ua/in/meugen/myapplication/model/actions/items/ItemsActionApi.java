package ua.in.meugen.myapplication.model.actions.items;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Response;
import timber.log.Timber;
import ua.in.meugen.myapplication.model.actions.BaseActionApi;
import ua.in.meugen.myapplication.model.network.Resource;
import ua.in.meugen.myapplication.model.network.ServiceApi;
import ua.in.meugen.myapplication.model.network.resp.ItemsResponse;

public class ItemsActionApi extends BaseActionApi<Resource<ItemsResponse>, ItemsRequest> {

    @Inject ServiceApi serviceApi;

    @Inject
    ItemsActionApi() {}

    @Override
    protected void internalRun(final ItemsRequest itemsRequest) throws InterruptedException {
        try {
            postValue(Resource.loading());
            final Response<ItemsResponse> response = serviceApi
                    .storageWithDelay(1, TimeUnit.SECONDS.toMillis(5))
                    .execute();
            if (response.isSuccessful()) {
                postValue(Resource.success(response.body()));
            } else {
                postValue(Resource.error(response));
            }
            Timber.d("LOADED ITEMS");
        } catch (IOException e) {
            Timber.d(e);
            postValue(Resource.error(e));
        }
    }
}
