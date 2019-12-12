package org.openhab.binding.wr3223.internal.controller;

import java.io.Closeable;
import java.io.IOException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.library.types.OnOffType;
import org.eclipse.smarthome.core.library.types.OpenClosedType;
import org.eclipse.smarthome.core.library.types.QuantityType;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.io.transport.serial.SerialPortManager;
import org.openhab.binding.wr3223.internal.WR3223Configuration;
import org.openhab.binding.wr3223.internal.client.AbstractWR3223Connector;
import org.openhab.binding.wr3223.internal.client.ConnectResult;
import org.openhab.binding.wr3223.internal.client.SerialWR3223Connector;
import org.openhab.binding.wr3223.internal.client.TcpWR3223Connector;
import org.openhab.binding.wr3223.internal.client.WR3223Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WR3223Controller implements Runnable, Closeable {

    public interface ThingHandler {

        void publishValueToBoundChannel(@NonNull WR3223CommandType wr3223CommandType, Object value);

        boolean isLinked(@NonNull WR3223CommandType wr3223CommandType);

        void updateStatus(@NonNull ThingStatus status, @NonNull ThingStatusDetail statusDetail, String description);

    }

    private static final WR3223CommandType[] READ_COMMANDS = { WR3223CommandType.TEMPERATURE_EVAPORATOR,
            WR3223CommandType.TEMPERATURE_CONDENSER, WR3223CommandType.TEMPERATURE_OUTSIDE,
            WR3223CommandType.TEMPERATURE_OUTGOING_AIR, WR3223CommandType.TEMPERATURE_AFTER_HEAT_EXCHANGER,
            WR3223CommandType.TEMPERATURE_SUPPLY_AIR, WR3223CommandType.TEMPERATURE_AFTER_BRINE_PREHEATING,
            WR3223CommandType.TEMPERATURE_AFTER_PREHEATING, WR3223CommandType.VENTILATION_LEVEL,
            WR3223CommandType.ROTATION_SPEED_SUPPLY_AIR_MOTOR, WR3223CommandType.ROTATION_SPEED_EXHAUST_AIR_MOTOR,
            WR3223CommandType.OPERATION_MODE, WR3223CommandType.TEMPERATURE_SUPPLY_AIR_TARGET,
            WR3223CommandType.HEAT_FEEDBACK_RATE, WR3223CommandType.SPEED_DEVIATION_MAX_LEVEL_1,
            WR3223CommandType.SPEED_DEVIATION_MAX_LEVEL_2, WR3223CommandType.SPEED_DEVIATION_MAX_LEVEL_3,
            WR3223CommandType.SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_1,
            WR3223CommandType.SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_2,
            WR3223CommandType.SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_3,
            WR3223CommandType.AIR_EXCHANGE_DECREASE_OUTSIDE_TEMPERATURE, WR3223CommandType.VENTILATION_SPEED_LEVEL_1,
            WR3223CommandType.VENTILATION_SPEED_LEVEL_2, WR3223CommandType.VENTILATION_SPEED_LEVEL_3,
            WR3223CommandType.SUMMER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE,
            WR3223CommandType.WINTER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE,
            WR3223CommandType.DEFROSTING_START_TEMPERATURE, WR3223CommandType.DEFROSTING_END_TEMPERATURE,
            WR3223CommandType.DEFROSTING_VENTILATION_LEVEL, WR3223CommandType.DEFROSTING_HOLD_OFF_TIME,
            WR3223CommandType.DEFROSTING_OVERTRAVEL_TIME, WR3223CommandType.DEFROSTING_HEAT_FEEDBACK_RATE,
            WR3223CommandType.SOLAR_MAX, WR3223CommandType.SOLAR_USAGE, WR3223CommandType.DELTA_T_OFF,
            WR3223CommandType.DELTA_T_ON, WR3223CommandType.TEMPERATURE_CONDENSER_MAX,
            WR3223CommandType.IDLE_TIME_PRESSURE_REDUCTION, WR3223CommandType.SUPPORT_FAN_LEVEL_1_EARTH_HEAT_EXCHANGER,
            WR3223CommandType.SUPPORT_FAN_LEVEL_2_EARTH_HEAT_EXCHANGER,
            WR3223CommandType.SUPPORT_FAN_LEVEL_3_EARTH_HEAT_EXCHANGER, WR3223CommandType.CONTROL_VOLTAGE_OUTGOING_AIR,
            WR3223CommandType.CONTROL_VOLTAGE_SUPPLY_AIR, WR3223CommandType.WARM_WATER_TARGET_TEMPERATURE,
            WR3223CommandType.HEAT_PUMP_OPEN, WR3223CommandType.ADDITIONAL_HEATER_OPEN };

    private static final EnumSet<WR3223CommandType> WRITE_COMMANDS = EnumSet.of(WR3223CommandType.OPERATION_MODE,
            WR3223CommandType.TEMPERATURE_SUPPLY_AIR_TARGET, WR3223CommandType.SPEED_DEVIATION_MAX_LEVEL_1,
            WR3223CommandType.SPEED_DEVIATION_MAX_LEVEL_2, WR3223CommandType.SPEED_DEVIATION_MAX_LEVEL_3,
            WR3223CommandType.SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_1,
            WR3223CommandType.SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_2,
            WR3223CommandType.SPEED_INCREASE_EARTH_HEAT_EXCHANGER_LEVEL_3,
            WR3223CommandType.AIR_EXCHANGE_DECREASE_OUTSIDE_TEMPERATURE, WR3223CommandType.VENTILATION_SPEED_LEVEL_1,
            WR3223CommandType.VENTILATION_SPEED_LEVEL_2, WR3223CommandType.VENTILATION_SPEED_LEVEL_3,
            WR3223CommandType.SUMMER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE,
            WR3223CommandType.WINTER_EARTH_HEAT_EXCHANGER_ACTIVATION_TEMPERATURE,
            WR3223CommandType.DEFROSTING_START_TEMPERATURE, WR3223CommandType.DEFROSTING_END_TEMPERATURE,
            WR3223CommandType.DEFROSTING_VENTILATION_LEVEL, WR3223CommandType.DEFROSTING_HOLD_OFF_TIME,
            WR3223CommandType.DEFROSTING_OVERTRAVEL_TIME, WR3223CommandType.DEFROSTING_HEAT_FEEDBACK_RATE,
            WR3223CommandType.SOLAR_MAX, WR3223CommandType.DELTA_T_OFF, WR3223CommandType.DELTA_T_ON,
            WR3223CommandType.TEMPERATURE_CONDENSER_MAX, WR3223CommandType.IDLE_TIME_PRESSURE_REDUCTION,
            WR3223CommandType.SUPPORT_FAN_LEVEL_1_EARTH_HEAT_EXCHANGER,
            WR3223CommandType.SUPPORT_FAN_LEVEL_2_EARTH_HEAT_EXCHANGER,
            WR3223CommandType.SUPPORT_FAN_LEVEL_3_EARTH_HEAT_EXCHANGER, WR3223CommandType.WARM_WATER_TARGET_TEMPERATURE,
            WR3223CommandType.HEAT_PUMP_OPEN, WR3223CommandType.ADDITIONAL_HEATER_OPEN);

    private final Logger logger = LoggerFactory.getLogger(WR3223Controller.class);

    private final WR3223Configuration config;
    private final SerialPortManager serialPortManager;

    /**
     * Status of the WR3223
     */
    private StatusValueHolder statusHolder = new StatusValueHolder();

    /**
     * Store the value to update
     */
    private Map<WR3223CommandType, Integer> updateMap = new HashMap<WR3223CommandType, Integer>();

    private ThingHandler thingHandler;

    /**
     * WR3223 Connector
     */
    @Nullable
    private AbstractWR3223Connector connector;

    /**
     * Not all controller supports all commands. The binding automatically disable not supported commands.
     */
    private Set<WR3223Commands> disabledCommands = new HashSet<>();

    private String thingId;

    public WR3223Controller(WR3223Configuration config, SerialPortManager serialPortManager, String thingId,
            ThingHandler thingHandler) {
        this.config = config;
        this.serialPortManager = serialPortManager;
        this.thingHandler = thingHandler;
        this.thingId = thingId;
    }

    @Override
    public void run() {

        // Connector if not already connected.
        if (connector == null) {
            initConnection();
        }

        if (connector != null) {
            try {

                // Read status values (Ta)
                if (checkIfCommandIsAvailable(WR3223Commands.Ta)) {
                    statusHolder.valueOf(connector.read(config.controllerAddr, WR3223Commands.Ta));
                    thingHandler.publishValueToBoundChannel(WR3223CommandType.MALFUNCTION,
                            statusHolder.isMalfunction());
                    thingHandler.publishValueToBoundChannel(WR3223CommandType.HEAT_PUMP_STATUS,
                            statusHolder.getHeatPumpOnStatus());
                    thingHandler.publishValueToBoundChannel(WR3223CommandType.ADDITIONAL_HEATER_STATUS,
                            statusHolder.getAdditionalHeatingOnStatus());
                }

                // EVU Blockade handling (Tf)
                if (checkIfCommandIsAvailable(WR3223Commands.Tf)) {
                    EvuBlockadeHandler handler = EvuBlockadeHandler
                            .valueOf(connector.read(config.controllerAddr, WR3223Commands.Tf));
                    thingHandler.publishValueToBoundChannel(WR3223CommandType.EVU_BLOCKADE, handler.isBlockade());
                }

                // Read relais
                RelaisValueDecoder relais = readAndPublishRelaisValues();

                // Read errors
                readAndPublishErrorValues();

                // Write values if no control device connected
                if (!relais.isControlDeviceActive()) {
                    if (connector.write(config.controllerAddr, WR3223Commands.SW, statusHolder.getStatusValue())) {

                        // Commit value updates to WR3223
                        Iterator<Entry<WR3223CommandType, Integer>> it = updateMap.entrySet().iterator();
                        while (it.hasNext()) {
                            Entry<WR3223CommandType, Integer> update = it.next();
                            if (connector.write(config.controllerAddr, update.getKey().getWr3223Command(),
                                    String.valueOf(update.getValue()))) {
                                it.remove();
                            }
                        }
                    } else {
                        logger.error("Couldn't send keep alive message to WR3223.");
                    }
                } else {
                    logger.warn(
                            "The control device is active! Openhab can only control the WR3223, when the control device is removed. (Bedienteil)");
                }

                // Read and publish other values from WR3223
                for (WR3223CommandType readCommand : READ_COMMANDS) {
                    if (checkIfCommandIsAvailable(readCommand.getWr3223Command())) {
                        if (!updateMap.containsKey(readCommand)) {
                            readAndPublishValue(readCommand);
                        } else {
                            logger.info(
                                    "Skip reading values for command {} from WR3223, because an updated value must first be sent to WR3223.",
                                    readCommand.name());
                        }
                    }
                }
            } catch (IOException e) {
                logger.error("Communication error to WR3223.", e);
                if (connector != null) {
                    try {
                        connector.close();
                    } catch (IOException e1) {
                        logger.error("Couldn't close communication to WR3223.", e1);
                    }
                }
                connector = null;
                disabledCommands.clear();
            }

        }
    }

    @Override
    public void close() {
        logger.info("Controller closed.");
        if (connector != null) {
            try {
                connector.close();
            } catch (IOException e1) {
                logger.error("Couldn't close communication to WR3223.", e1);
            }
        }
        connector = null;
        disabledCommands.clear();
    }

    public void handleCommand(ChannelUID channelUID, Command command) {
        Optional<WR3223CommandType> commandType = WR3223CommandType.getByChannelId(channelUID.getId());
        if (!commandType.isPresent()) {
            logger.info("Skip write values for channel UID {}, because channel is unknown in WR3223.", channelUID);
            return;
        }

        // Special commands
        if (commandType.get() == WR3223CommandType.VENTILATION_LEVEL) {
            if (command instanceof Number) {
                int value = ((Number) command).intValue();
                if (value >= 0 && value <= 3) {
                    statusHolder.setVentilationLevel(value);
                } else {
                    logger.warn("WR3223 value {} of channel UID {} out of range.", value, channelUID);
                }
            } else {
                logger.warn("WR3223 channel UID {} must be from type:{} but is {}.", channelUID,
                        DecimalType.class.getSimpleName(), command.getClass());
            }
        } else if (commandType.get() == WR3223CommandType.ADDITIONAL_HEATER_ACTIVATE) {
            if (command instanceof OnOffType) {
                if (command == OnOffType.ON) {
                    statusHolder.setAdditionalHeatingOn(true);
                } else {
                    statusHolder.setAdditionalHeatingOn(false);
                }
            } else {
                logger.warn("WR3223 channel UID {} must be from type:{}.", channelUID, OnOffType.class.getSimpleName());
            }
        } else if (commandType.get() == WR3223CommandType.HEAT_PUMP_ACTIVATE) {
            if (command instanceof OnOffType) {
                if (command == OnOffType.ON) {
                    statusHolder.setHeatPumpOn(true);
                } else {
                    statusHolder.setHeatPumpOn(false);
                }
            } else {
                logger.warn("WR3223 channel UID {} must be from type:{}.", channelUID, OnOffType.class.getSimpleName());
            }
        } else if (commandType.get() == WR3223CommandType.COOLING_MODE_ACTIVATE) {
            if (command instanceof OnOffType) {
                if (command == OnOffType.ON) {
                    statusHolder.setCoolingOn(true);
                } else {
                    statusHolder.setCoolingOn(false);
                }
            } else {
                logger.warn("WR3223 channel UID {} must be from type:{}.", channelUID, OnOffType.class.getSimpleName());
            }
        } else {

            if (!WRITE_COMMANDS.contains(commandType.get())) {
                logger.info("Skip write values for channel UID {}, because channel is not writable.", channelUID);
                return;
            }

            Integer value = 0;
            if (command instanceof Number) {
                value = ((Number) command).intValue();
            } else if (command instanceof OnOffType) {
                value = ((OnOffType) command) == OnOffType.ON ? 1 : 0;
            } else if (command instanceof OpenClosedType) {
                value = ((OpenClosedType) command) == OpenClosedType.CLOSED ? 1 : 0;
            } else if (command instanceof QuantityType) {
                value = ((QuantityType) command).intValue();
                logger.warn("Command type {} not supported for channel UID {}.", command.toFullString(), channelUID);
                return;
            }

            // Store the value update for the next communication with wr3223
            updateMap.put(commandType.get(), value);
        }

    }

    private void initConnection() {
        ConnectResult result;
        if (config.host != null) {
            TcpWR3223Connector tcpConnector = new TcpWR3223Connector();
            result = tcpConnector.connect(config.host, config.port);
            connector = tcpConnector;
            logger.info("Connected to WR3223 over tcp to host {}:{}.", config.host, config.port);
        } else if (config.serialPort != null) {
            SerialWR3223Connector serialConnector = new SerialWR3223Connector();
            result = serialConnector.connect(serialPortManager, thingId, config.serialPort, 9600);
            connector = serialConnector;
            logger.info("Connected to WR3223 over serial port {}.", config.serialPort);
        } else {
            result = new ConnectResult(ThingStatus.UNINITIALIZED, ThingStatusDetail.OFFLINE.CONFIGURATION_ERROR,
                    "IP or serial connection must be configured.");
        }
        thingHandler.updateStatus(result.getStatus(), result.getStatusDetail(), result.getDescription());
        if (result.getStatus() != ThingStatus.ONLINE) {
            connector = null;
        }
    }

    /**
     * Check if a command is available.
     *
     * @param command
     * @return
     * @throws IOException
     */
    private boolean checkIfCommandIsAvailable(WR3223Commands command) throws IOException {
        if (disabledCommands.contains(command)) {
            return false;
        }
        String value = connector.read(config.controllerAddr, command);
        if (value == null || value.contains("???")) {
            disabledCommands.add(command);
            logger.warn("Command {} is not supported by the controller.", command);
            return false;
        }
        return true;
    }

    /**
     * @return
     * @throws IOException
     */
    private RelaisValueDecoder readAndPublishRelaisValues() throws IOException {
        RelaisValueDecoder relais = RelaisValueDecoder
                .valueOf(connector.read(config.controllerAddr, WR3223Commands.RL));
        thingHandler.publishValueToBoundChannel(WR3223CommandType.COMPRESSOR, relais.isCompressor());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.ADDITIONAL_HEATER_RELAIS,
                relais.isAdditionalHeater());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.PREHEATING_RADIATOR_ACTIVE,
                relais.isPreHeaterRadiatorActive());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.BYPASS, !relais.isBypass());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.BYPASS_RELAY, relais.isBypassRelay());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.CONTROL_DEVICE_ACTIVE,
                relais.isControlDeviceActive());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.EARTH_HEAT_EXCHANGER, relais.isEarthHeatExchanger());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.MAGNET_VALVE, relais.isMagnetValve());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.OPENHAB_INTERFACE_ACTIVE,
                relais.isOpenhabInterfaceActive());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.PREHEATING_RADIATOR, relais.isPreheatingRadiator());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.VENTILATION_LEVEL_AVAILABLE,
                relais.isVentilationLevelAvailable());
        thingHandler.publishValueToBoundChannel(WR3223CommandType.WARM_WATER_POST_HEATER,
                relais.isWarmWaterPostHeater());
        return relais;
    }

    /**
     * @throws IOException
     */
    private void readAndPublishErrorValues() throws IOException {
        if (checkIfCommandIsAvailable(WR3223Commands.ER)) {
            ErrorValueDecoder errors = ErrorValueDecoder
                    .valueOf(connector.read(config.controllerAddr, WR3223Commands.ER));
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_TEMP_SENSOR_SHORT,
                    errors.isError_temp_sensor_short());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_OFFSET, errors.isError_offset());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_TEMP_SENSOR_INTERUPT,
                    errors.isError_temp_sensor_interupt());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_HIGH_PRESSURE,
                    errors.isError_high_pressure());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_SYS_RAM, errors.isError_sys_ram());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_SYS_ROM, errors.isError_sys_rom());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_SYS_EE, errors.isError_sys_ee());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_SYS_IO, errors.isError_sys_io());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_SYS_67_AD, errors.isError_sys_67_ad());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_SUPPLY_AIR, errors.isError_supply_air());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_OUTGOING_AIR,
                    errors.isError_outgoing_air());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_CONDENSER, errors.isError_condenser());
            thingHandler.publishValueToBoundChannel(WR3223CommandType.ERROR_PREHEATING, errors.isError_preheating());
        }
    }

    /**
     * Read value of given command and publish it to the bus.
     *
     * @param wr3223CommandType
     * @throws IOException
     */
    private void readAndPublishValue(WR3223CommandType wr3223CommandType) throws IOException {
        if (thingHandler.isLinked(wr3223CommandType)) {
            String value = connector.read(config.controllerAddr, wr3223CommandType.getWr3223Command());
            thingHandler.publishValueToBoundChannel(wr3223CommandType, value);
        }
    }

}