package ua.meugen.android.rxjavatests_client.presenter.rest

import javax.inject.Inject
import javax.inject.Singleton

import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subjects.BehaviorSubject
import ua.meugen.android.rxjavatests_client.model.DataResponse

@Singleton
class ModelApi @Inject constructor(private val restApi: RestApi) {
    var dataObservable: Observable<DataResponse>? = null
        private set
    private var subscription: Subscription? = null

    fun dataWithDelay(
            delay: Long, count: Int): Observable<DataResponse> {
        if (subscription != null) {
            subscription!!.unsubscribe()
            subscription = null
        }
        val dataSubject = BehaviorSubject.create<DataResponse>()
        subscription = restApi.dataWithDelay(delay, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataSubject.onNext(it) }, { dataSubject.onError(it) })
        dataObservable = dataSubject.asObservable()
        return dataObservable!!
    }
}
