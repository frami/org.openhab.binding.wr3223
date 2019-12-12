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
import java.net.Socket;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;

/**
 * Connector implementation for a TCP connection to WR3223.
 *
 * @author Michael Fraefel
 *
 */
public class TcpWR3223Connector extends AbstractWR3223Connector {

    private Socket socket;

    /**
     * Connect to WR3223 over IP
     *
     * @param host
     * @param port
     */
    public @NonNull ConnectResult connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(5000);
            connect(new DataInputStream(socket.getInputStream()), new DataOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            return new ConnectResult(ThingStatus.OFFLINE, ThingStatusDetail.OFFLINE.COMMUNICATION_ERROR, "I/O error!");
        }
        return new ConnectResult(ThingStatus.ONLINE);
    }

    @Override
    public void close() throws IOException {
        super.close();
        if (socket != null && socket.isConnected()) {
            socket.close();
        }
    }

}
