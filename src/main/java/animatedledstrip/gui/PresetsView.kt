package animatedledstrip.gui


import animatedledstrip.animationutils.*
import animatedledstrip.client.send
import animatedledstrip.colors.ColorContainer
import animatedledstrip.colors.ccpresets.CCBlack
import animatedledstrip.colors.ccpresets.CCPresets
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*
import java.lang.Math.random

class PresetsView : View() {


    private var toggleReverse: JFXButton by singleAssign()
    private var colorButton: JFXButton by singleAssign()
    private var stcButton: JFXButton by singleAssign()
    private var wipeButton: JFXButton by singleAssign()
    private var mtcButton: JFXButton by singleAssign()
    private var selectedColor = Color.ALICEBLUE
    private var selectedDirection = Direction.FORWARD

    private fun changeColor() {
        selectedColor = CCPresets.random().toColor()
        root.apply {
            style {
                backgroundColor += selectedColor
            }
        }
    }

    private fun sendC(color: String) {
        AnimationData().animation(Animation.COLOR).color(color).send()
        changeColor()
    }

    private fun sendWIP(color: String) {
        AnimationData().animation(Animation.WIPE).color(color).direction(selectedDirection).send()
        changeColor()
    }

    private fun sendSTC(color: String) {
        AnimationData().animation(Animation.SPARKLETOCOLOR).color(color).send()
        changeColor()
    }

    private fun sendMTC(color: String) {
        AnimationData().animation(Animation.MULTIPIXELRUNTOCOLOR).color(color).direction(selectedDirection).send()
        changeColor()
    }


    override val root = borderpane {

        val backColor = Color.AQUAMARINE

        style {
            backgroundColor += backColor
        }

        addTopButtons(this@PresetsView, this)
        addNavigation(this@PresetsView::class, this@PresetsView, this)


        val presetAnimations = listOf(
            {
                AnimationData()
                    .animation(Animation.RIPPLE)
                    .center((random() * 200).toInt())
                    .distance((random() * 100).toInt().coerceAtLeast(10))
                    .color(darkColors.random().toColorContainer())
            },
            {
                AnimationData()
                    .animation(Animation.SPLAT)
                    .center((random() * 100).toInt() + 50)
                    .distance((random() * 20).toInt().coerceAtLeast(5))
                    .color(darkColors.random().toColorContainer())
            },
            {
                AnimationData()
                    .animation(Animation.PIXELMARATHON)
                    .addColors(
                        darkColors.random().toColorContainer(),
                        darkColors.random().toColorContainer(),
                        darkColors.random().toColorContainer(),
                        darkColors.random().toColorContainer(),
                        darkColors.random().toColorContainer()
                    )
            },
            {
                AnimationData()
                    .animation(Animation.SMOOTHCHASE)
                    .addColor(
                        ColorContainer(
                            0xFF0000,
                            0xD52A00,
                            0xAB5500,
                            0xAB7F00,
                            0xABAB00,
                            0x56D500,
                            0x00FF00,
                            0x00D52A,
                            0x00AB55,
                            0x0056AA,
                            0x0000FF,
                            0x2A00D5,
                            0x5500AB,
                            0x7F0081,
                            0xAB0055,
                            0xD5002B
                        )
                    )
                    .direction(Direction.FORWARD)
            }
        )

        center {
            vbox {
                alignment = Pos.CENTER

                this += JFXButton("Reset Strip Color").apply {
                    setMinSize(50.0, 50.0)
                    alignment = Pos.CENTER
                    action {
                        AnimationData().color(CCBlack).send()
                    }
                }

                presetAnimations.forEachIndexed { i, animation ->
                    this += JFXButton("Preset $i").apply {
                        setMinSize(50.0, 50.0)
                        alignment = Pos.CENTER

                        action {
                            animation().send()
                        }
                    }
                }
            }
        }


//        left {
//            vbox {
//                alignment = Pos.CENTER
//
//                label {
//                    style {
//                        font = Font(5.0)
//                    }
//                }
//                toggleReverse = JFXButton()
//                this += toggleReverse.apply {
//                    alignment = Pos.CENTER_LEFT
//                    setMinSize(125.0, 50.0)
//                    setMaxSize(125.0, 50.0)
//                    style {
//                        backgroundColor += selectedColor
//                        alignment = Pos.CENTER
//                    }
//                    text = "Direction:\nForwards"
//                    action {
//                        toggleReverse.apply {
//                            when (toggleReverse.text) {
//                                "Direction:\nForwards" -> {
//                                    toggleReverse.text = " Direction:\nBackwards"
//                                    selectedDirection = Direction.BACKWARD
//                                }
//                                " Direction:\nBackwards" -> {
//                                    toggleReverse.text = "Direction:\nForwards"
//                                    selectedDirection = Direction.FORWARD
//                                }
//                                else -> {
//                                    toggleReverse.text = "Direction:\nForwards"
//                                    selectedDirection = Direction.FORWARD
//                                }
//                            }
//                        }
//                    }
//                }
//                label {
//                    style {
//                        font = Font(5.0)
//                    }
//                }
//                colorButton = JFXButton("Color")
//                this += colorButton.apply {
//                    alignment = Pos.CENTER_LEFT
//                    useMaxSize = true
//                    setMinSize(125.0, 50.0)
//                    setMaxSize(125.0, 50.0)
//                    style {
//                        backgroundColor += selectedColor
//                        alignment = Pos.CENTER
//                    }
//                    action {
//                        sendC(selectedColor.getHex())
//                    }
//                }
//                label {
//                    style {
//                        font = Font(5.0)
//                    }
//                }
//                stcButton = JFXButton("STC")
//                this += stcButton.apply {
//                    alignment = Pos.CENTER_LEFT
//                    useMaxSize = true
//                    setMinSize(125.0, 50.0)
//                    setMaxSize(125.0, 50.0)
//                    style {
//                        backgroundColor += selectedColor
//                        alignment = Pos.CENTER
//                    }
//                    action {
//                        sendSTC(selectedColor.getHex())
//                    }
//                }
//                label {
//                    style {
//                        font = Font(5.0)
//                    }
//                }
//                wipeButton = JFXButton("Wipe")
//                this += wipeButton.apply {
//                    alignment = Pos.CENTER_LEFT
//                    useMaxSize = true
//                    setMinSize(125.0, 50.0)
//                    setMaxSize(125.0, 50.0)
//                    style {
//                        backgroundColor += selectedColor
//                        alignment = Pos.CENTER
//                    }
//                    action {
//                        sendWIP(selectedColor.getHex())
//                    }
//                }
//                label {
//                    style {
//                        font = Font(5.0)
//                    }
//                }
//                mtcButton = JFXButton("MTC")
//                this += mtcButton.apply {
//                    alignment = Pos.CENTER_LEFT
//                    useMaxSize = true
//                    setMinSize(125.0, 50.0)
//                    setMaxSize(125.0, 50.0)
//                    style {
//                        backgroundColor += selectedColor
//                        alignment = Pos.CENTER
//                    }
//                    action {
//                        sendMTC(selectedColor.getHex())
//                    }
//                }
//            }
//        }
    }
}

