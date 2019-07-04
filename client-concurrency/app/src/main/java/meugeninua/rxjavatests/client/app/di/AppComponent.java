package meugeninua.rxjavatests.client.app.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import okhttp3.OkHttpClient;

public interface AppComponent {

    @NonNull
    ViewModelProvider.Factory provideViewModelFactory();

    @NonNull
    OkHttpClient provideHttpClient();
}
