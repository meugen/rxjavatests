package ua.in.meugen.myapplication.app.di.modules;

import dagger.Binds;
import dagger.Module;
import ua.in.meugen.myapplication.model.actions.AppActionApi;
import ua.in.meugen.myapplication.model.actions.items.ItemsActionApi;
import ua.in.meugen.myapplication.model.actions.items.ItemsRequest;
import ua.in.meugen.myapplication.model.network.Resource;
import ua.in.meugen.myapplication.model.network.resp.ItemsResponse;

@Module
public abstract class ActionsModule {

    @Binds
    abstract AppActionApi<Resource<ItemsResponse>, ItemsRequest> bindItemsActionApi(
            final ItemsActionApi api);
}
