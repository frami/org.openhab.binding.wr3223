package org.openhab.binding.wr3223.internal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Check the different possible errors codes
 *
 * @author Christian Spiegel
 *
 */
public final class ErrorValueDecoder {

    private static final Logger logger = LoggerFactory.getLogger(ErrorValueDecoder.class);

    private static final int ERROR_TEMP_SENSOR_SHORT = 1;
    private static final int ERROR_OFFSET = 2;
    private static final int ERROR_TEMP_SENSOR_INTERUPT = 3;
    private static final int ERROR_HIGH_PRESSURE = 4;
    private static final int ERROR_SYS_RAM = 61;
    private static final int ERROR_SYS_ROM = 62;
    private static final int ERROR_SYS_EE = 65;
    private static final int ERROR_SYS_IO = 66;
    private static final int ERROR_SYS_67_AD = 67;
    private static final int ERROR_SUPPLY_AIR = 128;
    private static final int ERROR_OUTGOING_AIR = 132;
    private static final int ERROR_CONDENSER = 130;
    private static final int ERROR_PREHEATING = 133;

    private boolean error_temp_sensor_short;
    private boolean error_offset;
    private boolean error_temp_sensor_interupt;
    private boolean error_high_pressure;
    private boolean error_sys_ram;
    private boolean error_sys_rom;
    private boolean error_sys_ee;
    private boolean error_sys_io;
    private boolean error_sys_67_ad;
    private boolean error_supply_air;
    private boolean error_outgoing_air;
    private boolean error_condenser;
    private boolean error_preheating;

    public static ErrorValueDecoder valueOf(String read) {

        ErrorValueDecoder errorValue = new ErrorValueDecoder();
        read = read.trim();
        int decPoint = read.indexOf(".");
        if (decPoint > 0) {
            read = read.substring(0, decPoint);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("WR3223.ErrorValueDecoder read string={}.", read);
        }
        int value = Integer.valueOf(read.trim());

        if ((value & ERROR_TEMP_SENSOR_SHORT) == ERROR_TEMP_SENSOR_SHORT) {
            errorValue.error_temp_sensor_short = true;
        }
        if ((value & ERROR_OFFSET) == ERROR_OFFSET) {
            errorValue.error_offset = true;
        }
        if ((value & ERROR_TEMP_SENSOR_INTERUPT) == ERROR_TEMP_SENSOR_INTERUPT) {
            errorValue.error_temp_sensor_interupt = true;
        }
        if ((value & ERROR_HIGH_PRESSURE) == ERROR_HIGH_PRESSURE) {
            errorValue.error_high_pressure = true;
        }
        if ((value & ERROR_SYS_RAM) == ERROR_SYS_RAM) {
            errorValue.error_sys_ram = true;
        }
        if ((value & ERROR_SYS_ROM) == ERROR_SYS_ROM) {
            errorValue.error_sys_rom = true;
        }
        if ((value & ERROR_SYS_EE) == ERROR_SYS_EE) {
            errorValue.error_sys_ee = true;
        }
        if ((value & ERROR_SYS_IO) == ERROR_SYS_IO) {
            errorValue.error_sys_io = true;
        }
        if ((value & ERROR_SYS_67_AD) == ERROR_SYS_67_AD) {
            errorValue.error_sys_67_ad = true;
        }
        if ((value & ERROR_SUPPLY_AIR) == ERROR_SUPPLY_AIR) {
            errorValue.error_supply_air = true;
        }
        if ((value & ERROR_OUTGOING_AIR) == ERROR_OUTGOING_AIR) {
            errorValue.error_outgoing_air = true;
        }
        if ((value & ERROR_CONDENSER) == ERROR_CONDENSER) {
            errorValue.error_condenser = true;
        }
        if ((value & ERROR_PREHEATING) == ERROR_PREHEATING) {
            errorValue.error_preheating = true;
        }
        return errorValue;
    }

    /**
     * @return the error_temp_sensor_short
     */
    public boolean isError_temp_sensor_short() {
        return error_temp_sensor_short;
    }

    /**
     * @return the error_offset
     */
    public boolean isError_offset() {
        return error_offset;
    }

    /**
     * @return the error_temp_sensor_interupt
     */
    public boolean isError_temp_sensor_interupt() {
        return error_temp_sensor_interupt;
    }

    /**
     * @return the error_high_pressure
     */
    public boolean isError_high_pressure() {
        return error_high_pressure;
    }

    /**
     * @return the error_sys_ram
     */
    public boolean isError_sys_ram() {
        return error_sys_ram;
    }

    /**
     * @return the error_sys_rom
     */
    public boolean isError_sys_rom() {
        return error_sys_rom;
    }

    /**
     * @return the error_sys_ee
     */
    public boolean isError_sys_ee() {
        return error_sys_ee;
    }

    /**
     * @return the error_sys_io
     */
    public boolean isError_sys_io() {
        return error_sys_io;
    }

    /**
     * @return the error_sys_67_ad
     */
    public boolean isError_sys_67_ad() {
        return error_sys_67_ad;
    }

    /**
     * @return the error_supply_air
     */
    public boolean isError_supply_air() {
        return error_supply_air;
    }

    /**
     * @return the error_outgoing_air
     */
    public boolean isError_outgoing_air() {
        return error_outgoing_air;
    }

    /**
     * @return the error_condenser
     */
    public boolean isError_condenser() {
        return error_condenser;
    }

    /**
     * @return the error_preheating
     */
    public boolean isError_preheating() {
        return error_preheating;
    }
}
