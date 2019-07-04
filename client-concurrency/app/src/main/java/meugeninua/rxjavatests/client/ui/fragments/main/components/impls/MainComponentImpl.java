package meugeninua.rxjavatests.client.ui.fragments.main.components.impls;

import meugeninua.rxjavatests.client.app.di.AppComponent;
import meugeninua.rxjavatests.client.ui.fragments.base.BaseComponentImpl;
import meugeninua.rxjavatests.client.ui.fragments.main.components.MainBinding;
import meugeninua.rxjavatests.client.ui.fragments.main.components.impls.MainBindingImpl;
import meugeninua.rxjavatests.client.ui.fragments.main.components.MainComponent;

public class MainComponentImpl extends BaseComponentImpl
        implements MainComponent {

    public MainComponentImpl(final AppComponent component) {
        super(component);
    }

    @Override
    public MainBinding provideBinding() {
        return new MainBindingImpl();
    }
}
