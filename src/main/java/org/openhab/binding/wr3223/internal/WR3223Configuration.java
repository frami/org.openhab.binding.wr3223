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

/**
 * The {@link WR3223Configuration} class contains fields mapping thing configuration parameters.
 *
 * @author Michael Fraefel - Initial contribution
 */
public class WR3223Configuration {

    /**
     * the refresh interval which is used to poll values from the WR3223
     * server (optional, defaults to 15s)
     */
    public int refreshInterval = 15;

    /**
     * Host if connection over IP is used.
     */
    public String host;

    /**
     * Port if connection over IP is used.
     */
    public int port;

    /**
     * Serial port if connection over serial interface is used.
     */
    public String serialPort;

    /**
     * Controller address.
     */
    public int controllerAddr = 1;

    @Override
    public String toString() {
        return "WR3223Configuration{" + "refreshInterval=" + refreshInterval + ", host='" + host + '\'' + ", port="
                + port + ", serialPort='" + serialPort + '\'' + ", controllerAddr=" + controllerAddr + '}';
    }
}
