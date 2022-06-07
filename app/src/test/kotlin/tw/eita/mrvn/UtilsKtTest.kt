package tw.eita.mrvn

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilsKtTest {

    @Test
    fun toReadableTime() {
        val actual1 = 1653373800000L
        val actual2 = 1653379200000L

        val expect1 = "14:30"
        val expect2 = "16:00"

        val timeZone = TimeZone.getTimeZone("Asia/Taipei")

        assertEquals(expect1, actual1.toReadableTime(timeZone))
        assertEquals(expect2, actual2.toReadableTime(timeZone))
    }
}