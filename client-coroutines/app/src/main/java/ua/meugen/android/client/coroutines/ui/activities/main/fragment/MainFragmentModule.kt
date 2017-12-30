package ua.meugen.android.client.coroutines.ui.activities.main.fragment

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import ua.meugen.android.client.coroutines.app.di.PerFragment
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.BaseFragmentModule
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.EmptyState
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.MvpState
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.presenter.MainPresenter
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.presenter.MainPresenterImpl
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.view.MainView

@Module(includes = [BaseFragmentModule::class])
abstract class MainFragmentModule {

    @Binds @PerFragment
    abstract fun bindFragment(fragment: MainFragment): Fragment

    @Binds @PerFragment
    abstract fun bindPresenter(presenter: MainPresenterImpl): MainPresenter

    @Binds @PerFragment
    abstract fun bindState(state: EmptyState): MvpState

    @Binds @PerFragment
    abstract fun bindView(fragment: MainFragment): MainView
}