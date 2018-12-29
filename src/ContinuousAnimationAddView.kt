import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXColorPicker
import javafx.animation.Animation
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import tornadofx.*

class ContinuousAnimationAddView : View() {

    private var centerVBox: VBox by singleAssign()

    private var toggleReverse: JFXButton by singleAssign()
    private var altButton: JFXButton by singleAssign()
    private var mprButton: JFXButton by singleAssign()
    private var pxmButton: JFXButton by singleAssign()
    private var pxrButton: JFXButton by singleAssign()
    private var pxrtButton: JFXButton by singleAssign()
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

    private val buttonGroupAnimations: List<JFXButton>
    private val buttonGroupColors1: List<Any>
    private val buttonGroupColors2: List<Any>
    private val buttonGroupColors3: List<Any>
    private val buttonGroupColors4: List<Any>
    private val buttonGroupColors5: List<Any>
    private val buttonGroupDirection: List<JFXButton>


    private var animationBuilder = mutableMapOf<String, Any>("Continuous" to true)

    init {
        centerVBox = VBox()

        altButton = JFXButton("Alternate")
        mprButton = JFXButton("Multi-Pixel Run")
        pxmButton = JFXButton("Pixel Marathon")
        pxrButton = JFXButton("Pixel Run")
        pxrtButton = JFXButton("Pixel Run with Trail")
        spkButton = JFXButton("Sparkle")
        stoButton = JFXButton("Stack Overflow")

        colorPicker1 = JFXColorPicker().apply {
            value = Color.BLACK
        }
        colorPicker2 = JFXColorPicker().apply {
            value = Color.BLACK
        }
        colorPicker3 = JFXColorPicker().apply {
            value = Color.BLACK
        }
        colorPicker4 = JFXColorPicker().apply {
            value = Color.BLACK
        }
        colorPicker5 = JFXColorPicker().apply {
            value = Color.BLACK
        }
        colorPickerButton = JFXButton("Set Colors")

        forwardButton = JFXButton("Forward")
        backwardButton = JFXButton("Backward")


        buttonGroupAnimations = listOf(altButton, mprButton, pxmButton, pxrButton, pxrtButton, spkButton, stoButton)

        buttonGroupColors1 = listOf(colorPicker1, colorPickerButton)
        buttonGroupColors2 = listOf(colorPicker1, colorPicker2, colorPickerButton)
        buttonGroupColors3 = listOf(colorPicker1, colorPicker2, colorPicker3, colorPickerButton)
        buttonGroupColors4 = listOf(colorPicker1, colorPicker2, colorPicker3, colorPicker4, colorPickerButton)
        buttonGroupColors5 = listOf(colorPicker1, colorPicker2, colorPicker3, colorPicker4, colorPicker5, colorPickerButton)

        buttonGroupDirection = listOf(forwardButton, backwardButton)


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

                    this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)
                }
            }
        }

        this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)

    }


    override val root = borderpane {
        addExitAndBlankButtons(this)

        onKeyPressed = EventHandler { t ->
            when (t.code) {
                KeyCode.RIGHT -> replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                KeyCode.LEFT -> replaceWith(ContinuousAnimationRemoveView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }

        center {
            this += centerVBox
        }
    }


    private fun addAnimation() {

    }

}
