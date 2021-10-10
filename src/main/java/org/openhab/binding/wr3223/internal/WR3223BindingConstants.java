/**
 * Copyright (c) 2014,2019 by the respective copyright holders.
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.wr3223.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.core.thing.ThingTypeUID;

/**
 * The {@link WR3223BindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Michael Fraefel - Initial contribution
 */
@NonNullByDefault
public class WR3223BindingConstants {

    private static final String BINDING_ID = "wr3223";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_CONTROLLER = new ThingTypeUID(BINDING_ID, "controller");

    // List of all Channel ids
    public static final String CHANNEL_TEMPERATURE_EVAPORATOR = "temperatureEvaporator";
    public static final String CHANNEL_TEMPERATURE_CONDENSER = "temperatureCondenser";
    public static final String CHANNEL_TEMPERATURE_OUTSIDE = "temperatureOutside";
    public static final String CHANNEL_TEMPERATURE_OUTGOING_AIR = "temperatureOutgoingAir";
    public static final String CHANNEL_TEMPERATURE_AFTER_HEAT_EXCHANGER = "temperatureAfterHeatExchanger";
    public static final String CHANNEL_TEMPERATURE_SUPPLY_AIR = "temperatureSupplyAir";
    public static final String CHANNEL_TEMPERATURE_AFTER_BRINE_PREHEATING = "temperatureAfterBrinePreheating";
    public static final String CHANNEL_TEMPERATURE_AFTER_PREHEATING = "temperatureAfterPreheating";
    public static final String CHANNEL_ROTATION_SPEED_SUPPLY_AIR_MOTOR = "rotationSpeedSupplyAirMotor";
    public static final String CHANNEL_ROTATION_SPEED_EXHAUST_AIR_MOTOR = "rotationSpeedExhaustAirMotor";
    public static final String CHANNEL_BYPASS = "bypass";
    public static final String CHANNEL_COMPRESSOR = "compressor";
    public static final String CHANNEL_ADDITIONAL_HEATER_RELAIS = "additionalHeaterRelais";
    public static final String CHANNEL_ADDITIONAL_HEATER_STATUS = "additionalHeaterStatus";
    public static final String CHANNEL_ADDITIONAL_HEATER_OPEN = "additionalHeaterOpen";
    public static final String CHANNEL_ADDITIONAL_HEATER_ACTIVATE = "additionalHeaterActivate";
    public static final String CHANNEL_BYPASS_RELAY = "bypassRelay";
    public static final String CHANNEL_PREHEATING_RADIATOR_ACTIVE = "preheatingRadiatorActive";
    public static final String CHANNEL_CONTROL_DEVICE_ACTIVE = "controlDeviceActive";
    public static final String CHANNEL_EARTH_HEAT_EXCHANGER = "earthHeatExchanger";
    public static final String CHANNEL_MAGNET_VALVE = "magnetValve";
    public static final String CHANNEL_OPENHAB_INTERFACE_ACTIVE = "openhabInterfaceActive";
    public static final String CHANNEL_PREHEATING_RADIATOR = "preheatingRadiator";
    public static final String CHANNEL_WARM_WATER_POST_HEATER = "warmWaterPostHeater";
    public static final String CHANNEL_VENTILATION_LEVEL_AVAILABLE = "ventilationLevelAvailable";
    public static final String CHANNEL_VENTILATION_LEVEL = "ventilationLevel";
    public static final String CHANNEL_OPERATION_MODE = "operationMode";
    public static final String CHANNEL_COOLING_MODE_ACTIVATE = "coolingMode";
    public static final String CHANNEL_TEMPERATURE_SUPPLY_AIR_TARGET = "temperatureSupplyAirTarget";
    public static final String CHANNEL_HEAT_FEEDBACK_RATE = "heatFeedbackRate";
    public static final String CHANNEL_SPEED_DEVIATION_MAX_LEVEL_1 = "speedDeviationMaxLevel1";
    public static final String CHANNEL_SPEED_DEVIATION_MAX_LEVEL_2 = "speedDeviationMaxLevel2";
    public static final String CHANNEL_SPEED_DEVIATION_MAX_LEVEL_3 = "speedDeviationMaxLevel3";
    public static final String CHANNEL_SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_1 = "speedIncreaseEarthHeatExchangerLevel1";
    public static final String CHANNEL_SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_2 = "speedIncreaseEarthHeatExchangerLevel2";
    public static final String CHANNEL_SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_3 = "speedIncreaseEarthHeatExchangerLevel3";
    public static final String CHANNEL_AIR_EXCHANGE_DECREASE_OUTSIDE_TEMPERATURE = "airExchangeDecreaseOutsideTemperature";
    public static final String CHANNEL_VENTILATION_SPEED_LEVEL_1 = "ventilationSpeedLevel1";
    public static final String CHANNEL_VENTILATION_SPEED_LEVEL_2 = "ventilationSpeedLevel2";
    public static final String CHANNEL_VENTILATION_SPEED_LEVEL_3 = "ventilationSpeedLevel3";
    public static final String CHANNEL_SUMMER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE = "summerEarthHeatExchangerActivationTemperature";
    public static final String CHANNEL_WINTER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE = "winterEarthHeatExchangerActivationTemperature";
    public static final String CHANNEL_DEFROSTING_START_TEMPERATURE = "defrostingStartTemperature";
    public static final String CHANNEL_DEFROSTING_END_TEMPERATURE = "defrostingEndTemperature";
    public static final String CHANNEL_DEFROSTING_VENTILATION_LEVEL = "defrostingVentilationLevel";
    public static final String CHANNEL_DEFROSTING_HOLD_OFF_TIME = "defrostingHoldOffTime";
    public static final String CHANNEL_DEFROSTING_OVERTRAVEL_TIME = "defrostingOvertravelTime";
    public static final String CHANNEL_DEFROSTING_HEAT_FEEDBACK_RATE = "defrostingHeatFeedbackRate";
    public static final String CHANNEL_SOLAR_MAX = "solarMax";
    public static final String CHANNEL_SOLAR_USAGE = "solarUsage";
    public static final String CHANNEL_DELTA_T_OFF = "deltaTOff";
    public static final String CHANNEL_DELTA_T_ON = "deltaTOn";
    public static final String CHANNEL_TEMPERATURE_CONDENSER_MAX = "temperatureCondenserMax";
    public static final String CHANNEL_IDLE_TIME_PRESSURE_REDUCTION = "idleTimePressureReduction";
    public static final String CHANNEL_SUPPORT_FAN_LEVEL_1_EARTH_HEAT_EXCHANGER = "supportFanLevel1EarthHeatExchanger";
    public static final String CHANNEL_SUPPORT_FAN_LEVEL_2_EARTH_HEAT_EXCHANGER = "supportFanLevel2EarthHeatExchanger";
    public static final String CHANNEL_SUPPORT_FAN_LEVEL_3_EARTH_HEAT_EXCHANGER = "supportFanLevel3EarthHeatExchanger";
    public static final String CHANNEL_CONTROL_VOLTAGE_OUTGOING_AIR = "controlVoltageOutgoingAir";
    public static final String CHANNEL_CONTROL_VOLTAGE_SUPPLY_AIR = "controlVoltageSupplyAir";
    public static final String CHANNEL_WARM_WATER_TARGET_TEMPERATURE = "warmWaterTargetTemperature";
    public static final String CHANNEL_HEAT_PUMP_STATUS = "heatPumpStatus";
    public static final String CHANNEL_HEAT_PUMP_OPEN = "heatPumpOpen";
    public static final String CHANNEL_HEAT_PUMP_ACTIVATE = "heatPumpActivate";
    public static final String CHANNEL_EVU_BLOCKADE = "evuBlockade";
    public static final String CHANNEL_MALFUNCTION = "malfunction";
    public static final String CHANNEL_ERROR_TEMP_SENSOR_SHORT = "errorTempSensorShort";
    public static final String CHANNEL_ERROR_OFFSET = "errorOffset";
    public static final String CHANNEL_ERROR_TEMP_SENSOR_INTERUPT = "errorTempSensorInterupt";
    public static final String CHANNEL_ERROR_HIGH_PRESSURE = "errorHighPressure";
    public static final String CHANNEL_ERROR_SYS_RAM = "errorSysRam";
    public static final String CHANNEL_ERROR_SYS_ROM = "errorSysRom";
    public static final String CHANNEL_ERROR_SYS_EE = "errorSysEe";
    public static final String CHANNEL_ERROR_SYS_IO = "errorSysIo";
    public static final String CHANNEL_ERROR_SYS_67_AD = "errorSys67Ad";
    public static final String CHANNEL_ERROR_SUPPLY_AIR = "errorSupplyAir";
    public static final String CHANNEL_ERROR_OUTGOING_AIR = "errorOutgoingAir";
    public static final String CHANNEL_ERROR_CONDENSER = "errorCondenser";
    public static final String CHANNEL_ERROR_PREHEATING = "errorPreheating";
}
