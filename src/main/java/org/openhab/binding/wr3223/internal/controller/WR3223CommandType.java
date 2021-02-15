/**
 * Copyright (c) 2010-2019 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.wr3223.internal.controller;

import java.util.Arrays;
import java.util.Optional;

import org.openhab.binding.wr3223.internal.client.WR3223Commands;

/**
 * Represents all valid commands which could be processed by this binding
 *
 * @author Michael Fraefel
 * @since 1.10.0
 */
public enum WR3223CommandType {

    /** T1 (de: Verdampfertemperatur) */
    TEMPERATURE_EVAPORATOR {
        {
            channelId = "temperatureEvaporator";
            wr3223Command = WR3223Commands.T1;
        }
    },

    /** T2 (de: Kondensatortemperatur) */
    TEMPERATURE_CONDENSER {
        {
            channelId = "temperatureCondenser";
            wr3223Command = WR3223Commands.T2;
        }
    },

    /** T3 (de: Aussentemperatur) */
    TEMPERATURE_OUTSIDE {
        {
            channelId = "temperatureOutside";
            wr3223Command = WR3223Commands.T3;
        }
    },

    /** T4 (de: Ablufttemperatur (Raumtemperatur)) */
    TEMPERATURE_OUTGOING_AIR {
        {
            channelId = "temperatureOutgoingAir";
            wr3223Command = WR3223Commands.T4;
        }
    },

    /** T5 (de: Temperatur nach Wärmetauscher (Fortluft)) */
    TEMPERATURE_AFTER_HEAT_EXCHANGER {
        {
            channelId = "temperatureAfterHeatExchanger";
            wr3223Command = WR3223Commands.T5;
        }
    },

    /** T6 (de: Zulufttemperatur) */
    TEMPERATURE_SUPPLY_AIR {
        {
            channelId = "temperatureSupplyAir";
            wr3223Command = WR3223Commands.T6;
        }
    },

    /** T7 (de: Temperatur nach Solevorerwärmung) */
    TEMPERATURE_AFTER_BRINE_PREHEATING {
        {
            channelId = "temperatureAfterBrinePreheating";
            wr3223Command = WR3223Commands.T7;
        }
    },

    /** T8 (de: Temperatur nach Wärmetauscher) */
    TEMPERATURE_AFTER_PREHEATING {
        {
            channelId = "temperatureAfterPreheating";
            wr3223Command = WR3223Commands.T8;
        }
    },

    /** (de: Drehzahl Zuluftmotor) */
    ROTATION_SPEED_SUPPLY_AIR_MOTOR {
        {
            channelId = "rotationSpeedSupplyAirMotor";
            wr3223Command = WR3223Commands.NZ;
        }
    },

    /** (de: Drehzahl Abluftmotor) */
    ROTATION_SPEED_EXHAUST_AIR_MOTOR {
        {
            channelId = "rotationSpeedExhaustAirMotor";
            wr3223Command = WR3223Commands.NA;
        }
    },

    /** (de: Bypass) */
    BYPASS {
        {
            channelId = "bypass";
            wr3223Command = null;
        }
    },

    /** (de: Kompressor Relais) */
    COMPRESSOR {
        {
            channelId = "compressor";
            wr3223Command = null;
        }
    },

    /** (de: Zusatzheizung Relais) */
    ADDITIONAL_HEATER_RELAIS {
        {
            channelId = "additionalHeaterRelais";
            wr3223Command = null;
        }
    },

    /** (de: Zusatzheizung An/Aus) */
    ADDITIONAL_HEATER_STATUS {
        {
            channelId = "additionalHeaterStatus";
            wr3223Command = null;
        }
    },

    /** (de: Zusatzheizung ausgeschaltet (0) oder freigegeben (1) */
    ADDITIONAL_HEATER_OPEN {
        {
            channelId = "additionalHeaterOpen";
            wr3223Command = WR3223Commands.ZH;
        }
    },

    /** (de: Zusatzheizung ein/ausschalten */
    ADDITIONAL_HEATER_ACTIVATE {
        {
            channelId = "additionalHeaterActivate";
            wr3223Command = null;
        }
    },

    /** (de: Netzrelais Bypass) */
    BYPASS_RELAY {
        {
            channelId = "bypassRelay";
            wr3223Command = null;
        }
    },

    /** (de: Vorheizen aktiv) */
    PREHEATING_RADIATOR_ACTIVE {
        {
            channelId = "preheatingRadiatorActive";
            wr3223Command = null;
        }
    },

    /** (de: Bedienteil aktiv) */
    CONTROL_DEVICE_ACTIVE {
        {
            channelId = "controlDeviceActive";
            wr3223Command = null;
        }
    },

