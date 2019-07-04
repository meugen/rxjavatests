package meugeninua.rxjavatests.client.ui.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import meugeninua.rxjavatests.client.R;
import meugeninua.rxjavatests.client.app.di.AppComponent;
import meugeninua.rxjavatests.client.ui.fragments.main.components.MainBinding;
import meugeninua.rxjavatests.client.ui.fragments.main.components.MainComponent;
import meugeninua.rxjavatests.client.ui.fragments.base.BaseFragment;
import meugeninua.rxjavatests.client.ui.fragments.main.components.MainViewModel;
import meugeninua.rxjavatests.client.ui.fragments.main.components.impls.MainComponentImpl;

public class MainFragment extends BaseFragment<MainComponent, MainBinding> {

    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setupRecycler();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = getViewModel(MainViewModel.class);
    }

    @NonNull
    @Override
    protected MainComponent createComponent(@NonNull final AppComponent component) {
        return new MainComponentImpl(component);
    }

    @Override
    protected void inject(@NonNull final MainComponent component) {
        super.inject(component);
        binding = component.provideBinding();
    }
}
