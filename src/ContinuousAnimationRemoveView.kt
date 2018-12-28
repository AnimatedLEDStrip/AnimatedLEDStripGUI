import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import tornadofx.*

class ContinuousAnimationRemoveView : View() {

    override val root = borderpane {
        addExitAndBlankButtons(this)
        onKeyPressed = EventHandler { t ->
            when (t.code) {
                KeyCode.RIGHT -> replaceWith(ContinuousAnimationAddView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                KeyCode.LEFT -> replaceWith(InputDynamic::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }
        center {
            scrollpane {

                this += animations.apply {
                    alignment = Pos.CENTER
                }
                onKeyPressed = EventHandler { t ->
                    when (t.code) {
                        KeyCode.RIGHT -> replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                        KeyCode.LEFT -> replaceWith(InputDynamic::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
                    }
                }
            }
        }
    }
}
