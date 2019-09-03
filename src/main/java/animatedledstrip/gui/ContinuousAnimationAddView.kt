package animatedledstrip.gui

import animatedledstrip.animationutils.*
import animatedledstrip.client.send
import animatedledstrip.colors.ColorContainer
import animatedledstrip.colors.ccpresets.CCBlack
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*
import java.lang.Math.random

class ContinuousAnimationAddView : View() {

    private var colorChoiceNumber = 1
    private var selectedColor = Color.ALICEBLUE

    private var centerVBox: VBox by singleAssign()
    private var sideVBox: VBox by singleAssign()

    private var altButton: JFXButton by singleAssign()
    private var bncButton: JFXButton by singleAssign()
    private var mprButton: JFXButton by singleAssign()
    private var pxmButton: JFXButton by singleAssign()
    private var pxrButton: JFXButton by singleAssign()
    private var pxrtButton: JFXButton by singleAssign()
    private var schButton: JFXButton by singleAssign()
    private var smfButton: JFXButton by singleAssign()
    private var spkButton: JFXButton by singleAssign()
    private var spfButton: JFXButton by singleAssign()
    private var stoButton: JFXButton by singleAssign()

    private var testButton: JFXButton by singleAssign()
    private var testButton2: JFXButton by singleAssign()
    private var testButton3: JFXButton by singleAssign()
    private var testButton4: JFXButton by singleAssign()
    private var testButton5: JFXButton by singleAssign()
    private var testButton6: JFXButton by singleAssign()
    private var testButton7: JFXButton by singleAssign()
    private var testButton8: JFXButton by singleAssign()

    private var chooseColorButton: JFXButton by singleAssign()
    private var smoothChaseChooseColorButton: JFXButton by singleAssign()
    private var sendColorsButton: JFXButton by singleAssign()
    private var defaultColorSelectButton: JFXButton by singleAssign()

    private var forwardButton: JFXButton by singleAssign()
    private var backwardButton: JFXButton by singleAssign()


    private val buttonGroupAnimations: List<JFXButton>
    private val buttonGroupDirection: List<JFXButton>

    private val buttonGroupTest: List<JFXButton>

    private var multiColorList: MutableList<Long>

    private var colorGridPane: GridPane by singleAssign()
    private var colorGridPaneButtons: HBox by singleAssign()

    private var colorGridPaneList: List<JFXButton>

    private var animation = AnimationData().continuous(true)

    init {

        centerVBox = VBox()
        sideVBox = VBox()

        altButton = JFXButton("Alternate").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        bncButton = JFXButton("Bounce").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        mprButton = JFXButton("Multi-Pixel Run").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        pxmButton = JFXButton("Pixel Marathon").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        pxrButton = JFXButton("Pixel Run").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        pxrtButton = JFXButton("Pixel Run with Trail").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        schButton = JFXButton("Smooth Chase").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        smfButton = JFXButton("Smooth Fade").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        spkButton = JFXButton("Sparkle").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        spfButton = JFXButton("Sparkle Fade").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        stoButton = JFXButton("Stack Overflow").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }

