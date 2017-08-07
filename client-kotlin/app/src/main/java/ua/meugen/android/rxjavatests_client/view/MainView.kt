package ua.meugen.android.rxjavatests_client.view

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * @author meugen
 */

interface MainView : MvpView {

    fun showData(data: List<String>)

    fun showProgress()
}
