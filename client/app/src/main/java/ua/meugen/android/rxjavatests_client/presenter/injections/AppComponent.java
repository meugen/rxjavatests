package ua.meugen.android.rxjavatests_client.presenter.injections;

import javax.inject.Singleton;

import dagger.Component;
import ua.meugen.android.rxjavatests_client.view.activities.MainActivity;

/**
 * @author meugen
 */
@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {

    void inject(MainActivity activity);
}
