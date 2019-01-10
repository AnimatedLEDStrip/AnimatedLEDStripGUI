import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

class DisconnectedView : View() {

    override val root = borderpane {

        val backColor = Color.RED

        style {
            backgroundColor += backColor
        }

        center = label("Not Connected") {
            useMaxSize = true
            font = Font.font(60.0)
            alignment = Pos.CENTER
        }

        addExitAndBlankButtons(this)

        bottom {
            gridpane {
                row {
                    alignment = Pos.CENTER
                    useMaxSize = true
                    this += JFXButton("Connect (Pi 1)").apply {
                        alignment = Pos.CENTER
                        buttonType = JFXButton.ButtonType.RAISED
                        font = Font.font(25.0)
                        useMaxSize = true
                        action {
                            ipAddress = "10.44.157.2"
                            try {
                                MessageSender
                                runLater(1.0.seconds) {
                                    if (!MessageSender.isDisconnected())
                                        replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                                    else
                                        replaceWith(ConnectingView::class, ViewTransition.Fade(1.0.seconds))
                                }
                            } catch (e: Exception) {
                                println("Error initializing Message Sender: $e")
                            }
                        }
                    }
                    this += JFXButton("Connect (Pi 2)").apply {
                        alignment = Pos.CENTER
                        buttonType = JFXButton.ButtonType.RAISED
                        font = Font.font(25.0)
                        useMaxSize = true
                        action {
                            ipAddress = "10.44.103.233"
                            try {
                                MessageSender
                                runLater(1.0.seconds) {
                                    if (!MessageSender.isDisconnected())
                                        replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                                    else
                                        replaceWith(ConnectingView::class, ViewTransition.Fade(1.0.seconds))
                                }
                            } catch (e: Exception) {
                                println("Error initializing Message Sender: $e")
                            }
                        }
                    }
                    this += JFXButton("Connect (Local)").apply {
                        alignment = Pos.CENTER
                        buttonType = JFXButton.ButtonType.RAISED
                        font = Font.font(25.0)
                        useMaxSize = true
                        action {
                            ipAddress = "localhost"
                            try {
                                MessageSender
                                runLater(1.0.seconds) {
                                    if (!MessageSender.isDisconnected())
                                        replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
                                    else
                                        replaceWith(ConnectingView::class, ViewTransition.Fade(1.0.seconds))
                                }
                            } catch (e: Exception) {
                                println("Error initializing Message Sender: $e")
                            }
                        }
                    }
                }
            }
        }
    }
}