package ua.meugen.android.client.coroutines.ui.activities.base.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.presenter.MvpPresenter
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.BaseState
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.MvpState
import javax.inject.Inject

abstract class BaseFragment<S: MvpState, P: MvpPresenter<S>>: Fragment() {

    @Inject protected lateinit var state: S
    @Inject protected lateinit var presenter: P

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            (state as BaseState).attachBundle(arguments)
        } else {
            (state as BaseState).attachBundle(savedInstanceState)
        }
        presenter.onRestoreState(state)
        (state as BaseState).detachBundle()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        (state as BaseState).attachBundle(outState)
        presenter.onSaveState(state)
        (state as BaseState).detachBundle()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onClear()
    }
}