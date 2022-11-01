package com.kartik.grevocab.vm

import androidx.lifecycle.ViewModel
import com.kartik.grevocab.adapters.display.TimeDisplay
import com.kartik.grevocab.utility.DataHolder
import com.kartik.grevocab.utility.TimeUtils
import org.koin.core.component.KoinComponent

class FragmentTimeViewModel(private val timeUtils: TimeUtils, val dataHolder: DataHolder) : ViewModel(), KoinComponent {

    suspend fun getTimeSlots(): List<TimeDisplay> {
        if (dataHolder.availableTimeSlots.isEmpty()) {
            dataHolder.availableTimeSlots = timeUtils.getAllTimeSlotsFromStartTimeToEndTime()
        }
        return dataHolder.availableTimeSlots
    }
}
