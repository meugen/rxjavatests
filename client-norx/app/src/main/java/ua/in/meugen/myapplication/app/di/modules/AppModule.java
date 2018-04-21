package ua.in.meugen.myapplication.app.di.modules;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ua.in.meugen.myapplication.app.MyApp;
import ua.in.meugen.myapplication.app.di.qualifiers.AppContext;
import ua.in.meugen.myapplication.app.di.scopes.PerApplication;

@Module
public abstract class AppModule {

    @Binds  @PerApplication
    abstract Application bindApplication(final MyApp app);

    @Binds @AppContext @PerApplication
    abstract Context bindAppContext(final Application app);

    @Provides @PerApplication
    static ExecutorService provideExecutorService() {
        return Executors.newCachedThreadPool();
    }
}
