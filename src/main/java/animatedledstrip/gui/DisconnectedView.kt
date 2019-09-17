package animatedledstrip.gui


import animatedledstrip.animationutils.AnimationData
import animatedledstrip.client.AnimationSenderFactory
import animatedledstrip.client.endAnimation
import animatedledstrip.colors.grayscaled
import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import tornadofx.*

val piIPs = listOf(
    "10.44.36.53",
    "10.44.38.85"
)

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

        addTopButtons(this)

        bottom {
            gridpane {
                row {
                    alignment = Pos.CENTER
                    useMaxSize = true

                    piIPs.forEachIndexed { i, ip ->
                        this += JFXButton("Connect (Pi ${i + 1})").apply {
                            alignment = Pos.CENTER
                            buttonType = JFXButton.ButtonType.RAISED
                            font = Font.font(25.0)
                            useMaxSize = true
                            action {
                                ipAddress = ip
                                try {
                                    mainSender =
                                        AnimationSenderFactory.create(ipAddress = ipAddress, connectAttemptLimit = 20)
                                            .setOnNewAnimationCallback { input: AnimationData ->
                                                println(input)
                                                runAsync {
                                                    val button =
                                                        JFXButton("${input.animation}: ID ${input.id}").apply {
                                                            style {
                                                                alignment = Pos.CENTER
                                                                backgroundColor += input.colors[0].toColorContainer()
                                                                    .toColor()
                                                                font = Font.font(12.0)
                                                                if (input.colors[0].toColorContainer().grayscaled().color and 0xFF < 0x80)
                                                                    textFill = Color.WHITE
                                                            }
                                                            action {
                                                                input.endAnimation()
                                                                style {
                                                                    textFill = Color.GRAY
                                                                }
                                                            }
                                                        }
                                                    buttons[input.id] = button
                                                    button
                                                } ui {
                                                    animations += it
                                                }
                                            }.setOnEndAnimationCallback { input: AnimationData ->
                                                runAsync {} ui {
                                                    animations.children.remove(buttons[input.id])
                                                    buttons.remove(input.id)
                                                }
                                            }
                                            .start().setAsDefaultSender()
                                    runLater(0.5.seconds) {
                                        if (AnimationSenderFactory.defaultSender.connected)
                                            replaceWith(ConnectedView::class, ViewTransition.Fade(0.5.seconds))
                                        else
                                            replaceWith(ConnectingView::class, ViewTransition.Fade(0.5.seconds))
                                    }
                                } catch (e: Exception) {
                                    println("Error initializing Message Sender: $e")
                                }
                            }
                        }
                    }
//                    this += JFXButton("Connect (Local)").apply {
//                        alignment = Pos.CENTER
//                        buttonType = JFXButton.ButtonType.RAISED
//                        font = Font.font(25.0)
//                        useMaxSize = true
//                        action {
//                            ipAddress = "localhost"
//                            try {
//                                mainSender =
//                                    AnimationSenderFactory.create(ipAddress = ipAddress, connectAttemptLimit = 20)
//                                        .start().setAsDefaultSender()
//                                runLater(1.0.seconds) {
//                                    if (AnimationSenderFactory.defaultSender.connected)
//                                        replaceWith(ConnectedView::class, ViewTransition.Fade(1.0.seconds))
//                                    else
//                                        replaceWith(ConnectingView::class, ViewTransition.Fade(1.0.seconds))
//                                }
//                            } catch (e: Exception) {
//                                println("Error initializing Message Sender: $e")
//                            }
//                        }
//                    }
                }
            }
        }
    }
}