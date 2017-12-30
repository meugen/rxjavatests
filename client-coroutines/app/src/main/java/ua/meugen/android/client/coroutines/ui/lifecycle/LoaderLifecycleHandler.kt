package ua.meugen.android.client.coroutines.ui.lifecycle

import android.content.Context
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import ua.meugen.android.client.coroutines.ui.activities.base.ACTIVITY_CONTEXT
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.suspendCoroutine

class LoaderLifecycleHandler @Inject constructor(@Named(ACTIVITY_CONTEXT) val context: Context, val loaderManager: LoaderManager): LifecycleHandler {

//    @Inject @Named(ACTIVITY_CONTEXT)
//    lateinit var context: Context
//    @Inject lateinit var loaderManager: LoaderManager

    private suspend fun <T> load(id: Int, restart: Boolean, block: suspend () -> T): T {
        if (restart) {
            loaderManager.destroyLoader(id)
        }
        return suspendCoroutine { continuation ->
            loaderManager.initLoader(id, Bundle.EMPTY,
                    LoaderCallbacksImpl<T>(context, block, continuation))
        }
    }

    override suspend fun <T> load(id: Int, block: suspend () -> T): T {
        return load(id, false, block)
    }

    override suspend fun <T> reload(id: Int, block: suspend () -> T): T {
        return load(id, true, block)
    }

    override fun clear(id: Int) {
        loaderManager.destroyLoader(id)
    }
}

class LoaderCallbacksImpl<T>(val context: Context, val block: suspend () -> T, val continuation: Continuation<T>):
        LoaderManager.LoaderCallbacks<T> {

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<T> {
        return LifecycleLoader<T>(context, block)
    }

    override fun onLoaderReset(loader: Loader<T>?) {
        loader!!.cancelLoad()
    }

    override fun onLoadFinished(loader: Loader<T>?, data: T) {
        continuation.resume(data)
    }
}