package meugeninua.rxjavatests.client.ui.fragments.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import meugeninua.rxjavatests.client.app.App;
import meugeninua.rxjavatests.client.app.di.AppComponent;
import meugeninua.rxjavatests.client.ui.fragments.base.binding.Binding;

public abstract class BaseFragment<C extends AppComponent, B extends Binding> extends Fragment {

    private ViewModelProvider.Factory factory;
    protected B binding;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent component = App.provideAppComponent(requireContext());
        inject(createComponent(component));
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.attachView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.detachView();
    }

    @NonNull
    protected final <VM extends ViewModel> VM getViewModel(final Class<VM> clazz) {
        return ViewModelProviders.of(this, factory).get(clazz);
    }

    @NonNull
    protected abstract C createComponent(@NonNull final AppComponent component);

    @CallSuper
    protected void inject(@NonNull final C component) {
        this.factory = component.provideViewModelFactory();
    }
}
