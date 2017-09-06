package ua.meugen.android.client.activities.main.api;

import java.util.List;

import io.reactivex.Observable;

public interface StorageApi {

    Observable<List<String>> storage(int id);
}
