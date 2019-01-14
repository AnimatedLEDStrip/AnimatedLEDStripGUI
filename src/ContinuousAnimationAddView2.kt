import animatedledstrip.client.AnimationData
import animatedledstrip.leds.Animation
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

class ContinuousAnimationAddView2 : View() {

    private var colorChoiceNumber = 1
    private var selectedColor = Color.ALICEBLUE

    private var centerVBox: VBox by singleAssign()

    private var altButton: JFXButton by singleAssign()
    private var mprButton: JFXButton by singleAssign()
    private var pxmButton: JFXButton by singleAssign()
    private var pxrButton: JFXButton by singleAssign()
    private var pxrtButton: JFXButton by singleAssign()
    private var schButton: JFXButton by singleAssign()
    private var spkButton: JFXButton by singleAssign()
    private var stoButton: JFXButton by singleAssign()

    private var chooseColorButton: JFXButton by singleAssign()
    private var smoothChaseChooseColorButton: JFXButton by singleAssign()
    private var sendColorsButton: JFXButton by singleAssign()
    private var defaultColorSelectButton: JFXButton by singleAssign()

    private var forwardButton: JFXButton by singleAssign()
    private var backwardButton: JFXButton by singleAssign()


    private val buttonGroupAnimations: List<JFXButton>
    private val buttonGroupDirection: List<JFXButton>

    private var multiColorList: MutableList<Long>

    private var colorGridPane: GridPane by singleAssign()
    private var colorGridPaneButtons: HBox by singleAssign()

    private var colorGridPaneList: List<JFXButton>

    private var animation = AnimationData().continuous(true)

