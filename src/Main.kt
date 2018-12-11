import javafx.stage.Stage
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*


fun main(args: Array<String>) {
//    PaletteHandler.addPalette("Test", listOf(255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 255))
//    PaletteHandler.saveConfig()
    try {
        launch<MyApp>(args)
    } catch (e: Exception) {
        println("Exception somewhere in program")
        try {shutdownGUI()}
        catch (e: Exception) {}
    }
}

fun shutdownGUI() {
    try {
        val commandLine = CommandLine.parse("xset -d :0 s 60")
        val executor = DefaultExecutor()
        executor.setExitValue(0)
        executor.execute(commandLine)
    } catch (e: Exception) {
        println("Exception while setting blank time to 60s")
    }

    try {System.exit(0)} catch(e: Exception) {}
}

class MyApp : App(DisconnectedView::class, SliderStylesheet::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isFullScreen = true

        val commandLine = CommandLine.parse("xset -d :0 s 30")
        val executor = DefaultExecutor()
        executor.setExitValue(0)
        executor.execute(commandLine)

    }
}
