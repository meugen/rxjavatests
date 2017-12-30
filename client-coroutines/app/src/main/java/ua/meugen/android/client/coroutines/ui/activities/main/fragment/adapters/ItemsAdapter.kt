package ua.meugen.android.client.coroutines.ui.activities.main.fragment.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ua.meugen.android.client.coroutines.app.di.PerFragment
import ua.meugen.android.client.coroutines.databinding.ItemBinding
import ua.meugen.android.client.coroutines.ui.activities.base.ACTIVITY_CONTEXT
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@PerFragment
class ItemsAdapter @Inject constructor(@Named(ACTIVITY_CONTEXT) context: Context):
        RecyclerView.Adapter<ItemHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items: List<String> = Collections.emptyList()

    fun swapItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemHolder?, position: Int) {
        holder!!.binding.item = items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemHolder {
        val binding = ItemBinding.inflate(inflater, parent, false)
        return ItemHolder(binding)
    }
}

class ItemHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root)