package ua.meugen.android.rxjavatests_client.presenter.injections

import javax.inject.Singleton

import dagger.Component
import ua.meugen.android.rxjavatests_client.presenter.MainPresenter
import ua.meugen.android.rxjavatests_client.view.activities.MainActivity

/**
 * @author meugen
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun createMainPresenter(): MainPresenter
}
