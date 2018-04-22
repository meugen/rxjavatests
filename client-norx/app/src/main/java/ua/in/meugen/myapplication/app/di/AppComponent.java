package ua.in.meugen.myapplication.app.di;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ua.in.meugen.myapplication.app.MyApp;
import ua.in.meugen.myapplication.app.di.modules.ActionsModule;
import ua.in.meugen.myapplication.app.di.modules.AppModule;
import ua.in.meugen.myapplication.app.di.modules.ContributesModule;
import ua.in.meugen.myapplication.app.di.modules.NetworkModule;
import ua.in.meugen.myapplication.app.di.modules.ViewModelModule;
import ua.in.meugen.myapplication.app.di.scopes.PerApplication;

@Component(modules = {AppModule.class,
        AndroidSupportInjectionModule.class,
        ContributesModule.class,
        NetworkModule.class,
        ViewModelModule.class,
        ActionsModule.class})
@PerApplication
public interface AppComponent extends AndroidInjector<MyApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MyApp> {}
}
