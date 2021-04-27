val FORBIDDEN = 0
val EXECUTE = 1
val WRITE = 2
val READ = 4

fun main() {
    println(checkPermissions(5))
    println(checkPermissions(7))
    println(checkPermissions(1))
    println(checkPermissions(20))
}

fun checkPermissions(perms: Int): Boolean
{
    return perms and READ !=0 && perms and EXECUTE != 0
}