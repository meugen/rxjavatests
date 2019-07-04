package meugeninua.rxjavatests.client.model.network.api;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.concurrent.Callable;

public interface RestApi {

    @NonNull
    Callable<List<String>> dataWithDelay(long delay, int count);
}
