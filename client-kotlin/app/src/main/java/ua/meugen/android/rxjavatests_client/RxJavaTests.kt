package ua.meugen.android.rxjavatests_client

import android.app.Application
import android.content.Context
import ua.meugen.android.rxjavatests_client.presenter.injections.AppComponent
import ua.meugen.android.rxjavatests_client.presenter.injections.DaggerAppComponent

/**
 * @author meugen
 */

class RxJavaTests : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

fun Context.aqosta() = applicationContext as RxJavaTests

fun Context.appComponent() = aqosta().appComponent
