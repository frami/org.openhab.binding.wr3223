package org.openhab.binding.wr3223.internal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Check if EVU blockade is on/off.
 *
 * @author Christian Spiegel
 *
 */
final class EvuBlockadeHandler {

    private static final Logger logger = LoggerFactory.getLogger(EvuBlockadeHandler.class);

    private static final int FLAG_BLOCKADE = 1;

    private boolean blockade = false;

    public static EvuBlockadeHandler valueOf(String read) {
        EvuBlockadeHandler handler = new EvuBlockadeHandler();
        read = read.trim();
        int decPoint = read.indexOf(".");
        if (decPoint > 0) {
            read = read.substring(0, decPoint);
        }

        int value = Integer.valueOf(read.trim()) * -1;
        if (logger.isDebugEnabled()) {
            logger.debug("WR3223.EvuBlockadeHandler value={}.", value);
        }

        if ((value & FLAG_BLOCKADE) == FLAG_BLOCKADE) {
            handler.blockade = true;
            // TODO Anlage ausschalten bei EVU Sperre?
        }

        return handler;
    }

    /**
     * @return the blockade
     */
    public boolean isBlockade() {
        return blockade;
    }
}
