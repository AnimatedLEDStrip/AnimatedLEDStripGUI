package animatedledstrip.gui


import animatedledstrip.client.AnimationSenderFactory
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
            runLater(5.0.seconds) {
                if (!AnimationSenderFactory.defaultSender.isDisconnected())
                    replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                else runLater(5.0.seconds) {
                    if (!AnimationSenderFactory.defaultSender.isDisconnected())
                        replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                    else runLater(5.0.seconds) {
                        if (!AnimationSenderFactory.defaultSender.isDisconnected())
                            replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                        else runLater(5.0.seconds) {
                            if (!AnimationSenderFactory.defaultSender.isDisconnected())
                                replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                            else runLater(5.0.seconds) {
                                if (!AnimationSenderFactory.defaultSender.isDisconnected())
                                    replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                                else runLater(5.0.seconds) {
                                    if (!AnimationSenderFactory.defaultSender.isDisconnected())
                                        replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
