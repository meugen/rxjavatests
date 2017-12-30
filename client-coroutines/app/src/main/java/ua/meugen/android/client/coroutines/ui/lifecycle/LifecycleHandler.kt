package ua.meugen.android.client.coroutines.ui.lifecycle

interface LifecycleHandler {

    suspend fun <T>load(id: Int, block: suspend () -> T): T

    suspend fun <T>reload(id: Int, block: suspend () -> T): T

    fun clear(id: Int)
}