import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import tornadofx.*

class ContinuousAnimationRemoveView : View() {

    override val root = borderpane {

        addExitAndBlankButtons(this)
        onKeyPressed = EventHandler { t ->
            when (t.code) {
                KeyCode.RIGHT -> replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                KeyCode.LEFT -> replaceWith(ContinuousAnimationAddView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
            }
        }
        center {
            scrollpane {

                style {
                    alignment = Pos.CENTER
                }

                this += animations.apply {
                    style {
                        alignment = Pos.CENTER
                    }
                }
                onKeyPressed = EventHandler { t ->
                    when (t.code) {
                        KeyCode.RIGHT -> replaceWith(CustomColorView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
                        KeyCode.LEFT -> replaceWith(ContinuousAnimationAddView::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
                    }
                }
            }
        }
    }
}
