fun main() {
    val ba = BankAccount()
    println(ba)
    val ba2 = BankAccount(100.0)
    println(ba2)
    ba2.payInto(40.0)
    println(ba2)
    ba2.withdraw(90.0)
    println(ba2)
    ba2.withdraw(90.0)
    println(ba2)


}