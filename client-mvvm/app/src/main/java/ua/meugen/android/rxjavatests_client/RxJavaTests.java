package ua.meugen.android.rxjavatests_client;

import android.app.Application;
import android.content.Context;

import ua.meugen.android.rxjavatests_client.viewmodel.injections.AppComponent;
import ua.meugen.android.rxjavatests_client.viewmodel.injections.AppModule;
import ua.meugen.android.rxjavatests_client.viewmodel.injections.DaggerAppComponent;

/**
 * @author meugen
 */

public class RxJavaTests extends Application {

    public static RxJavaTests from(final Context context) {
        return (RxJavaTests) context.getApplicationContext();
    }

    public static AppComponent appComponent(final Context context) {
        return from(context).getAppComponent();
    }

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
