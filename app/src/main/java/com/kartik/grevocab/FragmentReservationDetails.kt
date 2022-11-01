package com.kartik.grevocab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kartik.grevocab.base.FragmentBase
import com.kartik.grevocab.databinding.FragmentReservationDetailsBinding
import com.kartik.grevocab.vm.FragmentReservationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FragmentReservationDetails : FragmentBase() {

    lateinit var binding: FragmentReservationDetailsBinding
    private val vm: FragmentReservationViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentReservationDetailsBinding.inflate(inflater, container, false)
        binding.vm = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbarTitle(getString(R.string.booking_details_title))
    }
}