        testButton = JFXButton("SPLAT").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        testButton2 = JFXButton("Reset").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        testButton3 = JFXButton("TEST").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        testButton4 = JFXButton("TEST").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        testButton5 = JFXButton("TEST").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        testButton6 = JFXButton("TEST").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        testButton7 = JFXButton("TEST").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        }
        testButton8 = JFXButton("TEST").apply {
            setMinSize(50.0, 30.0)
            addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
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
            this += smoothChaseChooseColorButton
            this += sendColorsButton
            alignment = Pos.CENTER
        }

        buttonGroupAnimations = listOf(
            altButton,
            bncButton,
            mprButton,
            pxmButton,
            pxrButton,
            pxrtButton,
            schButton,
            smfButton,
            spkButton,
            spfButton,
            stoButton
        )

        buttonGroupDirection = listOf(forwardButton, backwardButton)

        buttonGroupTest = listOf(
            testButton,
            testButton2,
            testButton3,
            testButton4,
            testButton5,
            testButton6,
            testButton7,
            testButton8
        )

        fun reset() {
            this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
            this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPane)
            this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPaneButtons)
            this@ContinuousAnimationAddView.centerVBox.children.remove(chooseColorButton)
            this@ContinuousAnimationAddView.centerVBox.children.remove(sendColorsButton)
            this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupDirection)

            animation = AnimationData().continuous(true)
            multiColorList = mutableListOf()

            this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)
        }

        multiColorList = mutableListOf()

        colorGridPaneList = listOf(defaultColorSelectButton, smoothChaseChooseColorButton, sendColorsButton)

        altButton.apply {
            action {
                animation.animation = Animation.ALTERNATE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        bncButton.apply {
            action {
                animation.animation = Animation.BOUNCE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        mprButton.apply {
            action {
                animation.animation = Animation.MULTIPIXELRUN
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        pxmButton.apply {
            action {
                animation.animation = Animation.PIXELMARATHON
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        pxrButton.apply {
            action {
                animation.animation = Animation.PIXELRUN
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        pxrtButton.apply {
            action {
                animation.animation = Animation.PIXELRUNWITHTRAIL
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        schButton.apply {
            action {
                animation.animation = Animation.SMOOTHCHASE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += colorGridPaneButtons
            }
        }
        smfButton.apply {
            action {
                animation.animation = Animation.SMOOTHFADE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += colorGridPaneButtons
            }
        }
        spkButton.apply {
            action {
                animation.animation = Animation.SPARKLE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        spfButton.apply {
            action {
                animation.animation = Animation.SPARKLEFADE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }
        stoButton.apply {
            action {
                animation.animation = Animation.STACKOVERFLOW
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox += colorGridPane
                this@ContinuousAnimationAddView.centerVBox += chooseColorButton
            }
        }


        testButton.apply {
            action {
                animation.animation = Animation.PIXELMARATHON
                for (i in 0..4) animation.addColor(darkColors.random().toColorContainer())
                animation.send()
                reset()
            }
        }

        testButton2.apply {
            action {
                animation.animation = Animation.COLOR
                animation.color(CCBlack)
                animation.send()
                reset()
            }
        }


        testButton3.apply {
            action {
                animation.animation = Animation.RIPPLE
                animation.center = (random() * 100).toInt() + 50
                animation.distance = (random() * 30).toInt().coerceAtLeast(5)
                animation.color(darkColors.random().toColorContainer())
                animation.send()
                reset()
            }
        }

        testButton4.apply {
            action {
                animation.animation = Animation.SPLAT
                animation.center = (random() * 100).toInt() + 50
                animation.distance = (random() * 20).toInt().coerceAtLeast(5)
                animation.color(darkColors.random().toColorContainer())
                animation.send()
                reset()
            }
        }
        testButton5.apply {
            action {
            }
        }
        testButton6.apply {
            action {
            }
        }
        testButton7.apply {
            action {
            }
        }
        testButton8.apply {
            action {
            }
        }

        chooseColorButton.apply {
            action {
                when (animation.animation) {
                    Animation.BOUNCE,
                    Animation.MULTIPIXELRUN,
                    Animation.PIXELRUN,
                    Animation.PIXELRUNWITHTRAIL,
                    Animation.RIPPLE,
                    Animation.SPARKLE,
                    Animation.SPARKLEFADE,
                    Animation.SPLAT -> {
                        animation.color0(selectedColor.getHex())
                        this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPane)
                        this@ContinuousAnimationAddView.centerVBox.children.remove(chooseColorButton)
                        this@ContinuousAnimationAddView.centerVBox.children.remove(sendColorsButton)
                        animation.send()
                        reset()
                    }
                    Animation.ALTERNATE,
                    Animation.STACKOVERFLOW -> {
                        when (colorChoiceNumber) {
                            1 -> {
                                animation.color0(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            2 -> {
                                animation.color1(selectedColor.getHex())
                                colorChoiceNumber = 1
                                when (animation.animation) {
                                    Animation.ALTERNATE, Animation.STACKOVERFLOW -> {
                                        animation.send()
//                                        MessageSender.send(animation)
                                        reset()
                                    }
                                    else -> {
                                        this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPane)
                                        this@ContinuousAnimationAddView.centerVBox.children.remove(chooseColorButton)
                                        this@ContinuousAnimationAddView.centerVBox.children.remove(sendColorsButton)
                                        if (animation.animation == Animation.STACKOVERFLOW) {
                                            animation.send()
                                            reset()
                                        } else this@ContinuousAnimationAddView.centerVBox.children.addAll(
                                            buttonGroupDirection
                                        )
                                    }
                                }
                            }
                            else -> println("colorChoiceNumber out of 1..2")
                        }
                    }
                    Animation.PIXELMARATHON -> {
                        when (colorChoiceNumber) {
                            1 -> {
                                animation.color0(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            2 -> {
                                animation.color1(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            3 -> {
                                animation.color2(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            4 -> {
                                animation.color3(selectedColor.getHex())
                                colorChoiceNumber++
                            }
                            5 -> {
                                animation.color4(selectedColor.getHex())
                                colorChoiceNumber = 1
                                this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPane)
                                this@ContinuousAnimationAddView.centerVBox.children.remove(chooseColorButton)
                                this@ContinuousAnimationAddView.centerVBox.children.remove(sendColorsButton)
                                animation.send()
                                reset()
                            }
                            else -> println("colorChoiceNumber out of 1..5")
                        }
                    }
                    Animation.SMOOTHCHASE,
                    Animation.SMOOTHFADE -> {
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
                    animation.direction(
                        when (it.text) {
                            "Forward" -> 'F'
                            "Backward" -> 'B'
                            else -> 'F'
                        }
                    )
                    animation.send()
                    reset()
                }
            }
        }

        sendColorsButton.apply {
            action {
                animation.color0(multiColorList[0])
                multiColorList.forEach {
                    animation.addColor(it)
                }
                this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPane)
                this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPaneButtons)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupDirection)
            }
        }

        val defaultColorList = listOf<Long>(
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

        defaultColorSelectButton.apply {
            action {
                val container = ColorContainer()
                defaultColorList.forEach {
                    container += it
                }
                animation.color0(container)
//                animation.colorList(defaultColorList)
                this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPane)
                this@ContinuousAnimationAddView.centerVBox.children.remove(colorGridPaneButtons)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupDirection)
            }
        }

        this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)
        this@ContinuousAnimationAddView.sideVBox.children.addAll(buttonGroupTest)

    }


    override val root = borderpane {

        style {
            backgroundColor += Color.LIGHTSEAGREEN
        }

        addExitAndBlankButtons(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)
        addNavigation(this@ContinuousAnimationAddView::class, this@ContinuousAnimationAddView, this)

        center {
            this += centerVBox.apply {
                style {
                    alignment = Pos.CENTER
                }
            }
        }
        
        right {
            this += sideVBox.apply {
                style {
                    alignment = Pos.CENTER_RIGHT
                }
            }
        }
    }

}
