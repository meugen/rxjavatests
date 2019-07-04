package meugeninua.rxjavatests.client.ui.fragments.base.binding;

import android.view.View;

import androidx.annotation.NonNull;

public interface Binding {

    void attachView(@NonNull View view);

    void detachView();

    @NonNull
    <V extends View> V get(int id);

    boolean has(int id);
}
