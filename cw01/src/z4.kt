fun main() {
    val months = """Styczeń, Luty, Marzec, Kwiecień, Maj, Czerwiec,
                    |Lipiec, Sierpień, Wrzesień, Październik, Listopad,
                    |Grudzień""".trimMargin()
    val list = months.replace('\n', ' ').split(", ")

    println(list)

    for (i in list.indices)
        println(list[i])

    println("-------------")

    for (month in list)
    {
        if (month.startsWith("L"))  println(month)
    }

    println("-------------")
    list.forEachIndexed{i,string -> if(i%2==0) println("$string ")}

    println("-------------")
    val iterator = list.iterator()

    while (iterator.hasNext())
    {
        println(iterator.next())
    }
    println("-------------")

    fun rec (month: List<String>)
    {
        tailrec fun _rec(i: Int)
        {
            print("${month[i]} ")
            val nextI = i + 1
            if (nextI < month.size) _rec(nextI)
        }
        _rec(0)
    }
    rec(list)

    println("\n-------------")
    println(list.joinToString { it })
//    println(list.joinToString(separator = " "))

    println("-------------")
    list.forEach{if (!it.startsWith("P")) println(it.replace("e","_"))}
}