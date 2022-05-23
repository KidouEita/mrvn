package tw.eita.mrvn.data

import com.squareup.moshi.Json

data class News(
    val title: String? = null,
    val link: String? = null,
    val img: String? = null,
    @Json(name = "short_desc") val description: String? = null
)