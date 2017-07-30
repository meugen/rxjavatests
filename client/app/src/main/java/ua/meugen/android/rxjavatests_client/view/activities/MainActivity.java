package ua.meugen.android.rxjavatests_client.view.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import javax.inject.Inject;

import ua.meugen.android.rxjavatests_client.R;
import ua.meugen.android.rxjavatests_client.RxJavaTests;
import ua.meugen.android.rxjavatests_client.presenter.MainPresenter;
import ua.meugen.android.rxjavatests_client.view.MainView;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MainView {

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RxJavaTests.appComponent(this).inject(this);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return presenter;
    }
}
