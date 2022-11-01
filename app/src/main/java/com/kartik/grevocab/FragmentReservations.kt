package com.kartik.grevocab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kartik.grevocab.adapters.OnBindClickListener
import com.kartik.grevocab.adapters.ReservationsAdapter
import com.kartik.grevocab.adapters.display.ReservationDisplay
import com.kartik.grevocab.base.FragmentBase
import com.kartik.grevocab.databinding.FragmentReservationsBinding
import com.kartik.grevocab.utility.LoaderState
import com.kartik.grevocab.vm.FragmentReservationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FragmentReservations : FragmentBase() {

    lateinit var binding: FragmentReservationsBinding
    private val vm: FragmentReservationViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentReservationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbarTitle(resources.getString(R.string.current_reservations))
        (activity as ActivityMain).setToolbarVisibility(View.VISIBLE)

        val adapter = ReservationsAdapter(object : OnBindClickListener {
            override fun onItemClick(view: View, position: Int, item: Any) {
                val clickedItem = item as ReservationDisplay
                clickedItem.reservation.time?.let {
                    vm.selectedReservation = clickedItem
                    findNavController().navigate(FragmentReservationsDirections.actionFragmentReservationsToFragmentReservationDetails())
                }
            }
        })
        binding.recyclerView.adapter = adapter

        binding.create.setOnClickListener {
            findNavController().navigate(FragmentReservationsDirections.actionFragmentReservationsToFragmentPartySize())
        }

        CoroutineScope(Dispatchers.Main).launch {
            adapter.replaceData(vm.getReservations())
        }
    }

    override fun onResume() {
        super.onResume()
        vm.loaderLiveData.observe(viewLifecycleOwner) {
            when (it) {
                LoaderState.ERROR -> binding.loader.visibility = View.GONE
                LoaderState.LOADING -> binding.loader.visibility = View.VISIBLE
                LoaderState.DONE -> binding.loader.visibility = View.GONE
            }
        }
    }

    override fun onStop() {
        super.onStop()
        vm.loaderLiveData.removeObservers(viewLifecycleOwner)
    }
}
