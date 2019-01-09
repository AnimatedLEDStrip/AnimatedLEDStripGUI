import animatedledstrip.leds.Animations
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSlider
import com.jfoenix.controls.JFXToggleButton
import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
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

    private val colorList = listOf(Color.BLUE, Color.AQUA, Color.LIME, Color.YELLOW, Color.RED, Color.MAGENTA)

    /*  Helper functions for sending commands */
    private fun sendC(color: String) =
            MessageSender.send(mapOf("Animation" to Animations.COLOR, "Color1" to parseHex(color)))

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
        MessageSender.send(mapOf("Animation" to Animations.STACK, "Color1" to parseHex(color), "Direction" to direction))
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


        /*  Set style of whole GUI
        *   Sets background color
        */
        style {
            backgroundColor += Color.CORNFLOWERBLUE
        }

        addExitAndBlankButtons(this)
        addNavigation(this@DefaultsView::class, this@DefaultsView, this)

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

                            colorList.forEach {
                                this += JFXButton("Send C").apply {
                                    alignment = Pos.CENTER
                                    useMaxSize = true
                                    setMinSize(50.0, 50.0)
                                    style {
                                        backgroundColor += it
                                        if (it == Color.BLUE) textFill = Color.WHITE
                                    }
                                    action {
                                        sendC(it.getHex())
                                    }
                                }

                                label("  ") // Spacer
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

                            colorList.forEach {
                                this += JFXButton("Send WIP").apply {
                                    alignment = Pos.CENTER
                                    useMaxSize = true
                                    setMinSize(50.0, 50.0)
                                    style {
                                        backgroundColor += it
                                        if (it == Color.BLUE) textFill = Color.WHITE
                                    }
                                    action {
                                        sendWIP(it.getHex())
                                    }
                                }

                                label("  ") // Spacer
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

                            colorList.forEach {
                                this += JFXButton("Send STC").apply {
                                    alignment = Pos.CENTER
                                    useMaxSize = true
                                    setMinSize(50.0, 50.0)
                                    style {
                                        backgroundColor += it
                                        if (it == Color.BLUE) textFill = Color.WHITE
                                    }
                                    action {
                                        sendSTC(it.getHex())
                                    }
                                }

                                label("  ") // Spacer
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

                            colorList.forEach {
                                this += JFXButton("Send MTC").apply {
                                    alignment = Pos.CENTER
                                    useMaxSize = true
                                    setMinSize(50.0, 50.0)
                                    style {
                                        backgroundColor += it
                                        if (it == Color.BLUE) textFill = Color.WHITE
                                    }
                                    action {
                                        sendMTC(it.getHex())
                                    }
                                }

                                label("  ") // Spacer
                            }
                        }
                        row { label {} }    // Spacer between rows of buttons

                        /*  Fifth row of buttons
                        *   Each button says "Send STK" for "Send Stack"
                        *   Each button is colored to match the color it will send
                        */
                        row {
                            /*  Add black stack button */
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

                            colorList.forEach {
                                this += JFXButton("Send STK").apply {
                                    alignment = Pos.CENTER
                                    useMaxSize = true
                                    setMinSize(50.0, 50.0)
                                    style {
                                        backgroundColor += it
                                        if (it == Color.BLUE) textFill = Color.WHITE
                                    }
                                    action {
                                        sendSTK(it.getHex())
                                    }
                                }

                                label("  ") // Spacer
                            }
                        }
                    }
                }
            }
        }
    }
}
