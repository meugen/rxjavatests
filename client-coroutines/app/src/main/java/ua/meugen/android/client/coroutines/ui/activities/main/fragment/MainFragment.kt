package ua.meugen.android.client.coroutines.ui.activities.main.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ua.meugen.android.client.coroutines.databinding.FragmentMainBinding
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.BaseFragment
import ua.meugen.android.client.coroutines.ui.activities.base.fragment.state.MvpState
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.adapters.ItemsAdapter
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.presenter.MainPresenter
import ua.meugen.android.client.coroutines.ui.activities.main.fragment.view.MainView
import javax.inject.Inject

class MainFragment: BaseFragment<MvpState, MainPresenter>(), MainView {

    private lateinit var binding: FragmentMainBinding

    @Inject lateinit var adapter: ItemsAdapter

    override fun onCreateView(
            inflater: LayoutInflater?,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater!!, container, false)
        return binding.root
    }

    override fun onViewCreated(
            view: View?,
            savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.addItemDecoration(DividerItemDecoration(
                context, DividerItemDecoration.VERTICAL))
        binding.recycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.load()
    }

    override fun displayItems(items: List<String>) {
        adapter.swapItems(items)
    }
}