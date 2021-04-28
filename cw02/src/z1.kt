fun main() {
    val p1 = Person("Jan","Kowalski",Sex.MALE)
    println(p1)

    val p2 = "Jan;Nowak;MALE".toPerson()
    println(p2)
    val (firstName, lastName, sex) = p2
    println("$firstName -> $sex")

}

fun String.toPerson(): Person
{
    val parts = this.split(";")
    return Person(parts[0],parts[1],Sex.valueOf(parts[2]))
}