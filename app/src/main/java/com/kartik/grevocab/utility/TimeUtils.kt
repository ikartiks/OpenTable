package com.kartik.grevocab.utility

import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class TimeUtils {

    companion object {
        const val INTERVAL_IN_MIN = 15L
        const val RESERVATION_LENGTH_IN_MIN = 60L
        val START_TIME: LocalTime = LocalTime.of(15, 0, 0)
        val END_TIME: LocalTime = LocalTime.of(22, 0, 0)
    }

    fun getAllTimeSlotsFromStartTimeToEndTime(): List<LocalTime> {
        val timeSlots = mutableListOf<LocalTime>()
        var start = START_TIME
        timeSlots.add(start)
        while (END_TIME.isAfter(start)) {
            start = start.plusMinutes(INTERVAL_IN_MIN)
            timeSlots.add(start)
        }
        return timeSlots
    }

    fun getFormattedTime(localTime: LocalTime, format: String = "hh:mm a"): String {
        return localTime.format(DateTimeFormatter.ofPattern(format))
    }

    fun removeTimeSlotsPostReservation(slots: List<LocalTime>, reservationTime: LocalTime): List<LocalTime> {
        val lastTime = reservationTime.plusMinutes(RESERVATION_LENGTH_IN_MIN)
        val preTime = reservationTime.minusMinutes(RESERVATION_LENGTH_IN_MIN)
        val updatedSlots = mutableListOf<LocalTime>()
        for (slot in slots) {
            if (!(slot.isAfter(preTime) && slot.isBefore(lastTime))) {
                updatedSlots.add(slot)
            }
        }
        return updatedSlots
    }
}
