package ua.meugen.android.rxjavatests_client.presenter

import android.util.Log

import com.hannesdorfmann.mosby3.mvp.MvpPresenter

import javax.inject.Inject

import rx.Observable
import rx.subscriptions.CompositeSubscription
import ua.meugen.android.rxjavatests_client.model.DataResponse
import ua.meugen.android.rxjavatests_client.presenter.rest.ModelApi
import ua.meugen.android.rxjavatests_client.view.MainView

/**
 * @author meugen
 */

class MainPresenter @Inject constructor(private val modelApi: ModelApi) : MvpPresenter<MainView> {

    private var view: MainView? = null
    private var compositeSubscription: CompositeSubscription? = null

    override fun attachView(view: MainView) {
        this.view = view
        compositeSubscription = CompositeSubscription()
    }

    override fun detachView(retainInstance: Boolean) {
        this.view = null
        compositeSubscription?.unsubscribe()
        compositeSubscription = null;
    }

    fun setup(reload: Boolean) {
        view!!.showProgress()
        var observable: Observable<DataResponse>? = modelApi.dataObservable
        if (reload || observable == null) {
            observable = modelApi.dataWithDelay(DELAY, COUNT)
        }
        val subscription = observable
                .subscribe({ this.onDataSucess(it) }, { this.onDataError(it) })
        compositeSubscription!!.add(subscription)
    }

    fun onDataError(e: Throwable) {
        Log.e(TAG, e.message, e)
    }

    fun onDataSucess(response: DataResponse) {
        Log.i(TAG, "onDataSucess($response)")
        view!!.showData(response.data!!)
    }

    companion object {

        private val TAG = MainPresenter::class.java.simpleName

        private val DELAY = 5000L
        private val COUNT = 100
    }
}
