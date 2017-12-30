package ua.meugen.android.client.coroutines.ui.lifecycle

import android.content.Context
import android.support.v4.content.Loader
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class LifecycleLoader<T>(context: Context, val block: suspend () -> T): Loader<T>(context) {

    private val job = Job()
    private var data: T? = null

    override fun onStartLoading() {
        super.onStartLoading()
        if (data == null) {
            forceLoad()
        } else {
            deliverResult(data!!)
        }
    }

    override fun onForceLoad() {
        super.onForceLoad()
        launch(job + UI) {
            val data = block()
            deliverResult(data)
        }
    }

    override fun deliverResult(data: T) {
        super.deliverResult(data)
        this.data = data
    }

    override fun onCancelLoad(): Boolean {
        if (job.isActive) {
            job.cancel()
            return true
        }
        return false
    }
}