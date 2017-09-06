package ua.meugen.android.client.activities.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;

import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ua.meugen.android.client.R;
import ua.meugen.android.client.activities.main.adapters.DataAdapter;
import ua.meugen.android.client.activities.main.api.StorageApi;
import ua.meugen.android.client.databinding.ActivityMainBinding;
import ua.meugen.android.client.utils.RxUtils;

public class MainActivity extends AppCompatActivity {

    private static final String PARAM_ID = "id";

    @Inject StorageApi storageApi;
    @Inject RxUtils rxUtils;
    @Inject Random random;

    private ActivityMainBinding binding;
    private CompositeDisposable compositeDisposable;
    private DataAdapter adapter;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main);
        binding.recycler.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        if (savedInstanceState == null) {
            id = random.nextInt(100);
        } else {
            id = savedInstanceState.getInt(PARAM_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PARAM_ID, id);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final Disposable disposable = storageApi.storage(id)
                .compose(rxUtils.observableAsync())
                .subscribe(this::displayData);
        getCompositeDisposable().add(disposable);
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
    }

    private void displayData(final List<String> data) {
        if (adapter == null) {
            adapter = new DataAdapter(this);
            binding.recycler.setAdapter(adapter);
        }
        adapter.swapData(data);
    }
}
