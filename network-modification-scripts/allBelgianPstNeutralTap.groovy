for (twt in network.twoWindingsTransformers) {
    if (twt.terminal1.voltageLevel.substation.country == "BE") {
        twt.phaseTapChanger.tapPosition = 0
    }
}
