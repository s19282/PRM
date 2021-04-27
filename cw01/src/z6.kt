fun main() {
    val priceList = mapOf(
        "Banan" to 1.20,
        "Masło" to 5.7,
        "Chleb" to 3.40,
        "Herbata" to 7.70
    )
    fun printPrice(product: String) {
        val price: Double? = priceList[product]
        println("Cena produktu: $product ${price?.let {"%.2f zł".format(it)} ?: "Brak na stanie"}")
    }
    printPrice("Ser")
    printPrice("Chleb")
}