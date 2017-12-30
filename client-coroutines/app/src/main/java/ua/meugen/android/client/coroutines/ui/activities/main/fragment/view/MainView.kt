package ua.meugen.android.client.coroutines.ui.activities.main.fragment.view

import ua.meugen.android.client.coroutines.ui.activities.base.fragment.view.MvpView

interface MainView: MvpView {

    fun displayItems(items: List<String>)
}