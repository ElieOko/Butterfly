package elieoko.hoshi.butterfly.design

/**
 * Curated Christian/spiritual photography from Unsplash (free to use online).
 * Images are loaded at runtime for a richer, editorial Pinterest-style board.
 */
object SpiritualImagery {
  private const val BASE = "https://images.unsplash.com"

  val heroHome = "$BASE/photo-1507692049860-fc77521a121c?auto=format&fit=crop&w=1400&q=85"
  val bibleOpen = "$BASE/photo-1542812164-7e23f18f2abe?auto=format&fit=crop&w=1400&q=85"
  val prayerHands = "$BASE/photo-1519491059102-cf07f90e7025?auto=format&fit=crop&w=1400&q=85"
  val crossLight = "$BASE/photo-1504052434569-70ad583c22dd?auto=format&fit=crop&w=1400&q=85"
  val churchGlow = "$BASE/photo-1438038929217-1a64db3f0f95?auto=format&fit=crop&w=1400&q=85"
  val worshipCommunity = "$BASE/photo-1529156069898-49953e39b3ac?auto=format&fit=crop&w=1400&q=85"
  val coupleFaith = "$BASE/photo-1516589178581-d125542671e4?auto=format&fit=crop&w=1400&q=85"
  val meditationCalm = "$BASE/photo-1506126613408-eca07ce68773?auto=format&fit=crop&w=1400&q=85"
  val natureSpirit = "$BASE/photo-1452570052504-740c73656645?auto=format&fit=crop&w=1400&q=85"
  val candlePrayer = "$BASE/photo-1518531933037-91b2f5f229cc?auto=format&fit=crop&w=1400&q=85"
  val mountainFaith = "$BASE/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=1400&q=85"
  val scriptureDesk = "$BASE/photo-1491844532315-08cf8d63b9a9?auto=format&fit=crop&w=1400&q=85"

  val cardImages = listOf(
    prayerHands,
    bibleOpen,
    meditationCalm,
    worshipCommunity,
    coupleFaith,
    crossLight,
    candlePrayer,
    natureSpirit,
    mountainFaith,
    scriptureDesk,
    churchGlow,
  )

  fun cardAt(index: Int): String = cardImages[index % cardImages.size]
}
