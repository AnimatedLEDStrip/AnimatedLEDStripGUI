[![KDoc](https://img.shields.io/badge/KDoc-read-green.svg)](https://animatedledstrip.github.io/AnimatedLEDStripGUI/animatedledstrip-gui/index.html)

# AnimatedLEDStripGUI
This program is designed to communicate with an AnimatedLEDStripServer to start and end animations.

## Raspberry Pi Touchscreen
This is designed mainly to be run on a Raspberry Pi with a 7" touchscreen. To install, first the java version needs
to be rolled back to 8 (openjdk-8) and JavaFX needs to be installed (follow instructions for embedded devices
[here](https://docs.gluonhq.com/javafxports/#_embedded)). The program can then be packaged, copied onto the
Pi, and run.

## Uses Java 8
Because of a requirement in the [JFoenix](https://github.com/jfoenixadmin/JFoenix) library, we must use Java 8
> https://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase8-2177648.html

