package ua.meugen.android.client.di;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import ua.meugen.android.client.Client;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent extends AndroidInjector<Client> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<Client> {}
}
