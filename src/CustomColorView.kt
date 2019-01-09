import animatedledstrip.leds.Animations
import animatedledstrip.leds.Direction
import com.jfoenix.controls.JFXButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.paint.Color.*
import javafx.scene.text.Font
import tornadofx.*

class CustomColorView : View() {

    private var colorButton: JFXButton by singleAssign()
    private var stcButton: JFXButton by singleAssign()
    private var wipeButton: JFXButton by singleAssign()
    private var mtcButton: JFXButton by singleAssign()
    private var toggleReverse: JFXButton by singleAssign()
    private var selectedColor = Color.ALICEBLUE
    private var selectedDirection = Direction.FORWARD
    private var colorName: Label by singleAssign()


    private val colorRow1 = listOf(
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
            Color.CADETBLUE
    )
    private val colorRow2 = listOf(
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
            Color.DARKMAGENTA//
    )
    private val colorRow3 = listOf(
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
            Color.DODGERBLUE
    )
    private val colorRow4 = listOf(
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
            Color.INDIANRED//
    )
    private val colorRow5 = listOf(
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
            Color.LIGHTPINK
    )
    private val colorRow6 = listOf(
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
            Color.MEDIUMORCHID//
    )
    private val colorRow7 = listOf(
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
            Color.OLIVE//
    )
    private val colorRow8 = listOf(
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
            Color.POWDERBLUE
    )
    private val colorRow9 = listOf(
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
            Color.SLATEGRAY//
    )
    private val colorRow10 = listOf(
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
    private val colorRows = listOf(
            colorRow1,
            colorRow2,
            colorRow3,
            colorRow4,
            colorRow5,
            colorRow6,
            colorRow7,
            colorRow8,
            colorRow9,
            colorRow10
    )
    private val darkColors = listOf(
            Color.BLACK,
            Color.BLUE,
            Color.BLUEVIOLET,
            Color.BROWN,
            Color.CHOCOLATE,
            Color.CRIMSON,
            Color.DARKBLUE,
            Color.DARKCYAN,
            Color.DARKGREEN,
            Color.DARKMAGENTA,
            Color.DARKOLIVEGREEN,
            Color.DARKORCHID,
            Color.DARKRED,
            Color.DARKSLATEBLUE,
            Color.DARKSLATEGRAY,
            Color.DARKVIOLET,
            Color.DIMGRAY,
            Color.FIREBRICK,
            Color.FORESTGREEN,
            Color.GRAY,
            Color.GREEN,
            Color.INDIANRED,
            Color.INDIGO,
            Color.LIGHTSLATEGRAY,
            Color.MAGENTA,
            Color.MAROON,
            Color.MEDIUMBLUE,
            Color.MEDIUMSLATEBLUE,
            Color.MEDIUMVIOLETRED,
            Color.MIDNIGHTBLUE,
            Color.NAVY,
            Color.OLIVE,
            Color.OLIVEDRAB,
            Color.PALEVIOLETRED,
            Color.PERU,
            Color.PURPLE,
            Color.RED,
            Color.ROYALBLUE,
            Color.SADDLEBROWN,
            Color.SEAGREEN,
            Color.SIENNA,
            Color.SLATEBLUE,
            Color.SLATEGRAY,
            Color.STEELBLUE,
            Color.TEAL
    )
    private val colorNames = mapOf<Color, String>(
            ALICEBLUE to "Alice Blue",
            ANTIQUEWHITE to "Antique White",
            AQUA to "Aqua",
            AQUAMARINE to "Aquamarine",
            AZURE to "Azure",
            BEIGE to "Beige",
            BISQUE to "Bisque",
            BLACK to "Black",
            BLANCHEDALMOND to "Blanched Almond",
            BLUE to "Blue",
            BLUEVIOLET to "Blue Violet",
            BROWN to "Brown",
            BURLYWOOD to "Burly Wood",
            CADETBLUE to "Cadet Blue",
            CHARTREUSE to "Chartreuse",
            CHOCOLATE to "Chocolate",
            CORAL to "Coral",
            CORNFLOWERBLUE to "Cornflower Blue",
            CORNSILK to "Cornsilk",
            CRIMSON to "Crimson",
            CYAN to "Cyan",
            DARKBLUE to "Dark Blue",
            DARKCYAN to "Dark Cyan",
            DARKGOLDENROD to "Dark Golden Rod",
            DARKGRAY to "Dark Gray",
            DARKGREEN to "Dark Green",
            DARKGREY to "Dark Grey",
            DARKKHAKI to "Dark Khaki",
            DARKMAGENTA to "Dark Magenta",
            DARKOLIVEGREEN to "Dark Olive Green",
            DARKORANGE to "Dark Orange",
            DARKORCHID to "Dark Orchid",
            DARKRED to "Dark Red",
            DARKSALMON to "Dark Salmon",
            DARKSEAGREEN to "Dark Sea Green",
            DARKSLATEBLUE to "Dark Slate Blue",
            DARKSLATEGRAY to "Dark Slate Gray",
            DARKSLATEGREY to "Dark Slate Grey",
            DARKTURQUOISE to "Dark Turquoise",
            DARKVIOLET to "Dark Violet",
            DEEPPINK to "Deep Pink",
            DEEPSKYBLUE to "Deep Sky Blue",
            DIMGRAY to "Dim Gray",
            DIMGREY to "Dim Grey",
            DODGERBLUE to "Dodger Blue",
            FIREBRICK to "Fire Brick",
            FLORALWHITE to "Floral White",
            FORESTGREEN to "Forest Green",
            FUCHSIA to "Fuchsia",
            GAINSBORO to "Gainsboro",
            GHOSTWHITE to "Ghost White",
            GOLD to "Gold",
            GOLDENROD to "Golden Rod",
            GRAY to "Gray",
            GREEN to "Green",
            GREENYELLOW to "Green Yellow",
            GREY to "Grey",
            HONEYDEW to "Honey Dew",
            HOTPINK to "Hot Pink",
            INDIANRED to "Indian Red",
            INDIGO to "Indigo",
            IVORY to "Ivory",
            KHAKI to "Khaki",
            LAVENDER to "Lavender",
            LAVENDERBLUSH to "Lavender Blush",
            LAWNGREEN to "Lawn Green",
            LEMONCHIFFON to "Lemon Chiffon",
            LIGHTBLUE to "Light Blue",
            LIGHTCORAL to "Light Coral",
            LIGHTCYAN to "Light Cyan",
            LIGHTGOLDENRODYELLOW to "Light Golden Rod Yellow",
            LIGHTGRAY to "Light Gray",
            LIGHTGREEN to "Light Green",
            LIGHTGREY to "Light Grey",
            LIGHTPINK to "Light Pink",
            LIGHTSALMON to "Light Salmon",
            LIGHTSEAGREEN to "Light Sea Green",
            LIGHTSKYBLUE to "Light Sky Blue",
            LIGHTSLATEGRAY to "Light Slate Gray",
            LIGHTSLATEGREY to "Light Slate Grey",
            LIGHTSTEELBLUE to "Light Steel Blue",
            LIGHTYELLOW to "Light Yellow",
            LIME to "Lime",
            LIMEGREEN to "Lime Green",
            LINEN to "Linen",
            MAGENTA to "Magenta",
            MAROON to "Maroon",
            MEDIUMAQUAMARINE to "Medium Aquamarine",
            MEDIUMBLUE to "Medium Blue",
            MEDIUMORCHID to "Medium Orchid",
            MEDIUMPURPLE to "Medium Purple",
            MEDIUMSEAGREEN to "Medium Sea Green",
            MEDIUMSLATEBLUE to "Medium Slate Blue",
            MEDIUMSPRINGGREEN to "Medium Spring Green",
            MEDIUMTURQUOISE to "Medium Turquoise",
            MEDIUMVIOLETRED to "Medium Violet Red",
            MIDNIGHTBLUE to "Midnight Blue",
            MINTCREAM to "Mint Cream",
            MISTYROSE to "Misty Rose",
            MOCCASIN to "Moccasin",
            NAVAJOWHITE to "Navajo White",
            NAVY to "Navy",
            OLDLACE to "Old Lace",
            OLIVE to "Olive",
            OLIVEDRAB to "Olive Drab",
            ORANGE to "Orange",
            ORANGERED to "Orange Red",
            ORCHID to "Orchid",
            PALEGOLDENROD to "Pale Goldenrod",
            PALEGREEN to "Pale Green",
            PALETURQUOISE to "Pale Turquoise",
            PALEVIOLETRED to "Pale Violet Red",
            PAPAYAWHIP to "Papaya Whip",
            PEACHPUFF to "Peach Puff",
            PERU to "Peru",
            PINK to "Pink",
            PLUM to "Plum",
            POWDERBLUE to "Powder Blue",
            PURPLE to "Purple",
            RED to "Red",
            ROSYBROWN to "Rosy Brown",
            ROYALBLUE to "Royal Blue",
            SADDLEBROWN to "Saddle Brown",
            SALMON to "Salmon",
            SANDYBROWN to "Sandy Brown",
            SEAGREEN to "Sea Green",
            SEASHELL to "Sea Shell",
            SIENNA to "Sienna",
            SILVER to "Silver",
            SKYBLUE to "Sky Blue",
            SLATEBLUE to "Slate Blue",
            SLATEGRAY to "Slate Gray",
            SLATEGREY to "Slate Grey",
            SNOW to "Snow",
            SPRINGGREEN to "Spring Green",
            STEELBLUE to "Steel Blue",
            TAN to "Tan",
            TEAL to "Teal",
            THISTLE to "Thistle",
            TOMATO to "Tomato",
            TRANSPARENT to "Transparent",
            TURQUOISE to "Turquoise",
            VIOLET to "Violet",
            WHEAT to "Wheat",
            WHITE to "White",
            WHITESMOKE to "White Smoke",
            YELLOW to "Yellow",
            YELLOWGREEN to "Yellow Green"
    )

    /*  Helper functions for sending commands */
    private fun sendC(color: String) =
            MessageSender.send(mapOf("Animation" to Animations.COLOR, "Color1" to parseHex(color)))

    private fun sendWIP(color: String) {
        val direction = when (selectedDirection) {
            Direction.BACKWARD -> 'B'
            Direction.FORWARD -> 'F'
        }
        MessageSender.send(mapOf("Animation" to Animations.WIPE, "Color1" to parseHex(color), "Direction" to direction))
    }

    private fun sendSTC(color: String) =
            MessageSender.send(mapOf("Animation" to Animations.SPARKLETOCOLOR, "Color1" to parseHex(color)))

    private fun sendMTC(color: String) {
        val direction = when (selectedDirection) {
            Direction.BACKWARD -> 'B'
            Direction.FORWARD -> 'F'
        }
        MessageSender.send(mapOf("Animation" to Animations.MULTIPIXELRUNTOCOLOR, "Color1" to parseHex(color), "Direction" to direction))
    }


    override val root = borderpane {

        style {
            backgroundColor += Color.GRAY
        }

        addExitAndBlankButtons(this)
        addNavigation(this@CustomColorView::class, this@CustomColorView, this)

        right {
            hbox {
                label("Color") {

                    style {
                        rotate = 270.deg
                        font = Font(40.0)
                    }
                    alignment = Pos.CENTER_RIGHT
                    useMaxHeight = true
                }
                gridpane {
                    alignment = Pos.CENTER_LEFT
                    colorRows.forEach { list ->
                        row {
                            alignment = Pos.CENTER_LEFT
                            list.forEach { color ->
                                this += JFXButton().apply {
                                    setMinSize(35.0, 35.0)
                                    alignment = Pos.CENTER

                                    style {
                                        backgroundColor += color
                                    }
                                    action {
                                        selectedColor = color
                                        colorButton.apply {
                                            style {
                                                backgroundColor += color
                                                if (darkColors.contains(color)) textFill = Color.WHITE
                                                alignment = Pos.CENTER
                                            }
                                        }
                                        stcButton.apply {
                                            style {
                                                backgroundColor += color
                                                if (darkColors.contains(color)) textFill = Color.WHITE
                                                alignment = Pos.CENTER
                                            }
                                        }
                                        wipeButton.apply {
                                            style {
                                                backgroundColor += color
                                                if (darkColors.contains(color)) textFill = Color.WHITE
                                                alignment = Pos.CENTER
                                            }
                                        }
                                        mtcButton.apply {
                                            style {
                                                backgroundColor += color
                                                if (darkColors.contains(color)) textFill = Color.WHITE
                                                alignment = Pos.CENTER
                                            }
                                        }
                                        toggleReverse.apply {
                                            style {
                                                backgroundColor += color
                                                if (darkColors.contains(color)) textFill = Color.WHITE
                                                alignment = Pos.CENTER
                                            }
                                        }
                                        colorName.text = colorNames[color]
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        left {
            vbox {
                useMaxHeight = true
                alignment = Pos.CENTER_LEFT
                label {
                    style {
                        font = Font(5.0)
                    }
                }
                toggleReverse = JFXButton()
                this += toggleReverse.apply {
                    alignment = Pos.CENTER_LEFT
                    setMinSize(125.0, 50.0)
                    setMaxSize(125.0, 50.0)
                    style {
                        backgroundColor += selectedColor
                        alignment = Pos.CENTER
                    }
                    text = "Direction:\nForwards"
                    action {
                        toggleReverse.apply {
                            when (toggleReverse.text) {
                                "Direction:\nForwards" -> {
                                    toggleReverse.text = " Direction:\nBackwards"
                                    selectedDirection = Direction.BACKWARD
                                }
                                " Direction:\nBackwards" -> {
                                    toggleReverse.text = "Direction:\nForwards"
                                    selectedDirection = Direction.FORWARD
                                }
                                else -> {
                                    toggleReverse.text = "Direction:\nForwards"
                                    selectedDirection = Direction.FORWARD
                                }
                            }
                        }
                    }
                }
                label {
                    style {
                        font = Font(5.0)
                    }
                }
                colorButton = JFXButton("Color")
                this += colorButton.apply {
                    alignment = Pos.CENTER_LEFT
                    useMaxSize = true
                    setMinSize(125.0, 50.0)
                    setMaxSize(125.0, 50.0)
                    style {
                        backgroundColor += selectedColor
                        alignment = Pos.CENTER
                    }
                    action {
                        sendC(selectedColor.getHex())
                    }
                }
                label {
                    style {
                        font = Font(5.0)
                    }
                }
                stcButton = JFXButton("STC")
                this += stcButton.apply {
                    alignment = Pos.CENTER_LEFT
                    useMaxSize = true
                    setMinSize(125.0, 50.0)
                    setMaxSize(125.0, 50.0)
                    style {
                        backgroundColor += selectedColor
                        alignment = Pos.CENTER
                    }
                    action {
                        sendSTC(selectedColor.getHex())
                    }
                }
                label {
                    style {
                        font = Font(5.0)
                    }
                }
                wipeButton = JFXButton("Wipe")
                this += wipeButton.apply {
                    alignment = Pos.CENTER_LEFT
                    useMaxSize = true
                    setMinSize(125.0, 50.0)
                    setMaxSize(125.0, 50.0)
                    style {
                        backgroundColor += selectedColor
                        alignment = Pos.CENTER
                    }
                    action {
                        sendWIP(selectedColor.getHex())
                    }
                }
                label {
                    style {
                        font = Font(5.0)
                    }
                }
                mtcButton = JFXButton("MTC")
                this += mtcButton.apply {
                    alignment = Pos.CENTER_LEFT
                    useMaxSize = true
                    setMinSize(125.0, 50.0)
                    setMaxSize(125.0, 50.0)
                    style {
                        backgroundColor += selectedColor
                        alignment = Pos.CENTER
                    }
                    action {
                        sendMTC(selectedColor.getHex())
                    }
                }
            }
        }

        bottom {
            colorName = Label("Alice Blue")
            this += colorName.apply {
                alignment = Pos.CENTER
                font = Font.font(35.0)
                useMaxSize = true
            }
        }
    }
}



