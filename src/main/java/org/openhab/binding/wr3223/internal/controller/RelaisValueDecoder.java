package org.openhab.binding.wr3223.internal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Coding of the RL command.
 *
 * @author Michael Fraefel
 *
 */
final class RelaisValueDecoder {

    private static final Logger logger = LoggerFactory.getLogger(RelaisValueDecoder.class);

    private static final int FLAG_COMPRESSOR = 1;
    private static final int FLAG_ADDITIONAL_HEATER = 2;
    private static final int FLAG_EARTH_HEAT_EXCHANGER = 4;
    private static final int FLAG_BYPASS = 8;
    private static final int FLAG_PREHEATING_RADIATOR = 16;
    private static final int FLAG_BYPASS_RELAY = 32;
    private static final int FLAG_CONTROL_DEVICE_ACTIVE = 64;
    private static final int FLAG_OPENHAB_INTERFACE_ACTIVE = 128;
    private static final int FLAG_VENTILATION_LEVEL_AVAILABLE = 256;
    private static final int FLAG_WARM_WATER_POST_HEATER = 512;
    private static final int FLAG_MAGNET_VALVE = 2048;
    private static final int FLAG_PRE_HEATER_RADIATOR_ACTIVE = 4096;

    private boolean compressor;
    private boolean additionalHeater;
    private boolean earthHeatExchanger;
    private boolean bypass;
    private boolean bypassRelay;
    private boolean preheatingRadiator;
    private boolean controlDeviceActive;
    private boolean openhabInterfaceActive;
    private boolean ventilationLevelAvailable;
    private boolean warmWaterPostHeater;
    private boolean magnetValve;
    private boolean preHeaterRadiatorActive;

    public static RelaisValueDecoder valueOf(String read) {
        RelaisValueDecoder relaisValue = new RelaisValueDecoder();
        read = read.trim();
        int decPoint = read.indexOf(".");
        if (decPoint > 0) {
            read = read.substring(0, decPoint);
        }
        logger.debug("WR3223.RelaisValueDecoder read string={}.", read);
        int value = Integer.valueOf(read.trim());
        if ((value & FLAG_COMPRESSOR) == FLAG_COMPRESSOR) {
            relaisValue.compressor = true;
        }
        if ((value & FLAG_ADDITIONAL_HEATER) == FLAG_ADDITIONAL_HEATER) {
            relaisValue.additionalHeater = true;
        }
        if ((value & FLAG_EARTH_HEAT_EXCHANGER) == FLAG_EARTH_HEAT_EXCHANGER) {
            relaisValue.earthHeatExchanger = true;
        }
        if ((value & FLAG_BYPASS) == FLAG_BYPASS) {
            relaisValue.bypass = true;
        }
        if ((value & FLAG_PREHEATING_RADIATOR) == FLAG_PREHEATING_RADIATOR) {
            relaisValue.preheatingRadiator = true;
        }
        if ((value & FLAG_BYPASS_RELAY) == FLAG_BYPASS_RELAY) {
            relaisValue.bypassRelay = true;
        }
        if ((value & FLAG_CONTROL_DEVICE_ACTIVE) == FLAG_CONTROL_DEVICE_ACTIVE) {
            relaisValue.controlDeviceActive = true;
        }
        if ((value & FLAG_OPENHAB_INTERFACE_ACTIVE) == FLAG_OPENHAB_INTERFACE_ACTIVE) {
            relaisValue.openhabInterfaceActive = true;
        }
        if ((value & FLAG_VENTILATION_LEVEL_AVAILABLE) == FLAG_VENTILATION_LEVEL_AVAILABLE) {
            relaisValue.ventilationLevelAvailable = true;
        }
        if ((value & FLAG_WARM_WATER_POST_HEATER) == FLAG_WARM_WATER_POST_HEATER) {
            relaisValue.warmWaterPostHeater = true;
        }
        if ((value & FLAG_MAGNET_VALVE) == FLAG_MAGNET_VALVE) {
            relaisValue.magnetValve = true;
        }
        if ((value & FLAG_PRE_HEATER_RADIATOR_ACTIVE) == FLAG_PRE_HEATER_RADIATOR_ACTIVE) {
            relaisValue.preHeaterRadiatorActive = true;
        }
        return relaisValue;
    }

    /**
     * @return "Kompressor Relais"
     */
    public boolean isCompressor() {
        return compressor;
    }

    /**
     * @return "Zusatzheizung Relais"
     */
    public boolean isAdditionalHeater() {
        return additionalHeater;
    }

    /**
     * @return "Erdwärmetauscher"
     */
    public boolean isEarthHeatExchanger() {
        return earthHeatExchanger;
    }

    /**
     * @return "Bypass"
     */
    public boolean isBypass() {
        return bypass;
    }

    /**
     * "Netzrelais Bypass"
     *
     * @return
     */
    public boolean isBypassRelay() {
        return bypassRelay;
    }

    /**
     * @return "Vorheizregister"
     */
    public boolean isPreheatingRadiator() {
        return preheatingRadiator;
    }

    /**
     * @return "Bedienteil aktiv"
     */
    public boolean isControlDeviceActive() {
        return controlDeviceActive;
    }

    /**
     * @return "Bedienung über RS Schnittstelle"
     */
    public boolean isOpenhabInterfaceActive() {
        return openhabInterfaceActive;
    }

    /**
     * @return "Luftstufe vorhanden"
     */
    public boolean isVentilationLevelAvailable() {
        return ventilationLevelAvailable;
    }

    /**
     * @return "WW_Nachheizrgister"
     */
    public boolean isWarmWaterPostHeater() {
        return warmWaterPostHeater;
    }

    /**
     * @return "Magnetventil"
     */
    public boolean isMagnetValve() {
        return magnetValve;
    }

    /**
     * @return "Vorheizen aktiv"
     */
    public boolean isPreHeaterRadiatorActive() {
        return preHeaterRadiatorActive;
    }

}