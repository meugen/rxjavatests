package ua.meugen.android.client.coroutines.ui.activities.main

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ua.meugen.android.client.coroutines.app.di.PerActivity
import ua.meugen.android.client.coroutines.app.di.PerFragment
import ua.meugen.android.client.coroutines.ui.activities.base.BaseActivityModule
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.MainFragment
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.MainFragmentModule

@Module(includes = [BaseActivityModule::class])
abstract class MainActivityModule {

    @Binds @PerActivity
    abstract fun bindActivity(activity: MainActivity): AppCompatActivity

    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    @PerFragment
    abstract fun contributeMainFragment(): MainFragment
}