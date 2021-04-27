import java.util.*

fun main() {
    val original = "kot"
    var refs = original
    println(original=="kot")
    println(original==="kot")

    val sc = Scanner(System.`in`)
    refs = sc.next()
    println(refs=="kot")
    println(refs==="kot")

}