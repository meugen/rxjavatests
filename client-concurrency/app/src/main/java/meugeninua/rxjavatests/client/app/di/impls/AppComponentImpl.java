package meugeninua.rxjavatests.client.app.di.impls;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import meugeninua.rxjavatests.client.app.di.AppComponent;
import okhttp3.OkHttpClient;

public class AppComponentImpl implements AppComponent {

    private ViewModelProvider.Factory viewModelFactory;
    private OkHttpClient httpClient;

    @NonNull
    @Override
    public ViewModelProvider.Factory provideViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelProviderFactoryImpl(this);
        }
        return viewModelFactory;
    }

    @NonNull
    @Override
    public OkHttpClient provideHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient.Builder()
                    .build();
        }
        return httpClient;
    }
}
