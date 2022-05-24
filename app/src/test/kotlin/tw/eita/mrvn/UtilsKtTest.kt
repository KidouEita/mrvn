package tw.eita.mrvn

import org.junit.Test
import org.junit.Assert.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsKtTest {

    @Test
    fun toReadableTime() {
        val testLong1 = 1653373800000L
        val testLong2 = 1653379200000L
        val timeZone = TimeZone.getTimeZone("Asia/Taipei")
        assertEquals("14:30", testLong1.toReadableTime(timeZone))
        assertEquals("16:00", testLong2.toReadableTime(timeZone))
    }
}