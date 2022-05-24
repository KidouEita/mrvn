package tw.eita.mrvn

import java.util.*
import java.util.concurrent.TimeUnit

fun Long.toReadableTime(timeZone: TimeZone = TimeZone.getDefault()): String {
    val offset = timeZone.rawOffset
    val actualMilliseconds = this + offset
    val hours = TimeUnit.MILLISECONDS.toHours(actualMilliseconds) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(actualMilliseconds) % 60
    return String.format("%d:%02d", hours, minutes)
}