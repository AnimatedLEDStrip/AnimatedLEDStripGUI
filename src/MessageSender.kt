import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.text.Font
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tornadofx.*
import java.io.*
import java.net.Socket

object MessageSender {
    private val socket: Socket = try {
        Socket(ipAddress, 5)
    } catch (e: Exception) {
        shutdownGUI()
        Socket("0.0.0.0", 5)
    }
    private val out: ObjectOutputStream
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
                val animationData = input["Animation"] as Map<*, *>
                animations += JFXButton(
                        "${animationData["Animation"]}: ID ${input["ID"].toString()}"
                ).apply {
                    style {
                        alignment = Pos.CENTER
                        backgroundColor += ColorContainer(animationData["Color1"] as Long).toColor()
                        font = Font.font(12.0)
                    }
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
    }

    fun send(args: Map<*, *>) {
        out.writeObject(args)
    }

    fun isAnimationComplete() = animationComplete
    fun resetAnimationComplete() {
        animationComplete = false
    }
}
