package com.kartik.grevocab.utility

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalTime

internal class TimeUtilsTest {

    val timeUtils = TimeUtils()

    @Test
    fun getFormattedTime() {
        val slots = timeUtils.getAllTimeSlotsFromStartTimeToEndTime()
        val formattedTime = timeUtils.getFormattedTime(slots[0])
        assertEquals(formattedTime, "03:00 pm")
    }

    @Test
    fun removeTimeSlotsPostReservation() {
        val slots = timeUtils.getAllTimeSlotsFromStartTimeToEndTime()
        val updatedSlots = timeUtils.removeTimeSlotsPostReservation(slots, LocalTime.of(16, 15))
        val updatedSlotsTwo = timeUtils.removeTimeSlotsPostReservation(updatedSlots, LocalTime.of(15, 15))
        val updatedSlotsThree = timeUtils.removeTimeSlotsPostReservation(updatedSlotsTwo, LocalTime.of(20, 45))
        val updatedSlotsFour = timeUtils.removeTimeSlotsPostReservation(updatedSlotsThree, LocalTime.of(22, 0))
        updatedSlotsFour.toString()
    }
}
