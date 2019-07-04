package meugeninua.rxjavatests.client.ui.fragments.base.binding;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class BaseBindingImpl implements Binding {

    private WeakReference<View> rootViewRef;
    private SparseArray<WeakReference<View>> childrenViewRefs;

    @Override
    public void attachView(@NonNull final View view) {
        rootViewRef = new WeakReference<>(view);
        childrenViewRefs = new SparseArray<>();
    }

    @Override
    public void detachView() {
        rootViewRef = null;
        childrenViewRefs = null;
    }

    @Nullable
    private <V extends View> V getNullable(int id) {
        if (rootViewRef == null || childrenViewRefs == null) {
            return null;
        }
        WeakReference<View> childViewRef = childrenViewRefs.get(id);
        View childView = childViewRef == null ? null : childViewRef.get();
        if (childView == null) {
            View rootView = rootViewRef.get();
            childView = rootView == null ? null : rootView.findViewById(id);
            if (childView != null) {
                childrenViewRefs.put(id, new WeakReference<>(childView));
            }
        }
        return (V) childView;
    }

    @NonNull
    @Override
    public <V extends View> V get(final int id) {
        V view = getNullable(id);
        if (view == null) {
            throw new IllegalArgumentException("View with id " + id + " is not found");
        }
        return view;
    }

    @Override
    public boolean has(final int id) {
        return getNullable(id) != null;
    }
}
