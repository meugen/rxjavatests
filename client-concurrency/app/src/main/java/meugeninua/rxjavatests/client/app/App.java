package meugeninua.rxjavatests.client.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import meugeninua.rxjavatests.client.app.di.AppComponent;
import meugeninua.rxjavatests.client.app.di.impls.AppComponentImpl;

public class App extends Application {

    @NonNull
    public static AppComponent provideAppComponent(@NonNull final Context context) {
        App app = (App) context.getApplicationContext();
        if (app.component == null) {
            app.component = new AppComponentImpl();
        }
        return app.component;
    }

    private AppComponent component;
}
