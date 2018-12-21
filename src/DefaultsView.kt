import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSlider
import com.jfoenix.controls.JFXToggleButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.Font
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*

/*  Main view for controlling colors
*   Contains buttons for all primary and secondary colors and black
*   Each color has four possible animations
*   Has toggle to reverse direction and slider for brightness
*/
class DefaultsView : View() {

    /*  Create toggle and slider accessible by other elements */
    private var toggleReverse: JFXToggleButton by singleAssign()
    private var brightnessSlider: JFXSlider by singleAssign()


    /*  Helper functions for sending commands */
    private fun sendC(color: String) =
            MessageSender.send(mapOf("Animation" to Animations.COLOR1, "Color1" to parseHex(color)))

    private fun sendWIP(color: String) {
        val direction = when (toggleReverse.isSelected) {
            true -> 'B'
            false -> 'F'
        }
        MessageSender.send(mapOf("Animation" to Animations.WIPE, "Color1" to parseHex(color), "Direction" to direction))
    }

    private fun sendSTK(color: String) {
        val direction = when (toggleReverse.isSelected) {
            true -> 'B'
            false -> 'F'
        }
        MessageSender.send(mapOf("Animation" to Animations.STACKOVERFLOW, "Color1" to parseHex(color), "Color2" to parseHex("FFFF00"), "Direction" to direction))
    }

    private fun sendSTC(color: String) =
            MessageSender.send(mapOf("Animation" to Animations.SPARKLETOCOLOR, "Color1" to parseHex(color)))

    private fun sendMTC(color: String) {
        val direction = when (toggleReverse.isSelected) {
            true -> 'B'
            false -> 'F'
        }
        MessageSender.send(mapOf("Animation" to Animations.MULTIPIXELRUNTOCOLOR, "Color1" to parseHex(color), "Direction" to direction))
    }


