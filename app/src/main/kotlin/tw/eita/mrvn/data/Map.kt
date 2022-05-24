package tw.eita.mrvn.data

data class MapObj(
    val current: CurrentMap,
    val next: Map
)

data class CurrentMap(
    override val start: Long,
    override val end: Long,
    override val map: String,
    override val code: String,
    val asset: String,
    val remainingSecs: Int
) : IMap

data class Map(
    override val start: Long,
    override val end: Long,
    override val map: String,
    override val code: String
) : IMap

interface IMap {
    val start: Long
    val end: Long
    val map: String
    val code: String
}