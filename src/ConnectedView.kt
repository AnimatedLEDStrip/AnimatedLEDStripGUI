import com.jfoenix.controls.JFXButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*

class ConnectedView : View() {

    override val root = borderpane {

        onTouchPressed = EventHandler {
            wakeScreen()
        }

        val backColor = Color.GREEN

        style {
            backgroundColor += backColor
        }

        center = label("Connected") {
            useMaxSize = true
            font = Font.font(60.0)
            alignment = Pos.CENTER
        }

        top {
            borderpane {
                right {

                    this += JFXButton("Exit").apply {
                        alignment = Pos.CENTER_RIGHT
                        font = Font.font(15.0)
                        action {
                            shutdownGUI()
                        }
                    }
                }
            }
        }

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
                replaceWith(DefaultsView::class, ViewTransition.Fade(1.0.seconds))
            }
        }
    }

}
