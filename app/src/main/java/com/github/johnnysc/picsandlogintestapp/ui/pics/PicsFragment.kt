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
import com.github.johnnysc.picsandlogintestapp.databinding.FragmentPicsBinding
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

    private var _binding: FragmentPicsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val picsAdapter = PicsAdapter(this)
        val linearLayoutManager = LinearLayoutManager(context)
        with(binding.picsRecyclerView) {
            layoutManager = linearLayoutManager
            adapter = picsAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (picsViewModel.loadMoreData(linearLayoutManager.findLastVisibleItemPosition()))
                        picsViewModel.loadData()
                }
            })
        }
        picsViewModel.dataState.observe(viewLifecycleOwner, Observer {
            picsAdapter.setData(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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