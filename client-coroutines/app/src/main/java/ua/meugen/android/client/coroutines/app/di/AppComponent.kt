package ua.meugen.android.client.coroutines.app.di

import dagger.Component
import dagger.android.AndroidInjector
import ua.meugen.android.client.coroutines.app.ClientCoroutines
import ua.meugen.android.client.coroutines.app.di.modules.AppModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent: AndroidInjector<ClientCoroutines> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<ClientCoroutines>() {}
}