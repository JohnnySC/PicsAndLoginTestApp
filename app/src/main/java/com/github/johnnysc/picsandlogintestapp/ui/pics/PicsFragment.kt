package com.github.johnnysc.picsandlogintestapp.ui.pics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.johnnysc.picsandlogintestapp.R

/**
 * @author Asatryan on 31.03.21
 */
class PicsFragment : Fragment() {

    private lateinit var picsViewModel: PicsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        picsViewModel =
                ViewModelProvider(this).get(PicsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_pics, container, false)
        return root
    }
}