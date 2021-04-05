package com.github.johnnysc.picsandlogintestapp.ui.pics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.picsandlogintestapp.R
import com.github.johnnysc.picsandlogintestapp.ui.pics.adapter.PicsAdapter
import com.github.johnnysc.picsandlogintestapp.ui.pics.adapter.PicsClickListener

/**
 * Экран со списком изображений
 *
 * @author Asatryan on 31.03.21
 */
class PicsFragment : Fragment(),
    PicsClickListener {

    private val picsViewModel by viewModels<PicsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_pics, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.picsRecyclerView)
        val adapter = PicsAdapter(this)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (picsViewModel.loadMoreData(layoutManager.findLastVisibleItemPosition()))
                    picsViewModel.loadData()
            }
        })

        picsViewModel.dataState.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        return root
    }

    override fun loadData() {
        picsViewModel.loadData()
    }

    override fun tryLoadDataAgain() {
        picsViewModel.loadData()
    }

    override fun tryLoadMoreDataAgain() {
        picsViewModel.loadData()
    }
}