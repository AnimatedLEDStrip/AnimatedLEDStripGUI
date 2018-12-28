import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tornadofx.*
import java.io.*
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket

object MessageSender {
    private val socket: Socket = try {
        Socket(ipAddress, 5)
    } catch(e: Exception) {
        shutdownGUI()
        Socket("0.0.0.0", 5)
    }
    private val out: ObjectOutputStream
//    private val inStream: InputStreamReader
    private val socIn: ObjectInputStream
    private var doQuit = false
    private var animationComplete = false

    init {
        out = ObjectOutputStream(socket.getOutputStream())
        socIn = ObjectInputStream(BufferedInputStream(socket.getInputStream()))
        animations = VBox()

        GlobalScope.launch {
            var input: Map<*, *>
            while (true) {
                input = socIn.readObject() as Map<*, *>
                println("Received: $input")
                animations += JFXButton("${input["Animation"]}: ${input["ID"].toString()}").apply {
                    alignment = Pos.CENTER
                    val id = input["ID"].toString()
                    action {
                        GlobalScope.launch {
                            send(mapOf("Animation" to Animations.ENDANIMATION, "ID" to id))
                        }
                        animations.children.remove(this@apply)
                    }
                }
            }
        }

//        GlobalScope.launch {
//            while (true) {
//                when (socIn.readLine()) {
//                    "C" -> animationComplete = true
//                    "Q" -> System.exit(0)
//                }
//            }
//        }
    }

    fun send(args: Map<*, *>) {
        out.writeObject(args)
    }

    fun isAnimationComplete() = animationComplete
    fun resetAnimationComplete() {
        animationComplete = false
    }
}

