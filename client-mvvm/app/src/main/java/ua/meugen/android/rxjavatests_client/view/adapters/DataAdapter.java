package ua.meugen.android.rxjavatests_client.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private final LayoutInflater inflater;
    private List<String> data;

    public DataAdapter(final Context context) {
        this.inflater = LayoutInflater.from(context);
        this.data = Collections.emptyList();
    }

    public void swapData(final List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public DataViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataViewHolder holder, final int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.text1) TextView textView;

        public DataViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final String item) {
            textView.setText(item);
        }
    }
}
