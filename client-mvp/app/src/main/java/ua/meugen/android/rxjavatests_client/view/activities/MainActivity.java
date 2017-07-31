package ua.meugen.android.rxjavatests_client.view.activities;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.meugen.android.rxjavatests_client.R;
import ua.meugen.android.rxjavatests_client.RxJavaTests;
import ua.meugen.android.rxjavatests_client.presenter.MainPresenter;
import ua.meugen.android.rxjavatests_client.view.MainView;
import ua.meugen.android.rxjavatests_client.view.adapters.DataAdapter;

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swiperefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;
    @BindView(R.id.progressbar) ProgressBar progressBar;

    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        swipeRefreshLayout.setOnRefreshListener(this);
        presenter.setup(false);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return RxJavaTests.appComponent(this).createMainPresenter();
    }

    @Override
    public void showData(final List<String> data) {
        if (adapter == null) {
            adapter = new DataAdapter(this);
            recyclerView.setAdapter(adapter);
        }
        adapter.swapData(data);

        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        presenter.setup(true);
    }
}
