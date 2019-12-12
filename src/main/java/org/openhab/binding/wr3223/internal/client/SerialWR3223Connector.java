/**
 * Copyright (c) 2010-2019 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.wr3223.internal.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;
import org.eclipse.smarthome.io.transport.serial.PortInUseException;
import org.eclipse.smarthome.io.transport.serial.SerialPort;
import org.eclipse.smarthome.io.transport.serial.SerialPortIdentifier;
import org.eclipse.smarthome.io.transport.serial.SerialPortManager;
import org.eclipse.smarthome.io.transport.serial.UnsupportedCommOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Connector implementation for a serial port connection to WR3223.
 *
 * @author Michael Fraefel
 *
 */
public class SerialWR3223Connector extends AbstractWR3223Connector {

    private static final Logger logger = LoggerFactory.getLogger(SerialWR3223Connector.class);

    private SerialPort serialPort;
    private SerialPortIdentifier portId;

    /**
     * Connect to WR2332 over serial port.
     */
    public @NonNull ConnectResult connect(final SerialPortManager serialPortManager, final String thingUid,
            final String port, final int baud) {
        // parse ports and if the port is found, initialize the reader
        portId = serialPortManager.getIdentifier(port);
        if (portId == null) {
            return new ConnectResult(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.CONFIGURATION_ERROR,
                    "Port is not known!");
        }

        // initialize serial port
        try {
            serialPort = portId.open(thingUid, 2000);

            // set port parameters
            try {
                serialPort.setSerialPortParams(baud, SerialPort.DATABITS_7, SerialPort.STOPBITS_1,
                        SerialPort.PARITY_EVEN);
            } catch (UnsupportedCommOperationException e) {
                return new ConnectResult(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR,
                        "Serial port doesn't support the configuration 7 data bit, 1 stop bit and parity even.");

            }
            try {
                serialPort.enableReceiveTimeout(2000);
            } catch (UnsupportedCommOperationException ex) {
                logger.warn("Error by adding receive timeout.", ex);
            }

            DataInputStream inputStream = new DataInputStream(serialPort.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(serialPort.getOutputStream());
            connect(inputStream, outputStream);
            return new ConnectResult(ThingStatus.ONLINE);

        } catch (final IOException ex) {
            return new ConnectResult(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, "I/O error!");
        } catch (PortInUseException e) {
            return new ConnectResult(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR,
                    "Port is in use!");
        }

    }

    @Override
    public void close() throws IOException {
        super.close();
        if (serialPort != null) {
            serialPort.close();
            serialPort = null;
        }
    }

}
