package ks.hotelsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ks.hotelsapp.R
import ks.hotelsapp.domain.Hotel
import ks.hotelsapp.domain.HotelsAdapter

class ListOfHotelsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_hotels, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.rcView)
        val testData = listOf(
            Hotel(123, "Отель" , "Broadway, 10", 4.75f,"500 м", 3),
            Hotel(123, "Отель" , "Broadway, 10", 4.75f,"500 м", 3),
            Hotel(123, "Отель" , "Broadway, 10", 4.75f,"500 м", 3),
            Hotel(123, "Отель" , "Broadway, 10", 4.75f,"500 м", 3),
            Hotel(123, "Отель" , "Broadway, 10", 4.75f,"500 м", 3),
            Hotel(123, "Отель" , "Broadway, 10", 4.75f,"500 м", 3),

        )

        val adapter = HotelsAdapter(testData)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

}