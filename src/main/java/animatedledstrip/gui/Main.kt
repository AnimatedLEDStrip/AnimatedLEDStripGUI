package animatedledstrip.gui


import animatedledstrip.client.AnimationSenderFactory
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import tornadofx.*

var isFullscreen = true

var animations: VBox by singleAssign()

var ipAddress = "10.44.157.2"

var mainSender: AnimationSenderFactory.AnimationSender? = null

fun main(args: Array<String>) {
    animations = VBox()
    try {
        if (args[0].toUpperCase() == "WINDOWED") isFullscreen = false
    } catch (e: Exception) {
    }

    try {
        launch<MyApp>(args)
    } catch (e: Exception) {
        println("Exception somewhere in program")
        try {
            shutdownGUI()
        } catch (e: Exception) {
        }
    }
}

fun shutdownGUI() {
    try {
        val commandLine = CommandLine.parse("xset -d :0 s 60")
        val executor = DefaultExecutor()
        executor.setExitValue(0)
        executor.execute(commandLine)
        try {
//            MessageSender.send(mapOf("Quit" to true))
        } catch (e: Exception) {
        }
    } catch (e: Exception) {
        println("Exception while setting blank time to 60s: $e")
    }

    try {
        System.exit(0)
    } catch (e: Exception) {
    }
}

class MyApp : App(DisconnectedView::class, SliderStylesheet::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        stage.isFullScreen = isFullscreen
        if (!isFullscreen) {
            stage.minHeight = 480.0
            stage.minWidth = 800.0
        }

        try {
            val commandLine = CommandLine.parse("xset -d :0 s 30")
            val executor = DefaultExecutor()
            executor.setExitValue(0)
            executor.execute(commandLine)
        } catch (e: Exception) {
            println("Exception while setting blank time to 30s: $e")
        }
    }
}

fun wakeScreen() = try {
    val commandLine = CommandLine.parse("xset -d :0 dpms force on")
    val executor = DefaultExecutor()
    executor.setExitValue(0)
    executor.execute(commandLine)
} catch (e: Exception) {
    println("Exception while waking screen: $e")
}

fun blankScreen() = try {
    val commandLine = CommandLine.parse("xset -d :0 dpms force off")
    val executor = DefaultExecutor()
    executor.setExitValue(0)
    executor.execute(commandLine)   //  Send command
} catch (e: Exception) {
    println("Exception while blanking screen: $e")
}