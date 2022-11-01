package com.kartik.grevocab.adapters.display

import com.kartik.grevocab.R
import com.kartik.grevocab.base.ResProvider
import com.kartik.grevocab.utility.TimeUtils
import org.threeten.bp.LocalTime

data class TimeDisplay(val time: LocalTime, val friendlyTime: String, var isAvailable: Boolean, var backgroundColor: Int)

fun LocalTime.toDisplay(timeUtils: TimeUtils, resProvider: ResProvider) = TimeDisplay(
    this,
    timeUtils.getFormattedTime(this),
    true,
    resProvider.getColor(R.color.primaryText)
)