    /** (de: Erdwärmetauscher) */
    EARTH_HEAT_EXCHANGER {
        {
            channelId = "earthHeatExchanger";
            wr3223Command = null;
        }
    },

    /** (de: Magnetventil) */
    MAGNET_VALVE {
        {
            channelId = "magnetValve";
            wr3223Command = null;
        }
    },

    /** (de: Bedienung über RS Schnittstelle) */
    OPENHAB_INTERFACE_ACTIVE {
        {
            channelId = "openhabInterfaceActive";
            wr3223Command = null;
        }
    },

    /** (de: Vorheizregister) */
    PREHEATING_RADIATOR {
        {
            channelId = "preheatingRadiator";
            wr3223Command = null;
        }
    },

    /** (de: WW_Nachheizrgister) */
    WARM_WATER_POST_HEATER {
        {
            channelId = "warmWaterPostHeater";
            wr3223Command = null;
        }
    },

    /** (de: Luftstufe vorhanden) */
    VENTILATION_LEVEL_AVAILABLE {
        {
            channelId = "ventilationLevelAvailable";
            wr3223Command = null;
        }
    },

    /** (de: aktuelle Luftstufe) */
    VENTILATION_LEVEL {
        {
            channelId = "ventilationLevel";
            wr3223Command = WR3223Commands.LS;
        }
    },

    /** (de: Betriebsart) */
    OPERATION_MODE {
        {
            channelId = "operationMode";
            wr3223Command = WR3223Commands.MD;
        }
    },

    /** (de: Kühlen) */
    COOLING_MODE_ACTIVATE {
        {
            channelId = "coolingMode";
            wr3223Command = null;
        }
    },

    /** (de: Zuluftsoll Temperatur) */
    TEMPERATURE_SUPPLY_AIR_TARGET {
        {
            channelId = "temperatureSupplyAirTarget";
            wr3223Command = WR3223Commands.SP;
        }
    },

    /** (de: RWZ aktl., Aktuelle Rückwärmzahl in %) */
    HEAT_FEEDBACK_RATE {
        {
            channelId = "heatFeedbackRate";
            wr3223Command = WR3223Commands.RA;
        }
    },

    /** (de: Max. Drehzahlabweichung Zu-/Abluft in Stufe 1 */
    SPEED_DEVIATION_MAX_LEVEL_1 {
        {
            channelId = "speedDeviationMaxLevel1";
            wr3223Command = WR3223Commands.D1;
        }
    },

    /** (de: Max. Drehzahlabweichung Zu-/Abluft in Stufe 2 */
    SPEED_DEVIATION_MAX_LEVEL_2 {
        {
            channelId = "speedDeviationMaxLevel2";
            wr3223Command = WR3223Commands.D2;
        }
    },

    /** (de: Max. Drehzahlabweichung Zu-/Abluft in Stufe 3 */
    SPEED_DEVIATION_MAX_LEVEL_3 {
        {
            channelId = "speedDeviationMaxLevel3";
            wr3223Command = WR3223Commands.D3;
        }
    },

    /** (de: Drehzahlerhöhung Zuluftventilator Stufe 1, wenn Erdwärmetauscher ein (0% bis 40%) */
    SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_1 {
        {
            channelId = "speedIncreaseEarthHeatExchangerLevel1";
            wr3223Command = WR3223Commands.E1;
        }
    },

    /** (de: Drehzahlerhöhung Zuluftventilator Stufe 2, wenn Erdwärmetauscher ein (0% bis 40%) */
    SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_2 {
        {
            channelId = "speedIncreaseEarthHeatExchangerLevel2";
            wr3223Command = WR3223Commands.E2;
        }
    },

    /** (de: Drehzahlerhöhung Zuluftventilator Stufe 3, wenn Erdwärmetauscher ein (0% bis 40%) */
    SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_3 {
        {
            channelId = "speedIncreaseEarthHeatExchangerLevel3";
            wr3223Command = WR3223Commands.E3;
        }
    },

    /** (de: LuflREDUK, Luftwechsel um 3% reduziert ab Außentemp. ...°C (-20°C bis +10°C) */
    AIR_EXCHANGE_DECREASE_OUTSIDE_TEMPERATURE {
        {
            channelId = "airExchangeDecreaseOutsideTemperature";
            wr3223Command = WR3223Commands.LR;
        }
    },

    /** (de: Luftstufe 1, % des max. Ventilatorstellwerts (40 bis 100%) */
    VENTILATION_SPEED_LEVEL_1 {
        {
            channelId = "ventilationSpeedLevel1";
            wr3223Command = WR3223Commands.L1;
        }
    },

