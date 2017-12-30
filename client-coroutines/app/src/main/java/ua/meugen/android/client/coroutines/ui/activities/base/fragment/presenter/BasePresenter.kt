package ua.meugen.android.client.coroutines.ui.activities.base.fragment.presenter

import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.MvpState

abstract class BasePresenter<in S: MvpState>: MvpPresenter<S> {

    override fun onRestoreState(state: S) {}

    override fun onSaveState(state: S) {}

    override fun onClear() {}
}