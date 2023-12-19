package com.hlysine.create_connected;

import com.simibubi.create.content.kinetics.transmission.sequencer.SequencerInstructions;
import org.jetbrains.annotations.NotNull;

public class CCSequencerInstructions {
    @NotNull
    public static SequencerInstructions TURN_AWAIT;

    static {
        for (SequencerInstructions value : SequencerInstructions.values()) {
            if (value.name().equals("TURN_AWAIT")) {
                TURN_AWAIT = value;
            }
        }
    }
}
