package ua.meugen.android.client.coroutines.ui.activities.base.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import ua.meugen.android.client.coroutines.app.di.PerFragment
import ua.meugen.android.client.coroutines.ui.lifecycle.LifecycleHandler
import ua.meugen.android.client.coroutines.ui.lifecycle.LoaderLifecycleHandler

@Module
abstract class BaseFragmentModule {

    @Module
    companion object {

        @JvmStatic @Provides @PerFragment
        fun provideLoaderManager(fragment: Fragment): LoaderManager {
            return fragment.loaderManager
        }
    }

    @Binds @PerFragment
    abstract fun bindLifecycleHandler(handler: LoaderLifecycleHandler): LifecycleHandler
}