    init {

        centerVBox = VBox()

        altButton = JFXButton("Alternate").apply {
            setMinSize(50.0, 50.0)
        }
        mprButton = JFXButton("Multi-Pixel Run").apply {
            setMinSize(50.0, 50.0)
        }
        pxmButton = JFXButton("Pixel Marathon").apply {
            setMinSize(50.0, 50.0)
        }
        pxrButton = JFXButton("Pixel Run").apply {
            setMinSize(50.0, 50.0)
        }
        pxrtButton = JFXButton("Pixel Run with Trail").apply {
            setMinSize(50.0, 50.0)
        }
        schButton = JFXButton("Smooth Chase").apply {
            setMinSize(50.0, 50.0)
        }
        spkButton = JFXButton("Sparkle").apply {
            setMinSize(50.0, 50.0)
        }
        stoButton = JFXButton("Stack Overflow").apply {
            setMinSize(50.0, 50.0)
        }

        forwardButton = JFXButton("Forward").apply {
            setMinSize(50.0, 50.0)
        }
        backwardButton = JFXButton("Backward").apply {
            setMinSize(50.0, 50.0)
        }


        chooseColorButton = JFXButton("Add Color").apply {
            setMinSize(50.0, 50.0)
        }
        smoothChaseChooseColorButton = JFXButton("Add Color").apply {
            setMinSize(50.0, 50.0)
        }
        sendColorsButton = JFXButton("Send Colors").apply {
            setMinSize(50.0, 50.0)
        }

        defaultColorSelectButton = JFXButton("Send Default Colors").apply {
            setMinSize(50.0, 50.0)
        }

        colorGridPane = gridpane {
            alignment = Pos.CENTER
            colorRows.forEach { list ->
                row {
                    alignment = Pos.CENTER
                    list.forEach { color ->
                        this += JFXButton().apply {
                            setMinSize(35.0, 35.0)
                            alignment = Pos.CENTER

                            style {
                                backgroundColor += color
                            }
                            action {
                                selectedColor = color
                                chooseColorButton.apply {
                                    style {
                                        backgroundColor += color
                                        if (darkColors.contains(color)) textFill = Color.WHITE
                                        alignment = Pos.CENTER
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        colorGridPaneButtons = hbox {
            this += defaultColorSelectButton
            this += chooseColorButton
            this += sendColorsButton
            alignment = Pos.CENTER
        }

        buttonGroupAnimations = listOf(altButton, mprButton, pxmButton, pxrButton, pxrtButton, schButton, spkButton, stoButton)

        buttonGroupDirection = listOf(forwardButton, backwardButton)

        fun reset() {
            this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
            this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPane)
            this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPaneButtons)
            this@ContinuousAnimationAddView2.centerVBox.children.remove(chooseColorButton)
            this@ContinuousAnimationAddView2.centerVBox.children.remove(sendColorsButton)
            this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupDirection)

            animation = AnimationData().continuous(true)
            multiColorList = mutableListOf()

            this@ContinuousAnimationAddView2.centerVBox.children.addAll(buttonGroupAnimations)
        }

        multiColorList = mutableListOf()

        colorGridPaneList = listOf(defaultColorSelectButton, smoothChaseChooseColorButton, sendColorsButton)

        altButton.apply {
            action {
                animation.animation = Animation.ALTERNATE
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += chooseColorButton
            }
        }
        mprButton.apply {
            action {
                animation.animation = Animation.MULTIPIXELRUN
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += chooseColorButton
            }
        }
        pxmButton.apply {
            action {
                animation.animation = Animation.PIXELMARATHON
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += chooseColorButton
            }
        }
        pxrButton.apply {
            action {
                animation.animation = Animation.PIXELRUN
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += chooseColorButton
            }
        }
        pxrtButton.apply {
            action {
                animation.animation = Animation.PIXELRUNWITHTRAIL
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += chooseColorButton
            }
        }
        schButton.apply {
            action {
                animation.animation = Animation.SMOOTHCHASE
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += colorGridPaneButtons
            }
        }
        spkButton.apply {
            action {
                animation.animation = Animation.SPARKLE
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += chooseColorButton
            }
        }
        stoButton.apply {
            action {
                animation.animation = Animation.STACKOVERFLOW
                this@ContinuousAnimationAddView2.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView2.centerVBox += colorGridPane
                this@ContinuousAnimationAddView2.centerVBox += chooseColorButton
            }
        }


        chooseColorButton.apply {
            action {
                when (animation.animation) {
                    Animation.SPARKLE -> {
                        animation.color(selectedColor.getHex())
                        this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPane)
                        this@ContinuousAnimationAddView2.centerVBox.children.remove(chooseColorButton)
                        this@ContinuousAnimationAddView2.centerVBox.children.remove(sendColorsButton)
                        animation.send()
//                        MessageSender.send(animation)
                        reset()
                    }
                    Animation.ALTERNATE,
                    Animation.MULTIPIXELRUN,
                    Animation.PIXELRUN,
                    Animation.PIXELRUNWITHTRAIL,
                    Animation.STACKOVERFLOW -> {
                        when (colorChoiceNumber) {
                            1 -> {
                                animation.color1(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            2 -> {
                                animation.color2(selectedColor.getHex())
                                colorChoiceNumber = 1
                                when (animation.animation) {
                                    Animation.ALTERNATE, Animation.STACKOVERFLOW -> {
                                        animation.send()
//                                        MessageSender.send(animation)
                                        reset()
                                    }
                                    else -> {
                                        this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPane)
                                        this@ContinuousAnimationAddView2.centerVBox.children.remove(chooseColorButton)
                                        this@ContinuousAnimationAddView2.centerVBox.children.remove(sendColorsButton)
                                        this@ContinuousAnimationAddView2.centerVBox.children.addAll(buttonGroupDirection)
                                    }
                                }
                            }
                            else -> println("colorChoiceNumber out of 1..2")
                        }
                    }
                    Animation.PIXELMARATHON -> {
                        when (colorChoiceNumber) {
                            1 -> {
                                animation.color1(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            2 -> {
                                animation.color2(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            3 -> {
                                animation.color3(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            4 -> {
                                animation.color4(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            5 -> {
                                animation.color5(selectedColor.getHex())
                                colorChoiceNumber = 1
                                this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPane)
                                this@ContinuousAnimationAddView2.centerVBox.children.remove(chooseColorButton)
                                this@ContinuousAnimationAddView2.centerVBox.children.remove(sendColorsButton)
                                animation.send()
                                reset()
                            }
                            else -> println("colorChoiceNumber out of 1..5")
                        }
                    }
                    Animation.SMOOTHCHASE -> {
                        multiColorList.add(selectedColor.getHex().toLong(16))
                    }
                    else -> {
                        println("Animation not supported")
                    }
                }
            }
        }

        smoothChaseChooseColorButton.apply {
            action {
                multiColorList.add(selectedColor.getHex().toLong(16))
            }
        }

        buttonGroupDirection.forEach {
            it.apply {
                action {
                    animation.direction(when (it.text) {
                        "Forward" -> 'F'
                        "Backward" -> 'B'
                        else -> 'F'
                    })
                    animation.send()
                    reset()
                }
            }
        }

        sendColorsButton.apply {
            action {
                animation.color(multiColorList[0])
                animation.colorList(multiColorList)
                this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPane)
                this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPaneButtons)
                this@ContinuousAnimationAddView2.centerVBox.children.addAll(buttonGroupDirection)
            }
        }

        val defaultColorList = listOf<Long>(0xFF0000, 0xD52A00, 0xAB5500, 0xAB7F00, 0xABAB00, 0x56D500, 0x00FF00, 0x00D52A, 0x00AB55, 0x0056AA, 0x0000FF, 0x2A00D5, 0x5500AB, 0x7F0081, 0xAB0055, 0xD5002B)

        defaultColorSelectButton.apply {
            action {
                animation.color(defaultColorList[0])
                animation.colorList(defaultColorList)
                this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPane)
                this@ContinuousAnimationAddView2.centerVBox.children.remove(colorGridPaneButtons)
                this@ContinuousAnimationAddView2.centerVBox.children.addAll(buttonGroupDirection)
            }
        }

        this@ContinuousAnimationAddView2.centerVBox.children.addAll(buttonGroupAnimations)

    }


    override val root = borderpane {

        style {
            backgroundColor += Color.LIGHTSEAGREEN
        }

        addExitAndBlankButtons(this)
        addNavigation(this@ContinuousAnimationAddView2::class, this@ContinuousAnimationAddView2, this)

        center {
            this += centerVBox.apply {
                style {
                    alignment = Pos.CENTER
                }
            }
        }
    }

}
