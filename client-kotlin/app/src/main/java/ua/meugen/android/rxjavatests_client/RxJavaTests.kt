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

    companion object {

        fun from(context: Context): RxJavaTests {
            return context.applicationContext as RxJavaTests
        }

        fun appComponent(context: Context): AppComponent {
            return from(context).appComponent
        }
    }
}
