import animatedledstrip.client.AnimationSenderFactory
import animatedledstrip.client.send
import animatedledstrip.leds.Animation
import animatedledstrip.leds.AnimationData
import animatedledstrip.leds.ColorContainer
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*

class ConnectedView : View() {

    override val root = borderpane {

        val backColor = Color.GREEN

        style {
            backgroundColor += backColor
        }

        center = label("Connected") {
            useMaxSize = true
            font = Font.font(60.0)
            alignment = Pos.CENTER
        }

        addExitAndBlankButtons(this)

        bottom {
            this += JFXButton("").apply {
                alignment = Pos.CENTER
                buttonType = JFXButton.ButtonType.RAISED
                font = Font.font(35.0)
                useMaxSize = true
                }
            }
    }

    override fun onDock() {
        super.onDock()
        this.run {
            runLater(1.0.seconds) {
                AnimationSenderFactory.defaultSender?.setOnReceiveCallback { input ->
                    val animationData = input["Animation"] as Map<*, *>
                    animations += JFXButton(
                            "${animationData["Animation"]}: ID ${input["ID"].toString()}"
                    ).apply {
                        style {
                            alignment = Pos.CENTER
                            val color1 = ColorContainer(animationData["Color1"] as Long)
                            backgroundColor += color1.toColor()
                            font = Font.font(12.0)
                            if (color1.grayscale() < 0x80) textFill = Color.WHITE
                        }
                        val id = input["ID"].toString()
                        action {
                            GlobalScope.launch {
                                AnimationData().animation(Animation.ENDANIMATION).id(id).send()
                            }
                            animations.children.remove(this@apply)
                        }
                    }
                }
                replaceWith(CustomColorView::class, ViewTransition.Fade(1.0.seconds))
            }
        }
    }

}
