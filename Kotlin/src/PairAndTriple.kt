fun main(agrs : Array<String>){


    val coordinates = Pair(12,33)
    val (x,y) = coordinates

    println("x is $x and y is $y")

    println("x is ${coordinates.first} and y is ${coordinates.second}")

    val coordinatesDoubles = Pair(2.1,23.2)

    println("x is ${coordinatesDoubles.first} and y is ${coordinatesDoubles.second}")


    val coordinatesMixed = Pair(2.1, 1 )

    println("x is ${coordinatesMixed.first} and y is ${coordinatesMixed.second}")


    val coordinate3D = Triple(1,2,3)


    println("x is ${coordinate3D.first} and y is ${coordinate3D.second} and z is ${coordinate3D.third}")


    val(x4,y4,_) = coordinate3D

    println("x is $x4 and $y4")



}