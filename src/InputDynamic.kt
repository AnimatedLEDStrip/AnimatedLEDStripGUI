import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXToggleButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.text.Font
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*

class InputDynamic : View() {


    var toggleReverse: JFXToggleButton by singleAssign()

    private fun sendPixelMarathon(color: String) = MessageSender.send(mapOf("Animation" to Animations.PIXELMARATHON, "Color1" to parseHex(color)))

    override val root = borderpane {

        onSwipeLeft = EventHandler {
            replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
        }

        onSwipeRight = EventHandler {
            replaceWith(DefaultsView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
        }

        onTouchPressed = EventHandler {
            wakeScreen()
        }

        onKeyPressed = EventHandler { t ->
            when (t.code) {
                KeyCode.RIGHT -> replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                KeyCode.LEFT -> replaceWith(DefaultsView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }

        val backColor = javafx.scene.paint.Color.AQUAMARINE

        style {
            backgroundColor += backColor
        }

        addExitAndBlankButtons(this)

        center {
            gridpane {
                alignment = Pos.CENTER

                row {
                    this += JFXButton("Pixel Marathon").apply {
                        alignment = Pos.CENTER
                        action {
                            sendPixelMarathon(Color.RED.getHex())
                        }
                    }
                }

//                row {
//                    toggleReverse = JFXToggleButton()
//                    this += toggleReverse.apply {
//
//                        text = "Test"
//
//
//                    }
//                }

//                row {
//                    this += JFXButton("Change Test").apply {
//                        action {
//                            when (toggleReverse.isSelected) {
//                                true -> toggleReverse.text = "Works"
//                                false -> toggleReverse.text = "Test"
//                            }
//                        }
//                    }
//                }

//                row {
//                    this += JFXButton("Start Animation Group").apply {
//
//                        var continueAnimationGroup = false
//
//                        action {
//                            continueAnimationGroup = when (continueAnimationGroup) {
//                                true -> false
//                                false -> true
//                            }
//                            if (continueAnimationGroup) {
//                                GlobalScope.launch {
//                                    var animationCounter = 0
//                                    while (continueAnimationGroup) {
//                                        if (MessageSender.isAnimationComplete()) {
////                                            MessageSender.send(animationGroupList[(animationCounter % animationGroupList.size)])
//                                            animationCounter++
//                                            MessageSender.resetAnimationComplete()
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }

//                row {
//                    this += JFXButton("Start MTC Group").apply {
//
//                        var continueAnimationGroup = false
//
//                        action {
//                            continueAnimationGroup = when (continueAnimationGroup) {
//                                true -> false
//                                false -> true
//                            }
//                            if (continueAnimationGroup) {
//                                GlobalScope.launch {
//                                    var animationCounter = 0
//                                    while (continueAnimationGroup) {
//                                        if (MessageSender.isAnimationComplete()) {
////                                            MessageSender.send(getMTCString(colors[animationCounter % colors.size].getHex()))
//                                            animationCounter++
//                                            MessageSender.resetAnimationComplete()
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
            }
        }

//        top {
//            borderpane {
//                right {
//
//                    this += JFXButton("Exit").apply {
//                        alignment = Pos.CENTER_RIGHT
//                        font = Font.font(15.0)
////                useMaxSize = true
//                        action {
//                            shutdownGUI()
//                        }
//                    }
//                }
//            }
//        }
    }

}
