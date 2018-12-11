import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.ObjectOutputStream
import java.io.PrintWriter
import java.net.Socket

object MessageSender {

    private val socket = Socket("10.0.0.254", 5)
    private val out: ObjectOutputStream
    private val inStream: InputStreamReader
    private val socIn: BufferedReader
    private var doQuit = false
    private var animationComplete = false

    init {
        out = ObjectOutputStream(socket.getOutputStream())
        inStream = InputStreamReader(socket.getInputStream())
        socIn = BufferedReader(InputStreamReader(socket.getInputStream()))
        GlobalScope.launch {
            while (true) {
                when (socIn.readLine()) {
                    "C" -> animationComplete = true
                    "Q" -> System.exit(0)
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

