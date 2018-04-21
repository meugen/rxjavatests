package ua.in.meugen.myapplication.app.di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;
import dagger.android.support.AndroidSupportInjectionModule;
import ua.in.meugen.myapplication.app.MyApp;
import ua.in.meugen.myapplication.app.di.qualifiers.AppContext;
import ua.in.meugen.myapplication.app.di.scopes.PerApplication;

@Module(includes = {AndroidSupportInjectionModule.class,ContributesModule.class})
public abstract class AppModule {

    @Binds  @PerApplication
    abstract Application bindApplication(final MyApp app);

    @Binds @AppContext @PerApplication
    abstract Context bindAppContext(final Application app);
}
