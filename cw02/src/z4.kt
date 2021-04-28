fun main() {
    val FORBIDDEN = 0
    val EXECUTE = 1
    val WRITE = 2
    val READ = 4

    var p = Permission()
    println(READ in p)
    p += READ
    println(READ in p)
    p -= READ
    println(READ in p)

    println(p)
    p++
    println(p)
    p--
    println(p)
}