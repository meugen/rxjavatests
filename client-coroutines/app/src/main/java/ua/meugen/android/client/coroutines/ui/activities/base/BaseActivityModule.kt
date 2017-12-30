package ua.meugen.android.client.coroutines.ui.activities.base

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import ua.meugen.android.client.coroutines.app.di.PerActivity
import ua.meugen.android.client.coroutines.ui.lifecycle.LifecycleHandler
import ua.meugen.android.client.coroutines.ui.lifecycle.LoaderLifecycleHandler
import javax.inject.Named

const val ACTIVITY_CONTEXT = "activityContext"

@Module
abstract class BaseActivityModule {

    @Binds @Named(ACTIVITY_CONTEXT) @PerActivity
    abstract fun bindContext(activity: AppCompatActivity): Context
}