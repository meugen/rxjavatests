package ua.meugen.android.client.coroutines.ui.activities.base.fragment.state

import android.os.Bundle

abstract class BaseState: MvpState {

    protected var bundle: Bundle? = null

    fun attachBundle(bundle: Bundle?) {
        this.bundle = bundle ?: Bundle.EMPTY
    }

    fun detachBundle() {
        this.bundle = null
    }
}