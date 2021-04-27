fun main() {
    val priceList = mapOf(
        "Banan" to 1.20,
        "Masło" to 5.7,
        "Chleb" to 3.40,
        "Herbata" to 7.70
    )
    priceList.forEach{(product,price) ->
        println("%s - %.2f".format(product,price))
    }
    println()
    val salesPriceList = priceList.mapValues { it.value * .2 }
    salesPriceList.forEach{(product,price) ->
        println("%s - %.2f".format(product,price))
    }
}

