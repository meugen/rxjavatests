package ua.meugen.android.rxjavatests_client.view.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import ua.meugen.android.rxjavatests_client.R;
import ua.meugen.android.rxjavatests_client.RxJavaTests;
import ua.meugen.android.rxjavatests_client.databinding.ActivityMainBinding;
import ua.meugen.android.rxjavatests_client.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    @Inject MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxJavaTests.appComponent(this).inject(this);
        ActivityMainBinding binding = DataBindingUtil
                .setContentView(this, R.layout.activity_main);
        binding.setModel(model);
        model.setup(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.reset();
    }
}
