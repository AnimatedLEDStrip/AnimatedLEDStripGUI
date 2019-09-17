package animatedledstrip.gui


import animatedledstrip.client.AnimationSenderFactory
import com.jfoenix.controls.JFXButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.control.ScrollPane
import javafx.scene.input.KeyCode
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font
import tornadofx.*
import kotlin.reflect.KClass

fun addTopButtons(thisView: View, pane: BorderPane) {
    pane.top {
        /*  Create BorderPane to help set layout */
        borderpane {

            /*  Add "Blank Screen" button
            *   Aligned left
            *   Sends command to screen to turn off
            */
            left {
                /*  Add blank screen button */
                this += JFXButton("Blank Screen").apply {
                    alignment = Pos.CENTER_RIGHT    // Set alignment
                    font = Font.font(15.0)  // Set font size
                    /*  When button is pressed */
                    action {
                        blankScreen()
                    }
                }
            }

            center {
                /*  Add disconnect button */
                this += JFXButton("Disconnect").apply {
                    alignment = Pos.CENTER    // Set alignment
                    font = Font.font(15.0)  // Set font size
                    /*  When button is pressed */
                    action {
                        if (AnimationSenderFactory.defaultSender.connected)
                            AnimationSenderFactory.defaultSender.end()
                        val a = animations.children
                        animations.children.removeAll(a)
                        thisView.apply {
                            replaceWith(DisconnectedView::class, ViewTransition.Fade(1.0.seconds))
                        }
                    }
                }
            }

            /*  Add "Exit" button
            *   Aligned right
            *   Quits GUI
            */
            right {
                /*  Add exit button */
                this += JFXButton("Exit").apply {
                    alignment = Pos.CENTER_RIGHT    // Set alignment
                    font = Font.font(15.0)  // Set font size
                    /*  When button is pressed */
                    action {
                        shutdownGUI()   // Quit GUI
                    }
                }
            }
        }
    }

    /*  Create EventHandler for touch event
    *   Tells screen to wake up, solving issue of screen not waking normally due to GUI
    */
    pane.onTouchPressed = EventHandler {
        wakeScreen()
    }
}

fun addTopButtons(pane: BorderPane) {
    pane.top {
        /*  Create BorderPane to help set layout */
        borderpane {

            /*  Add "Blank Screen" button
            *   Aligned left
            *   Sends command to screen to turn off
            */
            left {
                /*  Add blank screen button */
                this += JFXButton("Blank Screen").apply {
                    alignment = Pos.CENTER_RIGHT    // Set alignment
                    font = Font.font(15.0)  // Set font size
                    /*  When button is pressed */
                    action {
                        blankScreen()
                    }
                }
            }

            /*  Add "Exit" button
            *   Aligned right
            *   Quits GUI
            */
            right {
                /*  Add exit button*/
                this += JFXButton("Exit").apply {
                    alignment = Pos.CENTER_RIGHT    // Set alignment
                    font = Font.font(15.0)  // Set font size
                    /*  When button is pressed */
                    action {
                        shutdownGUI()   // Quit GUI
                    }
                }
            }
        }
    }
    pane.onTouchPressed = EventHandler {
        wakeScreen()
    }
}

val pages = listOf(
    CustomColorView::class,
    PresetsView::class,
    ContinuousAnimationRemoveView::class,
    ContinuousAnimationAddView::class
)


fun addNavigation(thisClass: KClass<out View>, thisView: View, pane: BorderPane) {
    if (pages.contains(thisClass)) {
        val index = pages.indexOf(thisClass)
        pane.apply {

            onSwipeLeft = EventHandler {
                thisView.replaceWith(
                    pages[(index + 1) % pages.size],
                    ViewTransition.Fade(0.3.seconds)
                )
            }

            onSwipeRight = EventHandler {
                thisView.replaceWith(
                    pages[(index - 1 + pages.size) % pages.size],
                    ViewTransition.Fade(0.3.seconds)
                )
            }

            onKeyPressed = EventHandler { t ->
                when (t.code) {
                    KeyCode.RIGHT -> thisView.replaceWith(
                        pages[(index + 1) % pages.size],
                        ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT)
                    )
                    KeyCode.LEFT -> thisView.replaceWith(
                        pages[(index - 1 + pages.size) % pages.size],
                        ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT)
                    )
                    else -> {
                    }
                }
            }
        }
    }
}


fun addNavigation(thisClass: KClass<out View>, thisView: View, pane: ScrollPane) {
    if (pages.contains(thisClass)) {
        val index = pages.indexOf(thisClass)
        pane.apply {

            onSwipeLeft = EventHandler {
                thisView.replaceWith(
                    pages[(index + 1) % pages.size],
                    ViewTransition.Fade(0.3.seconds)
                )
            }

            onSwipeRight = EventHandler {
                thisView.replaceWith(
                    pages[(index - 1 + pages.size) % pages.size],
                    ViewTransition.Fade(0.3.seconds)
                )
            }

            onKeyPressed = EventHandler { t ->
                when (t.code) {
                    KeyCode.RIGHT -> thisView.replaceWith(
                        pages[(index + 1) % pages.size],
                        ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT)
                    )
                    KeyCode.LEFT -> thisView.replaceWith(
                        pages[(index - 1 + pages.size) % pages.size],
                        ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT)
                    )
                    else -> {
                    }
                }
            }
        }
    }
}

fun addNavigation(thisClass: KClass<out View>, thisView: View, pane: JFXButton) {
    if (pages.contains(thisClass)) {
        val index = pages.indexOf(thisClass)
        pane.apply {
            onKeyPressed = EventHandler { t ->
                when (t.code) {
                    KeyCode.RIGHT -> thisView.replaceWith(
                        pages[(index + 1) % pages.size],
                        ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT)
                    )
                    KeyCode.LEFT -> thisView.replaceWith(
                        pages[(index - 1 + pages.size) % pages.size],
                        ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT)
                    )
                    else -> {
                    }
                }
            }
        }
    }
}