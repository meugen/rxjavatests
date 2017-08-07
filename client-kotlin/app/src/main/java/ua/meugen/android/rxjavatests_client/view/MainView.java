package ua.meugen.android.rxjavatests_client.view;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/**
 * @author meugen
 */

public interface MainView extends MvpView {

    void showData(List<String> data);

    void showProgress();
}
