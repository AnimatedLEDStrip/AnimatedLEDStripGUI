package animatedledstrip.gui


import animatedledstrip.client.send
import animatedledstrip.leds.AnimationData
import animatedledstrip.leds.Animation
import animatedledstrip.leds.Direction
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class CustomColorView : View() {

    private var colorButton: JFXButton by singleAssign()
    private var stcButton: JFXButton by singleAssign()
    private var wipeButton: JFXButton by singleAssign()
    private var mtcButton: JFXButton by singleAssign()
    private var stkButton: JFXButton by singleAssign()
    private var toggleReverse: JFXButton by singleAssign()
    private var selectedColor = Color.ALICEBLUE
    private var selectedDirection = Direction.FORWARD
    private var colorName: Label by singleAssign()


    init {
        colorButton = JFXButton("Color")
        stcButton = JFXButton("STC")
        wipeButton = JFXButton("Wipe")
        mtcButton = JFXButton("MTC")
        stkButton = JFXButton("STK")
    }

    private val sendButtonList = listOf(colorButton, stcButton, wipeButton, mtcButton, stkButton)

    /*  Helper functions for sending commands */
    private fun sendC(color: String) = AnimationData().animation(Animation.COLOR).color(color).send()

    private fun sendWIP(color: String) =
            AnimationData().animation(Animation.WIPE).color(color).direction(selectedDirection).send()

    private fun sendSTK(color: String) =
            AnimationData().animation(Animation.STACK).color(color).direction(selectedDirection).send()

    private fun sendSTC(color: String) =
            AnimationData().animation(Animation.SPARKLETOCOLOR).color(color).send()

    private fun sendMTC(color: String) =
            AnimationData().animation(Animation.MULTIPIXELRUNTOCOLOR).color(color).direction(selectedDirection).send()


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
                    row {
                        vbox {
                            defaultColorColumn.forEach { color ->
                                this += JFXButton().apply {
                                    setMinSize(45.0, 50.0)
                                    alignment = Pos.CENTER

                                    style {
                                        backgroundColor += color
                                    }
                                    action {
                                        selectedColor = color
                                        sendButtonList.forEach { b ->
                                            b.apply {
                                                style {
                                                    backgroundColor += color
                                                    if (darkColors.contains(color)) textFill = Color.WHITE
                                                    alignment = Pos.CENTER
                                                }
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
                                                sendButtonList.forEach { b ->
                                                    b.apply {
                                                        style {
                                                            backgroundColor += color
                                                            if (darkColors.contains(color)) textFill = Color.WHITE
                                                            alignment = Pos.CENTER
                                                        }
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
                    setMinSize(75.0, 50.0)
                    setMaxSize(75.0, 50.0)
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

                sendButtonList.forEach { b ->
                    label {
                        style {
                            font = Font(5.0)
                        }
                    }
                    this += b.apply {
                        alignment = Pos.CENTER_LEFT
                        useMaxSize = true
                        setMinSize(75.0, 50.0)
                        setMaxSize(75.0, 50.0)
                        style {
                            backgroundColor += selectedColor
                            alignment = Pos.CENTER
                        }
                        action {
                            when (b) {
                                colorButton -> sendC(selectedColor.getHex())
                                stcButton -> sendSTC(selectedColor.getHex())
                                wipeButton -> sendWIP(selectedColor.getHex())
                                mtcButton -> sendMTC(selectedColor.getHex())
                                stkButton -> sendSTK(selectedColor.getHex())
                            }
                        }
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



