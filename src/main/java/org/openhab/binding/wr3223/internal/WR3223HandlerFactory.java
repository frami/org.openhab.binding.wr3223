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

import static org.openhab.binding.wr3223.internal.WR3223BindingConstants.THING_TYPE_CONTROLLER;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.eclipse.smarthome.core.thing.binding.ThingHandlerFactory;
import org.eclipse.smarthome.io.transport.serial.SerialPortManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link WR3223HandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Michael Fraefel - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.wr3223", service = ThingHandlerFactory.class)
public class WR3223HandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_CONTROLLER);

    private final Logger logger = LoggerFactory.getLogger(WR3223HandlerFactory.class);

    private @NonNullByDefault({}) SerialPortManager serialPortManager;

    private Map<ThingUID, ThingHandler> instanceMap = new HashMap<>();

    @Reference
    protected void setSerialPortManager(final SerialPortManager serialPortManager) {
        this.serialPortManager = serialPortManager;
    }

    protected void unsetSerialPortManager(final SerialPortManager serialPortManager) {
        this.serialPortManager = null;
    }

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();
        if (THING_TYPE_CONTROLLER.equals(thingTypeUID)) {
            ThingUID uid = thing.getUID();
            if (instanceMap.containsKey(uid)) {
                return instanceMap.get(uid);
            } else {
                ThingHandler thingHandler = new WR3223Handler(thing, serialPortManager);
                instanceMap.put(uid, thingHandler);
                logger.info("New ThingHandler {} created.", uid);
                return thingHandler;
            }
        }
        return null;
    }

    @Override
    protected void removeHandler(ThingHandler thingHandler) {
        ThingUID uid = thingHandler.getThing().getUID();
        instanceMap.remove(uid);
        logger.info("ThingHandler {} removed.", uid);
        super.removeHandler(thingHandler);
    }

}
