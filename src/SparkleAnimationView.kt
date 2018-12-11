import com.jfoenix.controls.JFXToggleButton
import tornadofx.*


class SparkleAnimationView : View() {

    var toggleReverse: JFXToggleButton by singleAssign()

    override val root = borderpane {

        center {
            gridpane {
                row {
                    toggleReverse = JFXToggleButton()
                    this += toggleReverse.apply {
                        action {

                        }
                    }
                }
            }
        }

    }
}

