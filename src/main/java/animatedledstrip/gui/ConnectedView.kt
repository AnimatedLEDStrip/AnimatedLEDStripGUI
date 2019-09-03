package animatedledstrip.gui


import animatedledstrip.animationutils.Animation
import animatedledstrip.animationutils.AnimationData
import animatedledstrip.animationutils.animation
import animatedledstrip.animationutils.id
import animatedledstrip.client.AnimationSenderFactory
import animatedledstrip.client.send
import animatedledstrip.colors.ColorContainer
import animatedledstrip.colors.grayscaled
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
                replaceWith(ContinuousAnimationAddView::class, ViewTransition.Fade(1.0.seconds))
            }
        }
    }

}
