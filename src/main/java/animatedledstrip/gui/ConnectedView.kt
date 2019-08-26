package animatedledstrip.gui


import animatedledstrip.animationutils.Animation
import animatedledstrip.animationutils.AnimationData
import animatedledstrip.client.AnimationSenderFactory
import animatedledstrip.client.send
import animatedledstrip.colors.ColorContainer
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

    fun ColorContainer.toColor(): Color =
        Color.color((color shr 16 and 0xFF) / 255.0, (color shr 8 and 0xFF) / 255.0, (color and 0xFF) / 255.0)

    override fun onDock() {
        super.onDock()
        this.run {
            runLater(1.0.seconds) {
                AnimationSenderFactory.defaultSender.setOnReceiveCallback { input: AnimationData ->
                    println("Received animation: $input")
                    animations += JFXButton(
                        "${input.animation}: ID ${input.id}"
                    ).apply {
                        style {
                            alignment = Pos.CENTER
                            backgroundColor += input.colors[0].toColorContainer().toColor()
                            font = Font.font(12.0)
                            if (input.colors[0].toColorContainer().grayscaled().color < 0x80) textFill = Color.WHITE
                        }
                        val id = input.id
                        action {
                            GlobalScope.launch {
                                AnimationData().animation(Animation.ENDANIMATION).id(id).send()
                            }
                            animations.children.remove(this@apply)
                        }
                    }
                }
                replaceWith(ContinuousAnimationAddView::class, ViewTransition.Fade(1.0.seconds))
            }
        }
    }

}
