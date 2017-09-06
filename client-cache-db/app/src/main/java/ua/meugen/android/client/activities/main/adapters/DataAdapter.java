package ua.meugen.android.client.activities.main.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ua.meugen.android.client.databinding.ItemBinding;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ItemViewHolder> {

    private final LayoutInflater inflater;
    private List<String> items;

    public DataAdapter(final Context context) {
        this.inflater = LayoutInflater.from(context);
        this.items = Collections.emptyList();
    }

    public void swapData(final List<String> items) {
        if (items == null) {
            throw new IllegalArgumentException("Items must not be null.");
        }
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ItemViewHolder(ItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.binding.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        public final ItemBinding binding;

        public ItemViewHolder(final ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
