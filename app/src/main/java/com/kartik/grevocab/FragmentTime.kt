package com.kartik.grevocab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.kartik.grevocab.adapters.OnBindClickListener
import com.kartik.grevocab.adapters.TimeAdapter
import com.kartik.grevocab.adapters.display.TimeDisplay
import com.kartik.grevocab.base.FragmentBase
import com.kartik.grevocab.databinding.FragmentTimeBinding
import com.kartik.grevocab.vm.FragmentReservationViewModel
import com.kartik.grevocab.vm.FragmentTimeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentTime : FragmentBase() {

    lateinit var binding: FragmentTimeBinding
    private val reservationViewModel: FragmentReservationViewModel by sharedViewModel()
    private val vm: FragmentTimeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle(getString(R.string.time_title))

        val adapter = TimeAdapter(object : OnBindClickListener {
            override fun onItemClick(view: View, position: Int, item: Any) {
                val td = item as TimeDisplay
                if (!td.isAvailable) {
                    showAppSheet(getString(R.string.already_reserved))
                    return
                }
                reservationViewModel.updateReservationTime(td.time)
                findNavController().navigate(FragmentTimeDirections.actionFragmentTimeToFragmentGuestDetails())
            }
        })
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        binding.recyclerView.adapter = adapter

        CoroutineScope(Dispatchers.Main).launch {
            adapter.replaceData(vm.getTimeSlots())
        }
    }
}
