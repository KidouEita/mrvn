package tw.eita.mrvn.data

import android.graphics.Color

data class CraftBundle(
    val bundle: String,
    val start: Long = 0L,
    val end: Long = 0L,
    val bundleType: String,
    val bundleContent: List<Craft>
)

data class Craft(
    val item: String,
    val cost: Int,
    val itemType: ItemType
)

data class ItemType(
    val name: String,
    val rarity: String,
    val asset: String,
    val rarityHex: String
) {
    val rarityColor get() = Color.parseColor(rarityHex)
}