package ks.hotelsapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ks.hotelsapp.R
import ks.hotelsapp.databinding.FragmentListOfHotelsBinding
import ks.hotelsapp.domain.Hotel
import ks.hotelsapp.domain.HotelsAdapter

@AndroidEntryPoint
class ListOfHotelsFragment : Fragment() {

    private val viewModel: HotelsViewModel by viewModels()
    private var _binding: FragmentListOfHotelsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.rcView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val progressBar: ProgressBar = binding.progressBar

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.hotels.observe(viewLifecycleOwner) { hotels ->
            val hotelsAdapter = HotelsAdapter(hotels,
                onItemClick = { selectedHotel ->
                    onHotelClicked(selectedHotel) //onItemClick = ) // onItemClick = Unit
                })
            recyclerView.adapter = hotelsAdapter
        }

    }

    private fun onHotelClicked(hotel: Hotel) {
        val action = ListOfHotelsFragmentDirections
            .actionListOfHotelsFragmentToHotelDetailsComposeFragment(hotel.id)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}