import java.util.*
import kotlin.random.Random.Default.nextInt

fun main()
{
    val months = """Styczeń, Luty, Marzec, Kwiecień, Maj, Czerwiec,
                    |Lipiec, Sierpień, Wrzesień, Październik, Listopad,
                    |Grudzień""".trimMargin()
    val list = months.replace('\n', ' ').split(", ")

    val randomMonth = list.random().also {print("$it: ")}
    fun getMonthType(monthName: String) = when (monthName.toLowerCase(Locale.forLanguageTag("PL")))
    {
        in listOf("luty","lipiec","sierpień","wrzesień") -> "Ferie"
        "październik", "listopad", "grudzień", "styczeń" -> "Semestr zimowy"
        "marzec", "kwiecień", "maj", "czerwiec" -> "Semestr letni"
        else -> "Nie ma takiego miesiąca"
    }
    println(getMonthType(randomMonth))

    val randomMonths = Array(nextInt(list.size)) {list.random() }
        .also { println(it.joinToString())}

    fun getMonthsTypes(vararg monthNames: String) = monthNames.associateWith(::getMonthType)

    println(getMonthsTypes(*randomMonths))

}