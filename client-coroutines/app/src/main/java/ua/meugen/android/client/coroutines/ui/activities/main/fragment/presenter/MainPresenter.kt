package ua.meugen.android.client.coroutines.ui.activities.main.fragment.presenter

import ua.meugen.android.client.coroutines.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.MvpState

interface MainPresenter: MvpPresenter<MvpState> {

    fun load()
}