package ua.in.meugen.myapplication.ui.activities.main.fragment.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ua.in.meugen.myapplication.app.di.qualifiers.ActivityContext;
import ua.in.meugen.myapplication.app.di.scopes.PerFragment;
import ua.in.meugen.myapplication.databinding.ItemBinding;

@PerFragment
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemHolder> {

    private final LayoutInflater inflater;
    private List<String> items;

    @Inject
    ItemsAdapter(@ActivityContext final Context context) {
        this.inflater = LayoutInflater.from(context);
        this.items = Collections.emptyList();
    }

    public void swapItems(final List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(
            @NonNull final ViewGroup parent, final int viewType) {
        final ItemBinding binding = ItemBinding.inflate(
                inflater, parent, false);
        return new ItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemHolder holder, final int position) {
        holder.binding.text.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        final ItemBinding binding;

        ItemHolder(final ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
