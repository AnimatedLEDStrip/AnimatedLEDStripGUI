import tornadofx.*


class SliderStylesheet : Stylesheet() {
    companion object {
        val coloredTrack by cssclass()
        val track by cssclass()
        val thumb by cssclass()
    }

    init {

        coloredTrack {
            backgroundColor += c("white")
        }

        track {
                backgroundColor += c("black")
        }

        thumb {
                backgroundColor += c("lightgray")
        }
    }
}