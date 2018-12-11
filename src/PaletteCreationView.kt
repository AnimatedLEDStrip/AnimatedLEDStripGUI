import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.text.Font
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*

class PaletteCreationView : View() {
    override val root = borderpane {
        /*  Section for top buttons */
        top {
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
                            val commandLine = CommandLine.parse("xset -d :0 dpms force off")
                            val executor = DefaultExecutor()
                            executor.setExitValue(0)
                            executor.execute(commandLine)   //  Send command
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




    }
}