package ua.in.meugen.myapplication.ui.activities.main.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ua.in.meugen.myapplication.app.di.scopes.PerFragment;
import ua.in.meugen.myapplication.model.actions.AppActionApi;
import ua.in.meugen.myapplication.model.actions.items.ItemsActionApi;
import ua.in.meugen.myapplication.model.actions.items.ItemsRequest;
import ua.in.meugen.myapplication.model.network.Resource;
import ua.in.meugen.myapplication.model.network.resp.ItemsResponse;
import ua.in.meugen.myapplication.ui.activities.base.fragment.BaseFragment;
import ua.in.meugen.myapplication.ui.activities.base.fragment.BaseFragmentModule;
import ua.in.meugen.myapplication.ui.activities.main.fragment.vm.MainViewModel;

@Module(includes = BaseFragmentModule.class)
public abstract class MainFragmentModule {

    @Binds @PerFragment
    abstract BaseFragment bindBaseFragment(final MainFragment fragment);

    @Provides @PerFragment
    static MainViewModel provideMainViewModel(
            final Fragment fragment,
            final ViewModelProvider.Factory factory) {
        return ViewModelProviders.of(fragment, factory).get(MainViewModel.class);
    }
}