    /** (de: Luftstufe 2, % des max. Ventilatorstellwerts (40 bis 100%) */
    VENTILATION_SPEED_LEVEL_2 {
        {
            channelId = "ventilationSpeedLevel2";
            wr3223Command = WR3223Commands.L2;
        }
    },

    /** (de: Luftstufe 3, % des max. Ventilatorstellwerts (40 bis 100%) */
    VENTILATION_SPEED_LEVEL_3 {
        {
            channelId = "ventilationSpeedLevel3";
            wr3223Command = WR3223Commands.L3;
        }
    },

    /** (de: ET sommer >, Einschalt-Außentemperatur Erdwämietauscher im Sommer (20°C bis 40°C) */
    SUMMER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE {
        {
            channelId = "summerEarthHeatExchangerActivationTemperature";
            wr3223Command = WR3223Commands.ES;
        }
    },

    /** (de: ET winter<, Einschalt-Außentemperatur Erdwärmetauscher im Winter (-20°C bis 10°C) */
    WINTER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE {
        {
            channelId = "winterEarthHeatExchangerActivationTemperature";
            wr3223Command = WR3223Commands.EW;
        }
    },

    /** (de: Abtau EIN, Beginn Abtauung ab Verdampfertemperatur ...°C */
    DEFROSTING_START_TEMPERATURE {
        {
            channelId = "defrostingStartTemperature";
            wr3223Command = WR3223Commands.AE;
        }
    },

    /** (de: Abtau AUS, Ende Abtauung ab Verdampfertemperatur ...°C */
    DEFROSTING_END_TEMPERATURE {
        {
            channelId = "defrostingEndTemperature";
            wr3223Command = WR3223Commands.AA;
        }
    },

    /** (de: Abtau Luft, Lüfterstufe im Abtaubetrieb */
    DEFROSTING_VENTILATION_LEVEL {
        {
            channelId = "defrostingVentilationLevel";
            wr3223Command = WR3223Commands.Az;
        }
    },

    /** (de: Abtaupause, Sperrzeit für den nächsten Abtauvorgang */
    DEFROSTING_HOLD_OFF_TIME {
        {
            channelId = "defrostingHoldOffTime";
            wr3223Command = WR3223Commands.AP;
        }
    },

    /** (de: Abtau NFL, Abtaunachlauzeit */
    DEFROSTING_OVERTRAVEL_TIME {
        {
            channelId = "defrostingOvertravelTime";
            wr3223Command = WR3223Commands.AN;
        }
    },

    /** (de: Abtau RWZ, Abtaurückwärmezahl Schaltpunkt (20% bis 80 %) */
    DEFROSTING_HEAT_FEEDBACK_RATE {
        {
            channelId = "defrostingHeatFeedbackRate";
            wr3223Command = WR3223Commands.AR;
        }
    },

    /** (de: Solar max */
    SOLAR_MAX {
        {
            channelId = "solarMax";
            wr3223Command = WR3223Commands.SM;
        }
    },

    /** (de: Solar Nutzen (Stunden) */
    SOLAR_USAGE {
        {
            channelId = "solarUsage";
            wr3223Command = WR3223Commands.SN;
        }
    },

    /** (de: Delta T Aus Temperaturdifferenz zwischen Speicher u. Kollektor */
    DELTA_T_OFF {
        {
            channelId = "deltaTOff";
            wr3223Command = WR3223Commands.DA;
        }
    },

    /** (de: Delta T Ein Temperaturdifferenz zwischen Speicher u. Kollektor */
    DELTA_T_ON {
        {
            channelId = "deltaTOn";
            wr3223Command = WR3223Commands.DE;
        }
    },

    /** (de: Maximale Kondensatortemperatur */
    TEMPERATURE_CONDENSER_MAX {
        {
            channelId = "temperatureCondenserMax";
            wr3223Command = WR3223Commands.KM;
        }
    },

    /** (de: Pausezeit für Druckabbau bei automatischer Umschaltung */
    IDLE_TIME_PRESSURE_REDUCTION {
        {
            channelId = "idleTimePressureReduction";
            wr3223Command = WR3223Commands.PA;
        }
    },

    /** (de: Unterstuetzungsgeblaese bei Luftstufe 1 bei EWT */
    SUPPORT_FAN_LEVEL_1_EARTH_HEAT_EXCHANGER {
        {
            channelId = "supportFanLevel1EarthHeatExchanger";
            wr3223Command = WR3223Commands.S1;
        }
    },

    /** (de: Unterstuetzungsgeblaese bei Luftstufe 2 bei EWT */
    SUPPORT_FAN_LEVEL_2_EARTH_HEAT_EXCHANGER {
        {
            channelId = "supportFanLevel2EarthHeatExchanger";
            wr3223Command = WR3223Commands.S2;
        }
    },

