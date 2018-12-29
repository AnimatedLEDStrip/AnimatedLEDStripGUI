import com.jfoenix.controls.JFXButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font
import tornadofx.*

fun addExitAndBlankButtons(pane: BorderPane) {
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

    /*  Create EventHandler for touch event
    *   Tells screen to wake up, solving issue of screen not waking normally due to GUI
    */
    pane.onTouchPressed = EventHandler {
        wakeScreen()
    }
}