    /*  GUI */
    override val root = borderpane {

        /*  Create EventHandlers for swipe events */
        onSwipeLeft = EventHandler {
            replaceWith(InputDynamic::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
        }

        onSwipeRight = EventHandler {
            replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
        }

//        onSwipeUp = EventHandler {
//            replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.UP))
//        }


        /*  Create EventHandler for touch event
        *   Tells screen to wake up, solving issue of screen not waking normally due to GUI
        */
        onTouchPressed = EventHandler {
            wakeScreen()
        }


        /*  Set style of whole GUI
        *   Sets background color
        */
        style {
            backgroundColor += Color.CORNFLOWERBLUE
        }

        addExitAndBlankButtons(this)
//        /*  Section for top buttons */
//        top {
//            /*  Create BorderPane to help set layout */
//            borderpane {
//
//                /*  Add "Blank Screen" button
//                *   Aligned left
//                *   Sends command to screen to turn off
//                */
//                left {
//                    /*  Add blank screen button */
//                    this += JFXButton("Blank Screen").apply {
//                        alignment = Pos.CENTER_RIGHT    // Set alignment
//                        font = Font.font(15.0)  // Set font size
//                        /*  When button is pressed */
//                        action {
//                            blankScreen()
//                        }
//                    }
//                }
//
//                /*  Add "Exit" button
//                *   Aligned right
//                *   Quits GUI
//                */
//                right {
//                    /*  Add exit button*/
//                    this += JFXButton("Exit").apply {
//                        alignment = Pos.CENTER_RIGHT    // Set alignment
//                        font = Font.font(15.0)  // Set font size
//                        /*  When button is pressed */
//                        action {
//                            shutdownGUI()   // Quit GUI
//                        }
//                    }
//                }
//            }
//        }

        /*  Main section of GUI */
        center {
            /*  Create GridPane to help set layout */
            gridpane {
                alignment = Pos.TOP_CENTER  // Set alignment of grid

                /*  First row of GUI
                *   Contains toggle for direction
                */
                row {
                    /*  Create GridPane to facilitate multiple rows */
                    gridpane {
                        /*  Create and add direction toggle to GUI */
                        row {
                            alignment = Pos.CENTER  // Set alignment of row
                            toggleReverse = JFXToggleButton()   // Create toggle
                            /*  Add toggle to GUI */
                            this += toggleReverse.apply {
                                alignment = Pos.CENTER  // Set alignment of toggle
                                text = "Reverse Direction"  // Set label text
                            }

                            label("Brightness") {
                                alignment = Pos.CENTER  // Set alignment
                                useMaxWidth = true  // Use full size of cell
                            }

                            brightnessSlider = JFXSlider(0.0, 255.0, 255.0)
                            this += brightnessSlider.apply {
                                alignment = Pos.CENTER  // Set alignment
                                setMinSize(150.0, 50.0) // Set minimum size
                            }

                        }

                        row { label {} }    // Spacer row
                    }
                }

                /*  Second row of GUI
                *   Contains rows of buttons for sending commands
                */
                row {
                    /*  Create GridPane in row */
                    gridpane {
                        alignment = Pos.CENTER  // Set alignment of grid

                        /*  First row of buttons
                        *   Each button says "Send C" for "Send single color"
                        *   Each button is colored to match the color it will send
                        */
                        row {
                            /*  Add black color button
                            *   All buttons are similar in setup so only this one is commented
                            */
                            this += JFXButton("Send C").apply {
                                alignment = Pos.CENTER  // Align button
                                useMaxSize = true   // Use full size of cell
                                setMinSize(50.0, 50.0)  // Set minimum size
                                style {
                                    backgroundColor += Color.BLACK  // Set background color
                                    textFill = Color.WHITE  // Set text color

                                }
                                /*  When button is pressed */
                                action {
                                    sendC("0")  // Call helper function to send command
                                }
                            }

                            label("    ")   // Spacer between buttons - Slightly larger than others

                            /*  Add blue color button */
                            this += JFXButton("Send C").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLUE
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendC("FF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add aqua color button */
                            this += JFXButton("Send C").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.AQUA
                                }
                                action {
                                    sendC("FFFF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add green color button */
                            this += JFXButton("Send C").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.GREEN
                                }
                                action {
                                    sendC("FF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add yellow color button */
                            this += JFXButton("Send C").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.YELLOW
                                }
                                action {
                                    sendC("FFFF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add red color button */
                            this += JFXButton("Send C").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.RED
                                }
                                action {
                                    sendC("FF0000")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add magenta color button */
                            this += JFXButton("Send C").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.MAGENTA
                                }
                                action {
                                    sendC("FF00FF")
                                }
                            }
                        }

                        row { label {} }    // Spacer between rows of buttons

                        /*  Second row of buttons
                        *   Each button says "Send WIP" for "Send Wipe"
                        *   Each button is colored to match the color it will send
                        */
                        row {
                            /*  Add black wipe button */
                            this += JFXButton("Send WIP").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLACK
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendWIP("0")
                                }
                            }

                            label("    ")   // Larger spacer

                            /*  Add blue wipe button */
                            this += JFXButton("Send WIP").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLUE
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendWIP("FF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add aqua wipe button */
                            this += JFXButton("Send WIP").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.AQUA
                                }
                                action {
                                    sendWIP("FFFF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add green wipe button */
                            this += JFXButton("Send WIP").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.GREEN

                                }
                                action {
                                    sendWIP("FF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add yellow wipe button */
                            this += JFXButton("Send WIP").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.YELLOW

                                }
                                action {
                                    sendWIP("FFFF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add red wipe button */
                            this += JFXButton("Send WIP").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.RED

                                }
                                action {
                                    sendWIP("FF0000")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add magenta wipe button */
                            this += JFXButton("Send WIP").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.MAGENTA

                                }
                                action {
                                    sendWIP("FF00FF")
                                }
                            }

                        }

                        row { label {} }    // Row spacer

                        /*  Third row of buttons
                        *   Each button says "Send STC" for "Send Sparkle to Color"
                        *   Each button is colored to match the color it will send
                        */
                        row {
                            /*  Add black STC button*/
                            this += JFXButton("Send STC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLACK
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendSTC("0")
                                }
                            }

                            label("    ")   // Larger spacer

                            /*  Add blue STC button */
                            this += JFXButton("Send STC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLUE
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendSTC("FF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add aqua STC button */
                            this += JFXButton("Send STC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.AQUA
                                }
                                action {
                                    sendSTC("FFFF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add green STC button */
                            this += JFXButton("Send STC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.GREEN
                                }
                                action {
                                    sendSTC("FF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add yellow STC button */
                            this += JFXButton("Send STC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.YELLOW
                                }
                                action {
                                    sendSTC("FFFF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add red STC button */
                            this += JFXButton("Send STC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.RED
                                }
                                action {
                                    sendSTC("FF0000")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add magenta STC button */
                            this += JFXButton("Send STC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.MAGENTA
                                }
                                action {
                                    sendSTC("FF00FF")
                                }
                            }
                        }

                        row { label {} }    // Row spacer

                        /*  Fourth row of buttons
                        *   Each button says "Send MTC" for "Send Multi-Pixel Run to Color"
                        *   Each button is colored to match the color it will send
                        */
                        row {
                            /*  Add black MTC button */
                            this += JFXButton("Send MTC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLACK
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendMTC("0")
                                }
                            }

                            label("    ")   // Larger spacer

                            /*  Add blue MTC button */
                            this += JFXButton("Send MTC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLUE
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendMTC("FF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add aqua MTC button */
                            this += JFXButton("Send MTC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.AQUA
                                }
                                action {
                                    sendMTC("FFFF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add green MTC button */
                            this += JFXButton("Send MTC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.GREEN
                                }
                                action {
                                    sendMTC("FF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add yellow MTC button */
                            this += JFXButton("Send MTC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.YELLOW
                                }
                                action {
                                    sendMTC("FFFF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add red MTC button */
                            this += JFXButton("Send MTC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.RED
                                }
                                action {
                                    sendMTC("FF0000")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add magenta MTC button */
                            this += JFXButton("Send MTC").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.MAGENTA
                                }
                                action {
                                    sendMTC("FF00FF")
                                }
                            }
                        }

                        row { label {} }    // Spacer between rows of buttons

                        /*  Fifth row of buttons
                        *   Each button says "Send STK" for "Send Stack"
                        *   Each button is colored to match the color it will send
                        */
                        row {
                            /*  Add black wipe button */
                            this += JFXButton("Send STK").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLACK
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendSTK("0")
                                }
                            }

                            label("    ")   // Larger spacer

                            /*  Add blue wipe button */
                            this += JFXButton("Send STK").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.BLUE
                                    textFill = Color.WHITE
                                }
                                action {
                                    sendSTK("FF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add aqua wipe button */
                            this += JFXButton("Send STK").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.AQUA
                                }
                                action {
                                    sendSTK("FFFF")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add green wipe button */
                            this += JFXButton("Send STK").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.GREEN

                                }
                                action {
                                    sendSTK("FF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add yellow wipe button */
                            this += JFXButton("Send STK").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.YELLOW

                                }
                                action {
                                    sendSTK("FFFF00")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add red wipe button */
                            this += JFXButton("Send STK").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.RED

                                }
                                action {
                                    sendSTK("FF0000")
                                }
                            }

                            label("  ") // Spacer

                            /*  Add magenta wipe button */
                            this += JFXButton("Send STK").apply {
                                alignment = Pos.CENTER
                                useMaxSize = true
                                setMinSize(50.0, 50.0)
                                style {
                                    backgroundColor += Color.MAGENTA

                                }
                                action {
                                    sendSTK("FF00FF")
                                }
                            }

                        }
                    }
                }

                /*  Third row of GUI
                *   Contains slider for brightness
                */
//                row {
//                    gridpane {
//                        row { label {} }    // Row spacer
//
//                        /*  Add brightness slider */
//                        row {
//                            alignment = Pos.CENTER  // Set alignment
//                            /*  Create slider with min, max and default values
//                            *   min = 0.0
//                            *   max = 255.0
//                            *   default = 255.0
//                            */
//                            brightnessSlider = JFXSlider(0.0, 255.0, 255.0)
//                            this += brightnessSlider.apply {
//                                alignment = Pos.CENTER  // Set alignment
//                                setMinSize(150.0, 50.0) // Set minimum size
//                            }
//                        }
//
//                        /*  Add brightness label */
//                        row {
//                            alignment = Pos.CENTER  // Set alignment
//                            /*  Create "Brightness" label */
//                            label("Brightness") {
//                                alignment = Pos.CENTER  // Set alignment
//                                useMaxWidth = true  // Use full size of cell
//                            }
//                        }
//                    }
//                }
            }
        }
    }
}
