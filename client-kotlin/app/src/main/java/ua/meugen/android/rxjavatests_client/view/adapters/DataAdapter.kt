package ua.meugen.android.rxjavatests_client.view.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import kotterknife.bindView


class DataAdapter(context: Context) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private val inflater: LayoutInflater
    private var data: List<String>

    init {
        this.inflater = LayoutInflater.from(context)
        this.data = emptyList<String>()
    }

    fun swapData(data: List<String>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView by bindView(android.R.id.text1)

        fun bind(item: String) {
            textView.text = item
        }
    }
}
