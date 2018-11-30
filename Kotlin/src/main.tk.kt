fun main(agrs : Array<String>){

    val temperature = 37.5
    val count : Int = 5

    var name = "Sam"

    name = "Fred"


    fun calculateTemperature(celcius : Double) : Double {
        return 9/5 * celcius + 32
    }

    println("Temp = ${calculateTemperature(celcius = 20.0)}")


    val intValue = "32".toInt()
    println("inValue = $intValue")

    val intString = 32.toString();
    println("intString = $intString")


    val fahrenheit = 32

    when(fahrenheit){
        in 0..30 -> println(" Really cold")
        in 31..40 -> println("Getting colder")
        in 41..50 -> println("Kind of cold")
        in 51..60 -> println("Nippy")
        in 71..80 -> println("Just right")
    }

    val nullableName : String? = "asd"
    val length = nullableName?.length ?: -1


    println(length);





}