package org.openhab.binding.wr3223.internal.controller;

/**
 * Hold the values which must be send every 20 seconds to the WR3223. The values received from the bus are stored in
 * this class.
 *
 * @author Michael Fraefel
 *
 */
final class StatusValueHolder {

    private boolean heatPumpOn = false;
    private int ventilationLevel = 2;
    private boolean additionalHeatingOn = false;
    private boolean coolingOn = false;

    private boolean malfunction = false;
    private final int FLAG_MALFUNCTION = 16;

    private boolean heatPumpOnStatus = false;
    private final int FLAG_HEAT_PUMP_STATUS = 32;

    private boolean additionalHeatingOnStatus = false;
    private final int FLAG_ADDITIONAL_HEATER_STATUS = 64;

    private final int STATUS_MASK = 112;

    /**
     * @param "Wärmepumpe ein (bei Anlagen mit Wärmepumpe)"
     */
    public void setHeatPumpOn(boolean heatPumpOn) {
        this.heatPumpOn = heatPumpOn;
    }

    /**
     * @param "Lüftungsstufe 0-3, 0=Aus"
     */
    public void setVentilationLevel(int ventilationLevel) {
        this.ventilationLevel = ventilationLevel;
    }

    /**
     * @param "Zusatzheizung ein"
     */
    public void setAdditionalHeatingOn(boolean additionalHeatingOn) {
        this.additionalHeatingOn = additionalHeatingOn;
    }

    /**
     * @param "kühlen (bei Anlagen mit Wärmepumpe)"
     */
    public void setCoolingOn(boolean coolingOn) {
        this.coolingOn = coolingOn;
    }

    /**
     * @return Störung
     */
    public boolean isMalfunction() {
        return malfunction;
    }

    /**
     * @return Zusatzheizung An/Aus
     */
    public boolean getAdditionalHeatingOnStatus() {
        return additionalHeatingOnStatus;
    }

    /**
     * @return Wärmepumpe An/Aus
     */
    public boolean getHeatPumpOnStatus() {
        return heatPumpOnStatus;
    }

    public String getStatusValue() {

        int data = 0;
        if (!heatPumpOn) {
            data += 1;
        }
        if (ventilationLevel == 2 || ventilationLevel == 1) {
            data += 2;
        }
        if (ventilationLevel == 3 || ventilationLevel == 1) {
            data += 4;
        }
        if (!additionalHeatingOn) {
            data += 8;
        }
        if (ventilationLevel == 0) {
            data += 16;
        }
        if (!coolingOn) {
            data += 32;
        }

        return String.valueOf(data);
    }

    public void valueOf(String read) {

        read = read.trim();
        int decPoint = read.indexOf(".");
        if (decPoint > 0) {
            read = read.substring(0, decPoint);
        }
        int value = Integer.valueOf(read.trim()) * -1;

        // Mask value as only bits 4, 5 and 6 are necessary
        value &= STATUS_MASK;

        malfunction = (value & FLAG_MALFUNCTION) == FLAG_MALFUNCTION ? true : false;
        heatPumpOnStatus = (value & FLAG_HEAT_PUMP_STATUS) == FLAG_HEAT_PUMP_STATUS ? true : false;
        additionalHeatingOnStatus = (value & FLAG_ADDITIONAL_HEATER_STATUS) == FLAG_ADDITIONAL_HEATER_STATUS ? true
                : false;
    }
}
