package ua.in.meugen.myapplication.app.di;

import dagger.Component;
import dagger.android.AndroidInjector;
import ua.in.meugen.myapplication.app.MyApp;
import ua.in.meugen.myapplication.app.di.modules.AppModule;
import ua.in.meugen.myapplication.app.di.scopes.PerApplication;

@Component(modules = AppModule.class)
@PerApplication
public interface AppComponent extends AndroidInjector<MyApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApp> {}
}
