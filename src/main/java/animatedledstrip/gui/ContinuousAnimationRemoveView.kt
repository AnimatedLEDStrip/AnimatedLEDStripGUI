package animatedledstrip.gui


import javafx.event.EventHandler
import javafx.geometry.Pos
import tornadofx.*

class ContinuousAnimationRemoveView : View() {

    override val root = borderpane {

        addExitAndBlankButtons(this@ContinuousAnimationRemoveView::class, this@ContinuousAnimationRemoveView, this)
        addNavigation(this@ContinuousAnimationRemoveView::class, this@ContinuousAnimationRemoveView, this)

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
                addNavigation(this@ContinuousAnimationRemoveView::class, this@ContinuousAnimationRemoveView, this)
                onTouchPressed = EventHandler {
                    wakeScreen()
                }
            }
        }
    }
}
