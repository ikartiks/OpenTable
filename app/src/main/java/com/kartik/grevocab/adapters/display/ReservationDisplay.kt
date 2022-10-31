package com.kartik.grevocab.adapters.display

import com.kartik.grevocab.dto.Reservation
import com.kartik.grevocab.utility.TimeUtils

data class ReservationDisplay(val reservation: Reservation, val friendlyTime: String, val partySize: String)

fun Reservation.toDisplay(timeUtils: TimeUtils) = ReservationDisplay(
    this,
    this.time?.let { timeUtils.getFormattedTime(it) } ?: "",
    partySize = this.partySize.toString()
)
