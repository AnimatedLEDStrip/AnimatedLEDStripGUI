package animatedledstrip.gui


import animatedledstrip.colors.ColorContainer
import javafx.scene.paint.Color

fun ColorContainer.toColor(): Color =
    Color.color((color shr 16 and 0xFF) / 255.0, (color shr 8 and 0xFF) / 255.0, (color and 0xFF) / 255.0)

fun Color.toColorContainer(): ColorContainer =
    ColorContainer(Triple((red * 255.0).toInt(), (green * 255.0).toInt(), (blue * 255.0).toInt()))

fun scaleBrightness(color: String, brightness: Int): String {
    val scale = brightness / 255.0
    val colorRGB = getRGBFromHex(parseHex(color))
    val colorRGBScaled = Triple(
        (colorRGB.first * scale).toInt().toString(16),
        (colorRGB.second * scale).toInt().toString(16),
        (colorRGB.third * scale).toInt().toString(16)
    )
    val colorOut = Triple(
        if (colorRGBScaled.first.length < 2) "0${colorRGBScaled.first}" else colorRGBScaled.first,
        if (colorRGBScaled.second.length < 2) "0${colorRGBScaled.second}" else colorRGBScaled.second,
        if (colorRGBScaled.third.length < 2) "0${colorRGBScaled.third}" else colorRGBScaled.third
    )
    return "${colorOut.first}${colorOut.second}${colorOut.third}".toUpperCase()
}

fun parseHex(string: String): Long = java.lang.Long.parseLong(string, 16)

fun getRGBFromHex(hexIn: Long): Triple<Int, Int, Int> {
    return Triple((hexIn and 0xFF0000 shr 16).toInt(), (hexIn and 0x00FF00 shr 8).toInt(), (hexIn and 0x0000FF).toInt())
}

fun Color.getHex(): String {
    val redScaled = red * 255
    val greenScaled = green * 255
    val blueScaled = blue * 255
    val colorRGB = Triple(redScaled, greenScaled, blueScaled)
    val colorRGBScaled = Triple(
        colorRGB.first.toInt().toString(16),
        colorRGB.second.toInt().toString(16),
        colorRGB.third.toInt().toString(16)
    )
    val colorOut = Triple(
        if (colorRGBScaled.first.length < 2) "0${colorRGBScaled.first}" else colorRGBScaled.first,
        if (colorRGBScaled.second.length < 2) "0${colorRGBScaled.second}" else colorRGBScaled.second,
        if (colorRGBScaled.third.length < 2) "0${colorRGBScaled.third}" else colorRGBScaled.third
    )
    return "${colorOut.first}${colorOut.second}${colorOut.third}".toUpperCase()
}


val colors = listOf(
    Color.ALICEBLUE,
    Color.ANTIQUEWHITE,
    Color.AQUA,
    Color.AQUAMARINE,
    Color.AZURE,
    Color.BEIGE,
    Color.BISQUE,
    Color.BLACK,//
    Color.BLANCHEDALMOND,
    Color.BLUE,//
    Color.BLUEVIOLET,//
    Color.BROWN,//
    Color.BURLYWOOD,
    Color.CADETBLUE,
    Color.CHARTREUSE,
    Color.CHOCOLATE,//
    Color.CORAL,
    Color.CORNFLOWERBLUE,
    Color.CORNSILK,
    Color.CRIMSON,//
    Color.CYAN,
    Color.DARKBLUE,//
    Color.DARKCYAN,//
    Color.DARKGOLDENROD,
    Color.DARKGRAY,
    Color.DARKGREEN,//
    Color.DARKKHAKI,
    Color.DARKMAGENTA,//
    Color.DARKOLIVEGREEN,//
    Color.DARKORANGE,
    Color.DARKORCHID,//
    Color.DARKRED,//
    Color.DARKSALMON,
    Color.DARKSEAGREEN,
    Color.DARKSLATEBLUE,//
    Color.DARKSLATEGRAY,//
    Color.DARKTURQUOISE,
    Color.DARKVIOLET,//
    Color.DEEPPINK,
    Color.DEEPSKYBLUE,
    Color.DIMGRAY,//
    Color.DODGERBLUE,
    Color.FIREBRICK,//
    Color.FLORALWHITE,
    Color.FORESTGREEN,//
    Color.FUCHSIA,
    Color.GAINSBORO,
    Color.GHOSTWHITE,
    Color.GOLD,
    Color.GOLDENROD,
    Color.GRAY,//
    Color.GREEN,//
    Color.GREENYELLOW,
    Color.HONEYDEW,
    Color.HOTPINK,
    Color.INDIANRED,//
    Color.INDIGO,//
    Color.IVORY,
    Color.KHAKI,
    Color.LAVENDER,
    Color.LAVENDERBLUSH,
    Color.LAWNGREEN,
    Color.LEMONCHIFFON,
    Color.LIGHTBLUE,
    Color.LIGHTCORAL,
    Color.LIGHTCYAN,
    Color.LIGHTGOLDENRODYELLOW,
    Color.LIGHTGRAY,
    Color.LIGHTGREEN,
    Color.LIGHTPINK,
    Color.LIGHTSALMON,
    Color.LIGHTSEAGREEN,
    Color.LIGHTSKYBLUE,
    Color.LIGHTSLATEGRAY,//
    Color.LIGHTSTEELBLUE,
    Color.LIGHTYELLOW,
    Color.LIME,
    Color.LIMEGREEN,
    Color.LINEN,
    Color.MAGENTA,//
    Color.MAROON,//
    Color.MEDIUMAQUAMARINE,
    Color.MEDIUMBLUE,//
    Color.MEDIUMORCHID,//
    Color.MEDIUMPURPLE,//
    Color.MEDIUMSEAGREEN,
    Color.MEDIUMSLATEBLUE,//
    Color.MEDIUMSPRINGGREEN,
    Color.MEDIUMTURQUOISE,
    Color.MEDIUMVIOLETRED,//
    Color.MIDNIGHTBLUE,//
    Color.MINTCREAM,
    Color.MISTYROSE,
    Color.MOCCASIN,
    Color.NAVAJOWHITE,
    Color.NAVY,//
    Color.OLDLACE,
    Color.OLIVE,//
    Color.OLIVEDRAB,//
    Color.ORANGE,
    Color.ORANGERED,
    Color.ORCHID,
    Color.PALEGOLDENROD,
    Color.PALEGREEN,
    Color.PALETURQUOISE,
    Color.PALEVIOLETRED,//
    Color.PAPAYAWHIP,
    Color.PEACHPUFF,
    Color.PERU,//
    Color.PINK,
    Color.PLUM,
    Color.POWDERBLUE,
    Color.PURPLE,//
    Color.RED,//
    Color.ROSYBROWN,
    Color.ROYALBLUE,//
    Color.SADDLEBROWN,//
    Color.SALMON,
    Color.SANDYBROWN,
    Color.SEAGREEN,//
    Color.SEASHELL,
    Color.SIENNA,//
    Color.SILVER,
    Color.SKYBLUE,
    Color.SLATEBLUE,//
    Color.SLATEGRAY,//
    Color.SNOW,
    Color.SPRINGGREEN,
    Color.STEELBLUE,//
    Color.TAN,
    Color.TEAL,//
    Color.THISTLE,
    Color.TOMATO,
    Color.TURQUOISE,
    Color.VIOLET,
    Color.WHEAT,
    Color.WHITE,
    Color.WHITESMOKE,
    Color.YELLOW,
    Color.YELLOWGREEN
)