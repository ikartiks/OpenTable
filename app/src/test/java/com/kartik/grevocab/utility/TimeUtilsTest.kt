package com.kartik.grevocab.utility

import com.kartik.grevocab.MockResProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.threeten.bp.LocalTime

internal class TimeUtilsTest {

    private val resProvider: MockResProvider = mockk()
    private val timeUtils = TimeUtils(resProvider)

    @BeforeEach
    fun preTest() {
        every { resProvider.getColor(any()) } returns 1
    }

    @Test
    fun getFormattedTime() {
        val slots = timeUtils.getAllTimeSlotsFromStartTimeToEndTime()
        val formattedTime = timeUtils.getFormattedTime(slots[0].time)
        assertEquals(formattedTime, "03:00 pm")
    }

    @Test
    fun removeTimeSlotsPostReservation() {
        val slots = timeUtils.getAllTimeSlotsFromStartTimeToEndTime()
        val updatedSlots = timeUtils.removeTimeSlotsPostReservation(slots, LocalTime.of(16, 15))
        val updatedSlotsTwo = timeUtils.removeTimeSlotsPostReservation(updatedSlots, LocalTime.of(15, 15))
        val updatedSlotsThree = timeUtils.removeTimeSlotsPostReservation(updatedSlotsTwo, LocalTime.of(20, 45))
        val updatedSlotsFour = timeUtils.removeTimeSlotsPostReservation(updatedSlotsThree, LocalTime.of(22, 0))
        val availableSlots = updatedSlotsFour.filter { it.isAvailable }
        assertEquals(availableSlots.size, 11)
        assertEquals(availableSlots[0].friendlyTime, "05:15 pm")
    }
}
