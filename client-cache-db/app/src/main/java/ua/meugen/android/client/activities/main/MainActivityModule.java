package ua.meugen.android.client.activities.main;

import dagger.Binds;
import dagger.Module;
import ua.meugen.android.client.activities.main.api.StorageApi;
import ua.meugen.android.client.activities.main.api.StorageApiImpl;
import ua.meugen.android.client.activities.main.api.StorageDb;
import ua.meugen.android.client.activities.main.api.StorageDbImpl;
import ua.meugen.android.client.di.PerActivity;

@Module
public abstract class MainActivityModule {

    @Binds @PerActivity
    public abstract StorageApi bindStorageApi(final StorageApiImpl impl);

    @Binds @PerActivity
    public abstract StorageDb bindStorageDb(final StorageDbImpl impl);
}
