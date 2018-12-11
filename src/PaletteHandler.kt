//import java.io.File
//import java.lang.StringBuilder
//
//object PaletteHandler {
//    private val palettes: MutableMap<String, List<Long>>
//
//    init {
//        palettes = openConfig()
//    }
//    private fun openConfig() : MutableMap<String, List<Long>>{
//        val config = File("LED.config").readLines()
//        val tempMap = mutableMapOf<String, List<Long>>()
//        config.forEach {s ->
//            val tempList = s.split(",")
//            val longList = mutableListOf<Long>()
//            for (i in 1..16) {
//                longList += parseHex(tempList[i])
//            }
//            tempMap += Pair(tempList[0], longList)
//        }
//        return tempMap
//    }
//
//    fun addPalette(name: String, colors: List<Long>) {
//        palettes += Pair(name, colors)
//    }
//
//    fun saveConfig() {
//        val config = File("LED.config")
//        val fileBuilder = StringBuilder()
//        fun StringBuilder.addComma() = this.append(",")
//        palettes.forEach {e ->
//            fileBuilder.append(e.key)
//            e.value.forEach {l ->
//                fileBuilder.addComma()
//                fileBuilder.append(l.toString(16))
//            }
//            fileBuilder.append("\n")
//        }
//        config.writeText(fileBuilder.toString())
//    }
//}