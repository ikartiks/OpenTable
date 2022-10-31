package com.kartik.grevocab.dto

import org.threeten.bp.LocalTime

class Reservation(
    val partySize: Long? = null,
    val time: LocalTime? = null,
    val guestDetails: GuestDetails? = null
)
