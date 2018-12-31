import animatedledstrip.leds.Animations
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXColorPicker
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class ContinuousAnimationAddView : View() {

    private var centerVBox: VBox by singleAssign()

    private var altButton: JFXButton by singleAssign()
    private var mprButton: JFXButton by singleAssign()
    private var pxmButton: JFXButton by singleAssign()
    private var pxrButton: JFXButton by singleAssign()
    private var pxrtButton: JFXButton by singleAssign()
    private var schButton: JFXButton by singleAssign()
    private var spkButton: JFXButton by singleAssign()
    private var stoButton: JFXButton by singleAssign()

    private var colorPicker1: JFXColorPicker by singleAssign()
    private var colorPicker2: JFXColorPicker by singleAssign()
    private var colorPicker3: JFXColorPicker by singleAssign()
    private var colorPicker4: JFXColorPicker by singleAssign()
    private var colorPicker5: JFXColorPicker by singleAssign()
    private var colorPickerButton: JFXButton by singleAssign()

    private var forwardButton: JFXButton by singleAssign()
    private var backwardButton: JFXButton by singleAssign()

    private var colorSpacer1: Label by singleAssign()
    private var colorSpacer2: Label by singleAssign()
    private var colorSpacer3: Label by singleAssign()
    private var colorSpacer4: Label by singleAssign()

    private var multiColorPicker: JFXColorPicker by singleAssign()
    private var multiColorPickerButton: JFXButton by singleAssign()
    private var multiColorPickerCompleteButton: JFXButton by singleAssign()

    private val buttonGroupAnimations: List<JFXButton>
    private val buttonGroupColors1: List<Any>
    private val buttonGroupColors2: List<Any>
    private val buttonGroupColors3: List<Any>
    private val buttonGroupColors4: List<Any>
    private val buttonGroupColors5: List<Any>
    private val buttonGroupDirection: List<JFXButton>
    private val buttonGroupMultiColor: List<Any>

    private var multiColorList: MutableList<Long>


    private var animationBuilder = mutableMapOf<String, Any>("Continuous" to true)

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

        colorPicker1 = JFXColorPicker().apply {
            value = Color.BLACK
            setMinSize(50.0, 50.0)
        }
        colorPicker2 = JFXColorPicker().apply {
            value = Color.BLACK
            setMinSize(50.0, 50.0)
        }
        colorPicker3 = JFXColorPicker().apply {
            value = Color.BLACK
            setMinSize(50.0, 50.0)
        }
        colorPicker4 = JFXColorPicker().apply {
            value = Color.BLACK
            setMinSize(50.0, 50.0)
        }
        colorPicker5 = JFXColorPicker().apply {
            value = Color.BLACK
            setMinSize(50.0, 50.0)
        }
        colorPickerButton = JFXButton("Set Colors").apply {
            setMinSize(50.0, 50.0)
        }

        forwardButton = JFXButton("Forward").apply {
            setMinSize(50.0, 50.0)
        }
        backwardButton = JFXButton("Backward").apply {
            setMinSize(50.0, 50.0)
        }

        multiColorPicker = JFXColorPicker().apply {
            value = Color.BLACK
            setMinSize(50.0, 50.0)
        }
        multiColorPickerButton = JFXButton("Add Color").apply {
            setMinSize(50.0, 50.0)
        }
        multiColorPickerCompleteButton = JFXButton("Send Colors").apply {
            setMinSize(50.0, 50.0)
        }


        colorSpacer1 = Label().apply {
            style {
                font = Font(5.0)
            }
        }
        colorSpacer2 = Label().apply {
            style {
                font = Font(5.0)
            }
        }
        colorSpacer3 = Label().apply {
            style {
                font = Font(5.0)
            }
        }
        colorSpacer4 = Label().apply {
            style {
                font = Font(5.0)
            }
        }

        buttonGroupAnimations = listOf(altButton, mprButton, pxmButton, pxrButton, pxrtButton, schButton, spkButton, stoButton)

        buttonGroupColors1 = listOf(colorPicker1, colorPickerButton)
        buttonGroupColors2 = listOf(colorPicker1, colorSpacer1, colorPicker2, colorPickerButton)
        buttonGroupColors3 = listOf(colorPicker1, colorSpacer1, colorPicker2, colorSpacer2, colorPicker3, colorPickerButton)
        buttonGroupColors4 = listOf(colorPicker1, colorSpacer1, colorPicker2, colorSpacer2, colorPicker3, colorSpacer3, colorPicker4, colorPickerButton)
        buttonGroupColors5 = listOf(colorPicker1, colorSpacer1, colorPicker2, colorSpacer2, colorPicker3, colorSpacer3, colorPicker4, colorSpacer4, colorPicker5, colorPickerButton)

        buttonGroupDirection = listOf(forwardButton, backwardButton)

        buttonGroupMultiColor = listOf(multiColorPicker, colorSpacer1, multiColorPickerButton, colorSpacer2, multiColorPickerCompleteButton)

        multiColorList = mutableListOf()

        altButton.apply {
            action {
                animationBuilder["Animation"] = Animations.ALTERNATE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors2)
            }
        }
        mprButton.apply {
            action {
                animationBuilder["Animation"] = Animations.MULTIPIXELRUN
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors2)
            }
        }
        pxmButton.apply {
            action {
                animationBuilder["Animation"] = Animations.PIXELMARATHON
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors5)
            }
        }
        pxrButton.apply {
            action {
                animationBuilder["Animation"] = Animations.PIXELRUN
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors2)
            }
        }
        pxrtButton.apply {
            action {
                animationBuilder["Animation"] = Animations.PIXELRUNWITHTRAIL
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors2)
            }
        }
        schButton.apply {
            action {
                animationBuilder["Animation"] = Animations.SMOOTHCHASE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupMultiColor)
            }
        }
        spkButton.apply {
            action {
                animationBuilder["Animation"] = Animations.SPARKLE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors1)
            }
        }
        stoButton.apply {
            action {
                animationBuilder["Animation"] = Animations.STACKOVERFLOW
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors2)
            }
        }


        colorPickerButton.apply {
            action {
                when (animationBuilder["Animation"] as Animations) {
                    Animations.SPARKLE -> {
                        animationBuilder["Color1"] = colorPicker1.value.getHex().toLong(16)
                    }
                    Animations.ALTERNATE,
                    Animations.MULTIPIXELRUN,
                    Animations.PIXELRUN,
                    Animations.PIXELRUNWITHTRAIL,
                    Animations.STACKOVERFLOW -> {
                        animationBuilder["Color1"] = colorPicker1.value.getHex().toLong(16)
                        animationBuilder["Color2"] = colorPicker2.value.getHex().toLong(16)
                    }
                    Animations.PIXELMARATHON -> {
                        animationBuilder["Color1"] = colorPicker1.value.getHex().toLong(16)
                        animationBuilder["Color2"] = colorPicker2.value.getHex().toLong(16)
                        animationBuilder["Color3"] = colorPicker3.value.getHex().toLong(16)
                        animationBuilder["Color4"] = colorPicker4.value.getHex().toLong(16)
                        animationBuilder["Color5"] = colorPicker5.value.getHex().toLong(16)
                    }
                    Animations.SMOOTHCHASE -> TODO()
                }

                when (animationBuilder["Animation"] as Animations) {
                    Animations.ALTERNATE,
                    Animations.SPARKLE,
                    Animations.STACKOVERFLOW,
                    Animations.PIXELMARATHON -> {
                        this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupColors5)
                        MessageSender.send(animationBuilder)

                        animationBuilder = mutableMapOf<String, Any>("Continuous" to true)

                        this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)
                    }
                    Animations.MULTIPIXELRUN,
                    Animations.PIXELRUN,
                    Animations.PIXELRUNWITHTRAIL -> {
                        this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupColors5)
                        this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupDirection)
                    }
                }

            }
        }


        buttonGroupDirection.forEach {
            it.apply {
                action {
                    animationBuilder["Direction"] = when (it.text) {
                        "Forward" -> 'F'
                        "Backward" -> 'B'
                        else -> 'F'
                    }
                    this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupDirection)
                    MessageSender.send(animationBuilder)

                    animationBuilder = mutableMapOf<String, Any>("Continuous" to true)
                    multiColorList = mutableListOf()

                    this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)
                }
            }
        }

        multiColorPickerButton.apply {
            action {
                multiColorList.add(multiColorPicker.value.getHex().toLong(16))
            }
        }

        multiColorPickerCompleteButton.apply {
            action {
                when (animationBuilder["Animation"] as Animations) {
                    Animations.SPARKLE,
                    Animations.ALTERNATE,
                    Animations.MULTIPIXELRUN,
                    Animations.PIXELRUN,
                    Animations.PIXELRUNWITHTRAIL,
                    Animations.STACKOVERFLOW,
                    Animations.PIXELMARATHON -> TODO()
                    Animations.SMOOTHCHASE -> {
                        animationBuilder["Color1"] = multiColorList[0]
                        animationBuilder["ColorList"] = multiColorList
                        this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupMultiColor)
                        this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupDirection)
                    }
                }
            }
        }

        this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)

    }


    override val root = borderpane {

        style {
            backgroundColor += Color.LIGHTSEAGREEN
        }

        addExitAndBlankButtons(this)

        onKeyPressed = EventHandler { t ->
            when (t.code) {
                KeyCode.RIGHT -> replaceWith(ContinuousAnimationRemoveView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                KeyCode.LEFT -> replaceWith(InputDynamic::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }

        center {
            this += centerVBox.apply {
                style {
                    alignment = Pos.CENTER
                }
            }
        }
    }

}
