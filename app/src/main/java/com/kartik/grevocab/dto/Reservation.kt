package com.kartik.grevocab.dto

import org.threeten.bp.LocalTime

class Reservation(
    var partySize: Long? = null,
    var time: LocalTime? = null,
    var guestDetails: GuestDetails? = null
)
