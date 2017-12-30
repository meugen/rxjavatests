package ua.meugen.android.client.coroutines.app.di.modules

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ua.meugen.android.client.coroutines.BuildConfig
import ua.meugen.android.client.coroutines.app.ClientCoroutines
import javax.inject.Named
import javax.inject.Singleton

const val APP_CONTEXT = "appContext"

@Module(includes = arrayOf(AndroidSupportInjectionModule::class,
        NetworkModule::class))
abstract class AppModule {

    @Binds @Singleton
    abstract fun bindApplication(clientCoroutines: ClientCoroutines): Application

    @Binds @Named(APP_CONTEXT) @Singleton
    abstract fun bindContext(application: Application): Context
}