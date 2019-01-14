//import animatedledstrip.leds.Animations
//import animatedledstrip.leds.ColorContainer
//import com.jfoenix.controls.JFXButton
//import javafx.geometry.Pos
//import javafx.scene.layout.VBox
//import javafx.scene.paint.Color
//import javafx.scene.text.Font
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import tornadofx.*
//import java.io.*
//import java.net.InetSocketAddress
//import java.net.Socket
//
//object MessageSender {
//    private var socket: Socket = Socket()
//    private var out: ObjectOutputStream? = null
//    private var socIn: ObjectInputStream? = null
//    private var disconnected = true
//    private var connectionTries = 0
//
//    init {
//        animations = VBox()
//        GlobalScope.launch {
//            startSocket()
//        }
//    }
//
//    private suspend fun startSocket() {
//        while (true) {
//            connectSocket()
//            try {
//                out = ObjectOutputStream(socket.getOutputStream())
//                socIn = ObjectInputStream(BufferedInputStream(socket.getInputStream()))
//                var input: Map<*, *>
//                while (true) {
//                    input = socIn!!.readObject() as Map<*, *>
//                    println("Received: $input")
//                    val animationData = input["Animation"] as Map<*, *>
//                    animations += JFXButton(
//                            "${animationData["Animation"]}: ID ${input["ID"].toString()}"
//                    ).apply {
//                        style {
//                            alignment = Pos.CENTER
//                            val color1 = ColorContainer(animationData["Color1"] as Long)
//                            backgroundColor += color1.toColor()
//                            font = Font.font(12.0)
//                            if (color1.grayscale() < 0x80) textFill = Color.WHITE
//                        }
//                        val id = input["ID"].toString()
//                        action {
//                            GlobalScope.launch {
//                                send(mapOf("Animation" to Animations.ENDANIMATION, "ID" to id))
//                            }
//                            animations.children.remove(this@apply)
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                println("Connection Lost")
//                socket = Socket()
//                disconnected = true
//            }
//        }
//    }
//
//    private suspend fun connectSocket() {
//        try {
//            socket.connect(InetSocketAddress(ipAddress, 5), 5000)
//            println("Connected to server at $ipAddress")
//            disconnected = false
//            connectionTries = 0
//        } catch (e: Exception) {
//            connectionTries++
//            println("Connection attempt $connectionTries: Server not found at $ipAddress: $e")
//            delay(10000)
//            if (connectionTries <= 5) {
//                socket = Socket()
//                connectSocket()
//            } else {
//                println("Could not locate server at $ipAddress after $connectionTries tries")
//                shutdownGUI()
//            }
//        }
//    }
//
//
//    fun send(args: Map<*, *>) {
//        try {
//            out!!.writeObject(args)
//        } catch (e: Exception) {
//            println("Error sending animation: $e")
//        }
//    }
//
//    fun isDisconnected() = disconnected
//
//}