    /** (de: Unterstuetzungsgeblaese bei Luftstufe 3 bei EWT */
    SUPPORT_FAN_LEVEL_3_EARTH_HEAT_EXCHANGER {
        {
            channelId = "supportFanLevel3EarthHeatExchanger";
            wr3223Command = WR3223Commands.S3;
        }
    },

    /** (de: Steuerspannung Abluft */
    CONTROL_VOLTAGE_OUTGOING_AIR {
        {
            channelId = "controlVoltageOutgoingAir";
            wr3223Command = WR3223Commands.UA;
        }
    },

    /** (de: Steuerspannung Zuluft */
    CONTROL_VOLTAGE_SUPPLY_AIR {
        {
            channelId = "controlVoltageSupplyAir";
            wr3223Command = WR3223Commands.UZ;
        }
    },

    /** (de: Warmwasser Sollwert */
    WARM_WATER_TARGET_TEMPERATURE {
        {
            channelId = "warmWaterTargetTemperature";
            wr3223Command = WR3223Commands.WS;
        }
    },

    /** (de: Waermepumpe An/Aus */
    HEAT_PUMP_STATUS {
        {
            channelId = "heatPumpStatus";
            wr3223Command = null;
        }
    },

    /** (de: Waermepumpe freigegeben (1) oder aus (0) */
    HEAT_PUMP_OPEN {
        {
            channelId = "heatPumpOpen";
            wr3223Command = WR3223Commands.WP;
        }
    },

    /** (de: Waermepumpe Ein/-Ausschalten */
    HEAT_PUMP_ACTIVATE {
        {
            channelId = "heatPumpActivate";
            wr3223Command = null;
        }
    },

    /** (de: EVU Abschaltung */
    EVU_BLOCKADE {
        {
            channelId = "evuBlockade";
            wr3223Command = null;
        }
    },

    /** (de: Störung vorhanden */
    MALFUNCTION {
        {
            channelId = "malfunction";
            wr3223Command = null;
        }
    },

    /** (de: kurzsch.TS */
    ERROR_TEMP_SENSOR_SHORT {
        {
            channelId = "errorTempSensorShort";
            wr3223Command = null;
        }
    },

    /** (de: Offset error */
    ERROR_OFFSET {
        {
            channelId = "errorOffset";
            wr3223Command = null;
        }
    },

    /** (de: unterbr.TS */
    ERROR_TEMP_SENSOR_INTERUPT {
        {
            channelId = "errorTempSensorInterupt";
            wr3223Command = null;
        }
    },

    /** (de: Hochdruckfehler */
    ERROR_HIGH_PRESSURE {
        {
            channelId = "errorHighPressure";
            wr3223Command = null;
        }
    },

    /** (de: error sys ram */
    ERROR_SYS_RAM {
        {
            channelId = "errorSysRam";
            wr3223Command = null;
        }
    },

    /** (de: error sys rom */
    ERROR_SYS_ROM {
        {
            channelId = "errorSysRom";
            wr3223Command = null;
        }
    },

    /** (de: error sys ee */
    ERROR_SYS_EE {
        {
            channelId = "errorSysEe";
            wr3223Command = null;
        }
    },

    /** (de: error sys io */
    ERROR_SYS_IO {
        {
            channelId = "errorSysIo";
            wr3223Command = null;
        }
    },

    /** (de: error sys 67 ad */
    ERROR_SYS_67_AD {
        {
            channelId = "errorSys67Ad";
            wr3223Command = null;
        }
    },

    /** (de: Zuluft fehlt */
    ERROR_SUPPLY_AIR {
        {
            channelId = "errorSupplyAir";
            wr3223Command = null;
        }
    },

    /** (de: Abluft fehlt */
    ERROR_OUTGOING_AIR {
        {
            channelId = "errorOutgoingAir";
            wr3223Command = null;
        }
    },

    /** (de: Kondensationsfehler */
    ERROR_CONDENSER {
        {
            channelId = "errorCondenser";
            wr3223Command = null;
        }
    },

    /** (de: Vorheizfehler */
    ERROR_PREHEATING {
        {
            channelId = "errorPreheating";
            wr3223Command = null;
        }
    };

    String channelId;
    WR3223Commands wr3223Command;

    public String getChannelId() {
        return channelId;
    }

    public WR3223Commands getWr3223Command() {
        return wr3223Command;
    }

    public static Optional<WR3223CommandType> getByChannelId(String channelId) {
        return Arrays.stream(values()).filter(val -> val.getChannelId().equals(channelId)).findFirst();
    }
}
