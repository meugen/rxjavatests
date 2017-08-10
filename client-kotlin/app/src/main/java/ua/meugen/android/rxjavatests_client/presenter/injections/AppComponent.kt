package ua.meugen.android.rxjavatests_client.presenter.injections

import dagger.Component
import ua.meugen.android.rxjavatests_client.presenter.MainPresenter
import javax.inject.Singleton

/**
 * @author meugen
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun createMainPresenter(): MainPresenter
}
