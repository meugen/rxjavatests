package ua.in.meugen.myapplication.ui.activities.base.fragment.vm;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


public class ViewModelProviderFactoryImpl implements ViewModelProvider.Factory {

    private final Map<Class<? extends  ViewModel>, Provider<ViewModel>> providers;

    @Inject
    ViewModelProviderFactoryImpl(
            final Map<Class<? extends ViewModel>, Provider<ViewModel>> providers) {
        this.providers = providers;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        final Provider<ViewModel> provider = providers.get(modelClass);
        if (provider == null) {
            throw new IllegalArgumentException("Not found provider implementation for class " + modelClass);
        }
        return (T) provider.get();
    }
}
