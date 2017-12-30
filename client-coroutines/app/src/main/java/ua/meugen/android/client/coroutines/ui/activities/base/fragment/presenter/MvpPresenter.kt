package ua.meugen.android.client.coroutines.ui.activities.base.fragment.presenter

import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.MvpState
import javax.inject.Inject

interface MvpPresenter<in S: MvpState> {

    fun onRestoreState(state: S)

    fun onSaveState(state: S)

    fun onClear()
}

class EmptyPresenter @Inject constructor(): BasePresenter<MvpState>()