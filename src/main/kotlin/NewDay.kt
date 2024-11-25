package dev.sondre

import java.io.File
import java.nio.file.Files
import kotlin.io.path.Path

fun main() {
    print("What day are you starting to work on now? ")
    val d = readln().toInt()
    println("Alright, day $d it is!\nWorking on creating a new day's environment... 🛠️")
    newDay(d)
    println("""
        🎅 A new day, a new Advent of Code Challenge! 🎅
        
        A template to take on a new day's challenge has been created for you!
        
        Good luck! 🚀
    """.trimIndent())
}

class F(val file: File, val content: String)

fun newDay(nr: Int) {
    val dayNr = if (nr >= 10) nr.toString() else "0$nr"
    val dayPath = "./src/main/kotlin/day$dayNr"
    val resourcePath = "./src/main/resources"
    val testd = F(File("$resourcePath/day${dayNr}test.txt"), "")
    val d = F(File("$resourcePath/day${dayNr}.txt"), "")

    fun packageExists(): Boolean {
        return Files.exists(Path(dayPath))
    }
    if (packageExists()) throw IllegalStateException("Package already exists!")

    val m = F(File("$dayPath/Main.kt"),
        """
            package dev.sondre.day$dayNr

            import dev.sondre.Day

            fun main() {
                val day = Day(listOf(Part1(), Part2()))
                day.all()
            }    
        """.trimIndent()
    )
    val p1 = F(File("$dayPath/Part1.kt"),
        """
            package dev.sondre.day$dayNr

            import dev.sondre.Part
            
            class Part1 : Part() {
                override fun calc(data: String): Int {
                    return 42
                }
            }            
        """.trimIndent()
    )
    val p2 = F(File("$dayPath/Part2.kt"),
        """
            package dev.sondre.day$dayNr

            import dev.sondre.Part
            
            class Part2 : Part() {
                override fun calc(data: String): Int {
                    return 42
                }
            }            
        """.trimIndent()
    )

    listOf(m, p1, p2, testd, d).forEach {
        it.file.parentFile.mkdirs()
        it.file.createNewFile()
        val w = it.file.writer()
        w.write(it.content)
        w.close()
    }
}