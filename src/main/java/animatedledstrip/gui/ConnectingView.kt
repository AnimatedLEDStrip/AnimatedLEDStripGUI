package animatedledstrip.gui


import animatedledstrip.client.AnimationSenderFactory
import animatedledstrip.utils.delayBlocking
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class ConnectingView : View() {

    override val root = borderpane {

        val backColor = Color.YELLOW

        style {
            backgroundColor += backColor
        }

        center = label("Connecting") {
            useMaxSize = true
            font = Font.font(60.0)
            alignment = Pos.CENTER
        }

        addTopButtons(this@ConnectingView, this)

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
            while (AnimationSenderFactory.defaultSender.started) {
                if (AnimationSenderFactory.defaultSender.connected) {
                    replaceWith(ConnectedView::class, ViewTransition.Fade(0.1.seconds))
                }
                delayBlocking(50)
            }
            delayBlocking(500)
            if (!AnimationSenderFactory.defaultSender.started)
                replaceWith(DisconnectedView::class, ViewTransition.Fade(0.1.seconds))
        }
    }
}
