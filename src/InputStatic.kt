import com.jfoenix.controls.JFXButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.text.Font
import tornadofx.*

class InputStatic : View() {
    override val root = borderpane {

        onSwipeRight = EventHandler {
            replaceWith(InputDynamic::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
        }

        onSwipeLeft = EventHandler {
            replaceWith(DefaultsView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
        }

        onTouchPressed = EventHandler {
            wakeScreen()
        }

        val backColor = javafx.scene.paint.Color.CYAN

        style {
            backgroundColor += backColor
        }

        center {
            gridpane {
                alignment = Pos.CENTER
                row {
                    this += JFXButton("Send C FFFF").apply {
                        alignment = Pos.CENTER
                        action {
//                            MessageSender.send("C FFFF")
                        }
                    }
                }
                row {
                    this += JFXButton("Send WIP FFFF00 F").apply {
                        alignment = Pos.CENTER
                        action {
//                            MessageSender.send("WIP FFFF00 F")
                        }
                    }
                }
                row {
                    this += JFXButton("Send STC FFFF").apply {
                        alignment = Pos.CENTER
                        action {
//                            MessageSender.send("STC FFFF")
                        }
                    }
                }
                row {
                    this += JFXButton("Send STC 0").apply {
                        alignment = Pos.CENTER
                        action {
//                            MessageSender.send("STC 0")
                        }
                    }
                }
                row {
                    this += JFXButton("Send STC FFFF00").apply {
                        alignment = Pos.CENTER
                        action {
//                            MessageSender.send("STC FFFF00")
                        }
                    }
                }
            }
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
    }

}
