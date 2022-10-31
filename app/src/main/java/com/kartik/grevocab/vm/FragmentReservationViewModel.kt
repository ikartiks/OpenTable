package com.kartik.grevocab.vm

import androidx.lifecycle.ViewModel
import com.kartik.grevocab.adapters.display.ReservationDisplay
import com.kartik.grevocab.adapters.display.toDisplay
import com.kartik.grevocab.base.SingleLiveEvent
import com.kartik.grevocab.dto.Reservation
import com.kartik.grevocab.utility.DataHolder
import com.kartik.grevocab.utility.LoaderState
import com.kartik.grevocab.utility.TimeUtils
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent

class FragmentReservationViewModel(private val timeUtils: TimeUtils, private val dataHolder: DataHolder) : ViewModel(), KoinComponent {

    val loaderLiveData = SingleLiveEvent<LoaderState>()
    suspend fun getReservations(): List<ReservationDisplay> {
        loaderLiveData.value = LoaderState.LOADING
        delay(250)
        val list = mutableListOf<ReservationDisplay>()
        list.addAll(dataHolder.reservations.map { it.toDisplay(timeUtils) })
        if (list.isEmpty()) {
            list.add(ReservationDisplay(Reservation(null, null, null), "", "No reservations yet"))
        }
        loaderLiveData.value = LoaderState.DONE
        return list
    }
}
