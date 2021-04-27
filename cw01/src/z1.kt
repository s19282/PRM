fun main() {
    val int1: Int = 123
    val int2: Int = -123
    val int3: Int = 0b1010101
    val int4: Int = 0x12d

    val byte1: Byte = 127
    val byte2: Byte = 0x7F
    val byte3: Byte = 0b1111111

    val short1: Short = 128
    val short2: Short = 0xF12
    val short3: Short = -0b1

    val long1: Long = 4_000_000_000_000
    val long2: Long = 4L
    val long3: Long = -1L

    val float1: Float = .34F
    val float2: Float = 3.14F

    val double1: Double = .000_000_001
    val double2: Double = 2.123e3
    val double3: Double = 3.14

    val char1: Char = 'a'
    val char2: Char = '\u0024'
    val char3: Char = '\n'

    val boolean1: Boolean = true
    val boolean2: Boolean = false

    val intValue = 44
//    val doubleValue: Double = intValue
//    val longValue: Long = intValue
//    val charValue: Char = intValue
    val doubleValue: Double = intValue.toDouble()
    val longValue: Long = intValue.toLong()
    val charValue: Char = intValue.toChar()
}