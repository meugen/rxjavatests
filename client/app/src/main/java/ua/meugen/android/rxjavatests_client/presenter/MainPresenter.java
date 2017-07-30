package ua.meugen.android.rxjavatests_client.presenter;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import javax.inject.Inject;

import ua.meugen.android.rxjavatests_client.model.rest.Api;
import ua.meugen.android.rxjavatests_client.view.MainView;

/**
 * @author meugen
 */

public class MainPresenter implements MvpPresenter<MainView> {

    private final Api api;

    private MainView view;

    @Inject
    public MainPresenter(final Api api) {
        this.api = api;
    }

    @Override
    public void attachView(final MainView view) {
        this.view = view;
    }

    @Override
    public void detachView(final boolean retainInstance) {
        this.view = null;
    }
}
