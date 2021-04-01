package com.github.johnnysc.picsandlogintestapp.ui.pics

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.johnnysc.picsandlogintestapp.R

/**
 * @author Asatryan on 31.03.21
 */
class PicsFragment : Fragment(), PicsClickListener {

    private lateinit var picsViewModel: PicsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        picsViewModel =
            ViewModelProvider(this).get(PicsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pics, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.picsRecyclerView)
        val adapter = PicsAdapter(this)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (picsViewModel.loadMoreData(layoutManager.findLastVisibleItemPosition())) {
                    Handler().postDelayed({
                        picsViewModel.loadMoreData()
                    }, 3000)//imitate network delay
                }
            }
        })

        picsViewModel.dataState.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })
        picsViewModel.init()
        return root
    }


    override fun loadData() {
        Handler().postDelayed({
            picsViewModel.loadData()
        }, 5000)//imitate network delay
    }

    override fun tryLoadDataAgain() {
        picsViewModel.init()
    }

    override fun tryLoadMoreDataAgain() {
        picsViewModel.init()
        Handler().postDelayed({
            picsViewModel.loadMoreData()
        }, 3000)//imitate network delay
    }
}