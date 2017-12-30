package ua.meugen.android.client.coroutines.app.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ua.meugen.android.client.coroutines.app.di.PerActivity
import ua.meugen.android.client.coroutines.ui.activities.main.MainActivity
import ua.meugen.android.client.coroutines.ui.activities.main.MainActivityModule

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    @PerActivity
    abstract fun contributeMainActivity(): MainActivity
}