package com.kartik.grevocab.utility

import com.kartik.grevocab.R
import com.kartik.grevocab.adapters.display.TimeDisplay
import com.kartik.grevocab.adapters.display.toDisplay
import com.kartik.grevocab.base.ResProvider
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class TimeUtils(private val resProvider: ResProvider) {

    companion object {
        const val INTERVAL_IN_MIN = 15L
        const val RESERVATION_LENGTH_IN_MIN = 60L
        val START_TIME: LocalTime = LocalTime.of(15, 0, 0)
        val END_TIME: LocalTime = LocalTime.of(22, 0, 0)
    }

    fun getAllTimeSlotsFromStartTimeToEndTime(): List<TimeDisplay> {
        val timeSlots = mutableListOf<LocalTime>()
        var start = START_TIME
        timeSlots.add(start)
        while (END_TIME.isAfter(start)) {
            start = start.plusMinutes(INTERVAL_IN_MIN)
            timeSlots.add(start)
        }
        return timeSlots.map { it.toDisplay(this, resProvider) }
    }

    fun getFormattedTime(localTime: LocalTime, format: String = "hh:mm a"): String {
        return localTime.format(DateTimeFormatter.ofPattern(format))
    }

    fun removeTimeSlotsPostReservation(slots: List<TimeDisplay>, reservationTime: LocalTime): List<TimeDisplay> {
        val lastTime = reservationTime.plusMinutes(RESERVATION_LENGTH_IN_MIN)
        val preTime = reservationTime.minusMinutes(RESERVATION_LENGTH_IN_MIN)
        for (slot in slots) {
            val slotTime = slot.time
            if ((slotTime.isAfter(preTime) && slotTime.isBefore(lastTime))) {
                slot.isAvailable = false
                slot.backgroundColor = resProvider.getColor(R.color.divider)
            }
        }
        return slots
    }
}
