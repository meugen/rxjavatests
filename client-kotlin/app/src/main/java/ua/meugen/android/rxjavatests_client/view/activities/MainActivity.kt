package ua.meugen.android.rxjavatests_client.view.activities

import android.support.v4.widget.SwipeRefreshLayout
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar

import com.hannesdorfmann.mosby3.mvp.MvpActivity

import kotterknife.bindView
import ua.meugen.android.rxjavatests_client.R
import ua.meugen.android.rxjavatests_client.RxJavaTests
import ua.meugen.android.rxjavatests_client.presenter.MainPresenter
import ua.meugen.android.rxjavatests_client.view.MainView
import ua.meugen.android.rxjavatests_client.view.adapters.DataAdapter

class MainActivity : MvpActivity<MainView, MainPresenter>(),
        MainView, SwipeRefreshLayout.OnRefreshListener {

    private val swipeRefreshLayout: SwipeRefreshLayout by bindView(R.id.swiperefresh)
    private val recyclerView: RecyclerView by bindView(R.id.recyclerview)
    private val progressBar: ProgressBar by bindView(R.id.progressbar)

    private var adapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout.setOnRefreshListener(this)
        presenter.setup(false)
    }

    override fun createPresenter(): MainPresenter {
        return RxJavaTests.appComponent(this).createMainPresenter()
    }

    override fun showData(data: List<String>) {
        if (adapter == null) {
            adapter = DataAdapter(this)
            recyclerView.adapter = adapter
        }
        adapter!!.swapData(data)

        swipeRefreshLayout.isRefreshing = false
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun showProgress() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun onRefresh() {
        presenter.setup(true)
    }
}
