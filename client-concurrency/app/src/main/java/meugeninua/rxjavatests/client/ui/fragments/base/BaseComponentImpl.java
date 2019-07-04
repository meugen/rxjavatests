package meugeninua.rxjavatests.client.ui.fragments.base;

import androidx.lifecycle.ViewModelProvider;

import meugeninua.rxjavatests.client.app.di.AppComponent;

public class BaseComponentImpl implements AppComponent {

    private final AppComponent component;

    public BaseComponentImpl(final AppComponent component) {
        this.component = component;
    }

    @Override
    public ViewModelProvider.Factory provideViewModelFactory() {
        return component.provideViewModelFactory();
    }
}
