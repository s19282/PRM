data class Person(val firstName: String, val lastName: String, val sex: Sex)
{
    override fun toString(): String {
        return "Person($firstName;$lastName;$sex)"
    }
}