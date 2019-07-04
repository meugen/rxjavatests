package meugeninua.rxjavatests.client.app.di.impls;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import meugeninua.rxjavatests.client.app.di.AppComponent;
import meugeninua.rxjavatests.client.app.di.Injector;

class ViewModelProviderFactoryImpl extends ViewModelProvider.NewInstanceFactory {

    private final AppComponent component;

    ViewModelProviderFactoryImpl(final AppComponent component) {
        this.component = component;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
        T result = super.create(modelClass);
        if (result instanceof Injector) {
            ((Injector) result).inject(component);
        }
        return result;
    }
}
