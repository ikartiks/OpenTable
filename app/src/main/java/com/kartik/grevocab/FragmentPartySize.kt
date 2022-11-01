package com.kartik.grevocab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.kartik.grevocab.base.FragmentBase
import com.kartik.grevocab.databinding.FragmentPartySizeBinding
import com.kartik.grevocab.vm.FragmentReservationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FragmentPartySize : FragmentBase() {

    lateinit var binding: FragmentPartySizeBinding
    private val vm: FragmentReservationViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentPartySizeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle(getString(R.string.party_size_title))

        binding.one.setOnClickListener {
            vm.updateReservationPartySize(1L)
            navigateToSelectTime()
        }
        binding.two.setOnClickListener {
            vm.updateReservationPartySize(2L)
            navigateToSelectTime()
        }
        binding.three.setOnClickListener {
            vm.updateReservationPartySize(3L)
            navigateToSelectTime()
        }
        binding.four.setOnClickListener {
            vm.updateReservationPartySize(4L)
            navigateToSelectTime()
        }
        binding.five.setOnClickListener {
            vm.updateReservationPartySize(5L)
            navigateToSelectTime()
        }
    }

    private fun navigateToSelectTime() {
        findNavController().navigate(FragmentPartySizeDirections.actionFragmentPartySizeToFragmentTime())
    }
}
