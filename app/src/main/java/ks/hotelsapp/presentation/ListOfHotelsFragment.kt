package ks.hotelsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ks.hotelsapp.R
import ks.hotelsapp.domain.HotelsAdapter

@AndroidEntryPoint
class ListOfHotelsFragment : Fragment() {

    private val viewModel: HotelsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_hotels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rcView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            recyclerView.adapter = HotelsAdapter(hotels)
        }

        viewModel.loadHotels()
    }
}