package animatedledstrip.gui


import animatedledstrip.animationutils.Animation
import animatedledstrip.animationutils.AnimationData
import animatedledstrip.animationutils.animation
import animatedledstrip.animationutils.id
import animatedledstrip.client.AnimationSenderFactory
import animatedledstrip.client.endAnimation
import animatedledstrip.client.send
import animatedledstrip.colors.grayscaled
import com.jfoenix.controls.JFXButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*

class ContinuousAnimationRemoveView : View() {

    override val root = borderpane {

        addExitAndBlankButtons(this@ContinuousAnimationRemoveView::class, this@ContinuousAnimationRemoveView, this)
        addNavigation(this@ContinuousAnimationRemoveView::class, this@ContinuousAnimationRemoveView, this)

        center {
            scrollpane {
                vbox {
                    style {
                        alignment = Pos.CENTER
                    }

                    val buttons = mutableMapOf<String, JFXButton>()

                    runAsync { mainSender?.runningAnimations } ui { animations ->
                        animations?.forEach {
                            val button =
                                JFXButton("${it.value.animation}: ID ${it.key}").apply {
                                    style {
                                        alignment = Pos.CENTER
                                        backgroundColor += it.value.colors[0].toColorContainer().toColor()
                                        font = Font.font(12.0)
                                        if (it.value.colors[0].toColorContainer().grayscaled().color and 0xFF < 0x80)
                                            textFill = Color.WHITE
                                    }
                                    action {
                                        it.value.endAnimation()
                                        style {
                                            textFill = Color.GRAY
                                        }
                                    }
                                }
                            buttons[it.value.id] = button
                            this += button
                        }
                    }

                    AnimationSenderFactory.defaultSender
                        .setOnNewAnimationCallback { input: AnimationData ->
                            runAsync {
                                val button =
                                    JFXButton("${input.animation}: ID ${input.id}").apply {
                                        style {
                                            alignment = Pos.CENTER
                                            backgroundColor += input.colors[0].toColorContainer().toColor()
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
                                this += it
                            }
                        }.setOnEndAnimationCallback { input: AnimationData ->
                            runAsync {} ui {
                                this.children.remove(buttons[input.id])
                                buttons.remove(input.id)
                            }
                        }
                }
                addNavigation(this@ContinuousAnimationRemoveView::class, this@ContinuousAnimationRemoveView, this)
                onTouchPressed = EventHandler {
                    wakeScreen()
                }
            }
        }
    }
}

