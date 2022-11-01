package com.kartik.grevocab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kartik.grevocab.base.FragmentBase
import com.kartik.grevocab.databinding.FragmentGuestDetailsBinding
import com.kartik.grevocab.dto.GuestDetails
import com.kartik.grevocab.vm.FragmentGuestDetailsViewModel
import com.kartik.grevocab.vm.FragmentReservationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentGuestDetails : FragmentBase() {

    lateinit var binding: FragmentGuestDetailsBinding
    private val reservationViewModel: FragmentReservationViewModel by sharedViewModel()
    private val vm: FragmentGuestDetailsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentGuestDetailsBinding.inflate(inflater, container, false)
        binding.vm = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle(getString(R.string.time_title))
        binding.confirm.setOnClickListener {
            if (vm.validate()) {
                reservationViewModel.updateReservationGuestDetailsAndProceed(GuestDetails(vm.name.get()!!, vm.notes.get() ?: ""))
                findNavController().navigate(FragmentGuestDetailsDirections.actionFragmentGuestDetailsToFragmentReservations())
            }
        }
    }
}
