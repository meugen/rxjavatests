package meugeninua.rxjavatests.client.model.network.api.impls;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Callable;

import meugeninua.rxjavatests.client.model.network.api.RestApi;
import okhttp3.OkHttpClient;

public class RestApiImpl implements RestApi {

    private final OkHttpClient httpClient;

    public RestApiImpl(final OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @NonNull
    @Override
    public Callable<List<String>> dataWithDelay(final long delay, final int count) {
        return null;
    }
}
