package com.kartik.grevocab.utility

import com.kartik.grevocab.adapters.display.TimeDisplay
import com.kartik.grevocab.dto.Reservation

object DataHolder {

    fun addReservation(reservation: Reservation) {
        reservations.add(0, reservation)
    }

    val reservations = mutableListOf<Reservation>()
    var availableTimeSlots: List<TimeDisplay> = ArrayList()
}
