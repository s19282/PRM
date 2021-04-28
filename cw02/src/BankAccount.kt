import java.lang.UnsupportedOperationException

class BankAccount(startBalance: Double)
{
    private var _balance: Double = startBalance
    val balance: Double
        get() = _balance

    constructor() : this(0.0)

    fun payInto(amount: Double)
    {
        _balance += amount
    }
    fun withdraw(amount: Double)
    {
        if(_balance >= amount) _balance -= amount
        else    throw UnsupportedOperationException("Not enough founds!")
    }

    override fun toString(): String {
        return "BankAccount(balance=$balance)"
    }

}