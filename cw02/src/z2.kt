fun main()
{
    println(100.0)
    println(100.0.K)
    println(100.0.F)
}

val Double.K: Double
    get() = this + 273.15

val Double.F: Double
    get() = this * 1.8 + 32.0