package ua.meugen.android.client.coroutines.ui.activities.main.fragment.presenter

import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ua.meugen.android.client.coroutines.model.api.ServerApi
import ua.meugen.android.client.coroutines.model.awaits.await
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.presenter.BasePresenter
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.MvpState
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.view.MainView
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(): BasePresenter<MvpState>(), MainPresenter {

    companion object {
        const val ID = 1
        const val DELAY = 5000L
    }

    private val job = Job()

    @Inject lateinit var view: MainView
    @Inject lateinit var serverApi: ServerApi

    override fun load() {
        launch(job + UI) {
            val response = serverApi.storageWithDelay(ID, DELAY).await()
            view.displayItems(response.data)
        }
    }

    override fun onClear() {
        super.onClear()
        job.cancel()
    }
}