import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXColorPicker
import javafx.event.EventHandler
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import tornadofx.*

class ContinuousAnimationAddView : View() {

    private var toggleReverse: JFXButton by singleAssign()
    private var altButton: JFXButton by singleAssign()
    private var mprButton: JFXButton by singleAssign()
    private var pxmButton: JFXButton by singleAssign()
    private var pxrButton: JFXButton by singleAssign()
    private var pxrtButton: JFXButton by singleAssign()
    private var spkButton: JFXButton by singleAssign()
    private var stoButton: JFXButton by singleAssign()

    private var colorPicker: JFXColorPicker by singleAssign()
    private var colorPickerButton: JFXButton by singleAssign()

    private var centerVBox: VBox by singleAssign()


    private val animationBuilder = mutableMapOf<String, Any>()

    private val buttonGroupAnimations: List<JFXButton>
    private val buttonGroupColors: List<Any>

    init {
        centerVBox = VBox()

        altButton = JFXButton("Alternate")
        mprButton = JFXButton("Multi-Pixel Run")
        pxmButton = JFXButton("Pixel Marathon")
        pxrButton = JFXButton("Pixel Run")
        pxrtButton = JFXButton("Pixel Run with Trail")
        spkButton = JFXButton("Sparkle")
        stoButton = JFXButton("Stack Overflow")

        colorPicker = JFXColorPicker()
        colorPickerButton = JFXButton("Set Color 1").apply {
            action {
                when (text) {
                    "Set Color 1" ->
                        animationBuilder["Color1"] = colorPicker.value.getHex()
                    "Set Color 2" ->
                        animationBuilder["Color2"] = colorPicker.value.getHex()
                    "Set Color 3" ->
                        animationBuilder["Color3"] = colorPicker.value.getHex()
                    "Set Color 4" ->
                        animationBuilder["Color4"] = colorPicker.value.getHex()
                    "Set Color 5" ->
                        animationBuilder["Color5"] = colorPicker.value.getHex()
                }

                println(animationBuilder["Color1"])
            }
        }

        buttonGroupAnimations = listOf(altButton, mprButton, pxmButton, pxrButton, pxrtButton, spkButton, stoButton)

        buttonGroupColors = listOf(colorPicker, colorPickerButton)

        this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupAnimations)

        altButton.apply {
            action {
                animationBuilder["Animation"] = Animations.ALTERNATE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors)
            }
        }
        mprButton.apply {
            action {
                animationBuilder["Animation"] = Animations.MULTIPIXELRUN
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors)
            }
        }
        pxmButton.apply {
            action {
                animationBuilder["Animation"] = Animations.PIXELMARATHON
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors)
            }
        }
        pxrButton.apply {
            action {
                animationBuilder["Animation"] = Animations.PIXELRUN
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors)
            }
        }
        pxrtButton.apply {
            action {
                animationBuilder["Animation"] = Animations.PIXELRUNWITHTRAIL
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors)
            }
        }
        spkButton.apply {
            action {
                animationBuilder["Animation"] = Animations.SPARKLE
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors)
            }
        }
        stoButton.apply {
            action {
                animationBuilder["Animation"] = Animations.STACKOVERFLOW
                this@ContinuousAnimationAddView.centerVBox.children.removeAll(buttonGroupAnimations)
                this@ContinuousAnimationAddView.centerVBox.children.addAll(buttonGroupColors)
            }
        }

    }



    override val root = borderpane {
//        addExitAndBlankButtons(this)

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
}
