package ua.in.meugen.myapplication.app.di.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ua.in.meugen.myapplication.app.di.keys.ViewModelKey;
import ua.in.meugen.myapplication.app.di.scopes.PerApplication;
import ua.in.meugen.myapplication.ui.activities.base.fragment.vm.ViewModelProviderFactoryImpl;
import ua.in.meugen.myapplication.ui.activities.main.fragment.vm.MainViewModel;

@Module
public abstract class ViewModelModule {

    @Binds @PerApplication
    abstract ViewModelProvider.Factory bindProviderFactory(
            final ViewModelProviderFactoryImpl impl);

    @Binds @IntoMap @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(final MainViewModel viewModel);
}
