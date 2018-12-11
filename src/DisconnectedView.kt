import com.jfoenix.controls.JFXButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*

class DisconnectedView : View() {

    override val root = borderpane {

        onTouchPressed = EventHandler {
            val commandLine = CommandLine.parse("xset -d :0 dpms force on")
            val executor = DefaultExecutor()
            executor.setExitValue(0)
            executor.execute(commandLine)
        }

        val backColor = Color.RED

        style {
            backgroundColor += backColor
        }

        center = label("Not Connected") {
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
//                useMaxSize = true
                        action {
                            shutdownGUI()
                        }
                    }
                }
            }
        }

        bottom {

            this += JFXButton("Connect").apply {
                alignment = Pos.CENTER
                buttonType = JFXButton.ButtonType.RAISED
                font = Font.font(35.0)
                useMaxSize = true
                action {
                    MessageSender
                    runLater(1.0.seconds) {
                        replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                    }
                }
            }
        }
    }
}
