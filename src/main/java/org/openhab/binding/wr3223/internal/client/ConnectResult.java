package org.openhab.binding.wr3223.internal.client;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.ThingStatusDetail;

public class ConnectResult {

    private ThingStatus status;
    private ThingStatusDetail statusDetail;
    private String description;

    public ConnectResult(ThingStatus status) {
        this.status = status;
        this.statusDetail = ThingStatusDetail.NONE;
    }

    public ConnectResult(ThingStatus status, ThingStatusDetail statusDetail, @Nullable String description) {
        this.status = status;
        this.statusDetail = statusDetail;
        this.description = description;
    }

    public ThingStatus getStatus() {
        return status;
    }

    public ThingStatusDetail getStatusDetail() {
        return statusDetail;
    }

    public String getDescription() {
        return description;
    }

}
