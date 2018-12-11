import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

var continueAnimationGroup = true

fun runAnimationGroup() {
    GlobalScope.launch {

    }
}

fun getMTCString(color: String) : String = "MTC $color 10 F"

val animationGroupList = listOf(
        "MPR FF 0 5 F",
        "MPR FF 0 5 F",
        "MPR FF 0 5 F",
        "MPR FF 0 5 F",
        "MPR FF 0 5 F",
        "MPR FF 0 5 F",
        "SPK FF 0",
        "MPR FF00 0 5 B",
        "MPR FF00 0 5 B",
        "MPR FF00 0 5 B",
        "MPR FF00 0 5 B",
        "MPR FF00 0 5 B",
        "MPR FF00 0 5 B",
        "PXR FF 0 F N",
        "WIP FFFF F",
        "MTC FFFF00 5 F"
)