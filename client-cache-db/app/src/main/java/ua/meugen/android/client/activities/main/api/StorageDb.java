package ua.meugen.android.client.activities.main.api;

import java.util.List;

import io.reactivex.Completable;

public interface StorageDb {

    void storeItems(List<String> items);

    List<String> loadItems();
}
