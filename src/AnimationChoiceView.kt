import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*

class AnimationChoiceView : View() {
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

        center {
            gridpane {
                row {
                    alignment = Pos.CENTER  // Set alignment of grid

                    /*  First row of buttons
                    *   Each button says "Send C" for "Send single color"
                    *   Each button is colored to match the color it will send
                    */
                    row {
                        /*  Alternate animation button */
                        this += JFXButton("ALT").apply {
                            alignment = Pos.CENTER  // Align button
                            useMaxSize = true   // Use full size of cell
                            setMinSize(50.0, 50.0)  // Set minimum size
                            style {
                                backgroundColor += Color.BLACK  // Set background color
                                textFill = Color.WHITE  // Set text color

                            }
                            /*  When button is pressed */
                            action {
                            }
                        }

                        label("  ")   // Spacer

                        /*  Multi-pixel run animation button */
                        this += JFXButton("MPR").apply {
                            alignment = Pos.CENTER
                            useMaxSize = true
                            setMinSize(50.0, 50.0)
                            style {
                                backgroundColor += Color.BLUE
                                textFill = Color.WHITE
                            }
                            action {
                            }
                        }

                        label("  ") // Spacer

                        /*  Add aqua color button */
                        this += JFXButton("PXR").apply {
                            alignment = Pos.CENTER
                            useMaxSize = true
                            setMinSize(50.0, 50.0)
                            style {
                                backgroundColor += Color.AQUA
                            }
                            action {
                            }
                        }

                        label("  ") // Spacer

                        /*  Add green color button */
                        this += JFXButton("PXRT").apply {
                            alignment = Pos.CENTER
                            useMaxSize = true
                            isDisable = true
                            setMinSize(50.0, 50.0)
                            style {
                                backgroundColor += Color.GREEN
                            }
                            action {
                            }
                        }

                        label("  ") // Spacer

                        /*  Add yellow color button */
                        this += JFXButton("SPK").apply {
                            alignment = Pos.CENTER
                            useMaxSize = true
                            setMinSize(50.0, 50.0)
                            style {
                                backgroundColor += Color.YELLOW
                            }
                            action {
                            }
                        }
                    }
                }
            }
        }
    }
}